/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coffeeshop;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author user
 */
public class DasshboardController implements Initializable {

    @FXML
    private AnchorPane Main_form;

    @FXML
    private AnchorPane ABOUTUS_FORM;

    @FXML
    private Button AboutUs_btn;

    @FXML
    private Button Close_button;

    @FXML
    private AnchorPane DASHBOARD_FORM;

    @FXML
    private AreaChart<?, ?> DASHBOARD_custchart;

    @FXML
    private AreaChart<?, ?> DASHBOARD_incomechart;

    @FXML
    private Label DASHBOARD_numberofcust;

    @FXML
    private Label DASHBOARD_numberofsold;

    @FXML
    private Label DASHBOARD_todayincome;

    @FXML
    private Label DASHBOARD_totalincome;

    @FXML
    private Button Dashboard_btn;

    @FXML
    private TableColumn<inventoryData, String> INVNTRY_CO_date;

    @FXML
    private TableColumn<inventoryData, String> INVNTRY_CO_id;

    @FXML
    private TableColumn<inventoryData, String> INVNTRY_CO_menu;

    @FXML
    private TableColumn<inventoryData, String> INVNTRY_CO_price;

    @FXML
    private TableColumn<inventoryData, String> INVNTRY_CO_status;

    @FXML
    private TableColumn<inventoryData, String> INVNTRY_CO_stock;

    @FXML
    private TableColumn<inventoryData, String> INVNTRY_CO_type;

    @FXML
    private AnchorPane INVNTRY_FORM;

    @FXML
    private Button INVNTRY_addbtn;

    @FXML
    private Button INVNTRY_clearbtn;

    @FXML
    private Button INVNTRY_deletebtn;

    @FXML
    private ImageView INVNTRY_imageview;

    @FXML
    private Button INVNTRY_importbtn;

    @FXML
    private TextField INVNTRY_menu;

    @FXML
    private TextField INVNTRY_menuid;

    @FXML
    private TextField INVNTRY_price;

    @FXML
    private ComboBox<?> INVNTRY_status;

    @FXML
    private TextField INVNTRY_stock;

    @FXML
    private TableView<inventoryData> INVNTRY_tableview;

    @FXML
    private ComboBox<?> INVNTRY_type;

    @FXML
    private Button INVNTRY_updatebtn;

    @FXML
    private Button Inventory_btn;

    @FXML
    private Button MenunCart_btn;

    @FXML
    private Button Minimize_button;

    @FXML
    private TableColumn<?, ?> MnC_CO_date;

    @FXML
    private TableColumn<?, ?> MnC_CO_id;

    @FXML
    private TableColumn<?, ?> MnC_CO_menu;

    @FXML
    private TableColumn<?, ?> MnC_CO_price;

    @FXML
    private TableColumn<?, ?> MnC_CO_status;

    @FXML
    private TableColumn<?, ?> MnC_CO_stock;

    @FXML
    private TableColumn<?, ?> MnC_CO_type;

    @FXML
    private Button MnC_addbtn;

    @FXML
    private Button MnC_clearbtn;

    @FXML
    private Button MnC_deletebtn;

    @FXML
    private TextField MnC_search;

    @FXML
    private TableView<?> MnC_tableview;

    @FXML
    private Button MnC_updatebtn;

    @FXML
    private AnchorPane Mnc_form;

    @FXML
    private Button SignOut_btn;

    @FXML
    private Button Maximize_button;

    @FXML
    private Text CART_AMOUNT;

    @FXML
    private Label CART_CHANGE;

    @FXML
    private AnchorPane CART_FORM;

    @FXML
    private TableColumn<?, ?> CART_MENU_COL;

    @FXML
    private Button CART_PAYBTN;

    @FXML
    private TableColumn<?, ?> CART_PRICE_COL;

    @FXML
    private TableColumn<?, ?> CART_QTY_COL;

    @FXML
    private Button CART_RECEIPTBTN;

    @FXML
    private Button CART_REMOVEBTN;

    @FXML
    private Label CART_TOTAL;

    @FXML
    private GridPane CART_gridpane;

    @FXML
    private ScrollPane CART_scrollpane;

    @FXML
    private TableView<?> CART_tableview;

    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;
    Alert alert;
    private Image image;

