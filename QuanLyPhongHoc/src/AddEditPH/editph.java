package AddEditPH;

import MuonTra.MuonTra;
import connectmySQL.DataAccess;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.Controller;
import sample.Phonghoc;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;

public class editph implements Initializable {

    public boolean checkint(String s) {
        for (int i = 0; i < s.length(); ++i)
            if (s.charAt(i) < '0' | s.charAt(i) > '9') return false;
        return true;
    }
    static String idphong;
    public TextField maphong, succhua;
    public CheckBox camera, micro, maychieu, dieuhoa;
    public ComboBox<String> tinhtrang, loaiphong;
    public Button okbut, cancelbut;

    public void okbutClick(MouseEvent event) throws Exception{
        String mp = maphong.getText();
        if (mp == null) mp = "";
        mp = MuonTra.toUpper(mp);
        String  sc = succhua.getText();
        if (sc == null) sc = "";
        String mc, dh, ca, mi;
        if (maychieu.isSelected()) mc = "Có"; else mc = "Không";
        if (dieuhoa.isSelected()) dh = "Có"; else dh = "Không";
        if (camera.isSelected()) ca = "Có"; else ca = "Không";
        if (micro.isSelected()) mi = "Có"; else mi = "Không";
        String lp = loaiphong.getValue();
        String tt = tinhtrang.getValue();
        if (mp.length() < 1 | sc.length() < 1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Vui lòng nhập đầy đủ thông tin!");
            alert.setContentText(null);
            Stage sta = (Stage) alert.getDialogPane().getScene().getWindow();
            sta.getIcons().add(new Image("/logohvan.png"));
            alert.showAndWait();
            return;
        }
        if (checkint(sc) == false) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Sức chứa phải là một số!");
            alert.setContentText(null);
            Stage sta = (Stage) alert.getDialogPane().getScene().getWindow();
            sta.getIcons().add(new Image("/logohvan.png"));
            alert.showAndWait();
            return;
        }
        Connection conn = DataAccess.ketNoi();
        Statement comm = conn.createStatement();
        String strsql = "SELECT * FROM phong_hoc WHERE MaPhong = '" + mp + "'";
        ResultSet rs = comm.executeQuery(strsql);
        if (rs.next() & !mp.equals(idphong)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Mã phòng đã tồn tại!");
            alert.setContentText(null);
            Stage sta = (Stage) alert.getDialogPane().getScene().getWindow();
            sta.getIcons().add(new Image("/logohvan.png"));
            alert.showAndWait();
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText("Bạn chắc chắn muốn chỉnh sửa phòng này chứ?");
        alert.setContentText(null);
        Stage sta = (Stage) alert.getDialogPane().getScene().getWindow();
        sta.getIcons().add(new Image("/logohvan.png"));
        Optional<ButtonType> rss = alert.showAndWait();
        if (rss.get() == ButtonType.CANCEL) return;
        strsql = String.format("UPDATE phong_hoc SET MaPhong = '%s', LoaiPhong = '%s', TinhTrang = '%s', SucChua = '%s'," +
                " MayChieu = '%s', DieuHoa = '%s', Micro = '%s', Camera = '%s' WHERE phong_hoc.MaPhong = '%s'", mp, lp, tt, sc, mc, dh, mi, ca, idphong);
        comm.execute(strsql);
        strsql = String.format("UPDATE muon_phong SET MaPhong = '%s' WHERE muon_phong.MaPhong = '%s'", mp, idphong);
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
        loaiphong.setItems(FXCollections.observableArrayList("Phòng thường", "Phòng máy", "Hội trường"));
        tinhtrang.setItems(FXCollections.observableArrayList("Trống", "Đang sửa"));
        Phonghoc.setPh(Controller.EditPh, maphong, loaiphong, tinhtrang, succhua, maychieu, dieuhoa, micro, camera);
        idphong = Controller.EditPh.getMp();
    }
}
