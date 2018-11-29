package com.sistema.service;

import com.sistema.model.Adn;

public interface AdnService {

    public boolean verificarMatriz(Adn adn);

    public int contarHorizontal(Adn adn);

    public int contarVertical(Adn adn, int cont);

    public int contarDiagonalDerecha(Adn adn, int cont);

    public int contarDiagonalIzquierda(Adn adn, int cont);

}
