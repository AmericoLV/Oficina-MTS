package com.mycompany.oficinamecanica.OficinaSistema;

import com.google.gson.reflect.TypeToken;
import com.mycompany.oficinamecanica.OficinaDados.Lista;
import com.mycompany.oficinamecanica.OficinaDados.Pessoa;
import java.lang.reflect.Type;
import java.util.List;

public class Funcionario extends Pessoa implements Lista{
    
    // atributos
    private int id;
    private String cargo; // colaborador ou administrador
    private String login;
    private String senha;
    private static int proximoIdFunc = 1;

    // construtor
    public Funcionario(String nome, String email, String telefone, String cargo, String login, String senha) {
        super(nome, email, telefone);
        this.id = proximoIdFunc++;
        this.cargo = cargo;
        this.login = login;
        this.senha = senha;
        listaFuncionarios.add(this);
    }

    // getters e setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    @Override
    public String toString(){
        return "Funcionario #" + getId() + ": Nome: " + getNome() + " - Cargo: " + getCargo();
    }
    
    public static void listarFuncionarios() {
        for (Funcionario f : listaFuncionarios) {
            System.out.println(f.getLogin() + " - " + f.getSenha() + " - " + f.getCargo());
        }
    }
    
     public static void salvarFuncionarios(){
        Json.salvarLista(listaFuncionarios, "json/funcionarios.json");
        System.out.println("Funcionarios salvos na pasta json!");

    }
    
    public static void carregarFuncionarios(){
        Type tipo = new TypeToken<List<Funcionario>>(){}.getType();
        List<Funcionario> lista = Json.carregarLista("json/funcionariosCarregar.json", tipo);
        if (lista != null) {
            listaFuncionarios.addAll(lista);
            proximoIdFunc = listaFuncionarios.size() + 1;
        }
    }    
}
