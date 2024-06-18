package ru.vtb.lesson5.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.vtb.lesson5.controller.dto.AccountRequest;
import ru.vtb.lesson5.service.Accountable;

@RestController
@RequiredArgsConstructor
public class CorporateSettlementAccount {
    private final Accountable account;
    @PostMapping("corporate-settlement-account/create")
    public ResponseEntity<?> create(@Valid @RequestBody AccountRequest accountRequest) {
        return ResponseEntity.ok(account.create(accountRequest));
    }
}
