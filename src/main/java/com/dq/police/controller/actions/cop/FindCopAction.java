package com.dq.police.controller.actions.cop;


import com.dq.police.controller.Action;
import com.dq.police.helpers.ConstraintsUtil;
import com.dq.police.model.PersistenceManager;
import com.dq.police.model.entities.Cop;
import com.dq.police.view.View;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class FindCopAction extends Action {

    private PersistenceManager persistence;
    private View view;

    @Override
    public String execute() {
        String id = view.getPropertyCancellable("identifier");
        String val = view.getPropertyCancellable("identifier value");

        List<String> results;
        try {
            results = persistence.find(id, Integer.valueOf(val), Cop.class);
        }catch (NumberFormatException e){
            results = persistence.find(id, val, Cop.class);
        }

        view.showGroup("cops", results);

        return ConstraintsUtil.OPERATION_SUCCESS_MESSAGE;
    }

    @Override
    public String getName() {
        return "Find Cop";
    }
}
