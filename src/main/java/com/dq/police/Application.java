package com.dq.police;


import com.dq.police.controller.SelectOption;
import com.dq.police.controller.actions.ClearDatabaseAction;
import com.dq.police.controller.actions.CreatePresentationDataAction;
import com.dq.police.controller.actions.CrimePast.*;
import com.dq.police.controller.actions.ExitAction;
import com.dq.police.controller.actions.Related.*;
import com.dq.police.controller.actions.Task.*;
import com.dq.police.controller.actions.advanced.ShowActiveTasksAction;
import com.dq.police.controller.actions.advanced.ShowHistoryOfRelatedAction;
import com.dq.police.controller.actions.advanced.ShowWantedListAction;
import com.dq.police.controller.actions.cop.*;
import com.dq.police.controller.options.RunWithCassandraPersistence;
import com.dq.police.controller.options.RunWithMapRPersistence;
import com.dq.police.helpers.CancellingOperationException;
import com.dq.police.helpers.ConstraintsUtil;
import com.dq.police.model.PersistenceManager;
import com.dq.police.view.ConsoleView;
import com.dq.police.view.View;

import java.util.ArrayList;
import java.util.List;

public class Application {

    private static List<SelectOption> registeredActions;
    private static PersistenceManager persistence;
    private static View view;

    public static void main(String[] args) {

        view = new ConsoleView();
        view.showMessage("Starting application");

        persistence = initializePersistence();
        registeredActions = initializeActions();

        while (view.isAppRunning()) {
            SelectOption action = view.selectFromOptions("action", registeredActions);
            String result = executeCancellableAction(action);
            view.showMessage(result);
        }

        view.showMessage("Good bye!");

    }

    private static String executeCancellableAction(SelectOption action) {
        try {
            return (String) action.execute();
        } catch (CancellingOperationException e) {
            return ConstraintsUtil.OPERATION_CANCELLED_MESSAGE;
        } catch (Exception e) {
            return e.getClass().getSimpleName() + " " + e.getMessage();
        }
    }

    private static PersistenceManager initializePersistence() {
        List<SelectOption> options = new ArrayList<>();

        options.add(new RunWithCassandraPersistence());
//        options.add(new RunWithMapRPersistence());

        SelectOption option = view.selectFromOptions("persistence handlers", options);

        return (PersistenceManager) option.execute();
    }

    private static List<SelectOption> initializeActions() {
        ArrayList<SelectOption> actions = new ArrayList<>();

        actions.add(new AddCopAction(persistence, view));
        actions.add(new AddCrimePastAction(persistence, view));
        actions.add(new AddRelatedAction(persistence, view));
        actions.add(new AddTaskAction(persistence, view));

        actions.add(new DeleteCopAction(persistence, view));
        actions.add(new DeleteCrimePastAction(persistence, view));
        actions.add(new DeleteRelatedAction(persistence, view));
        actions.add(new DeleteTaskAction(persistence, view));

        actions.add(new FindAllCopsAction(persistence, view));
        actions.add(new FindAllCrimePastsAction(persistence, view));
        actions.add(new FindAllRelatedsAction(persistence, view));
        actions.add(new FindAllTasksAction(persistence, view));

        actions.add(new FindCopAction(persistence, view));
        actions.add(new FindCrimePastAction(persistence, view));
        actions.add(new FindRelatedAction(persistence, view));
        actions.add(new FindTaskAction(persistence, view));

        actions.add(new UpdateCopAction(persistence, view));
        actions.add(new UpdateCrimePastAction(persistence, view));
        actions.add(new UpdateRelatedAction(persistence, view));
        actions.add(new UpdateTaskAction(persistence, view));

        actions.add(new ShowActiveTasksAction(persistence, view));
        actions.add(new ShowHistoryOfRelatedAction(persistence, view));
        actions.add(new ShowWantedListAction(persistence, view));

        actions.add(new CreatePresentationDataAction(persistence));
        actions.add(new ClearDatabaseAction(persistence));
        actions.add(new ExitAction(view));

        return actions;
    }
}
