package com.mycompany.oficinamecanica.OficinaDados;

import com.mycompany.oficinamecanica.OficinaContabilidade.Despesa;
import com.mycompany.oficinamecanica.OficinaContabilidade.OrdemServico;
import com.mycompany.oficinamecanica.OficinaContabilidade.Produto;
import com.mycompany.oficinamecanica.OficinaContabilidade.Venda;
import com.mycompany.oficinamecanica.OficinaSistema.Funcionario;
import com.mycompany.oficinamecanica.Servico;
import com.mycompany.oficinamecanica.Veiculo;
import java.util.LinkedList;


public interface Lista {

    // listas para armazenar objetos das classes
    LinkedList<Cliente> listaClientes = new LinkedList<>();
    LinkedList<Funcionario> listaFuncionarios = new LinkedList<>();
    LinkedList<Agendamento> listaAgendamentos = new LinkedList<>();
    LinkedList<Venda> listaVendas = new LinkedList<>();
    LinkedList<Produto> listaProdutos = new LinkedList<>();
    LinkedList<OrdemServico> listaOrdensServicos = new LinkedList<>();
    LinkedList<Veiculo> listaVeiculos = new LinkedList<>();
    LinkedList<Servico> listaServicos = new LinkedList<>();
    LinkedList<Despesa> listaDespesas = new LinkedList<>();
}
