package com.mycompany.oficinamecanica;

import com.google.gson.reflect.TypeToken;
import com.mycompany.oficinamecanica.OficinaDados.Lista;
import com.mycompany.oficinamecanica.OficinaSistema.Json;
import java.lang.reflect.Type;
import java.util.List;

public class Servico implements Lista {
    
    // atributos
    private int id;
    private String descricao;
    private double preco;
    private static int proximoId = 1;
     
    // construtor
    public Servico(String descricao, double preco) {
        this.id = proximoId++;
        this.descricao = descricao;
        this.preco = preco;
        listaServicos.add(this);
    }

    // getters e setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
    
    @Override
    public String toString(){
        return "Servico #" + getId() + " - Descricao: " + getDescricao() + " - Preco: " + getPreco();
    }

    public static Servico buscarServicoPorId(int id){
        for (Servico s : listaServicos) {
            if (s.getId() == id) {
                return s;
            }
        }
        return null;
    }
    
     public static void listarServicos() {
        for (Servico s : listaServicos) {
            System.out.println(s);
        }
    }
    
     public static void salvarServico(){
        Json.salvarLista(listaServicos, "json/servicos.json");
        System.out.println("Servicos salvos na pasta json!");

    }
    
    public static void carregarServico(){
        Type tipo = new TypeToken<List<Servico>>(){}.getType();
        List<Servico> lista = Json.carregarLista("json/servicosCarregar.json", tipo);
        if (lista != null) {
            listaServicos.addAll(lista);
            proximoId = listaServicos.size() + 1;
        }
    }    
}
