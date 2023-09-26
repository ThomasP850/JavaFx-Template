package data.visualization.workshop.javafxtemplate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import data.visualization.workshop.javafxtemplate.types.TimeSeriesDailyResponseData;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;

import java.net.http.HttpRequest;

public class Application extends javafx.application.Application {
    private static final String REQUEST_URL_TEMPLATE = "https://alpha-vantage.p.rapidapi.com/query?function=TIME_SERIES_DAILY&symbol=%s&outputsize=compact&datatype=json";
    private static final String RAPID_API_KEY = "changeme";

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 750);
        stage.setTitle("Finance Data");
        stage.setScene(scene);
        
        try {
            TimeSeriesDailyResponseData data = getDailyFinanceData("TSLA");
            fxmlLoader.<Controller>getController().sendTimeSeriesData(data);
        } catch(Exception ex) {
            System.out.println("!!! Error fetching data !!!");
            ex.printStackTrace();
        }
        
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    /**
     * Fetch daily financial data from the Alpha Vantage API and map it to an object
     *
     * @param symbol Symbol of the stock to read data from
     * @return a Java object representation of the response from the API
     * @throws Exception If there was an issue when fetching data
     */
    public static TimeSeriesDailyResponseData getDailyFinanceData(String symbol) throws Exception {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").setPrettyPrinting().create();

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(String.format(REQUEST_URL_TEMPLATE, symbol)))
            .header("X-RapidAPI-Key", RAPID_API_KEY)
            .header("X-RapidAPI-Host", "alpha-vantage.p.rapidapi.com")
            .method("GET", HttpRequest.BodyPublishers.noBody())
        .build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        TimeSeriesDailyResponseData data = gson.fromJson(response.body(), TimeSeriesDailyResponseData.class);

        return data;
    }
}