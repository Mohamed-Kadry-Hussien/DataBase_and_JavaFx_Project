package com.example.pharmacy;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ReStockTable {
    public static class Order {
        private int medicineId;
        private String medicineName;
        private int quantity;
        private String supplierName;
        private String supplierPhone;

        public Order(int medicineId, String medicineName, int quantity, String sellerName, String phone) {
            this.medicineId = medicineId;
            this.medicineName = medicineName;
            this.quantity = quantity;
            this.supplierName = sellerName;
            this.supplierPhone = phone;
        }


        public int getMedicineId() {
            return medicineId;
        }

        public String getMedicineName() {
            return medicineName;
        }

        public int getQuantity() {
            return quantity;
        }

        public String getSupplierName() {
            return supplierName;
        }

        public String getSupplierPhone() {
            return supplierPhone;
        }
    }

    public TableView<Order> createStockTable(ObservableList<Order> orders) {
        TableView<Order> tableView = new TableView<>(orders);

        TableColumn<Order, Integer> idColumn = new TableColumn<>("Medicine ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("medicineId"));
        idColumn.setPrefWidth(120);

        TableColumn<Order, String> nameColumn = new TableColumn<>("Medicine Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("medicineName"));
        nameColumn.setPrefWidth(120);

        TableColumn<Order, Integer> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        quantityColumn.setPrefWidth(120);

        TableColumn<Order, String> supplierNameColumn = new TableColumn<>("supplier Name");
        supplierNameColumn.setCellValueFactory(new PropertyValueFactory<>("supplierName"));
        supplierNameColumn.setPrefWidth(120);


        TableColumn<Order, String> supplierPhoneColumn = new TableColumn<>("supplier Phone");
        supplierPhoneColumn.setCellValueFactory(new PropertyValueFactory<>("supplierPhone"));
        supplierPhoneColumn.setPrefWidth(120);

        tableView.getColumns().addAll(idColumn, nameColumn, quantityColumn, supplierNameColumn, supplierPhoneColumn);
        tableView.setPrefSize(600, 300); // Adjust table size as necessary
        return tableView;
    }
}
