package org.noname.html;

import java.lang.reflect.Field;
import java.time.LocalDate;

public class FieldWrapper {
    private final Field field;
    private Converter converter;


    private final Object instance;


    FieldWrapper(Field field,  Object instance) {
        this.field = field;
        this.converter = converter;
        this.instance = instance;
    }


    Field getField() {
        return field;
    }


    private void set(Object value) {
        final boolean accessible = field.isAccessible();
        field.setAccessible(true);
        try {
            field.set(instance, value);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        field.setAccessible(accessible);
    }


/*    private void set(String resultStr) {
        if (isCollection()) {

        }
        if (converter == Converter.NONE) {
            final Class<?> type = field.getType();
            if (type == int.class) {
                set(Converter.INT.convert(resultStr));
            } else if (LocalDate.class.isAssignableFrom(type)) {
                set(Converter.DATE.convert(resultStr));
            } else {
                set(resultStr);
            }
        } else {
            set(converter.convert(resultStr));
        }
    }*/
}
