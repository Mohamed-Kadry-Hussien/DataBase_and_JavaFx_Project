package com.example.pharmacy;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SearchFormDB {
    private SearchForm Form ;
    DatabaseConnector db0 ;
    public SearchFormDB() {
        db0 = new DatabaseConnector();
    }
    public List<RestockForm> MedicineNeedRestock() {
        List<RestockForm> searchForms = new ArrayList<>();
        String query = """
                SELECT 
                    m.medicine_id, 
                    m.medicine_name,  
                    m.quantity_in_stock,  
                    s.supplier_name, 
                    s.contact_number AS supplier_phone
                FROM Medicine m
                JOIN Supplier s ON m.supplier_id = s.supplier_id
                Where m.quantity_in_stock < 4 
                """;

        try (PreparedStatement preparedStatement = db0.connect().prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                RestockForm restockForm = new RestockForm(
                        resultSet.getInt("medicine_id"),
                        resultSet.getString("medicine_name"),
                        resultSet.getInt("quantity_in_stock"),
                        resultSet.getString("supplier_name"),
                        resultSet.getString("supplier_phone")
                );
                searchForms.add(restockForm);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return searchForms;
    }
    public SearchForm fetchMedicineDetailsById(int medicineId) {

        String query = """
            SELECT 
                m.medicine_id, 
                m.medicine_name, 
                m.selling_price, 
                m.quantity_in_stock, 
                mf.manufacturer_name, 
                mf.email AS manufacturer_email, 
                s.supplier_name, 
                s.contact_number AS supplier_phone
            FROM Medicine m
            JOIN Manufacturer mf ON m.manufacturer_id = mf.manufacturer_id
            JOIN Supplier s ON m.supplier_id = s.supplier_id
            WHERE m.medicine_id = ?;
            """;

        try (PreparedStatement preparedStatement = db0.connect().prepareStatement(query)) {
            preparedStatement.setInt(1, medicineId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return new SearchForm(
                        resultSet.getString("manufacturer_email"),
                        resultSet.getString("manufacturer_name"),
                        resultSet.getInt("medicine_id"),
                        resultSet.getString("medicine_name"),
                        resultSet.getInt("quantity_in_stock"),
                        resultSet.getFloat("selling_price"),
                        resultSet.getString("supplier_name"),
                        resultSet.getString("supplier_phone")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Return null if no matching record is found
    }

}


