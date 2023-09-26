module data.visualization.workshop.javafxtemplate {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires java.net.http;


    opens data.visualization.workshop.javafxtemplate to javafx.fxml, com.google.gson;
    opens data.visualization.workshop.javafxtemplate.types to javafx.fxml, com.google.gson;

    exports data.visualization.workshop.javafxtemplate;
    exports data.visualization.workshop.javafxtemplate.types;
}