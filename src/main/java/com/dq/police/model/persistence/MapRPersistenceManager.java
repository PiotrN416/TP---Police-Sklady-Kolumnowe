package com.dq.police.model.persistence;

import com.dq.police.model.Entity;
import com.dq.police.model.PersistenceManager;
import com.mapr.db.MapRDB;
import com.mapr.db.Table;
import org.ojai.Document;

import java.util.List;

public class MapRPersistenceManager implements PersistenceManager {

    public MapRPersistenceManager() {

        String tablePath = "/app/police/test";

        Table t = MapRDB.tableExists(tablePath) ? MapRDB.getTable(tablePath) : MapRDB.createTable(tablePath);

        Document doc = MapRDB.newDocument().set("abcd", "efgh");
        t.insertOrReplace("X", doc);

        Document d2 = t.findById("X");
        System.out.println(d2);

    }

    @Override
    public Entity create(Entity entity) {


        return null;
    }

    @Override
    public String update(String id, Object idVal, String field, Object newVal, Class<? extends Entity> type) {
        return null;
    }

    @Override
    public String delete(String id, Object idVal, Class<? extends Entity> type) {
        return null;
    }

    @Override
    public List<String> findAllAsJson(Class<? extends Entity> type) {
        return null;
    }

    @Override
    public List<Entity> findAllAsEntity(Class<? extends Entity> type) {
        return null;
    }

    @Override
    public List<String> find(String id, Object idVal, Class<? extends Entity> type) {
        return null;
    }

    @Override
    public void clearData(Class<? extends Entity> type) {

    }
}
