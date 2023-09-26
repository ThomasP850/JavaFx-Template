package data.visualization.workshop.javafxtemplate.types;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.Map;

public class TimeSeriesDailyResponseData {
    @SerializedName("Meta Data")
    private MetaData metaData;

    @SerializedName("Time Series (Daily)")
    private Map<Date, DataPoint> timeSeriesDaily;

    public MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }

    public Map<Date, DataPoint> getTimeSeriesDaily() {
        return timeSeriesDaily;
    }

    public void setTimeSeriesDaily(Map<Date, DataPoint> timeSeriesDaily) {
        this.timeSeriesDaily = timeSeriesDaily;
    }

    public static class MetaData {
        @SerializedName("1. Information")
        private String information;

        @SerializedName("2. Symbol")
        private String symbol;

        @SerializedName("3. Last Refreshed")
        private String lastRefresh;

        @SerializedName("4. Output Size")
        private String outputSize;

        @SerializedName("5. Time Zone")
        private String timeZone;

        public String getInformation() {
            return information;
        }

        public void setInformation(String information) {
            this.information = information;
        }

        public String getSymbol() {
            return symbol;
        }

        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }

        public String getLastRefresh() {
            return lastRefresh;
        }

        public void setLastRefresh(String lastRefresh) {
            this.lastRefresh = lastRefresh;
        }

        public String getOutputSize() {
            return outputSize;
        }

        public void setOutputSize(String outputSize) {
            this.outputSize = outputSize;
        }

        public String getTimeZone() {
            return timeZone;
        }

        public void setTimeZone(String timeZone) {
            this.timeZone = timeZone;
        }
    }

    public static class DataPoint {
        @SerializedName("1. open")
        private double open;

        @SerializedName("2. high")
        private double high;

        @SerializedName("3. low")
        private double low;

        @SerializedName("4. close")
        private double close;

        @SerializedName("5. volume")
        private double volume;

        public double getOpen() {
            return open;
        }

        public void setOpen(double open) {
            this.open = open;
        }

        public double getHigh() {
            return high;
        }

        public void setHigh(double high) {
            this.high = high;
        }

        public double getLow() {
            return low;
        }

        public void setLow(double low) {
            this.low = low;
        }

        public double getClose() {
            return close;
        }

        public void setClose(double close) {
            this.close = close;
        }

        public double getVolume() {
            return volume;
        }

        public void setVolume(double volume) {
            this.volume = volume;
        }
    }


}
