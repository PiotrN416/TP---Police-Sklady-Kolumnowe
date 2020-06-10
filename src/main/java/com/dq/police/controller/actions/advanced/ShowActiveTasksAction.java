package com.dq.police.controller.actions.advanced;

import com.dq.police.controller.Action;
import com.dq.police.helpers.ConstraintsUtil;
import com.dq.police.model.PersistenceManager;
import com.dq.police.model.entities.Task;
import com.dq.police.view.View;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ShowActiveTasksAction extends Action {

    private PersistenceManager persistence;
    private View view;

    @Override
    public String execute() {
        List<String> result = persistence.find("status", "W trakcie", Task.class);
        view.showGroup("Tasks", result);
        return ConstraintsUtil.OPERATION_SUCCESS_MESSAGE;
    }

    @Override
    public String getName() {
        return "Active Tasks";
    }
}
