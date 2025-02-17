package com.example.pharmacy;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StockDB {

    private static Stock form ;
    private static DatabaseConnector db ;

    public StockDB() {
        db = new DatabaseConnector();
    }

    public StockDB(Stock form) {
        this.form = form;
        db = new DatabaseConnector();
    }

    public  void insertManufacturer() throws SQLException {

        if(!isManufacturerExist()){
            String query = "INSERT INTO Manufacturer (manufacturer_name, email, address) VALUES (?, ?, ?)";

            try (PreparedStatement preparedStatement = db.connect().prepareStatement(query)) {
                preparedStatement.setString(1, form.getManf_name());
                preparedStatement.setString(2, form.getManf_mail());
                preparedStatement.setString(3, form.getManf_address());

                int rowsAffected = preparedStatement.executeUpdate();

            }
        }
    }
    public  void insertSupplier() throws SQLException {

        if(!isSupplierExist()){
            String query = "INSERT INTO Supplier (supplier_name, contact_number, email,address) VALUES (?, ?, ?, ?)";

            try (PreparedStatement preparedStatement = db.connect().prepareStatement(query)) {
                preparedStatement.setString(1, form.getSupplier_name());
                preparedStatement.setString(2, form.getSupplier_phone());
                preparedStatement.setString(3, form.getSupplier_mail());
                preparedStatement.setString(4, form.getSupplier_address());

                int rowsAffected = preparedStatement.executeUpdate();

            }
        }
    }
    public  void AddMedicine() throws SQLException {

        if(!isMedicineExist()){

            String query = "INSERT INTO Medicine (medicine_id, medicine_name, unite_price , selling_Price,quantity_in_stock,manufacturer_id,supplier_id) VALUES (?, ?, ?, ?,?,?,?)";

            try (PreparedStatement preparedStatement = db.connect().prepareStatement(query)) {
                preparedStatement.setInt(1, form.getMedicineId());
                preparedStatement.setString(2, form.getMedicineName());
                preparedStatement.setFloat(3, form.getUnitPrice());
                preparedStatement.setFloat(4, form.getSellingPrice());
                preparedStatement.setInt(5, form.getQuantity());
                preparedStatement.setInt(6, getManufacturerIdByName());
                preparedStatement.setInt(7, getSupplierIdByName());

                int rowsAffected = preparedStatement.executeUpdate();

            }
        }
        }
    public void updateMedicine() throws SQLException {
        String query = """
        UPDATE Medicine 
        SET 
            medicine_name = ?, 
            unite_price = ?, 
            selling_price = ?, 
            quantity_in_stock = ? 
        WHERE medicine_id = ?
    """;

        try (PreparedStatement preparedStatement = db.connect().prepareStatement(query)) {
            preparedStatement.setString(1, form.getMedicineName());
            preparedStatement.setFloat(2, form.getUnitPrice());
            preparedStatement.setFloat(3, form.getSellingPrice());
            preparedStatement.setFloat(4, form.getQuantity());
            preparedStatement.setInt(5, form.getMedicineId());

            int rowsAffected = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }




    public  boolean isMedicineExist() throws SQLException {
        // Use a fully parameterized query
        String query = "SELECT 1 FROM " + "Medicine" + " WHERE [medicine_name] = ? and [medicine_id] = ?";

        try (PreparedStatement preparedStatement = db.connect().prepareStatement(query)) {
            preparedStatement.setString(1, form.getMedicineName());
            preparedStatement.setInt(2, form.getMedicineId());

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }
        }
    }
    public  boolean isSupplierExist() throws SQLException {
        // Use a fully parameterized query
        String query = "SELECT 1 FROM " + "Supplier" + " WHERE [supplier_name] = ?";

        try (PreparedStatement preparedStatement = db.connect().prepareStatement(query)) {
            preparedStatement.setString(1, form.getSupplier_name());

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }
        }
    }
    public  boolean isManufacturerExist() throws SQLException {
        // Use a fully parameterized query
        String query = "SELECT 1 FROM " + "Manufacturer" + " WHERE [manufacturer_name] = ?";

        try (PreparedStatement preparedStatement = db.connect().prepareStatement(query)) {
            preparedStatement.setString(1, form.getManf_name());

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }
        }
    }
    public  int getSupplierIdByName() throws SQLException {
        String query = "SELECT [supplier_id] FROM " + "Supplier" + " WHERE [supplier_name] = ?";

        try (PreparedStatement preparedStatement = db.connect().prepareStatement(query)) {
            preparedStatement.setString(1, form.getSupplier_name());

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("supplier_id");
                }
            }
        }
        return -1; // Return -1 if no ID is found for the given name
    }
    public  int getManufacturerIdByName() throws SQLException {
        String query = "SELECT [manufacturer_id] FROM " + "Manufacturer" + " WHERE [manufacturer_name] = ?";

        try (PreparedStatement preparedStatement = db.connect().prepareStatement(query)) {
            preparedStatement.setString(1, form.getManf_name());

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("manufacturer_id");
                }
            }
        }
        return -1; // Return -1 if no ID is found for the given name
    }

    public  int getCurrentQuantity(int id) throws SQLException {
        String query = "SELECT [quantity_in_stock] FROM " + "Medicine" + " WHERE [medicine_id] = ?";

        try (PreparedStatement preparedStatement = db.connect().prepareStatement(query)) {
            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("quantity_in_stock");
                }
            }
        }
        return -1; // Return -1 if no ID is found for the given name
    }

    public List<String> getAllSupplierNames() {
        List<String> supplierNames = new ArrayList<>();
        String query = "SELECT supplier_name FROM Supplier";  // Replace 'suppliers' with your table name

        // Open database connection
        try (PreparedStatement preparedStatement = db.connect().prepareStatement(query)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    supplierNames.add(resultSet.getString("supplier_name"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return supplierNames;
    }
    public List<String> getAllManufactureNames() {
        List<String> manufacturer_names = new ArrayList<>();
        String query = "SELECT manufacturer_name FROM Manufacturer";  // Replace 'suppliers' with your table name

        // Open database connection
        try (PreparedStatement preparedStatement = db.connect().prepareStatement(query)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    manufacturer_names.add(resultSet.getString("manufacturer_name"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return manufacturer_names;
    }


}
