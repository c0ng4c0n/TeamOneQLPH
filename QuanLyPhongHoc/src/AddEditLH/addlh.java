package AddEditLH;

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

public class addlh implements Initializable {

    public TextField malop, tenlop, siso, email, hotenlt, namsinhlt, hotencn, namsinhcn, chucvucn;
    public ComboBox<String> gioitinhlt, gioitinhcn;
    public Button okbut, cancelbut;

    public boolean checkns(String s) {
        for (int i = 0; i < s.length(); ++i)
            if (s.charAt(i) < '0' | s.charAt(i) > '9') return false;
        if (s.length() > 4) return false;
        int i = Integer.parseInt(s);
        if (i < 1900 | i > 2018) return false;
        return true;
    }

    public boolean checkint(String s) {
        for (int i = 0; i < s.length(); ++i)
            if (s.charAt(i) < '0' | s.charAt(i) > '9') return false;
        return true;
    }

    public void okbutClick(MouseEvent event) throws Exception{
        String ml = malop.getText();
        if (ml == null) ml = "";
        ml = MuonTra.toUpper(ml);
        String tl = tenlop.getText();
        if (tl == null) tl = "";
        String ss = siso.getText();
        if (ss == null) ss = "";
        String em = email.getText();
        if (em == null) em = "";
        String htlt = hotenlt.getText();
        if (htlt == null) htlt = "";
        htlt = MuonTra.toFormat(htlt);
        String nslt = namsinhlt.getText();
        if (nslt == null) nslt = "";
        String htcn = hotencn.getText();
        if (htcn == null) htcn = "";
        htcn = MuonTra.toFormat(htcn);
        String nscn = namsinhcn.getText();
        if (nscn == null) nscn = "";
        String cvcn = chucvucn.getText();
        if (cvcn == null) cvcn = "";
        String gtlt = gioitinhlt.getValue();
        String gtcn = gioitinhcn.getValue();

        if (ml.length() < 1 | tl.length() < 1 | ss.length() < 1 | em.length() < 1 | htlt.length() < 1 |
                gtlt.length() < 1 | nslt.length() < 1 | htcn.length() < 1 | gtcn.length() < 1 | nscn.length() < 1 | cvcn.length() < 1) {
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
        String strsql = "SELECT * FROM lop_hoc WHERE MaLop = '" + ml + "'";
        ResultSet rs = comm.executeQuery(strsql);
        if (rs.next()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Mã lớp đã tồn tại!");
            alert.setContentText(null);
            Stage sta = (Stage) alert.getDialogPane().getScene().getWindow();
            sta.getIcons().add(new Image("/logohvan.png"));
            alert.showAndWait();
            return;
        }
        if (checkint(ss) == false) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Sĩ số không phù hợp!");
            alert.setContentText(null);
            Stage sta = (Stage) alert.getDialogPane().getScene().getWindow();
            sta.getIcons().add(new Image("/logohvan.png"));
            alert.showAndWait();
            return;
        }
        if (checkns(nslt) == false) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Năm sinh LT không phù hợp!");
            alert.setContentText(null);
            Stage sta = (Stage) alert.getDialogPane().getScene().getWindow();
            sta.getIcons().add(new Image("/logohvan.png"));
            alert.showAndWait();
            return;
        }
        if (checkns(nscn) == false) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Năm sinh CN không phù hợp!");
            alert.setContentText(null);
            Stage sta = (Stage) alert.getDialogPane().getScene().getWindow();
            sta.getIcons().add(new Image("/logohvan.png"));
            alert.showAndWait();
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText("Bạn chắc chắn muốn thêm thông tin lớp học này chứ?");
        alert.setContentText(null);
        Stage sta = (Stage) alert.getDialogPane().getScene().getWindow();
        sta.getIcons().add(new Image("/logohvan.png"));
        Optional<ButtonType> rss = alert.showAndWait();
        if (rss.get() == ButtonType.CANCEL) return;
        strsql = String.format("INSERT INTO lop_hoc (MaLop, TenLop, SiSo, Email, HoTenLT, GioiTinhLT, NamSinhLT" +
                ", HoTenCN, GioiTinhCN, NamSinhCN, ChucVuCN) VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', " +
                "'%s', '%s')", ml, tl, ss, em, htlt, gtlt, nslt, htcn, gtcn, nscn, cvcn);
        comm.execute(strsql);
        strsql = String.format("INSERT INTO account (username, password, type) " +
                "VALUES ('%s', '%s', 'Lớp học')", ml, Main.md5(ml));
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
        gioitinhlt.setItems(FXCollections.observableArrayList("Nam","Nữ"));
        gioitinhcn.setItems(FXCollections.observableArrayList("Nam","Nữ"));
        gioitinhcn.setValue("Nam");
        gioitinhlt.setValue("Nam");
    }
}
