package com.sistema.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "persona")
public class Persona {

    @Id
    @Column(name = "adn")
    private String adn;

    @NotNull
    @Column(name = "mutante")
    private Boolean mutante;

    public String getAdn() {
        return adn;
    }

    public void setAdn(String adn) {
        this.adn = adn;
    }

    public Boolean getMutante() {
        return mutante;
    }

    public void setMutante(Boolean mutante) {
        this.mutante = mutante;
    }
}
