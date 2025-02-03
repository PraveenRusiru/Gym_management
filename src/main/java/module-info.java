module lk.ijse.gym_management {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;
    requires java.naming;
    requires java.sql;
    requires lombok;
    requires java.desktop;
    requires mysql.connector.j;
    requires java.mail;
    requires net.sf.jasperreports.core;

    opens lk.ijse.gym_management to javafx.fxml;
    exports lk.ijse.gym_management;
    exports lk.ijse.gym_management.Controller;
    opens lk.ijse.gym_management.Controller to javafx.fxml;
}