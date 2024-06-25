package ru.vtb.lesson5.service;

import ru.vtb.lesson5.controller.dto.AccountRequest;
import ru.vtb.lesson5.controller.dto.AccountResponse;

public interface Accountable {
    AccountResponse create(AccountRequest accountRequest);
}
