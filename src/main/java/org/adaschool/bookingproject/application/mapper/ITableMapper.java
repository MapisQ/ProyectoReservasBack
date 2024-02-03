package org.adaschool.bookingproject.application.mapper;

import org.adaschool.bookingproject.application.mapper.base.IBaseMapper;
import org.adaschool.bookingproject.domain.dto.TableDto;
import org.adaschool.bookingproject.domain.entity.Table;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ITableMapper extends IBaseMapper {

    Table toEntity(TableDto dto);
    TableDto toDto(Table entity);
    List<Table> toEntitylist(List<TableDto> dtoList);
    List<TableDto> toDtoList(List<Table> entityList);
}
