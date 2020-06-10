package com.dq.police.controller.actions.advanced;

import com.dq.police.controller.Action;
import com.dq.police.helpers.ConstraintsUtil;
import com.dq.police.model.PersistenceManager;
import com.dq.police.model.entities.CrimePast;
import com.dq.police.model.entities.Task;
import com.dq.police.view.View;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ShowHistoryOfRelatedAction extends Action {

    private PersistenceManager persistence;
    private View view;

    @Override
    public String execute() {
        String firstName = view.getPropertyCancellable("first name");
        String lastName = view.getPropertyCancellable("last name");

        List<String> result = persistence.find("person", firstName + " " + lastName, CrimePast.class);
        view.showGroup("Crime Past", result);
        return ConstraintsUtil.OPERATION_SUCCESS_MESSAGE;
    }

    @Override
    public String getName() {
        return "History of Related";
    }
}
