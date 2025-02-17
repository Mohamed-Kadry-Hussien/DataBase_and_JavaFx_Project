package com.example.pharmacy;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.sql.SQLException;

public class SupplierPage {
    private static Pane pane;
    private Image background ;
    private ImageView imv_background ;
    private VBox vbox1 ;
    private ComboBox<String> SupplierName;
    private Label supplierNameLabel;
    private TextField supplierNameField ;
    private Label supplierPhoneLabel;
    private TextField supplierPhoneField;
    private Label supplierEmailLabel;
    private TextField supplierEmailField ;
    private Label supplierAddressLabel ;
    private TextField supplierAddressField;
    private Button AddSupplier;
    private Button backButton;


    private static final String defaultButtonStyle =
            "-fx-background-color: #4376ec; " +
                    "-fx-text-fill: white; " +
                    "-fx-font-weight: bold; " +
                    "-fx-border-color: white; " +
                    "-fx-border-width: 2px; " +
                    "-fx-border-radius: 20px; " +
                    "-fx-background-radius: 20px;";




    public SupplierPage(Stage primaryStage, Scene scene) {
        pane = new Pane();


        // Background Image
        background = new Image("pills.jpg");
        imv_background = new ImageView(background);
        imv_background.setFitWidth(1200);
        imv_background.setFitHeight(600);
        imv_background.setPreserveRatio(false);
        imv_background.setPreserveRatio(false);
        imv_background.setSmooth(true);
        imv_background.setCache(true);

        // Header Label
        Label headerLabel = new Label("ADD SUPPLIER");
        headerLabel.setFont(Font.font("Arial", 28));
        headerLabel.setStyle("-fx-text-fill: black;  -fx-font-weight: bold;");
        headerLabel.setAlignment(Pos.CENTER);

        VBox headerBox = new VBox(headerLabel);
        headerBox.setAlignment(Pos.CENTER);
        headerBox.setPadding(new Insets(10));
        headerBox.setLayoutX(primaryStage.getWidth() / 4);
        headerBox.setLayoutY(20);
        headerBox.setPrefWidth(450);

        // VBox for Form Layout
        vbox1 = new VBox(10);
        vbox1.setAlignment(Pos.TOP_LEFT);
        vbox1.setPadding(new Insets(20));
        vbox1.setLayoutX(100);
        vbox1.setLayoutY(150);
        vbox1.setPrefWidth(800);
        // vbox1.setPrefHeight(200);
        vbox1.setStyle("-fx-background-color: transparent ; -fx-border-width: 5px;-fx-border-radius: 15px;-fx-border-color: #4376ec;");

        supplierNameLabel = new Label("Supplier Name:");
        supplierNameField = createStyledTextField("Enter Supplier Name");

        supplierPhoneLabel = new Label("Supplier Phone:");
        supplierPhoneField = createStyledTextField("Enter Supplier Phone");

        supplierEmailLabel = new Label("Supplier Email:");
        supplierEmailField = createStyledTextField("Enter Supplier Email");

        supplierAddressLabel = new Label("Supplier Address:");
        supplierAddressField = createStyledTextField("Enter Supplier Address");

        vbox1.getChildren().addAll(supplierNameLabel,supplierNameField,
                supplierPhoneLabel,supplierPhoneField,supplierEmailLabel,supplierEmailField,
                supplierAddressLabel,supplierAddressField

        );


        ////////////////////////////////back btn ///////////////////////////
        HBox buttonBox = new HBox(20);
        buttonBox.setAlignment(Pos.CENTER_LEFT);
        buttonBox.setPadding(new Insets(20));

        AddSupplier = createStyledButton("Add Supplier"," #386080");

        AddSupplier.setOnAction(event -> {
            handleAddSupplier();
        });

        backButton = createStyledButton("Back", "#FF9800");



        buttonBox.getChildren().addAll(AddSupplier, backButton);
        buttonBox.setLayoutX(primaryStage.getWidth() / 4);
        buttonBox.setLayoutY(500);

        ///////////////////////////////////////////////////////


        // Add elements to pane
        pane.getChildren().addAll(imv_background,headerBox, vbox1, buttonBox);

        backButton.setOnAction(e -> {
            AddMedicinePage addMedicinePage = new AddMedicinePage(primaryStage, scene);
            Scene addmedsscene = new Scene(addMedicinePage.getPane());
            primaryStage.setScene(addmedsscene); // Set back to the previous scene
            Button addMedicineBtn = (Button) scene.lookup("#newSupplier"); // Find the button in the main scene
            if (addMedicineBtn != null) {
                addMedicineBtn.setStyle(defaultButtonStyle); // Reset to the original style
            }
        });

    }
    private void handleAddSupplier() {
        if (supplierNameField.getText().isEmpty() || supplierPhoneField.getText().isEmpty() ||
                supplierEmailField.getText().isEmpty() || supplierAddressField.getText().isEmpty()) {


            Alert alert = new Alert(Alert.AlertType.WARNING, "All fields must be filled out.", ButtonType.OK);
            alert.setTitle("Input Error");
            alert.setHeaderText("Missing Information");
            alert.showAndWait();
        } else {
            String supplier_address = supplierAddressField.getText();
            String supplier_mail = supplierEmailField.getText();
            String supplier_name = supplierNameField.getText();
            String supplier_phone = supplierPhoneField.getText();

            Stock addToStock = new Stock();
            addToStock.setSupplier_name(supplier_name);
            addToStock.setSupplier_address(supplier_address);
            addToStock.setSupplier_mail(supplier_mail);
            addToStock.setSupplier_phone(supplier_phone);
            StockDB add = new StockDB(addToStock);
            try {
                add.insertSupplier();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            supplierNameField.clear();
            supplierPhoneField.clear();
            supplierEmailField.clear();
            supplierAddressField.clear();
            System.out.println("Supplier added successfully.");
        }
    }

    public static Pane getPane() {
        return pane;
    }
    private TextField createStyledTextField(String promptText) {
        TextField textField = new TextField();
        textField.setPromptText(promptText);
        textField.setStyle("-fx-text-fill: black;  -fx-font-weight: bold;");
        textField.setStyle("-fx-background-color: #f5f5f5;  -fx-border-radius: 5px;");
        textField.setPrefHeight(30);
        textField.setPrefWidth(100);
        return textField;
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
}
