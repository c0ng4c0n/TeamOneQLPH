package AddEditNV;

import MuonTra.MuonTra;
import connectmySQL.DataAccess;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.Controller;
import sample.Main;
import sample.Nhanvien;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;

public class editnv implements Initializable {

    public TextField manv, hotennv, chucvu, manvlanhdao, namsinh;
    public ComboBox<String> gioitinh;
    public Button okbut, cancelbut;
    static String idnv;

    public boolean checkns(String s) {
        for (int i = 0; i < s.length(); ++i)
            if (s.charAt(i) < '0' | s.charAt(i) > '9') return false;
        if (s.length() > 4) return false;
        int i = Integer.parseInt(s);
        if (i < 1900 | i > 2018) return false;
        return true;
    }

    public void okbutClick(MouseEvent event) throws Exception{
        String mnv = manv.getText();
        mnv = MuonTra.toUpper(mnv);
        String tnv = hotennv.getText();
        tnv = MuonTra.toFormat(tnv);
        String ns = namsinh.getText();
        String cv = chucvu.getText();
        String ld = manvlanhdao.getText();
        String gt = gioitinh.getValue();

        if (mnv.length() < 1 | tnv.length() < 1 | ns.length() < 1 | cv.length() < 1 | gt.length() < 1 | ld.length() < 1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Vui lòng nhập đầy đủ thông tin!");
            alert.setContentText(null);
            Stage sta = (Stage) alert.getDialogPane().getScene().getWindow();
            sta.getIcons().add(new Image("/logohvan.png"));
            alert.showAndWait();
            return;
        }
        Connection conn = DataAccess.ketNoi();
        Statement comm = conn.createStatement();
        String strsql = "SELECT * FROM nhan_vien WHERE MaNV = '" + mnv + "'";
        ResultSet rs = comm.executeQuery(strsql);
        if (rs.next() & !mnv.equals(idnv)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Mã nhân viên đã tồn tại!");
            alert.setContentText(null);
            Stage sta = (Stage) alert.getDialogPane().getScene().getWindow();
            sta.getIcons().add(new Image("/logohvan.png"));
            alert.showAndWait();
            return;
        }
        if (checkns(ns) == false) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Năm sinh không phù hợp!");
            alert.setContentText(null);
            Stage sta = (Stage) alert.getDialogPane().getScene().getWindow();
            sta.getIcons().add(new Image("/logohvan.png"));
            alert.showAndWait();
            return;
        }
        strsql = "SELECT * FROM nhan_vien WHERE MaNV = '" + ld + "'";
        rs = comm.executeQuery(strsql);
        if (!rs.next() & !ld.equals(mnv)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Mã NV lãnh đạo không tồn tại!");
            alert.setContentText(null);
            Stage sta = (Stage) alert.getDialogPane().getScene().getWindow();
            sta.getIcons().add(new Image("/logohvan.png"));
            alert.showAndWait();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText("Bạn chắc chắn muốn chỉnh sửa thông tin nhân viên này chứ?");
        alert.setContentText(null);
        Stage sta = (Stage) alert.getDialogPane().getScene().getWindow();
        sta.getIcons().add(new Image("/logohvan.png"));
        Optional<ButtonType> rss = alert.showAndWait();
        if (rss.get() == ButtonType.CANCEL) return;
        strsql = String.format("UPDATE nhan_vien SET MaNV = '%s', HoTenNV = '%s', GioiTinh = '%s', NamSinh = '%s'," +
                " ChucVu = '%s', MaNVLanhDao = '%s' WHERE nhan_vien.MaNV = '%s'", mnv, tnv, gt, ns, cv, ld, idnv);
        comm.execute(strsql);
        strsql = String.format("UPDATE account SET username = '%s', password = '%s' WHERE account.username = '%s'", mnv, Main.md5(mnv), idnv);
        comm.execute(strsql);
        strsql = String.format("UPDATE muon_phong SET MaNV = '%s' WHERE muon_phong.MaNV = '%s'", mnv, idnv);
        comm.execute(strsql);
        strsql = String.format("UPDATE so_dien_thoai SET MaSo = '%s' WHERE MaSo = '%s'", mnv, idnv);
        comm.execute(strsql);
        strsql = String.format("UPDATE nhan_vien SET MaNVLanhDao = '%s' WHERE MaNVLanhDao = '%s'", mnv, idnv);
        comm.execute(strsql);
        Stage stg = (Stage) okbut.getScene().getWindow();
        stg.close();
    }

    public void cancelbutClick(MouseEvent event) {
        Stage stg = (Stage) cancelbut.getScene().getWindow();
        stg.close();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gioitinh.setItems(FXCollections.observableArrayList("Nam","Nữ"));
        Nhanvien.setNv(Controller.EditNv, manv, hotennv, gioitinh, namsinh, chucvu, manvlanhdao);
        idnv = Controller.EditNv.getMnv();
    }
}
