package AdminPanel;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import login.DBConnection;

/**
 * FXML Controller class
 *
 * @author root
 */
public class AddTrainFormController implements Initializable {

    @FXML
    private JFXTextField txt_from;
    @FXML
    private JFXTextField txt_to;
    @FXML
    private JFXTimePicker txt_time;
    @FXML
    private JFXTextField txt_price;
    @FXML
    private JFXTextField txt_trainname;
    @FXML
    private JFXTextField txt_trainid;
    @FXML
    private AnchorPane calltrainlistpane;

    
    
    private Connection conn = null;
    private PreparedStatement pst = null;
    private ResultSet rs;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            conn = DBConnection.bookingDatabaseConnection();
        } catch (SQLException ex) {
            Logger.getLogger(AddTrainFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void addinformation(ActionEvent event) throws SQLException {
        String trainid = txt_trainid.getText();
        String tname = txt_trainname.getText();
        String tfrom = txt_from.getText();
        String tto = txt_to.getText();
        String ttime = txt_time.getEditor().getText();
        String tprice = txt_price.getText();
        
        if(txt_trainid.getText().trim().isEmpty()||txt_to.getText().trim().isEmpty()||
               txt_time.getValue() == null||txt_from.getText().trim().isEmpty()||
               txt_trainname.getText().trim().isEmpty()||txt_price.getText().trim().isEmpty()){
            
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention");
            alert.setHeaderText(null);
            alert.setContentText("Fill the form to save Information");
            alert.showAndWait();
        } 
        
        else if(txt_from.equals("")||txt_to.equals("")||txt_trainname.equals("")||txt_price.equals("")
           ||txt_time.equals("")||txt_trainid.equals("")){
            
       }else{
            try {
            pst = conn.prepareStatement("insert into addTrain(trainID,name,addtrain_from,addtrain_to,time,price)values(?,?,?,?,?,?)");
            pst.setString(1, trainid);
            pst.setString(2, tname);
            pst.setString(3, tfrom);
            pst.setString(4, tto);
            pst.setString(5, ttime);
            pst.setString(6, tprice);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AddTrainFormController.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            pst.close();
        }
            alert();
            reset();
        } 
    
    }
    
    private void reset(){
    txt_from.setText(null);
    txt_to.setText(null);
    txt_trainname.setText(null);
    txt_trainid.setText(null);
    txt_price.setText(null);   
    }
    
    private void alert(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("New Train has been Added!");
            alert.showAndWait();
    }

    @FXML
    private void callhome(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Admin.fxml"));
        calltrainlistpane.getChildren().setAll(pane);
    }

    @FXML
    private void calltrainlist(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("AddedTrainList.fxml"));
        calltrainlistpane.getChildren().setAll(pane);
    }
    
}
