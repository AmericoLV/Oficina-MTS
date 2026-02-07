package com.mycompany.oficinamecanica;




public class Elevador {
    
    // atributos
    private int id;
    private String tipo; // alinhamento, balanceamento e corriqueiro
    private boolean ocupado; 
    public static Elevador[] elevadores = new Elevador[3]; // tamanho fixo

    // construtor
    public Elevador(int id, String tipo, boolean ocupado) {
        this.id = id;
        this.tipo = tipo;
        this.ocupado = ocupado;
                       
    }

    // getters e setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }
    
    @Override
    public String toString(){
        return "Elevador #" + getId() + " - Tipo: " + getTipo() + " - Ocupado: " + isOcupado();
    }
}
