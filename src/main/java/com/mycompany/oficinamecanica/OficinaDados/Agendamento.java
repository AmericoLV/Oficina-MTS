package com.mycompany.oficinamecanica.OficinaDados;

import com.google.gson.reflect.TypeToken;
import com.mycompany.oficinamecanica.OficinaSistema.Funcionario;
import com.mycompany.oficinamecanica.OficinaSistema.Json;
import com.mycompany.oficinamecanica.Veiculo;
import com.mycompany.oficinamecanica.Servico;
import java.lang.reflect.Type;
import java.util.List;

public class Agendamento implements Lista {
    
    // atributos
    private int id;
    private Cliente cliente;
    private Veiculo veiculo;
    private Servico servico;
    private Funcionario mecanicoResponsavel;
    private String dataHora;
    private String status; // ex: agendado, em manutenção e finalizado
    private String descricaoProblema;
    private static int proximoId = 1;


    // construtor
    public Agendamento(Cliente cliente, Veiculo veiculo, Servico servico, Funcionario mecanicoResponsavel, String dataHora, String status, String descricaoProblema) {
        this.id = proximoId++;
        this.cliente = cliente;
        this.veiculo = veiculo;
        this.servico = servico;
        this.mecanicoResponsavel = mecanicoResponsavel;
        this.dataHora = dataHora;
        this.status = status;
        this.descricaoProblema = descricaoProblema;
        listaAgendamentos.add(this);
    }

    // getters e setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public Funcionario getMecanicoResponsavel() {
        return mecanicoResponsavel;
    }

    public void setMecanicoResponsavel(Funcionario mecanicoResponsavel) {
        this.mecanicoResponsavel = mecanicoResponsavel;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescricaoProblema() {
        return descricaoProblema;
    }

    public void setDescricaoProblema(String descricaoProblema) {
        this.descricaoProblema = descricaoProblema;
    }
    
    @Override
    public String toString(){
        return "Agendamento #" + getId() + " - Cliente " + getCliente() + " - Veiculo: " + getVeiculo() + " - Mecanico responsavel: " + getMecanicoResponsavel();
    }

    public boolean cancelarAgendamento() {
        setStatus("Cancelado");
        System.out.println("Agendamento #" + getId() + " cancelado com sucesso.");
        return true;
    }

    public Agendamento buscarAgendamentoPorId(int idAgendamento) {
        for (Agendamento a : listaAgendamentos) {
            if (a.getId() == idAgendamento) {
                return a;
            }
        }
        return null;
    }

    // agendamento
    public boolean realizarAgendamento(Cliente cliente, Veiculo veiculo, Funcionario funcionario, String dataHora, String descricaoProblema) {
        
        try {
            Agendamento agendamento = new Agendamento(cliente, veiculo, servico, funcionario, dataHora, "Agendado", descricaoProblema);
            listaAgendamentos.add(agendamento);
            System.out.println("Agendamento realizado com sucesso para " + cliente.getNome());
            return true;
       
        } catch(Exception e){
            
            System.out.println("Erro: cliente, veiculo ou funcionario nao encontrado.");
            return false; 
        }
       
    }

    public static void listarAgendamentos() {
        for (Agendamento a : listaAgendamentos) {
            System.out.println("ID: " + a.getId() + " - Cliente: " + a.getCliente().getNome() + " - Data: " + a.getDataHora() + " - Descricao: " + a.getDescricaoProblema() + " - Status: " + a.getStatus());
        }
    }
    public static void salvarAgendamento(){
        Json.salvarLista(listaAgendamentos, "json/agendamentos.json");
        System.out.println("Agendamentos salvos na pasta json!");

    }
    
    public static void carregarAgendamento(){
        Type tipo = new TypeToken<List<Agendamento>>(){}.getType();
        List<Agendamento> lista = Json.carregarLista("json/agendamentosCarregar.json", tipo);
        if (lista != null) {
            listaAgendamentos.addAll(lista);
            proximoId = listaAgendamentos.size() + 1;
            
        }
    }    
}
