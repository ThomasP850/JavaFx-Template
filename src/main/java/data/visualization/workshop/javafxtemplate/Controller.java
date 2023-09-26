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
    private TimeSeriesDailyResponseData data;

    public void sendTimeSeriesData(TimeSeriesDailyResponseData data) {
        this.data = data;
//        outputLabel.setText(data.getTimeSeriesDaily().values().toArray(new TimeSeriesDailyResponseData.DataPoint[data.getTimeSeriesDaily().size()])[0].getClose() + "");
        updateChart();
    }

    private void updateChart() {
        lineChart.setTitle(data.getMetaData().getSymbol() + " Daily Closing Prices");

        Calendar monthAgo = new GregorianCalendar();
        monthAgo.set(Calendar.DAY_OF_MONTH, 1);

        XYChart.Series dataPoints = new XYChart.Series();

        double minVal = data.getTimeSeriesDaily().values().stream().map(e -> e.getClose()).min(Double::compare).get();
        double maxVal = data.getTimeSeriesDaily().values().stream().map(e -> e.getClose()).max(Double::compare).get();

        yAxis.setAutoRanging(false);
        yAxis.setLowerBound(minVal);
        yAxis.setUpperBound(maxVal);

        data.getTimeSeriesDaily().forEach((Date day, TimeSeriesDailyResponseData.DataPoint dPoint) -> {
            int elapsedDays = (int)ChronoUnit.DAYS.between(monthAgo.toInstant(), day.toInstant());
            // Exlude days that are before this month.
            if(elapsedDays >= 0) {
                double closeVal = dPoint.getClose();
                dataPoints.getData().add(new XYChart.Data(elapsedDays, closeVal));
            }
        });

        lineChart.getData().removeAll();
        lineChart.getData().add(dataPoints);
    }



}