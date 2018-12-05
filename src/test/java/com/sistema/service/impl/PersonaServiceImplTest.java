package com.sistema.service.impl;

import com.sistema.ApiRestApplication;
import com.sistema.dto.Estadistica;
import com.sistema.model.Persona;
import com.sistema.repository.PersonaRepository;
import com.sistema.service.PersonaService;
import com.sistema.utils.EstadisticaUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class PersonaServiceImplTest {

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
    public void findById() {

        String adn = "[\"ATGCGA\",\"CAGTGC\",\"TTATGT\",\"AGAAGG\",\"CCCCTA\",\"TCACTG\"]";

        Persona persona = new Persona();
        persona.setAdn(adn);
        persona.setMutante(true);

        Mockito.when(personaRepository.findByAdn(persona.getAdn())).thenReturn(persona);

        Persona found = personaService.findById(adn);

        assertEquals(found.getAdn(), adn);
    }

    @Test
    public void savePersona() {
        Persona persona = new Persona();
        persona.setAdn("[\"ATGCGA\",\"CAGTGC\",\"TTATGT\",\"AGAAGG\",\"CCCCTA\",\"TCACTG\"]");
        persona.setMutante(true);
        Mockito.when(personaRepository.save(persona)).thenReturn(persona);

        Persona found = personaService.savePersona(persona);

        assertEquals(found, persona);
    }

    @Test
    public void contarMutantes()
    {
        List<Persona> personas = new ArrayList<>();
        //Creo 2 personas mutantes y un humano
        Persona p1 = new Persona();
        p1.setMutante(true);
        p1.setAdn("[\"ATGCGA\",\"CAGTGC\",\"TTATGT\",\"AGAAGG\",\"CCCCTA\",\"TCACTG\"]");
        personas.add(p1);
        Persona p2 = new Persona();
        p2.setMutante(true);
        p2.setAdn("[\"ATGCGA\",\"CAGTGC\",\"TTATGT\",\"AGAAGA\",\"CCCCTA\",\"TCACTG\"]");
        personas.add(p2);
        Persona p3 = new Persona();
        p3.setMutante(false);
        p3.setAdn("[\"CTGCGA\",\"CAGTGC\",\"TTATGT\",\"AGAAGG\",\"CACCTA\",\"TCACTG\"]");
        personas.add(p3);

        //Como sé que hay solo 2 mutantes en la cadena de 3 personas, cuando invoco al método devuelvo 2.
        //Mockito.when(personaService.contarPersonas(true)).thenReturn(new BigDecimal(2));
        Mockito.when(personaRepository.countPersonaByMutante(true)).thenReturn((long)2);
        int cont = 0;
        for(Persona p : personas)
        {
            if(p.getMutante() == true)
            {
                cont++;
            }
        }

        assertEquals(personaService.contarPersonas(true), new BigDecimal (cont));
    }
}