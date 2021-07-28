package com.t2012e.lab3reflection.reflection;

import com.t2012e.util.ConnectionHelper;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class MasterModel<T> {
    public void save(T obj) {
        try {
            Class<T> clazz = (Class<T>) obj.getClass();
            Connection cnn = ConnectionHelper.getConnection();
            Statement statement = cnn.createStatement();
            String tableName = null;
            String fieldNamesBuilder = null;
            String fieldValuesBuilder = null;
            String sqlQuery = String.format("insert into %s %s values %s", tableName, fieldNamesBuilder, fieldValuesBuilder);
            statement.execute(sqlQuery);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
