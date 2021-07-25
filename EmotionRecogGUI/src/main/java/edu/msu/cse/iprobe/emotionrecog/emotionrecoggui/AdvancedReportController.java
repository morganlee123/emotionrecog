package edu.msu.cse.iprobe.emotionrecog.emotionrecoggui;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ValueAxis;
import javafx.scene.chart.XYChart;

public class AdvancedReportController {
/*
    @FXML
    private BarChart<String, Float> barChart;
    
    @FXML
    private CategoryAxis xAxis;
    
    @FXML
    private NumberAxis yAxis;
    
    @FXML
    private void initialize()
    {
        // Create axes
        xAxis = new CategoryAxis();
        yAxis = new NumberAxis(0.1, 2, 0.1);
        yAxis.setLabel("Ratio of emotion predicted");
        xAxis.setLabel("Emotion Labels");
        
        // Initialize barChart
        barChart = new BarChart<String, Float>(xAxis, yAxis);
        
        
        // Add data
        XYChart.Series<String, Float> timestamp = new XYChart.Series<>();
        timestamp.setName("test");
        timestamp.getData().add(new XYChart.Data("100", 100));
        timestamp.getData().add(new XYChart.Data("200", 200));
        timestamp.getData().add(new XYChart.Data("300", 300));
        
        
        
        // Add series and show legend
        barChart.getData().addAll(timestamp);
        barChart.setLegendSide(Side.RIGHT);
        
        
        
    }
    */
    @FXML
    private void goBackToReport() throws IOException {
        App.setReport();
    }
}