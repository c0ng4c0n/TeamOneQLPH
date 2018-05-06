package MuonTra;

import connectmySQL.DataAccess;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import loginqlph.*;
import sample.Main;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class MuonTra implements Initializable {

    @FXML
    private TableView<QLMuonTra> table;
    public ScrollPane scroll = new ScrollPane(table);
    @FXML
    private TableColumn<QLMuonTra, String> mnv;
    @FXML
    private TableColumn<QLMuonTra, String> ml;
    @FXML
    private TableColumn<QLMuonTra, String> mp;
    @FXML
    private TableColumn<QLMuonTra, String> bd;
    @FXML
    private TableColumn<QLMuonTra, String> kt;
    @FXML
    private TableColumn<QLMuonTra, String> tnm;
    @FXML
    private TableColumn<QLMuonTra, String> sdt;
    @FXML
    private TableColumn<QLMuonTra, String> mc;
    @FXML
    private TableColumn<QLMuonTra, String> mi;
    @FXML
    private TableColumn<QLMuonTra, String> dh;

    public TextField namnm, phonm, idph, idlm, idlt;
    public PasswordField palm, palt;
    public CheckBox con, mic, pro;
    public Button mphong, tphong;
    public Label usermuon, usertra;
    public Button qlphocm, qlphoct;
    public Button logoutM, logoutT;
    public AnchorPane MuonPhong, TraPhong;

    public static String toFormat(String s) {
        if (s.length() < 1) return s;
        int i;
        for (i = 0; i < s.length(); ++i)
            s = s.substring(0,i ) + String.valueOf(s.charAt(i)).toLowerCase() + s.substring(i+1,s.length());
        s = s.trim();
        i = 0;
        while (i < s.length() - 1) {
            if (s.charAt(i) == ' ' & s.charAt(i + 1) == ' ') s = s.substring(0,i) + s.substring(i + 1, s.length());
            else i = i + 1;
        }
        s = String.valueOf(s.charAt(0)).toUpperCase() + s.substring(1,s.length());
        for (i = 1; i < s.length(); ++i)
            if (s.charAt(i-1) == ' ')
                s = s.substring(0, i) + String.valueOf(s.charAt(i)).toUpperCase() + s.substring(i+1, s.length());
        return s;
    }

    public static String toUpper(String s) {
        if (s.length() < 1) return s;
        int i;
        for (i = 0; i < s.length(); ++i)
            s = s.substring(0,i ) + String.valueOf(s.charAt(i)).toUpperCase() + s.substring(i+1,s.length());
        return s;
    }

    public void qlphBut(MouseEvent event) throws Exception{
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText("Bạn muốn chuyển qua phần mềm Quản lý phòng học?");
        alert.setContentText(null);
        Stage sta = (Stage) alert.getDialogPane().getScene().getWindow();
        sta.getIcons().add(new Image("/logohvan.png"));
        Optional<ButtonType> rst = alert.showAndWait();
        if (rst.get() == ButtonType.OK) {
            ControllerLogin.tk = usermuon.getText();
            ControllerLogin.ty = "Nhân viên";

            Stage stage = (Stage) qlphocm.getScene().getWindow();
            stage.close();

            Parent root = FXMLLoader.load(getClass().getResource("/sample/sample.fxml"));
            Stage stg = new Stage();
            Scene scene = new Scene(root);
            stg.setTitle("Quản Lý Phòng Học");
            stg.initStyle(StageStyle.DECORATED);
            stg.setScene(scene);
            stg.getIcons().add(new Image("/logohvan.png"));
            stg.show();
            stg.setMinWidth(600);
            stg.setMinHeight(400);
        }
    }

    public void tphongBut(MouseEvent event) throws Exception{
        if (table.getSelectionModel().getSelectedItem() == null) return;
        if (table.getSelectionModel().getSelectedItem().getKt().length() > 0) return;
        String user = idlt.getText(); if (user.length() < 1) user = "";
        String pass = palt.getText(); if (pass.length() < 1) pass = "";
        Connection conn = DataAccess.ketNoi();
        Statement comm = conn.createStatement();
        String strsql;

        if (user == "" | pass == "") {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Vui lòng nhập đầy đủ thông tin!");
            alert.setContentText(null);
            Stage sta = (Stage) alert.getDialogPane().getScene().getWindow();
            sta.getIcons().add(new Image("/logohvan.png"));
            conn.close();
            alert.showAndWait();
        } else {
            pass = Main.md5(pass);
            strsql = "SELECT * FROM account WHERE (username = '" + user + "' AND password = '" + pass + "' AND type = 'Lớp học')";
            ResultSet rs = comm.executeQuery(strsql);
            if (!rs.next()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Thông báo");
                alert.setHeaderText("Bạn đã nhập sai mã lớp hoặc mật khẩu!");
                alert.setContentText(null);
                Stage sta = (Stage) alert.getDialogPane().getScene().getWindow();
                sta.getIcons().add(new Image("/logohvan.png"));
                conn.close();
                alert.showAndWait();
            } else {
                String lhoc = table.getSelectionModel().getSelectedItem().getMl();
                if (user.startsWith(lhoc) & user.length() == lhoc.length()) {
                    String nvien = table.getSelectionModel().getSelectedItem().getMnv();
                    String phoc = table.getSelectionModel().getSelectedItem().getMp();
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date date = new Date();
                    String end = dateFormat.format(date);
                    strsql = "UPDATE muon_phong SET TGKetThuc = '" + end + "' WHERE muon_phong.MaNV = '" + nvien
                            + "' AND muon_phong.MaLop = '" + lhoc + "' AND muon_phong.MaPhong = '" + phoc + "'";

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Thông báo");
                    alert.setHeaderText("Bạn chắc chắn muốn trả phòng");
                    alert.setContentText(null);
                    Stage sta = (Stage) alert.getDialogPane().getScene().getWindow();
                    sta.getIcons().add(new Image("/logohvan.png"));
                    Optional<ButtonType> rst = alert.showAndWait();
                    if (rst.get() == ButtonType.OK) {
                        comm.execute(strsql);

                        ObservableList<QLMuonTra> data = FXCollections.observableArrayList();
                        MainMT service = new MainMT();
                        List<Object[]> istmt = service.laydsmt();

                        for (Object[] i : istmt) {
                            data.add(new QLMuonTra(i[0].toString(), i[1].toString(), i[2].toString(), i[3].toString(), i[4].toString(),
                                    i[5].toString(), i[6].toString(), i[7].toString(), i[8].toString(), i[9].toString()));
                        }
                        table.setItems(data);
                    }
                    strsql = "UPDATE phong_hoc SET TinhTrang = 'Trống' WHERE phong_hoc.MaPhong = '" + phoc + "'";
                    comm.execute(strsql);
                    conn.close();

                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Thông báo");
                    alert.setHeaderText("Bạn không thể trả phòng cho lớp khác!");
                    alert.setContentText(null);
                    Stage sta = (Stage) alert.getDialogPane().getScene().getWindow();
                    sta.getIcons().add(new Image("/logohvan.png"));
                    conn.close();
                    alert.showAndWait();
                }
            }
        }
    }

    public void SearchMlop(ActionEvent event) throws Exception{
        String user = idlt.getText();
        Connection conn = DataAccess.ketNoi();
        Statement comm = conn.createStatement();
        String strsql = "SELECT * FROM muon_phong WHERE MaLop LIKE '%" + user + "%'";
        ResultSet rs = comm.executeQuery(strsql);

        List<Object[]> istmt = new ArrayList();
        Object[] mt = null;
        while (rs.next()) {
            mt = new Object[10];
            mt[0] = rs.getString("MaNV");
            mt[1] = rs.getString("MaLop");
            mt[2] = rs.getString("MaPhong");
            mt[3] = rs.getString("TGBatDau");
            mt[3] = mt[3].toString().substring(0,19);
            mt[4] = rs.getString("TGKetThuc");
            mt[4] = mt[4].toString().substring(0,19);
            if (mt[4].toString().startsWith("2001")) mt[4] = "";
            mt[5] = rs.getString("HoTenNM");
            mt[6] = rs.getString("SDTNM");
            mt[7] = rs.getString("MayChieu");
            mt[8] = rs.getString("Micro");
            mt[9] = rs.getString("DKDieuHoa");
            istmt.add(mt);
        }
        ObservableList<QLMuonTra> data = FXCollections.observableArrayList();
        for (Object[] i : istmt) {
            data.add(new QLMuonTra(i[0].toString(), i[1].toString(), i[2].toString(), i[3].toString(), i[4].toString(),
                    i[5].toString(), i[6].toString(), i[7].toString(), i[8].toString(), i[9].toString()))   ;
        }
        table.setItems(data);
        conn.close();
    }

    public void mphongBut(MouseEvent event) throws Exception {
        String name = namnm.getText();
        if (name.length() < 1) name = "";
        String phone = phonm.getText();
        if (phone.length() < 1) phone = "";
        String idphong = idph.getText();
        idphong = toUpper(idphong);
        if (idphong.length() < 1) idphong = "";
        String idlop = idlm.getText();
        idlop = toUpper(idlop);
        if (idlop.length() < 1) idlop = "";
        String idnvien = usermuon.getText();
        String palop = palm.getText();
        if (palop.length() < 1) palop = "";
        String micm, conm, prom;
        if (mic.isSelected()) micm = "Có";
        else micm = "Không";
        if (con.isSelected()) conm = "Có";
        else conm = "Không";
        if (pro.isSelected()) prom = "Có";
        else prom = "Không";
        if (name == "" | phone == "" | idphong == "" | idlop == "" | palop == "") {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Vui lòng nhập đầy đủ thông tin!");
            alert.setContentText(null);
            Stage sta = (Stage) alert.getDialogPane().getScene().getWindow();
            sta.getIcons().add(new Image("/logohvan.png"));
            alert.showAndWait();
        } else {
            Connection conn = DataAccess.ketNoi();
            Statement comm = conn.createStatement();
            String pw = Main.md5(palop);
            String strsql = "SELECT * FROM account WHERE (username = '" + idlop + "' AND password = '" + pw + "' AND type = 'Lớp học')";
            ResultSet rs = comm.executeQuery(strsql);
            if (rs.next()) {
//                strsql = "SELECT * FROM muon_phong WHERE (MaNV = '" + idnvien + "' AND MaLop = '" + idlop + "' AND MaPhong = '" + idphong + "')";
//                rs = comm.executeQuery(strsql);
//                if (rs.next()) {
//                    Alert alert = new Alert(Alert.AlertType.ERROR);
//                    alert.setTitle("Thông báo");
//                    alert.setHeaderText(null);
//                    alert.setContentText("Thông tin bạn nhập bị trùng lặp!");
//                    alert.showAndWait();
//                } else {
                    strsql = "SELECT * FROM phong_hoc WHERE MaPhong = '" + idphong + "'";
                    rs = comm.executeQuery(strsql);
                    if (!rs.next()) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Thông báo");
                        alert.setHeaderText(null);
                        alert.setContentText("Mã phòng không tồn tại!");
                        Stage sta = (Stage) alert.getDialogPane().getScene().getWindow();
                        sta.getIcons().add(new Image("/logohvan.png"));
                        alert.showAndWait();
                        return;
                    } else {
                        if (rs.getString("TinhTrang").startsWith("Đang học")) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Thông báo");
                            alert.setHeaderText(null);
                            alert.setContentText("Phòng bạn cần mượn đang có lớp học!");
                            Stage sta = (Stage) alert.getDialogPane().getScene().getWindow();
                            sta.getIcons().add(new Image("/logohvan.png"));
                            alert.showAndWait();
                            return;
                        }
                        if (rs.getString("TinhTrang").startsWith("Đang sửa")) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Thông báo");
                            alert.setHeaderText(null);
                            alert.setContentText("Phòng bạn cần mượn đang sửa!");
                            Stage sta = (Stage) alert.getDialogPane().getScene().getWindow();
                            sta.getIcons().add(new Image("/logohvan.png"));
                            alert.showAndWait();
                            return;
                        }
                    }
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Thông báo");
                    alert.setHeaderText("Bạn chắc chắn muốn mượn phòng này chứ?");
                    alert.setContentText(null);
                    Stage sta = (Stage) alert.getDialogPane().getScene().getWindow();
                    sta.getIcons().add(new Image("/logohvan.png"));
                    Optional<ButtonType> rst = alert.showAndWait();
                    if (rst.get() == ButtonType.CANCEL) return;

                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date date = new Date();
                    String start = dateFormat.format(date);
                    name = toFormat(name);
                    strsql = "INSERT INTO muon_phong (MaNV, MaLop, MaPhong, TGBatDau, TGKetThuc, HoTenNM, SDTNM, MayChieu, Micro, DKDieuHoa) VALUES ('" + idnvien
                            + "', '" + idlop + "', '" + idphong + "', '" + start + "', '2001-01-01 00:00:00', '" + name + "', '" + phone + "', '" + prom + "', '" + micm + "', '"
                            + conm + "')";
                    comm.execute(strsql);

                    strsql = "UPDATE phong_hoc SET TinhTrang = 'Đang học' WHERE phong_hoc.MaPhong = '" + idphong + "'";
                    comm.execute(strsql);

                    ObservableList<QLMuonTra> data = FXCollections.observableArrayList();
                    MainMT service = new MainMT();
                    List<Object[]> istmt = service.laydsmt();

                    for (Object[] i : istmt) {
                        data.add(new QLMuonTra(i[0].toString(), i[1].toString(), i[2].toString(), i[3].toString(), i[4].toString(),
                                i[5].toString(), i[6].toString(), i[7].toString(), i[8].toString(), i[9].toString()));
                    }
                    table.setItems(data);
                    conn.close();
//                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Thông báo");
                alert.setHeaderText(null);
                alert.setContentText("Mã lớp hoặc mật khẩu không đúng!");
                Stage sta = (Stage) alert.getDialogPane().getScene().getWindow();
                sta.getIcons().add(new Image("/logohvan.png"));
                conn.close();
                alert.showAndWait();
            }
        }
    }

    public void LogoutMT(MouseEvent event) throws Exception{
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText("Bạn chắc chắn muốn đăng xuất chứ?");
        alert.setContentText(null);
        Stage sta = (Stage) alert.getDialogPane().getScene().getWindow();
        sta.getIcons().add(new Image("/logohvan.png"));

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            Stage stage = (Stage) logoutM.getScene().getWindow();
            stage.close();

            Parent root = FXMLLoader.load(getClass().getResource(MainLogin.logscreen));
            Stage stg = new Stage();
            Scene scene = new Scene(root);
            stg.setTitle("Đăng nhập");
            stg.initStyle(StageStyle.DECORATED);
            stg.setScene(scene);
            stg.getIcons().add(new Image("/logohvan.png"));
            stg.show();
            stg.setResizable(false);
        }
    }

    private ObservableList<QLMuonTra> getData() {
        ObservableList<QLMuonTra> data = FXCollections.observableArrayList();
        MainMT service = new MainMT();
        List<Object[]> istmt = service.laydsmt();

        for (Object[] i : istmt) {
            data.add(new QLMuonTra(i[0].toString(), i[1].toString(), i[2].toString(), i[3].toString(), i[4].toString(),
                    i[5].toString(), i[6].toString(), i[7].toString(), i[8].toString(), i[9].toString()))   ;
        }
        return data;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        scroll.setFitToHeight(true);
        scroll.setFitToWidth(true);
        mnv.setCellValueFactory(new PropertyValueFactory<QLMuonTra, String >("mnv"));
        ml.setCellValueFactory(new PropertyValueFactory<QLMuonTra, String >("ml"));
        mp.setCellValueFactory(new PropertyValueFactory<QLMuonTra, String >("mp"));
        bd.setCellValueFactory(new PropertyValueFactory<QLMuonTra, String >("bd"));
        kt.setCellValueFactory(new PropertyValueFactory<QLMuonTra, String >("kt"));
        tnm.setCellValueFactory(new PropertyValueFactory<QLMuonTra, String >("tnm"));
        sdt.setCellValueFactory(new PropertyValueFactory<QLMuonTra, String >("sdt"));
        mc.setCellValueFactory(new PropertyValueFactory<QLMuonTra, String >("mc"));
        mi.setCellValueFactory(new PropertyValueFactory<QLMuonTra, String >("mi"));
        dh.setCellValueFactory(new PropertyValueFactory<QLMuonTra, String >("dh"));
        table.setItems(getData());

        usermuon.setText(LoginMT.usermt);
        usertra.setText(LoginMT.usermt);
    }
}
