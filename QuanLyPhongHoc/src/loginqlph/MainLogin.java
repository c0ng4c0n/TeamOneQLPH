package loginqlph;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainLogin extends Application{
    public static String logscreen = "";
    @Override
    public void start(Stage loginstage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/loginqlph/Login.fxml"));
        logscreen = "/loginqlph/Login.fxml";
        Parent parent = fxmlLoader.load();
        Scene scene = new Scene(parent);
        loginstage.setTitle("Đăng nhập");
        loginstage.initStyle(StageStyle.DECORATED);
        loginstage.setScene(scene);
        loginstage.show();
        loginstage.setResizable(false);
        loginstage.getIcons().add(new Image("/logohvan.png"));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
