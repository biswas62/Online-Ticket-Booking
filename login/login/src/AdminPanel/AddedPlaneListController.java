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
import javafx.scene.control.Alert;
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
public class AddedPlaneListController implements Initializable {
    
    @FXML
    private TableView<NewPlane> planelisttable;
    @FXML
    private TableColumn<NewPlane, String> columnplaneid;
    @FXML
    private TableColumn<NewPlane, String> addedplaneid;
    @FXML
    private TableColumn<NewPlane, String> planename;
    @FXML
    private TableColumn<NewPlane, String> planefrom;
    @FXML
    private TableColumn<NewPlane, String> planeto;
    @FXML
    private TableColumn<NewPlane, String> planetime;
    @FXML
    private TableColumn<NewPlane, String> planeprice;
    @FXML
    private TextField searchplaneid;
    @FXML
    private JFXTextField txt_newplaneid;
    
    @FXML
    private Label label;
    @FXML
    private AnchorPane callnewplaneformpane;
    
    private Connection conn = null;
    private PreparedStatement pst = null;
    private ResultSet rs;
    private ObservableList <NewPlane> data6;
    
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            conn = DBConnection.bookingDatabaseConnection();
        } catch (SQLException ex) {
            Logger.getLogger(AddedPlaneListController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        data6 = FXCollections.observableArrayList();
       try {
            rs = conn.createStatement().executeQuery("select * from addPlane");
            while(rs.next()){
                data6.add(new NewPlane(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddedPlaneListController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        addedplaneid.setCellValueFactory(new PropertyValueFactory<>("addedplaneid"));
        planename.setCellValueFactory(new PropertyValueFactory<>("planename"));
        planefrom.setCellValueFactory(new PropertyValueFactory<>("planefrom"));
        planeto.setCellValueFactory(new PropertyValueFactory<>("planeto"));
        planeprice.setCellValueFactory(new PropertyValueFactory<>("planeprice"));
        planetime.setCellValueFactory(new PropertyValueFactory<>("planetime"));
        columnplaneid.setCellValueFactory(new PropertyValueFactory<>("columnplaneid"));
        
        planelisttable.setItems(null);
        planelisttable.setItems(data6);
        
        count();
        searchinfo();
        setvaluetosearchbox();
        
    }   
    
    private void refreshtable(){
       data6 = FXCollections.observableArrayList();
       try {
            rs = conn.createStatement().executeQuery("select * from addPlane");
            while(rs.next()){
                data6.add(new NewPlane(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddedPlaneListController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        addedplaneid.setCellValueFactory(new PropertyValueFactory<>("addedplaneid"));
        planename.setCellValueFactory(new PropertyValueFactory<>("planename"));
        planefrom.setCellValueFactory(new PropertyValueFactory<>("planefrom"));
        planeto.setCellValueFactory(new PropertyValueFactory<>("planeto"));
        planeprice.setCellValueFactory(new PropertyValueFactory<>("planeprice"));
        planetime.setCellValueFactory(new PropertyValueFactory<>("planetime"));
        columnplaneid.setCellValueFactory(new PropertyValueFactory<>("columnplaneid"));
        
        planelisttable.setItems(null);
        planelisttable.setItems(data6);
        
    }
    
    private void searchinfo() {
        searchplaneid.setOnKeyReleased(e->{
        if(searchplaneid.getText().equals("")){
            refreshtable();
        }else{
            data6.clear();
            try {
                pst = conn.prepareStatement("select * from addPlane where planeID like '%"+searchplaneid.getText()+"%'");
                rs = pst.executeQuery();
                while(rs.next()){
                    data6.add(new NewPlane(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7)));
                }
                planelisttable.setItems(data6);
            } catch (SQLException ex) {
                Logger.getLogger(AddedPlaneListController.class.getName()).log(Level.SEVERE, null, ex);
            }   
        }        
    });
}
        private void setvaluetosearchbox(){
        planelisttable.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                NewPlane np = planelisttable.getItems().get(planelisttable.getSelectionModel().getSelectedIndex());
                searchplaneid.setText(np.getAddedplaneid());
                txt_newplaneid.setText(np.getColumnplaneid());
            } 
        });
    }
        private void count(){
            String counter = "";
            try {
            pst = conn.prepareStatement("select count(id) from addPlane");
            rs = pst.executeQuery();
            if(rs.next()){
                counter = rs.getString(1);
                label.setText(counter);
                rs.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddedPlaneListController.class.getName()).log(Level.SEVERE, null, ex);
    }
        
}
        private void resetsearchid(){
            searchplaneid.setText(null);
        }

    @FXML
    private void deleteplane(ActionEvent event) {
        if(searchplaneid.getText().trim().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention");
            alert.setHeaderText(null);
            alert.setContentText("Select a Row to Delete!");
            alert.showAndWait();
        }else{
            try {
            pst = conn.prepareStatement("delete from addPlane where id = ?");
            pst.setString(1, txt_newplaneid.getText());
            pst.executeUpdate();
            pst.close();
            txt_newplaneid.setText(null);
        } catch (SQLException ex) {
            Logger.getLogger(AddedPlaneListController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
       refreshtable();
       count();
       resetsearchid();
    }

    @FXML
    private void addnewplane(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("AddPlaneForm.fxml"));
        callnewplaneformpane.getChildren().setAll(pane);
    }

    @FXML
    private void callhomefromplanelist(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Admin.fxml"));
        callnewplaneformpane.getChildren().setAll(pane);
    }
    
}
