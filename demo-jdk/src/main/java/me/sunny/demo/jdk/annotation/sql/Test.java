package me.sunny.demo.jdk.annotation.sql;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Test {
    public static void main(String[] args) {
        Mapper mapper = new Mapper();
        mapper.setId(1);
        mapper.setUsername("sunny");

        System.out.println(query(mapper));
    }

    private static String query(Object mapper) {
        StringBuilder sql = new StringBuilder();
        Class cMapper = mapper.getClass();
        if (!cMapper.isAnnotationPresent(Table.class)) {
            return null;
        }
        Table table = (Table) cMapper.getAnnotation(Table.class);
        String tableName = table.value();
        sql.append("select * from ").append(tableName).append(" where 1=1 ");
        Field[] fields = cMapper.getDeclaredFields();
        for (Field field : fields) {
            if (!field.isAnnotationPresent(Column.class)) {
                continue;
            }
            Column column = field.getAnnotation(Column.class);
            String ColumuName = column.value();
            String fieldName = field.getName();
            String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
            Object ColumuValue = null;
            try {
                Method method = cMapper.getMethod(getMethodName);
                ColumuValue = method.invoke(mapper);
            } catch (NoSuchMethodException | SecurityException | IllegalAccessException
                    | IllegalArgumentException | InvocationTargetException e) {
                e.printStackTrace();
            }
            if (ColumuValue instanceof Integer && (int) ColumuValue != 0) {
                sql.append("and " + ColumuName + " = " + ColumuValue + " ");

            } else if (ColumuValue instanceof String) {
                if (((String) ColumuValue).contains(",")) {
                    String[] values = ((String) ColumuValue).split(",");
                    sql.append("and " + ColumuName + " in (");
                    for (int i = 0; i < values.length; i++) {
                        sql.append("'").append(values[i]).append(" ',");
                    }
                    sql.deleteCharAt(sql.length() - 1);
                    sql.append(")");
                } else {
                    sql.append("and " + ColumuName + " = '" + ColumuValue + "' ");
                }
            }
        }
        return sql.toString();
    }
}
