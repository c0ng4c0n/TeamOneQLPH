package AddEditGV;

import MuonTra.MuonTra;
import connectmySQL.DataAccess;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.Main;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;

public class addgv implements Initializable {

    public TextField magv, namsinh, email, khoa, hotengv;
    public ComboBox<String> gioitinh;
    public Button cancelbut, okbut;

    public boolean checkint(String s) {
        for (int i = 0; i < s.length(); ++i)
            if (s.charAt(i) < '0' | s.charAt(i) > '9') return false;
        if (s.length() > 4) return false;
        int i = Integer.parseInt(s);
        if (i < 1900 | i > 2018) return false;
        return true;
    }

    public void okbutClick(MouseEvent event) throws Exception{
        String mg = magv.getText();
        if (mg == null) mg = "";
        mg = MuonTra.toUpper(mg);
        String ns = namsinh.getText();
        if (ns == null) ns = "";
        String ht = hotengv.getText();
        if (ht == null) ht = "";
        ht = MuonTra.toFormat(ht);
        String em = email.getText();
        if (em == null) em = "";
        String kh = khoa.getText();
        if (kh == null) kh = "";
        String gt = gioitinh.getValue();

        if (mg.length() < 1 | ht.length() < 1 | ns.length() < 1 | em.length() < 1 | kh.length() < 1 | gt.length() < 1) {
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
        String strsql = "SELECT * FROM giao_vien WHERE MaGV = '" + mg + "'";
        ResultSet rs = comm.executeQuery(strsql);
        if (rs.next()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Mã giáo viên đã tồn tại!");
            alert.setContentText(null);
            Stage sta = (Stage) alert.getDialogPane().getScene().getWindow();
            sta.getIcons().add(new Image("/logohvan.png"));
            alert.showAndWait();
            return;
        }
        if (checkint(ns) == false) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Năm sinh không phù hợp!");
            alert.setContentText(null);
            Stage sta = (Stage) alert.getDialogPane().getScene().getWindow();
            sta.getIcons().add(new Image("/logohvan.png"));
            alert.showAndWait();
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText("Bạn chắc chắn muốn thêm thông tin giáo viên này chứ?");
        alert.setContentText(null);
        Stage sta = (Stage) alert.getDialogPane().getScene().getWindow();
        sta.getIcons().add(new Image("/logohvan.png"));
        Optional<ButtonType> rss = alert.showAndWait();
        if (rss.get() == ButtonType.CANCEL) return;
        strsql = String.format("INSERT INTO giao_vien (MaGV, HoTenGV, GioiTinh, NamSinh, Email, Khoa) " +
                "VALUES ('%s', '%s', '%s', '%s', '%s', '%s')", mg, ht, gt, ns, em, kh);
        comm.execute(strsql);
        strsql = String.format("INSERT INTO account (username, password, type) " +
                    "VALUES ('%s', '%s', 'Giáo viên')", mg, Main.md5(mg));
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
