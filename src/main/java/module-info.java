module data.visualization.workshop.javafxtemplate {
    requires javafx.controls;
    requires javafx.fxml;


    opens data.visualization.workshop.javafxtemplate to javafx.fxml;
    exports data.visualization.workshop.javafxtemplate;
}