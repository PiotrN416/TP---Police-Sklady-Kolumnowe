package com.dq.police.controller.actions.advanced;

import com.dq.police.controller.Action;
import com.dq.police.helpers.ConstraintsUtil;
import com.dq.police.model.PersistenceManager;
import com.dq.police.model.entities.Related;
import com.dq.police.model.entities.Task;
import com.dq.police.view.View;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ShowWantedListAction extends Action {

    private PersistenceManager persistence;
    private View view;

    @Override
    public String execute() {
        List<String> result = persistence.find("desc", "Poszukiwany", Related.class);
        view.showGroup("Wanteds", result);
        return ConstraintsUtil.OPERATION_SUCCESS_MESSAGE;
    }

    @Override
    public String getName() {
        return "Wanted List";
    }
}
