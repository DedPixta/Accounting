package ru.test.task.carservice.mapper;

public interface Mapper <T,V>{
    T toDto(V entity);
    V toEntity(T dto);
}
