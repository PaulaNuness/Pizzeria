package com.example.pizzeria;

/**
 *
 */
public class Pedido {
    /**
     *
     */
    String sabor;
    String tamano;
    String bebida;
    String salsa;

    /**
     *
     * @param sabor
     * @param tamano
     * @param bebida
     * @param salsa
     */
    public Pedido(String sabor, String tamano, String bebida, String salsa) {
        this.sabor = sabor;
        this.tamano = tamano;
        this.bebida = bebida;
        this.salsa = salsa;
    }

    /**
     *
     * @return
     */
    public String getSabor() {
        return sabor;
    }

    /**
     *
     * @param sabor
     */
    public void setSabor(String sabor) {
        this.sabor = sabor;
    }

    /**
     *
     * @return
     */
    public String getTamano() {
        return tamano;
    }

    /**
     *
     * @param tamano
     */
    public void setTamano(String tamano) {
        this.tamano = tamano;
    }

    /**
     *
     * @return
     */
    public String getBebida() {
        return bebida;
    }

    /**
     *
     * @param bebida
     */
    public void setBebida(String bebida) {
        this.bebida = bebida;
    }

    /**
     *
     * @return
     */
    public String getSalsa() {
        return salsa;
    }

    /**
     *
     * @param salsa
     */
    public void setSalsa(String salsa) {
        this.salsa = salsa;
    }
}
