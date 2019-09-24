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
public class AddPlaneFormController implements Initializable {

    @FXML
    private AnchorPane callplanelistpane;
    @FXML
    private JFXTextField txt_from;
    @FXML
    private JFXTextField txt_to;
    @FXML
    private JFXTextField txt_planename;
    @FXML
    private JFXTimePicker txt_time;
    @FXML
    private JFXTextField txt_planeid;
    @FXML
    private JFXTextField txt_price;
    
    
    private Connection conn = null;
    private PreparedStatement pst = null;
    private ResultSet rs;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            conn = DBConnection.bookingDatabaseConnection();
        } catch (SQLException ex) {
            Logger.getLogger(AddPlaneFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void addinformation(ActionEvent event) throws SQLException {
        String planeid = txt_planeid.getText();
        String pname = txt_planename.getText();
        String pfrom = txt_from.getText();
        String pto = txt_to.getText();
        String ptime = txt_time.getEditor().getText();
        String pprice = txt_price.getText();
        
        if(txt_planeid.getText().trim().isEmpty()||txt_to.getText().trim().isEmpty()||
               txt_time.getValue() == null||txt_from.getText().trim().isEmpty()||
               txt_planename.getText().trim().isEmpty()||txt_price.getText().trim().isEmpty()){
            
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention");
            alert.setHeaderText(null);
            alert.setContentText("Fill the form to save Information");
            alert.showAndWait();
        } 
        
        else if(txt_from.equals("")||txt_to.equals("")||txt_planename.equals("")||txt_price.equals("")
           ||txt_time.equals("")||txt_planeid.equals("")){
            
       }else{
            try {
            pst = conn.prepareStatement("insert into addPlane(planeID,name,addplane_from,addplane_to,time,price)values(?,?,?,?,?,?)");
            pst.setString(1, planeid);
            pst.setString(2, pname);
            pst.setString(3, pfrom);
            pst.setString(4, pto);
            pst.setString(5, ptime);
            pst.setString(6, pprice);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AddPlaneFormController.class.getName()).log(Level.SEVERE, null, ex);
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
    txt_planename.setText(null);
    txt_planeid.setText(null);
    txt_price.setText(null);   
    }
    
    private void alert(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("New Plane has been Added!");
            alert.showAndWait();
    }

    @FXML
    private void callhome(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Admin.fxml"));
        callplanelistpane.getChildren().setAll(pane);
    }

    @FXML
    private void callplanelist(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("AddedPlaneList.fxml"));
        callplanelistpane.getChildren().setAll(pane);
    }
    
}
