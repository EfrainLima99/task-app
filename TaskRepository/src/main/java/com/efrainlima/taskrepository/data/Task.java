package com.efrainlima.taskrepository.data;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Task {
    private String id;
    private String taskName;
    private String taskDescription;
    private String initDate;
    private String isClosed;
}
