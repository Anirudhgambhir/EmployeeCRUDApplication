package com.example.anirudh.model.getAllEmployeesModel;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GetAllEmployeeInput {
    private boolean realTimeDataRequired;
}
