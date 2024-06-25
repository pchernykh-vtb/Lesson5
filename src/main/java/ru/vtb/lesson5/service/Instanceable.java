package ru.vtb.lesson5.service;

import ru.vtb.lesson5.controller.dto.InstanceRequest;
import ru.vtb.lesson5.controller.dto.InstanceResponse;

public interface Instanceable {
    InstanceResponse process(InstanceRequest instanceRequest);
}
