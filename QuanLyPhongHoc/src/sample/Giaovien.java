package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class Giaovien {
    private final SimpleStringProperty mgv;
    private final SimpleStringProperty tgv;
    private final SimpleStringProperty ggv;
    private final SimpleStringProperty ngv;
    private final SimpleStringProperty egv;
    private final SimpleStringProperty kgv;

    public Giaovien(String mgv, String tgv, String ggv, String ngv, String egv, String kgv) {
        this.mgv = new SimpleStringProperty(mgv);
        this.tgv = new SimpleStringProperty(tgv);
        this.ggv = new SimpleStringProperty(ggv);
        this.ngv = new SimpleStringProperty(ngv);
        this.egv = new SimpleStringProperty(egv);
        this.kgv = new SimpleStringProperty(kgv);
    }

    public String getMgv() {
        return mgv.get();
    }

    public SimpleStringProperty mgvProperty() {
        return mgv;
    }

    public void setMgv(String mgv) {
        this.mgv.set(mgv);
    }

    public String getTgv() {
        return tgv.get();
    }

    public SimpleStringProperty tgvProperty() {
        return tgv;
    }

    public void setTgv(String tgv) {
        this.tgv.set(tgv);
    }

    public String getGgv() {
        return ggv.get();
    }

    public SimpleStringProperty ggvProperty() {
        return ggv;
    }

    public void setGgv(String ggv) {
        this.ggv.set(ggv);
    }

    public String getNgv() {
        return ngv.get();
    }

    public SimpleStringProperty ngvProperty() {
        return ngv;
    }

    public void setNgv(String ngv) {
        this.ngv.set(ngv);
    }

    public String getEgv() {
        return egv.get();
    }

    public SimpleStringProperty egvProperty() {
        return egv;
    }

    public void setEgv(String egv) {
        this.egv.set(egv);
    }

    public String getKgv() {
        return kgv.get();
    }

    public SimpleStringProperty kgvProperty() {
        return kgv;
    }

    public void setKgv(String kgv) {
        this.kgv.set(kgv);
    }

    public static void setGv(Giaovien gv, TextField mgv, TextField tgv, ComboBox<String> ggv, TextField ngv, TextField egv, TextField kgv) {
        mgv.setText(gv.getMgv());
        tgv.setText(gv.getTgv());
        ggv.setValue(gv.getGgv());
        ngv.setText(gv.getNgv());
        egv.setText(gv.getEgv());
        kgv.setText(gv.getKgv());
    }
}
