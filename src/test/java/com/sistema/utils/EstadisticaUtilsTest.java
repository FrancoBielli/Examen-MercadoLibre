package com.sistema.utils;

import com.sistema.dto.Estadistica;
import com.sistema.repository.PersonaRepository;
import com.sistema.service.PersonaService;
import com.sistema.service.impl.PersonaServiceImpl;
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

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class EstadisticaUtilsTest {

    EstadisticaUtils estadisticaUtils = new EstadisticaUtils();

    @Test
    public void calcularRatio()
    {

        Estadistica estadistica = new Estadistica();
        estadistica.setHumanos(new BigDecimal(10));
        estadistica.setMutantes(new BigDecimal(5));

        estadistica.setRatio(estadistica.getMutantes().divide(estadistica.getHumanos(), 1, RoundingMode.HALF_EVEN));


        assertEquals(estadisticaUtils.calcularRatio(new BigDecimal(5), new BigDecimal(10)), estadistica.toString());
    }

    @Test
    public void calcularRatioZero()
    {
        assertEquals(estadisticaUtils.calcularRatio(BigDecimal.ZERO, new BigDecimal(10)), "{\"count_mutant_dna\":0,\"count_human_dna\":10,\"ratio\":0}");
    }
}