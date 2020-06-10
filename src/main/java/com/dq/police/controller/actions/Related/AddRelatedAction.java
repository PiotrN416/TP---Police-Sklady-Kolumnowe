package com.dq.police.controller.actions.Related;

import com.dq.police.controller.Action;
import com.dq.police.helpers.ConstraintsUtil;
import com.dq.police.model.PersistenceManager;
import com.dq.police.model.entities.CrimePast;
import com.dq.police.model.entities.Related;
import com.dq.police.view.View;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AddRelatedAction extends Action {

    private PersistenceManager persistence;
    private View view;

    @Override
    public String execute() {
        Related related = new Related();

        related.setFirstName(view.getPropertyCancellable("first name"));
        related.setLastName(view.getPropertyCancellable("last name"));
        related.setDesc(view.getPropertyCancellable("desc"));

        persistence.create(related);

        return ConstraintsUtil.OPERATION_SUCCESS_MESSAGE;
    }

    @Override
    public String getName() {
        return "Add Related";
    }
}
