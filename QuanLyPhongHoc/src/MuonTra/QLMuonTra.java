package MuonTra;

import javafx.beans.property.SimpleStringProperty;

public class QLMuonTra {
    private final SimpleStringProperty mnv;
    private final SimpleStringProperty ml;
    private final SimpleStringProperty mp;
    private final SimpleStringProperty tnm;
    private final SimpleStringProperty sdt;
    private final SimpleStringProperty mc;
    private final SimpleStringProperty dh;
    private final SimpleStringProperty mi;
    private final SimpleStringProperty bd;
    private final SimpleStringProperty kt;

    public String getMp() {
        return mp.get();
    }

    public SimpleStringProperty mpProperty() {
        return mp;
    }

    public void setMp(String mp) {
        this.mp.set(mp);
    }

    public QLMuonTra(String mnv, String ml, String mp, String bd, String kt, String tnm, String sdt, String mc, String mi, String dh) {
        this.mnv = new SimpleStringProperty(mnv);
        this.ml = new SimpleStringProperty(ml);
        this.tnm = new SimpleStringProperty(tnm);
        this.mp = new SimpleStringProperty(mp);
        this.sdt = new SimpleStringProperty(sdt);
        this.mc = new SimpleStringProperty(mc);
        this.dh = new SimpleStringProperty(dh);
        this.mi = new SimpleStringProperty(mi);
        this.bd = new SimpleStringProperty(bd);
        this.kt = new SimpleStringProperty(kt);
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

    public String getMl() {
        return ml.get();
    }

    public SimpleStringProperty mlProperty() {
        return ml;
    }

    public void setMl(String ml) {
        this.ml.set(ml);
    }

    public String getTnm() {
        return tnm.get();
    }

    public SimpleStringProperty tnmProperty() {
        return tnm;
    }

    public void setTnm(String tnm) {
        this.tnm.set(tnm);
    }

    public String getSdt() {
        return sdt.get();
    }

    public SimpleStringProperty sdtProperty() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt.set(sdt);
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

    public String getBd() {
        return bd.get();
    }

    public SimpleStringProperty bdProperty() {
        return bd;
    }

    public void setBd(String bd) {
        this.bd.set(bd);
    }

    public String getKt() {
        return kt.get();
    }

    public SimpleStringProperty ktProperty() {
        return kt;
    }

    public void setKt(String kt) {
        this.kt.set(kt);
    }
}
