package com.example.pharmacy;

public class Stock {
    private int medicineId ;
    private String medicineName;
    private float unitPrice ;
    private float sellingPrice ;
    private int quantity ;
    private String manf_name ;
    private String manf_mail ;
    private String manf_address ;
    private String supplier_name ;
    private String supplier_mail ;
    private String supplier_phone ;
    private String supplier_address ;

    public Stock(int medicineId, String medicineName, int quantity, float sellingPrice, float unitPrice , String manf_name , String supplier_name) {
        this.medicineId = medicineId;
        this.medicineName = medicineName;
        this.quantity = quantity;
        this.sellingPrice = sellingPrice;
        this.unitPrice = unitPrice;
        this.manf_name = manf_name;
        this.supplier_name = supplier_name;
    }

    public Stock(String manf_address, String manf_mail, String manf_name, int medicineId, String medicineName, int quantity, float sellingPrice, String supplier_address, String supplier_mail, String supplier_name, String supplier_phone, float unitPrice) {
        this.manf_address = manf_address;
        this.manf_mail = manf_mail;
        this.manf_name = manf_name;
        this.medicineId = medicineId;
        this.medicineName = medicineName;
        this.quantity = quantity;
        this.sellingPrice = sellingPrice;
        this.supplier_address = supplier_address;
        this.supplier_mail = supplier_mail;
        this.supplier_name = supplier_name;
        this.supplier_phone = supplier_phone;
        this.unitPrice = unitPrice;
    }

    public Stock(int medicineId, String medicineName, int quantity, float sellingPrice, float unitPrice) {
        this.medicineId = medicineId;
        this.medicineName = medicineName;
        this.quantity = quantity;
        this.sellingPrice = sellingPrice;
        this.unitPrice = unitPrice;
    }
    public Stock(){}


    public String getManf_address() {
        return manf_address;
    }

    public void setManf_address(String manf_address) {
        this.manf_address = manf_address;
    }

    public String getManf_mail() {
        return manf_mail;
    }

    public void setManf_mail(String manf_mail) {
        this.manf_mail = manf_mail;
    }

    public String getManf_name() {
        return manf_name;
    }

    public void setManf_name(String manf_name) {
        this.manf_name = manf_name;
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

    public float getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(float sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public String getSupplier_address() {
        return supplier_address;
    }

    public void setSupplier_address(String supplier_address) {
        this.supplier_address = supplier_address;
    }

    public String getSupplier_mail() {
        return supplier_mail;
    }

    public void setSupplier_mail(String supplier_mail) {
        this.supplier_mail = supplier_mail;
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

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "AddToStock{" +
                "manf_address='" + manf_address + '\'' +
                ", medicineId=" + medicineId +
                ", medicineName='" + medicineName + '\'' +
                ", unitPrice=" + unitPrice +
                ", sellingPrice=" + sellingPrice +
                ", quantity=" + quantity +
                ", manf_name='" + manf_name + '\'' +
                ", manf_mail='" + manf_mail + '\'' +
                ", supplier_name='" + supplier_name + '\'' +
                ", supplier_mail='" + supplier_mail + '\'' +
                ", supplier_phone='" + supplier_phone + '\'' +
                ", supplier_address='" + supplier_address + '\'' +
                '}';
    }
}
