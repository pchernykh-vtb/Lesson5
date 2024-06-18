package ru.vtb.lesson5.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class AccountResponse {
    private Data data;

    public AccountResponse(String accountId) {
        this.data = new Data(accountId);
    }

    @Getter
    @AllArgsConstructor
    public class Data{
        private String accountId;
    }
}
