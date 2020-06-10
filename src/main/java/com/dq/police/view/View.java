package com.dq.police.view;

import com.dq.police.controller.SelectOption;

import java.util.List;

public interface View {

    void showMessage(String message);

    void showSubMessage(String message);

    void showGroup(String name, List messages);

    String getProperty(String propertyName);

    String getPropertyCancellable(String propertyName);

    long getValidNumberPropertyCancellable(String propertyName);

    SelectOption selectFromOptions(String setName, List<SelectOption> options);

    boolean getConfirmation(String message);

    boolean isAppRunning();

    void setAppRunning(boolean flag);
}
