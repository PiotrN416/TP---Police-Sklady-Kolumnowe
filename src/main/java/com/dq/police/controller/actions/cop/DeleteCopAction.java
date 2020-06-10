package com.dq.police.controller.actions.cop;


import com.dq.police.controller.Action;
import com.dq.police.model.PersistenceManager;
import com.dq.police.model.entities.Cop;
import com.dq.police.view.View;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteCopAction extends Action {

    private PersistenceManager persistence;
    private View view;

    @Override
    public String execute() {
        String id = view.getPropertyCancellable("identifier");
        String val = view.getPropertyCancellable("identifier value");

        return persistence.delete(id, val, Cop.class);
    }

    @Override
    public String getName() {
        return "Delete Cop";
    }
}
