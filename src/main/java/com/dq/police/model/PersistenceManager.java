package com.dq.police.model;

import java.util.List;

public interface PersistenceManager {

    Entity create(Entity entity);

    String update(String id, Object idVal, String field, Object newVal, Class<? extends Entity> type);

    String delete(String id, Object idVal, Class<? extends Entity> type);

    List<String> findAllAsJson(Class<? extends Entity> type);

    List<Entity> findAllAsEntity(Class<? extends Entity> type);

    List<String> find(String id, Object idVal, Class<? extends Entity> type);

    void clearData(Class<? extends Entity> type);
}
