package com.sistema.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.sistema.dto.Adn;
import org.springframework.stereotype.Component;

@Component
public class AdnUtils {

    //Convertir String de JSON en un array de Strings.
    public Adn parseJson(String cadenaADN)
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


    //Verificar que la matriz ingresada cumpla con ciertos requisitos básicos antes de verificar si es mutante o no
    public boolean verificarMatriz(Adn adn)
    {
        return rangoMinimo(adn) && verificarCaracteres(adn) && matrizCuadrada(adn);
    }

    //Verificar que sea una matriz con la que se pueda trabajar
    private boolean rangoMinimo( Adn adn) {
        //Si la matriz no es de al menos 4x4 no se puede analizar
        return adn.getDna().size() > 3;
    }

    //Verificar que sea una matriz de NxN
    private boolean matrizCuadrada(Adn adn) {
        //Si la cantidad de cadenas es diferente al largo de las cadenas entonces no es una matriz cuadrada
        int filas = adn.getDna().size();
        for(String s : adn.getDna())
        {
            if(s.length()!= filas)
            {
                return false;
            }
        }
        return true;
    }

    //Verificar que se hayan ingresado solo caracteres permitidos
    private boolean verificarCaracteres( Adn adn) {
        //Si se ingresa un caracter diferente a los permitidos (A, T, C y G)
        for(String s : adn.getDna())
        {
            if(!s.matches("[ATCG]+"))
            {
                return false;
            }
        }
        return true;
    }

    //Verificar que los cuatro caracteres ingresados sean iguales
    private boolean CompararCaracteres(char A, char B, char C, char D)
    {
        return A == B && A == C && A == D;
    }

    public int contarHorizontal(Adn adn)
    {
        int cont = 0;
        for(String s : adn.getDna())
        {
            //Recorrer letra por letra de la cadena
            //Si al menos una cadena contiene 4 caracteres iguales consecutivos eso significa que el adn es mutante
            for(int i = 0; i<s.length()-3; i++)
            {
                if(CompararCaracteres(s.charAt(i), s.charAt(i+1), s.charAt(i+2), s.charAt(i+3)))
                {
                    cont++;
                    if(cont >= 2)
                    {
                        return cont;
                    }
                }
            }
        }
        return cont;
    }

    //Verifico de forma vertical
    public int contarVertical(Adn adn, int cont)
    {
        if(verificarContador(cont))
        {
            return cont;
        }

        for(int i = 0; i < adn.getDna().size(); i++)
        {
            for(int j = 0; j < adn.getDna().get(i).length()-3; j++)
            {
                if(CompararCaracteres(adn.getDna().get(j).charAt(i), adn.getDna().get(j+1).charAt(i), adn.getDna().get(j+2).charAt(i), adn.getDna().get(j+3).charAt(i)))
                {
                    cont++;
                    if(cont >= 2)
                    {
                        return cont;
                    }
                }
            }
        }
        return cont;
    }

    //Recorro las diagonales hacia la derecha (mismo sentido que la diagonal principal)
    public int contarDiagonalDerecha(Adn adn, int cont)
    {
        if(verificarContador(cont))
        {
            return cont;
        }
        for(int i = 0; i< adn.getDna().size()-3; i++)
        {
            for(int j = 0; j<adn.getDna().get(i).length()-3 ; j++)
            {
                if(CompararCaracteres(adn.getDna().get(i).charAt(j), adn.getDna().get(i+1).charAt(j+1), adn.getDna().get(i+2).charAt(j+2), adn.getDna().get(i+3).charAt(j+3)))
                {
                    cont++;
                    if(cont >= 2)
                    {
                        return cont;
                    }
                }
            }
        }
        return cont;
    }

    //Recorro las diagonales hacia la izquierda (mismo sentido que la diagonal secundaria)
    public int contarDiagonalIzquierda(Adn adn, int cont)
    {
        if(verificarContador(cont))
        {
            return cont;
        }
        for(int i = 0; i < adn.getDna().size()-3; i++)
        {
            int largo = adn.getDna().get(i).length();
            for(int j = 0; j < largo-3; j++)
            {
                if(CompararCaracteres(adn.getDna().get(i).charAt(largo-j-1), adn.getDna().get(i+1).charAt(largo-j-2),adn.getDna().get(i+2).charAt(largo-j-3), adn.getDna().get(i+3).charAt(largo-j-4)))
                {
                    cont++;
                    if(cont >= 2)
                    {
                        return cont;
                    }
                }
            }
        }
        return cont;
    }

    //Verificar que el contador sea menor que 2 para no tener que hacer verificaciones innecesarias
    private boolean verificarContador(int contador)
    {
        return contador >= 2;
    }
}

