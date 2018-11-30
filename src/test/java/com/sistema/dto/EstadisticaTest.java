package com.sistema.dto;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.Assert.*;

public class EstadisticaTest {

    @Test
    public void EstadisticaHumanos() {
        Estadistica e = new Estadistica();
        e.setHumanos(new BigDecimal(20));

        assertEquals(e.getHumanos(), new BigDecimal(20));
    }

    @Test
    public void EstadisticaMutantes() {
        Estadistica e = new Estadistica();
        e.setMutantes(new BigDecimal(10));

        assertEquals(e.getMutantes(), new BigDecimal(10));
    }

    @Test
    public void EstadisticaRatio() {
        Estadistica e = new Estadistica();
        e.setRatio(new BigDecimal(1.5));

        assertEquals(e.getRatio(), new BigDecimal(1.5));
    }

    @Test
    public void EstadisticaRatioCalculada() {
        Estadistica e = new Estadistica();
        e.setMutantes(new BigDecimal(40));
        e.setHumanos(new BigDecimal(100));
        e.setRatio(e.getMutantes().divide(e.getHumanos(), 1, RoundingMode.HALF_EVEN));


        //Tuve que ponerle la escala al número para comparar porque por alguna razón el número creado no era exactamente 0.4
        //new BigDecimal(0.4) resultaba en: 0.40000000000000002220446049250313080847263336181640625
        assertEquals(e.getRatio(), new BigDecimal(0.4).setScale(1, RoundingMode.HALF_EVEN));
    }

    @Test
    public void EstadisticaToString() {
        Estadistica e = new Estadistica();
        e.setMutantes(new BigDecimal(40));
        e.setHumanos(new BigDecimal(100));
        e.setRatio(e.getMutantes().divide(e.getHumanos(), 1, RoundingMode.HALF_EVEN));

        assertEquals(e.toString(), "{\"count_mutant_dna\":40,\"count_human_dna\":100,\"ratio\":0.4}");
    }

}