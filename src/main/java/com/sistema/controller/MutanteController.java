package com.sistema.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.sistema.model.Adn;
import com.sistema.model.Persona;
import com.sistema.service.AdnService;
import com.sistema.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MutanteController {

    @Autowired
    PersonaService personaService;

    @Autowired
    AdnService adnService;

    @RequestMapping(value = "/mutant", method = RequestMethod.POST)
    public ResponseEntity<String> mutant(@RequestBody String JsonAdn)
    {
        Adn adn = parseJson(JsonAdn);

        if(adn == null)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        //Primero verifico que lo ingresado cumpla con los requisitos básicos, si no los cumple se devuelve un error
        if(!adnService.verificarMatriz(adn))
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if(isMutante(adn))
        {
            //Guardo el ADN que se está verificando en la base de datos.

            guardarPersona(adn, true);
            return ResponseEntity.ok("Es Mutante");
        }
        else
        {
            guardarPersona(adn, false);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No es mutante");
        }
    }

    @RequestMapping(value="/stats", method = RequestMethod.GET, produces={ MediaType.APPLICATION_JSON_VALUE + "; charset=UTF-8"})
    public String Estadisticas()
    {
        return obtenerEstadistica();
    }

    private boolean isMutante(Adn adn)
    {
        try {
            int contador;
            //Busco las coincidencias, reemplazo contador en cada línea ya que se incrementa dentro del método.
            contador = adnService.contarHorizontal(adn);
            contador = adnService.contarVertical(adn, contador);
            contador = adnService.contarDiagonalDerecha(adn, contador);
            contador = adnService.contarDiagonalIzquierda(adn, contador);
            return contador >= 2;
        } catch (Exception e) {
            return false;
        }
    }
    //Convertir String de JSON en un array de Strings.
    private Adn parseJson(String cadenaADN)
    {
        try
        {
            ObjectMapper oMapper = new ObjectMapper().setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE).configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            return oMapper.readValue(cadenaADN, Adn.class);
        }
        catch (Exception ex)
        {
            return null;
        }
    }

    private String obtenerEstadistica()
    {
        String ratio = personaService.calcularRatio();
        return ratio;
    }

    private void guardarPersona(Adn adn, Boolean mutante)
    {
        //Verifico si existe la persona
        Persona p = personaService.findById(adn.getDna().toString());

        //Si la persona existe no se guarda ni se actualiza la estadística
        if(p == null)
        {
            //Guardo la nueva persona
            Persona persona = new Persona();
            persona.setAdn(adn.getDna().toString());
            persona.setMutante(mutante);
            if(personaService.savePersona(persona) == null)
            {
                //error
            }
        }
    }
}
