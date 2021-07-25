module edu.msu.cse.iprobe.emotionrecog.emotionrecoggui {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires javafx.media;
    requires javafx.graphics;
    
    opens edu.msu.cse.iprobe.emotionrecog.emotionrecoggui to javafx.fxml;
    exports edu.msu.cse.iprobe.emotionrecog.emotionrecoggui;
}
