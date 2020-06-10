package com.dq.police.controller.options;

import com.dq.police.controller.SelectOption;
import com.dq.police.model.PersistenceManager;
import com.dq.police.model.entities.Cop;
import com.dq.police.model.entities.CrimePast;
import com.dq.police.model.entities.Related;
import com.dq.police.model.entities.Task;
import com.dq.police.model.persistence.CassandraPersistenceManager;

public class RunWithCassandraPersistence extends SelectOption<PersistenceManager> {

    @Override
    public PersistenceManager execute() {
        return new CassandraPersistenceManager(Cop.class, CrimePast.class, Related.class, Task.class);
    }

    @Override
    public String getOptionLabel() {
        return "Cassandra";
    }
}
