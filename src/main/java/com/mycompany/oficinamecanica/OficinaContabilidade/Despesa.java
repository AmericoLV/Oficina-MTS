package com.mycompany.oficinamecanica.OficinaContabilidade;

import com.google.gson.reflect.TypeToken;
import com.mycompany.oficinamecanica.OficinaDados.Lista;
import com.mycompany.oficinamecanica.OficinaSistema.Funcionario;
import com.mycompany.oficinamecanica.OficinaSistema.Json;

import java.lang.reflect.Type;
import java.util.List;

public class Despesa implements Lista {

    // atributos
    private int id;
    private static int proximoId = 1;
    private String tipo; // Ex: "Café", "Funcionário", "Limpeza", etc.
    private double valor;
    private String data;

    // construtor
    public Despesa(int idUsuarioLogado, String tipo, double valor, String data) {
        if (validarUserAdm(idUsuarioLogado)){
            this.id = proximoId++;
            this.tipo = tipo;
            this.valor = valor;
            this.data = data;
            listaDespesas.add(this);
        } else {
            System.out.println("Voce nao tem permissao para criar despesa");
        }

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

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public static List<Despesa> getDespesas() {
        return listaDespesas;
    }

    // métodos
    @Override
    public String toString(){
        return "Despesa #" + getId() + ": Tipo: " + getTipo() + " - Valor: R$" + String.format("%.2f", getValor()) + " - Data: " + getData();
    }

    public static void listarDespesas(int idUsuarioLogado){
        if (validarUserAdm(idUsuarioLogado)){
            for (Despesa d : listaDespesas){
                System.out.println(d);
            }
        } else {
            System.out.println("\nVoce nao tem permissao para listar as despesas");
        }
    }

    private static Despesa buscarDespesaPorId(int id) {
        for (Despesa d : listaDespesas) {
            if (d.getId() == id) {
                return d;
            }
        }
        return null;
    }

    public static void removerDespesa(int idUsuarioLogado, int id){
        if (validarUserAdm(idUsuarioLogado)){
            listaDespesas.removeIf(d -> d.getId() == id);
            System.out.println("\nDespesa " + id + " removida com sucesso!\n");
        } else {
            System.out.println("\nVoce nao tem permissao para remover uma despesa");
        }
    }

    public static void editarDespesa(int idFuncionarioLogado, int id, String novoTipo, double novoValor, String novoData){
        if (validarUserAdm(idFuncionarioLogado)){
            for (Despesa d : listaDespesas){
                if (d.getId() == id) {
                    d.setTipo(novoTipo);
                    d.setValor(novoValor);
                    d.setData(novoData);
                    System.out.println("\nDespesa " + novoTipo + " editada com sucesso!\n");
                }
            }
        } else {
            System.out.println("\nVoce nao tem permissao para editar uma despesa");
        }
    }

    public static void salvarDespesas(){
        Json.salvarLista(listaDespesas, "json/despesas.json");
        System.out.println("Despesas salvas na pasta json!");
    }

    public static void carregarDespesas(){
        Type tipo = new TypeToken<List<Despesa>>(){}.getType();
        List<Despesa> lista = Json.carregarLista("json/despesasCarregar.json", tipo);
        if (lista != null) {
            listaDespesas.addAll(lista);
            proximoId = listaDespesas.size()+ 1;
        }
    }

    private static boolean validarUserAdm(int id){
        for(Funcionario f : listaFuncionarios){
            if(f.getId() == id && f.getCargo().equalsIgnoreCase("administrador")){
                return true;
            }
        }
        return false;
    }
}
