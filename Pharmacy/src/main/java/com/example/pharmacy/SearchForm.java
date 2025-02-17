package com.example.pharmacy;

public class SearchForm {
    private int medicineId ;
    private String medicineName;
    private float sellingPrice ;
    private int quantity ;
    private String manf_name ;
    private String manf_mail ;
    private String supplier_name ;
    private String supplier_phone ;

    public SearchForm(int medicineId, String medicineName, int quantityInStock, String supplierName, String supplierPhone) {
    }

    public SearchForm(String manf_mail, String manf_name, int medicineId, String medicineName, int quantity, float sellingPrice, String supplier_name, String supplier_phone) {
        this.manf_mail = manf_mail;
        this.manf_name = manf_name;
        this.medicineId = medicineId;
        this.medicineName = medicineName;
        this.quantity = quantity;
        this.sellingPrice = sellingPrice;
        this.supplier_name = supplier_name;
        this.supplier_phone = supplier_phone;
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

    @Override
    public String toString() {
        return "SearchForm{" +
                "manf_mail='" + manf_mail + '\'' +
                ", medicineId=" + medicineId +
                ", medicineName='" + medicineName + '\'' +
                ", sellingPrice=" + sellingPrice +
                ", quantity=" + quantity +
                ", manf_name='" + manf_name + '\'' +
                ", supplier_name='" + supplier_name + '\'' +
                ", supplier_phone='" + supplier_phone + '\'' +
                '}';
    }
}
