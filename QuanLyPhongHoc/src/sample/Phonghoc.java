package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class Phonghoc {
    private final SimpleStringProperty mp;
    private final SimpleStringProperty lp;
    private final SimpleStringProperty tt;
    private final SimpleStringProperty sc;
    private final SimpleStringProperty mc;
    private final SimpleStringProperty dh;
    private final SimpleStringProperty mi;
    private final SimpleStringProperty ca;

    public Phonghoc(String mp, String lp, String tt, String sc, String mc, String dh, String mi, String ca) {
        this.mp = new SimpleStringProperty(mp);
        this.lp = new SimpleStringProperty(lp);
        this.tt = new SimpleStringProperty(tt);
        this.sc = new SimpleStringProperty(sc);
        this.mc = new SimpleStringProperty(mc);
        this.dh = new SimpleStringProperty(dh);
        this.mi = new SimpleStringProperty(mi);
        this.ca = new SimpleStringProperty(ca);
    }

    public String getMp() {
        return mp.get();
    }

    public SimpleStringProperty mpProperty() {
        return mp;
    }

    public void setMp(String mp) {
        this.mp.set(mp);
    }

    public String getLp() {
        return lp.get();
    }

    public SimpleStringProperty lpProperty() {
        return lp;
    }

    public void setLp(String lp) {
        this.lp.set(lp);
    }

    public String getTt() {
        return tt.get();
    }

    public SimpleStringProperty ttProperty() {
        return tt;
    }

    public void setTt(String tt) {
        this.tt.set(tt);
    }

    public String getSc() {
        return sc.get();
    }

    public SimpleStringProperty scProperty() {
        return sc;
    }

    public void setSc(String sc) {
        this.sc.set(sc);
    }

    public String getMc() {
        return mc.get();
    }

    public SimpleStringProperty mcProperty() {
        return mc;
    }

    public void setMc(String mc) {
        this.mc.set(mc);
    }

    public String getDh() {
        return dh.get();
    }

    public SimpleStringProperty dhProperty() {
        return dh;
    }

    public void setDh(String dh) {
        this.dh.set(dh);
    }

    public String getMi() {
        return mi.get();
    }

    public SimpleStringProperty miProperty() {
        return mi;
    }

    public void setMi(String mi) {
        this.mi.set(mi);
    }

    public String getCa() {
        return ca.get();
    }

    public SimpleStringProperty caProperty() {
        return ca;
    }

    public void setCa(String ca) {
        this.ca.set(ca);
    }

    public static void setPh(Phonghoc ph, TextField mp, ComboBox<String> lp, ComboBox<String> tt, TextField sc, CheckBox mc, CheckBox dh, CheckBox mi, CheckBox ca) {
        mp.setText(ph.getMp());
        lp.setValue(ph.getLp());
        tt.setValue(ph.getTt());
        sc.setText(ph.getSc());
        if (ph.getMc().startsWith("Có")) mc.setSelected(true);
        if (ph.getDh().startsWith("Có")) dh.setSelected(true);
        if (ph.getMi().startsWith("Có")) mi.setSelected(true);
        if (ph.getCa().startsWith("Có")) ca.setSelected(true);
    }
}
