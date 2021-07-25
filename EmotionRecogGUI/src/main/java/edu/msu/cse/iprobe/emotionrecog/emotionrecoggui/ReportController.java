package edu.msu.cse.iprobe.emotionrecog.emotionrecoggui;

import java.io.IOException;
import java.util.ArrayList;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.util.Duration;

/*
* Big TODOS
* 1. Fix the wavesurfer.js in the webview so the actual waveform graphic shows up. Reloading the page helps, also fix CORS file retrieval
* 2. Show results in the advanced view as a bar graph
* 3. Get the voice recorder interface working
* 4. Set up confirmation wavesurfer screen, and cache file locally
*/


public class ReportController {

    @FXML
    private Label emotionLabel;
    
    @FXML
    private ImageView emotionGraphic;
    
    @FXML
    private WebView waveform;

    private int time = -1;
    
    private final int SEGMENT_LENGTH = 3;
    
    private ArrayList<EmotionState> mEmotionStates;
    
    @FXML
    private void initialize()
    {
        WebEngine engine = waveform.getEngine();
        
        engine.setJavaScriptEnabled(true);
        engine.load("http://localhost:8000/");
        
        engine.reload();
      
        
        Timeline backgroundTimeCheck = new Timeline(
                 new KeyFrame(Duration.seconds(SEGMENT_LENGTH), 
                 new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Double response = (Double) engine.executeScript("retrieveTime()");
                //System.out.println("Time response:" + response);
                time = (int) Math.floor(response);
                System.out.println(time);
                if(time >= 0)
                {
                    for (EmotionState e : mEmotionStates)
                    {
                        if(e.getTimestamp() == time)
                        {
                            setEmotionLabel(e.getTopPrediction());
                            setEmotionGraphic(e.getTopPrediction());
                        }
                    }
                }
                
                
                
            }
        }));
        backgroundTimeCheck.setCycleCount(Timeline.INDEFINITE);
        backgroundTimeCheck.play();

    }
    
    @FXML
    private void switchToAdvanced() throws IOException {
        App.setRoot("advanced_view");
    }
    
    @FXML
    private void switchToPrimary() throws IOException {
        App.setPrimary();
    }
    
    public void setEmotionLabel(String emotion){
        emotionLabel.setText(emotion);
    }
    
    public void setEmotionGraphic(String emotion){
        emotionGraphic.setImage(new Image(App.class.getResource("images/" + emotion.toLowerCase() + ".png").toString()));
    }
    
    public void giveEmotionStates(ArrayList<EmotionState> emotionStates)
    {
        mEmotionStates = emotionStates;
    }
}