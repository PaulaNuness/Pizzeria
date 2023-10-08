package com.example.pizzeria.clases;

/**
 *
 */
public class Pedido {
    /**
     *atibutos da clase
     */
    String sabor;
    String tamano;
    String bebida;
    String salsa;

    /**
     *metodo construtor com parametros
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
     *retorna o sabor
     * @return
     */
    public String getSabor() {
        return sabor;
    }

    /**
     *setea o sabor
     * @param sabor
     */
    public void setSabor(String sabor) {
        this.sabor = sabor;
    }

    /**
     *retorna o tamanho
     * @return
     */
    public String getTamano() {
        return tamano;
    }

    /**
     *setea o tamanho
     * @param tamano
     */
    public void setTamano(String tamano) {
        this.tamano = tamano;
    }

    /**
     *retorna a bebida
     * @return
     */
    public String getBebida() {
        return bebida;
    }

    /**
     *setea a bebida
     * @param bebida
     */
    public void setBebida(String bebida) {
        this.bebida = bebida;
    }

    /**
     *retorna a salsa
     * @return
     */
    public String getSalsa() {
        return salsa;
    }

    /**
     *setea a salsa
     * @param salsa
     */
    public void setSalsa(String salsa) {
        this.salsa = salsa;
    }
}
