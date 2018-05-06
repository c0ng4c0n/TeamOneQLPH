package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class Taikhoan {
    private final SimpleStringProperty acc;
    private final SimpleStringProperty pas;
    private final SimpleStringProperty typ;


    public Taikhoan(String acc, String pas, String typ) {
        this.acc = new SimpleStringProperty(acc);
        this.pas = new SimpleStringProperty(pas);
        this.typ = new SimpleStringProperty(typ);

    }

    public String getTyp() {
        return typ.get();
    }

    public SimpleStringProperty typProperty() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ.set(typ);
    }

    public String getAcc() {
        return acc.get();
    }

    public SimpleStringProperty accProperty() {
        return acc;
    }

    public void setAcc(String acc) {
        this.acc.set(acc);
    }

    public String getPas() {
        return pas.get();
    }

    public SimpleStringProperty pasProperty() {
        return pas;
    }

    public void setPas(String pas) {
        this.pas.set(pas);
    }

    public static void setTk(Taikhoan tk, TextField acc, TextField pas, ComboBox<String> typ) {
        acc.setText(tk.getAcc());
        pas.setText(tk.getPas());
        typ.setValue(tk.getTyp());
    }

}
