package edu.msu.cse.iprobe.emotionrecog.emotionrecoggui;

import java.io.File;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ReportController {

    @FXML
    private Label emotionLabel;
    
    @FXML
    private ImageView emotionGraphic;
    
    @FXML
    private void switchToAdvanced() throws IOException {
        App.setRoot("advanced_view");
    }
    
    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
    
    public void setEmotionLabel(String emotion){
        emotionLabel.setText(emotion);
    }
    
    public void setEmotionGraphic(String emotion){
        emotionGraphic.setImage(new Image(App.class.getResource("images/" + emotion.toLowerCase() + ".png").toString()));
    }
    
}