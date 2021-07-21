package edu.msu.cse.iprobe.emotionrecog.emotionrecoggui;

import java.io.IOException;
import javafx.fxml.FXML;

public class VoiceRecordingController {

    @FXML
    private void switchToConfirm() throws IOException {
        App.setRoot("voice_confirm");
    }
    
    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}