package org.adaschool.proyectoReservas.domain.entity;


import jakarta.persistence.*;

import lombok.*;
import org.adaschool.proyectoReservas.application.lasting.EStateTable;

import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@jakarta.persistence.Table(name = "\"table\"")
public class Table {
    @Id
    @SequenceGenerator(name = "id_table", sequenceName = "id_table")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_table_generator")
    private Integer id;
    private Integer chairsNumber;

    @Enumerated(EnumType.ORDINAL)
    private EStateTable stateTable;

    @ManyToOne
    @JoinColumn(name = "id_table")
    private Booking booking;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Table table = (Table) o;
        return Objects.equals(id, table.id) && Objects.equals(chairsNumber, table.chairsNumber) && stateTable == table.stateTable && Objects.equals(booking, table.booking);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, chairsNumber, stateTable, booking);
    }
}
