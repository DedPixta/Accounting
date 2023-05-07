package ru.test.task.carservice.mapper.impl;

import org.springframework.stereotype.Component;
import ru.test.task.carservice.dto.PartDto;
import ru.test.task.carservice.dto.parts.EngineDto;
import ru.test.task.carservice.dto.parts.MuffleDto;
import ru.test.task.carservice.entity.Part;
import ru.test.task.carservice.entity.parts.Engine;
import ru.test.task.carservice.entity.parts.Muffle;
import ru.test.task.carservice.mapper.Mapper;
import ru.test.task.carservice.util.PartType;

@Component
public class PartMapper implements Mapper<PartDto, Part> {

    @Override
    public Part toEntity(PartDto partDto) {
        if (partDto == null) {
            throw new NullPointerException("Missing partDto");
        }
        if (PartType.MUFFLE.equals(partDto.getPartType())) {
            return this.toEntity((MuffleDto) partDto);
        }
        if (PartType.ENGINE.equals(partDto.getPartType())) {
            return this.toEntity((EngineDto) partDto);
        }
        throw new IllegalArgumentException("Unknown dto type");
    }

    @Override
    public PartDto toDto(Part part) {
        if (part == null) {
            throw new NullPointerException("Missing part");
        }
        if (part instanceof Muffle) {
            return this.toDto((Muffle) part);
        }
        if (part instanceof Engine) {
            return this.toDto((Engine) part);
        }
        throw new IllegalArgumentException("Unknown entity type");
    }

    protected Muffle toEntity(MuffleDto muffleDto) {
        return Muffle.builder()
                .id(muffleDto.getId())
                .serialNumber(muffleDto.getSerialNumber())
                .length(muffleDto.getLength())
                .type(muffleDto.getType())
                .build();
    }

    protected Engine toEntity(EngineDto engineDto) {
        return Engine.builder()
                .id(engineDto.getId())
                .serialNumber(engineDto.getSerialNumber())
                .capacity(engineDto.getCapacity())
                .build();
    }

    protected MuffleDto toDto(Muffle muffle) {
        return MuffleDto.builder()
                .id(muffle.getId())
                .partType(PartType.MUFFLE)
                .serialNumber(muffle.getSerialNumber())
                .length(muffle.getLength())
                .type(muffle.getType())
                .build();
    }

    protected EngineDto toDto(Engine engine) {
        return EngineDto.builder()
                .id(engine.getId())
                .partType(PartType.ENGINE)
                .serialNumber(engine.getSerialNumber())
                .capacity(engine.getCapacity())
                .build();
    }
}
