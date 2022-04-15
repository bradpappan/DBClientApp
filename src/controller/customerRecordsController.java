package controller;


import com.mysql.cj.xdevapi.Table;
import helper.customerRecordsQuery;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.customerRecordsModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;


//Check DAODemo2021 Controller class for good example on tableview data
public class customerRecordsController implements Initializable {
    public customerRecordsController() {

    }

    /*ObservableList<>

    public static customerRecordsModel selectedCustomerToEdit;

    @FXML public TableView<customerRecordsModel> customerRecordsTable;
    @FXML private TableColumn<customerRecordsModel, Integer> customerId;
    @FXML private TableColumn<customerRecordsModel, String> customerName;
    @FXML private TableColumn<customerRecordsModel, String> customerPostalCode;
    @FXML private TableColumn<customerRecordsModel, String> customerPhone;
    @FXML private TableColumn<customerRecordsModel, Date> customerCreateDate;
    @FXML private TableColumn<customerRecordsModel, String> customerCreatedBy;
    @FXML private TableColumn<customerRecordsModel, Date> customerLastUpdate;
    @FXML private TableColumn<customerRecordsModel, String> customerLastUpdatedBy;
    @FXML private TableColumn<customerRecordsModel, Integer> customerDivisionId;*/

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void deleteCustomerHandler() throws SQLException {

        int rowsAffected = customerRecordsQuery.delete(1);
    }
}
