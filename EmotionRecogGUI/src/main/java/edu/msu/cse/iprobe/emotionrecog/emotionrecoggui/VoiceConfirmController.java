package edu.msu.cse.iprobe.emotionrecog.emotionrecoggui;

import java.io.IOException;
import javafx.fxml.FXML;

public class VoiceConfirmController {

    @FXML
    private void switchToReport() throws IOException {
        App.setReport();
    }
    
    @FXML
    private void switchToRecording() throws IOException {
        App.setRoot("voice_recording");
    }
}