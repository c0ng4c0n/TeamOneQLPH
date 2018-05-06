package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class Giangday {
    private final SimpleStringProperty msgv;
    private final SimpleStringProperty mslh;
    private final SimpleStringProperty mhoc;

    public String getMsgv() {
        return msgv.get();
    }

    public SimpleStringProperty msgvProperty() {
        return msgv;
    }

    public void setMsgv(String msgv) {
        this.msgv.set(msgv);
    }

    public String getMslh() {
        return mslh.get();
    }

    public SimpleStringProperty mslhProperty() {
        return mslh;
    }

    public void setMslh(String mslh) {
        this.mslh.set(mslh);
    }

    public String getMhoc() {
        return mhoc.get();
    }

    public SimpleStringProperty mhocProperty() {
        return mhoc;
    }

    public void setMhoc(String mhoc) {
        this.mhoc.set(mhoc);
    }

    public Giangday(String msgv, String mslh, String mhoc) {
        this.msgv = new SimpleStringProperty(msgv);
        this.mslh = new SimpleStringProperty(mslh);
        this.mhoc = new SimpleStringProperty(mhoc);
    }

    public static void setGd(Giangday gd, TextField gv, TextField lh, TextField mh) {
        gv.setText(gd.getMsgv());
        lh.setText(gd.getMslh());
        mh.setText(gd.getMhoc());
    }

}
