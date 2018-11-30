package com.sistema.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class AdnTest {

    @Test
    public void classDna() {
        /*
            Ingreso una lista de String por el set y comparo que sea igual a la lista ingresada en el get.
         */
        Adn adn = new Adn();
        List<String> dna = new ArrayList<>();
        dna.add("ATGCGA");
        dna.add("CAGTGC");
        dna.add("TTATGT");
        dna.add("AGAAGG");
        dna.add("CCCCTA");
        dna.add("TCACTG");

        adn.setDna(dna);

        assertEquals(adn.getDna(), dna);
    }
}