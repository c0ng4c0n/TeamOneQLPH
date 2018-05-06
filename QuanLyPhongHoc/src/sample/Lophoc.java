package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class Lophoc {
    private final SimpleStringProperty ml;
    private final SimpleStringProperty tl;
    private final SimpleStringProperty el;
    private final SimpleStringProperty sl;
    private final SimpleStringProperty tlt;
    private final SimpleStringProperty glt;
    private final SimpleStringProperty nlt;
    private final SimpleStringProperty tcn;
    private final SimpleStringProperty gcn;
    private final SimpleStringProperty ncn;
    private final SimpleStringProperty ccn;

    public Lophoc(String ml, String tl, String el, String sl, String tlt, String glt, String nlt, String tcn, String gcn, String ncn, String ccn) {
        this.ml = new SimpleStringProperty(ml);
        this.tl = new SimpleStringProperty(tl);
        this.el = new SimpleStringProperty(el);
        this.sl = new SimpleStringProperty(sl);
        this.tlt = new SimpleStringProperty(tlt);
        this.glt = new SimpleStringProperty(glt);
        this.nlt = new SimpleStringProperty(nlt);
        this.tcn = new SimpleStringProperty(tcn);
        this.gcn = new SimpleStringProperty(gcn);
        this.ncn = new SimpleStringProperty(ncn);
        this.ccn = new SimpleStringProperty(ccn);
    }

    public String getMl() {
        return ml.get();
    }

    public SimpleStringProperty mlProperty() {
        return ml;
    }

    public void setMl(String ml) {
        this.ml.set(ml);
    }

    public String getTl() {
        return tl.get();
    }

    public SimpleStringProperty tlProperty() {
        return tl;
    }

    public void setTl(String tl) {
        this.tl.set(tl);
    }

    public String getEl() {
        return el.get();
    }

    public SimpleStringProperty elProperty() {
        return el;
    }

    public void setEl(String el) {
        this.el.set(el);
    }

    public String getSl() {
        return sl.get();
    }

    public SimpleStringProperty slProperty() {
        return sl;
    }

    public void setSl(String sl) {
        this.sl.set(sl);
    }

    public String getTlt() {
        return tlt.get();
    }

    public SimpleStringProperty tltProperty() {
        return tlt;
    }

    public void setTlt(String tlt) {
        this.tlt.set(tlt);
    }

    public String getGlt() {
        return glt.get();
    }

    public SimpleStringProperty gltProperty() {
        return glt;
    }

    public void setGlt(String glt) {
        this.glt.set(glt);
    }

    public String getNlt() {
        return nlt.get();
    }

    public SimpleStringProperty nltProperty() {
        return nlt;
    }

    public void setNlt(String nlt) {
        this.nlt.set(nlt);
    }

    public String getTcn() {
        return tcn.get();
    }

    public SimpleStringProperty tcnProperty() {
        return tcn;
    }

    public void setTcn(String tcn) {
        this.tcn.set(tcn);
    }

    public String getGcn() {
        return gcn.get();
    }

    public SimpleStringProperty gcnProperty() {
        return gcn;
    }

    public void setGcn(String gcn) {
        this.gcn.set(gcn);
    }

    public String getNcn() {
        return ncn.get();
    }

    public SimpleStringProperty ncnProperty() {
        return ncn;
    }

    public void setNcn(String ncn) {
        this.ncn.set(ncn);
    }

    public String getCcn() {
        return ccn.get();
    }

    public SimpleStringProperty ccnProperty() {
        return ccn;
    }

    public void setCcn(String ccn) {
        this.ccn.set(ccn);
    }

    public static void setLh(Lophoc lh, TextField ml, TextField tl, TextField sl, TextField el, TextField tlt, ComboBox<String> glt, TextField nlt, TextField tcn, ComboBox<String> gcn, TextField ncn, TextField ccn) {
        ml.setText(lh.getMl());
        tl.setText(lh.getTl());
        sl.setText(lh.getSl());
        el.setText(lh.getEl());
        tlt.setText(lh.getTlt());
        glt.setValue(lh.getGlt());
        nlt.setText(lh.getNlt());
        tcn.setText(lh.getTcn());
        gcn.setValue(lh.getGcn());
        ncn.setText(lh.getNcn());
        ccn.setText(lh.getCcn());

    }
}
