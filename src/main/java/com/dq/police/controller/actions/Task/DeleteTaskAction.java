package com.dq.police.controller.actions.Task;


import com.dq.police.controller.Action;
import com.dq.police.model.PersistenceManager;
import com.dq.police.model.entities.Related;
import com.dq.police.model.entities.Task;
import com.dq.police.view.View;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteTaskAction extends Action {

    private PersistenceManager persistence;
    private View view;

    @Override
    public String execute() {
        String id = view.getPropertyCancellable("identifier");
        String val = view.getPropertyCancellable("identifier value");

        return persistence.delete(id, val, Task.class);
    }

    @Override
    public String getName() {
        return "Delete Task";
    }
}
