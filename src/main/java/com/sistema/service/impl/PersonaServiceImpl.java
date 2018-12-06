package com.sistema.service.impl;

import com.sistema.model.Persona;
import com.sistema.repository.PersonaRepository;
import com.sistema.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    PersonaRepository personaRepository;

    @Override
    public Persona findById(String adn) {
        //Buscar y devolver la persona por el ID
        return personaRepository.findByAdn(adn);
    }

    @Override
    public Boolean personaExiste(String adn) {
        //Verificar si la persona existe
        if(personaRepository.findByAdn(adn) == null)
        {
            return false;
        }
        return true;
    }

    @Override
    public Persona savePersona(Persona persona) {
        return personaRepository.save(persona);
    }

    @Override
    public BigDecimal contarPersonas(Boolean mutante)
    {
        return new BigDecimal(personaRepository.countPersonaByMutante(mutante));
    }
}