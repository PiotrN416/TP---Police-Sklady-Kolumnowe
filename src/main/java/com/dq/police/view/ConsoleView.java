package com.dq.police.view;

import com.dq.police.controller.SelectOption;
import com.dq.police.helpers.CancellingOperationException;
import com.dq.police.helpers.ConstraintsUtil;

import java.util.List;
import java.util.Scanner;

public class ConsoleView implements View {

    private Scanner scanner;

    private boolean appRunning = true;

    public ConsoleView() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public String getProperty(String propertyName) {
        System.out.print(propertyName + ": ");
        return scanner.nextLine();
    }

    @Override
    public String getPropertyCancellable(String propertyName) {
        String property = getProperty(propertyName);

        if (isOperationCancelled(property)) {
            throw new CancellingOperationException("Cancelling operation");
        }

        return property;
    }

    @Override
    public long getValidNumberPropertyCancellable(String propertyName) {
        while (true) {
            String property = getProperty(propertyName);

            if (isOperationCancelled(property)) {
                throw new CancellingOperationException("Cancelling operation");
            }

            try {
                return Long.valueOf(property);
            } catch (NumberFormatException e) {
                continue;
            }
        }
    }

    @Override
    public SelectOption selectFromOptions(String setName, List<SelectOption> options) {
        showMessage("Please select " + setName);

        for (SelectOption o : options) {
            showSubMessage("[" + o.getOptionId() + "] " + o.getOptionLabel());
        }

        while (true) {
            String property = getProperty("id/name");
            for (SelectOption opt : options) {
                if (isMatchingOption(property, opt)) {
                    return opt;
                }
            }

            showMessage(ConstraintsUtil.INVALID_OPTION_MESSAGE);
        }
    }

    @Override
    public boolean getConfirmation(String message) {
        showMessage(message);
        while (true) {
            String decision = getProperty("y/n");

            if ("y".equalsIgnoreCase(decision)) {
                return true;
            } else if ("n".equalsIgnoreCase(decision)) {
                return false;
            } else {
                showMessage(ConstraintsUtil.INVALID_OPTION_MESSAGE);
            }
        }
    }

    private static boolean isMatchingOption(String property, SelectOption option) {
        try {
            return (option.getOptionLabel().equalsIgnoreCase(property)) || option.getOptionId() == Long.valueOf(property);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isOperationCancelled(String propertyName) {
        return "q".equalsIgnoreCase(propertyName);
    }

    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void showSubMessage(String message) {
        showMessage("\t" + message);
    }

    @Override
    public void showGroup(String name, List messages) {
        showMessage(name + " " + messages.size());
        for (Object o : messages) {
            showSubMessage(o.toString());
        }
    }

    @Override
    public boolean isAppRunning() {
        return appRunning;
    }

    @Override
    public void setAppRunning(boolean flag) {
        this.appRunning = flag;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        if (scanner != null) {
            scanner.close();
        }
    }
}
