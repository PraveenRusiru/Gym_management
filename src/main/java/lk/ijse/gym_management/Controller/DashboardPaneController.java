package lk.ijse.gym_management.Controller;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DashboardPaneController implements Initializable {

    @FXML
    private AreaChart<?, ?> areaChart;

    @FXML
    private Label enddateLbl;

    @FXML
    private Label machineCountLbl;

    @FXML
    private Pane mainPane;

    @FXML
    private Label mealCountLbl;

    @FXML
    private Label memberCountLbl;

    @FXML
    private Label memberIdLbl;

    @FXML
    private Label memberIdLbl1;

    @FXML
    private Label memberTypeLbl;

    @FXML
    private PieChart pieChart;

    @FXML
    private VBox tableBox;

    @FXML
    private Label workoutCountLbl;

    public void setDataAreachart(){
        XYChart.Series series = new XYChart.Series();

        series.getData().add(new XYChart.Data("Jan",20));
        series.getData().add(new XYChart.Data("Feb",30));
        series.getData().add(new XYChart.Data("Mar",10));

        XYChart.Series series1 = new XYChart.Series();

        series1.getData().add(new XYChart.Data("Jan",23));
        series1.getData().add(new XYChart.Data("Feb",56));
        series1.getData().add(new XYChart.Data("Mar",14));

        areaChart.getData().addAll(series);
    }

    private void setDataPieChart(){
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Suppliements",47.3),
                new PieChart.Data("Memberships",28.2),
                new PieChart.Data("Shedules",25.5)
        );

        pieChartData.forEach(data ->
                data.nameProperty().bind(
                        Bindings.concat(
                                data.getName(),"Amount : ",data.pieValueProperty()
                        )));

        pieChart.getData().addAll(pieChartData);

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        setDataPieChart();



    }
}
