package com.efrainlima.task.data;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@ToString
public class TaskData {
    @NotNull
    private String taskName;
    @NotNull
    private String taskDescription;
    @NotNull
    private String startDate;
}
