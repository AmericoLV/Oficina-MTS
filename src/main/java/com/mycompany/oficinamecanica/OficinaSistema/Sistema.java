package com.mycompany.oficinamecanica.OficinaSistema;

import com.mycompany.oficinamecanica.OficinaContabilidade.OrdemServico;
import com.mycompany.oficinamecanica.Veiculo;
import com.mycompany.oficinamecanica.OficinaContabilidade.Venda;
import com.mycompany.oficinamecanica.OficinaDados.Lista;
import java.util.ArrayList;
import java.util.List;

public class Sistema implements Lista{
    
    // construtor
    public Sistema() {
       
    }
    
    // métodos
    public static void addFuncionario(String nome, String email, String telefone, String cargo, String login, String senha) {
        Funcionario funcionario = new Funcionario(nome, email, telefone, cargo, login, senha);
    }
    
    public static boolean editarFuncionario(int id, String novoNome, String novoEmail, String novoTelefone, String novoCargo, String novoLogin, String novaSenha) {
    for (Funcionario f : listaFuncionarios) {
        if (f.getId() == id) {
            f.setNome(novoNome);
            f.setEmail(novoEmail);
            f.setTelefone(novoTelefone);
            f.setCargo(novoCargo);
            f.setLogin(novoLogin);
            f.setSenha(novaSenha);
            System.out.println("Funcionario " + novoNome + " editado com sucesso!\n");
            return true;
        }
    }
        return false;
    }
    
    public static boolean removerFuncionario(int id) {
        return listaFuncionarios.removeIf(f -> f.getId() == id);
    }

    public static void listarFuncionarios() {
        for (Funcionario f : listaFuncionarios) {
            System.out.println("Funcionario #" + f.getId() + ": " + f.getNome() + " - Cargo: " + f.getCargo());
        }
    }
    
    public static void alterarSenhaAdministrador(int idUsuarioLogado, int idFuncionarioNovaSenha, String novaSenha) {
        if (validarUserAdm(idUsuarioLogado)){
            for (Funcionario f : listaFuncionarios) {
                if (f.getId() == idFuncionarioNovaSenha && f.getCargo().equalsIgnoreCase("administrador")) {
                    f.setSenha(novaSenha);
                    System.out.println("Senha do funcionario " + f.getNome() + " alterado com sucesso!\n");
                }
            }
        } else {
            System.out.println("Voce nao tem permissao");
        }
    }
    
    public static Funcionario buscarFuncionarioPorId(int id){
        for (Funcionario f : listaFuncionarios){
            if (f.getId() == id) return f;
        }
        return null;
    }

    public void gerarRelatorioPorMes(int idUsuarioLogado, String mesAno, List<Venda> vendas, List<OrdemServico> ordensServicos) {
        if (validarUserAdm(idUsuarioLogado)){
            System.out.println("Relatorio de vendas no mes" + mesAno);
            for (Venda v : vendas) {
                if (v.getData().endsWith(mesAno)) {
                    System.out.println("- Venda - Produto: " + v.getProduto().getNome() + " | Quantidade: " + v.getQuantidade() + " | Total: R$" + v.getValorTotal());
                }
            }
            System.out.println("\nRelatorio de servico no mes " + mesAno);
            for (OrdemServico os : ordensServicos) {
                if (os.getData().endsWith(mesAno)) {
                    System.out.println("- Ordem de Servico - Cliente: " + os.getAgendamento().getCliente().getNome() + " - Pecas utilizadas: " + os.getPecasUtilizadas() + " - Servicos: " + os.getServicos() + " | Total: R$" + os.getValorTotal());
                }
            }
        }
        System.out.println("Voce nao tem permisao para gerar o relatorio do mes");
    }

    public void gerarBalancoMensal(int idUsuarioLogado, String mesAno, List<Venda> vendas, List<OrdemServico> ordensServicos) {
        if (validarUserAdm(idUsuarioLogado)){
            int totalVendas = 0;
            double valorTotalVendas = 0.0;
            int totalServicos = 0;
            double valorTotalServicos = 0.0;

            // vendas
            for (Venda v : vendas) {
                if (v.getData().endsWith(mesAno)) {
                    totalVendas++;
                    valorTotalVendas += v.getValorTotal();
                }
            }

            // ordens de serviço
            for (OrdemServico os : ordensServicos) {
                if (os.getData().endsWith(mesAno)) {
                    totalServicos++;
                    valorTotalServicos += os.getValorTotal();
                }
            }

            // exibir resumo
            System.out.println("BALANCO MENSAL - " + mesAno);
            System.out.println("Vendas: " + totalVendas + " | Valor total: R$" + String.format("%.2f", valorTotalVendas));
            System.out.println("Ordens de Servico: " + totalServicos + " | Valor total: R$" + String.format("%.2f", valorTotalServicos));

        } else {
            System.out.println("Voce nao tem permissao para gerar o relatorio mensal");
        }
    }

    public void gerarRelatorioPorDia(int idUsuarioLogado, String data, List<Venda> vendas, List<OrdemServico> ordensServicos) {
        if (validarUserAdm(idUsuarioLogado)){
            System.out.println("Relatorio de vendas em " + data);
            for (Venda v : vendas) {
                if (v.getData().equals(data)) {
                    System.out.println("- Venda - Produto: " + v.getProduto().getNome() + " | Quantidade: " + v.getQuantidade() + " | Total: R$" + v.getValorTotal());
                }
            }
            System.out.println("\nRelatorio de servicos em " + data);
            for (OrdemServico os : ordensServicos) {
                if (os.getData().equals(data)) {
                    System.out.println("- Ordem de Servico - Cliente: " + os.getAgendamento().getCliente().getNome() + " - Pecas utilizadas: " + os.getPecasUtilizadas() + " - Servicos: " + os.getServicos() + " | Total: R$" + os.getValorTotal());
                }
            }

        } else {
            System.out.println("Voce nao tem permisao para gerar o relatorio do dia");
        }

    }
    
    public static int getTotalVeiculosCriados(Veiculo veiculo){
        return veiculo.getTotalVeiculos();
    }

    public static Funcionario validarLogin(String login, String senha) {
        for (Funcionario f : listaFuncionarios){
            if (f.getLogin().equals(login) && f.getSenha().equals(senha)){
                return f;
            }
        }
        return null;
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