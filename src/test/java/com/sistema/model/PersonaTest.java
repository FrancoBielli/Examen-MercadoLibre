package com.sistema.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class PersonaTest {

    @Test
    public void testPersonaAdn() {
        Persona p = new Persona();

        p.setAdn("adn");

        assertEquals(p.getAdn(), "adn");
    }

    @Test
    public void testPersonaMutante() {
        Persona p = new Persona();

        p.setMutante(true);

        assertEquals(p.getMutante(), true);
    }
}