package com.sistema.service;

import com.sistema.model.Persona;

import java.math.BigDecimal;

public interface PersonaService {

    Persona findById(String adn);

    Boolean personaExiste(String id);

    Persona savePersona(Persona persona);

    BigDecimal contarPersonas(Boolean mutante);
}
