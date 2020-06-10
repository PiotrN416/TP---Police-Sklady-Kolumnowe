package com.dq.police.controller.actions.CrimePast;

import com.dq.police.controller.Action;
import com.dq.police.helpers.ConstraintsUtil;
import com.dq.police.model.PersistenceManager;
import com.dq.police.model.entities.Cop;
import com.dq.police.model.entities.CrimePast;
import com.dq.police.view.View;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AddCrimePastAction extends Action {

    private PersistenceManager persistence;
    private View view;

    @Override
    public String execute() {
        CrimePast cop = new CrimePast();

        cop.setPerson(view.getPropertyCancellable("person"));
        cop.setDesc(view.getPropertyCancellable("desc"));

        persistence.create(cop);

        return ConstraintsUtil.OPERATION_SUCCESS_MESSAGE;
    }

    @Override
    public String getName() {
        return "Add Crime Past";
    }
}
