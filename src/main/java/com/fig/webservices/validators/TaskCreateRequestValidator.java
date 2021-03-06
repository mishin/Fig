package com.fig.webservices.validators;

import com.fig.annotations.ThreadSafe;
import com.fig.domain.Task;
import com.fig.domain.ValidationResponse;
import com.fig.exception.JsonSyntaxException;
import com.gs.collections.api.block.function.Function;

import static com.fig.domain.ValidationResponse.Builder.response;
import static com.fig.util.BindingUtil.fromJsonArray;
import static com.google.common.base.Strings.isNullOrEmpty;

/**
 * Common function to validate the task create request
 * User: Fizal
 * Date: 11/28/13
 * Time: 7:23 PM
 */
@ThreadSafe
public class TaskCreateRequestValidator implements Function<String, ValidationResponse> {

    @Override
    public ValidationResponse valueOf(String request) {
        if (isNullOrEmpty(request)) {
            return response().error("Property 'request' is missing or empty.", "Request to create task(s) failed !!!").build();
        } else {
            try{
                Task[] tasks = fromJsonArray(request, Task[].class);

                for (Task task : tasks) {
                    task.dropNullValueProperties();
                }

                return response().success("Request accepted successfully. ").output(tasks).build();
            } catch (JsonSyntaxException e){
                return response().error(e.toString(), "Invalid JSON sent in request").build();
            }
        }
    }

}
