/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package coffeeshop;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author user
 */
public class FXMLDocumentController implements Initializable {
    
      @FXML
    private Button SignUp;

    @FXML
    private Button close;

    @FXML
    private AnchorPane main_form;

    @FXML
    private TextField nama;

    @FXML
    private PasswordField password;
    
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    
    private double x = 0;
    private double y = 0;

    
    public void SignUp(){
        String sql = "SELECT  * FROM admin WHERE username = ? and password = ?";
        connect = DataBase.connectDb();
        
        try{
        prepare = connect.prepareStatement(sql);
                prepare.setString(1, nama.getText() );
                prepare.setString(2, password.getText() );
                
                result = prepare.executeQuery();
                Alert alert;
                
                if(nama.getText().isEmpty() || password.getText().isEmpty()){
                alert = new Alert (AlertType.ERROR);
                alert.setTitle("Peringatan");
                alert.setHeaderText(null);
                alert.setContentText("isi data");
                alert.showAndWait();
                }else{
                    if(result.next()){
                        //kalo bener ngisinya, pindah ke dashboard
                        SignUp.getScene().getWindow().hide();
                        alert = new Alert (AlertType.INFORMATION);
                alert.setTitle("INFORMATION");
                alert.setHeaderText(null);
                alert.setContentText("successfully signup");
                alert.showAndWait();
                
                        //link ke dashboard
                        Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
                  
                        Stage stage = new Stage();
                        Scene scene = new Scene(root);
                        
                        stage.setTitle("Arthur Coffee Management");
                        stage.setMinWidth(1100);
                        stage.setMinHeight(600);
                        
                        root.setOnMousePressed((MouseEvent event)->{
                            x = event.getSceneX();
                            y = event.getSceneY();
                        });
                        
                        root.setOnMouseDragged((MouseEvent event)->{
                            stage.setX(event.getScreenX() - x);
                            stage.setY(event.getScreenY() - y);

                            
                        });
                        
                        stage.initStyle(StageStyle.TRANSPARENT);
                        
                        stage.setScene(scene);
                        stage.show();
                        
                    }else{
                    //kalo salah muncul peringatan eror
                    alert = new Alert (AlertType.ERROR);
                alert.setTitle("Peringatan");
                alert.setHeaderText(null);
                alert.setContentText("username/password salah");
                alert.showAndWait();
                    
                    }
                }
                
        }catch(Exception e) {e.printStackTrace();}
           
    }
    
    public void close(){
        System.exit(0);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
