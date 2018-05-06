package AddEditLH;

import MuonTra.MuonTra;
import connectmySQL.DataAccess;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.Controller;
import sample.Lophoc;
import sample.Main;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;

public class editlh implements Initializable {

    public TextField malop, tenlop, siso, email, hotenlt, namsinhlt, hotencn, namsinhcn, chucvucn;
    public ComboBox<String> gioitinhlt, gioitinhcn;
    public Button okbut, cancelbut;
    static String idlop;

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
        ml = MuonTra.toUpper(ml);
        String tl = tenlop.getText();
        String ss = siso.getText();
        String em = email.getText();
        String htlt = hotenlt.getText();
        htlt = MuonTra.toFormat(htlt);
        String nslt = namsinhlt.getText();
        String htcn = hotencn.getText();
        htcn = MuonTra.toFormat(htcn);
        String nscn = namsinhcn.getText();
        String cvcn = chucvucn.getText();
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
        if (rs.next() & !ml.equals(idlop)) {
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
        alert.setHeaderText("Bạn chắc chắn muốn chỉnh sửa thông tin lớp học này chứ?");
        alert.setContentText(null);
        Stage sta = (Stage) alert.getDialogPane().getScene().getWindow();
        sta.getIcons().add(new Image("/logohvan.png"));
        Optional<ButtonType> rss = alert.showAndWait();
        if (rss.get() == ButtonType.CANCEL) return;
        strsql = String.format("UPDATE lop_hoc SET MaLop = '%s', TenLop = '%s', SiSo = '%s', Email = '%s'," +
                " HoTenLT = '%s', GioiTinhLT = '%s', NamSinhLT = '%s', HoTenCN = '%s', GioiTinhCN = '%s', NamSinhCN = '%s'," +
                " ChucVuCN = '%s' WHERE lop_hoc.MaLop = '%s'", ml, tl, ss, em, htlt, gtlt, nslt, htcn, gtcn, nscn, cvcn, idlop);
        comm.execute(strsql);
        strsql = String.format("UPDATE account SET username = '%s', password = '%s' WHERE account.username = '%s'", ml, Main.md5(ml), idlop);
        comm.execute(strsql);
        strsql = String.format("UPDATE muon_phong SET MaLop = '%s' WHERE muon_phong.MaLop = '%s'", ml, idlop);
        comm.execute(strsql);
        strsql = String.format("UPDATE so_dien_thoai SET MaSo = '%s' WHERE MaSo = '%s'", ml, idlop);
        comm.execute(strsql);
        strsql = String.format("UPDATE giang_day SET MaLop = '%s' WHERE MaLop = '%s'", ml, idlop);
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
        Lophoc.setLh(Controller.EditLh, malop, tenlop, siso, email, hotenlt, gioitinhlt, namsinhlt, hotencn, gioitinhcn, namsinhcn, chucvucn);
        idlop = Controller.EditLh.getMl();
    }
}
