package org.untitled.weatherapp;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class WeatherApp extends Application {

    private ComboBox<String> cityCombo;
    private Label cityLabel;
    private Label tempLabel;
    private ImageView weatherImage;

    private Map<String, CityWeather> cityData = new HashMap<>();

    @Override
    public void start(Stage primaryStage) {
        cityData.put("Cairo", new CityWeather("Cairo", 34, "Sunny"));
        cityData.put("Nicosia", new CityWeather("Nicosia", 30, "Rainy"));
        cityData.put("Praia", new CityWeather("Praia", 27, "Stormy"));

        cityCombo = new ComboBox<>();
        cityCombo.getItems().addAll(cityData.keySet());
        cityCombo.setValue("Cairo");
        Button refreshBtn = new Button("Refresh");
        refreshBtn.setOnAction(e -> updateWeather());

        cityLabel = new Label("Cairo");
        cityLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        tempLabel = new Label();
        tempLabel.setStyle("-fx-font-size: 18px;");

        weatherImage = new ImageView();
        weatherImage.setFitHeight(100);
        weatherImage.setFitWidth(100);



        BorderPane root = new BorderPane();
        VBox content  = new VBox(15, cityCombo, refreshBtn, cityLabel, tempLabel, weatherImage);
        content.setAlignment(Pos.CENTER);
        content.setStyle("-fx-padding: 20;");

        MenuBar menuBar = new MenuBar();
        Menu menu = new Menu("Menu");
        MenuItem refreshItem = new MenuItem("Refresh");
        MenuItem closeItem = new MenuItem("Close");
        refreshItem.setOnAction(e -> updateWeather());
        closeItem.setOnAction(e -> primaryStage.close());
        menu.getItems().addAll(refreshItem, closeItem);
        menuBar.getMenus().add(menu);

        root.setTop(menuBar);
        root.setTop(content);

        cityCombo.setOnAction(e -> updateWeather());
        updateWeather();

        Scene scene = new Scene(root, 300, 300);
        primaryStage.setTitle("Weather App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void updateWeather() {
        String selectedCity = cityCombo.getValue();
        CityWeather data = cityData.get(selectedCity);

        if (data != null) {
            cityLabel.setText(data.city);
            tempLabel.setText(data.temp + " °C");
            switch (data.weather.toLowerCase()) {
                case "sunny":
                    weatherImage.setImage(new Image(getClass().getResourceAsStream("/img/sun.png")));
                    break;
                case "rainy":
                    weatherImage.setImage(new Image(getClass().getResourceAsStream("/img/protection.png")));
                    break;
                case "stormy":
                    weatherImage.setImage(new Image(getClass().getResourceAsStream("/img/lightening.png")));
                    break;
                default:
                    weatherImage.setImage(new Image(getClass().getResourceAsStream("/img/sun.png")));
                    break;
            }
        }
    }

    static class CityWeather {
        String city;
        int temp;
        String weather;
        CityWeather(String city, int temp, String weather) {
            this.city = city;
            this.temp = temp;
            this.weather = weather;
        }
    }
}

