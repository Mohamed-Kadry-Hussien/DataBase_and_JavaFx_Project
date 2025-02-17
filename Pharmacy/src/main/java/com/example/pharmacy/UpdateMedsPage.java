package com.example.pharmacy;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.sql.SQLException;

public class UpdateMedsPage {
    private Pane pane;
    private static final String defaultButtonStyle =
            "-fx-background-color: #4376ec; " +
                    "-fx-text-fill: white; " +
                    "-fx-font-weight: bold; " +
                    "-fx-border-color: white; " +
                    "-fx-border-width: 2px; " +
                    "-fx-border-radius: 20px; " +
                    "-fx-background-radius: 20px;";

    public UpdateMedsPage(Stage primaryStage, Scene scene) {
        pane = new Pane();

        // Background Image
        Image background = new Image("pills.jpg");
        ImageView imvBackground = new ImageView(background);
        imvBackground.setFitWidth(1200);
        imvBackground.setFitHeight(600);
        imvBackground.setPreserveRatio(false);
        imvBackground.setSmooth(true);
        imvBackground.setCache(true);

        // Header Label
        Label headerLabel = new Label("Update Medicine Information");
        headerLabel.setFont(Font.font("Arial", 28));
        headerLabel.setStyle("-fx-text-fill: black;  -fx-font-weight: bold;");
        headerLabel.setAlignment(Pos.CENTER);

        // Header Box
        VBox headerBox = new VBox(headerLabel);
        headerBox.setAlignment(Pos.CENTER);
        headerBox.setPadding(new Insets(10));
        headerBox.setLayoutX(primaryStage.getWidth() / 4);
        headerBox.setLayoutY(20);
        headerBox.setPrefWidth(450);

        VBox formBox = new VBox(10);
        formBox.setAlignment(Pos.TOP_LEFT);
        formBox.setPadding(new Insets(20));
        formBox.setLayoutX(100);
        formBox.setLayoutY(100);
        formBox.setPrefWidth(800);
        formBox.setStyle(" -fx-border-color: #4376ec; -fx-border-width: 5px;");

        Label medicineIdLabel = new Label("Medicine ID:");
        TextField medicineIdField = createStyledTextField("Enter Medicine ID");

        Label medicineNameLabel = new Label("Medicine Name:");
        TextField medicineNameField = createStyledTextField("Enter Medicine Name");

        Label unitPriceLabel = new Label("Unit Price:");
        TextField unitPriceField = createStyledTextField("Enter Unit Price");

        Label sellingPriceLabel = new Label("Selling Price:");
        TextField sellingPriceField = createStyledTextField("Enter Selling Price");

        Label quantityLabel = new Label("Quantity:");
        TextField quantityField = createStyledTextField("Enter Quantity");

        // Add fields to the form layout
        formBox.getChildren().addAll(
                medicineIdLabel, medicineIdField,
                medicineNameLabel, medicineNameField,
                unitPriceLabel, unitPriceField,
                sellingPriceLabel, sellingPriceField,
                quantityLabel, quantityField
        );

        ////////////////////////////////// Buttons ///////////////////////////////////
        HBox buttonBox = new HBox(20);
        buttonBox.setAlignment(Pos.CENTER_LEFT);
        buttonBox.setPadding(new Insets(20));

        Button updateButton = createStyledButton("Update Medicine","#4CAF50");
        Button backButton = createStyledButton("Back", "#FF9800");

        updateButton.setOnAction(event -> {
            // Validate input fields before updating
            if (medicineIdField.getText().isEmpty() || medicineNameField.getText().isEmpty() ||
                    unitPriceField.getText().isEmpty() || sellingPriceField.getText().isEmpty() || quantityField.getText().isEmpty()) {

                Alert alert = new Alert(Alert.AlertType.WARNING, "All fields must be filled out.", ButtonType.OK);
                alert.setTitle("Input Error");
                alert.setHeaderText("Missing Information");
                alert.getDialogPane().setStyle("-fx-background-color: #4376ec; -fx-text-fill: white;");
                alert.showAndWait();
            } else {
                // Proceed with updating medicine
                int medicineId = Integer.parseInt(medicineIdField.getText());
                String medicineName = medicineNameField.getText();
                float unitPrice = Float.parseFloat(unitPriceField.getText());
                float sellingPrice = Float.parseFloat(sellingPriceField.getText());
                int quantity = Integer.parseInt(quantityField.getText());

                Stock formstock = new Stock(medicineId, medicineName, quantity, sellingPrice, unitPrice);
                StockDB updateForm = new StockDB(formstock);
                try {
                    updateForm.updateMedicine();
                    Alert successAlert = new Alert(Alert.AlertType.INFORMATION, "Medicine updated successfully!", ButtonType.OK);
                    successAlert.setTitle("Success");
                    successAlert.setHeaderText("Update Successful");
                    successAlert.showAndWait();
                } catch (SQLException e) {
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR, "Failed to update medicine: " + e.getMessage(), ButtonType.OK);
                    errorAlert.setTitle("Database Error");
                    errorAlert.setHeaderText("Update Failed");
                    errorAlert.showAndWait();
                }
                // Clear the fields after update
                medicineIdField.setText("");
                medicineNameField.setText("");
                unitPriceField.setText("");
                sellingPriceField.setText("");
                quantityField.setText("");
            }
        });

        backButton.setOnAction(e -> {
            primaryStage.setScene(scene);
            Button updateMedicineBtn = (Button) scene.lookup("#updateMedicineBtn");
            if (updateMedicineBtn != null) {
                updateMedicineBtn.setStyle(defaultButtonStyle);
            }
        });

        buttonBox.getChildren().addAll(updateButton, backButton);
        buttonBox.setLayoutX(primaryStage.getWidth() / 4);
        buttonBox.setLayoutY(500);

        pane.getChildren().addAll(imvBackground, headerBox, formBox, buttonBox);
    }

    public Pane getPane() {
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

