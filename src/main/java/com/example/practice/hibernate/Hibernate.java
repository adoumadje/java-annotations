package com.example.practice.hibernate;

import com.example.practice.hibernate.annotations.Column;
import com.example.practice.hibernate.annotations.PrimaryKey;
import com.example.practice.hibernate.annotations.Table;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.StringJoiner;
import java.util.concurrent.atomic.AtomicLong;

public class Hibernate<T> {
    private Connection connection;
    private AtomicLong id;

    public Hibernate() {
        String url = "jdbc:h2:C:/Users/FU773TY/OneDrive - EY/Documents/java-annotations/src/main/resources/database/hibernate";
        String username = "sa";
        String password = "";
        try {
            this.connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
        this.id = new AtomicLong(0);
    }

    public void write(T t) throws SQLException, NoSuchMethodException, IllegalAccessException {
        Class<?> clss = t.getClass();

        Table table = clss.getAnnotation(Table.class);
        String tableName = table.name();

        StringJoiner columnJoiner = new StringJoiner(", ");
        StringJoiner valJoiner = new StringJoiner(", ");

        Field[] fields = clss.getDeclaredFields();

        for(Field field: fields) {
            valJoiner.add("?");
            if(field.getAnnotation(PrimaryKey.class) != null) {
                PrimaryKey primaryKey = field.getAnnotation(PrimaryKey.class);
                columnJoiner.add(primaryKey.name());
            } else if (field.getAnnotation(Column.class) != null) {
                Column column = field.getAnnotation(Column.class);
                columnJoiner.add(column.name());
            }
        }

        String sql = """
                INSERT INTO %s (%s)
                VALUES (%s);
                """.formatted(tableName, columnJoiner, valJoiner);

        PreparedStatement statement = connection.prepareStatement(sql);
        int index = 1;
        statement.setLong(index++, id.incrementAndGet());

        for(Field field: fields) {
            field.setAccessible(true);
            if(field.getAnnotation(PrimaryKey.class) != null) {
                field.set(t, id.get());
            } else if (field.getAnnotation(Column.class) != null) {
                if(field.getType() == String.class) {
                    statement.setString(index++, (String) field.get(t));
                } else if (field.getType() == Double.class) {
                    statement.setDouble(index++, (Double) field.get(t));
                }
            }
        }

        if(statement.executeUpdate() > 0) {
            System.out.printf("Entity saved successfully: %s\n", t);
        } else {
            System.out.printf("unable to save entity: %s\n", t);
        }
    }

    public T read(Class<T> clazz, Long pk) throws SQLException, NoSuchMethodException, InvocationTargetException,
            InstantiationException, IllegalAccessException {
        Table table = clazz.getAnnotation(Table.class);
        PrimaryKey primaryKey = null;

        Field[] fields = clazz.getDeclaredFields();
        for(Field field: fields) {
            if(field.getAnnotation(PrimaryKey.class) != null) {
                primaryKey = field.getAnnotation(PrimaryKey.class);
                break;
            }
        }

        String sql = """
                SELECT * FROM %s
                WHERE %s = %d
                """.formatted(table.name(), primaryKey.name(), pk);

        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        if(!resultSet.next()) {
            System.out.println("Not found");
            return null;
        }
        Constructor<T> constructor = clazz.getConstructor();
        T dbObject = constructor.newInstance();

        for(Field field: fields) {
            field.setAccessible(true);
            if(field.getAnnotation(PrimaryKey.class) != null) {
                field.set(dbObject, pk);
            } else if(field.getAnnotation(Column.class) != null) {
                Column column = field.getAnnotation(Column.class);
                field.set(dbObject, resultSet.getObject(column.name()));
            }
        }

        resultSet.next();
        return dbObject;
    }
}
