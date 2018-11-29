package com.sistema.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class Estadistica implements Serializable {
    private BigDecimal Humanos;
    private BigDecimal Mutantes;
    private BigDecimal ratio;

    public BigDecimal getHumanos() {
        return Humanos;
    }

    public void setHumanos(BigDecimal humanos) {
        Humanos = humanos;
    }

    public BigDecimal getMutantes() {
        return Mutantes;
    }

    public void setMutantes(BigDecimal mutantes) {
        Mutantes = mutantes;
    }

    public BigDecimal getRatio() {
        return ratio;
    }

    public void setRatio(BigDecimal ratio) {
        this.ratio = ratio;
    }

    @Override
    public String toString() {
        return "{" +
                "\"count_mutant_dna\":" + getMutantes() +
                ",\"count_human_dna\":" + getHumanos() +
                ",\"ratio\":" + getRatio() +
                '}';
    }
}