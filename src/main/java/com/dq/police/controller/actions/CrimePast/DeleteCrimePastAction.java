package com.dq.police.controller.actions.CrimePast;


import com.dq.police.controller.Action;
import com.dq.police.model.PersistenceManager;
import com.dq.police.model.entities.CrimePast;
import com.dq.police.view.View;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteCrimePastAction extends Action {

    private PersistenceManager persistence;
    private View view;

    @Override
    public String execute() {
        String id = view.getPropertyCancellable("identifier");
        String val = view.getPropertyCancellable("identifier value");

        return persistence.delete(id, val, CrimePast.class);
    }

    @Override
    public String getName() {
        return "Delete Crime Past";
    }
}
