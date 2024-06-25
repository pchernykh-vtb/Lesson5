package ru.vtb.lesson5.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
public class InstanceResponse {
    private InstanceResponse.Data data;

    public InstanceResponse(String instanceId, List<String> registerId, List<String> supplementaryAgreementId) {
        this.data = new InstanceResponse.Data(instanceId, registerId, supplementaryAgreementId);
    }

    @Getter
    @AllArgsConstructor
    private class Data{
        private String instanceId;
        private List<String> registerId;
        private List<String> supplementaryAgreementId;
    }
}
