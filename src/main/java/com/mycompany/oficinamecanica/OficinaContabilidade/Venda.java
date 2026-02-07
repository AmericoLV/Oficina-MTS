package com.mycompany.oficinamecanica.OficinaContabilidade;

import com.google.gson.reflect.TypeToken;
import com.mycompany.oficinamecanica.OficinaDados.Cliente;
import com.mycompany.oficinamecanica.OficinaDados.Lista;
import com.mycompany.oficinamecanica.OficinaSistema.Json;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.List;

public class Venda implements Lista{
    
    // atributos
    private int id;
    private Produto produto;
    private int quantidade;
    private double valorTotal;
    private String data;
    private Cliente cliente;
    private static int proximoIdVenda = 1;
  

    // construtor
    public Venda(Produto produto, int quantidade, String data, Cliente cliente) {
        if (produto.getQuantidadeEstoque() < quantidade) {
            System.out.println("Estoque insuficiente.");
            return;
        }
        produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - quantidade);
   
        this.id = proximoIdVenda++;
        this.produto = produto;
        this.quantidade = quantidade;
        this.valorTotal = quantidade * produto.getPrecoUnitario();
        this.data = data;
        this.cliente = cliente;
        listaVendas.add(this);
        
        gerarExtratoVenda();
    }

    public static List<Venda> getVendas() {
        return listaVendas;
    }

    // getters e setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    @Override
    public String toString(){
        return "Venda #" + getId() + " - Produto: " + getProduto() + " - Valor: " + String.format("%.2f", getValorTotal());
    }


    public void gerarExtratoVenda() {
        String conteudo = "Extrato de Venda\n" + "Cliente: " + this.getCliente().getNome() + " | CPF: " + this.getCliente().getCpfPseudoAnonimizado() + "\n" + "Produto: " + this.getProduto().getNome() + "\n" + "Quantidade: " + this.getQuantidade() + "\n" + "Valor total: R$" + String.format("%.2f", this.getValorTotal()) + "\n" + "Data: " + this.getData() + "\n";
        try (PrintWriter writer = new PrintWriter("extrato/extrato_venda_" + this.getId() + ".txt")) {
            writer.println(conteudo);
            System.out.println("Extrato da venda salvo com sucesso.\n");
        } catch (IOException e) {
            System.out.println("Erro ao salvar extrato: " + e.getMessage());
        }
    }
    
    public static void listarVendas() {
        for (Venda v : listaVendas) {
            System.out.println(v);
        }
    }
     
    public static void salvarVenda(){
        Json.salvarLista(listaVendas, "json/vendas.json");
        System.out.println("Vendas salvas na pasta json!");

    }
    
    public static void carregarVenda(){
        Type tipo = new TypeToken<List<Venda>>(){}.getType();
        List<Venda> lista = Json.carregarLista("json/vendasCarregar.json", tipo);
        if (lista != null) {
            listaVendas.addAll(lista);
            proximoIdVenda = listaVendas.size() + 1;
        }
    }
}
