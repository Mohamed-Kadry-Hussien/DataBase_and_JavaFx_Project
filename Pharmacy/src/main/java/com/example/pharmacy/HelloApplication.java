package com.example.pharmacy;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    int appWidth = 1200;
    int appHeight = 600;
    int Btn_height = 70;
    int Btn_width = 250;
    private Pane mainPane;
    private Scene mainScene;
    private Stage primaryStage;
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        mainPane = new Pane();
        mainScene = new Scene(mainPane, appWidth, appHeight);



        // Background Image
        Image background = new Image("background.jpg");
        ImageView imv_background = new ImageView(background);
        imv_background.setFitWidth(appWidth);
        imv_background.setFitHeight(appHeight);
        imv_background.setPreserveRatio(false); // Stretch to fit

        // Buttons
        Button addMedicineBtn = new Button("Add Medicine");
        styleButton(addMedicineBtn);

        Button saleBtn = new Button("Sale");
        styleButton(saleBtn);

        Button searchMedicineBtn = new Button("Search Medicine");
        styleButton(searchMedicineBtn);

        Button stockBtn = new Button("Re-Stock Meds");
        styleButton(stockBtn);

        Button updateMedicineBtn = new Button("Update Meds Data");
        styleButton(updateMedicineBtn);

        //handle buttons
        addMedicineBtn.setOnMouseEntered(event -> {
            addMedicineBtn.setPrefHeight(Btn_height-10);
            addMedicineBtn.setPrefWidth(Btn_width-40);
        });
        addMedicineBtn.setOnMouseExited(event -> {
            addMedicineBtn.setPrefHeight(Btn_height);
            addMedicineBtn.setPrefWidth(Btn_width );
        });
        addMedicineBtn.setId("addMedicineBtn");
        addMedicineBtn.setOnMouseClicked(event -> {
            System.out.println("click");
            addMedicineBtn.setStyle(
                    "-fx-background-color: #000;" +
                            "-fx-text-fill: white;       " + // Text color
                            "-fx-font-weight: bold;      " + // Bold text
                            "-fx-border-color: white;    " + // White border
                            "-fx-border-width: 2px;      " + // Border thickness
                            "-fx-border-radius: 20px;    " + // Rounded border
                            "-fx-background-radius: 20px;"
            );
            AddMedicinePage addMedicinePage = new AddMedicinePage(primaryStage, mainScene);
            Scene add_scene =new Scene(addMedicinePage.getPane(), appWidth, appHeight);
            primaryStage.setScene(add_scene);
        });



        ///////////////////////////////////////////////////////////////
        saleBtn.setOnMouseEntered(event -> {
            saleBtn.setPrefHeight(Btn_height-10);
            saleBtn.setPrefWidth(Btn_width-40);
        });
        saleBtn.setOnMouseExited(event -> {
            saleBtn.setPrefHeight(Btn_height);
            saleBtn.setPrefWidth(Btn_width);
        });
        saleBtn.setId("saleBtn");
        saleBtn.setOnMouseClicked(event -> {
            saleBtn.setStyle(
                    "-fx-background-color: #000;" +
                            "-fx-text-fill: white;       " + // Text color
                            "-fx-font-weight: bold;      " + // Bold text
                            "-fx-border-color: white;    " + // White border
                            "-fx-border-width: 2px;      " + // Border thickness
                            "-fx-border-radius: 20px;    " + // Rounded border
                            "-fx-background-radius: 20px;"
            );
            PaymentPage Pay = new PaymentPage(primaryStage, mainScene);
            Scene Pay_scene =new Scene(Pay.getPane(), appWidth, appHeight);
            primaryStage.setScene(Pay_scene);

        });
        ///////////////////////////////////////////////////////////////

        searchMedicineBtn.setOnMouseEntered(event -> {
            searchMedicineBtn.setPrefHeight(Btn_height-10);
            searchMedicineBtn.setPrefWidth(Btn_width-40);
        });
        searchMedicineBtn.setOnMouseExited(event -> {
            searchMedicineBtn.setPrefHeight(Btn_height);
            searchMedicineBtn.setPrefWidth(Btn_width);
        });
        searchMedicineBtn.setId("searchMedicineBtn");
        searchMedicineBtn.setOnMouseClicked(event -> {
            searchMedicineBtn.setStyle(
                    "-fx-background-color: #000;" +
                            "-fx-text-fill: white;       " + // Text color
                            "-fx-font-weight: bold;      " + // Bold text
                            "-fx-border-color: white;    " + // White border
                            "-fx-border-width: 2px;      " + // Border thickness
                            "-fx-border-radius: 20px;    " + // Rounded border
                            "-fx-background-radius: 20px;"
            );

            SearchMedicinePage searchpage = new SearchMedicinePage(primaryStage, mainScene);
            Scene search_scene =new Scene(searchpage.getPane(), appWidth, appHeight);
            primaryStage.setScene(search_scene);
        });

        ///////////////////////////////////////////////////////////////
        stockBtn.setOnMouseEntered(event -> {
            stockBtn.setPrefHeight(Btn_height-10);
            stockBtn.setPrefWidth(Btn_width-40);
        });
        stockBtn.setOnMouseExited(event -> {
            stockBtn.setPrefHeight(Btn_height);
            stockBtn.setPrefWidth(Btn_width);
        });
        stockBtn.setId("stockBtn");
        stockBtn.setOnMouseClicked(event -> {
            stockBtn.setStyle(
                    "-fx-background-color: #000;" +
                            "-fx-text-fill: white;       " + // Text color
                            "-fx-font-weight: bold;      " + // Bold text
                            "-fx-border-color: white;    " + // White border
                            "-fx-border-width: 2px;      " + // Border thickness
                            "-fx-border-radius: 20px;    " + // Rounded border
                            "-fx-background-radius: 20px;"
            );
            ReStockPage Pay = new ReStockPage(primaryStage, mainScene);
            Scene Pay_scene =new Scene(Pay.getPane(), appWidth, appHeight);
            primaryStage.setScene(Pay_scene);

        });
        ///////////////////////////////////////////////////////////////
        updateMedicineBtn.setOnMouseEntered(event -> {
            updateMedicineBtn.setPrefHeight(Btn_height-10);
            updateMedicineBtn.setPrefWidth(Btn_width-40);
        });
        updateMedicineBtn.setOnMouseExited(event -> {
            updateMedicineBtn.setPrefHeight(Btn_height);
            updateMedicineBtn.setPrefWidth(Btn_width);
        });
        updateMedicineBtn.setId("updateMedicineBtn");
        updateMedicineBtn.setOnMouseClicked(event -> {
            updateMedicineBtn.setStyle(
                    "-fx-background-color: #000;" +
                            "-fx-text-fill: white;       " + // Text color
                            "-fx-font-weight: bold;      " + // Bold text
                            "-fx-border-color: white;    " + // White border
                            "-fx-border-width: 2px;      " + // Border thickness
                            "-fx-border-radius: 20px;    " + // Rounded border
                            "-fx-background-radius: 20px;"
            );
            UpdateMedsPage Pay = new UpdateMedsPage(primaryStage, mainScene);
            Scene Pay_scene =new Scene(Pay.getPane(), appWidth, appHeight);
            primaryStage.setScene(Pay_scene);

        });
        ///////////////////////////////////////////////////////////////

        // VBox to hold buttons
        VBox buttonBox = new VBox(20); // Spacing between buttons
        buttonBox.setAlignment(Pos.CENTER); // Center align the buttons
        buttonBox.setLayoutX(150); // Position VBox horizontally
        buttonBox.setLayoutY(90); // Position VBox vertically
        buttonBox.getChildren().addAll(addMedicineBtn, saleBtn, searchMedicineBtn,stockBtn,updateMedicineBtn);

        // Pane and Scene

        mainPane.getChildren().addAll(imv_background, buttonBox);



        primaryStage.setTitle("Pharmacy Management System");
        primaryStage.setResizable(false);
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    // Method to style buttons
    private void styleButton(Button button) {
        button.setStyle("-fx-background-color: #4376ec; " + // Button background
                "-fx-text-fill: white; " + // Text color
                "-fx-font-weight: bold; " + // Bold text
                "-fx-border-color: white; " + // White border
                "-fx-border-width: 2px; " + // Border thickness
                "-fx-border-radius: 20px; " + // Rounded border
                "-fx-background-radius: 20px;"); // Matching background radius
        button.setPrefHeight(Btn_height);
        button.setPrefWidth(Btn_width);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
