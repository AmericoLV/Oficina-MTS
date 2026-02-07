package com.mycompany.oficinamecanica.OficinaContabilidade;

import com.google.gson.reflect.TypeToken;
import com.mycompany.oficinamecanica.OficinaDados.Lista;
import com.mycompany.oficinamecanica.OficinaSistema.Json;
import java.lang.reflect.Type;
import java.util.List;

public class Produto implements Lista{
    
    // atributos
    private int id;
    private String nome;
    private int quantidadeEstoque;
    private double precoUnitario;
    private static int proximoIdProduto = 1;


    // construtor
    public Produto( String nome, int quantidadeEstoque, double precoUnitario) {
        this.id = proximoIdProduto++;
        this.nome = nome;
        this.quantidadeEstoque = quantidadeEstoque;
        this.precoUnitario = precoUnitario;
        listaProdutos.add(this);
    }

    // getters e setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }
    
    @Override
    public String toString(){
        return "Produto #" + getId() + " - Nome: " + getNome() + " - Estoque: " + getQuantidadeEstoque();
    }

    public static Produto buscarProdutoPorId(int idProduto) {
        for (Produto p : listaProdutos) {
            if (p.getId() == idProduto) {
                return p;
            }
        }
        return null;
    }

    public static void listarProdutos() {
        for (Produto p : listaProdutos) {
            System.out.println("Produto #" + p.getId() + ": " + p.getNome() + " - Quantidade: " + p.getQuantidadeEstoque() + " - Preco: R$" + p.getPrecoUnitario());
        }
    }

    public boolean receberProdutoDeFornecedor( int quantidadeRecebida) {
        int novaQuantidade = getQuantidadeEstoque() + quantidadeRecebida;
        setQuantidadeEstoque(novaQuantidade);
        System.out.println("Produto '" + getNome() + "' atualizado. Novo estoque: " + novaQuantidade);
        return true;
    }

    // produto
    public void verificarProdutoNoEstoque(String nomeProduto) {
        for (Produto p : listaProdutos) {
            if (p.getNome().equalsIgnoreCase(nomeProduto)) {
                System.out.println("\nProduto encontrado: " + getNome() + " | Quantidade em estoque: " + getQuantidadeEstoque() + " | Preco: R$" + p.getPrecoUnitario());
                return;
            }
        }
        System.out.println("\nProduto '" + nomeProduto + "' nao encontrado no estoque.");
    }
    
    public static void salvarProduto(){
        Json.salvarLista(listaProdutos, "json/produtos.json");
        System.out.println("Produtos salvos na pasta json!");

    }
    
    public static void carregarProduto(){
        Type tipo = new TypeToken<List<Produto>>(){}.getType();
        List<Produto> lista = Json.carregarLista("json/produtosCarregar.json", tipo);
        if (lista != null) {
            listaProdutos.addAll(lista);
            proximoIdProduto = listaProdutos.size() + 1;
            
        }
    }    
}
