module edu.msu.cse.iprobe.emotionrecog.emotionrecoggui {
    requires javafx.controls;
    requires javafx.fxml;

    opens edu.msu.cse.iprobe.emotionrecog.emotionrecoggui to javafx.fxml;
    exports edu.msu.cse.iprobe.emotionrecog.emotionrecoggui;
}
