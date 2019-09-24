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
public class AddBusFormController implements Initializable {

    @FXML
    private JFXTextField txt_from;
    @FXML
    private JFXTextField txt_to;
    @FXML
    private JFXTextField txt_busname;
    @FXML
    private JFXTimePicker txt_time;
    @FXML
    private JFXTextField txt_busid;
    @FXML
    private JFXTextField txt_price;
    
    private Connection conn = null;
    private PreparedStatement pst = null;
    private ResultSet rs;
    @FXML
    private AnchorPane callbuslistpane;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            conn = DBConnection.bookingDatabaseConnection();
        } catch (SQLException ex) {
            Logger.getLogger(AddBusFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void addinformation(ActionEvent event) throws SQLException {
        String busid = txt_busid.getText();
        String bname = txt_busname.getText();
        String bfrom = txt_from.getText();
        String bto = txt_to.getText();
        String btime = txt_time.getEditor().getText();
        String bprice = txt_price.getText();
        
        if(txt_busid.getText().trim().isEmpty()||txt_to.getText().trim().isEmpty()||
               txt_time.getValue() == null||txt_from.getText().trim().isEmpty()||
               txt_busname.getText().trim().isEmpty()||txt_price.getText().trim().isEmpty()){
            
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention");
            alert.setHeaderText(null);
            alert.setContentText("Fill the form to save Information");
            alert.showAndWait();
        } 
        
        else if(txt_from.equals("")||txt_to.equals("")||txt_busname.equals("")||txt_price.equals("")
           ||txt_time.equals("")||txt_busid.equals("")){
            
       }else{
            try {
            pst = conn.prepareStatement("insert into addBus(busID,name,addbus_from,addbus_to,time,price)values(?,?,?,?,?,?)");
            pst.setString(1, busid);
            pst.setString(2, bname);
            pst.setString(3, bfrom);
            pst.setString(4, bto);
            pst.setString(5, btime);
            pst.setString(6, bprice);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AddBusFormController.class.getName()).log(Level.SEVERE, null, ex);
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
    txt_busname.setText(null);
    txt_busid.setText(null);
    txt_price.setText(null);   
    }
    
    private void alert(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("New Bus has been Added!");
            alert.showAndWait();
    }

    @FXML
    private void callbuslist(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("AddedBusList.fxml"));
        callbuslistpane.getChildren().setAll(pane);
    }

    @FXML
    private void callhome(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Admin.fxml"));
        callbuslistpane.getChildren().setAll(pane);
    }
    
}
