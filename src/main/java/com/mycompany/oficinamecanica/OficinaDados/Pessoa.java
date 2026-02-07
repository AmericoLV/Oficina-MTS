package com.mycompany.oficinamecanica.OficinaDados;

public class Pessoa {
    
    // atributos
    private String nome;
    private String email;
    private String telefone;

    // construtores
    public Pessoa(){
        
    }

    public Pessoa(String nome){
        this.nome = nome;
    }
            
    public Pessoa(String nome, String email, String telefone) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }

    // getters e setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
