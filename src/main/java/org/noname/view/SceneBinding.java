package org.noname.view;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.layout.Pane;
import org.noname.html.Util;
import org.springframework.util.ConcurrentReferenceHashMap;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.*;
import java.util.function.Consumer;

public class SceneBinding {
    private static final Map<Class<?>, Field[]> declaredFieldsCache = new ConcurrentReferenceHashMap<>(256);
    private static final Field[] NO_FIELDS = {};
    public static final String SEP = "_";
    final Map<String, Object> objectMap;
    private Scene scene;

    public SceneBinding(Object obj, Scene scene) {
        objectMap = extract(obj, "");
        this.scene = scene;
    }

    public void bind() {
        doWithNodesOfType(scene.getRoot(), Labeled.class, node -> {
            Labeled label = (Labeled) node;
            String id = label.getId();
            if (id !=null && id.startsWith(SEP)) {
                Object val = objectMap.get(id);
                if (val != null) {
                    label.setText(val.toString());
                }
            }
        });
    }

    static Map<String, Object> extract(Object instance, String parentName) {
        BeanInfo beanInfo = Util.wrapEx(() -> Introspector.getBeanInfo(instance.getClass()));

        return extract(beanInfo, instance, parentName);
    }

    static Map<String, Object> extract(BeanInfo beanInfo, Object instance, String parentName) {
        Map<String, Object> result = new HashMap<>();
        for (PropertyDescriptor propertyDescriptor : beanInfo.getPropertyDescriptors()) {
            Class<?> propertyType = propertyDescriptor.getPropertyType();
            if (propertyDescriptor.getName().equals("class")){
                continue;
            }
            Object value = Util.wrapEx(() -> propertyDescriptor.getReadMethod().invoke(instance));
            if (value == null) {
                continue;
            }
            String newName = parentName + SEP + propertyDescriptor.getName();
            result.put(newName, value.toString());
            if (!isSimple(propertyType)) {
                BeanInfo info = Util.wrapEx(() -> Introspector.getBeanInfo(propertyType));
                result.putAll(extract(info, value, newName));
            }

        }
        return result;
    }

    private void doWithNodesOfType(Parent parent, Class<? extends Node> type, Consumer<Node> consumer) {
        for (Node node : parent.getChildrenUnmodifiable()) {
            if (node instanceof Pane) {
                doWithNodesOfType((Pane) node, type, consumer);
            } else if (type.isAssignableFrom(node.getClass())) {
                //noinspection unchecked
                consumer.accept(node);
            }
        }
    }


    private static boolean isSimple(Class<?> type) {
        return Number.class.isAssignableFrom(type) || String.class.isAssignableFrom(type)
                || type.isPrimitive() || type.isEnum() || type.isArray() || Class.class.isAssignableFrom(type);

    }

}
