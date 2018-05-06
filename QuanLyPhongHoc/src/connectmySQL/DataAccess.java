package connectmySQL;

import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataAccess {
    private static final String jdbc_driver = "com.mysql.jdbc.Driver";
    private static final String database_link = "jdbc:mysql://localhost:3306/qlph?useSSL=false&useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&autoReconnect=true";

    public static Connection ketNoi() {
        Connection conn = null;

        try {
            Class.forName(jdbc_driver);
            conn = DriverManager.getConnection(database_link,"root","");
        }
        catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Lỗi kết nối cơ sở dữ liệu!");
            alert.setContentText(null);
            alert.showAndWait();
        }
        return conn;

    }
}