    private ObservableList<inventoryData> cardListData = FXCollections.observableArrayList();

    public void inventoryaddbtn() {
        if (INVNTRY_menuid.getText().isEmpty()
                //menu_name ga si harusnya? tar kalo ada eror ksini y
                || INVNTRY_menu.getText().isEmpty()
                || INVNTRY_type.getSelectionModel().getSelectedItem() == null
                || INVNTRY_stock.getText().isEmpty()
                || INVNTRY_price.getText().isEmpty()
                || INVNTRY_status.getSelectionModel().getSelectedItem() == null
                || data.path == null) {

            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Massage");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();

        } else {

            String checkmenuid = "SELECT menu_id FROM inventory WHERE menu_id='" + INVNTRY_menuid.getText() + "'";

            connect = DataBase.connectDb();

            try {

                statement = connect.createStatement();
                result = statement.executeQuery(checkmenuid);

                if (result.next()) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Massage");
                    alert.setHeaderText(null);
                    alert.setContentText(INVNTRY_menuid.getText() + " is already taken");
                    alert.showAndWait();
                } else {
                    String insertData = "INSERT INTO inventory " + "(menu_id, menu_name, type, stock, price, status, image, date) "
                            + "VALUES(?,?,?,?,?,?,?,CURRENT_DATE)";

                    prepare = connect.prepareStatement(insertData);
                    prepare.setString(1, INVNTRY_menuid.getText());
                    prepare.setString(2, INVNTRY_menu.getText());
                    prepare.setString(3, (String) INVNTRY_type.getSelectionModel().getSelectedItem());
                    prepare.setInt(4, Integer.parseInt(INVNTRY_stock.getText()));
                    prepare.setDouble(5, Double.parseDouble(INVNTRY_price.getText()));
                    prepare.setString(6, (String) INVNTRY_status.getSelectionModel().getSelectedItem());

                    String path = data.path;
                    path = path.replace("\\", "\\\\");

                    prepare.setString(7, data.path);

                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                   

                    prepare.executeUpdate();

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Error Massage");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully added!");
                    alert.showAndWait();

                    inventoryShowData();
                    inventoryclearbtn();
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("SQL Error: " + e.getMessage());
            }
        }
    }

    public void inventoryupdatebtn() {

        if (INVNTRY_menuid.getText().isEmpty()
                //menu_name ga si harusnya? tar kalo ada eror ksini y
                || INVNTRY_menu.getText().isEmpty()
                || INVNTRY_type.getSelectionModel().getSelectedItem() == null
                || INVNTRY_stock.getText().isEmpty()
                || INVNTRY_price.getText().isEmpty()
                || INVNTRY_status.getSelectionModel().getSelectedItem() == null
                || data.path == null || data.id == 0) {

            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Massage");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();

        } else {

            String path = data.path;
            path = path.replace("\\", "\\\\");

            String updateData = "UPDATE inventory SET " + "menu_id = '" + INVNTRY_menuid.getText() + "', menu_name = '"
                    + INVNTRY_menu.getText() + "', type = '"
                    + INVNTRY_type.getSelectionModel().getSelectedItem() + "', stock = '"
                    + INVNTRY_stock.getText() + "', price = '"
                    + INVNTRY_price.getText() + "', status = '"
                    + INVNTRY_status.getSelectionModel().getSelectedItem() + "', image = '"
                    + path + "', date = '"
                    + data.date + "' WHERE id = " + data.id;

            connect = DataBase.connectDb();

            try {

                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to UPDATE menu id : " + INVNTRY_menuid.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();
                if (option.get().equals(ButtonType.OK)) {
                    prepare = connect.prepareStatement(updateData);
                    prepare.executeUpdate();

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Error Massage");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated!");
                    alert.showAndWait();

                    inventoryShowData();
                    inventoryclearbtn();

                } else {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Massage");
                    alert.setHeaderText(null);
                    alert.setContentText("Cancelled");
                    alert.showAndWait();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public void inventorydeletebtn() {

        if (data.id == 0) {

            alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to DELETE menu id : " + INVNTRY_menuid.getText() + "?");
            Optional<ButtonType> option = alert.showAndWait();
            if (option.get().equals(ButtonType.OK)) {

                String deleteData = "DELETE FROM inventory WHERE id = " + data.id;
                try {
                    prepare = connect.prepareStatement(deleteData);
                    prepare.executeUpdate();

                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Massage");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Deleted");
                    alert.showAndWait();

                    inventoryShowData();
                    inventoryclearbtn();

                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else {

                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Massage");
                alert.setHeaderText(null);
                alert.setContentText("Cancelled");
                alert.showAndWait();

            }
        }
    }

    public void inventoryclearbtn() {
        INVNTRY_menuid.setText("");
        INVNTRY_menu.setText("");
        INVNTRY_type.getSelectionModel().clearSelection();
        INVNTRY_stock.setText("");
        INVNTRY_price.setText("");
        INVNTRY_status.getSelectionModel().clearSelection();
        data.path = "";
        data.id = 0;
        INVNTRY_imageview.setImage(null);
    }

    public void inventoryimportbtn() {

        FileChooser openFile = new FileChooser();
        openFile.getExtensionFilters().add(new ExtensionFilter("Open Image File", "*png", "*jpg"));

        File file = openFile.showOpenDialog(Main_form.getScene().getWindow());

        if (file != null) {

            data.path = file.getAbsolutePath();
            image = new Image(file.toURI().toString(), 139, 163, false, true);

            INVNTRY_imageview.setImage(image);

        }

    }

    public ObservableList<inventoryData> inventoryDataList() {

        ObservableList<inventoryData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM inventory";

        connect = DataBase.connectDb();

        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            inventoryData invData;

            while (result.next()) {

                invData = new inventoryData(result.getInt("id"),
                        result.getString("menu_id"),
                        result.getString("menu_name"),
                        result.getString("type"),
                        result.getInt("stock"),
                        result.getDouble("price"),
                        result.getString("status"),
                        result.getString("image"),
                        result.getDate("date"));

                listData.add(invData);
                
                System.out.println("Menu ID: " + invData.getMenuId() + ", Menu Name: " + invData.getMenuName());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listData;
    }

    public ObservableList<inventoryData> inventoryListData;

    public void inventoryShowData() {
        connect = DataBase.connectDb();
        inventoryListData = inventoryDataList();

       INVNTRY_CO_id.setCellValueFactory(new PropertyValueFactory<>("menu_id"));
    INVNTRY_CO_menu.setCellValueFactory(new PropertyValueFactory<>("menu_name"));
    INVNTRY_CO_type.setCellValueFactory(new PropertyValueFactory<>("type"));
    INVNTRY_CO_stock.setCellValueFactory(new PropertyValueFactory<>("stock"));
    INVNTRY_CO_price.setCellValueFactory(new PropertyValueFactory<>("price"));
    INVNTRY_CO_status.setCellValueFactory(new PropertyValueFactory<>("status"));
    INVNTRY_CO_date.setCellValueFactory(new PropertyValueFactory<>("date"));

        INVNTRY_tableview.setItems(inventoryListData);
        

    }
    
    

    public void inventorySelectData() {

        inventoryData invData = INVNTRY_tableview.getSelectionModel().getSelectedItem();
        int num = INVNTRY_tableview.getSelectionModel().getSelectedIndex();

        if ((num - 1) < - 1) {
            return;
        }

        INVNTRY_menuid.setText(invData.getMenuId());
        INVNTRY_menu.setText(invData.getMenuName());
        INVNTRY_stock.setText(String.valueOf(invData.getStock()));
        INVNTRY_price.setText(String.valueOf(invData.getPrice()));

        data.path = invData.getImage();

        String path = "File:" + invData.getImage();
        data.date = String.valueOf(invData.getDate());
        data.id = invData.getId();

        image = new Image(path, 139, 163, false, true);
        INVNTRY_imageview.setImage(image);
        
       
    }

    public void switchForm(ActionEvent event) {

        if (event.getSource() == Dashboard_btn) {

            DASHBOARD_FORM.setVisible(true);
            CART_FORM.setVisible(false);
            INVNTRY_FORM.setVisible(false);
            ABOUTUS_FORM.setVisible(false);

        } else if (event.getSource() == Inventory_btn) {

            DASHBOARD_FORM.setVisible(false);
            CART_FORM.setVisible(false);
            INVNTRY_FORM.setVisible(true);
            ABOUTUS_FORM.setVisible(false);
            
            inventoryShowData();

        } else if (event.getSource() == MenunCart_btn) {

            DASHBOARD_FORM.setVisible(false);
            CART_FORM.setVisible(true);
            INVNTRY_FORM.setVisible(false);
            ABOUTUS_FORM.setVisible(false);

            
            

        } else if (event.getSource() == AboutUs_btn) {

            DASHBOARD_FORM.setVisible(false);
            CART_FORM.setVisible(false);
            INVNTRY_FORM.setVisible(false);
            ABOUTUS_FORM.setVisible(true);

            menuDisplayCard();

        }
    }

    private String[] typeList = {"Foods", "Beverages"};

    public void inventoryTypeList() {

        List<String> typeL = new ArrayList<>();

        for (String data : typeList) {
            typeL.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(typeL);
        INVNTRY_type.setItems(listData);
    }

    private String[] statusList = {"Available", "Unavailable"};

    public void inventoryStatusList() {

        List<String> statusL = new ArrayList<>();

        for (String data : statusList) {
            statusL.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(statusL);
        INVNTRY_status.setItems(listData);
    }

    public ObservableList<inventoryData> menuGetData() {

        String sql = "SELECT * FROM inventory";

        ObservableList<inventoryData> listData = FXCollections.observableArrayList();
        connect = DataBase.connectDb();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            inventoryData prod;

            while (result.next()) {
                prod = new inventoryData(result.getInt("id"), result.getString("menu_id"), result.getString("menu_name"),
                        result.getDouble("price"), result.getString("image"));

                listData.add(prod);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listData;

    }

    public void menuDisplayCard() {
        cardListData.clear();
        cardListData.addAll(menuGetData());

        int row = 0;
        int column = 0;

        CART_gridpane.getChildren().clear();
        CART_gridpane.getRowConstraints().clear();
        CART_gridpane.getColumnConstraints().clear();

        for (int q = 0; q < cardListData.size(); q++) {

            try {
                FXMLLoader load = new FXMLLoader();
                load.getClass().getResource("cardProd.fxml");
                //alternatif kalo ga kepanggil:
                //load.setLocation(getClass().getResource("cardProd.fxml"));
                AnchorPane pane = load.load();
                cardProdController cardC = load.getController();
                cardC.setData(cardListData.get(q));

                if (column == 3) {
                    column = 0;
                    row += 1;
                }

                CART_gridpane.add(pane, column++, row);
                GridPane.setMargin(pane, new Insets(10));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private double x = 0;
    private double y = 0;

    public void SignOut() {

        try {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Massage");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to SignOut?");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {

                SignOut_btn.getScene().getWindow().hide();

                Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
                Scene scene = new Scene(root);
                Stage stage = new Stage();

                root.setOnMousePressed((MouseEvent event) -> {
                    x = event.getSceneX();
                    y = event.getSceneY();

                });

                root.setOnMouseDragged((MouseEvent event) -> {
                    stage.setX(event.getScreenX() - x);
                    stage.setY(event.getScreenY() - y);

                    stage.setOpacity(.8);
                });

                root.setOnMouseReleased((MouseEvent event) -> {

                    stage.setOpacity(1);
                });

                stage.initStyle(StageStyle.TRANSPARENT);

                stage.setScene(scene);
                stage.show();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean isMaximized = false;

    public void close() {
        System.exit(0);
    }

    public void minimize() {
        Stage stage = (Stage) Main_form.getScene().getWindow();
        stage.setIconified(true);
    }

    public void toggleMaximizeRestore() {
        Stage stage = (Stage) Main_form.getScene().getWindow();

        if (isMaximized) {
            // Jika sedang maximize, kembalikan ke ukuran sebelumnya
            stage.setMaximized(false);
            isMaximized = false;
        } else {
            // Jika tidak maximize, lakukan maximize
            stage.setMaximized(true);
            isMaximized = true;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inventoryTypeList();
        inventoryStatusList();

        inventoryShowData();

        menuDisplayCard();
    }

}
