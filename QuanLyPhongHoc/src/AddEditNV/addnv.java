package AddEditNV;

import MuonTra.MuonTra;
import connectmySQL.DataAccess;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.Main;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;

public class addnv implements Initializable {

    public TextField manv, hotennv, chucvu, manvlanhdao, namsinh;
    public ComboBox<String> gioitinh;
    public Button okbut, cancelbut;

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
        if (mnv == null) mnv = "";
        mnv = MuonTra.toUpper(mnv);
        String tnv = hotennv.getText();
        if (tnv == null) tnv = "";
        tnv = MuonTra.toFormat(tnv);
        String ns = namsinh.getText();
        if (ns == null) ns = "";
        String cv = chucvu.getText();
        if (cv == null) cv = "";
        String ld = manvlanhdao.getText();
        if (ld == null) ld = "";
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
        if (rs.next()) {
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
        if (!rs.next()) {
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
        alert.setHeaderText("Bạn chắc chắn muốn thêm thông tin nhân viên này chứ?");
        alert.setContentText(null);
        Stage sta = (Stage) alert.getDialogPane().getScene().getWindow();
        sta.getIcons().add(new Image("/logohvan.png"));
        Optional<ButtonType> rss = alert.showAndWait();
        if (rss.get() == ButtonType.CANCEL) return;
        strsql = String.format("INSERT INTO nhan_vien (MaNV, HoTenNV, GioiTinh, NamSinh, ChucVu, MaNVLanhDao) VALUES " +
                "('%s', '%s', '%s', '%s', '%s','%s')", mnv, tnv, gt, ns, cv, ld);
        comm.execute(strsql);
        strsql = String.format("INSERT INTO account (username, password, type) " +
                "VALUES ('%s', '%s', 'Nhân viên')", mnv, Main.md5(mnv));
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
        gioitinh.setValue("Nam");
    }
}
