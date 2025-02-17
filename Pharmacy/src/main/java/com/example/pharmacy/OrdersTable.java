package com.example.pharmacy;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.ObservableList;

public class OrdersTable {

    public TableView<Order> createOrdersTable(ObservableList<Order> orders) {
        // تعريف جدول الطلبات
        TableView<Order> tableView = new TableView<>();

        // تعريف الأعمدة
        TableColumn<Order, String> idColumn = new TableColumn<>("Medicine ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("medicineId"));
        idColumn.setMinWidth(80);

        TableColumn<Order, String> nameColumn = new TableColumn<>("Medicine Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("medicineName"));
        nameColumn.setMinWidth(200);

        TableColumn<Order, Integer> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        quantityColumn.setMinWidth(50);

        TableColumn<Order, Double> unitPriceColumn = new TableColumn<>("Unit Price");
        unitPriceColumn.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        unitPriceColumn.setMinWidth(100);

        TableColumn<Order, Double> totalPriceColumn = new TableColumn<>("Total Price");
        totalPriceColumn.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        totalPriceColumn.setMinWidth(120);

        // إضافة الأعمدة إلى الجدول
        tableView.getColumns().addAll(idColumn, nameColumn, quantityColumn, unitPriceColumn, totalPriceColumn);

        // ضبط البيانات
        tableView.setItems(orders);

        return tableView;
    }

    // نموذج بيانات الطلب
    public static class Order {
        private int medicineId;
        private String medicineName;
        private int quantity;
        private double unitPrice;

        public Order(int medicineId, String medicineName, int quantity, double unitPrice) {
            this.medicineId = medicineId;
            this.medicineName = medicineName;
            this.quantity = quantity;
            this.unitPrice = unitPrice;
        }

        public int getMedicineId() {
            return medicineId;
        }

        public void setMedicineId(int medicineId) {
            this.medicineId = medicineId;
        }

        public String getMedicineName() {
            return medicineName;
        }

        public void setMedicineName(String medicineName) {
            this.medicineName = medicineName;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public double getUnitPrice() {
            return unitPrice;
        }

        public void setUnitPrice(double unitPrice) {
            this.unitPrice = unitPrice;
        }

        public double getTotalPrice() {
            return quantity * unitPrice;
        }
    }
}
