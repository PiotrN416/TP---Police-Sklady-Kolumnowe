package com.dq.police.controller.actions.Related;


import com.dq.police.controller.Action;
import com.dq.police.helpers.ConstraintsUtil;
import com.dq.police.model.PersistenceManager;
import com.dq.police.model.entities.CrimePast;
import com.dq.police.model.entities.Related;
import com.dq.police.view.View;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class FindAllRelatedsAction extends Action {

    private PersistenceManager persistence;
    private View view;

    @Override
    public String execute() {
        List<String> results = persistence.findAllAsJson(Related.class);
        view.showGroup("Relateds", results);
        return ConstraintsUtil.OPERATION_SUCCESS_MESSAGE;
    }

    @Override
    public String getName() {
        return "All Relateds";
    }
}
