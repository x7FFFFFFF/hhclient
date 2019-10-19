package org.noname;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("application.properties")
public class Launcher extends AbstractLauncher {

    @Value("${title}")//
    private String windowTitle;

    @View("main")
    private Parent main;

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void init() {
        //context = new AnnotationConfigApplicationContext("org.noname");
    }

    @Override
    public void start(Stage stage) throws Exception {
        super.start(stage);
        stage.setTitle(windowTitle);
        stage.setScene(new Scene(main));
        stage.setResizable(true);
        stage.centerOnScreen();
        stage.show();

    }

}