package AdminPanel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author root
 */
public class NewBus {
    private final StringProperty addedbusid;
    private final StringProperty busname;
    private final StringProperty busfrom;
    private final StringProperty busto;
    private final StringProperty bustime;
    private final StringProperty busprice;
    private final StringProperty columnid;

    public NewBus(String addedbusid, String busname, String busfrom, String busto, String bustime, String busprice, String columnid) {
        this.addedbusid = new SimpleStringProperty(addedbusid);
        this.busname = new SimpleStringProperty(busname);
        this.busfrom = new SimpleStringProperty(busfrom);
        this.busto = new SimpleStringProperty(busto);
        this.bustime = new SimpleStringProperty(bustime);
        this.busprice = new SimpleStringProperty(busprice);
        this.columnid = new SimpleStringProperty(columnid);
    }

    public String getAddedbusid() {
        return addedbusid.get();
    }

    public void setAddedbusid(String value) {
        addedbusid.set(value);
    }
    
    public String getBusname() {
        return busname.get();
    }

    public void setBusname(String value) {
        busname.set(value);
    }
    
    public String getBusfrom() {
        return busfrom.get();
    }

    public void setBusfrom(String value) {
        busfrom.set(value);
    }
    public String getBusto() {
        return busto.get();
    }

    public void setBusto(String value) {
        busto.set(value);
    }
    public String getBustime() {
        return bustime.get();
    }

    public void setBustime(String value) {
        bustime.set(value);
    }
    public String getBusprice() {
        return busprice.get();
    }

    public void setBusprice(String value) {
        busprice.set(value);
    }
    public String getColumnid() {
        return columnid.get();
    }

    public void setColumnid(String value) {
        columnid.set(value);
    }
    
    public StringProperty addedbusidProperty() {
        return addedbusid;
    }

    public StringProperty busnameProperty() {
        return busname;
    }

    public StringProperty busfromProperty() {
        return busfrom;
    }

    public StringProperty bustoProperty() {
        return busto;
    }

    public StringProperty bustimeProperty() {
        return bustime;
    }

    public StringProperty buspriceProperty() {
        return busprice;
    }
    public StringProperty columnidProperty() {
        return columnid;
    }
}
