package com.sistema.utils;

import com.sistema.dto.Estadistica;
import com.sistema.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class EstadisticaUtils {

    @Autowired
    PersonaService personaService;

    public String obtenerEstadistica()
    {
        BigDecimal mutantes = personaService.contarPersonas(true);
        BigDecimal humanos = personaService.contarPersonas(false);
        String ratio = calcularRatio(mutantes, humanos);
        return ratio;
    }

    public String calcularRatio(BigDecimal mutantes, BigDecimal humanos){

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
