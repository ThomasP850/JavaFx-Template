package data.visualization.workshop.javafxtemplate;

import data.visualization.workshop.javafxtemplate.types.TimeSeriesDailyResponseData;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Controller {
    @FXML
    private Label outputLabel;
    @FXML
    private LineChart<Integer, Double> lineChart;
    @FXML
    private NumberAxis xAxis;
    @FXML
    private NumberAxis yAxis;

    /**
     * Send data to the line chart
     * <p>
     * This clears any previous data that was on the chart.
     * 
     * @param data The data to send
     */
    public void sendTimeSeriesData(TimeSeriesDailyResponseData data) {
        lineChart.setTitle(data.getMetaData().getSymbol() + " Daily Closing Prices");

        Calendar monthAgo = new GregorianCalendar();
        monthAgo.set(Calendar.DAY_OF_MONTH, 1);

        XYChart.Series<Integer, Double> dataPoints = new XYChart.Series<Integer, Double>();

        
        data.getTimeSeriesDaily().forEach((Date day, TimeSeriesDailyResponseData.DataPoint dPoint) -> {
            int elapsedDays = (int)ChronoUnit.DAYS.between(monthAgo.toInstant(), day.toInstant());
            // Exclude days that are before this month.
            if(elapsedDays >= 0) {
                double closeVal = dPoint.getClose();
                dataPoints.getData().add(new XYChart.Data<Integer, Double>(elapsedDays, closeVal));
            }
        });

        yAxis.setAutoRanging(false);
        yAxis.setLowerBound(dataPoints.getData().stream().mapToDouble(d -> d.getYValue()).min().getAsDouble());
        yAxis.setUpperBound(dataPoints.getData().stream().mapToDouble(d -> d.getYValue()).max().getAsDouble());

        lineChart.getData().removeAll(lineChart.getData());
        lineChart.getData().add(dataPoints);
    }
}