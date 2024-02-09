package org.adaschool.proyectoReservas;

import org.adaschool.proyectoReservas.application.exception.ReservationException;
import org.adaschool.proyectoReservas.application.lasting.ERoles;
import org.adaschool.proyectoReservas.application.lasting.EStateReservation;
import org.adaschool.proyectoReservas.application.lasting.EStateTable;
import org.adaschool.proyectoReservas.application.mapper.ITableMapper;
import org.adaschool.proyectoReservas.application.service.TableService;
import org.adaschool.proyectoReservas.domain.dto.BookingDto;
import org.adaschool.proyectoReservas.domain.dto.TableDto;
import org.adaschool.proyectoReservas.domain.dto.UserDto;
import org.adaschool.proyectoReservas.domain.entity.Booking;
import org.adaschool.proyectoReservas.domain.entity.Table;
import org.adaschool.proyectoReservas.domain.entity.User;
import org.adaschool.proyectoReservas.domain.repository.TableRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class TableServiceTests {

    @MockBean
    TableRepository tableRepository;
    @MockBean
    ITableMapper mapperT;
    @Autowired
    TableService tableService;

    private Booking booking;
    private User user;
    private BookingDto bookingDto;
    private UserDto userDto;
    private Table table;
    private TableDto tableDto;

    @BeforeEach
    void setUp(){

        user = User.builder()
                .id(1)
                .name("Maria")
                .lastName("Quito")
                .email("mapis2321@gmail.com")
                .password("mapis2321")
                .enable(true)
                .document("1488523")
                .roles(ERoles.Client)
                .build();

        booking = Booking.builder()
                .id(1)
                .bookingDate(LocalDate.of(2023, 5, 10))
                .bookingHour(LocalTime.of(14, 30))
                .description("para un cumpleaños")
                .stateReservation(EStateReservation.Active)
                .user(user).build();

        table = table.builder()
                .id(1)
                .chairsNumber(6)
                .stateTable(EStateTable.Available).build();

        userDto = new UserDto
                (1,"Maria","Quito","mapis2321@gmail.com",
                        "mapis2321",true,"1488523",ERoles.Client);
        bookingDto = new BookingDto
                (1,LocalDate.of(2023, 5, 10),
                        LocalTime.of(14, 30),
                        "para un cumpleaños",EStateReservation.Active,userDto);
        tableDto = new TableDto(1,6,EStateTable.Available,bookingDto);
    }

    @Test
    void saveTable(){
        when(mapperT.toEntity(tableDto)).thenReturn(table);
        tableService.createTable(tableDto);

        assertNotNull(table);
        verify(tableRepository).save(table);
    }

    @Test
    void findTableById(){
        final Integer id = 1;

        when(mapperT.toDto(table)).thenReturn(tableDto);
        try {
            TableDto result = tableService.findTableById(id);

            assertNotNull(result);
            assertNotNull(bookingDto);
            assertEquals(bookingDto,result);
        }catch (ReservationException e) {
            e.printStackTrace();
        }
    }

    @Test
    void  notFoundById(){
        final Integer id = 1;

        when(tableRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(ReservationException.class,()-> tableService.findTableById(id));

    }

    @Test
    void allTables(){
        final Integer offset=0;
        final Integer limit=50;

        List<Table> tableList = Arrays.asList(table);
        List<TableDto> expectedList = Arrays.asList(mapperT.toDto(table));

        when(tableRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(tableList));
        when(mapperT.toDtoList(tableList)).thenReturn(expectedList);

        try{
            List<TableDto> result = tableService.listAllTables(offset,limit);
            assertEquals(expectedList,result);
        }catch (ReservationException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void updateTable(){
        final Integer id =1;
        Table tableM = table.builder()
                .id(1)
                .chairsNumber(12)
                .stateTable(EStateTable.NotAvailable).build();
        TableDto tableDtoM = new TableDto(1,12,EStateTable.NotAvailable,bookingDto);

        when(mapperT.toEntity(tableDtoM)).thenReturn(tableM);
        when(tableRepository.findById(id)).thenReturn(Optional.of(tableM));

        try {
            tableService.updateTable(id,tableDtoM);
            verify(tableRepository).save(tableM);
        }catch (ReservationException e) {
            throw new RuntimeException(e);
        }
    }
}
