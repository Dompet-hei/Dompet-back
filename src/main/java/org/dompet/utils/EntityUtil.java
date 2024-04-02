package org.dompet.utils;

import java.lang.reflect.Field;

public class EntityUtil {
  public static <T> void updateEntityFields(T existingEntity, T newEntity) {
    Field[] fields = existingEntity.getClass().getDeclaredFields();
    for (Field field : fields) {
      field.setAccessible(true);
      try {
        Object value = field.get(newEntity);
        if (value != null) {
          field.set(existingEntity, value);
        }
      } catch (IllegalAccessException e) {
        System.err.println("Error updating field: " + field.getName());
        e.printStackTrace();
      }
    }
  }
}
