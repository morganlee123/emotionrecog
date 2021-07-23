package edu.msu.cse.iprobe.emotionrecog.emotionrecoggui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static Stage mStage;
    
    private static Parent primaryControllerLoaded;
    private static Parent reportControllerLoaded;
    
    @Override
    public void start(Stage stage) throws IOException {
        mStage = stage;
        
        //
        FXMLLoader primaryLoader = new FXMLLoader(App.class.getResource("primary.fxml"));
        FXMLLoader reportControllerLoader = new FXMLLoader(App.class.getResource("report.fxml"));
        //
       
        Parent loadedPrimary = primaryLoader.load();
        scene = new Scene(loadedPrimary, 640, 480);
        
        // Initialize the report page state
        Parent loadedReport = reportControllerLoader.load();
        
        PrimaryController pc = primaryLoader.getController();
        ReportController rc = reportControllerLoader.getController();
        
        // Pass the reportcontroller to the primary screen so that it may set information
        pc.setReportController(rc);
        this.reportControllerLoaded = loadedReport;
        this.primaryControllerLoaded = loadedPrimary;
        
        
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    static void setPrimary() throws IOException {
        scene.setRoot(primaryControllerLoaded);
    }
    
    /*
    * Report needs its own set function since it's initialized in this main class. 
    * This is so that we can maintain the state of the emotion
    */
    static void setReport() throws IOException {
        scene.setRoot(reportControllerLoaded);
    }
    
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
    
    public static Stage getStage(){
        return mStage;
    }

}