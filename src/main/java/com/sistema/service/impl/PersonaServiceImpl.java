package com.sistema.service.impl;

import com.sistema.dto.Estadistica;
import com.sistema.model.Persona;
import com.sistema.repository.PersonaRepository;
import com.sistema.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

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
        //Antes de guardar la persona me fijo si existe
        if(personaExiste(persona.getAdn()))
        {
            //Si existe devuelvo la que ya existe pero no la guardo
            return findById(persona.getAdn());
        }
        else
        {
            //Si no existe la guardo
            return personaRepository.save(persona);
        }
    }

    @Override
    public BigDecimal contarPersonas(Boolean mutante)
    {
        return new BigDecimal(personaRepository.countPersonaByMutante(mutante));
    }

    @Override
    public String calcularRatio(){
        BigDecimal mutantes = contarPersonas(true);
        BigDecimal humanos = contarPersonas(false);

        Estadistica estadistica = new Estadistica();
        estadistica.setHumanos(humanos);
        estadistica.setMutantes(mutantes);
        estadistica.setRatio(BigDecimal.ZERO);

        if(humanos.compareTo(BigDecimal.ZERO) != 0 && mutantes.compareTo(BigDecimal.ZERO) != 0)
        {
            estadistica.setRatio(mutantes.divide(humanos, 1, RoundingMode.HALF_EVEN));

            return estadistica.toString();
        }
        else
        {
            return estadistica.toString();
        }
    }
}
