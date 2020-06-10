package com.dq.police.controller.actions.cop;


import com.dq.police.controller.Action;
import com.dq.police.helpers.ConstraintsUtil;
import com.dq.police.model.PersistenceManager;
import com.dq.police.model.entities.Cop;
import com.dq.police.view.View;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class FindAllCopsAction extends Action {

    private PersistenceManager persistence;
    private View view;

    @Override
    public String execute() {
        List<String> employees = persistence.findAllAsJson(Cop.class);
        view.showGroup("cops", employees);
        return ConstraintsUtil.OPERATION_SUCCESS_MESSAGE;
    }

    @Override
    public String getName() {
        return "All Cops";
    }
}
