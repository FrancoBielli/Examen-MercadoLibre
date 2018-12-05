package com.sistema.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.sistema.dto.Adn;
import com.sistema.model.Persona;
import com.sistema.service.PersonaService;
import com.sistema.utils.AdnUtils;
import com.sistema.utils.EstadisticaUtils;
import com.sistema.utils.PersonaUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api")
public class MutanteController {

    @Autowired
    PersonaUtils personaUtils;

    @Autowired
    AdnUtils adnUtils;

    @Autowired
    EstadisticaUtils estadisticaUtils;

    @RequestMapping(value = "/mutant", method = RequestMethod.POST)
    public ResponseEntity<String> mutant(@RequestBody String JsonAdn)
    {
        Adn adn = adnUtils.parseJson(JsonAdn);

        if(adn == null)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        //Primero verifico que lo ingresado cumpla con los requisitos básicos, si no los cumple se devuelve un error
        if(!adnUtils.verificarMatriz(adn))
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        //Verifico si la persona es un mutante o no
        Persona persona = personaUtils.isMutante(adn);
        //Guardo la persona en la base de datos, si falla devolver un error
        if(personaUtils.guardarPersona(persona) == null)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Verificar conexión a la base de datos");
        }
        if(persona.getMutante())
        {
            return ResponseEntity.status(HttpStatus.OK).body("Es Mutante");
        }
        else
        {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No es mutante");
        }
    }

    @RequestMapping(value="/stats", method = RequestMethod.GET, produces={ MediaType.APPLICATION_JSON_VALUE + "; charset=UTF-8"})
    public String Estadisticas()
    {
        return estadisticaUtils.obtenerEstadistica();
    }
}