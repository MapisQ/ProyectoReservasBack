package org.adaschool.bookingproject.application.service;

import org.adaschool.bookingproject.application.exception.ReservationException;
import org.adaschool.bookingproject.application.lasting.EMessage;
import org.adaschool.bookingproject.application.mapper.ITableMapper;
import org.adaschool.bookingproject.domain.dto.TableDto;
import org.adaschool.bookingproject.domain.entity.Table;
import org.adaschool.bookingproject.domain.repository.TableRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record TableService(TableRepository tablesRepository, ITableMapper mapper) {

    public void createTable(TableDto tableDto) {
        Table table = mapper.toEntity(tableDto);
        tablesRepository.save(table);
    }

    public TableDto findTableById(Integer idTable) throws ReservationException {
        Table table = tablesRepository.findById(idTable).orElseThrow(()-> new ReservationException(EMessage.ID_NOT_FOUND));
        return mapper.toDto(table);
    }

    public List<TableDto> listAllTables(Integer offset, Integer limit) throws ReservationException {

        Pageable pageable = PageRequest.of(offset,limit);
        Page<Table> tables = tablesRepository.findAll(pageable);
        if (tables.getContent().isEmpty()){
            throw new ReservationException(EMessage.DATA_NOT_FOUND);
        }
        return mapper.toDtoList(tables.getContent());
    }

    public void updateTable(Integer idTable, TableDto tableDto) throws ReservationException {
        tablesRepository.findById(idTable).orElseThrow(()-> new ReservationException(EMessage.TABLE_NOT_FOUND));
        Table table = mapper.toEntity(tableDto);
        tablesRepository.save(table);
    }
}
