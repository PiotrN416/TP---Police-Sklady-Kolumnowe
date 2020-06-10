package com.dq.police.controller.actions;


import com.dq.police.controller.Action;
import com.dq.police.helpers.ConstraintsUtil;
import com.dq.police.view.View;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ExitAction extends Action {

    private View view;

    @Override
    public String execute() {
        view.setAppRunning(false);
        return ConstraintsUtil.OPERATION_SUCCESS_MESSAGE;
    }

    @Override
    public String getName() {
        return "Exit";
    }
}
