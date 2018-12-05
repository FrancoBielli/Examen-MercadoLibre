package com.sistema.utils;

import com.sistema.dto.Adn;
import com.sistema.model.Persona;
import com.sistema.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersonaUtils {

    @Autowired
    PersonaService personaService;

    AdnUtils adnUtils = new AdnUtils();

    public Persona isMutante(Adn adn)
    {
        try {
            int contador;
            Persona persona = new Persona();
            //Busco las coincidencias, reemplazo contador en cada línea ya que se incrementa dentro del método.
            contador = adnUtils.contarHorizontal(adn);
            contador = adnUtils.contarVertical(adn, contador);
            contador = adnUtils.contarDiagonalDerecha(adn, contador);
            contador = adnUtils.contarDiagonalIzquierda(adn, contador);
            if(contador >= 2)
            {
                persona.setMutante(true);
                persona.setAdn(adn.getDna().toString());
            }
            else
            {
                persona.setAdn(adn.getDna().toString());
                persona.setMutante(false);
            }
            return persona;
        } catch (Exception e) {
            return null;
        }
    }

    public Persona guardarPersona(Persona persona)
    {
        try
        {
            //Verifico si existe la persona
            Persona p = personaService.findById(persona.getAdn());

            //Si la persona existe no se guarda ni se actualiza la estadística
            if(p == null)
            {
                //Guardo la nueva persona
                return personaService.savePersona(persona);
            }
            return p;
        }
        catch (Exception ex)
        {
            return null;
        }

    }
}
