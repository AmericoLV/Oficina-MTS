package com.mycompany.oficinamecanica.OficinaContabilidade;

import com.google.gson.reflect.TypeToken;
import com.mycompany.oficinamecanica.OficinaDados.Agendamento;
import com.mycompany.oficinamecanica.OficinaDados.Cliente;
import com.mycompany.oficinamecanica.OficinaDados.Lista;
import com.mycompany.oficinamecanica.OficinaSistema.Json;
import com.mycompany.oficinamecanica.Servico;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class OrdemServico implements Lista {

    // atributos
    private int id;
    private Agendamento agendamento;
    private List<Servico> servicos = new ArrayList<>();
    private List<Produto> pecasUtilizadas = new ArrayList<>();
    private double valorTotal;
    private String data;
    private static int proximoIdOrdemServico = 1;

    // Construtor atualizado
    public OrdemServico(Agendamento agendamento, List<Servico> servicos, List<Produto> pecasUtilizadas, String data) {
        this.id = proximoIdOrdemServico++;
        agendamento.setStatus("Finalizado");
        this.agendamento = agendamento;
        this.servicos = servicos;
        this.pecasUtilizadas = pecasUtilizadas;
        this.data = data;

        this.valorTotal = 0;

        for (Servico servico : servicos) {
            this.valorTotal += servico.getPreco();
        }

        for (Produto produto : pecasUtilizadas) {
            this.valorTotal += produto.getPrecoUnitario();

            int novoEstoque = produto.getQuantidadeEstoque() - 1; // assume 1 unidade por produto
            produto.setQuantidadeEstoque(novoEstoque);
        }

        listaOrdensServicos.add(this);

        gerarExtratoOrdemServico();
        gerarNotaFiscal();
    }

    public static List<OrdemServico> getOrdensServico() {
        return listaOrdensServicos;
    }

    // getters e setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Agendamento getAgendamento() {
        return agendamento;
    }

    public void setAgendamento(Agendamento agendamento) {
        this.agendamento = agendamento;
    }

    public List<Servico> getServicos() {
        return servicos;
    }

    public void setServicos(List<Servico> servicos) {
        this.servicos = servicos;
    }

    public List getPecasUtilizadas() {
        return pecasUtilizadas;
    }

    public void setPecasUtilizadas(List pecasUtilizadas) {
        this.pecasUtilizadas = pecasUtilizadas;
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
    
    @Override
    public String toString(){
        return "Ordem Servico #" + getId() + ": Agendamento: " + getAgendamento() + " - Valor: " + getValorTotal() + " - Data: " + getData();
    }

    // ordem de serviço
    public static void listarOrdemServico() {
        for (OrdemServico os : listaOrdensServicos) {
            System.out.println(os);
        }
    }

    private void gerarExtratoOrdemServico() {
        Cliente cliente = this.getAgendamento().getCliente();
        String conteudo = "Extrato de ordem de servico\n" + "Cliente: " + cliente.getNome() + " | CPF: " + cliente.getCpfPseudoAnonimizado() + "\n" + "Data: " + this.getData() + "\n" + "Problema: " + this.getAgendamento().getDescricaoProblema() + "\n" + "Status: " + this.getAgendamento().getStatus() + "\n" + "Servicos: \n";
        for (Servico s : this.getServicos()) {
            conteudo += " - " + s.getDescricao() + "(R$ " + String.format("%.2f", s.getPreco()) + ")\n";
        }
        conteudo += "Valor total: R$" + String.format("%.2f", this.getValorTotal()) + "\n";
        try (PrintWriter writer = new PrintWriter("extrato/extrato_os_" + this.getId() + ".txt")) {
            writer.println(conteudo);
            System.out.println("Extrato da ordem de servico salvo com sucesso.\n");
        } catch (IOException e) {
            System.out.println("Erro ao salvar extrato: " + e.getMessage());
        }
    }

    public void imprimirOrdensPorCliente(List<Cliente> clientes, List<OrdemServico> ordensServico ) {
        System.out.println("Ordens de Servico por cliente");
        for (Cliente cliente : clientes) {
            System.out.println("\nCliente: " + cliente.getNome() + " | ID: " + cliente.getId());
            boolean encontrou = false;
            for (OrdemServico os : ordensServico) {
                if (os.getAgendamento().getCliente().getId() == cliente.getId()) {
                    encontrou = true;
                    System.out.println("Ordem ID: " + os.getId());
                    System.out.println("Data: " + os.getData());
                    System.out.println("Valor total: R$" + String.format("%.2f", os.getValorTotal()));
                    System.out.println("Status: " + os.getAgendamento().getStatus());
                    System.out.println("Problema: " + os.getAgendamento().getDescricaoProblema());
                }
            }
            if (!encontrou) {
                System.out.println("Nenhuma ordem de servico encontrada");
            }
        }
    }
    
    public static void salvarOrdemServico(){
        Json.salvarLista(listaOrdensServicos, "json/ordensServico.json");
        System.out.println("Ordens de servicos salvas na pasta json!");

    }
    
    public static void carregarOrdemServico(){
        Type tipo = new TypeToken<List<OrdemServico>>(){}.getType();
        List<OrdemServico> lista = Json.carregarLista("json/ordemServicosCarregar.json", tipo);
        if (lista != null) {
            listaOrdensServicos.addAll(lista);
            proximoIdOrdemServico = listaOrdensServicos.size() + 1;

            for (OrdemServico os : lista) {
                os.gerarExtratoOrdemServico();
                os.gerarNotaFiscal();
            }
        }
    }

    public void gerarNotaFiscal() {
        String nomeOficina = "Oficina Mecanica MTS";
        String cnpj = "12.345.678/0001-99";
        String dataHoraAtual = java.time.LocalDateTime.now()
                .format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));

        double imposto = this.valorTotal * 0.10;
        double valorComImposto = this.valorTotal + imposto;

        StringBuilder conteudo = new StringBuilder();
        conteudo.append("========= NOTA FISCAL =========\n");
        conteudo.append("Oficina: ").append(nomeOficina).append("\n");
        conteudo.append("CNPJ: ").append(cnpj).append("\n");
        conteudo.append("Data/Hora: ").append(dataHoraAtual).append("\n");
        conteudo.append("---------------------------------\n");
        conteudo.append("Cliente: ").append(this.agendamento.getCliente().getNome())
                .append(" | CPF: ").append(this.agendamento.getCliente().getCpfPseudoAnonimizado()).append("\n");

        conteudo.append("Serviços:\n");
        for (Servico servico : this.servicos) {
            conteudo.append("- ").append(servico.getDescricao())
                    .append(" (R$ ").append(String.format("%.2f", servico.getPreco())).append(")\n");
        }

        conteudo.append("Peças Utilizadas:\n");
        for (Produto produto : this.pecasUtilizadas) {
            conteudo.append("- ").append(produto.getNome())
                    .append(" | Quantidade: 1")
                    .append(" | Unitário: R$ ").append(String.format("%.2f", produto.getPrecoUnitario()))
                    .append(" | Subtotal: R$ ").append(String.format("%.2f", produto.getPrecoUnitario()))
                    .append("\n");
        }

        conteudo.append("---------------------------------\n");
        conteudo.append(String.format("Valor Total (sem imposto): R$ %.2f\n", this.valorTotal));
        conteudo.append(String.format("Imposto (10%%): R$ %.2f\n", imposto));
        conteudo.append(String.format("Valor Final: R$ %.2f\n", valorComImposto));
        conteudo.append("==================================");

        try (PrintWriter writer = new PrintWriter("notaFiscal/nota_fiscal_ordem_servico_" + this.id + ".txt")) {
            writer.print(conteudo.toString());
            System.out.println("Nota Fiscal gerada com sucesso. \n");
        } catch (IOException e) {
            System.out.println("Erro ao gerar nota fiscal: " + e.getMessage());
        }
    }
}
