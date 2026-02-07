package com.mycompany.oficinamecanica.OficinaDados;

import com.google.gson.reflect.TypeToken;
import com.mycompany.oficinamecanica.OficinaSistema.Json;
import java.lang.reflect.Type;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;


public class Cliente extends Pessoa implements Lista {

    // atributos
    private int id;
    private String endereco;
    private String cpfOriginal;
    private String cpfPseudoAnonimizado;
    private static int proximoIdCliente = 1;

    // construtores
    public Cliente(){

    }

    public Cliente(String nome, String email, String telefone, String endereco, String cpfOriginal) {
        super(nome, email, telefone);
        this.id = proximoIdCliente++;
        this.endereco = endereco;
        setCpfOriginal(cpfOriginal);
        listaClientes.add(this);
    }
    
    public Cliente(String nome){
        super(nome);
    }

    public static List<Cliente> getClientes() {
        return listaClientes;
    }

    // getters e setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCpfOriginal() {
        return cpfOriginal;
    }

    public void setCpfOriginal(String cpfOriginal) {
        this.cpfOriginal = cpfOriginal;
        this.cpfPseudoAnonimizado = pseudoAnonimizarCpf(cpfOriginal);
    }
    
    public String getCpfPseudoAnonimizado() {
        return cpfPseudoAnonimizado;
    }
    
    private static String pseudoAnonimizarCpf(String cpf){
        cpf = cpf.replace(".", "").replace("-", "");
        if (cpf.length() != 11) return "CPF inválido!";
        return "***.***." + cpf.substring(6, 9) + "-" + cpf.substring(9);
    }
    
    @Override
    public String toString(){
        return "Cliente #" + getId() + ": " + getNome() + " - " + getCpfPseudoAnonimizado() + " - " + getEmail();
    }

    // métodos
    public static void editarCliente(int id, String novoNome, String novoEmail, String novoTelefone, String novoEndereco, String novoCpf) {
        Cliente cliente = buscarClientePorId(id);
        cliente.setNome(novoNome);
        cliente.setEmail(novoEmail);
        cliente.setTelefone(novoTelefone);
        cliente.setEndereco(novoEndereco);
        cliente.setCpfOriginal(novoCpf);
        System.out.println("Cliente " + novoNome + " editado com sucesso!\n");
    }

    public static void listarClientes() {
        for (Cliente c : listaClientes) {
            System.out.println(c);
        }
    }
    
    public static boolean removerCliente(int id){
        return listaClientes.removeIf(c -> c.getId() == id);
    }

    public static Cliente buscarClientePorId(int id) {
        for (Cliente c : listaClientes ) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }
    
    public  static Cliente findClientePorNome(String nome, List<Cliente> lista, Comparator<Cliente> comparador){
       Iterator<Cliente> iterator = lista.iterator();
       Cliente buscado = new Cliente(nome);
       while (iterator.hasNext()){
           Cliente atual = iterator.next();
           if (comparador.compare(atual, buscado) == 0){
               return atual;
           }
       }
       return null;
    }
    
    public static void salvarClientes(){
        Json.salvarLista(listaClientes, "json/clientes.json");
        System.out.println("Clientes salvos na pasta json!");
    }
    
    public static void carregarClientes(){
        Type tipo = new TypeToken<List<Cliente>>(){}.getType();
        List<Cliente> lista = Json.carregarLista("json/clientesCarregar.json", tipo);
        if (lista != null) {
            listaClientes.addAll(lista);
            proximoIdCliente = listaClientes.size()+ 1;
        }
    }
}
