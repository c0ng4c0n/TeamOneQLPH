package AddEditTK;

import connectmySQL.DataAccess;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.Controller;
import sample.Main;
import sample.Taikhoan;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;

public class Ediacc implements Initializable{
    @FXML
    public TextField ediuser, edipass;
    @FXML
    public ComboBox<String> editype;
    public static String ube = "", pbe = "", tbe = "";
    @FXML
    public Button editok, editcan;

    public void EditcanBut(MouseEvent event) {
        Stage stage = (Stage) editcan.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void EnterEdit(KeyEvent e) throws Exception{
        if(e.getCode().toString().equals("ENTER"))
        {
            String user = ediuser.getText();
            String pass = edipass.getText();
            String type = editype.getValue();
            if (user == null) user = "";
            if (pass == null) pass = "";
            if (type == null) type = "";
            if ((user.length() < 1) | (pass.length() < 1) | (type.length() < 1)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Thông báo");
                alert.setHeaderText(null);
                alert.setContentText("Vui lòng điền đầy đủ thông tin!");
                Stage sta = (Stage) alert.getDialogPane().getScene().getWindow();
                sta.getIcons().add(new Image("/logohvan.png"));
                alert.showAndWait();
            } else {
                Connection conn = DataAccess.ketNoi();
                Statement comm = conn.createStatement();
                String strsql = "SELECT * FROM account WHERE (username = '" + user + "')";
                ResultSet rs = comm.executeQuery(strsql);

                if (rs.next() & !user.startsWith(ube) & user.length() != ube.length()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Thông báo");
                    alert.setHeaderText(null);
                    alert.setContentText("Tài khoản đã tồn tại!");
                    Stage sta = (Stage) alert.getDialogPane().getScene().getWindow();
                    sta.getIcons().add(new Image("/logohvan.png"));
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Thông báo");
                    alert.setHeaderText("Bạn chắc chắn muốn chỉnh sửa thông tin chứ?");
                    alert.setContentText(null);
                    Stage sta = (Stage) alert.getDialogPane().getScene().getWindow();
                    sta.getIcons().add(new Image("/logohvan.png"));
                    Optional<ButtonType> rst = alert.showAndWait();
                    if (rst.get() == ButtonType.OK) {
                        pass = Main.md5(pass);
                        conn = DataAccess.ketNoi();
                        comm = conn.createStatement();
                        strsql = "UPDATE account SET username = '" + user + "', password = '" + pass + "', type = '" + type + "' WHERE account.username = '" + ube + "'";
                        comm.execute(strsql);
                        Stage stage = (Stage) editcan.getScene().getWindow();
                        stage.close();
                    }
                }
            }
        }
    }

    public void EditokBut(MouseEvent event) throws Exception {
        String user = ediuser.getText();
        String pass = edipass.getText();
        String type = editype.getValue();
        if (user == null) user = "";
        if (pass == null) pass = "";
        if (type == null) type = "";
        if ((user.length() < 1) | (pass.length() < 1) | (type.length() < 1)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Thông báo");
            alert.setHeaderText(null);
            alert.setContentText("Vui lòng điền đầy đủ thông tin!");
            Stage sta = (Stage) alert.getDialogPane().getScene().getWindow();
            sta.getIcons().add(new Image("/logohvan.png"));
            alert.showAndWait();
        } else {
            Connection conn = DataAccess.ketNoi();
            Statement comm = conn.createStatement();
            String strsql = "SELECT * FROM account WHERE (username = '" + user + "')";
            ResultSet rs = comm.executeQuery(strsql);

            if (rs.next() & !user.equals(ube)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Thông báo");
                alert.setHeaderText(null);
                alert.setContentText("Tài khoản đã tồn tại!");
                Stage sta = (Stage) alert.getDialogPane().getScene().getWindow();
                sta.getIcons().add(new Image("/logohvan.png"));
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Thông báo");
                alert.setHeaderText("Bạn chắc chắn muốn chỉnh sửa thông tin chứ?");
                alert.setContentText(null);
                Stage sta = (Stage) alert.getDialogPane().getScene().getWindow();
                sta.getIcons().add(new Image("/logohvan.png"));
                Optional<ButtonType> rst = alert.showAndWait();
                if (rst.get() == ButtonType.OK) {
                    pass = Main.md5(pass);
                    conn = DataAccess.ketNoi();
                    comm = conn.createStatement();
                    strsql = "UPDATE account SET username = '" + user + "', password = '" + pass + "', type = '" + type + "' WHERE account.username = '" + ube + "'";
                    comm.execute(strsql);
                    Stage stage = (Stage) editcan.getScene().getWindow();
                    stage.close();
                }
            }
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        editype.setItems(FXCollections.observableArrayList("Nhân viên", "Giáo viên", "Lớp học"));
        Taikhoan.setTk(Controller.EditTk, ediuser, edipass, editype);
    }
}
