package org.noname.view;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.noname.entities.EmployeeSimple;

public class EmployeeDetailsStage extends Stage {
    public EmployeeDetailsStage(Scene scene, EmployeeSimple item) {
        this.initModality(Modality.WINDOW_MODAL);
        this.centerOnScreen();
        this.setScene(scene);
        setTitle("Детали: " + item.getResumeLink());
        /*Label  jobNameLabel = (Label) scene.lookup("#jobName");
        jobNameLabel.setText(item.getResumeLink().getJobTitle());*/
        new SceneBinding(item, scene).bind();

    }
}
