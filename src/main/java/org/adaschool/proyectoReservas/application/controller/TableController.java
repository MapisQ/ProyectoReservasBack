package org.adaschool.proyectoReservas.application.controller;

import org.adaschool.proyectoReservas.application.exception.ReservationException;
import org.adaschool.proyectoReservas.application.service.TableService;
import org.adaschool.proyectoReservas.domain.dto.TableDto;
import org.adaschool.proyectoReservas.domain.entity.Table;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/table")
public record TableController(TableService tableService) {

    @PostMapping("/saveTable")
    public ResponseEntity<?> saveTable(@RequestBody TableDto tablesDto) {
        tableService.createTable(tablesDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("search/{idTable}")
    public ResponseEntity<?> findTableById
        (@PathVariable("idTable") Integer idTable) throws ReservationException {
        tableService.findTableById(idTable);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/allTables/{offset}/{limit}")
    public ResponseEntity<?> allTables
        (@PathVariable("offset") Integer offset, @PathVariable("limit") Integer limit) throws ReservationException {
        List<TableDto> tableDtoList = tableService.listAllTables(offset, limit);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("update/{idTable}")
    public ResponseEntity<?> updateTable
        (@PathVariable("idTable") Integer idTable, TableDto tableDto) throws ReservationException {
        tableService.updateTable(idTable, tableDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
