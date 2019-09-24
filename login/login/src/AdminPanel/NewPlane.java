package AdminPanel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author root
 */
public class NewPlane {
    private final StringProperty addedplaneid;
    private final StringProperty planename;
    private final StringProperty planefrom;
    private final StringProperty planeto;
    private final StringProperty planetime;
    private final StringProperty planeprice;
    private final StringProperty columnplaneid;

    public NewPlane(String addedplaneid, String planename, String planefrom, String planeto, String planetime, String planeprice, String columnplaneid) {
        this.addedplaneid = new SimpleStringProperty(addedplaneid);
        this.planename = new SimpleStringProperty(planename);
        this.planefrom = new SimpleStringProperty(planefrom);
        this.planeto = new SimpleStringProperty(planeto);
        this.planetime = new SimpleStringProperty(planetime);
        this.planeprice = new SimpleStringProperty(planeprice);
        this.columnplaneid = new SimpleStringProperty(columnplaneid);
    }

    public String getAddedplaneid() {
        return addedplaneid.get();
    }

    public void setAddedplaneid(String value) {
        addedplaneid.set(value);
    }
    
    public String getplanename() {
        return planename.get();
    }

    public void setPlanename(String value) {
        planename.set(value);
    }
    
    public String getPlanefrom() {
        return planefrom.get();
    }

    public void setPlanefrom(String value) {
        planefrom.set(value);
    }
    public String getPlaneto() {
        return planeto.get();
    }

    public void setPlaneto(String value) {
        planeto.set(value);
    }
    public String getPlanetime() {
        return planetime.get();
    }

    public void setplanetime(String value) {
        planetime.set(value);
    }
    public String getPlaneprice() {
        return planeprice.get();
    }

    public void setPlaneprice(String value) {
        planeprice.set(value);
    }
    public String getColumnplaneid() {
        return columnplaneid.get();
    }

    public void setColumnplaneid(String value) {
        columnplaneid.set(value);
    }
    
    public StringProperty addedplaneidProperty() {
        return addedplaneid;
    }

    public StringProperty planenameProperty() {
        return planename;
    }

    public StringProperty planefromProperty() {
        return planefrom;
    }

    public StringProperty planetoProperty() {
        return planeto;
    }

    public StringProperty planetimeProperty() {
        return planetime;
    }

    public StringProperty planepriceProperty() {
        return planeprice;
    }
    public StringProperty columnplaneidProperty() {
        return columnplaneid;
    }
    
}
