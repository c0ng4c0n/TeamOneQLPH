package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class Sodienthoai {
    private final SimpleStringProperty msdt;
    private final SimpleStringProperty ssdt;
    private final SimpleStringProperty plsdt;

    public Sodienthoai(String msdt, String ssdt, String plsdt) {
        this.msdt = new SimpleStringProperty(msdt);
        this.ssdt = new SimpleStringProperty(ssdt);
        this.plsdt = new SimpleStringProperty(plsdt);
    }

    public String getMsdt() {
        return msdt.get();
    }

    public SimpleStringProperty msdtProperty() {
        return msdt;
    }

    public void setMsdt(String msdt) {
        this.msdt.set(msdt);
    }

    public String getSsdt() {
        return ssdt.get();
    }

    public SimpleStringProperty ssdtProperty() {
        return ssdt;
    }

    public void setSsdt(String ssdt) {
        this.ssdt.set(ssdt);
    }

    public String getPlsdt() {
        return plsdt.get();
    }

    public SimpleStringProperty plsdtProperty() {
        return plsdt;
    }

    public void setPlsdt(String plsdt) {
        this.plsdt.set(plsdt);
    }
    public static void setSdt(Sodienthoai sdt, TextField id, TextField num, ComboBox<String> typ) {
        id.setText(sdt.getMsdt());
        num.setText(sdt.getSsdt());
        typ.setValue(sdt.getPlsdt());
    }
}
