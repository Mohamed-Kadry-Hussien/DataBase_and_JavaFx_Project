package com.example.pharmacy;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class AddMedicinePage {
    private Pane pane;
    private Image background;
    private ImageView imv_background;
    private GridPane gridPane;

    private Label medicineIdLabel, unitPriceLabel, medicineNameLabel, sellingPriceLabel, quantityLabel, manufacturerNameLabel, supplierNameLabel;
    private TextField medicineIdField, medicineNameField, unitPriceField, sellingPriceField, quantityField;
    private ComboBox<String> ManufacturerName, SupplierName;
    private Button addMedicineBtn, newSupplierBtn, newManufacturerBtn, backButton;

    private static final String defaultButtonStyle =
            "-fx-background-color: #4376ec; " +
                    "-fx-text-fill: white; " +
                    "-fx-font-weight: bold; " +
                    "-fx-border-color: white; " +
                    "-fx-border-width: 2px; " +
                    "-fx-border-radius: 20px; " +
                    "-fx-background-radius: 20px;";
    public AddMedicinePage(Stage primaryStage, Scene scene) {
        pane = new Pane();


        // Background Image
        background = new Image("pills.jpg"); // Replace with your background
        imv_background = new ImageView(background);
        imv_background.setFitWidth(1200);
        imv_background.setFitHeight(600);
        imv_background.setPreserveRatio(false);

        // Header Label
        Label headerLabel = new Label("Medicine Information");
        headerLabel.setFont(Font.font("Arial", 28));
        headerLabel.setStyle("-fx-text-fill: black;  -fx-font-weight: bold;");
        headerLabel.setAlignment(Pos.CENTER);

        VBox headerBox = new VBox(headerLabel);
        headerBox.setAlignment(Pos.CENTER);
        headerBox.setPadding(new Insets(10));
        headerBox.setLayoutX(primaryStage.getWidth() / 6);
        headerBox.setLayoutY(20);
        headerBox.setPrefWidth(450);

        // GridPane Layout
        gridPane = new GridPane();
        gridPane.setHgap(20);
        gridPane.setVgap(15);
        gridPane.setPadding(new Insets(30, 50, 30, 50));
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setStyle("-fx-background-color: rgba(255, 255, 255, 0.8); -fx-background-radius: 15;");

        // Form Labels and Fields
        Font labelFont = Font.font("Arial", FontWeight.BOLD, 14);

        medicineIdLabel = new Label("Medicine ID:");
        medicineIdLabel.setFont(labelFont);
        medicineIdField = new TextField();
        medicineIdField.setPromptText("Enter Medicine ID");

        medicineNameLabel = new Label("Medicine Name:");
        medicineNameLabel.setFont(labelFont);
        medicineNameField = new TextField();
        medicineNameField.setPromptText("Enter Medicine Name");

        unitPriceLabel = new Label("Unit Price:");
        unitPriceLabel.setFont(labelFont);
        unitPriceField = new TextField();
        unitPriceField.setPromptText("Enter Unit Price");

        sellingPriceLabel = new Label("Selling Price:");
        sellingPriceLabel.setFont(labelFont);
        sellingPriceField = new TextField();
        sellingPriceField.setPromptText("Enter Selling Price");

        quantityLabel = new Label("Quantity:");
        quantityLabel.setFont(labelFont);
        quantityField = new TextField();
        quantityField.setPromptText("Enter Quantity");

        manufacturerNameLabel = new Label("Manufacturer Name:");
        manufacturerNameLabel.setFont(labelFont);
        ManufacturerName = new ComboBox<>();
        StockDB stockDB = new StockDB();
        ManufacturerName.getItems().addAll(stockDB.getAllManufactureNames());
        ManufacturerName.setPromptText("Select Manufacturer");

        supplierNameLabel = new Label("Supplier Name:");
        supplierNameLabel.setFont(labelFont);
        SupplierName = new ComboBox<>();
        SupplierName.getItems().addAll(stockDB.getAllSupplierNames());
        SupplierName.setPromptText("Select Supplier");

        // Buttons
        addMedicineBtn = createStyledButton("Add Medicine","blue");

        addMedicineBtn.setOnAction(event -> handleAddMedicine(SupplierName, ManufacturerName));

        newSupplierBtn = createStyledButton("New Supplier"," #386080");

        newSupplierBtn.setOnAction(event -> {
            SupplierPage supplierPage = new SupplierPage(primaryStage, scene);
            primaryStage.setScene(new Scene(SupplierPage.getPane()));
        });

        newManufacturerBtn = createStyledButton("New Manufacturer","#6c8ca4");

        newManufacturerBtn.setOnAction(event -> {
            ManufacturerPage manufacturerPage = new ManufacturerPage(primaryStage, scene);
            primaryStage.setScene(new Scene(ManufacturerPage.getPane()));
        });

        backButton = createStyledButton("Back", "#FF9800");
        backButton.setOnAction(e -> {
            primaryStage.setScene(scene);
            Button searchMedicineBtn = (Button) scene.lookup("#addMedicineBtn");
            if (searchMedicineBtn != null) searchMedicineBtn.setStyle(defaultButtonStyle);
        });

        // Adding Components to GridPane
        gridPane.add(medicineIdLabel, 0, 0);
        gridPane.add(medicineIdField, 1, 0);

        gridPane.add(medicineNameLabel, 0, 1);
        gridPane.add(medicineNameField, 1, 1);

        gridPane.add(unitPriceLabel, 0, 2);
        gridPane.add(unitPriceField, 1, 2);

        gridPane.add(sellingPriceLabel, 0, 3);
        gridPane.add(sellingPriceField, 1, 3);

        gridPane.add(quantityLabel, 0, 4);
        gridPane.add(quantityField, 1, 4);

        gridPane.add(manufacturerNameLabel, 0, 5);
        gridPane.add(ManufacturerName, 1, 5);

        gridPane.add(supplierNameLabel, 0, 6);
        gridPane.add(SupplierName, 1, 6);

        gridPane.add(addMedicineBtn, 0, 7);
        gridPane.add(newSupplierBtn, 1, 7);
        gridPane.add(newManufacturerBtn, 0, 8);
        gridPane.add(backButton, 1, 8);
        gridPane.setLayoutX(primaryStage.getWidth() / 7);
        gridPane.setLayoutY(primaryStage.getHeight() / 6);

        // Adding to Pane
        pane.getChildren().addAll(imv_background, headerBox,gridPane);
    }

    public Pane getPane() {
        return pane;
    }

    private void handleAddMedicine(ComboBox<String> suppliers, ComboBox<String> manufacturers) {
        try {
            String medicineIdText = medicineIdField.getText();
            String medicineName = medicineNameField.getText();
            String quantityText = quantityField.getText();
            String sellingPriceText = sellingPriceField.getText();
            String unitPriceText = unitPriceField.getText();

            if (medicineIdText.isEmpty() || medicineName.isEmpty() || quantityText.isEmpty() || sellingPriceText.isEmpty() || unitPriceText.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Input Error", "Please fill in all fields.");
            } else {
                int medicineId = Integer.parseInt(medicineIdText);
                int quantity = Integer.parseInt(quantityText);
                float sellingPrice = Float.parseFloat(sellingPriceText);
                float unitPrice = Float.parseFloat(unitPriceText);
                String supplierName = suppliers.getValue();
                String manufacturerName = manufacturers.getValue();

                Stock stock = new Stock(medicineId, medicineName, quantity, sellingPrice, unitPrice, manufacturerName, supplierName);
                StockDB db = new StockDB(stock);
                db.AddMedicine();

                clearAllFields();
                showAlert(Alert.AlertType.INFORMATION, "Success", "Medicine added successfully!");
            }
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "An error occurred. Check your input values.");
        }
    }

    private void clearAllFields() {
        medicineIdField.clear();
        medicineNameField.clear();
        unitPriceField.clear();
        sellingPriceField.clear();
        quantityField.clear();
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
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



