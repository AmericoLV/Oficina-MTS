package com.mycompany.oficinamecanica;

import com.google.gson.reflect.TypeToken;
import com.mycompany.oficinamecanica.OficinaDados.Cliente;
import com.mycompany.oficinamecanica.OficinaDados.Lista;
import static com.mycompany.oficinamecanica.OficinaDados.Lista.listaVeiculos;
import com.mycompany.oficinamecanica.OficinaSistema.Json;
import java.lang.reflect.Type;
import java.util.List;

public class Veiculo implements Lista{
    
    // atritbutos
    private String placa;
    private String modelo;
    private String marca;
    private int ano;
    private Cliente clienteAssociado; // relacionamento
    private static int totalVeiculos = 0;
    protected static int contadorVeiculosProtegido = 0;


    // construtor
    public Veiculo(String placa, String modelo, String marca, int ano, Cliente clienteAssociado) {
        this.placa = placa;
        this.modelo = modelo;
        this.marca = marca;
        this.ano = ano;
        this.clienteAssociado = clienteAssociado;
        totalVeiculos++;
        contadorVeiculosProtegido++;
        listaVeiculos.add(this);
    }
    
    // getters e setters
    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public Cliente getClienteAssociado() {
        return clienteAssociado;
    }

    public void setClienteAssociado(Cliente clienteAssociado) {
        this.clienteAssociado = clienteAssociado;
    }
    
    public static int getTotalVeiculos(){
        return totalVeiculos;
    }
    
    public static void setTotalVeiculos(){
        Veiculo.totalVeiculos = totalVeiculos;
    }

    // métodos
    @Override
    public String toString(){
        return "Placa: " + getPlaca() + " - Modelo: " + getModelo();
    }

    public static Veiculo buscarVeiculoPorPlaca(String placa){
        for (Veiculo v : listaVeiculos){
            if (v.getPlaca().equalsIgnoreCase(placa)) return v;
        }
        return null;
    }

    public static void editarVeiculo(String placa, String novaPlaca, String novoModelo, String novaMarca, int novoAno, Cliente novoClienteAssociado) {
        Veiculo veiculo = buscarVeiculoPorPlaca(placa);
        veiculo.setPlaca(novaPlaca);
        veiculo.setModelo(novoModelo);
        veiculo.setMarca(novaMarca);
        veiculo.setAno(novoAno);
        veiculo.setClienteAssociado(novoClienteAssociado);
        System.out.println("Veiculo " + novoModelo + " editado com sucesso!\n");
    }
    
    public static void listarVeiculos() {
        for (Veiculo v : listaVeiculos) {
            System.out.println(v);
        }
    }

    public static boolean removerVeiculo(String placa){
        return listaVeiculos.removeIf(v -> v.getPlaca().equals(placa));
    }
    
    public static void salvarVeiculos(){
        Json.salvarLista(listaVeiculos, "json/veiculos.json");
        System.out.println("Veiculos salvos na pasta json!");
    }
    
    public static void carregarVeiculos(){
        Type tipo = new TypeToken<List<Veiculo>>(){}.getType();
        List<Veiculo> lista = Json.carregarLista("json/veiculosCarregar.json", tipo);
        if (lista != null) {
            listaVeiculos.addAll(lista);
            totalVeiculos += listaVeiculos.size();
            contadorVeiculosProtegido += listaVeiculos.size();
        }
    }    
}