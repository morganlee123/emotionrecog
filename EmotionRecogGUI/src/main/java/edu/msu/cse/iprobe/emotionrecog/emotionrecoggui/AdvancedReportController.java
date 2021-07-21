package edu.msu.cse.iprobe.emotionrecog.emotionrecoggui;

import java.io.IOException;
import javafx.fxml.FXML;

public class AdvancedReportController {

    @FXML
    private void goBackToReport() throws IOException {
        App.setReport();
    }
}