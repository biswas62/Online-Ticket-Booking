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
public class AddedBusListController implements Initializable {

    @FXML
    private TableView<NewBus> buslisttable;
    @FXML
    private TableColumn<NewBus, String> addedbusid;
    @FXML
    private TableColumn<NewBus, String> busname;
    @FXML
    private TableColumn<NewBus, String> busfrom;
    @FXML
    private TableColumn<NewBus, String> busto;
    @FXML
    private TableColumn<NewBus, String> bustime;
    @FXML
    private TableColumn<NewBus, String> busprice;
    @FXML
    private TableColumn<NewBus, String> columnid;
    @FXML
    private TextField searchbusid;
    
    
    private Connection conn = null;
    private PreparedStatement pst = null;
    private ResultSet rs;
    private ObservableList <NewBus> data4;
    @FXML
    private JFXTextField txt_newbusid;
    @FXML
    private Label label;
    @FXML
    private AnchorPane callnewbusformpane;
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            conn = DBConnection.bookingDatabaseConnection();
        } catch (SQLException ex) {
            Logger.getLogger(AddedBusListController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        data4 = FXCollections.observableArrayList();
       try {
            rs = conn.createStatement().executeQuery("select * from addBus");
            while(rs.next()){
                data4.add(new NewBus(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddedBusListController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        addedbusid.setCellValueFactory(new PropertyValueFactory<>("addedbusid"));
        busname.setCellValueFactory(new PropertyValueFactory<>("busname"));
        busfrom.setCellValueFactory(new PropertyValueFactory<>("busfrom"));
        busto.setCellValueFactory(new PropertyValueFactory<>("busto"));
        busprice.setCellValueFactory(new PropertyValueFactory<>("busprice"));
        bustime.setCellValueFactory(new PropertyValueFactory<>("bustime"));
        columnid.setCellValueFactory(new PropertyValueFactory<>("columnid"));
        
        buslisttable.setItems(null);
        buslisttable.setItems(data4);
        
        count();
        searchinfo();
        setvaluetosearchbox();
        
    }   
    
    private void refreshtable(){
       data4 = FXCollections.observableArrayList();
       try {
            rs = conn.createStatement().executeQuery("select * from addBus");
            while(rs.next()){
                data4.add(new NewBus(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddedBusListController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        addedbusid.setCellValueFactory(new PropertyValueFactory<>("addedbusid"));
        busname.setCellValueFactory(new PropertyValueFactory<>("busname"));
        busfrom.setCellValueFactory(new PropertyValueFactory<>("busfrom"));
        busto.setCellValueFactory(new PropertyValueFactory<>("busto"));
        busprice.setCellValueFactory(new PropertyValueFactory<>("busprice"));
        bustime.setCellValueFactory(new PropertyValueFactory<>("bustime"));
        columnid.setCellValueFactory(new PropertyValueFactory<>("columnid"));
        
        buslisttable.setItems(null);
        buslisttable.setItems(data4);
    }
    
    private void searchinfo() {
        searchbusid.setOnKeyReleased(e->{
        if(searchbusid.getText().equals("")){
            refreshtable();
        }else{
            data4.clear();
            try {
                pst = conn.prepareStatement("select * from addBus where busID like '%"+searchbusid.getText()+"%'");
                rs = pst.executeQuery();
                while(rs.next()){
                    data4.add(new NewBus(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7)));
                }
                buslisttable.setItems(data4);
            } catch (SQLException ex) {
                Logger.getLogger(AddedBusListController.class.getName()).log(Level.SEVERE, null, ex);
            }   
        }        
    });
}
        private void setvaluetosearchbox(){
        buslisttable.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                NewBus nb = buslisttable.getItems().get(buslisttable.getSelectionModel().getSelectedIndex());
                searchbusid.setText(nb.getAddedbusid());
                txt_newbusid.setText(nb.getColumnid());
            } 
        });
    }
        private void count(){
            String counter = "";
            try {
            pst = conn.prepareStatement("select count(id) from addBus");
            rs = pst.executeQuery();
            if(rs.next()){
                counter = rs.getString(1);
                label.setText(counter);
                rs.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddedBusListController.class.getName()).log(Level.SEVERE, null, ex);
    }
        
}
        private void resetsearchid(){
            searchbusid.setText(null);
        }

    @FXML
    private void deletebus(ActionEvent event) {
        if(searchbusid.getText().trim().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention");
            alert.setHeaderText(null);
            alert.setContentText("Select a Row to Delete!");
            alert.showAndWait();
        }else{
            try {
            pst = conn.prepareStatement("delete from addBus where id = ?");
            pst.setString(1, txt_newbusid.getText());
            pst.executeUpdate();
            pst.close();
            txt_newbusid.setText(null);
        } catch (SQLException ex) {
            Logger.getLogger(AddedBusListController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
       refreshtable();
       count();
       resetsearchid();
    }

    @FXML
    private void addnewbus(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("AddBusForm.fxml"));
        callnewbusformpane.getChildren().setAll(pane);
    }

    @FXML
    private void callhomefrombuslist(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Admin.fxml"));
        callnewbusformpane.getChildren().setAll(pane);
    }
    
}
