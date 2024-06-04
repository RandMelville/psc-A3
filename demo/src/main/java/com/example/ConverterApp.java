package com.example;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ConverterApp extends Application {

    @Override //método que configura a GUI
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Conversor monetário");

        // Layout
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        // Componentes
        Label amountLabel = new Label("Valor:");
        GridPane.setConstraints(amountLabel, 0, 0);

        TextField amountInput = new TextField();
        GridPane.setConstraints(amountInput, 1, 0);

        Label fromCurrencyLabel = new Label("De:");
        GridPane.setConstraints(fromCurrencyLabel, 0, 1);

        ComboBox<String> fromCurrency = new ComboBox<>();
        fromCurrency.getItems().addAll("USD", "EUR", "GBP", "BRL"); 
        GridPane.setConstraints(fromCurrency, 1, 1);

        Label toCurrencyLabel = new Label("Para:");
        GridPane.setConstraints(toCurrencyLabel, 0, 2);

        ComboBox<String> toCurrency = new ComboBox<>();
        toCurrency.getItems().addAll("USD", "EUR", "GBP", "BRL");
        GridPane.setConstraints(toCurrency, 1, 2);

        Button convertButton = new Button("Converter");
        GridPane.setConstraints(convertButton, 1, 3);

        Label resultLabel = new Label();
        GridPane.setConstraints(resultLabel, 1, 4);

        // Add componentes ao layout
        grid.getChildren().addAll(amountLabel, amountInput, fromCurrencyLabel, fromCurrency, toCurrencyLabel, toCurrency, convertButton, resultLabel);

        // Manipulação de eventos
        convertButton.setOnAction(e -> {
            String from = fromCurrency.getValue();
            String to = toCurrency.getValue();
            double amount = Double.parseDouble(amountInput.getText());
            double rate = getExchangeRate(from, to);
            double result = amount * rate;
            resultLabel.setText(String.format("%.2f %s", result, to));
        });

        // Scene
        Scene scene = new Scene(grid, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    //obter a taxa de câmbio:
    private double getExchangeRate(String from, String to) {
        Double rate = getExchangeRateFromDatabase(from, to);
        if (rate != null) {
            return rate;
        } else {
            rate = getExchangeRateFromApi(from, to);
            saveExchangeRate(from, to, rate);
            return rate;
        }
    }

    //conectar a API
    private double getExchangeRateFromApi(String from, String to) {
        String apiKey = "ad3f76ab7ca9d1d46aa5099a"; 
        String url = String.format("https://v6.exchangerate-api.com/v6/%s/latest/%s", apiKey, from);
        
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(url);
            HttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();
            
            if (entity != null) {
                String result = EntityUtils.toString(entity);
                JsonObject json = JsonParser.parseString(result).getAsJsonObject();
                return json.getAsJsonObject("conversion_rates").get(to).getAsDouble();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1.0;
    }

    //Salva a taxa de câmbio no banco de dados
    private void saveExchangeRate(String from, String to, double rate) {
        String query = "INSERT INTO exchange_rates (base_currency, target_currency, rate) VALUES (?, ?, ?)";

        try (Connection conn = connectDatabase();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, from);
            pstmt.setString(2, to);
            pstmt.setDouble(3, rate);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //obtem a taxa de câmbio do banco de dados local
    private Double getExchangeRateFromDatabase(String from, String to) {
        String query = "SELECT rate FROM exchange_rates WHERE base_currency = ? AND target_currency = ? ORDER BY last_updated DESC LIMIT 1";

        try (Connection conn = connectDatabase();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, from);
            pstmt.setString(2, to);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return rs.getDouble("rate");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //Cria a conexão com o banco de dados
    private Connection connectDatabase() {
        String url = "jdbc:mysql://localhost:3306/currency_converter";
        String user = "root";
        String password = "May@2005"; 

        try {
            return DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
