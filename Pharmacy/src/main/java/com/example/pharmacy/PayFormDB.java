package com.example.pharmacy;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class PayFormDB {
    private static PayForm form ;
    private static DatabaseConnector db ;

    public PayFormDB(PayForm form) {
        this.form = form;
        db = new DatabaseConnector();
    }

    public PayFormDB() {
    }

    public static boolean isMedicineAvailable(int medicineId, int quantity) throws SQLException {
        String query = "SELECT quantity_in_stock FROM Medicine WHERE medicine_id = ?";

        try (PreparedStatement stmt = db.connect().prepareStatement(query)) {
            stmt.setInt(1, medicineId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    double quantityInStock = rs.getDouble("quantity_in_stock");
                    return quantityInStock > quantity;
                } else {
                    // Medicine ID not found
                    return false;
                }
            }
        }
    }
    public static PayForm getMedicineData(int medicineId,int quantity) throws SQLException {
        String query = "SELECT medicine_id, medicine_name, selling_Price " +
                "FROM Medicine WHERE medicine_id = ?";

        try (PreparedStatement stmt = db.connect().prepareStatement(query)) {
            stmt.setInt(1, medicineId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    PayForm payForm = new PayForm();
                    payForm.setMed_id(rs.getInt("medicine_id"));
                    payForm.setMed_name(rs.getString("medicine_name"));
                    payForm.setMed_price(rs.getFloat("selling_Price"));
                    payForm.setMed_quantity(quantity);
                    payForm.setTotal_price(rs.getFloat("selling_Price") * quantity);
                    return payForm;
                } else {
                    throw new SQLException("Medicine with ID " + medicineId + " not found.");
                }
            }
        }
    }
    public static boolean isCustomerNameExists() throws SQLException {
        String query = "SELECT 1 FROM Customer WHERE customer_name = ?";

        try (PreparedStatement stmt = db.connect().prepareStatement(query)) {
            stmt.setString(1, form.getCost_name());

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next(); // Returns true if a record exists
            }
        }
    }
    public static void insertCustomer() throws SQLException {
        if(!isCustomerNameExists()) {
            String insertQuery = "INSERT INTO Customer (customer_name, phone_number, address) VALUES (?, ?, ?)";

            try (PreparedStatement stmt = db.connect().prepareStatement(insertQuery)) {
                stmt.setString(1, form.getCost_name());
                stmt.setString(2, form.getCust_phone());
                stmt.setString(3, form.getCust_adress());

                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Customer inserted successfully.");
                } else {
                    System.out.println("Customer insertion failed.");
                }
            }
        }
    }
    public static int getCustomerIdByName() throws SQLException {
        String query = "SELECT customer_id FROM Customer WHERE customer_name = ?";
        try (PreparedStatement stmt = db.connect().prepareStatement(query)) {
            stmt.setString(1, form.getCost_name());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("customer_id");
                } else {
                    return -1; // Customer not found
                }
            }
        }
    }
    //  IMPORTANT : HERE WE GET THE DATA ROW BY ROW FROM TABLE
    public static boolean updateMedicineQuantity() throws SQLException {
        // Step 1: Check current stock
        String checkQuery = "SELECT quantity_in_stock FROM Medicine WHERE medicine_id = ?";
        try (PreparedStatement checkStmt = db.connect().prepareStatement(checkQuery)) {
            checkStmt.setInt(1, form.getMed_id());

            try (ResultSet rs = checkStmt.executeQuery()) {
                if (rs.next()) {
                    int currentStock = rs.getInt("quantity_in_stock");

                    if (currentStock >= form.getMed_quantity()) {
                        // Step 2: Update stock
                        String updateQuery = "UPDATE Medicine SET quantity_in_stock = quantity_in_stock - ? WHERE medicine_id = ?";
                        try (PreparedStatement updateStmt = db.connect().prepareStatement(updateQuery)) {
                            updateStmt.setFloat(1, form.getMed_quantity());
                            updateStmt.setInt(2, form.getMed_id());

                            int rowsAffected = updateStmt.executeUpdate();
                            return rowsAffected > 0; // Return true if the update was successful
                        }
                    } else {
                        System.out.println("Insufficient stock. Current stock: " + currentStock);
                        return false;
                    }
                } else {
                    System.out.println("Medicine with ID " + form.getMed_id() + " not found.");
                    return false;
                }
            }
        }
    }
    // دي هنستخدمها علشان نجيب ال order_id اللي عليه الدور للفاتوره دي
    public static int getMaxOrderId() throws SQLException {
        String query = "SELECT MAX(order_id) AS max_order_id FROM Orders";

        try (PreparedStatement stmt = db.connect().prepareStatement(query)) {
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("max_order_id")+1;
                } else {
                    return 0; // No orders in the table
                }
            }
        }
    }
    // افتكر هنا بناخد الاوردر id مره وحده للجدول كله
    public static void insertOrder() throws SQLException {
        String insertQuery = "INSERT INTO Orders (order_id,  customer_id, order_date) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = db.connect().prepareStatement(insertQuery)) {
            stmt.setInt(1, form.getOrder_id());
            stmt.setInt(2, getCustomerIdByName());
            stmt.setDate(3, Date.valueOf(LocalDate.now()));
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Order inserted successfully.");
            } else {
                System.out.println("Failed to insert order.");
            }
        }
    }
    public static void insertOrderDetails() throws SQLException {
        String insertQuery = "INSERT INTO OrderDetails (order_id, medicine_id, quantity , total_price) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = db.connect().prepareStatement(insertQuery)) {
            stmt.setInt(1, form.getOrder_id());
            stmt.setInt(2, form.getMed_id());
            stmt.setInt(3,form.getMed_quantity() );
            stmt.setFloat(4, form.getTotal_price());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Order inserted successfully.");
            } else {
                System.out.println("Failed to insert order.");
            }
        }
    }

}
