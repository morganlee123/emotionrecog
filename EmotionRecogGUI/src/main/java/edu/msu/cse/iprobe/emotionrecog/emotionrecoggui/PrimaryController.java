package edu.msu.cse.iprobe.emotionrecog.emotionrecoggui;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;

public class PrimaryController {
    
    private ReportController mReportController;
    
    
    @FXML
    private String openFile() throws IOException, InterruptedException {
        // TODO: Need to add code to run the python tkinter program
        
        // Select the file from a FileChooser
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File file = fileChooser.showOpenDialog(App.getStage() /* There has to be a better way to do this :\ */);

        // Pass the filename to the python program to open, analyze, and report
        // Must return the array of emotions at their corresponding timestamps (10 second window).
        // E.g {{[.1, .2, .1, .1, .1, .4], [Angry, Sad, Surprise, Neutral, Happy, Fear], '10'}, 
        //      {[.1, .2, .1, .1, .1, .4], [Angry, Sad, Surprise, Neutral, Happy, Fear], '20'},
        //       {etc.}     }
        String filepath = file.getAbsolutePath();
        String pythonPath = "/Users/morgan/miniconda3/bin/python3";
        String command = "/Users/morgan/Work/MSU/IPROBE/research/EmotionRecogGUIDev/EmotionRecogGUI/src/main/analyze_file.py";
        
        Process process = null;
        try{
            //process = Runtime.getRuntime().exec(new String[]{command,filepath});
            process = Runtime.getRuntime().exec(pythonPath + " " + command + " " + filepath);
            //System.out.println(pythonPath + " " + command + " " + filepath );
            
        }catch(Exception e) {
           System.out.println("Exception Raised" + e.toString());
        }
        
        process.waitFor();
        
        InputStream stdout = process.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(stdout,StandardCharsets.UTF_8));
        String line;
        
        // TODO: Parse emotion_predicted into an ArrayList storing {time, [emotion information]}
        // Positional Data for reference:
        // 0 - Angry, 1 - Disgust, 2 - Fear, 3 - Happy, 4 - Neutral, 5 - Sad, 6 - Surprise
        String emotion_predicted = "Unknown";
        
        try{
           while((line = reader.readLine()) != null){
                //System.out.println("stdout: "+ line);
                emotion_predicted = line;
           }
        }catch(IOException e){
              System.out.println("Exception in reading output"+ e.toString());
        }
        
        // Update the emotion shown in the report
        mReportController.setEmotionLabel(emotion_predicted);
        
        //App.setRoot(("report"));
        App.setReport();
        
        return emotion_predicted;
    }
    
    @FXML
    private void openVoiceRecording() throws IOException {
        // TODO: Need to add code to run a program to capture the voice
        
        App.setRoot("voice_recording");
    }
    
    public void setReportController(ReportController rc)
    {
        this.mReportController = rc;
    }
}
