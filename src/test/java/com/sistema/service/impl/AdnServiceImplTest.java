package com.sistema.service.impl;

import com.sistema.model.Adn;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class AdnServiceImplTest {

    @Test
    public void verificarMatrizSuccess() {
        /*
            Este test debe pasar porque se cumplen los requisitos básicos para poder evaluar el ADN:
            - Hay al menos 4 filas (Sin 4 filas no se pueden evaluar coincidencias en sentido vertical ni oblicuo)
            - La matriz es cuadrada (Se especifica que la matriz tiene la msima cantidad de filas que de columnas NxN)
            - Solo se han ingresado caracteres permitidos (A,T,C,G).
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

        Boolean resultado = new AdnServiceImpl().verificarMatriz(adn);

        assertEquals(resultado, true);
    }

    @Test
    public void verificarMatrizCorta(){
        /*
            Este test no debe pasar porque no tiene el mínimo de 4 filas
         */
        Adn adn = new Adn();
        List<String> dna = new ArrayList<>();
        dna.add("ATGCGA");
        dna.add("CAGTGC");
        dna.add("TTATGT");

        adn.setDna(dna);

        Boolean resultado = new AdnServiceImpl().verificarMatriz(adn);

        assertEquals(resultado, false);
    }

    @Test
    public void verificarMatrizNoCuadrada(){
        /*
            Este test no debe pasar porque la matriz no es cuadrada NxN
         */
        Adn adn = new Adn();
        List<String> dna = new ArrayList<>();
        dna.add("ATGCGA");
        dna.add("CAGTGC");
        dna.add("TTATGT");
        dna.add("AGAAGGA");
        dna.add("CCCCTA");
        dna.add("TCACTG");

        adn.setDna(dna);

        Boolean resultado = new AdnServiceImpl().verificarMatriz(adn);

        assertEquals(resultado, false);
    }

    @Test
    public void verificarMatrizCaracteresNoAdmitidos() {
        /*
            Este test debe fallar porque se han ingresado caracteres no admitidos en la primer cadena "AQWEGA"
         */

        Adn adn = new Adn();
        List<String> dna = new ArrayList<>();
        dna.add("AQWEGA");
        dna.add("CAGTGC");
        dna.add("TTATGT");
        dna.add("AGAAGG");
        dna.add("CCCCTA");
        dna.add("TCACTG");

        adn.setDna(dna);

        Boolean resultado = new AdnServiceImpl().verificarMatriz(adn);

        assertEquals(resultado, false);
    }

    @Test
    public void contarHorizontalSuccess() {
        /*
            Este test debe devolver 1 porque solo hay una secuencia correcta en la primera fila.
         */

        Adn adn = new Adn();
        List<String> dna = new ArrayList<>();
        dna.add("AAAACC");
        dna.add("CCGTGC");
        dna.add("TTATGT");
        dna.add("AGAAGG");
        dna.add("CACCTA");
        dna.add("TCACTG");

        adn.setDna(dna);

        int resultado = new AdnServiceImpl().contarHorizontal(adn);

        assertEquals(resultado, 1);
    }

    @Test
    public void contarHorizontal()
    {
        /*
            Este test debe devolver 2 porque una vez que se encontraron 2 coincidencias se puede determinar
            que el individuo es un mutante por lo que no hace falta verificar el resto de las filas
         */
        Adn adn = new Adn();
        List<String> dna = new ArrayList<>();
        dna.add("AAAAAA");
        dna.add("CCCCCC");
        dna.add("TTATGT");
        dna.add("AGAAGG");
        dna.add("CACCTA");
        dna.add("GGGGGG");

        adn.setDna(dna);

        int resultado = new AdnServiceImpl().contarHorizontal(adn);

        assertEquals(resultado, 2);

    }

    @Test
    public void contarHorizontalCero()
    {
        /*
            Este test debe devolver 0 porque no hay ninguna secuencia horizontal de 4 letras iguales consecutivas
         */

        Adn adn = new Adn();
        List<String> dna = new ArrayList<>();
        dna.add("AACCAA");
        dna.add("CCGGCC");
        dna.add("TTATGT");
        dna.add("AGAAGG");
        dna.add("CACCTA");
        dna.add("GCACTG");

        adn.setDna(dna);

        int resultado = new AdnServiceImpl().contarHorizontal(adn);

        assertEquals(resultado, 0);
    }

    @Test
    public void contarVertical() {
        /*
            Este test debe devolver 1 porque solo hay una secuencia válida en la primera columna
         */
        Adn adn = new Adn();
        List<String> dna = new ArrayList<>();
        dna.add("ATGCGA");
        dna.add("AAGTCC");
        dna.add("ATATGT");
        dna.add("AGAAGG");
        dna.add("CCCCTA");
        dna.add("TCACTG");

        adn.setDna(dna);

        int resultado = new AdnServiceImpl().contarVertical(adn, 0);

        assertEquals(resultado, 1);
    }

    @Test
    public void contarVerticalDos() {
        /*
            Este test debe devolver 2 porque solo hay una secuencia válida en la primera columna pero consideramos una
            coincidencia previa
         */
        Adn adn = new Adn();
        List<String> dna = new ArrayList<>();
        dna.add("ATGCGA");
        dna.add("AAGTCC");
        dna.add("ATATGT");
        dna.add("AGAAGG");
        dna.add("CCCCTA");
        dna.add("TCACTG");

        adn.setDna(dna);

        int resultado = new AdnServiceImpl().contarVertical(adn, 1);

        assertEquals(resultado, 2);
    }

    @Test
    public void contarVerticalSkip() {
        /*
            Este test debe devolver 2 porque ya existen dos secuencias válidas anteriores a la evaluación vertical
            por lo tanto se determina que el individuo es mutante sin necesidad de seguir buscando coincidencias
         */
        Adn adn = new Adn();
        List<String> dna = new ArrayList<>();
        dna.add("AAAAGA");
        dna.add("AAAACC");
        dna.add("ATATGT");
        dna.add("AGAAGG");
        dna.add("CCCCTA");
        dna.add("TCACTG");

        adn.setDna(dna);

        int resultado = new AdnServiceImpl().contarVertical(adn, 2);

        assertEquals(resultado, 2);
    }

    @Test
    public void contarVerticalSinCoincidencias() {
        /*
            Este test debe devolver 0 porque no existen coincidencias verticales y
            no se toman en cuenta las demás coincidencias
         */
        Adn adn = new Adn();
        List<String> dna = new ArrayList<>();
        dna.add("ATGCGA");
        dna.add("AAGTCC");
        dna.add("ATATGT");
        dna.add("GGAAGG");
        dna.add("CCCCTA");
        dna.add("TCACTG");

        adn.setDna(dna);

        int resultado = new AdnServiceImpl().contarVertical(adn, 0);

        assertEquals(resultado, 0);
    }



    @Test
    public void contarDiagonalDerecha() {
        /*
            Este test debe devolver 1 porque solo hay una secuencia válida en la diagonal principal y no se consideran
            coincidencias previas
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

        int resultado = new AdnServiceImpl().contarDiagonalDerecha(adn, 0);

        assertEquals(resultado, 1);
    }

    @Test
    public void contarDiagonalDerechaSkip() {
        /*
            Este test debe devolver 2 porque consideramos que ya tenemos 2 coincidencias previas por lo tanto no es
            necesario evaluar las diagonales
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

        int resultado = new AdnServiceImpl().contarDiagonalDerecha(adn, 2);

        assertEquals(resultado, 2);
    }

    @Test
    public void contarDiagonalDerechaCero() {
        /*
            Este test debe devolver 0 porque no hay una secuencia válida en ninguna diagonal y no se consideran
            coincidencias previas
         */
        Adn adn = new Adn();
        List<String> dna = new ArrayList<>();
        dna.add("ATGCGA");
        dna.add("CCGTGC");
        dna.add("TTATGT");
        dna.add("AGAAGG");
        dna.add("CCCCTA");
        dna.add("TCACTG");

        adn.setDna(dna);

        int resultado = new AdnServiceImpl().contarDiagonalDerecha(adn, 0);

        assertEquals(resultado, 0);
    }

    @Test
    public void contarDiagonalDerechaMultiples() {
        /*
            Este test debe devolver 2 porque hay una secuencia válida en la diagonal principal y otra en
            la diagonal inmediata a la derecha y no se consideran coincidencias previas
         */
        Adn adn = new Adn();
        List<String> dna = new ArrayList<>();
        dna.add("ATGCGA");
        dna.add("CATTGC");
        dna.add("TTATGT");
        dna.add("AGAATG");
        dna.add("CCCCTA");
        dna.add("TCACTG");

        adn.setDna(dna);

        int resultado = new AdnServiceImpl().contarDiagonalDerecha(adn, 0);

        assertEquals(resultado, 2);
    }

    @Test
    public void contarDiagonalIzquierda() {
        /*
            Este test debe devolver 0 porque no hay coincidencias en el sentido de la diagonal secundaria
            No se consideran coincidencias previas
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

        int resultado = new AdnServiceImpl().contarDiagonalIzquierda(adn, 0);

        assertEquals(resultado, 0);
    }

    @Test
    public void contarDiagonalIzquierdaSkip() {
        /*
            Este test debe devolver 2 porque se consideran 2 secuencias válidas previas
            Y por lo tanto no es necesario evaluar las coincidencias
         */
        Adn adn = new Adn();
        List<String> dna = new ArrayList<>();
        dna.add("ATGCGA");
        dna.add("CAGTAC");
        dna.add("TTAAGT");
        dna.add("AGAAGG");
        dna.add("CCCCTA");
        dna.add("TCACTG");

        adn.setDna(dna);

        int resultado = new AdnServiceImpl().contarDiagonalIzquierda(adn, 2);

        assertEquals(resultado, 2);
    }

    @Test
    public void contarDiagonalIzquierdaCoincidencias() {
        /*
            Este test debe devolver 2 porque existen 2 secuencias válidas
            No se consideran coincidencias anteriores
         */
        Adn adn = new Adn();
        List<String> dna = new ArrayList<>();
        dna.add("ATGCGA");
        dna.add("CAGTAC");
        dna.add("TTAAGA");
        dna.add("AGAAAG");
        dna.add("CCCATA");
        dna.add("TCACTG");

        adn.setDna(dna);

        int resultado = new AdnServiceImpl().contarDiagonalIzquierda(adn, 0);

        assertEquals(resultado, 2);
    }
}