package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class Nhanvien {
    private final SimpleStringProperty mnv;
    private final SimpleStringProperty tnv;
    private final SimpleStringProperty gnv;
    private final SimpleStringProperty nnv;
    private final SimpleStringProperty cnv;
    private final SimpleStringProperty mldnv;

    public Nhanvien(String mnv, String tnv, String gnv, String nnv, String cnv, String mldnv) {
        this.mnv = new SimpleStringProperty(mnv);
        this.tnv = new SimpleStringProperty(tnv);
        this.gnv = new SimpleStringProperty(gnv);
        this.nnv = new SimpleStringProperty(nnv);
        this.cnv = new SimpleStringProperty(cnv);
        this.mldnv = new SimpleStringProperty(mldnv);
    }

    public String getMnv() {
        return mnv.get();
    }

    public SimpleStringProperty mnvProperty() {
        return mnv;
    }

    public void setMnv(String mnv) {
        this.mnv.set(mnv);
    }

    public String getTnv() {
        return tnv.get();
    }

    public SimpleStringProperty tnvProperty() {
        return tnv;
    }

    public void setTnv(String tnv) {
        this.tnv.set(tnv);
    }

    public String getGnv() {
        return gnv.get();
    }

    public SimpleStringProperty gnvProperty() {
        return gnv;
    }

    public void setGnv(String gnv) {
        this.gnv.set(gnv);
    }

    public String getNnv() {
        return nnv.get();
    }

    public SimpleStringProperty nnvProperty() {
        return nnv;
    }

    public void setNnv(String nnv) {
        this.nnv.set(nnv);
    }

    public String getCnv() {
        return cnv.get();
    }

    public SimpleStringProperty cnvProperty() {
        return cnv;
    }

    public void setCnv(String cnv) {
        this.cnv.set(cnv);
    }

    public String getMldnv() {
        return mldnv.get();
    }

    public SimpleStringProperty mldnvProperty() {
        return mldnv;
    }

    public void setMldnv(String mldnv) {
        this.mldnv.set(mldnv);
    }

    public static void setNv(Nhanvien nv, TextField mnv, TextField tnv, ComboBox<String> gnv, TextField nnv, TextField cnv, TextField mldnv) {
        mnv.setText(nv.getMnv());
        tnv.setText(nv.getTnv());
        gnv.setValue(nv.getGnv());
        nnv.setText(nv.getNnv());
        cnv.setText(nv.getCnv());
        mldnv.setText(nv.getMldnv());
    }
}
