package data.visualization.workshop.javafxtemplate.types;

import com.google.gson.annotations.SerializedName;

public class TimeSeriesDailyResponse {
    @SerializedName("Meta Data")
    private MetaData metaData;

    public static class MetaData {
        @SerializedName("Information")
        private String information;

        @SerializedName("Symbol")
        private String symbol;

        @SerializedName("")
        private String information;
    }
}
