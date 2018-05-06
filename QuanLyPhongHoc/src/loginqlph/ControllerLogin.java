package loginqlph;

import connectmySQL.DataAccess;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.Main;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ControllerLogin implements Initializable{

    public TextField username;
    public PasswordField password;
    public Button loginbut;
    public AnchorPane loginpane;

    public static String tk;
    public static String ty;

    public void EnterKey (KeyEvent event) throws Exception {
        if(event.getCode().toString().equals("ENTER"))
        {
            String user = username.getText();
            String pass = password.getText();
            if (user == null) user = "";
            if (pass == null) pass = "";
            if (pass.length() < 1 | user.length() < 1) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Thông báo");
                alert.setHeaderText("Vui lòng nhập đầy đủ thông tin!");
                alert.setContentText(null);
                Stage sta = (Stage) alert.getDialogPane().getScene().getWindow();
                sta.getIcons().add(new Image("/logohvan.png"));
                alert.showAndWait();
            } else {
                tk = user;
                pass = Main.md5(pass);
                Connection conn = DataAccess.ketNoi();
                Statement comm = conn.createStatement();
                String strsql = "SELECT * FROM account WHERE (username = '" + user + "' AND password = '" + pass + "')";
                ResultSet rs = comm.executeQuery(strsql);
                if (!rs.next()) {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Thông báo");
                    alert.setHeaderText("Bạn đã nhập sai tài khoản hoặc mật khẩu!");
                    alert.setContentText("Vui lòng kiểm tra lại.");
                    Stage sta = (Stage) alert.getDialogPane().getScene().getWindow();
                    sta.getIcons().add(new Image("/logohvan.png"));
                    alert.showAndWait();

                } else {
                    ty = rs.getString("type");
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Thông báo");
                    alert.setHeaderText("Đăng nhập thành công!");
                    alert.setContentText("Chào mừng bạn đến với phần mềm Quản lý phòng học.");
                    Stage sta = (Stage) alert.getDialogPane().getScene().getWindow();
                    sta.getIcons().add(new Image("/logohvan.png"));
                    alert.showAndWait();

                    Stage stage = (Stage) loginbut.getScene().getWindow();
                    stage.close();

                    Parent root = FXMLLoader.load(getClass().getResource("/sample/sample.fxml"));
                    Stage stg = new Stage();
                    Scene scene = new Scene(root);
                    stg.setTitle("Quản Lý Phòng Học");
                    stg.initStyle(StageStyle.DECORATED);
                    stg.setScene(scene);
                    stg.getIcons().add(new Image("/logohvan.png"));
                    stg.show();
                    stg.setMinHeight(400);
                    stg.setMinWidth(650);
                }
                conn.close();
            }
        }
    }

    public void LoginClick(MouseEvent event) throws Exception{
        String user = username.getText();
        String pass = password.getText();
        if (user == null) user = "";
        if (pass == null) pass = "";
        if (pass.length() < 1 | user.length() < 1) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Vui lòng nhập đầy đủ thông tin!");
            alert.setContentText(null);
            Stage sta = (Stage) alert.getDialogPane().getScene().getWindow();
            sta.getIcons().add(new Image("/logohvan.png"));
            alert.showAndWait();
        } else {
            tk = user;
            pass = Main.md5(pass);
            Connection conn = DataAccess.ketNoi();
            Statement comm = conn.createStatement();
            String strsql = "SELECT * FROM account WHERE (username = '" + user + "' AND password = '" + pass + "')";
            ResultSet rs = comm.executeQuery(strsql);
            if (!rs.next()) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Thông báo");
                alert.setHeaderText("Bạn đã nhập sai tài khoản hoặc mật khẩu!");
                alert.setContentText("Vui lòng kiểm tra lại.");
                Stage sta = (Stage) alert.getDialogPane().getScene().getWindow();
                sta.getIcons().add(new Image("/logohvan.png"));
                alert.showAndWait();

            } else {
                ty = rs.getString("type");
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Thông báo");
                alert.setHeaderText("Đăng nhập thành công!");
                alert.setContentText("Chào mừng bạn đến với phần mềm Quản lý phòng học.");
                Stage sta = (Stage) alert.getDialogPane().getScene().getWindow();
                sta.getIcons().add(new Image("/logohvan.png"));
                alert.showAndWait();

                Stage stage = (Stage) loginbut.getScene().getWindow();
                stage.close();

                Parent root = FXMLLoader.load(getClass().getResource("/sample/sample.fxml"));
                Stage stg = new Stage();
                Scene scene = new Scene(root);
                stg.setTitle("Quản Lý Phòng Học");
                stg.initStyle(StageStyle.DECORATED);
                stg.setScene(scene);
                stg.getIcons().add(new Image("/logohvan.png"));
                stg.show();
                stg.setMinHeight(400);
                stg.setMinWidth(600);
            }
            conn.close();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
