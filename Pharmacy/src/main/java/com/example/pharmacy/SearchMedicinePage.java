package com.example.pharmacy;


import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class SearchMedicinePage {

    private static final double labelWidth = 300;
    private static final double labelHeight = 30;
    private static final String defaultButtonStyle =
            "-fx-background-color: #4376ec; " +
                    "-fx-text-fill: white; " +
                    "-fx-font-weight: bold; " +
                    "-fx-border-color: white; " +
                    "-fx-border-width: 2px; " +
                    "-fx-border-radius: 20px; " +
                    "-fx-background-radius: 20px;";

    private Pane pane = new Pane(); // Declare the Pane

    public SearchMedicinePage(Stage primaryStage, Scene scene) {

        Image background = new Image("search.jpg"); // Replace with your background
        ImageView imv_background = new ImageView(background);
        imv_background.setFitWidth(1200);
        imv_background.setFitHeight(600);
        imv_background.setPreserveRatio(false);
        imv_background.setSmooth(true);
        imv_background.setCache(true);
        pane.getChildren().add(imv_background);

        // Create the title and search bar
        VBox rootBox = new VBox(20); // VBox to organize the components
        rootBox.setAlignment(Pos.CENTER);
        rootBox.setPrefWidth(1200);
        rootBox.setPrefHeight(600);

        // Create the searchBox separately for absolute positioning
        HBox searchBox = new HBox(10);
        searchBox.setAlignment(Pos.CENTER);

        Label titleLabel = new Label("Medicine ID:   ");
        titleLabel.setStyle("-fx-text-fill: black; -fx-font-weight: strong;-fx-font-size: 20px;");
        TextField medicineIDField = new TextField();

        Button searchButton = createStyledButton("Search","#217afa");
        // searchButton.setStyle("-fx-background-color: #4376ec; -fx-text-fill: white; -fx-font-weight: bold;");

        Button backButton = createStyledButton("Back", "#FF9800");
        // backButton.setStyle("-fx-background-color: #4376ec; -fx-text-fill: white; -fx-font-weight: bold;");
        backButton.setOnAction(e -> {
            primaryStage.setScene(scene);
            Button searchMedicineBtn = (Button) scene.lookup("#searchMedicineBtn"); // Find the button in the main scene
            if (searchMedicineBtn != null) {
                searchMedicineBtn.setStyle(defaultButtonStyle); // Reset to the original style
            }
        });
        // Style the search bar components
        medicineIDField.setPrefSize(labelWidth, labelHeight); // Set size

        searchBox.getChildren().addAll(titleLabel, medicineIDField );

        // Set the position of the searchBox in the Pane (move it using setLayoutX and setLayoutY)
        searchBox.setLayoutX(110);
        searchBox.setLayoutY(80);


        pane.getChildren().add(searchBox);


        Pane detailsPane = new Pane();

        VBox detailsBox = new VBox(20);
        detailsBox.setAlignment(Pos.CENTER);
        detailsBox.setPrefWidth(600);
        detailsBox.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5); -fx-padding: 20px; -fx-border-radius: 10px; -fx-background-radius: 10px;");

        // Medicine Name
        HBox idBox = new HBox(10);
        Label medicineNameLabel = new Label("Medicine Name:     ");
        Label idField = new Label();
        styleLabel(medicineNameLabel, idField);
        idBox.getChildren().addAll(medicineNameLabel, idField);

        // Medicine Price
        HBox priceBox = new HBox(10);
        Label priceLabel = new Label("Price:                        ");
        Label priceField = new Label();
        styleLabel(priceLabel, priceField);
        priceBox.getChildren().addAll(priceLabel, priceField);

        // Medicine Quantity in Stock
        HBox quantityBox = new HBox(10);
        Label quantityLabel = new Label("Quantity:                 ");
        Label quantityField = new Label();
        styleLabel(quantityLabel, quantityField);
        quantityBox.getChildren().addAll(quantityLabel, quantityField);

        // Medicine Supplier
        HBox supplierBox = new HBox(10);
        Label supplierLabel = new Label("Supplier:                  ");
        Label supplierField = new Label();
        styleLabel(supplierLabel, supplierField);
        supplierBox.getChildren().addAll(supplierLabel, supplierField);

        // Supplier Phone
        HBox supplierPhoneBox = new HBox(10);
        Label supplierPhoneLabel = new Label("Supplier Phone:       ");
        Label supplierPhoneField = new Label();
        styleLabel(supplierPhoneLabel, supplierPhoneField);
        supplierPhoneBox.getChildren().addAll(supplierPhoneLabel, supplierPhoneField);

        // Manufacturer
        HBox manufacturerBox = new HBox(10);
        Label manufacturerLabel = new Label("Manufacturer:          ");
        Label manufacturerField = new Label();
        styleLabel(manufacturerLabel, manufacturerField);
        manufacturerBox.getChildren().addAll(manufacturerLabel, manufacturerField);

        // Manufacturer Email
        HBox manufacturerEmailBox = new HBox(10);
        Label manufacturerEmailLabel = new Label("Manufacturer Email:");
        Label manufacturerEmailField = new Label();
        styleLabel(manufacturerEmailLabel, manufacturerEmailField);
        manufacturerEmailBox.getChildren().addAll(manufacturerEmailLabel, manufacturerEmailField);

        // Add all components to the detailsBox
        detailsBox.getChildren().addAll(
                idBox, priceBox, quantityBox, supplierBox, supplierPhoneBox, manufacturerBox, manufacturerEmailBox
        );
        //detailsBox.setStyle("-fx-background-color: #4376ec ; -fx-padding: 20px; -fx-border-radius: 10px; -fx-background-radius: 10px;");
        // Set the position of detailsBox in the Pane
        detailsBox.setLayoutX(100);  // Set X position
        detailsBox.setLayoutY(70);  // Set Y position

        // Add detailsBox to the detailsPane
        detailsPane.getChildren().add(detailsBox);
        // Add components to the rootBox
        rootBox.getChildren().addAll(detailsPane  );



        searchButton.setOnAction(e->{
            System.out.println("mouse click");
            int medicineId = Integer.parseInt(medicineIDField.getText());
            SearchFormDB searchFormDB = new SearchFormDB();
            SearchForm form = searchFormDB.fetchMedicineDetailsById(medicineId);
            idField.setText("");
            priceField.setText("");
            quantityField.setText("");
            supplierField.setText("");
            supplierPhoneField.setText("");
            manufacturerField.setText("");
            manufacturerEmailField.setText("");
            if (form != null) {
                idField.setText(form.getMedicineName());
                priceField.setText(form.getSellingPrice() + "");
                quantityField.setText(form.getQuantity()+"");
                supplierField.setText(form.getSupplier_name());
                supplierPhoneField.setText(form.getSupplier_phone());
                manufacturerField.setText(form.getManf_name());
                manufacturerEmailField.setText(form.getManf_mail());
            }
            medicineIDField.clear();
            medicineIDField.requestFocus();
        });


        searchButton.setLayoutX(575);  // X coordinate (from the left)
        searchButton.setLayoutY(80); // Y coordinate (from the top)

        backButton.setLayoutX(650);  // X coordinate (from the left)
        backButton.setLayoutY(80); // Y coordinate (from the top)



        // Add rootBox to the pane
        pane.getChildren().addAll(rootBox , searchButton, backButton);
    }

    // Method to style labels
    private void styleLabel(Label label1, Label label2) {
        label1.setStyle("-fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold;");
        label2.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #000000; -fx-border-radius: 5px; -fx-padding: 5px;");
        label2.setPrefSize(labelWidth, labelHeight); // Set size for labels
    }
    private Button createStyledButton(String text, String color) {
        Button button = new Button(text);
        button.setStyle(
                "-fx-background-color: " + color + ";" +
                        "-fx-text-fill: white;" +
                        "-fx-font-weight: bold;" +
                        "-fx-border-radius: 10px;" +
                        "-fx-background-radius: 10px;");
        button.setEffect(new DropShadow(3, Color.GRAY));
        return button;
    }

    // Getter method for pane
    public Pane getPane() {
        return this.pane;
    }
}