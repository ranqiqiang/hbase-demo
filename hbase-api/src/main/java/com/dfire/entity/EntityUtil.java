package com.dfire.entity;

import com.dfire.annocation.ColName;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by qqr on 16/8/3.
 */
public class EntityUtil {

    private static Map<Class,Set<String>> fieldMap = Maps.newConcurrentMap();

    public static Set<String> getFieldNames(Class c){
        Set list = fieldMap.get(c);
        return  list == null?getFieldNamesByClass(c):list;
    }


    public static Set<String> getFieldNamesByClass(Class c){
        Field[] fields = c.getDeclaredFields();
        Set<String> names = Sets.newHashSet();
        for(Field field : fields){
            ColName colName = field.getAnnotation(ColName.class);
            if(colName != null){
                String name = "".equals(colName.value())?field.getName():colName.value();
                names.add(name);
            }

        }
        fieldMap.put(c,names);
        return names;
    }
}
