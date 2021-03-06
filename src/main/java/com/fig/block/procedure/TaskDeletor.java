package com.fig.block.procedure;

import com.fig.annotations.ThreadSafe;
import com.fig.manager.Neo4jTaskAdapter;
import com.google.common.annotations.VisibleForTesting;
import com.gs.collections.api.block.procedure.Procedure;

import java.util.Collection;

/**
 * Re-usable procedure to delete tasks.
 * User: Fizal
 * Date: 11/24/13
 * Time: 6:30 PM
 */
@ThreadSafe
public class TaskDeletor implements Procedure<Collection<String>> {
    private final Neo4jTaskAdapter adapter = new Neo4jTaskAdapter();

    @Override
    public void value(Collection<String> taskNames) {
        for (String taskName : taskNames) {
            getAdapter().deleteTask(taskName);
        }
    }

    @VisibleForTesting
    Neo4jTaskAdapter getAdapter() {
        return adapter;
    }
}
