package com.dq.police.controller.actions.cop;

import com.dq.police.controller.Action;
import com.dq.police.helpers.ConstraintsUtil;
import com.dq.police.model.PersistenceManager;
import com.dq.police.model.entities.Cop;
import com.dq.police.view.View;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AddCopAction extends Action {

    private PersistenceManager persistence;
    private View view;

    @Override
    public String execute() {
        Cop cop = new Cop();

        cop.setFirstName(view.getPropertyCancellable("first name"));
        cop.setLastName(view.getPropertyCancellable("last name"));
        cop.setPosition(view.getPropertyCancellable("position"));
        cop.setWeapon(view.getPropertyCancellable("weapon"));

        persistence.create(cop);

        return ConstraintsUtil.OPERATION_SUCCESS_MESSAGE;
    }

    @Override
    public String getName() {
        return "Add Cop";
    }
}
