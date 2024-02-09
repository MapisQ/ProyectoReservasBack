package org.adaschool.proyectoReservas.domain.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;
import org.adaschool.proyectoReservas.application.lasting.ERoles;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "\"user\"",uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
public class User implements UserDetails{
    @Id
    @SequenceGenerator(name = "id_user",sequenceName = "id_user")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_user_generator")
    private Integer id;
    private String name;
    private String lastName;
    private String email;
    private String password;
    private boolean enable;
    private String document;

    @Enumerated(EnumType.ORDINAL)
    private ERoles roles;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return enable == user.enable && Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(lastName, user.lastName) && Objects.equals(password, user.password) && Objects.equals(email, user.email) && roles == user.roles && Objects.equals(document, user.document);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastName, password, email, enable, roles, document);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enable;
    }
}
