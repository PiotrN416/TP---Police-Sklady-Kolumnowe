package com.dq.police.controller.actions.Task;

import com.dq.police.controller.Action;
import com.dq.police.helpers.ConstraintsUtil;
import com.dq.police.model.PersistenceManager;
import com.dq.police.model.entities.Related;
import com.dq.police.model.entities.Task;
import com.dq.police.view.View;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AddTaskAction extends Action {

    private PersistenceManager persistence;
    private View view;

    @Override
    public String execute() {
        Task task = new Task();

        task.setName(view.getPropertyCancellable("name"));
        task.setDesc(view.getPropertyCancellable("desc"));
        task.setType(view.getPropertyCancellable("type"));
        task.setCop(view.getPropertyCancellable("cop"));
        task.setStatus("NOT STARTED");

        persistence.create(task);

        return ConstraintsUtil.OPERATION_SUCCESS_MESSAGE;
    }

    @Override
    public String getName() {
        return "Add Task";
    }
}
