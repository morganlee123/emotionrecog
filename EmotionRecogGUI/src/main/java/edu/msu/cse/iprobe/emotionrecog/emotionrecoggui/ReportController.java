package edu.msu.cse.iprobe.emotionrecog.emotionrecoggui;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.GeneralSecurityException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;


/*
* Big TODOS
* 1. Fix the wavesurfer.js in the webview so the actual waveform graphic shows up. Might be a problem with scaling idk. CHeck options tab on wavesurfer website
* 2. Show results in the advanced view as a bar graph
* 3. Constantly check the wavesurfer timestamp and change graphics to correlate to the EmotionState in the list at the specified time if within that range.
* 4. Get the voice recorder interface working
* 5. Set up confirmation wavesurfer screen, and cache file locally
* 6. Pray
*/
public class ReportController {

    @FXML
    private Label emotionLabel;
    
    @FXML
    private ImageView emotionGraphic;
    
    @FXML
    private WebView waveform;
    
    @FXML
    private void initialize()
    {
        WebEngine engine = waveform.getEngine();
        File file = new File("/Users/morgan/Work/MSU/IPROBE/research/EmotionRecogGUIDev/EmotionRecogGUI/src/main/index.html");

        engine.load(file.toURI().toString());
        
        // TODO: Write a monitoring function that checks for the timestamp using the wavesurfer.js getCurrentTime() function in the wavehelper.js
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
    
}