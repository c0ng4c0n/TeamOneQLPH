package sample;

import AddEditTK.Ediacc;
import MuonTra.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import loginqlph.ControllerLogin;
import loginqlph.MainLogin;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Controller implements Initializable{


    /******************************************PHONG HOC*********************************************/

    @FXML
    private TableView<Phonghoc> table1;
    @FXML
    public ScrollPane scrollph = new ScrollPane(table1);
    @FXML
    private TableColumn<Phonghoc, String> mp;
    @FXML
    private TableColumn<Phonghoc, String> lp;
    @FXML
    private TableColumn<Phonghoc, String> tt;
    @FXML
    private TableColumn<Phonghoc, String> sc;
    @FXML
    private TableColumn<Phonghoc, String> mc;
    @FXML
    private TableColumn<Phonghoc, String> dh;
    @FXML
    private TableColumn<Phonghoc, String> mi;
    @FXML
    private TableColumn<Phonghoc, String> ca;

    public Tab tabroom;
    public JFXTextField idroom, caproom;
    public JFXComboBox<String> conroom, proroom, microom, camroom, typroom, staroom;

    ObservableList<String> listtyp = FXCollections.observableArrayList("Tất cả", "Phòng thường", "Phòng máy", "Hội trường");
    ObservableList<String> liststa = FXCollections.observableArrayList("Tất cả", "Đang học", "Trống", "Đang sửa");
    ObservableList<String> listcs = FXCollections.observableArrayList("Tất cả", "Có", "Không");

    public void Runeventph() {

        String id = idroom.getText();

        String cap = caproom.getText();
        if (cap.length() < 1) cap = "0";

        String typ = typroom.getValue();
        if (typ == null) typ = "";
        if (typ == "Tất cả") typ = "";

        String sta = staroom.getValue();
        if (sta == null) sta = "";
        if (sta == "Tất cả") sta = "";

        String pro = proroom.getValue();
        if (pro == null) pro = "";
        if (pro == "Tất cả") pro = "";

        String cam = camroom.getValue();
        if (cam == null) cam = "";
        if (cam == "Tất cả") cam = "";

        String con = conroom.getValue();
        if (con == null) con = "";
        if (con == "Tất cả") con = "";

        String mic = microom.getValue();
        if (mic == null) mic = "";
        if (mic == "Tất cả") mic = "";

        List<Object[]> istph = new ArrayList();

        try {
            Connection conn = DataAccess.ketNoi();

            Statement comm = conn.createStatement();

            String strsql = "SELECT * FROM phong_hoc WHERE (MaPhong LIKE '%" + id + "%' AND SucChua >= " + cap +
                    " AND LoaiPhong LIKE '%" + typ + "' AND TinhTrang LIKE '%" + sta + "' AND MayChieu LIKE '%" + pro +
                    "' AND DieuHoa LIKE '%" + con + "' AND Micro LIKE '%" + mic + "' AND Camera LIKE '%" + cam + "')";
            ResultSet rs = comm.executeQuery(strsql);

            Object[] phong = null;
            while (rs.next()) {
                phong = new Object[8];
                phong[0] = rs.getString("MaPhong");
                phong[1] = rs.getString("LoaiPhong");
                phong[2] = rs.getString("TinhTrang");
                phong[3] = rs.getString("SucChua");
                phong[4] = rs.getString("MayChieu");
                phong[5] = rs.getString("DieuHoa");
                phong[6] = rs.getString("Micro");
                phong[7] = rs.getString("Camera");
                istph.add(phong);
            }

            conn.close();

        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        ObservableList<Phonghoc> data = FXCollections.observableArrayList();
        for (Object[] i : istph) {
            data.add(new Phonghoc(i[0].toString(), i[1].toString(), i[2].toString(), i[3].toString(), i[4].toString(), i[5].toString(), i[6].toString(), i[7].toString()))   ;
        }
        table1.setItems(data);
    }

    public void Acteventph(ActionEvent event) {
        Runeventph();
    }

    public void Moueventph(MouseEvent event) {
        Runeventph();
    }

    public void Keyeventph(KeyEvent event) {
        Runeventph();
    }

    private ObservableList<Phonghoc> getData() {
        ObservableList<Phonghoc> data = FXCollections.observableArrayList();
        Main service = new Main();
        List<Object[]> istphong = service.laydsphong();

        for (Object[] i : istphong) {
            data.add(new Phonghoc(i[0].toString(), i[1].toString(), i[2].toString(), i[3].toString(), i[4].toString(),
                    i[5].toString(), i[6].toString(), i[7].toString()))   ;
        }
        return data;
    }

    public Button resph;
    public void ResphBut(MouseEvent event) {
        idroom.setText("");
        caproom.setText("");
        conroom.setValue("");
        proroom.setValue("");
        microom.setValue("");
        typroom.setValue("");
        staroom.setValue("");
        camroom.setValue("");
        ObservableList<Phonghoc> data = FXCollections.observableArrayList();
        Main service = new Main();
        List<Object[]> istphong = service.laydsphong();

        for (Object[] i : istphong) {
            data.add(new Phonghoc(i[0].toString(), i[1].toString(), i[2].toString(), i[3].toString(), i[4].toString(),
                    i[5].toString(), i[6].toString(), i[7].toString()))   ;
        }
        table1.setItems(data);
    }

    public ContextMenu contextph;
    public void Rightclickph(MouseEvent event) {
        if (event.getButton().toString().equals("SECONDARY")) {
            contextph.show(table1, event.getScreenX(), event.getScreenY());
        }
    }

    public MenuItem addph, editph, delph;
    public void delphAction(ActionEvent event) throws Exception{
        if (table1.getSelectionModel().getSelectedItem() == null) return;
        String maphong = table1.getSelectionModel().getSelectedItem().getMp();
        String tinhtrang = table1.getSelectionModel().getSelectedItem().getTt();
        if (tinhtrang.startsWith("Đang học")) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Không thể xóa phòng đang học!");
            alert.setContentText(null);
            Stage sta = (Stage) alert.getDialogPane().getScene().getWindow();
            sta.getIcons().add(new Image("/logohvan.png"));
            alert.showAndWait();
            return;
        }
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText("Bạn chắc chắn muốn xóa thông tin phòng này chứ?");
        alert.setContentText(null);
        Stage sta = (Stage) alert.getDialogPane().getScene().getWindow();
        sta.getIcons().add(new Image("/logohvan.png"));
        Optional<ButtonType> rs = alert.showAndWait();
        if (rs.get() == ButtonType.OK) {
            Connection conn = DataAccess.ketNoi();
            Statement comm = conn.createStatement();
            String strsql = "DELETE FROM phong_hoc WHERE MaPhong = '" + maphong + "'";
            comm.execute(strsql);
            strsql = "DELETE FROM muon_phong WHERE MaPhong = '" + maphong + "'";
            comm.execute(strsql);

            Runeventph();
        }
    }

    public void addphAction(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/AddEditPH/addph.fxml"));
        Stage stg = new Stage();
        Scene scene = new Scene(root);
        stg.setTitle("Thêm mới");
        stg.setScene(scene);
        stg.initModality(Modality.APPLICATION_MODAL);
        stg.getIcons().add(new Image("/logohvan.png"));
        stg.setResizable(false);
        stg.showAndWait();

        Runeventph();
    }

    public static Phonghoc EditPh;
    public void editphAction(ActionEvent event) throws Exception{
        if (table1.getSelectionModel().getSelectedItem() == null) return;
        if (table1.getSelectionModel().getSelectedItem().getTt().startsWith("Đang học")) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Không thể chỉnh sửa phòng đang học!");
            alert.setContentText(null);
            Stage sta = (Stage) alert.getDialogPane().getScene().getWindow();
            sta.getIcons().add(new Image("/logohvan.png"));
            alert.showAndWait();
            return;
        }
        EditPh = table1.getSelectionModel().getSelectedItem();
        Parent root = FXMLLoader.load(getClass().getResource("/AddEditPH/editph.fxml"));
        Stage stg = new Stage();
        Scene scene = new Scene(root);
        stg.setTitle("Chỉnh sửa");
        stg.setScene(scene);
        stg.initModality(Modality.APPLICATION_MODAL);
        stg.getIcons().add(new Image("/logohvan.png"));
        stg.setResizable(false);
        stg.showAndWait();

        Runeventph();
    }

    /*****************************************GIAO VIEN**************************************************/

    @FXML
    private TableView<Giaovien> table2;
    public ScrollPane scrollgv = new ScrollPane(table2);
    @FXML
    private TableColumn<Giaovien, String> mgv;
    @FXML
    private TableColumn<Giaovien, String> tgv;
    @FXML
    private TableColumn<Giaovien, String> ggv;
    @FXML
    private TableColumn<Giaovien, String> ngv;
    @FXML
    private TableColumn<Giaovien, String> egv;
    @FXML
    private TableColumn<Giaovien, String> kgv;

    public ComboBox<String> sexgv;
    public TextField idgv, namgv, birgv, emagv, facgv;
    ObservableList<String> listsex = FXCollections.observableArrayList("Tất cả", "Nam", "Nữ");

    private ObservableList<Giaovien> getDatagv() {
        ObservableList<Giaovien> datagv = FXCollections.observableArrayList();
        Main service = new Main();
        List<Object[]> istgv = service.laydsgv();

        for (Object[] i : istgv) {
            datagv.add(new Giaovien(i[0].toString(), i[1].toString(), i[2].toString(), i[3].toString(), i[4].toString(),
                    i[5].toString()))   ;
        }
        return datagv;
    }

    public void Runeventgv() {

        String id = idgv.getText();
        String bir = birgv.getText();
        String ema = emagv.getText();
        String fac = facgv.getText();
        String nam = namgv.getText();
        String sex = sexgv.getValue();
        if (sex == null) sex = "";
        if (sex == "Tất cả") sex = "";

        List<Object[]> istgv = new ArrayList();

        try {
            Connection conn = DataAccess.ketNoi();

            Statement comm = conn.createStatement();

            String strsql = "SELECT * FROM giao_vien WHERE (MaGV LIKE '%" + id + "%' AND HoTenGV LIKE '%" + nam + "%' AND GioiTinh LIKE '%" +
                    sex + "%' AND NamSinh LIKE '%" + bir + "%' AND Email LIKE '%" + ema + "%' AND Khoa LIKE '%" + fac + "%')";
            ResultSet rs = comm.executeQuery(strsql);

            Object[] gv = null;
            while (rs.next()) {
                gv = new Object[6];
                gv[0] = rs.getString("MaGV");
                gv[1] = rs.getString("HoTenGV");
                gv[2] = rs.getString("GioiTinh");
                gv[3] = rs.getString("NamSinh");
                String ns = gv[3].toString();
                ns = ns.substring(0,4);
                gv[3] = ns;
                gv[4] = rs.getString("Email");
                gv[5] = rs.getString("Khoa");
                istgv.add(gv);
            }

            conn.close();

        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        ObservableList<Giaovien> data = FXCollections.observableArrayList();
        for (Object[] i : istgv) {
            data.add(new Giaovien(i[0].toString(), i[1].toString(), i[2].toString(), i[3].toString(), i[4].toString(), i[5].toString()))   ;
        }
        table2.setItems(data);
    }

    public void Acteventgv(ActionEvent event) {
        Runeventgv();
    }

    public void Keyeventgv(KeyEvent event) {
        Runeventgv();
    }

    public void Moueventgv(MouseEvent event) {
        Runeventgv();
    }

    public Button resgv;
    public void ResgvBut(MouseEvent event) {
        idgv.setText("");
        namgv.setText("");
        birgv.setText("");
        emagv.setText("");
        facgv.setText("");
        sexgv.setValue("");
        ObservableList<Giaovien> datagv = FXCollections.observableArrayList();
        Main service = new Main();
        List<Object[]> istgv = service.laydsgv();

        for (Object[] i : istgv) {
            datagv.add(new Giaovien(i[0].toString(), i[1].toString(), i[2].toString(), i[3].toString(), i[4].toString(),
                    i[5].toString()))   ;
        }
        table2.setItems(datagv);
    }

    public ContextMenu contextgv;
    public void Rightclickgv(MouseEvent event) {
        if (event.getButton().toString().equals("SECONDARY")) {
            contextph.show(table1, event.getScreenX(), event.getScreenY());
        }
    }

    public MenuItem addgv, editgv, delgv;
    public void delgvAction(ActionEvent event) throws Exception{
        if (table2.getSelectionModel().getSelectedItem() == null) return;
        String magv = table2.getSelectionModel().getSelectedItem().getMgv();

        if (magv.equals(ControllerLogin.tk)) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Không thể xóa giáo viên đang đăng nhập!");
            alert.setContentText(null);
            Stage sta = (Stage) alert.getDialogPane().getScene().getWindow();
            sta.getIcons().add(new Image("/logohvan.png"));
            alert.showAndWait();
            return;
        }

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText("Bạn chắc chắn muốn xóa thông tin giáo viên này chứ?");
        alert.setContentText(null);
        Stage sta = (Stage) alert.getDialogPane().getScene().getWindow();
        sta.getIcons().add(new Image("/logohvan.png"));
        Optional<ButtonType> rs = alert.showAndWait();
        if (rs.get() == ButtonType.OK) {
            Connection conn = DataAccess.ketNoi();
            Statement comm = conn.createStatement();
            String strsql = "DELETE FROM giao_vien WHERE MaGV = '" + magv + "'";
            comm.execute(strsql);
            strsql = "DELETE FROM account WHERE username = '" + magv + "'";
            comm.execute(strsql);
            strsql = "DELETE FROM so_dien_thoai WHERE MaSo = '" + magv + "'";
            comm.execute(strsql);
            strsql = "DELETE FROM muon_phong WHERE MaGV = '" + magv + "'";
            comm.execute(strsql);

            Runeventgd();
            Runeventgv();
            Runeventsdt();
            LoadTk();

        }
    }

    public void addgvAction(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/AddEditGV/addgv.fxml"));
        Stage stg = new Stage();
        Scene scene = new Scene(root);
        stg.setTitle("Thêm mới");
        stg.setScene(scene);
        stg.initModality(Modality.APPLICATION_MODAL);
        stg.getIcons().add(new Image("/logohvan.png"));
        stg.setResizable(false);
        stg.showAndWait();

        Runeventgv();
        LoadTk();
    }

    public static Giaovien EditGv;
    public void editgvAction(ActionEvent event) throws Exception{
        if (table2.getSelectionModel().getSelectedItem() == null) return;
        EditGv = table2.getSelectionModel().getSelectedItem();
        Parent root = FXMLLoader.load(getClass().getResource("/AddEditGV/editgv.fxml"));
        Stage stg = new Stage();
        Scene scene = new Scene(root);
        stg.setTitle("Chỉnh sửa");
        stg.setScene(scene);
        stg.initModality(Modality.APPLICATION_MODAL);
        stg.getIcons().add(new Image("/logohvan.png"));
        stg.setResizable(false);
        stg.showAndWait();

        Runeventgv();
        LoadTk();
        Runeventsdt();
        Runeventgd();
    }

    /*****************************************LOP HOC**************************************************/

    @FXML
    private TableView<Lophoc> table3;
    public ScrollPane scrolllh = new ScrollPane(table3);
    @FXML
    private TableColumn<Lophoc, String> ml;
    @FXML
    private TableColumn<Lophoc, String> tl;
    @FXML
    private TableColumn<Lophoc, String> el;
    @FXML
    private TableColumn<Lophoc, String> sl;
    @FXML
    private TableColumn<Lophoc, String> tlt;
    @FXML
    private TableColumn<Lophoc, String> glt;
    @FXML
    private TableColumn<Lophoc, String> nlt;
    @FXML
    private TableColumn<Lophoc, String> tcn;
    @FXML
    private TableColumn<Lophoc, String> gcn;
    @FXML
    private TableColumn<Lophoc, String> ncn;
    @FXML
    private TableColumn<Lophoc, String> ccn;

    public TextField idlh, namlh, emalh, caplh, namlt, birlt, namcn, bircn, rancn;
    public ComboBox<String> sexcn, sexlt;

    private ObservableList<Lophoc> getDatalh() {
        ObservableList<Lophoc> datalh = FXCollections.observableArrayList();
        Main service = new Main();
        List<Object[]> istlh = service.laydslh();

        for (Object[] i : istlh) {
            datalh.add(new Lophoc(i[0].toString(), i[1].toString(), i[2].toString(), i[3].toString(), i[4].toString(),
                    i[5].toString(),i[6].toString(),i[7].toString(),i[8].toString(),i[9].toString(),i[10].toString()))   ;
        }
        return datalh;
    }

    public void Runeventlh() {

        String id = idlh.getText();
        String nam = namlh.getText();
        String ema = emalh.getText();
        String cap = caplh.getText();
        String nalt = namlt.getText();
        String bilt = birlt.getText();
        String nacn = namcn.getText();
        String bicn = bircn.getText();
        String racn = rancn.getText();
        String selt = sexlt.getValue();
        String secn = sexcn.getValue();

        if (cap.length() < 1) cap = "0";
        if (selt == null) selt = "";
        if (selt == "Tất cả") selt = "";
        if (secn == null) secn = "";
        if (secn == "Tất cả") secn = "";

        List<Object[]> istlh = new ArrayList();

        try {
            Connection conn = DataAccess.ketNoi();

            Statement comm = conn.createStatement();

            String strsql = "SELECT * FROM lop_hoc WHERE (MaLop LIKE '%" + id + "%' AND TenLop LIKE '%" + nam + "%' AND Email LIKE '%" +
                    ema + "%' AND SiSo >= " + cap + " AND HoTenLT LIKE '%" + nalt + "%' AND GioiTinhLT LIKE '%" + selt + "%' AND NamSinhLT LIKE '%" +
                    bilt + "%' AND HoTenCN LIKE '%" + nacn + "%' AND GioiTinhCN LIKE '%" + secn + "%' AND NamSinhCN LIKE '%" + bicn +
                    "%' AND ChucVuCN LIKE '%" + racn + "%')";
            ResultSet rs = comm.executeQuery(strsql);

            Object[] lh = null;
            while (rs.next()) {
                lh = new Object[11];
                lh[0] = rs.getString("MaLop");
                lh[1] = rs.getString("TenLop");
                lh[2] = rs.getString("Email");
                lh[3] = rs.getString("SiSo");
                lh[4] = rs.getString("HoTenLT");
                lh[5] = rs.getString("GioiTinhLT");
                lh[6] = rs.getString("NamSinhLT");
                String s = lh[6].toString();
                lh[6] = s.substring(0,4);
                lh[7] = rs.getString("HoTenCN");
                lh[8] = rs.getString("GioiTinhCN");
                lh[9] = rs.getString("NamSinhCN");
                s = lh[9].toString();
                lh[9] = s.substring(0,4);
                lh[10] = rs.getString("ChucVuCN");
                istlh.add(lh);
            }

            conn.close();

        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        ObservableList<Lophoc> datalh = FXCollections.observableArrayList();
        for (Object[] i : istlh) {
            datalh.add(new Lophoc(i[0].toString(), i[1].toString(), i[2].toString(), i[3].toString(), i[4].toString(),
                    i[5].toString(),i[6].toString(),i[7].toString(),i[8].toString(),i[9].toString(),i[10].toString()));
        }
        table3.setItems(datalh);
    }

    public void Acteventlh(ActionEvent event) {
        Runeventlh();
    }

    public void Keyeventlh(KeyEvent event) {
        Runeventlh();
    }

    public void Moueventlh(MouseEvent event) {
        Runeventlh();
    }

    public Button reslh;
    public void ReslhBut(MouseEvent event) {
        sexcn.setValue("");
        sexlt.setValue("");
        idlh.setText("");
        namlh.setText("");
        emalh.setText("");
        caplh.setText("");
        namlt.setText("");
        birlt.setText("");
        namcn.setText("");
        bircn.setText("");
        rancn.setText("");
        ObservableList<Lophoc> datalh = FXCollections.observableArrayList();
        Main service = new Main();
        List<Object[]> istlh = service.laydslh();

        for (Object[] i : istlh) {
            datalh.add(new Lophoc(i[0].toString(), i[1].toString(), i[2].toString(), i[3].toString(), i[4].toString(),
                    i[5].toString(),i[6].toString(),i[7].toString(),i[8].toString(),i[9].toString(),i[10].toString()))   ;
        }
        table3.setItems(datalh);
    }

    public ContextMenu contextlh;
    public void Rightclicklh(MouseEvent event) {
        if (event.getButton().toString().equals("SECONDARY")) {
            contextlh.show(table3, event.getScreenX(), event.getScreenY());
        }
    }

    public MenuItem addlh, editlh, dellh;
    public void dellhAction(ActionEvent event) throws Exception{
        if (table3.getSelectionModel().getSelectedItem() == null) return;
        String malh = table3.getSelectionModel().getSelectedItem().getMl();

        if (malh.equals(ControllerLogin.tk)) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Không thể xóa lớp học đang đăng nhập!");
            alert.setContentText(null);
            Stage sta = (Stage) alert.getDialogPane().getScene().getWindow();
            sta.getIcons().add(new Image("/logohvan.png"));
            alert.showAndWait();
            return;
        }

        Connection conn = DataAccess.ketNoi();
        Statement comm = conn.createStatement();
        String strsql = "SELECT * FROM muon_phong WHERE (MaLop LIKE '" + malh + "' AND TGKetThuc LIKE '%2001%')";
        ResultSet rss = comm.executeQuery(strsql);
        if (rss.next()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Không thể xóa lớp đang học!");
            alert.setContentText(null);
            Stage sta = (Stage) alert.getDialogPane().getScene().getWindow();
            sta.getIcons().add(new Image("/logohvan.png"));
            alert.showAndWait();
            return;
        }
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText("Bạn chắc chắn muốn xóa thông tin lớp học này chứ?");
        alert.setContentText(null);
        Stage sta = (Stage) alert.getDialogPane().getScene().getWindow();
        sta.getIcons().add(new Image("/logohvan.png"));
        Optional<ButtonType> rs = alert.showAndWait();
        if (rs.get() == ButtonType.OK) {
            strsql = "DELETE FROM lop_hoc WHERE MaLop = '" + malh + "'";
            comm.execute(strsql);
            strsql = "DELETE FROM so_dien_thoai WHERE MaSo = '" + malh + "'";
            comm.execute(strsql);
            strsql = "DELETE FROM account WHERE username = '" + malh + "'";
            comm.execute(strsql);
            strsql = "DELETE FROM muon_phong WHERE MaLop = '" + malh + "'";
            comm.execute(strsql);
            strsql = "DELETE FROM giang_day WHERE MaLop = '" + malh + "'";
            comm.execute(strsql);

            Runeventgd();
            Runeventlh();
            Runeventsdt();
            LoadTk();
        }
    }

    public void addlhAction(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/AddEditLH/addlh.fxml"));
        Stage stg = new Stage();
        Scene scene = new Scene(root);
        stg.setTitle("Thêm mới");
        stg.setScene(scene);
        stg.initModality(Modality.APPLICATION_MODAL);
        stg.getIcons().add(new Image("/logohvan.png"));
        stg.setResizable(false);
        stg.showAndWait();

        Runeventlh();
        LoadTk();
    }

    public static Lophoc EditLh;
    public void editlhAction(ActionEvent event) throws Exception{
        if (table3.getSelectionModel().getSelectedItem() == null) return;
        EditLh = table3.getSelectionModel().getSelectedItem();
        Parent root = FXMLLoader.load(getClass().getResource("/AddEditLH/editlh.fxml"));
        Stage stg = new Stage();
        Scene scene = new Scene(root);
        stg.setTitle("Chỉnh sửa");
        stg.setScene(scene);
        stg.initModality(Modality.APPLICATION_MODAL);
        stg.getIcons().add(new Image("/logohvan.png"));
        stg.setResizable(false);
        stg.showAndWait();

        Runeventgd();
        Runeventlh();
        Runeventsdt();
        LoadTk();

    }

    /******************************************NHAN VIEN*************************************/

    @FXML
    private TableView<Nhanvien> table4;
    public ScrollPane scrollnv = new ScrollPane(table4);
    @FXML
    private TableColumn<Nhanvien, String> mnv;
    @FXML
    private TableColumn<Nhanvien, String> tnv;
    @FXML
    private TableColumn<Nhanvien, String> gnv;
    @FXML
    private TableColumn<Nhanvien, String> nnv;
    @FXML
    private TableColumn<Nhanvien, String> cnv;
    @FXML
    private TableColumn<Nhanvien, String> mldnv;

    public TextField namnv, idnv, birnv, rannv, mnvld;
    public ComboBox<String> sexnv;

    private ObservableList<Nhanvien> getDatanv() {
        ObservableList<Nhanvien> datanv = FXCollections.observableArrayList();
        Main service = new Main();
        List<Object[]> istnv = service.laydsnv();

        for (Object[] i : istnv) {
            datanv.add(new Nhanvien(i[0].toString(), i[1].toString(), i[2].toString(), i[3].toString(), i[4].toString(),
                    i[5].toString()));
        }
        return datanv;
    }

    public void Runeventnv() {
        String id = idnv.getText();
        String nam = namnv.getText();
        String bir = birnv.getText();
        String ran = rannv.getText();
        String mld = mnvld.getText();
        String sex = sexnv.getValue();
        if (sex == "Tất cả") sex = "";
        if (sex == null) sex = "";

        List<Object[]> istnv = new ArrayList();

        try {
            Connection conn = DataAccess.ketNoi();

            Statement comm = conn.createStatement();

            String strsql = "SELECT * FROM nhan_vien WHERE (MaNV LIKE '%" + id + "%' AND HoTenNV LIKE '%" + nam + "%' AND GioiTinh LIKE '%" +
                    sex + "%' AND NamSinh LIKE '%" + bir + "%' AND ChucVu LIKE '%" + ran + "%' AND MaNVLanhDao LIKE '%" + mld + "%')";
            ResultSet rs = comm.executeQuery(strsql);

            Object[] nv = null;
            while (rs.next()) {
                nv = new Object[6];
                nv[0] = rs.getString("MaNV");
                nv[1] = rs.getString("HoTenNV");
                nv[2] = rs.getString("GioiTinh");
                nv[3] = rs.getString("NamSinh");
                String ns = nv[3].toString();
                ns = ns.substring(0,4);
                nv[3] = ns;
                nv[4] = rs.getString("ChucVu");
                nv[5] = rs.getString("MaNVLanhDao");
                istnv.add(nv);
            }

            conn.close();

        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        ObservableList<Nhanvien> data = FXCollections.observableArrayList();
        for (Object[] i : istnv) {
            data.add(new Nhanvien(i[0].toString(), i[1].toString(), i[2].toString(), i[3].toString(), i[4].toString(), i[5].toString()))   ;
        }
        table4.setItems(data);
    }

    public void Acteventnv(ActionEvent event) {
        Runeventnv();
    }

    public void Keyeventnv(KeyEvent event) {
        Runeventnv();
    }

    public void Moueventnv(MouseEvent event) {
        Runeventnv();
    }

    public Button resnv;
    public void ResnvBut(MouseEvent event) {
        namnv.setText("");
        idnv.setText("");
        birnv.setText("");
        rannv.setText("");
        mnvld.setText("");
        sexnv.setValue("");
        ObservableList<Nhanvien> datanv = FXCollections.observableArrayList();
        Main service = new Main();
        List<Object[]> istnv = service.laydsnv();

        for (Object[] i : istnv) {
            datanv.add(new Nhanvien(i[0].toString(), i[1].toString(), i[2].toString(), i[3].toString(), i[4].toString(),
                    i[5].toString()));
        }
        table4.setItems(datanv);
    }

    public ContextMenu contextnv;
    public void Rightclicknv(MouseEvent event) {
        if (event.getButton().toString().equals("SECONDARY")) {
            contextlh.show(table3, event.getScreenX(), event.getScreenY());
        }
    }

    public MenuItem addnv, editnv, delnv;
    public void delnvAction(ActionEvent event) throws Exception{
        if (table4.getSelectionModel().getSelectedItem() == null) return;
        String mnv = table4.getSelectionModel().getSelectedItem().getMnv();

        if (mnv.equals(ControllerLogin.tk)) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Không thể xóa nhân viên đang đăng nhập!");
            alert.setContentText(null);
            Stage sta = (Stage) alert.getDialogPane().getScene().getWindow();
            sta.getIcons().add(new Image("/logohvan.png"));
            alert.showAndWait();
            return;
        }

        Connection conn = DataAccess.ketNoi();
        Statement comm = conn.createStatement();
        String strsql = "SELECT * FROM muon_phong WHERE (MaNV LIKE '" + mnv + "' AND TGKetThuc LIKE '%2001%')";
        ResultSet rss = comm.executeQuery(strsql);
        if (rss.next()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Không thể xóa nhân viên đang cho mượn phòng!");
            alert.setContentText(null);
            Stage sta = (Stage) alert.getDialogPane().getScene().getWindow();
            sta.getIcons().add(new Image("/logohvan.png"));
            alert.showAndWait();
            return;
        }
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText("Bạn chắc chắn muốn xóa thông tin nhân viên này chứ?");
        alert.setContentText(null);
        Stage sta = (Stage) alert.getDialogPane().getScene().getWindow();
        sta.getIcons().add(new Image("/logohvan.png"));
        Optional<ButtonType> rs = alert.showAndWait();
        if (rs.get() == ButtonType.OK) {
            strsql = "DELETE FROM nhan_vien WHERE MaNV = '" + mnv + "'";
            comm.execute(strsql);
            strsql = "DELETE FROM so_dien_thoai WHERE MaSo = '" + mnv + "'";
            comm.execute(strsql);
            strsql = "DELETE FROM account WHERE username = '" + mnv + "'";
            comm.execute(strsql);
            strsql = "DELETE FROM muon_phong WHERE MaNV = '" + mnv + "'";
            comm.execute(strsql);
            strsql = String.format("UPDATE nhan_vien SET MaNVLanhDao = MaNV WHERE MaNVLanhDao = '%s'", mnv);
            comm.execute(strsql);

            Runeventnv();
            Runeventsdt();
            LoadTk();

        }
    }

    public void addnvAction(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/AddEditNV/addnv.fxml"));
        Stage stg = new Stage();
        Scene scene = new Scene(root);
        stg.setTitle("Thêm mới");
        stg.setScene(scene);
        stg.initModality(Modality.APPLICATION_MODAL);
        stg.getIcons().add(new Image("/logohvan.png"));
        stg.setResizable(false);
        stg.showAndWait();

        Runeventnv();
        LoadTk();
    }

    public static Nhanvien EditNv;
    public void editnvAction(ActionEvent event) throws Exception{
        if (table4.getSelectionModel().getSelectedItem() == null) return;
        EditNv = table4.getSelectionModel().getSelectedItem();
        Parent root = FXMLLoader.load(getClass().getResource("/AddEditNV/editnv.fxml"));
        Stage stg = new Stage();
        Scene scene = new Scene(root);
        stg.setTitle("Chỉnh sửa");
        stg.setScene(scene);
        stg.initModality(Modality.APPLICATION_MODAL);
        stg.getIcons().add(new Image("/logohvan.png"));
        stg.setResizable(false);
        stg.showAndWait();

        Runeventnv();
        Runeventsdt();
        LoadTk();

    }


    /**************TAI KHOAN*****************/

    public Button addacc, delacc, ediacc;
    public Label tkroom, tkgv, tklh, tktk, tknv, tksdt, tkgd;
    public Tab tabaccount;
    @FXML
    private TableView<Taikhoan> table5;
    @FXML
    public ScrollPane scrolltk = new ScrollPane(table5);
    @FXML
    private TableColumn<Taikhoan, String> acc;
    @FXML
    private TableColumn<Taikhoan, String> pas;
    @FXML
    private TableColumn<Taikhoan, String> typ;

    public Button logoutph, logoutlh, logoutgv, logouttk, logoutnv, logoutsdt, logoutgd;

    public void LogoutBut(MouseEvent event) throws Exception{
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText("Bạn chắc chắn muốn đăng xuất chứ?");
        alert.setContentText(null);
        Stage sta = (Stage) alert.getDialogPane().getScene().getWindow();
        sta.getIcons().add(new Image("/logohvan.png"));

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            Stage stage = (Stage) logoutgv.getScene().getWindow();
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

    private ObservableList<Taikhoan> getDatatk() {
        ObservableList<Taikhoan> datatk = FXCollections.observableArrayList();
        Main service = new Main();
        List<Object[]> isttk = service.laydstk();

        for (Object[] i : isttk) {
            datatk.add(new Taikhoan(i[0].toString(), i[1].toString(), i[2].toString()));
        }
        return datatk;
    }

    public void LoadTk() {
        ObservableList<Taikhoan> datatk = FXCollections.observableArrayList();
        Main service = new Main();
        List<Object[]> isttk = service.laydstk();

        for (Object[] i : isttk) {
            datatk.add(new Taikhoan(i[0].toString(), i[1].toString(), i[2].toString()));
        }
        table5.setItems(datatk);
    }

    public void AddaccBut(MouseEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/AddEditTK/Addacc.fxml"));
        Stage stg = new Stage();
        Scene scene = new Scene(root);
        stg.setTitle("Thêm mới");
        stg.setScene(scene);
        stg.initModality(Modality.APPLICATION_MODAL);
        stg.getIcons().add(new Image("/logohvan.png"));
        stg.setResizable(false);
        stg.showAndWait();
        LoadTk();
    }

    public void DelaccBut(MouseEvent event) throws Exception{
        if (table5.getSelectionModel().getSelectedItem() == null) return;
        if (table5.getSelectionModel().getSelectedItem().getAcc().startsWith(ControllerLogin.tk) & table5.getSelectionModel().getSelectedItem().getAcc().length() == ControllerLogin.tk.length()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Không thể xóa tài khoản đang đăng nhập!");
            alert.setContentText(null);
            Stage sta = (Stage) alert.getDialogPane().getScene().getWindow();
            sta.getIcons().add(new Image("/logohvan.png"));
            alert.showAndWait();
            return;
        }
        String user;
        user = table5.getSelectionModel().getSelectedItem().getAcc();
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText("Xóa tài khoản: " + user);
        alert.setContentText("Bạn chắc chắn muốn xóa chứ?");
        Stage sta = (Stage) alert.getDialogPane().getScene().getWindow();
        sta.getIcons().add(new Image("/logohvan.png"));
        Optional<ButtonType> rs = alert.showAndWait();
        if (rs.get() == ButtonType.OK) {
            Connection conn = DataAccess.ketNoi();
            Statement comm = conn.createStatement();
            String strsql = "DELETE FROM account WHERE account.username = '" + user + "'";
            comm.execute(strsql);
            LoadTk();
        }
    }

    public static Taikhoan EditTk;
    public void EdiaccBut(MouseEvent event) throws Exception{
        if (table5.getSelectionModel().getSelectedItem() == null) return;
        if (table5.getSelectionModel().getSelectedItem().getAcc().startsWith(ControllerLogin.tk) & table5.getSelectionModel().getSelectedItem().getAcc().length() == ControllerLogin.tk.length()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Không thể chỉnh sửa tài khoản đang đăng nhập!");
            alert.setContentText(null);
            Stage sta = (Stage) alert.getDialogPane().getScene().getWindow();
            sta.getIcons().add(new Image("/logohvan.png"));
            alert.showAndWait();
            return;
        }
        EditTk = table5.getSelectionModel().getSelectedItem();
        Ediacc.ube = EditTk.getAcc();
        Ediacc.pbe = EditTk.getPas();
        Ediacc.tbe = EditTk.getTyp();
        Parent root = FXMLLoader.load(getClass().getResource("/AddEditTK/Ediacc.fxml"));
        Stage stg = new Stage();
        Scene scene = new Scene(root);
        stg.setTitle("Chỉnh sửa");
        stg.setScene(scene);
        stg.initModality(Modality.APPLICATION_MODAL);
        stg.getIcons().add(new Image("/logohvan.png"));
        stg.setResizable(false);
        stg.showAndWait();

        LoadTk();
    }

    public void edittkAction(ActionEvent event) throws Exception{
        if (table5.getSelectionModel().getSelectedItem() == null) return;
        if (table5.getSelectionModel().getSelectedItem().getAcc().startsWith(ControllerLogin.tk) & table5.getSelectionModel().getSelectedItem().getAcc().length() == ControllerLogin.tk.length()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Không thể chỉnh sửa tài khoản đang đăng nhập!");
            alert.setContentText(null);
            Stage sta = (Stage) alert.getDialogPane().getScene().getWindow();
            sta.getIcons().add(new Image("/logohvan.png"));
            alert.showAndWait();
            return;
        }
        EditTk = table5.getSelectionModel().getSelectedItem();
        Ediacc.ube = EditTk.getAcc();
        Ediacc.pbe = EditTk.getPas();
        Ediacc.tbe = EditTk.getTyp();
        Parent root = FXMLLoader.load(getClass().getResource("/AddEditTK/Ediacc.fxml"));
        Stage stg = new Stage();
        Scene scene = new Scene(root);
        stg.setTitle("Chỉnh sửa");
        stg.setScene(scene);
        stg.initModality(Modality.APPLICATION_MODAL);
        stg.getIcons().add(new Image("/logohvan.png"));
        stg.setResizable(false);
        stg.showAndWait();

        LoadTk();
    }

    public void addtkAction(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/AddEditTK/Addacc.fxml"));
        Stage stg = new Stage();
        Scene scene = new Scene(root);
        stg.setTitle("Thêm mới");
        stg.setScene(scene);
        stg.initModality(Modality.APPLICATION_MODAL);
        stg.getIcons().add(new Image("/logohvan.png"));
        stg.setResizable(false);
        stg.showAndWait();

        LoadTk();
    }

    public void deltkAction(ActionEvent event) throws Exception{
        if (table5.getSelectionModel().getSelectedItem() == null) return;
        if (table5.getSelectionModel().getSelectedItem().getAcc().startsWith(ControllerLogin.tk) & table5.getSelectionModel().getSelectedItem().getAcc().length() == ControllerLogin.tk.length()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Không thể xóa tài khoản đang đăng nhập!");
            alert.setContentText(null);
            Stage sta = (Stage) alert.getDialogPane().getScene().getWindow();
            sta.getIcons().add(new Image("/logohvan.png"));
            alert.showAndWait();
            return;
        }
        String user;
        user = table5.getSelectionModel().getSelectedItem().getAcc();
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText("Xóa tài khoản: " + user);
        alert.setContentText("Bạn chắc chắn muốn xóa chứ?");
        Stage sta = (Stage) alert.getDialogPane().getScene().getWindow();
        sta.getIcons().add(new Image("/logohvan.png"));
        Optional<ButtonType> rs = alert.showAndWait();
        if (rs.get() == ButtonType.OK) {
            Connection conn = DataAccess.ketNoi();
            Statement comm = conn.createStatement();
            String strsql = "DELETE FROM account WHERE account.username = '" + user + "'";
            comm.execute(strsql);

            LoadTk();
        }
    }



    public JFXButton mtphoc;
    public void mtphocBut(MouseEvent event) throws Exception{
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText("Bạn muốn chuyển qua phần mềm Quản lý mượn trả phòng học?");
        alert.setContentText(null);
        Stage sta = (Stage) alert.getDialogPane().getScene().getWindow();
        sta.getIcons().add(new Image("/logohvan.png"));
        Optional<ButtonType> rst = alert.showAndWait();
        if (rst.get() == ButtonType.OK) {
            LoginMT.usermt = ControllerLogin.tk;

            Stage stg = (Stage) mtphoc.getScene().getWindow();
            stg.close();

            Parent root = FXMLLoader.load(getClass().getResource("/MuonTra/MuonTra.fxml"));
            stg = new Stage();
            Scene scene = new Scene(root);
            stg.setTitle("Quản Lý Mượn Trả Phòng Học");
            stg.initStyle(StageStyle.DECORATED);
            stg.setScene(scene);
            stg.getIcons().add(new Image("/logohvan.png"));
            stg.show();
            stg.setMinHeight(400);
            stg.setMinWidth(666);
        }
    }

    /************************************SO DIEN THOAI***********************************/
    @FXML
    private TableView<Sodienthoai> table6;
    public ScrollPane scrollsdt = new ScrollPane(table6);
    @FXML
    private TableColumn<Sodienthoai, String> msdt, ssdt, plsdt;

    ObservableList<String> listpl = FXCollections.observableArrayList("Tất cả","Giáo viên", "Lớp trưởng", "GV chủ nhiệm", "Nhân viên");

    public TextField idsdt, numsdt;
    public ComboBox<String> typesdt;
    public ContextMenu contextsdt;
    public MenuItem addsdt, editsdt, delsdt;

    private ObservableList<Sodienthoai> getDatasdt() {
        ObservableList<Sodienthoai> datasdt = FXCollections.observableArrayList();
        Main service = new Main();
        List<Object[]> istsdt = service.laydssdt();

        for (Object[] i : istsdt) {
            datasdt.add(new Sodienthoai(i[0].toString(), i[1].toString(), i[2].toString()));
        }
        return datasdt;
    }

    public void Runeventsdt() {
        String id = idsdt.getText();
        String num = numsdt.getText();
        String typ = typesdt.getValue();
        if (typ == "Tất cả") typ = "";
        if (typ == null) typ = "";

        List<Object[]> istsdt = new ArrayList();

        try {
            Connection conn = DataAccess.ketNoi();

            Statement comm = conn.createStatement();

            String strsql = "SELECT * FROM so_dien_thoai WHERE (MaSo LIKE '%" + id + "%' AND SoDienThoai LIKE '%" + num + "%' AND PhanLoai LIKE '%" +
                    typ + "%')";
            ResultSet rs = comm.executeQuery(strsql);

            Object[] sdt = null;
            while (rs.next()) {
                sdt = new Object[3];
                sdt[0] = rs.getString("MaSo");
                sdt[1] = rs.getString("SoDienThoai");
                sdt[2] = rs.getString("PhanLoai");
                istsdt.add(sdt);
            }

            conn.close();

        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        ObservableList<Sodienthoai> data = FXCollections.observableArrayList();
        for (Object[] i : istsdt) {
            data.add(new Sodienthoai(i[0].toString(), i[1].toString(), i[2].toString()));
        }
        table6.setItems(data);
    }

    public void Acteventsdt(ActionEvent event) {
        Runeventsdt();
    }

    public void Keyeventsdt(KeyEvent event) {
        Runeventsdt();
    }

    public void Moueventsdt(MouseEvent event) {
        Runeventsdt();
    }

    public Button ressdt;
    public void RessdtBut(MouseEvent event) {
        idsdt.setText("");
        numsdt.setText("");
        typesdt.setValue("");
        ObservableList<Sodienthoai> data = FXCollections.observableArrayList();
        Main service = new Main();
        List<Object[]> ist = service.laydssdt();

        for (Object[] i : ist) {
            data.add(new Sodienthoai(i[0].toString(), i[1].toString(), i[2].toString()));
        }
        table6.setItems(data);
    }

    public void addsdtAction(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/AddEditSDT/Addsdt.fxml"));
        Stage stg = new Stage();
        Scene scene = new Scene(root);
        stg.setTitle("Thêm mới");
        stg.setScene(scene);
        stg.initModality(Modality.APPLICATION_MODAL);
        stg.getIcons().add(new Image("/logohvan.png"));
        stg.setResizable(false);
        stg.showAndWait();

        Runeventsdt();
    }

    public void delsdtAction(ActionEvent event) throws Exception{
        if (table6.getSelectionModel().getSelectedItem() == null) return;
        String id, num;
        id = table6.getSelectionModel().getSelectedItem().getMsdt();
        num = table6.getSelectionModel().getSelectedItem().getSsdt();
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText("Bạn chắc chắn muốn xóa số điện thoại này chứ?");
        alert.setContentText(null);
        Stage sta = (Stage) alert.getDialogPane().getScene().getWindow();
        sta.getIcons().add(new Image("/logohvan.png"));
        Optional<ButtonType> rs = alert.showAndWait();
        if (rs.get() == ButtonType.OK) {
            Connection conn = DataAccess.ketNoi();
            Statement comm = conn.createStatement();
            String strsql = "DELETE FROM so_dien_thoai WHERE MaSo = '" + id + "' AND SoDienThoai = '" + num + "'";
            comm.execute(strsql);

            Runeventsdt();
        }
    }

    public static Sodienthoai EditSdt;
    public void editsdtAction(ActionEvent event) throws Exception{
        if (table6.getSelectionModel().getSelectedItem() == null) return;
        EditSdt = table6.getSelectionModel().getSelectedItem();
        Parent root = FXMLLoader.load(getClass().getResource("/AddEditSDT/Editsdt.fxml"));
        Stage stg = new Stage();
        Scene scene = new Scene(root);
        stg.setTitle("Chỉnh sửa");
        stg.setScene(scene);
        stg.initModality(Modality.APPLICATION_MODAL);
        stg.getIcons().add(new Image("/logohvan.png"));
        stg.setResizable(false);
        stg.showAndWait();

        Runeventsdt();
    }

    /****************************GIANG DAY************************************************/
    @FXML
    private TableView<Giangday> table7;
    public ScrollPane scrollgd = new ScrollPane(table7);
    @FXML
    private TableColumn<Giangday, String> msgv, mslh, mhoc;

    public TextField gvgd, mhgd, lhgd;
    public ContextMenu contextgd;
    public MenuItem addgd, editgd, delgd;

    private ObservableList<Giangday> getDatagd() {
        ObservableList<Giangday> datagd = FXCollections.observableArrayList();
        Main service = new Main();
        List<Object[]> istgd = service.laydsgd();

        for (Object[] i : istgd) {
            datagd.add(new Giangday(i[0].toString(), i[1].toString(), i[2].toString()));
        }
        return datagd;
    }

    public void Runeventgd() {
        String gv = gvgd.getText();
        String lh = lhgd.getText();
        String mh = mhgd.getText();

        List<Object[]> istgd = new ArrayList();

        try {
            Connection conn = DataAccess.ketNoi();

            Statement comm = conn.createStatement();

            String strsql = "SELECT * FROM giang_day WHERE (MaGV LIKE '%" + gv + "%' AND MaLop LIKE '%" + lh + "%' AND MonHoc LIKE '%" +
                    mh + "%')";
            ResultSet rs = comm.executeQuery(strsql);

            Object[] gd = null;
            while (rs.next()) {
                gd = new Object[3];
                gd[0] = rs.getString("MaGV");
                gd[1] = rs.getString("MaLop");
                gd[2] = rs.getString("MonHoc");
                istgd.add(gd);
            }

            conn.close();

        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        ObservableList<Giangday> data = FXCollections.observableArrayList();
        for (Object[] i : istgd) {
            data.add(new Giangday(i[0].toString(), i[1].toString(), i[2].toString()));
        }
        table7.setItems(data);
    }

    public void Acteventgd(ActionEvent event) {
        Runeventgd();
    }

    public void Keyeventgd(KeyEvent event) {
        Runeventgd();
    }

    public void Moueventgd(MouseEvent event) {
        Runeventgd();
    }

    public Button resgd;
    public void ResgdBut(MouseEvent event) {
        gvgd.setText("");
        lhgd.setText("");
        mhgd.setText("");
        ObservableList<Giangday> data = FXCollections.observableArrayList();
        Main service = new Main();
        List<Object[]> ist = service.laydsgd();

        for (Object[] i : ist) {
            data.add(new Giangday(i[0].toString(), i[1].toString(), i[2].toString()));
        }
        table7.setItems(data);
    }

    public void addgdAction(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/AddEditGD/Addgd.fxml"));
        Stage stg = new Stage();
        Scene scene = new Scene(root);
        stg.setTitle("Thêm mới");
        stg.setScene(scene);
        stg.initModality(Modality.APPLICATION_MODAL);
        stg.getIcons().add(new Image("/logohvan.png"));
        stg.setResizable(false);
        stg.showAndWait();

        Runeventgd();
    }

    public void delgdAction(ActionEvent event) throws Exception{
        if (table7.getSelectionModel().getSelectedItem() == null) return;
        String gv, lh, mh;
        gv = table7.getSelectionModel().getSelectedItem().getMsgv();
        lh = table7.getSelectionModel().getSelectedItem().getMslh();
        mh = table7.getSelectionModel().getSelectedItem().getMhoc();
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText("Bạn chắc chắn muốn xóa thông tin này chứ?");
        alert.setContentText(null);
        Stage sta = (Stage) alert.getDialogPane().getScene().getWindow();
        sta.getIcons().add(new Image("/logohvan.png"));
        Optional<ButtonType> rs = alert.showAndWait();
        if (rs.get() == ButtonType.OK) {
            Connection conn = DataAccess.ketNoi();
            Statement comm = conn.createStatement();
            String strsql = "DELETE FROM giang_day WHERE MaGV = '" + gv + "' AND MaLop = '" + lh + "' AND MonHoc = '" + mh + "'";
            comm.execute(strsql);

            Runeventgd();
        }
    }

    public static Giangday EditGd;
    public void editgdAction(ActionEvent event) throws Exception{
        if (table7.getSelectionModel().getSelectedItem() == null) return;
        EditGd = table7.getSelectionModel().getSelectedItem();
        Parent root = FXMLLoader.load(getClass().getResource("/AddEditGD/Editgd.fxml"));
        Stage stg = new Stage();
        Scene scene = new Scene(root);
        stg.setTitle("Chỉnh sửa");
        stg.setScene(scene);
        stg.initModality(Modality.APPLICATION_MODAL);
        stg.getIcons().add(new Image("/logohvan.png"));
        stg.setResizable(false);
        stg.showAndWait();

        Runeventgd();
    }

    /*
    *
    *
    *
    * initialize
    *
    *
    *
    *
    *
     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        /********TAI KHOAN**********/
        String s = ControllerLogin.tk;
        String t = ControllerLogin.ty;
        if (t.startsWith("Nhân viên") & t.length() == 9) { tabaccount.setDisable(false); tabaccount.setText("Tài khoản"); }
        else {tabaccount.setDisable(true); tabaccount.setText("");}
        s = "Tài khoản: " + s;
        tkroom.setText(s);
        tkgv.setText(s);
        tklh.setText(s);
        tktk.setText(s);
        tknv.setText(s);
        tksdt.setText(s);
        tkgd.setText(s);
        acc.setCellValueFactory(new PropertyValueFactory<Taikhoan, String>("acc"));
        pas.setCellValueFactory(new PropertyValueFactory<Taikhoan, String>("pas"));
        typ.setCellValueFactory(new PropertyValueFactory<Taikhoan, String>("typ"));
        table5.setItems(getDatatk());
        scrolltk.setFitToHeight(true);
        scrolltk.setFitToWidth(true);

        if (!ControllerLogin.ty.equals("Nhân viên")) {
            contextph.setOpacity(0);
            contextgv.setOpacity(0);
            contextlh.setOpacity(0);
            contextnv.setOpacity(0);
            contextsdt.setOpacity(0);
            contextgd.setOpacity(0);
        }

        /*********************PHONG HOC***********************************/

        mp.setCellValueFactory(new PropertyValueFactory<Phonghoc, String>("mp"));
        lp.setCellValueFactory(new PropertyValueFactory<Phonghoc, String>("lp"));
        tt.setCellValueFactory(new PropertyValueFactory<Phonghoc, String>("tt"));
        sc.setCellValueFactory(new PropertyValueFactory<Phonghoc, String>("sc"));
        mc.setCellValueFactory(new PropertyValueFactory<Phonghoc, String>("mc"));
        dh.setCellValueFactory(new PropertyValueFactory<Phonghoc, String>("dh"));
        mi.setCellValueFactory(new PropertyValueFactory<Phonghoc, String>("mi"));
        ca.setCellValueFactory(new PropertyValueFactory<Phonghoc, String>("ca"));
        typroom.setItems(listtyp);
        staroom.setItems(liststa);
        proroom.setItems(listcs);
        conroom.setItems(listcs);
        camroom.setItems(listcs);
        microom.setItems(listcs);
        table1.setItems(getData());
        scrollph.setFitToHeight(true);
        scrollph.setFitToWidth(true);

        /*******************************************GIAO VIEN**********************************/
        mgv.setCellValueFactory(new PropertyValueFactory<Giaovien, String>("mgv"));
        tgv.setCellValueFactory(new PropertyValueFactory<Giaovien, String>("tgv"));
        ggv.setCellValueFactory(new PropertyValueFactory<Giaovien, String>("ggv"));
        ngv.setCellValueFactory(new PropertyValueFactory<Giaovien, String>("ngv"));
        egv.setCellValueFactory(new PropertyValueFactory<Giaovien, String>("egv"));
        kgv.setCellValueFactory(new PropertyValueFactory<Giaovien, String>("kgv"));
        table2.setItems(getDatagv());
        sexgv.setItems(listsex);
        scrollgv.setFitToHeight(true);
        scrollgv.setFitToWidth(true);

        /*****************************************LOP HOC************************************/
        ml.setCellValueFactory(new PropertyValueFactory<Lophoc, String>("ml"));
        tl.setCellValueFactory(new PropertyValueFactory<Lophoc, String>("tl"));
        el.setCellValueFactory(new PropertyValueFactory<Lophoc, String>("el"));
        sl.setCellValueFactory(new PropertyValueFactory<Lophoc, String>("sl"));
        tlt.setCellValueFactory(new PropertyValueFactory<Lophoc, String>("tlt"));
        glt.setCellValueFactory(new PropertyValueFactory<Lophoc, String>("glt"));
        nlt.setCellValueFactory(new PropertyValueFactory<Lophoc, String>("nlt"));
        tcn.setCellValueFactory(new PropertyValueFactory<Lophoc, String>("tcn"));
        gcn.setCellValueFactory(new PropertyValueFactory<Lophoc, String>("gcn"));
        ncn.setCellValueFactory(new PropertyValueFactory<Lophoc, String>("ncn"));
        ccn.setCellValueFactory(new PropertyValueFactory<Lophoc, String>("ccn"));
        table3.setItems(getDatalh());
        sexcn.setItems(listsex);
        sexlt.setItems(listsex);
        scrolllh.setFitToHeight(true);
        scrolllh.setFitToWidth(true);

        /**************************************NHAN VIEN**************************************/
        mnv.setCellValueFactory(new PropertyValueFactory<Nhanvien, String >("mnv"));
        tnv.setCellValueFactory(new PropertyValueFactory<Nhanvien, String >("tnv"));
        gnv.setCellValueFactory(new PropertyValueFactory<Nhanvien, String >("gnv"));
        nnv.setCellValueFactory(new PropertyValueFactory<Nhanvien, String >("nnv"));
        cnv.setCellValueFactory(new PropertyValueFactory<Nhanvien, String >("cnv"));
        mldnv.setCellValueFactory(new PropertyValueFactory<Nhanvien, String >("mldnv"));
        table4.setItems(getDatanv());
        sexnv.setItems(listsex);
        scrollnv.setFitToHeight(true);
        scrollnv.setFitToWidth(true);

        /***************************SO DIEN THOAI*****************************/
        msdt.setCellValueFactory(new PropertyValueFactory<Sodienthoai, String >("msdt"));
        ssdt.setCellValueFactory(new PropertyValueFactory<Sodienthoai, String >("ssdt"));
        plsdt.setCellValueFactory(new PropertyValueFactory<Sodienthoai, String >("plsdt"));
        table6.setItems(getDatasdt());
        typesdt.setItems(listpl);
        scrollsdt.setFitToHeight(true);
        scrollsdt.setFitToWidth(true);

        /****************************GIANG DAY***************************/
        msgv.setCellValueFactory(new PropertyValueFactory<Giangday, String >("msgv"));
        mslh.setCellValueFactory(new PropertyValueFactory<Giangday, String >("mslh"));
        mhoc.setCellValueFactory(new PropertyValueFactory<Giangday, String >("mhoc"));
        table7.setItems(getDatagd());
        scrollgd.setFitToHeight(true);
        scrollgd.setFitToWidth(true);
    }
}
