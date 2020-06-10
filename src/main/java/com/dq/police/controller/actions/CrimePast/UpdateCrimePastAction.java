package com.dq.police.controller.actions.CrimePast;


import com.dq.police.controller.Action;
import com.dq.police.model.PersistenceManager;
import com.dq.police.model.entities.Cop;
import com.dq.police.model.entities.CrimePast;
import com.dq.police.view.View;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdateCrimePastAction extends Action {

    private PersistenceManager persistence;
    private View view;

    @Override
    public String execute() {
        String id = view.getPropertyCancellable("identifier");
        String idVal = view.getPropertyCancellable("identifier value");
        String field = view.getPropertyCancellable("update what");
        String fieldVal = view.getPropertyCancellable("update to");

        return persistence.update(id, idVal, field, fieldVal, CrimePast.class);
    }

    @Override
    public String getName() {
        return "Update Crime Past";
    }
}
