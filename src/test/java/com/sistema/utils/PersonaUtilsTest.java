package com.sistema.utils;

import com.sistema.dto.Adn;
import com.sistema.model.Persona;
import com.sistema.repository.PersonaRepository;
import com.sistema.service.PersonaService;
import com.sistema.service.impl.PersonaServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class PersonaUtilsTest {

    PersonaUtils personaUtils = new PersonaUtils();

    @TestConfiguration
    static class PersonaServiceImplTestContextConfiguration {

        @Bean
        public PersonaService personaService() {
            return new PersonaServiceImpl();
        }
    }

    @Autowired
    private PersonaService personaService;

    @MockBean
    private PersonaRepository personaRepository;

    @Test
    public void isMutanteTrue() {
        Adn adn = new Adn();
        List<String> ListAdn = new ArrayList<>();
        ListAdn.add("ATGCGA");
        ListAdn.add("CAGTGC");
        ListAdn.add("TTATGT");
        ListAdn.add("AGAAGG");
        ListAdn.add("CCCCTA");
        ListAdn.add("TCACTG");
        adn.setDna(ListAdn);


        Persona p = personaUtils.isMutante(adn);

        //Persona de referencia
        String dna = "[ATGCGA, CAGTGC, TTATGT, AGAAGG, CCCCTA, TCACTG]";
        Persona persona = new Persona();
        persona.setAdn(dna);
        persona.setMutante(true);

        assertEquals(persona.getAdn(), p.getAdn());
    }

    @Test
    public void isMutanteFalse() {
        Adn adn = new Adn();
        List<String> ListAdn = new ArrayList<>();
        ListAdn.add("CTGCGA");
        ListAdn.add("CAGTGC");
        ListAdn.add("TTATGT");
        ListAdn.add("AGAAGG");
        ListAdn.add("ACCCTA");
        ListAdn.add("TCACTG");
        adn.setDna(ListAdn);


        Persona p = personaUtils.isMutante(adn);

        //Persona de referencia
        String dna = "[CTGCGA, CAGTGC, TTATGT, AGAAGG, ACCCTA, TCACTG]";
        Persona persona = new Persona();
        persona.setAdn(dna);
        persona.setMutante(false);

        assertEquals(persona.getAdn(), p.getAdn());
        assertEquals(persona.getMutante(), p.getMutante());
    }

    @Test
    public void guardarPersonaNueva() {

        String adn = "[CTGCGA, CAGTGC, TTATGT, AGAAGG, ACCCTA, TCACTG]";
        Persona personaGuardar = new Persona();
        personaGuardar.setAdn(adn);
        personaGuardar.setMutante(false);

        Mockito.when(personaRepository.findByAdn(personaGuardar.getAdn())).thenReturn(null);

        Persona found = personaService.findById(personaGuardar.getAdn());

        Mockito.when(personaRepository.save(personaGuardar)).thenReturn(personaGuardar);

        Persona saved = personaService.savePersona(personaGuardar);

        personaUtils.personaService = personaService;

        Persona p = personaUtils.guardarPersona(personaGuardar);

        assertEquals(personaGuardar, p);
    }

    @Test
    public void guardarPersonaExistente() {

        String adn = "[CTGCGA, CAGTGC, TTATGT, AGAAGG, ACCCTA, TCACTG]";
        Persona personaGuardar = new Persona();
        personaGuardar.setAdn(adn);
        personaGuardar.setMutante(false);

        Mockito.when(personaRepository.findByAdn(personaGuardar.getAdn())).thenReturn(personaGuardar);

        Persona found = personaService.findById(personaGuardar.getAdn());

        personaUtils.personaService = personaService;

        Persona p = personaUtils.guardarPersona(personaGuardar);

        assertEquals(personaGuardar, p);

    }
}