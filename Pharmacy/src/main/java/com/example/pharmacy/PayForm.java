package com.example.pharmacy;

import java.time.LocalDate;
import java.util.Date;

public class PayForm {
    private int order_id;
    private int med_id;
    private String med_name;
    private int med_quantity;
    private float med_price;
    private float total_price ;
    private String cost_name;
    private String cust_phone;
    private String cust_adress;
    private float Total_med_price ;

    public PayForm() {
    }

    public PayForm(int order_id,String cost_name, String cust_adress, String cust_phone,  int med_id, String med_name, float med_price, int med_quantity, float total_med_price, float total_price) {
        this.order_id = order_id;
        this.cost_name = cost_name;
        this.cust_adress = cust_adress;
        this.cust_phone = cust_phone;
        this.med_id = med_id;
        this.med_name = med_name;
        this.med_price = med_price;
        this.med_quantity = med_quantity;
        Total_med_price = total_med_price;
        this.total_price = total_price;
    }

    public String getCost_name() {
        return cost_name;
    }

    public void setCost_name(String cost_name) {
        this.cost_name = cost_name;
    }


    public String getCust_adress() {
        return cust_adress;
    }

    public void setCust_adress(String cust_adress) {
        this.cust_adress = cust_adress;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }



    public String getCust_phone() {
        return cust_phone;
    }

    public void setCust_phone(String cust_phone) {
        this.cust_phone = cust_phone;
    }

    public int getMed_id() {
        return med_id;
    }

    public void setMed_id(int med_id) {
        this.med_id = med_id;
    }

    public String getMed_name() {
        return med_name;
    }

    public void setMed_name(String med_name) {
        this.med_name = med_name;
    }

    public float getMed_price() {
        return med_price;
    }

    public void setMed_price(float med_price) {
        this.med_price = med_price;
    }

    public int getMed_quantity() {
        return med_quantity;
    }

    public void setMed_quantity(int med_quantity) {
        this.med_quantity = med_quantity;
    }

    public float getTotal_med_price() {
        return Total_med_price;
    }

    public void setTotal_med_price(float total_med_price) {
        Total_med_price = total_med_price;
    }

    public float getTotal_price() {
        return total_price;
    }

    public void setTotal_price(float total_price) {
        this.total_price = total_price;
    }

    public void setDate(LocalDate now) {

    }
}
