package edu.msu.cse.iprobe.emotionrecog.emotionrecoggui;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ReportController {

    @FXML
    private Label emotionLabel;
    
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
    
}