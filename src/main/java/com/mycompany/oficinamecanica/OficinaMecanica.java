package com.mycompany.oficinamecanica;

import com.mycompany.oficinamecanica.OficinaContabilidade.Despesa;
import com.mycompany.oficinamecanica.OficinaContabilidade.OrdemServico;
import com.mycompany.oficinamecanica.OficinaContabilidade.Produto;
import com.mycompany.oficinamecanica.OficinaContabilidade.Venda;
import com.mycompany.oficinamecanica.OficinaDados.Agendamento;
import com.mycompany.oficinamecanica.OficinaDados.Cliente;
import com.mycompany.oficinamecanica.OficinaSistema.ComparadorClientePorId;
import com.mycompany.oficinamecanica.OficinaSistema.ComparadorClientePorNome;
import com.mycompany.oficinamecanica.OficinaSistema.Funcionario;
import com.mycompany.oficinamecanica.OficinaSistema.Ordenador;
import com.mycompany.oficinamecanica.OficinaSistema.Sistema;

import java.util.*;

public class OficinaMecanica {

    public static void main(String[] args) {

        // importando dados do JSON.
        Cliente.carregarClientes();
        Veiculo.carregarVeiculos();
        Agendamento.carregarAgendamento();
        Produto.carregarProduto();
        Venda.carregarVenda();
        OrdemServico.carregarOrdemServico();
        Servico.carregarServico();
        Funcionario.carregarFuncionarios();
        Despesa.carregarDespesas();

        Sistema sistema = new Sistema();
        Scanner scanner = new Scanner(System.in);

        // validacao de login
        System.out.println("Login: ");
        String login = scanner.nextLine().strip();
        System.out.println("Senha: ");
        String senha = scanner.nextLine().strip();

        Funcionario funcionarioLogado = Sistema.validarLogin(login, senha);
        System.out.println(funcionarioLogado);

        if (funcionarioLogado == null){
            System.out.println("\nLogin e senha incorretos, tente novamente!");
            System.exit(0);
        } else {
            System.out.println("\nBem-vindo(a) a Oficina MTS!");
        }

        Elevador.elevadores[0] = new Elevador(1,"Alinhamento", false);
        Elevador.elevadores[1] = new Elevador(2,"Balanceamento", false);
        Elevador.elevadores[2] = new Elevador(3,"Corriqueiro", true);

        // questao 1.1 e questão 2.7
        // listar clientes
        System.out.println("\n--- Cadastro de Clientes ---");

        System.out.println("Quantos clientes deseja cadastrar? ");
        int quantidadeClientes = Integer.parseInt(scanner.nextLine());

        if (quantidadeClientes > 0){
            for (int i = 0; i < quantidadeClientes; i++){
                System.out.println("\nCadastro do cliente #" + (i + 1));

                System.out.println("Nome: ");
                String nome = scanner.nextLine();

                System.out.println("E-mail: ");
                String email = scanner.nextLine();

                System.out.println("Telefone: ");
                String telefone = scanner.nextLine();

                System.out.println("Endereco: ");
                String endereco = scanner.nextLine();

                System.out.println("CPF (no formato XXX.XXX.XXX-XX)");
                String cpf = scanner.nextLine();

                Cliente cliente = new Cliente(nome, email,telefone, endereco, cpf);
                System.out.println("Cliente cadastrado com sucesso!\n");
            }
        }

        System.out.println();
        Cliente.listarClientes();

        System.out.println("\nDeseja editar algum cliente? (s/n) ");
        char opcao = scanner.nextLine().charAt(0);

        if (opcao == 's'){
            System.out.println("\nDigite o ID do cliente que vc deseja editar: ");
            int idClienteEdicao = Integer.parseInt(scanner.nextLine());

            Cliente clienteEdicao =  Cliente.buscarClientePorId(idClienteEdicao);
            if (clienteEdicao != null){
                System.out.println("Novo nome: ");
                String novoNome = scanner.nextLine();

                System.out.println("Novo e-mail: ");
                String novoEmail = scanner.nextLine();

                System.out.println("Novo telefone: ");
                String novoTelefone = scanner.nextLine();

                System.out.println("Novo endereco: ");
                String novoEndereco = scanner.nextLine();

                System.out.println("Novo CPF (no formato XXX.XXX.XXX-XX)");
                String novoCpf = scanner.nextLine();

                Cliente.editarCliente(clienteEdicao.getId(), novoNome, novoEmail, novoTelefone,novoEndereco, novoCpf);
            } else {
                System.out.println("Cliente nao encontrado!");
            }
        }

        System.out.println();
        Cliente.listarClientes();

        System.out.println("\nDeseja remover algum cliente? (s/n) ");
        opcao = scanner.nextLine().charAt(0);

        if (opcao == 's'){
            System.out.println("\nDigite o ID do Cliente que vc deseja remover: ");
            int idClienteRemover = Integer.parseInt(scanner.nextLine());
            Cliente.removerCliente(idClienteRemover);
            System.out.println("\nCliente removido com sucesso!");
        }

        System.out.println();
        Cliente.listarClientes();

        // questao 1.2 e questão 2.6
        System.out.println("\n--- Cadastro de Funcionarios ---");

        // add funcionários
        System.out.println("Quantos funcionarios deseja cadastrar? ");
        int quantidadeFuncionarios = Integer.parseInt(scanner.nextLine());

        if (quantidadeFuncionarios > 0){
            for (int i = 0; i < quantidadeFuncionarios; i++){
                System.out.println("\nCadastro do funcionario #" + (i + 1));

                System.out.println("Nome: ");
                String nome = scanner.nextLine();

                System.out.println("E-mail: ");
                String email = scanner.nextLine();

                System.out.println("Telefone: ");
                String telefone = scanner.nextLine();

                System.out.println("Cargo: ");
                String cargo = scanner.nextLine();

                System.out.println("Login: ");
                String loginFuncionario = scanner.nextLine();

                System.out.println("Senha: ");
                String senhaFuncionario = scanner.nextLine();

                Sistema.addFuncionario(nome, email, telefone, cargo, loginFuncionario, senhaFuncionario);
                System.out.println("Funcionario cadastrado com sucesso!\n");
            }
        }

        System.out.println();
        Sistema.listarFuncionarios();

        System.out.println("\nDeseja editar algum funcionario? (s/n) ");
        char opcaoFuncionario = scanner.nextLine().charAt(0);

        if (opcaoFuncionario == 's'){
            System.out.println("\nDigite o ID do funcionario que vc deseja editar: ");
            int idFuncionarioEdicao = Integer.parseInt(scanner.nextLine());

            Funcionario funcionarioEdicao =  Sistema.buscarFuncionarioPorId(idFuncionarioEdicao);
            if (funcionarioEdicao != null){
                System.out.println("Novo nome: ");
                String novoNome = scanner.nextLine();

                System.out.println("Novo e-mail: ");
                String novoEmail = scanner.nextLine();

                System.out.println("Novo Telefone: ");
                String novoTelefone = scanner.nextLine();

                System.out.println("Novo cargo: ");
                String novoCargo = scanner.nextLine();

                System.out.println("Novo login: ");
                String novoLogin = scanner.nextLine();

                System.out.println("Nova senha: ");
                String novaSenha = scanner.nextLine();

                Sistema.editarFuncionario(funcionarioEdicao.getId(), novoNome, novoEmail, novoTelefone, novoCargo, novoLogin, novaSenha);
            } else {
                System.out.println("Funcionario nao encontrado!");
            }
        }

        System.out.println();
        Sistema.listarFuncionarios();

        System.out.println("\nDeseja remover algum funcionario? (s/n) ");
        opcaoFuncionario = scanner.nextLine().charAt(0);

        if (opcaoFuncionario == 's'){
            System.out.println("\nDigite o ID do Funcionario que vc deseja remover: ");
            int idClienteRemover = Integer.parseInt(scanner.nextLine());
            Sistema.removerFuncionario(idClienteRemover);
            System.out.println("\nFuncionario removido com sucesso!");
        }

        System.out.println();
        Sistema.listarFuncionarios();
        
        System.out.println("\n--- Cadastro de Veiculos ---");

        // add Veiculos
        System.out.println("Quantos Veiculos deseja cadastrar? ");
        int quantidadeVeiculos = Integer.parseInt(scanner.nextLine());

        if (quantidadeClientes > 0){
            for (int i = 0; i < quantidadeVeiculos; i++){
                System.out.println("\nCadastro do Veiculo #" + (i + 1));

                System.out.println("Placa: ");
                String placa = scanner.nextLine();

                System.out.println("Modelo: ");
                String modelo = scanner.nextLine();

                System.out.println("Marca: ");
                String marca = scanner.nextLine();

                System.out.println("Ano: ");
                int ano = scanner.nextInt();
                scanner.nextLine();

                System.out.println("Id Cliente: ");
                int idCliente = scanner.nextInt();
                scanner.nextLine();

                Veiculo veiculos = new Veiculo(placa, modelo, marca, ano, Cliente.buscarClientePorId(idCliente));
                System.out.println("Funcionario cadastrado com sucesso!\n");
            }
        }

        System.out.println();
        Veiculo.listarVeiculos();

        System.out.println("\nDeseja editar algum veiculo? (s/n) ");
        char opcaoVeiculo = scanner.nextLine().charAt(0);

        if (opcaoVeiculo == 's'){
            System.out.println("\nDigite a placa que vc deseja editar: ");
            String placaVeiculoEdicao = scanner.nextLine();

            Veiculo veiculoEdicao =  Veiculo.buscarVeiculoPorPlaca(placaVeiculoEdicao);
            if (veiculoEdicao != null){
                System.out.println("Nova placa: ");
                String novaPlaca = scanner.nextLine();

                System.out.println("Novo modelo ");
                String novoModelo = scanner.nextLine();

                System.out.println("Nova Marca: ");
                String novaMcarca = scanner.nextLine();

                System.out.println("Novo ano: ");
                int novoAno = scanner.nextInt();
                scanner.nextLine();

                System.out.println("Novo ID cliente associado: ");
                int novoClienteAssociado = scanner.nextInt();
                scanner.nextLine();

                Veiculo.editarVeiculo(placaVeiculoEdicao, novaPlaca, novoModelo, novaMcarca, novoAno, Cliente.buscarClientePorId(novoClienteAssociado));
            } else {
                System.out.println("Veiculo nao encontrado!");
            }
        }

        System.out.println();
        Veiculo.listarVeiculos();

        System.out.println("\nDeseja remover algum veiculo? (s/n) ");
        opcaoVeiculo = scanner.nextLine().charAt(0);

        if (opcaoVeiculo == 's'){
            System.out.println("\nDigite a placa do veiculo que vc deseja remover: ");
            String placaVeiculoremover = scanner.nextLine();
            Veiculo.removerVeiculo(placaVeiculoremover);
            System.out.println("\nVeiculo removido com sucesso!");
        }

        System.out.println();
        Veiculo.listarVeiculos();

        // gerenciamento de despesas (apenas adm tem permissao para gerenciar as despesas)
        System.out.println("\n--- Gerenciamento de despesas ---");
        // add despesa
        Despesa d1 = new Despesa(funcionarioLogado.getId(), "Reposicao de materiais", 99.9, "22-06-2025");
        Despesa d2 = new Despesa(funcionarioLogado.getId(), "Compra de materiais de limpeza", 250, "05-06-2025");
        Despesa d3 = new Despesa(funcionarioLogado.getId(), "Conta de luz", 100, "10-06-2025");
        Despesa.listarDespesas(funcionarioLogado.getId());

        // editar despesa
        Despesa.editarDespesa(funcionarioLogado.getId(), 1, "Cafe semanal", 200, "01-06-2025");
        Despesa.listarDespesas(funcionarioLogado.getId());

        // remover despesa
        Despesa.removerDespesa(funcionarioLogado.getId(), 2);
        Despesa.listarDespesas(funcionarioLogado.getId());

        // questao 1.3
        System.out.println("\n--- Alterar senha de administrador ---");

        Sistema.alterarSenhaAdministrador(funcionarioLogado.getId(), 1, "novaSenhaADM123");
        
        System.out.println("\nListar funcionarios alteracao de senha:");
        sistema.listarFuncionarios();
        
        // questao 1.4
        System.out.println("\n--- Buscar produtos por nome ---");
        
        // adicionar produtos
        Produto p1 = new Produto("Pneu", 10, 299.90);
        Produto p2 = new Produto("0leo", 20, 49.99);

        Produto.listarProdutos();
        
        // verificar estoque por nome
        p1.verificarProdutoNoEstoque("Pneu");
        p2.verificarProdutoNoEstoque("Retrovisor");
        
        // questao 1.5
        System.out.println("\n--- Realizar Agendamento ---");
        Agendamento a1 = new Agendamento(Cliente.buscarClientePorId(3), Veiculo.buscarVeiculoPorPlaca("GFR-7980"), Servico.buscarServicoPorId(1), sistema.buscarFuncionarioPorId(1), "10-06-2025 10:00", "agendado", "Tanque de oleo vazio");
        Agendamento a2 = new Agendamento(Cliente.buscarClientePorId(1), Veiculo.buscarVeiculoPorPlaca("BRA-1023"), Servico.buscarServicoPorId(2), sistema.buscarFuncionarioPorId(2), "12-06-2025 12:00",  "Cancelado","Carro desalinhado");
        Agendamento a3 = new Agendamento(Cliente.buscarClientePorId(2), Veiculo.buscarVeiculoPorPlaca("XYZ-4598"), Servico.buscarServicoPorId(4), sistema.buscarFuncionarioPorId(1), "09-06-2025 11:00",  "Agendado","Lanterna queimada");
        
        // questao 1.6
        System.out.println("\n--- Receber de fornecedores ---");
        p1.receberProdutoDeFornecedor(5); // adiciona 5 unidades ao estoque
        p1.verificarProdutoNoEstoque("Pneu");
        
        // questao 1.7
        System.out.println("\n--- Cancelar agendamento ---");
        a1.cancelarAgendamento(); 
        
        // questao 1.8
        System.out.println("\n--- Emitir relatorio de vendas e ordem de servico ---");
        // adicionando servicos
        Servico s1 = new Servico("Troca de oleo", 89.90);
        Servico s2 = new Servico("Alinhamento e balanceamento", 59.90);
        List<Servico> listaServico = new ArrayList<>(){{
            add(s2);
            add(s1);
        }};
        
        // adicionando produtos
        Produto p3 = new Produto("Filtro de oleo", 10, 45.90);
        Produto p4 = new Produto("Oleo 5w30", 15, 29.99);
        List<Produto> listaProduto = new ArrayList<>(){{
            add(p1);
            add(p2);
            add(p3);
            add(p4);
        
        }};

        // adicionando venda e ordem de servico
        Venda v = new Venda(p1, 4 , "12-06-2025", Cliente.buscarClientePorId(2));
        OrdemServico os = new OrdemServico(a2, listaServico, listaProduto, "06-06-2025");

        // emitindo relatorios por dia e por mes
        sistema.gerarRelatorioPorDia(funcionarioLogado.getId(),"12-06-2025", Venda.getVendas(), OrdemServico.getOrdensServico());
        sistema.gerarRelatorioPorMes(funcionarioLogado.getId(), "06-2025", Venda.getVendas(), OrdemServico.getOrdensServico());
        
        // questao 1.9 e questao 2.2
        System.out.println("\n--- Gerar Balanco Mensal ---");
        sistema.gerarBalancoMensal(funcionarioLogado.getId(),"06-2025", Venda.getVendas(), OrdemServico.getOrdensServico());
        
        // questão 2.8
        System.out.println("\n--- Imprimir dados das Ordens de Servico de cada cliente ---");
        os.imprimirOrdensPorCliente(Cliente.getClientes(), OrdemServico.getOrdensServico());
        
        // questao 2.10
        System.out.println("\n--- Gerar extrato de Venda e Ordem de servico e salva em arquivo .txt ---");
        Venda vendaNova = new Venda (p2, 3, "12-06-2025", Cliente.buscarClientePorId(2));
        OrdemServico novaOs = new OrdemServico(a1, listaServico, listaProduto, "06-06-2025");
        
        // questao 2.11
        System.out.println("\n--- Variaveis static que guardam o numero de instancias da classe Veiculo ---");
        System.out.println("Total de veiculos: " + Veiculo.getTotalVeiculos() ); // 2.11 letra a
        System.out.println("Total de veiculos: " + Veiculo.contadorVeiculosProtegido); // 2.11 letra b
        // 2.11 letra c - O uso de private static com métodos getters e setters oferece maior segurança e controle sobre o acesso ao atributo, permitindo validações e ocultando a implementação interna, mas exige mais código e pode gerar overhead. Já o uso de protected static permite acesso direto por subclasses e reduz a verbosidade, porém compromete o encapsulamento e aumenta o risco de alterações indevidas por herança.
        
        // questao 2.12
        System.out.println("\n--- Metodo de Sistema que retorna numero de instancias da classe Veiculo ---");
        System.out.println("Total de veiculos: " + sistema.getTotalVeiculosCriados(Veiculo.buscarVeiculoPorPlaca("GFR-7980")));
        
        // questao 2.13
        System.out.println("\n--- Interface Comparator ---");
        System.out.println("Lista de clientes antes da ordencao por nome:");
        Cliente[] listaCliente = {Cliente.buscarClientePorId(2), Cliente.buscarClientePorId(3), Cliente.buscarClientePorId(1)};
        for (Cliente c : listaCliente){
            System.out.println(c.getNome());
        }
        Ordenador.ordenarClientesPorNome(listaCliente);
        System.out.println("\nLista de clientes apos ordencao por nome:");
        for (Cliente c : listaCliente){
            System.out.println(c.getNome());
        }
        
        System.out.println("\nLista  de clientes antes da ordenacao por id:");
        Cliente c2 = Cliente.buscarClientePorId(2);
        Cliente c5 = Cliente.buscarClientePorId(5);
        Cliente c4 = Cliente.buscarClientePorId(4);
        Cliente c3 = Cliente.buscarClientePorId(3);
        Cliente c1 = Cliente.buscarClientePorId(1);
        Cliente[] listaClientePorId = {c2, c3, c1};
        for (Cliente c : listaClientePorId){
            System.out.println(c.getId());
        }
        Ordenador.ordenarClientes(listaClientePorId);
        System.out.println("\nLista de clientes apos ordencao por id:");
        for (Cliente c : listaClientePorId){
            System.out.println(c.getId());
        }
      
        
        System.out.println("\nLista de agendamentos antes da ordenacao:");
        Agendamento[] listaAgendamentos = {a2, a3, a1};
        for (Agendamento a : listaAgendamentos){
            System.out.println("id: " + a.getId());
        }
        Ordenador.ordenarAgendamento(listaAgendamentos);
        System.out.println("\nLista de agendamentos depois da ordenacao por ID:");
        for (Agendamento a : listaAgendamentos){
            System.out.println("id: " + a.getId());
        }
        
        //Questão 2.14
        System.out.println("\n--- Json ---");
        System.out.println("Clientes:");
        Cliente.salvarClientes();
        
        System.out.println("\nVeiculos:");
        Veiculo.salvarVeiculos();
        
        System.out.println("\nAgendamentos:");
        Agendamento.salvarAgendamento();
        
        System.out.println("\nProdutos:");
        Produto.salvarProduto();
        
        System.out.println("\nVendas:");
        Venda.salvarVenda();
        
        System.out.println("\nOrdem Servicos:");
        OrdemServico.salvarOrdemServico();
        
        System.out.println("\nServico:");
        Servico.salvarServico();
        
        System.out.println("\nFuncionario:");
        Funcionario.salvarFuncionarios();

        System.out.println("\nDespesas:");
        Despesa.salvarDespesas();
        
        //Questão extra 15
        System.out.println("\n--- Iterator ---");
        List<Cliente> listaClienteIterator = Cliente.getClientes();
        Iterator<Cliente> iterator = listaClienteIterator.iterator();
        while (iterator.hasNext()){
            Cliente c = iterator.next();
            System.out.println(c.getId() + " - " + c.getNome());
        }
        System.out.println("\nPercorrendo com o foreach");
        for (Cliente c : listaClienteIterator){
            System.out.println(c.getId() + " - " + c.getNome());
        }
        
        // Questão extra 16 comparator
        System.out.println("\n--- Comparator e sort ---");
        List<Cliente> listaCliente2 =  new ArrayList<>();
        listaCliente2.add(c3);
        listaCliente2.add(c1);
        listaCliente2.add(c2);
        List<Cliente> listaCliente3 = new ArrayList<>();
        listaCliente3.add(c3);
        listaCliente3.add(c1);
        listaCliente3.add(c2);
        
        System.out.println("\nLista de clientes antes da ordenacao:");
        for(Cliente c : listaCliente2){
            System.out.println(c.getNome());
        }
        System.out.println("\nLista de clientes apos a ordenacao:");
        Collections.sort(listaCliente2, new ComparadorClientePorNome());
        for(Cliente c : listaCliente2){
            System.out.println(c.getNome());
        }
        
        System.out.println("\nLista de clientes antes da ordenacao por ID:");
        for(Cliente c : listaCliente3){
            System.out.println(c.getId());
        }
        
        System.out.println("\nLista de clientes apos a ordenacao por ID:");
        Collections.sort(listaCliente3, new ComparadorClientePorId());
        for(Cliente c : listaCliente3){
            System.out.println(c.getId());
        }
        
        //Questão extra 17
        System.out.println("\n--- Find e binarySearch ---");
        Comparator<Cliente> comparadorPorNome = new ComparadorClientePorNome();
        List<Cliente> listaFind = Cliente.getClientes();
        Cliente resultado = Cliente.findClientePorNome("Ana", listaFind, comparadorPorNome);
        if (resultado != null){
            System.out.println("Encontrado via find: " + resultado.getNome());
        }else {
            System.out.println("Cliente nao encontrado via find");
        }
        List<Cliente> listaBinaria = Cliente.getClientes();
        Collections.sort(listaBinaria, comparadorPorNome);
        Cliente chave = new Cliente("Carla");
        int indice = Collections.binarySearch(listaBinaria, chave, comparadorPorNome);
        if (indice >= 0){
            Cliente encontrado = listaBinaria.get(indice);
            System.out.println("Encontrado via binarySearch: " + encontrado.getNome());
        } else{
            System.out.println("Cliente nao encontrado via binarySearch.");
        }
    }
}