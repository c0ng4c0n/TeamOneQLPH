package AddEditTK;

import com.jfoenix.controls.JFXButton;
import connectmySQL.DataAccess;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.Main;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class Addacc implements Initializable{

    public TextField adduser, addpass;
    public ComboBox<String> addtype;
    public JFXButton addbut, canbut;

    public void CancelAdd(MouseEvent event) {
        Stage stage = (Stage) canbut.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void EnterAdd(KeyEvent e) throws Exception {
        if(e.getCode().toString().equals("ENTER"))
        {
            String user = adduser.getText();
            String pass = addpass.getText();
            String type = addtype.getValue().toString();
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
            }
            else{
                Connection conn = DataAccess.ketNoi();
                Statement comm = conn.createStatement();
                String strsql = "SELECT * FROM account WHERE (username = '" + user + "')";
                ResultSet rs = comm.executeQuery(strsql);
                if (rs.next()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Thông báo");
                    alert.setHeaderText(null);
                    alert.setContentText("Tài khoản đã tồn tại!");
                    Stage sta = (Stage) alert.getDialogPane().getScene().getWindow();
                    sta.getIcons().add(new Image("/logohvan.png"));
                    alert.showAndWait();
                }
                else
                {
                    pass = Main.md5(pass);
                    strsql = "INSERT INTO account (username, password, type) VALUES ('" + user + "', '" + pass + "', '" + type + "')";
                    comm.execute(strsql);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thông báo");
                    alert.setHeaderText(null);
                    alert.setContentText("Thêm mới tài khoản thành công!");
                    Stage sta = (Stage) alert.getDialogPane().getScene().getWindow();
                    sta.getIcons().add(new Image("/logohvan.png"));
                    alert.showAndWait();
                    Stage stage = (Stage) addbut.getScene().getWindow();
                    stage.close();
                }
            }
        }
    }

    public void AddAccount(MouseEvent event) throws Exception{
        String user = adduser.getText();
        String pass = addpass.getText();
        String type = addtype.getValue();
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
        }
        else{
            Connection conn = DataAccess.ketNoi();
            Statement comm = conn.createStatement();
            String strsql = "SELECT * FROM account WHERE (username = '" + user + "')";
            ResultSet rs = comm.executeQuery(strsql);
            if (rs.next()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Thông báo");
                alert.setHeaderText(null);
                alert.setContentText("Tài khoản đã tồn tại!");
                Stage sta = (Stage) alert.getDialogPane().getScene().getWindow();
                sta.getIcons().add(new Image("/logohvan.png"));
                alert.showAndWait();
            }
            else
            {
                pass = Main.md5(pass);
                strsql = "INSERT INTO account (username, password, type) VALUES ('" + user + "', '" + pass + "', '" + type + "')";
                comm.execute(strsql);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thông báo");
                alert.setHeaderText(null);
                alert.setContentText("Thêm mới tài khoản thành công!");
                Stage sta = (Stage) alert.getDialogPane().getScene().getWindow();
                sta.getIcons().add(new Image("/logohvan.png"));
                alert.showAndWait();
                Stage stage = (Stage) addbut.getScene().getWindow();
                stage.close();
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        addtype.setItems(FXCollections.observableArrayList("Nhân viên", "Giáo viên", "Lớp học"));
        addtype.setValue("Nhân viên");
    }
}
