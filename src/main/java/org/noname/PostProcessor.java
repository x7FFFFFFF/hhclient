package org.noname;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.io.IOException;
import java.lang.reflect.Field;

@Component
public class PostProcessor implements InstantiationAwareBeanPostProcessor {
    private final ConfigurableListableBeanFactory configurableBeanFactory;

    @Autowired
    public PostProcessor(ConfigurableListableBeanFactory configurableBeanFactory) {
        this.configurableBeanFactory = configurableBeanFactory;
    }

    private boolean skip(String beanName) {
        return beanName.startsWith("org.springframework");
    }


    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        if (skip(beanName)) return true;
        ViewFieldCallback callback = new ViewFieldCallback(configurableBeanFactory, bean);
        ReflectionUtils.doWithFields(bean.getClass(), callback);
        return true;
    }

    private static class ViewFieldCallback implements ReflectionUtils.FieldCallback {
        private  boolean applied;

        private final ConfigurableListableBeanFactory configurableBeanFactory;
        private final Object bean;

        ViewFieldCallback(ConfigurableListableBeanFactory bf, Object bean) {
            configurableBeanFactory = bf;
            this.bean = bean;
        }

        @Override
        public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
            if (!field.isAnnotationPresent(View.class) || !Parent.class.isAssignableFrom(field.getType())) {
                return;
            }
            ReflectionUtils.makeAccessible(field);
            try {
                String viewName = field.getAnnotation(View.class).value();
                String nameBean =  "fxml."+ viewName;
                field.set(bean, getValue(nameBean, viewName));
                applied  = true;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

        private Object getValue(String beanName, String viewName) throws IOException {
            if (!configurableBeanFactory.containsBean(beanName)) {
                Parent view = loadView("fxml/" + viewName + ".fxml");

                //Object instance = configurableBeanFactory.initializeBean(view, beanName);
                //configurableBeanFactory.autowireBeanProperties(instance, AutowireCapableBeanFactory.AUTOWIRE_BY_NAME, true);
                configurableBeanFactory.registerSingleton(beanName, view);
                return view;
            } else {
                return configurableBeanFactory.getBean(beanName);
            }

        }

        Parent loadView(String url) throws IOException {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(url));
            loader.setControllerFactory(configurableBeanFactory::getBean); //TODO: circular depth?
            loader.load();
            return loader.getRoot();

        }
    }

}

