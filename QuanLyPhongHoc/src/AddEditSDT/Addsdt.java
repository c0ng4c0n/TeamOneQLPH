package AddEditSDT;

import MuonTra.MuonTra;
import connectmySQL.DataAccess;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class Addsdt implements Initializable {

    public TextField addid, addnum;
    public ComboBox<String> addtype;
    public Button okbut, cancelbut;

    public void CancelAdd(MouseEvent event) {
        Stage stage = (Stage) cancelbut.getScene().getWindow();
        stage.close();
    }

    public boolean checksdt(String s) {
        for (int i = 0; i < s.length(); ++i){
            if (s.charAt(i) < '0' | s.charAt(i) > '9') return false;
        }
        return true;
    }

    public void Addsdt(MouseEvent event) throws Exception {
        String id = addid.getText();
        if (id == null) id = "";
        id = MuonTra.toUpper(id);
        String num = addnum.getText();
        if (num == null) num = "";
        String type = addtype.getValue().toString();
        if (type == null) type = "";;
        if ((id.length() < 1) | (num.length() < 1) | (type.length() < 1)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Thông báo");
            alert.setHeaderText(null);
            alert.setContentText("Vui lòng điền đầy đủ thông tin!");
            Stage sta = (Stage) alert.getDialogPane().getScene().getWindow();
            sta.getIcons().add(new Image("/logohvan.png"));
            alert.showAndWait();
            return;
        }
        if (checksdt(num) == false) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Thông báo");
            alert.setHeaderText(null);
            alert.setContentText("Số điện thoại không hợp lệ!");
            Stage sta = (Stage) alert.getDialogPane().getScene().getWindow();
            sta.getIcons().add(new Image("/logohvan.png"));
            alert.showAndWait();
            return;
        }
        Connection conn = DataAccess.ketNoi();
        Statement comm = conn.createStatement();
        String strsql = "SELECT * FROM so_dien_thoai WHERE (MaSo = '" + id + "' AND SoDienThoai = '" + num + "')";
        ResultSet rs = comm.executeQuery(strsql);
        if (rs.next()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Thông báo");
            alert.setHeaderText(null);
            alert.setContentText("Thông tin đã tồn tại!");
            Stage sta = (Stage) alert.getDialogPane().getScene().getWindow();
            sta.getIcons().add(new Image("/logohvan.png"));
            alert.showAndWait();
            return;
        }
        if (type.startsWith("Giáo viên")) {
            strsql = "SELECT * FROM giao_vien WHERE (MaGV = '" + id + "')";
            rs = comm.executeQuery(strsql);
            if (!rs.next()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Thông báo");
                alert.setHeaderText(null);
                alert.setContentText("Mã số chưa có trong danh sách giáo viên!");
                Stage sta = (Stage) alert.getDialogPane().getScene().getWindow();
                sta.getIcons().add(new Image("/logohvan.png"));
                alert.showAndWait();
                return;
            }
        }
        if (type.startsWith("GV chủ nhiệm") | type.startsWith("Lớp trưởng")) {
            strsql = "SELECT * FROM lop_hoc WHERE (MaLop = '" + id + "')";
            rs = comm.executeQuery(strsql);
            if (!rs.next()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Thông báo");
                alert.setHeaderText(null);
                alert.setContentText("Mã số chưa có trong danh sách lớp học!");
                Stage sta = (Stage) alert.getDialogPane().getScene().getWindow();
                sta.getIcons().add(new Image("/logohvan.png"));
                alert.showAndWait();
                return;
            }
        }
        if (type.startsWith("Nhân viên")) {
            strsql = "SELECT * FROM nhan_vien WHERE (MaNV = '" + id + "')";
            rs = comm.executeQuery(strsql);
            if (!rs.next()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Thông báo");
                alert.setHeaderText(null);
                alert.setContentText("Mã số chưa có trong danh sách nhân viên!");
                Stage sta = (Stage) alert.getDialogPane().getScene().getWindow();
                sta.getIcons().add(new Image("/logohvan.png"));
                alert.showAndWait();
                return;
            }
        }
        strsql = "INSERT INTO so_dien_thoai (MaSo, SoDienThoai, PhanLoai) VALUES ('" + id + "', '" + num + "', '" + type + "')";
        comm.execute(strsql);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText(null);
        alert.setContentText("Thêm mới thông tin thành công!");
        Stage sta = (Stage) alert.getDialogPane().getScene().getWindow();
        sta.getIcons().add(new Image("/logohvan.png"));
        alert.showAndWait();
        Stage stage = (Stage) okbut.getScene().getWindow();
        stage.close();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        addtype.setItems(FXCollections.observableArrayList("Giáo viên", "Lớp trưởng", "GV chủ nhiệm", "Nhân viên"));
        addtype.setValue("Giáo viên");
    }
}
