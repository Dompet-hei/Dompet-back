package org.dompet.utils.annotations;

import org.dompet.model.Account;

import java.util.Arrays;

public class IdAnnotation {
    public static String getIdColumnName(Class<?> clazz){
        return Arrays
                .stream(clazz.getDeclaredFields())
                .filter(champ -> champ.isAnnotationPresent(Id.class))
                .toList()
                .get(0)
                .getAnnotation(Column.class)
                .name();
    }
}
