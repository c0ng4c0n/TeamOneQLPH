package MuonTra;

import connectmySQL.DataAccess;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.Main;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class LoginMT implements Initializable {

    public static String usermt = "";
    public TextField username, password;
    public Button loginbut;

    public void EnterMT(KeyEvent e) throws Exception {
        if(e.getCode().toString().equals("ENTER"))
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
                pass = Main.md5(pass);
                Connection conn = DataAccess.ketNoi();
                Statement comm = conn.createStatement();
                String strsql = "SELECT * FROM account WHERE (username = '" + user + "' AND password = '" + pass + "')";
                ResultSet rs = comm.executeQuery(strsql);
                if (!rs.next()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Thông báo");
                    alert.setHeaderText("Bạn đã nhập sai tài khoản hoặc mật khẩu!");
                    alert.setContentText("Vui lòng kiểm tra lại.");
                    Stage sta = (Stage) alert.getDialogPane().getScene().getWindow();
                    sta.getIcons().add(new Image("/logohvan.png"));
                    alert.showAndWait();

                } else {
                    usermt = user;
                    String type = rs.getString("type");
                    if (type.startsWith("Nhân viên") & (type.length() == 9)) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Thông báo");
                        alert.setHeaderText("Đăng nhập thành công!");
                        alert.setContentText("Chào mừng bạn đến với phần mềm Quản lý mượn trả phòng!");
                        Stage sta = (Stage) alert.getDialogPane().getScene().getWindow();
                        sta.getIcons().add(new Image("/logohvan.png"));
                        alert.showAndWait();

                        Stage stage = (Stage) loginbut.getScene().getWindow();
                        stage.close();

                        Parent root = FXMLLoader.load(getClass().getResource("/MuonTra/MuonTra.fxml"));
                        Stage stg = new Stage();
                        Scene scene = new Scene(root);
                        stg.setTitle("Quản Lý Mượn Trả Phòng");
                        stg.initStyle(StageStyle.DECORATED);
                        stg.setScene(scene);
                        stg.getIcons().add(new Image("/logohvan.png"));
                        stg.show();
                        stg.setMinHeight(400);
                        stg.setMinWidth(666);
                    }
                    else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Thông báo");
                        alert.setHeaderText("Chỉ tài khoản nhân viên mới có quyền đăng nhập!");
                        alert.setContentText("Vui lòng kiểm tra lại.");
                        Stage sta = (Stage) alert.getDialogPane().getScene().getWindow();
                        sta.getIcons().add(new Image("/logohvan.png"));
                        alert.showAndWait();
                    }
                    conn.close();
                }
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
            pass = Main.md5(pass);
            Connection conn = DataAccess.ketNoi();
            Statement comm = conn.createStatement();
            String strsql = "SELECT * FROM account WHERE (username = '" + user + "' AND password = '" + pass + "')";
            ResultSet rs = comm.executeQuery(strsql);
            if (!rs.next()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Thông báo");
                alert.setHeaderText("Bạn đã nhập sai tài khoản hoặc mật khẩu!");
                alert.setContentText("Vui lòng kiểm tra lại.");
                Stage sta = (Stage) alert.getDialogPane().getScene().getWindow();
                sta.getIcons().add(new Image("/logohvan.png"));
                alert.showAndWait();

            } else {
                usermt = user;
                String type = rs.getString("type");
                if (type.startsWith("Nhân viên") & (type.length() == 9)) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thông báo");
                    alert.setHeaderText("Đăng nhập thành công!");
                    alert.setContentText("Chào mừng bạn đến với phần mềm Quản lý mượn trả phòng!");
                    Stage stg = (Stage) alert.getDialogPane().getScene().getWindow();
                    stg.getIcons().add(new Image("/logohvan.png"));
                    alert.showAndWait();

                    stg = (Stage) loginbut.getScene().getWindow();
                    stg.close();

                    Parent root = FXMLLoader.load(getClass().getResource("/MuonTra/MuonTra.fxml"));
                    stg = new Stage();
                    Scene scene = new Scene(root);
                    stg.setTitle("Quản Lý Mượn Trả Phòng");
                    stg.initStyle(StageStyle.DECORATED);
                    stg.setScene(scene);
                    stg.getIcons().add(new Image("/logohvan.png"));
                    stg.show();
                    stg.setMinHeight(400);
                    stg.setMinWidth(666);

                }
                else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Thông báo");
                    alert.setHeaderText("Chỉ tài khoản nhân viên mới có quyền đăng nhập!");
                    alert.setContentText("Vui lòng kiểm tra lại.");
                    Stage sta = (Stage) alert.getDialogPane().getScene().getWindow();
                    sta.getIcons().add(new Image("/logohvan.png"));
                    alert.showAndWait();
                }
                conn.close();
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
