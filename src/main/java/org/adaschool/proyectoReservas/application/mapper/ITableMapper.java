package org.adaschool.proyectoReservas.application.mapper;

import org.adaschool.proyectoReservas.application.mapper.base.IBaseMapper;
import org.adaschool.proyectoReservas.domain.dto.TableDto;
import org.adaschool.proyectoReservas.domain.entity.Table;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ITableMapper extends IBaseMapper {

    Table toEntity(TableDto dto);
    TableDto toDto(Table entity);
    List<Table> toEntitylist(List<TableDto> dtoList);
    List<TableDto> toDtoList(List<Table> entityList);
}
