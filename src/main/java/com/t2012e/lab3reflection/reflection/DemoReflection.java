package com.t2012e.lab3reflection.reflection;


import com.t2012e.util.ConnectionHelper;
import com.t2012e.lab3reflection.reflection.myannotion.Column;
import com.t2012e.lab3reflection.reflection.myannotion.Id;
import com.t2012e.lab3reflection.reflection.myannotion.Table;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DemoReflection {
    public static void main(String[] args) throws SQLException, IllegalAccessException {
        Student student = new Student("A001", "Nguyen");
        migrateData(Student.class);
        save(student);
    }

    public  static void migrateData(Class clazz) throws SQLException {
        String tableName = clazz.getSimpleName();
        if(!clazz.isAnnotationPresent(Table.class)){
            System.err.println("Class ko duoc danh dau de mapping voi database");
            return;
        }
        Table table = (Table) clazz.getAnnotation(Table.class);
        if (!table.name().isEmpty()){
            tableName = table.name();
        }
        StringBuilder sqlstringBuilder = new StringBuilder();
        sqlstringBuilder.append("CREATE TABLE");
        sqlstringBuilder.append(" ");
        sqlstringBuilder.append(tableName);
        sqlstringBuilder.append(" ");
        sqlstringBuilder.append("(");
         Field[] fields = clazz.getDeclaredFields();
        for (Field field: fields) {
            boolean isPrimarykey = field.isAnnotationPresent(Id.class);
            if (field.isAnnotationPresent(Column.class)){
                Column column = field.getAnnotation(Column.class);
                sqlstringBuilder.append(column.filedName());
                sqlstringBuilder.append(" ");
                sqlstringBuilder.append(column.filedType());
            }else {
                sqlstringBuilder.append(field.getName());
                sqlstringBuilder.append(" ");
                if (field.getType().getSimpleName().equals("int")){
                    sqlstringBuilder.append("INT");
                } else if (field.getType().getSimpleName().equals("String")){
                    sqlstringBuilder.append("VARCHAR(250)");
                }else if(field.getType().getSimpleName().equals("Double")){
                    sqlstringBuilder.append("DOUBLE");
                }
            }
            if (isPrimarykey){
                Id id = field.getAnnotation(Id.class);
                if(id.autoIncrement()){
                    sqlstringBuilder.append(" AUTO_INCREMENT");
                }
                sqlstringBuilder.append(" PRIMARYKEY");
            }
            sqlstringBuilder.append(", ");
        }
        sqlstringBuilder.setLength(sqlstringBuilder.length() - 2);
        sqlstringBuilder.append(")");
        System.out.println(sqlstringBuilder);
        Connection cnn = ConnectionHelper.getConnection();
        Statement stt = cnn.createStatement();
//        stt.execute(sqlstringBuilder.toString());
    }

    public  static void save(Object obj) throws SQLException, IllegalAccessException {
        Class clazz = obj.getClass();
        Connection cnn = ConnectionHelper.getConnection();
        Statement stt = cnn.createStatement();
        String tableName = clazz.getSimpleName();
        Table table = (Table) clazz.getAnnotation(Table.class);
        if (!table.name().isEmpty()){
            tableName = table.name();
        }
        StringBuilder columnBuild = new StringBuilder();
        columnBuild.append("(");
        Field[] fields = clazz.getDeclaredFields();
        for (Field field: fields) {
            if (field.isAnnotationPresent(Column.class)){
                Column column = field.getAnnotation(Column.class);
                columnBuild.append(column.filedName());
                columnBuild.append(", ");
            }else {
                columnBuild.append(field.getName());
                columnBuild.append(", ");
            }

        }
        columnBuild.setLength(columnBuild.length() - 2);
        columnBuild.append(")");

        StringBuilder valuesBuild = new StringBuilder();
        valuesBuild.append("(");
        for (Field field: fields) {
            if (field.getType().getSimpleName().equals("String")){
                valuesBuild.append("'");
                valuesBuild.append(field.get(obj));
                valuesBuild.append("', ");
            }else {
                valuesBuild.append(field.get(obj));
                valuesBuild.append(", ");
            }
        }
        valuesBuild.setLength(valuesBuild.length() - 2);
        valuesBuild.append(")");
        String sqlQuery = String.format("insert into %s %s values %s",
                tableName,
                columnBuild,
                valuesBuild
                );
//        stt.execute(sqlQuery);
        System.out.println(sqlQuery);
    }

}
