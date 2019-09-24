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
public class AddedTrainListController implements Initializable {
    
    @FXML
    private TableView<NewTrain> trainlisttable;
    @FXML
    private TableColumn<NewTrain, String> columntrainid;
    @FXML
    private TableColumn<NewTrain, String> addedtrainid;
    @FXML
    private TableColumn<NewTrain, String> trainname;
    @FXML
    private TableColumn<NewTrain, String> trainfrom;
    @FXML
    private TableColumn<NewTrain, String> trainto;
    @FXML
    private TableColumn<NewTrain, String> traintime;
    @FXML
    private TableColumn<NewTrain, String> trainprice;
    @FXML
    private TextField searchtrainid;
    @FXML
    private JFXTextField txt_newtrainid;
    
    @FXML
    private Label label;
    @FXML
    private AnchorPane callnewtrainformpane;
    
    private Connection conn = null;
    private PreparedStatement pst = null;
    private ResultSet rs;
    private ObservableList <NewTrain> data5;
    
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            conn = DBConnection.bookingDatabaseConnection();
        } catch (SQLException ex) {
            Logger.getLogger(AddedTrainListController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        data5 = FXCollections.observableArrayList();
       try {
            rs = conn.createStatement().executeQuery("select * from addTrain");
            while(rs.next()){
                data5.add(new NewTrain(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddedTrainListController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        addedtrainid.setCellValueFactory(new PropertyValueFactory<>("addedtrainid"));
        trainname.setCellValueFactory(new PropertyValueFactory<>("trainname"));
        trainfrom.setCellValueFactory(new PropertyValueFactory<>("trainfrom"));
        trainto.setCellValueFactory(new PropertyValueFactory<>("trainto"));
        trainprice.setCellValueFactory(new PropertyValueFactory<>("trainprice"));
        traintime.setCellValueFactory(new PropertyValueFactory<>("traintime"));
        columntrainid.setCellValueFactory(new PropertyValueFactory<>("columntrainid"));
        
        trainlisttable.setItems(null);
        trainlisttable.setItems(data5);
        
        count();
        searchinfo();
        setvaluetosearchbox();
        
    }   
    
    private void refreshtable(){
       data5 = FXCollections.observableArrayList();
       try {
            rs = conn.createStatement().executeQuery("select * from addTrain");
            while(rs.next()){
                data5.add(new NewTrain(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddedTrainListController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        addedtrainid.setCellValueFactory(new PropertyValueFactory<>("addedtrainid"));
        trainname.setCellValueFactory(new PropertyValueFactory<>("trainname"));
        trainfrom.setCellValueFactory(new PropertyValueFactory<>("trainfrom"));
        trainto.setCellValueFactory(new PropertyValueFactory<>("trainto"));
        trainprice.setCellValueFactory(new PropertyValueFactory<>("trainprice"));
        traintime.setCellValueFactory(new PropertyValueFactory<>("traintime"));
        columntrainid.setCellValueFactory(new PropertyValueFactory<>("columntrainid"));
        
        trainlisttable.setItems(null);
        trainlisttable.setItems(data5);
        
    }
    
    private void searchinfo() {
        searchtrainid.setOnKeyReleased(e->{
        if(searchtrainid.getText().equals("")){
            refreshtable();
        }else{
            data5.clear();
            try {
                pst = conn.prepareStatement("select * from addTrain where trainID like '%"+searchtrainid.getText()+"%'");
                rs = pst.executeQuery();
                while(rs.next()){
                    data5.add(new NewTrain(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7)));
                }
                trainlisttable.setItems(data5);
            } catch (SQLException ex) {
                Logger.getLogger(AddedTrainListController.class.getName()).log(Level.SEVERE, null, ex);
            }   
        }        
    });
}
        private void setvaluetosearchbox(){
        trainlisttable.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                NewTrain nt = trainlisttable.getItems().get(trainlisttable.getSelectionModel().getSelectedIndex());
                searchtrainid.setText(nt.getAddedtrainid());
                txt_newtrainid.setText(nt.getColumntrainid());
            } 
        });
    }
        private void count(){
            String counter = "";
            try {
            pst = conn.prepareStatement("select count(id) from addTrain");
            rs = pst.executeQuery();
            if(rs.next()){
                counter = rs.getString(1);
                label.setText(counter);
                rs.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddedTrainListController.class.getName()).log(Level.SEVERE, null, ex);
    }
        
}
        private void resetsearchid(){
            searchtrainid.setText(null);
        }

    @FXML
    private void deletetrain(ActionEvent event) {
         if(searchtrainid.getText().trim().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention");
            alert.setHeaderText(null);
            alert.setContentText("Select a Row to Delete!");
            alert.showAndWait();
        }else{
            try {
            pst = conn.prepareStatement("delete from addTrain where id = ?");
            pst.setString(1, txt_newtrainid.getText());
            pst.executeUpdate();
            pst.close();
            txt_newtrainid.setText(null);
        }catch (SQLException ex) {
            Logger.getLogger(AddedTrainListController.class.getName()).log(Level.SEVERE, null, ex);
    }
}
       refreshtable();
       count();
       resetsearchid();
    }

    @FXML
    private void addnewtrain(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("AddTrainForm.fxml"));
        callnewtrainformpane.getChildren().setAll(pane);
    }

    @FXML
    private void callhomefromtrainlist(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Admin.fxml"));
        callnewtrainformpane.getChildren().setAll(pane);
    }
    
}
