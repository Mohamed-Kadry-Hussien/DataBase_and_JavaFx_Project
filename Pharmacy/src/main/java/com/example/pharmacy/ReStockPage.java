package com.example.pharmacy;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.List;

public class ReStockPage {
    private Pane pane;
    private final int appWidth = 1200;
    private final int appHeight = 650;

    private static final String defaultButtonStyle =
            "-fx-background-color: #4376ec; " +
                    "-fx-text-fill: white; " +
                    "-fx-font-weight: bold; " +
                    "-fx-border-color: white; " +
                    "-fx-border-width: 2px; " +
                    "-fx-border-radius: 20px; " +
                    "-fx-background-radius: 20px;";

    public ReStockPage(Stage primaryStage, Scene scene) {
        pane = new Pane();

        /////////////////////////////// Background Image //////////////////////////////////////////
        Image background = new Image("pills.jpg"); // Replace with your background
        ImageView imv_background = new ImageView(background);
        imv_background.setFitWidth(1200);
        imv_background.setFitHeight(600);
        imv_background.setPreserveRatio(false);
        imv_background.setSmooth(true);
        imv_background.setCache(true);

        //////////////////////////////// Header Section ///////////////////////////////////////////
        Label headerLabel = new Label("Items To Re-Stock");
        headerLabel.setFont(Font.font("Arial", 35));
        headerLabel.setStyle("-fx-text-fill: black; -fx-font-weight: strong;");
        headerLabel.setAlignment(Pos.CENTER);
        headerLabel.setLayoutX(primaryStage.getWidth() / 3.5);
        headerLabel.setLayoutY(50);

        /////////////////////////////// Table for Stock Items /////////////////////////////////////
        VBox vbox2 = new VBox(5);
        vbox2.setAlignment(Pos.CENTER);
        vbox2.setPadding(new Insets(20));
        vbox2.setStyle(
                //  "-fx-background-color: #4376ec;" +
                //  "-fx-background-radius: 20px;" +
                "-fx-border-color: white;" +
                        "-fx-border-radius: 20px;");
        vbox2.setLayoutX(primaryStage.getWidth() / 7);
        vbox2.setLayoutY(primaryStage.getHeight() / 5);

        // Initialize StockTable and TableView
        ObservableList<ReStockTable.Order> stockOrders = FXCollections.observableArrayList(); // Placeholder for data
        ReStockTable stockTable = new ReStockTable();
        vbox2.getChildren().add(stockTable.createStockTable(stockOrders));

        ///////////////////////////////// Populate Table Data /////////////////////////////////////
        SearchFormDB search = new SearchFormDB();
        List<RestockForm> medicines = search.MedicineNeedRestock();

        for (RestockForm medicine : medicines) {
            System.out.println(medicine.toString());
            stockOrders.add(new ReStockTable.Order(
                    medicine.getMedicineId(),
                    medicine.getMedicineName(),
                    medicine.getQuantity(),
                    medicine.getSupplier_name(),
                    medicine.getSupplier_phone()
            ));
        }

        ///////////////////////// Back Button /////////////////////////////////////////////////////
        HBox back_btn = new HBox(10);
        back_btn.setAlignment(Pos.CENTER);
        back_btn.setLayoutX(400);
        back_btn.setLayoutY(500);

        Button backButton = createStyledButton("Back", "#FF9800");
        backButton.setOnAction(e -> {
            primaryStage.setScene(scene); // Navigate back to the main scene
            Button searchMedicineBtn = (Button) scene.lookup("#stockBtn"); // Find the button in the main scene
            if (searchMedicineBtn != null) {
                searchMedicineBtn.setStyle(defaultButtonStyle); // Reset to the original style
            }
        });
        back_btn.getChildren().addAll(backButton);

        /////////////////////////// Pane //////////////////////////////////////////////////////////
        pane.getChildren().addAll(imv_background, headerLabel, vbox2, back_btn);
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
    public Pane getPane () {
        return pane;
    }
}

