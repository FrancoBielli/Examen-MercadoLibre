package com.sistema.controller;

import com.sistema.ApiRestApplication;
import com.sistema.service.AdnService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;

public class MutanteControllerTest {

    @Test
    public void MutanteBadRequest() {
        /*
            Si se ingresa una cadena que no se puede parsear se devuelve un error indicando que hubo un
            problema con la entrada.
            En este caso se ingresa una cadena vacía
        */
        ResponseEntity respuesta = new MutanteController().mutant("");

        assertEquals(respuesta.getStatusCode(), HttpStatus.BAD_REQUEST);
    }



    @Test
    public void estadisticas() {
    }
}
