package AddEditGD;

import MuonTra.MuonTra;
import connectmySQL.DataAccess;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.Controller;
import sample.Giangday;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class Editgd implements Initializable {
    public TextField editgv, editlh, editmh;
    public Button okbut, cancelbut;

    public void CancelEdit(MouseEvent event) {
        Stage stage = (Stage) cancelbut.getScene().getWindow();
        stage.close();
    }

    public void Editgd(MouseEvent event) throws Exception {
        String gv = editgv.getText();
        if (gv == null) gv = "";
        gv = MuonTra.toUpper(gv);
        String lh = editlh.getText();
        if (lh == null) lh = "";
        lh = MuonTra.toUpper(lh);
        String mh = editmh.getText();
        if (mh == null) mh = "";
        if ((lh.length() < 1) | (gv.length() < 1) | (mh.length() < 1)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Thông báo");
            alert.setHeaderText(null);
            alert.setContentText("Vui lòng điền đầy đủ thông tin!");
            Stage sta = (Stage) alert.getDialogPane().getScene().getWindow();
            sta.getIcons().add(new Image("/logohvan.png"));
            alert.showAndWait();
            return;
        }
        String sgv = Controller.EditGd.getMsgv();
        String slh = Controller.EditGd.getMslh();
        String smh = Controller.EditGd.getMhoc();
        Connection conn = DataAccess.ketNoi();
        Statement comm = conn.createStatement();
        String strsql = "SELECT * FROM giang_day WHERE (MaGV = '" + gv + "' AND MaLop = '" + lh + "' AND MonHoc = '" + mh + "')";
        ResultSet rs = comm.executeQuery(strsql);
        if (rs.next() & !gv.equals(sgv) & !lh.equals(slh) & !mh.equals(smh)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Thông báo");
            alert.setHeaderText(null);
            alert.setContentText("Thông tin đã tồn tại!");
            Stage sta = (Stage) alert.getDialogPane().getScene().getWindow();
            sta.getIcons().add(new Image("/logohvan.png"));
            alert.showAndWait();
            return;
        }
        strsql = "SELECT * FROM giao_vien WHERE (MaGV = '" + gv + "')";
        rs = comm.executeQuery(strsql);
        if (!rs.next()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Thông báo");
            alert.setHeaderText(null);
            alert.setContentText("Mã GV không tồn tại!");
            Stage sta = (Stage) alert.getDialogPane().getScene().getWindow();
            sta.getIcons().add(new Image("/logohvan.png"));
            alert.showAndWait();
            return;
        }

        strsql = "SELECT * FROM lop_hoc WHERE (MaLop = '" + lh + "')";
        rs = comm.executeQuery(strsql);
        if (!rs.next()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Thông báo");
            alert.setHeaderText(null);
            alert.setContentText("Mã lớp không tồn tại!");
            Stage sta = (Stage) alert.getDialogPane().getScene().getWindow();
            sta.getIcons().add(new Image("/logohvan.png"));
            alert.showAndWait();
            return;
        }
        strsql = "UPDATE giang_day SET MaGV = '" + gv + "', MaLop = '" + lh + "', MonHoc = '" + mh +"' " +
                "WHERE MaGV = '" + sgv + "' AND MaLop = '" + slh + "' AND MonHoc = '" + smh + "'";
        comm.execute(strsql);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText(null);
        alert.setContentText("Chỉnh sửa thông tin thành công!");
        Stage sta = (Stage) alert.getDialogPane().getScene().getWindow();
        sta.getIcons().add(new Image("/logohvan.png"));
        alert.showAndWait();
        Stage stage = (Stage) okbut.getScene().getWindow();
        stage.close();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Giangday.setGd(Controller.EditGd, editgv, editlh, editmh);
    }
}
