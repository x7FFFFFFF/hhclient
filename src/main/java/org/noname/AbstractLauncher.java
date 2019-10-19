package org.noname;

import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;


public abstract class AbstractLauncher extends Application {
    private GenericApplicationContext context;

    @Override
    public void start(Stage primaryStage) throws Exception {
        context = new AnnotationConfigApplicationContext("org.noname");
        context.getAutowireCapableBeanFactory().autowireBean(this);
    }

    @Override
    public void stop() {
        context.close();
    }
}
