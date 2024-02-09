package org.adaschool.proyectoReservas.domain.entity;


import jakarta.persistence.*;
import lombok.*;
import org.adaschool.proyectoReservas.application.lasting.EStateReservation;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@jakarta.persistence.Table(name = "booking")
public class Booking {

    @Id
    @SequenceGenerator(name = "id_booking", sequenceName = "id_booking")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_booking_generator")
    private Integer id;
    private LocalDate bookingDate;
    private LocalTime bookingHour;
    private String description;

    @Enumerated(EnumType.ORDINAL)
    private EStateReservation stateReservation;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @OneToMany
    @ToString.Exclude
    private List<Table> table;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return Objects.equals(id, booking.id) && Objects.equals(bookingDate, booking.bookingDate) && Objects.equals(bookingHour, booking.bookingHour) && Objects.equals(description, booking.description) && stateReservation == booking.stateReservation && Objects.equals(user, booking.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bookingDate, bookingHour, description, stateReservation, user);
    }
}
