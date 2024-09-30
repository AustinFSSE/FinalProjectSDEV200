module com.github.austinfsse.sdev.finalproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires junit;

    // Allow javafx.fxml module to reflectively access your classes in these packages
    opens com.github.austinfsse.sdev200.finalproject to javafx.fxml;

    // You need to open the Clients Controllers package explicitly
    opens com.github.austinfsse.sdev200.finalproject.Controllers.Clients to javafx.fxml;

    // Export packages for other parts of your application to use
    exports com.github.austinfsse.sdev200.finalproject;
    exports com.github.austinfsse.sdev200.finalproject.Controllers;
    exports com.github.austinfsse.sdev200.finalproject.Models;
    exports com.github.austinfsse.sdev200.finalproject.Stages;
}
