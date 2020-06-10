package com.dq.police.model.persistence;


import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import com.datastax.oss.driver.api.core.servererrors.AlreadyExistsException;
import com.datastax.oss.driver.api.core.servererrors.InvalidQueryException;
import com.datastax.oss.driver.api.core.type.DataTypes;
import com.datastax.oss.driver.api.querybuilder.QueryBuilder;
import com.datastax.oss.driver.api.querybuilder.SchemaBuilder;
import com.datastax.oss.driver.api.querybuilder.delete.Delete;
import com.datastax.oss.driver.api.querybuilder.schema.CreateKeyspace;
import com.datastax.oss.driver.api.querybuilder.schema.CreateTable;
import com.datastax.oss.driver.api.querybuilder.schema.Drop;
import com.datastax.oss.driver.api.querybuilder.select.Select;
import com.datastax.oss.driver.api.querybuilder.update.Update;
import com.dq.police.model.Entity;
import com.dq.police.model.PersistenceManager;

import java.lang.reflect.Field;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static com.datastax.oss.driver.api.querybuilder.QueryBuilder.literal;

public class CassandraPersistenceManager implements PersistenceManager {

    private CqlSession session;

    public CassandraPersistenceManager(Class<? extends Entity>... entities) {
        session = CqlSession.builder().build();

        try {
            CreateKeyspace createKeyspace = SchemaBuilder.createKeyspace("police").withSimpleStrategy(1);
            session.execute(createKeyspace.build().setTimeout(Duration.ofSeconds(30)));
        } catch (AlreadyExistsException e) {
        }

        session.execute("USE police");

        for (Class<?> entity : entities) {
            createTable(entity);
        }
    }

    private void createTable(Class<?> entity) {
        try {
            CreateTable table = SchemaBuilder.createTable(entity.getSimpleName())
                    .ifNotExists()
                    .withPartitionKey("id", DataTypes.INT);

            for (Field f : entity.getDeclaredFields()) {
                if (f.getName().equalsIgnoreCase("id")) {
                    continue;
                }
//                table = table.withColumn(f.getName(), f.getType().equals(String.class) ? DataTypes.TEXT : DataTypes.INT);
                table = table.withColumn("\"" + f.getName() + "\"", f.getType().equals(String.class) ? DataTypes.TEXT : DataTypes.INT);
            }

            session.execute(table.build().setTimeout(Duration.ofSeconds(30)));
        } catch (AlreadyExistsException e) {
        }
    }

    @Override
    public Entity create(Entity entity) {
        String insert = insertFromEntity(entity);
        session.execute(insert);
        return entity;
    }

    private String insertFromEntity(Entity entity) {
        StringBuilder insert = new StringBuilder("INSERT INTO ")
                .append(entity.getClass().getSimpleName())
                .append("(id,");

        StringBuilder values = new StringBuilder(" VALUES (")
                .append(entity.getId())
                .append(",");

        for (Field f : entity.getClass().getDeclaredFields()) {
            try {
                boolean accessibility = f.isAccessible();
                f.setAccessible(true);

                insert.append(f.getType().equals(String.class) ? "\"" + f.getName() + "\"" : f.getName())
                        .append(",");

                values.append(f.getType().equals(String.class) ? "\'" + f.get(entity) + "\'" : f.get(entity))
                        .append(",");

                f.setAccessible(accessibility);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        insert.deleteCharAt(insert.length() - 1);
        values.deleteCharAt(values.length() - 1);

        insert.append(")");
        values.append(")");

        return insert.toString() + values.toString();
    }

    @Override
    public String update(String id, Object idVal, String field, Object newVal, Class<? extends Entity> type) {
        Update update = QueryBuilder
                .update(type.getSimpleName())
                .setColumn("\"" + field + "\"", newVal instanceof String ? QueryBuilder.raw("'" + newVal + "'") : QueryBuilder.literal(newVal))
                .whereColumn(id)
                .isEqualTo(QueryBuilder.raw(idVal.toString()));

        ResultSet result = session.execute(update.build().setTimeout(Duration.ofSeconds(30)));
        return result.toString();
    }

    @Override
    public String delete(String id, Object idVal, Class<? extends Entity> type) {
        Delete delete = QueryBuilder
                .deleteFrom(type.getSimpleName())
                .whereColumn(id)
                .isEqualTo(QueryBuilder.raw(idVal.toString()));

        ResultSet result = session.execute(delete.build().setTimeout(Duration.ofSeconds(30)));
        return result.toString();
    }

    @Override
    public List<String> findAllAsJson(Class<? extends Entity> type) {
        Select select = QueryBuilder.selectFrom(type.getSimpleName()).all();
        SimpleStatement simpleStatement = select.build();
        ResultSet resultSet = session.execute(simpleStatement.setTimeout(Duration.ofSeconds(30)));

        return extractResultSet(type, resultSet);
    }

    @Override
    public List<Entity> findAllAsEntity(Class<? extends Entity> type) {
        return null;
    }

    @Override
    public List<String> find(String id, Object idVal, Class<? extends Entity> type) {
        Select select = QueryBuilder
                .selectFrom(type.getSimpleName())
                .all()
                .whereColumn("\"" + id + "\"")
                .isEqualTo(literal(idVal));

        ResultSet resultSet = session.execute(select.allowFiltering().build().setTimeout(Duration.ofSeconds(30)));
        return extractResultSet(type, resultSet);
    }

    @Override
    public void clearData(Class<? extends Entity> type) {
        try {
            Drop drop = SchemaBuilder.dropTable(type.getSimpleName());
            session.execute(drop.build().setTimeout(Duration.ofSeconds(30)));
        } catch (InvalidQueryException e) {
        }
    }

    private List<String> extractResultSet(Class<? extends Entity> type, ResultSet resultSet) {
        Field[] fields = type.getDeclaredFields();
        List<String> results = new ArrayList<>();

        for (Row row : resultSet) {
            StringBuilder obj = new StringBuilder(type.getSimpleName() + ": id=")
                    .append(row.get("id", Integer.class))
                    .append(" ");

            for (Field f : fields) {
                obj.append(f.getName())
                        .append("=")
                        .append(row.get(f.getName(), f.getType()))
                        .append(" ");

            }

            results.add(obj.toString());
        }

        return results;
    }
}
