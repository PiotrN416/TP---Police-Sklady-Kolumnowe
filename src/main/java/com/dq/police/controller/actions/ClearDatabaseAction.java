package com.dq.police.controller.actions;


import com.dq.police.controller.Action;
import com.dq.police.helpers.ConstraintsUtil;
import com.dq.police.model.PersistenceManager;
import com.dq.police.model.entities.Cop;
import com.dq.police.model.entities.CrimePast;
import com.dq.police.model.entities.Related;
import com.dq.police.model.entities.Task;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ClearDatabaseAction extends Action {

    private PersistenceManager persistence;

    @Override
    public String execute() {
        persistence.clearData(Cop.class);
        persistence.clearData(CrimePast.class);
        persistence.clearData(Related.class);
        persistence.clearData(Task.class);
        return ConstraintsUtil.OPERATION_SUCCESS_MESSAGE;
    }

    @Override
    public String getName() {
        return "Clear Database";
    }
}
