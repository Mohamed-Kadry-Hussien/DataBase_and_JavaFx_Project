package com.example.pharmacy;

public class RestockForm {
    private int medicineId ;
    private String medicineName;
    private int quantity ;
    private String supplier_name ;
    private String supplier_phone ;

    public RestockForm(int medicineId, String medicineName, int quantity, String supplier_name, String supplier_phone) {
        this.medicineId = medicineId;
        this.medicineName = medicineName;
        this.quantity = quantity;
        this.supplier_name = supplier_name;
        this.supplier_phone = supplier_phone;
    }
    public RestockForm(){}

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

    public String getSupplier_name() {
        return supplier_name;
    }

    public void setSupplier_name(String supplier_name) {
        this.supplier_name = supplier_name;
    }

    public String getSupplier_phone() {
        return supplier_phone;
    }

    public void setSupplier_phone(String supplier_phone) {
        this.supplier_phone = supplier_phone;
    }
}
