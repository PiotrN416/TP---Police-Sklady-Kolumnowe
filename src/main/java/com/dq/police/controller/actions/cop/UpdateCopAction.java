package com.dq.police.controller.actions.cop;


import com.dq.police.controller.Action;
import com.dq.police.model.PersistenceManager;
import com.dq.police.model.entities.Cop;
import com.dq.police.view.View;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdateCopAction extends Action {

    private PersistenceManager persistence;
    private View view;

    @Override
    public String execute() {
        String id = view.getPropertyCancellable("identifier");
        String idVal = view.getPropertyCancellable("identifier value");
        String field = view.getPropertyCancellable("update what");
        String fieldVal = view.getPropertyCancellable("update to");

        return persistence.update(id, idVal, field, fieldVal, Cop.class);
    }

    @Override
    public String getName() {
        return "Update Cop";
    }
}
