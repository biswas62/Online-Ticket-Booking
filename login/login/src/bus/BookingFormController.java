package bus;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author root
 */
public class BookingFormController implements Initializable {

    /**
     * Initializes the controller class.
     */
     @FXML
    private JFXTextField Bfrom;

    @FXML
    private JFXTextField Bto;

    @FXML
    private JFXDatePicker Bdate;
    @FXML
    private JFXTextField Bpayment;
    @FXML
    private JFXTextField Bname;
    @FXML
    private JFXTextField Bmobile;
    @FXML
    private JFXTextField Bemail;

    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    
    private boolean validEmail(){
        Pattern p = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)");
        Matcher m = p.matcher(Bemail.getText());
        if(m.find()&&m.group().equals(Bemail.getText())){
            return true;
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid Email");
            alert.setHeaderText(null);
            alert.setContentText("Please Enter a Valid Email Address");
            alert.showAndWait();
            return false;   
        }
    }
    
    private boolean validmob(){
        Pattern p = Pattern.compile("[0][1][5-9][0-9]{8}");
        Matcher m = p.matcher(Bmobile.getText());
        if(m.find()&&m.group().equals(Bmobile.getText())){
            return true;
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid Phone Number");
            alert.setHeaderText(null);
            alert.setContentText("Please Enter a Valid Phone number");
            alert.showAndWait();
            return false;   
        }
    }
    
    private boolean validPay(){
        Pattern p = Pattern.compile("[1-9][0-9]{2,6}");
        Matcher m = p.matcher(Bpayment.getText());
        if(m.find()&&m.group().equals(Bpayment.getText())){
            return true;
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid Payment");
            alert.setHeaderText(null);
            alert.setContentText("Insert above 100 Taka");
            alert.showAndWait();
            return false;   
        }
    }
    
    
    
    @FXML
    private void handleReserve(ActionEvent event) throws SQLException, IOException {
                 Image img5 = new Image("/notification/Multiply_52px.png");
       if(Bfrom.getText().trim().isEmpty()||Bto.getText().trim().isEmpty()||
               Bdate.getValue() == null||Bmobile.getText().trim().isEmpty()||
               Bpayment.getText().trim().isEmpty()||Bname.getText().trim().isEmpty()
               ||Bemail.getText().trim().isEmpty()){
            
            Notifications noti = Notifications.create()
                    .title("Wrong Input")
                    .text("Fill The From")
                    .graphic(new ImageView(img5))
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.TOP_RIGHT);
            noti.darkStyle();
            noti.show();
            
            
        } 
        
        String txt_from = Bfrom.getText();
        String txt_to = Bto.getText();
        String txt_date = ((TextField)Bdate.getEditor()).getText();
        String txt_name = Bname.getText();
        String txt_email = Bemail.getText();
        String txt_pay = Bpayment.getText();
        String txt_mob = Bmobile.getText();
        
        if(txt_from.equals("")||txt_to.equals("")||txt_name.equals("")||txt_pay.equals("")
           ||txt_email.equals("")||txt_mob.equals("")||txt_date.equals("")){
            
       }else if(validEmail()&&validmob()&&validPay()){
           
           FXMLLoader loader = new FXMLLoader();
           loader.setLocation(getClass().getResource("DetailsForm.fxml"));
          Parent root;
           try{
              root = loader.load();
            Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
         ((DetailsFormController)loader.getController()).insertText(Bfrom.getText(),Bto.getText(),
         ((TextField)Bdate.getEditor()).getText(),Bname.getText(),Bemail.getText(),
            Bmobile.getText(),Bpayment.getText());
        stage.show();
           }
           catch (IOException ex) {
            Logger.getLogger(BookingFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       }
    }
}
