package MuonTra;

import connectmySQL.DataAccess;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import loginqlph.MainLogin;
import sample.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainMT extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public List<Object[]> laydsmt() {

        List<Object[]> istmt = new ArrayList();

        try {
            Connection conn = DataAccess.ketNoi();

            Statement comm = conn.createStatement();

            String strsql = "SELECT * FROM muon_phong";
            ResultSet rs = comm.executeQuery(strsql);

            Object[] mt = null;
            while (rs.next()) {
                mt = new Object[10];
                mt[0] = rs.getString("MaNV");
                mt[1] = rs.getString("MaLop");
                mt[2] = rs.getString("MaPhong");
                mt[3] = rs.getString("TGBatDau");
                mt[3] = mt[3].toString().substring(0,19);
                mt[4] = rs.getString("TGKetThuc");
                mt[4] = mt[4].toString().substring(0,19);
                if (mt[4].toString().startsWith("2001")) mt[4] = "";
                mt[5] = rs.getString("HoTenNM");
                mt[6] = rs.getString("SDTNM");
                mt[7] = rs.getString("MayChieu");
                mt[8] = rs.getString("Micro");
                mt[9] = rs.getString("DKDieuHoa");
                istmt.add(mt);
            }

            conn.close();

        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        return istmt;
    }

    @Override
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/MuonTra/LoginMT.fxml"));
        MainLogin.logscreen = "/MuonTra/LoginMT.fxml";
        Scene scene = new Scene(root);
        stage.setTitle("Đăng nhập");
        stage.initStyle(StageStyle.DECORATED);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.getIcons().add(new Image("/logohvan.png"));
        stage.show();
    }

}
