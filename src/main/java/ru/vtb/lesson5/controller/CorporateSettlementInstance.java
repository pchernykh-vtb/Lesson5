package ru.vtb.lesson5.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.vtb.lesson5.controller.dto.InstanceRequest;
import ru.vtb.lesson5.service.Instanceable;

@RestController
@RequiredArgsConstructor
public class CorporateSettlementInstance {
    private final Instanceable instance;
    @PostMapping("corporate-settlement-instance/create")
    public ResponseEntity<?> create(@Valid @RequestBody InstanceRequest instanceRequest) {
        return ResponseEntity.ok(instance.process(instanceRequest));
    }
}
