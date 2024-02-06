/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coffeeshop;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author user
 */
public class cardProdController implements Initializable {
    
    @FXML
    private AnchorPane card_form;

    @FXML
    private Button cardprod_addbtn;

    @FXML
    private ImageView cardprod_image;

    @FXML
    private Label cardprod_name;

    @FXML
    private Label cardprod_price;

    @FXML
    private Spinner<Integer> cardprod_spinner;
    
    private inventoryData prodData;
    private Image image;
    
    private String prodID;
    
    private SpinnerValueFactory<Integer> spin;
    
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    
    private Alert alert;
    
    public void setData (inventoryData prodData){
        this.prodData = prodData;
       
        cardprod_name.setText(prodData.getMenuName());
        cardprod_price.setText("Rp" + String.valueOf(prodData.getPrice()));
        String path = "File:" + prodData.getImage();
        image = new Image(path,200,92,false,true);
        cardprod_image.setImage(image);
    
}
    private int qty;
    public void setQuantity(){
        spin = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);
        cardprod_spinner.setValueFactory(spin);
    }
    
    private void addbtn(){
        qty = cardprod_spinner.getValue();
        String check = "";
        String checkAvailable = "SELECT status FROM inventory WHERE cardprod_name = '"
                + cardprod_name + "'";
        
        connect = DataBase.connectDb();
        
        try{
            prepare = connect.prepareStatement(checkAvailable);
            result = prepare.executeQuery(); 
            
            if(result.next()){
                check = result.getString("status");
            
        }
            if(!check.equals ("Available") || qty == 0){
                 alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Massage");
            alert.setHeaderText(null);
            alert.setContentText("Something Wrong :3");
            
            }else{
                String insertData = "INSERT INTO";
            }
            
        }catch(Exception e) {
            e.printStackTrace();
        
        if (qty == 0 ) {
            
        }
    }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
