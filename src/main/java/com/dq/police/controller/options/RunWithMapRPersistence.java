package com.dq.police.controller.options;

import com.dq.police.controller.SelectOption;
import com.dq.police.model.PersistenceManager;
import com.dq.police.model.persistence.MapRPersistenceManager;

public class RunWithMapRPersistence extends SelectOption<PersistenceManager> {

    @Override
    public PersistenceManager execute() {
        return new MapRPersistenceManager();
    }

    @Override
    public String getOptionLabel() {
        return "MapR";
    }
}
