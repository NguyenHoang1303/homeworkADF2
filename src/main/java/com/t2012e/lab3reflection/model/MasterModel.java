package com.t2012e.lab3reflection.model;

import com.t2012e.lab3reflection.reflection.Student;
import com.t2012e.lab3reflection.reflection.myannotion.Column;
import com.t2012e.lab3reflection.reflection.myannotion.Id;
import com.t2012e.lab3reflection.reflection.myannotion.Table;
import com.t2012e.util.ConnectionHelper;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MasterModel<T> {
    public void save(T obj) throws SQLException, IllegalAccessException {
        Class clazz = obj.getClass();
        Connection cnn = ConnectionHelper.getConnection();
        Statement stt = cnn.createStatement();
        String tableName = clazz.getSimpleName();
        Table table = (Table) clazz.getAnnotation(Table.class);
        if (!table.name().isEmpty()) {
            tableName = table.name();
        }
        StringBuilder columnBuild = new StringBuilder();
        columnBuild.append("(");
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Column.class)) {
                Column column = field.getAnnotation(Column.class);
                columnBuild.append(column.filedName());
                columnBuild.append(", ");
            } else {
                columnBuild.append(field.getName());
                columnBuild.append(", ");
            }

        }
        columnBuild.setLength(columnBuild.length() - 2);
        columnBuild.append(")");

        StringBuilder valuesBuild = new StringBuilder();
        valuesBuild.append("(");
        for (Field field : fields) {
            if (field.getType().getSimpleName().equals("String")) {
                valuesBuild.append("'");
                valuesBuild.append(field.get(obj));
                valuesBuild.append("', ");
            } else {
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
//        System.out.println(sqlQuery);
    }

    public List<T> findAll(Class clazz) throws SQLException, NoSuchMethodException, IllegalAccessException {
        List<T> result = new ArrayList<>();
        Student student = new Student("A001", "Nguyen");
        Connection cnn = ConnectionHelper.getConnection();
        Statement stt = cnn.createStatement();
        String tableName = clazz.getSimpleName();
        Table table = (Table) clazz.getAnnotation(Table.class);
        if (!table.name().isEmpty()) {
            tableName = table.name();
        }
        String sqlQuery = String.format("select * from %s", tableName);
//        ResultSet resultSet = stt.executeQuery(sqlQuery);
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            String fieldname = field.getName();
            System.out.println("fieldname: " +fieldname);
//            field.set(fieldname, "001");
        }
//        while (resultSet.next()){
//            for (Field field: fields) {
//                if (field.getType().getSimpleName().equals("String")){
//                    String fieldname = field.getName();
//                    field.set(fieldname,resultSet.getString(fieldname));
//                }
//            }
//        }

        System.out.println(sqlQuery);
        return result;
    }

    public void deleteByPrimarykey(T obj) throws SQLException, IllegalAccessException {
        Class clazz = obj.getClass();
        Connection cnn = ConnectionHelper.getConnection();
        Statement stt = cnn.createStatement();
        String tableName = clazz.getSimpleName();
        Table table = (Table) clazz.getAnnotation(Table.class);
        if (!table.name().isEmpty()) {
            tableName = table.name();
        }
        StringBuilder buildPrimarykey = new StringBuilder();
        StringBuilder valuesBuild = new StringBuilder();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            boolean isPrimarykey = field.isAnnotationPresent(Id.class);
            if (isPrimarykey){
                buildPrimarykey.append(field.getName());
                buildPrimarykey.append(" ");
                System.out.println(field.getName());
                if (field.getType().getSimpleName().equals("String")) {
                    valuesBuild.append("'");
                    valuesBuild.append(field.get(obj));
                    valuesBuild.append("', ");
                } else {
                    valuesBuild.append(field.get(obj));
                    valuesBuild.append(", ");
                }
            }

        }
//        columnBuild.setLength(columnBuild.length() - 2);


        String sqlQuery = String.format("delete from %s where %s = %s",
                tableName,
                buildPrimarykey,
                valuesBuild
        );
//        stt.execute(sqlQuery);
        System.out.println(sqlQuery);
    }


}
