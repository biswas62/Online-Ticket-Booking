package AdminPanel;

import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import login.DBConnection;

/**
 * FXML Controller class
 *
 * @author root
 */
public class PlanePassengerInfoController implements Initializable {

    @FXML
    private TableView<Plane> tableplanepassenger;
    @FXML
    private TableColumn<Plane, String> planepassname;
    @FXML
    private TableColumn<Plane, String> planepassfrom;
    @FXML
    private TableColumn<Plane, String> planepassto;
    @FXML
    private TableColumn<Plane, String> planepassdate;
    @FXML
    private TableColumn<Plane, String> planepassmobile;
    @FXML
    private TableColumn<Plane, String> planepasspayment;
    @FXML
    private TableColumn<Plane, String> planepassemail;
    @FXML
    private TableColumn<Plane, String> planepassid;
    @FXML
    private TextField searchplane;
    @FXML
    private JFXTextField txt_planeid;
    
    
    private Connection conn = null;
    private PreparedStatement pst = null;
    private ResultSet rs;
    private ObservableList <Plane> data3;
    @FXML
    private Label label;
    @FXML
    private AnchorPane planepane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            conn = DBConnection.bookingDatabaseConnection();
        } catch (SQLException ex) {
            Logger.getLogger(PlanePassengerInfoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        data3 = FXCollections.observableArrayList();
        try {
            rs = conn.createStatement().executeQuery("select * from planeInfo");
            while(rs.next()){
                data3.add(new Plane(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PlanePassengerInfoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        planepassfrom.setCellValueFactory(new PropertyValueFactory<>("planepassfrom"));
        planepassto.setCellValueFactory(new PropertyValueFactory<>("planepassto"));
        planepassname.setCellValueFactory(new PropertyValueFactory<>("planepassname"));
        planepassdate.setCellValueFactory(new PropertyValueFactory<>("planepassdate"));
        planepassmobile.setCellValueFactory(new PropertyValueFactory<>("planepassmobile"));
        planepasspayment.setCellValueFactory(new PropertyValueFactory<>("planepasspayment"));
        planepassemail.setCellValueFactory(new PropertyValueFactory<>("planepassemail"));
        planepassemail.setCellValueFactory(new PropertyValueFactory<>("planepassemail"));
        planepassid.setCellValueFactory(new PropertyValueFactory<>("planepassid"));

        tableplanepassenger.setItems(null);
        tableplanepassenger.setItems(data3);
        count();
        searchinfo();
        setvaluetosearchbox();
    }   
    
    private void refreshtable(){
        data3 = FXCollections.observableArrayList();
        try {
            rs = conn.createStatement().executeQuery("select * from planeInfo");
            while(rs.next()){
                data3.add(new Plane(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PlanePassengerInfoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        planepassfrom.setCellValueFactory(new PropertyValueFactory<>("planepassfrom"));
        planepassto.setCellValueFactory(new PropertyValueFactory<>("planepassto"));
        planepassname.setCellValueFactory(new PropertyValueFactory<>("planepassname"));
        planepassdate.setCellValueFactory(new PropertyValueFactory<>("planepassdate"));
        planepassmobile.setCellValueFactory(new PropertyValueFactory<>("planepassmobile"));
        planepasspayment.setCellValueFactory(new PropertyValueFactory<>("planepasspayment"));
        planepassemail.setCellValueFactory(new PropertyValueFactory<>("planepassemail"));
        planepassemail.setCellValueFactory(new PropertyValueFactory<>("planepassemail"));
        planepassid.setCellValueFactory(new PropertyValueFactory<>("planepassid"));

        tableplanepassenger.setItems(null);
        tableplanepassenger.setItems(data3);

    }
    private void searchinfo() {
        searchplane.setOnKeyReleased(e->{
        if(searchplane.getText().equals("")){
            refreshtable();
        }else{
            data3.clear();
            try {
                pst = conn.prepareStatement("select * from planeInfo where name like '%"+searchplane.getText()+"%'");
                rs = pst.executeQuery();
                while(rs.next()){
                    data3.add(new Plane(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)));
                }
                tableplanepassenger.setItems(data3);
            } catch (SQLException ex) {
                Logger.getLogger(PlanePassengerInfoController.class.getName()).log(Level.SEVERE, null, ex);
            }   
        }        
    });
}
        private void setvaluetosearchbox(){
        tableplanepassenger.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                Plane p = tableplanepassenger.getItems().get(tableplanepassenger.getSelectionModel().getSelectedIndex());
                searchplane.setText(p.getPlanepassname());
                txt_planeid.setText(p.getPlanepassid());
            } 
        });
    }
        private void count(){
            String counter = "";
        try {
            pst = conn.prepareStatement("select count(id) from planeInfo");
            rs = pst.executeQuery();
            if(rs.next()){
                counter = rs.getString(1);
                label.setText(counter);
                rs.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(PlanePassengerInfoController.class.getName()).log(Level.SEVERE, null, ex);
    }
}

    @FXML
    private void deletefromplanedatabase(ActionEvent event) {
        try {
            pst = conn.prepareStatement("delete from planeInfo where id = ?");
            pst.setString(1, txt_planeid.getText());
            pst.executeUpdate();
            pst.close();
            txt_planeid.setText(null);
        } catch (SQLException ex) {
            Logger.getLogger(PlanePassengerInfoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        refreshtable();
        count();
    }

    @FXML
    private void backtoadmin(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Admin.fxml"));
        planepane.getChildren().setAll(pane);
    }
 }
