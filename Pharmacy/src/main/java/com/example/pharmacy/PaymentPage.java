package com.example.pharmacy;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class PaymentPage {
    private Pane pane;
    private GridPane medicineDetails; //  dynamic
    private final int appWidth = 1200;
    private final int appHeight = 650;
    private Button addButton ;
    private static final String defaultButtonStyle =
            "-fx-background-color: #4376ec; " +
                    "-fx-text-fill: white; " +
                    "-fx-font-weight: bold; " +
                    "-fx-border-color: white; " +
                    "-fx-border-width: 2px; " +
                    "-fx-border-radius: 20px; " +
                    "-fx-background-radius: 20px;";

    public PaymentPage(Stage primaryStage, Scene scene) {
        pane = new Pane();
        /////////////////////////////// Background Image//////////////////////////////////////////
        Image background = new Image("payImage.jpg"); // Replace with your background
        ImageView imv_background = new ImageView(background);
        imv_background.setFitWidth(1200);
        imv_background.setFitHeight(600);
        imv_background.setPreserveRatio(false);


        ///////////////////////////////////////////Payment ID & Date & Time //////////////////////////////////
        HBox paymentDetails = new HBox(100);
        paymentDetails.setAlignment(Pos.CENTER);
        paymentDetails.setLayoutX(120);
        paymentDetails.setLayoutY(20);
        paymentDetails.setAlignment(Pos.CENTER);

        VBox paymentID = new VBox(15);
        Label PaymentID = new Label("Payment ID");
        PaymentID.setFont(Font.font("Arial", 25));
        paymentID.setAlignment(Pos.CENTER);

        TextField receiptIDText = new TextField();
        receiptIDText.setPrefSize(250,40);
        receiptIDText.setAlignment(Pos.CENTER);
        receiptIDText.setStyle(
                "-fx-font-weight: bold;" +
                        "-fx-text-fill: black;" +
                        "-fx-background-color: trasparent;" +
                        "-fx-background-radius: 20px;" +
                        "-fx-border-color: white;" +
                        "-fx-border-radius: 20px ;"
        );
        receiptIDText.setEditable(false);
        paymentID.getChildren().addAll(PaymentID, receiptIDText);
        paymentID.setAlignment(Pos.CENTER);

        VBox date = new VBox(15);
        Label dateLabel = new Label("Date");
        dateLabel.setFont(Font.font("Arial", 25));
        dateLabel.setAlignment(Pos.CENTER);

        TextField dateText = new TextField(LocalDate.now().toString());
        dateText.setPrefSize(250,40);
        dateText.setAlignment(Pos.CENTER);
        dateText.setStyle(
                "-fx-font-weight: bold;" +
                        "-fx-text-fill: black;" +
                        "-fx-background-color: trasparent;" +
                        "-fx-background-radius: 20px;" +
                        "-fx-border-color: white;" +
                        "-fx-border-radius: 20px ;"
        );
        dateText.setEditable(false);
        date.getChildren().addAll(dateLabel, dateText);
        date.setAlignment(Pos.CENTER);

        VBox time = new VBox(15);
        Label TimeLabel = new Label("Time");
        TimeLabel.setAlignment(Pos.CENTER);
        TimeLabel.setFont(Font.font("Arial", 25));


        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
        TextField timeText = new TextField(LocalTime.now().format(timeFormatter));
        timeText.setAlignment(Pos.CENTER);
        timeText.setPrefSize(250,40);
        timeText.setStyle(
                "-fx-font-weight: bold;" +
                        "-fx-text-fill: black;" +
                        "-fx-background-color: trasparent;" +
                        "-fx-background-radius: 20px;" +
                        "-fx-border-color: white;" +
                        "-fx-border-radius: 20px ;"
        );
        timeText.setEditable(false);
        time.getChildren().addAll(TimeLabel, timeText);
        time.setAlignment(Pos.CENTER);

        paymentDetails.getChildren().addAll( date, time);


        ///////////////////////////////////Input Fields ////////////////////////////////////

        HBox inputFields = new HBox(100);
        inputFields.setAlignment(Pos.CENTER);
        inputFields.setPadding(new Insets(20));
        inputFields.setLayoutX(100);
        inputFields.setLayoutY(120);

        TextField medicineIDField = new TextField();
        medicineIDField.setAlignment(Pos.CENTER);
        medicineIDField.setPromptText("Enter Medicine ID");
        medicineIDField.setPrefSize(250,40);
        medicineIDField.setStyle( "-fx-font-weight: bold;" +
                "-fx-text-fill: #007686;" +
                "-fx-prompt-text-fill: #007686;"+
                "-fx-background-color: transparent;" +
                "-fx-background-radius: 10px;" +
                "-fx-border-color: #007686;" +
                "-fx-border-radius: 20px ;");


        TextField quantityField = new TextField();
        quantityField.setAlignment(Pos.CENTER);
        quantityField.setPromptText("Enter Quantity");
        quantityField.setPrefSize(250,40);
        quantityField.setStyle( "-fx-font-weight: bold;" +
                "-fx-text-fill: #007686;" +
                "-fx-prompt-text-fill: #007686;"+
                "-fx-background-color: transparent;" +
                "-fx-background-radius: 10px;" +
                "-fx-border-color: #007686;" +
                "-fx-border-radius: 20px ;");

        Button addButton = createStyledButton("Add Medicine", "#4376ec");
        addButton.setPrefSize(250,40);
        inputFields.getChildren().addAll(medicineIDField, quantityField, addButton);


        ////////////////////////////////////////////VBOXS////////////////////////////////////
        VBox vbox1 = new VBox(10);
        vbox1.setAlignment(Pos.CENTER);
        vbox1.setPadding(new Insets(15));

        vbox1.setStyle(
                "-fx-background-color: white;" +
                        "-fx-background-radius: 20px;" +
                        "-fx-border-color: white;" +
                        "-fx-border-radius: 20px ;");
        vbox1.setPrefSize(250,600);
        //////////////////////////////////////////////
        VBox vbox2 = new VBox(10);
        vbox2.setAlignment(Pos.CENTER);
        vbox2.setPadding(new Insets(15));
        vbox2.setStyle(
                "-fx-background-color: transparent;" +
                        "-fx-background-radius: 20px;" +
                        "-fx-border-color: transparent;" +
                        "-fx-border-radius: 20px ;");
        vbox2.setPrefSize(600,400);
        /////////////////////////VBOX 1 Usage/////////////////////////////////////////////
        Label customerNameLabel = new Label("Customer Name:");
        customerNameLabel.setFont(Font.font("Arial", 15));
        customerNameLabel.setStyle("-fx-text-fill: #007686;" );
        TextField customerNameField = new TextField();
        customerNameField.setStyle("-fx-text-fill: #007686;" +
                "-fx-prompt-text-fill: #007686;");
        customerNameField.setAlignment(Pos.CENTER);
        customerNameField.setPromptText("Ex: Ahmed");

        vbox1.getChildren().addAll(customerNameLabel, customerNameField);

        Label customerPhoneLabel = new Label("Phone Number:");
        customerPhoneLabel.setFont(Font.font("Arial", 15));
        customerPhoneLabel.setStyle("-fx-text-fill: #007686;" );
        TextField customerPhoneField = new TextField();
        customerPhoneField.setStyle("-fx-text-fill: #007686;"+
                "-fx-prompt-text-fill: #007686;");
        customerPhoneField.setAlignment(Pos.CENTER);
        customerPhoneField.setPromptText("Ex: 01111111111");
        vbox1.getChildren().addAll(customerPhoneLabel, customerPhoneField);

        Label customerAddressLabel = new Label("Customer Address:");
        customerAddressLabel.setFont(Font.font("Arial", 15));
        customerAddressLabel.setStyle("-fx-text-fill: #007686;" );
        TextField customerAddressField = new TextField();
        customerAddressField.setStyle("-fx-text-fill: #007686;" +
                "-fx-prompt-text-fill: #007686;");
        customerAddressField.setAlignment(Pos.CENTER);
        customerAddressField.setPromptText("Ex: Zagazig Street");
        vbox1.getChildren().addAll(customerAddressLabel, customerAddressField);

        ////////////////////////////////vbox 2 Components /////////////////////////////////
        // Medicine Details GridPane
        //  addButton= new Button("Add Medicine");
        ObservableList<OrdersTable.Order> orders = FXCollections.observableArrayList();

        // إنشاء الجدول باستخدام OrdersTable class
        OrdersTable ordersTable = new OrdersTable();
        TableView<OrdersTable.Order> tableView = ordersTable.createOrdersTable(orders);

        TextField totalField = new TextField();
        totalField.setText("0.0");
        totalField.setEditable(false);
        totalField.setPrefSize(150, 40);
        // ضبط الحجم الافتراضي للجدول
        // tableView.setPrefHeight(500);
        addButton.setOnAction(event -> {
            // Validate if medicineIDField and quantityField are not empty
            if (medicineIDField.getText().isEmpty() || quantityField.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Medicine ID and Quantity must be filled out.", ButtonType.OK);
                alert.setTitle("Input Error");
                alert.setHeaderText("Missing Information");
                alert.getDialogPane().setStyle("-fx-background-color: #f8d7da; -fx-text-fill: black;");
                alert.showAndWait();
                return; // Stop further processing if fields are empty
            }

            try {
                int medicineId = Integer.parseInt(medicineIDField.getText());
                int quantity = Integer.parseInt(quantityField.getText());

                PayForm newform = new PayForm();
                PayFormDB formDB = new PayFormDB(newform);

                if (formDB.isMedicineAvailable(medicineId, quantity)) {
                    PayForm dbData = formDB.getMedicineData(medicineId, quantity);

                    // Extract data from database result
                    int medicine_id = dbData.getMed_id();
                    float sellingPrice = dbData.getMed_price();
                    String medicineName = dbData.getMed_name();
                    float totalPrice = dbData.getTotal_price();
                    int quantities = dbData.getMed_quantity();

                    // Add the order to the list and update the total field
                    orders.add(new OrdersTable.Order(medicine_id, medicineName, quantities, sellingPrice));
                    totalField.requestFocus();
                    totalField.setText(String.valueOf(Double.parseDouble(totalField.getText()) + totalPrice));
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Medicine not found or insufficient quantity.", ButtonType.OK);
                    alert.setTitle("Inventory Error");
                    alert.setHeaderText("Unavailable Medicine");
                    alert.getDialogPane().setStyle("-fx-background-color: #f8d7da; -fx-text-fill: black;");
                    alert.showAndWait();
                }
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid input. Please enter valid numbers for Medicine ID and Quantity.", ButtonType.OK);
                alert.setTitle("Input Error");
                alert.setHeaderText("Invalid Data Format");
                alert.getDialogPane().setStyle("-fx-background-color: #f8d7da; -fx-text-fill: black;");
                alert.showAndWait();
            } catch (SQLException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Database error: " + e.getMessage(), ButtonType.OK);
                alert.setTitle("Database Error");
                alert.setHeaderText("Failed to Fetch Data");
                alert.getDialogPane().setStyle("-fx-background-color: #f8d7da; -fx-text-fill: black;");
                alert.showAndWait();
            }
        });


        vbox2.getChildren().addAll(tableView);

        ////////////////////////////HBOX/////////////////////////////////////
        HBox vboxContainer = new HBox(100);
        vboxContainer.setAlignment(Pos.CENTER);
        vboxContainer.setLayoutX(120);
        vboxContainer.setLayoutY(200);
        vboxContainer.setPrefHeight(280);
        vboxContainer.getChildren().addAll(vbox1, vbox2);

        ///////////////////////
        HBox totalPriceContainer = new HBox(20);
        totalPriceContainer.setAlignment(Pos.CENTER);
        totalPriceContainer.setLayoutX(120);
        totalPriceContainer.setLayoutY(500);
        totalPriceContainer.setPrefHeight(50);
        /////////////////////////hbox back btn ///////////////////////
        HBox back_btn = new HBox(100);
        back_btn.setAlignment(Pos.CENTER);
        back_btn.setLayoutX(120);
        back_btn.setLayoutY(550);
        back_btn.setPrefHeight(50);


        /////////////////////////////Total & Print btn//////////////////////////////////////

        HBox hboxTotal = new HBox(15);
        hboxTotal.setAlignment(Pos.CENTER);
        hboxTotal.setPadding(new Insets(10));
        hboxTotal.setPrefHeight(120);
        hboxTotal.setStyle("-fx-font-weight: bold;" +
                "-fx-text-fill: black;" +
                "-fx-background-color: white;" +
                "-fx-background-radius: 20px;" +
                "-fx-border-color: white;" +
                "-fx-border-radius: 20px ;");


        Label lblTotal = new Label("Total Price ");
        lblTotal.setFont(Font.font("Arial", 15));
        lblTotal.setStyle("-fx-text-fill: black;" );

        totalField.setAlignment(Pos.CENTER);
        hboxTotal.getChildren().addAll(lblTotal, totalField);

        ////////////////////////pay and cancel Btn///////////////////////

        Button payButton = createStyledButton("Pay", "#4CAF50");
        //payButton.setFont(Font.font("Arial", 25));
        //payButton.setPrefSize(240,40);
        payButton.setOnMouseClicked(event ->{
            System.out.println("Pay button pressed");
        });

        payButton.setOnMouseClicked(event -> {
            System.out.println("Pay button pressed");
        });

        payButton.setOnAction(event -> {
            // Validate if customer fields are empty or tableView has no rows
            if (customerNameField.getText().isEmpty() || customerPhoneField.getText().isEmpty() ||
                    customerAddressField.getText().isEmpty() || tableView.getItems().isEmpty()) {

                Alert alert = new Alert(Alert.AlertType.WARNING,
                        "Customer information and table data must be filled before proceeding.", ButtonType.OK);
                alert.setTitle("Input Error");
                alert.setHeaderText("Missing Information");
                alert.getDialogPane().setStyle("-fx-background-color: #f8d7da; -fx-text-fill: black;");
                alert.showAndWait();
                return; // Stop further processing if validation fails
            }

            PayFormDB getoder = new PayFormDB();
            int order_Id;
            try {
                order_Id = getoder.getMaxOrderId();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            System.out.println(order_Id);

            PayForm newform = new PayForm();
            PayFormDB formDB = new PayFormDB(newform);

            String customer_name = customerNameField.getText();
            String customer_phone = customerPhoneField.getText();
            String customer_address = customerAddressField.getText();

            newform.setOrder_id(order_Id);
            newform.setCost_name(customer_name);
            newform.setCust_phone(customer_phone);
            newform.setCust_adress(customer_address);

            try {
                formDB.insertCustomer();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                formDB.insertOrder();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            // Process table rows
            while (!tableView.getItems().isEmpty()) {
                OrdersTable.Order firstOrder = tableView.getItems().get(0);

                PayForm payForm = new PayForm();
                payForm.setOrder_id(order_Id);
                payForm.setMed_id(firstOrder.getMedicineId());
                payForm.setMed_name(firstOrder.getMedicineName());
                payForm.setMed_quantity(firstOrder.getQuantity());
                payForm.setMed_price((float) firstOrder.getUnitPrice());
                payForm.setTotal_price((float) firstOrder.getTotalPrice());
                payForm.setCost_name(customer_name);

                PayFormDB callDb = new PayFormDB(payForm);

                try {
                    callDb.updateMedicineQuantity();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                // Remove the processed row from the table
                tableView.getItems().remove(0);
            }

            // Clear fields after processing
            customerNameField.setText("");
            customerPhoneField.setText("");
            customerAddressField.setText("");
            totalField.setText("0.0");
        });

        Button cancelButton = createStyledButton("Cancel Payment", "#f44336");

        cancelButton.setOnMouseClicked(event ->{
            System.out.println("Cancel button pressed");
        });
        cancelButton.setOnAction(event -> {
            while (!tableView.getItems().isEmpty()) {
                tableView.getItems().remove(0);
            }
            totalField.setText("0.0");
        });


        ////////////////////back btn ////////////////////////////////////////////

        Button backButton = createStyledButton("Back", "#FF9800");
        // backButton.setPrefSize(250,40);
        backButton.setOnAction(e -> {
            primaryStage.setScene(scene); // Navigate back to the main scene
            Button searchMedicineBtn = (Button) scene.lookup("#saleBtn"); // Find the button in the main scene
            if (searchMedicineBtn != null) {
                searchMedicineBtn.setStyle(defaultButtonStyle); // Reset to the original style
            }
        });

        totalPriceContainer.getChildren().addAll(backButton,hboxTotal, payButton,cancelButton);



        ///////////////////////////Pane////////////////////////////////////////////

        pane.getChildren().addAll(imv_background, paymentDetails,inputFields,vboxContainer,totalPriceContainer,back_btn);





    }
    private Button createStyledButton(String text, String color) {
        Button button = new Button(text);
        button.setPrefSize(200, 40);
        button.setStyle(
                "-fx-background-color: " + color + ";" +
                        "-fx-text-fill: white;" +
                        "-fx-font-weight: bold;" +
                        "-fx-border-radius: 10px;" +
                        "-fx-background-radius: 10px;");
        button.setEffect(new DropShadow(3, Color.GRAY));
        return button;
    }

    public Pane getPane() {
        return pane;
    }
}
