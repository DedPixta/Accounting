package ru.test.task.carservice.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.test.task.carservice.mapper.impl.PartMapper;
import ru.test.task.carservice.repository.PartRepository;

@AllArgsConstructor
@Service
public class PartService {

    private final PartRepository partRepository;
    private final PartMapper partMapper;

    // TODO CRUD methods for separate car parts
}
