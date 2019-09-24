package AdminPanel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author root
 */
public class NewTrain {
    private final StringProperty addedtrainid;
    private final StringProperty trainname;
    private final StringProperty trainfrom;
    private final StringProperty trainto;
    private final StringProperty traintime;
    private final StringProperty trainprice;
    private final StringProperty columntrainid;

    public NewTrain(String addedtrainid, String trainname, String trainfrom, String trainto, String traintime, String trainprice, String columntrainid) {
        this.addedtrainid = new SimpleStringProperty(addedtrainid);
        this.trainname = new SimpleStringProperty(trainname);
        this.trainfrom = new SimpleStringProperty(trainfrom);
        this.trainto = new SimpleStringProperty(trainto);
        this.traintime = new SimpleStringProperty(traintime);
        this.trainprice = new SimpleStringProperty(trainprice);
        this.columntrainid = new SimpleStringProperty(columntrainid);
    }

    public String getAddedtrainid() {
        return addedtrainid.get();
    }

    public void setAddedtrainid(String value) {
        addedtrainid.set(value);
    }
    
    public String getTrainname() {
        return trainname.get();
    }

    public void setTrainname(String value) {
        trainname.set(value);
    }
    
    public String getTrainfrom() {
        return trainfrom.get();
    }

    public void setTrainfrom(String value) {
        trainfrom.set(value);
    }
    public String getTrainto() {
        return trainto.get();
    }

    public void setTrainto(String value) {
        trainto.set(value);
    }
    public String getTraintime() {
        return traintime.get();
    }

    public void setTraintime(String value) {
        traintime.set(value);
    }
    public String getTrainprice() {
        return trainprice.get();
    }

    public void setTrainprice(String value) {
        trainprice.set(value);
    }
    public String getColumntrainid() {
        return columntrainid.get();
    }

    public void setColumntrainid(String value) {
        columntrainid.set(value);
    }
    
    public StringProperty addedtrainidProperty() {
        return addedtrainid;
    }

    public StringProperty trainnameProperty() {
        return trainname;
    }

    public StringProperty trainfromProperty() {
        return trainfrom;
    }

    public StringProperty traintoProperty() {
        return trainto;
    }

    public StringProperty traintimeProperty() {
        return traintime;
    }

    public StringProperty trainpriceProperty() {
        return trainprice;
    }
    public StringProperty columntrainidProperty() {
        return columntrainid;
    }
    
}
