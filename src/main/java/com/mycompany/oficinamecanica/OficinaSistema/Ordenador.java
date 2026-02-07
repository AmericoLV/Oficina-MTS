package com.mycompany.oficinamecanica.OficinaSistema;

import com.mycompany.oficinamecanica.OficinaDados.Agendamento;
import com.mycompany.oficinamecanica.OficinaDados.Cliente;
import static com.mycompany.oficinamecanica.OficinaSistema.ComparadorAgendamentoPorId.compararAgendamentoPorId;
import static com.mycompany.oficinamecanica.OficinaSistema.ComparadorClientePorId.compararClientePorId;
import static com.mycompany.oficinamecanica.OficinaSistema.ComparadorClientePorNome.compararClientePorNome;



public class Ordenador {
    
    public static void ordenarClientes(Cliente[] clientes){
        for(int i = 0; i < clientes.length -1; i++){
            for(int j = 0; j < clientes.length -i -1; j++){
                if(compararClientePorId(clientes[j], clientes[j+1])> 0){
                    Cliente aux = clientes[j];
                    clientes[j] =  clientes[j+1];
                    clientes[j+1] = aux;
                }
            }
        }
    }
    
    public static void ordenarClientesPorNome(Cliente[] clientes){
        for(int i = 0; i < clientes.length -1; i++){
            for(int j = 0; j < clientes.length -i -1; j++){
                if(compararClientePorNome(clientes[j], clientes[j+1])> 0){
                    Cliente aux = clientes[j];
                    clientes[j] =  clientes[j+1];
                    clientes[j+1] = aux;
                }
            }
        }
    }
    
    public static void ordenarAgendamento(Agendamento[] agendamentos){
        for(int i = 0; i < agendamentos.length -1; i++){
            for(int j = 0; j < agendamentos.length -i -1; j++){
                if(compararAgendamentoPorId(agendamentos[j], agendamentos[j+1])> 0){
                    Agendamento aux = agendamentos[j];
                    agendamentos[j] =  agendamentos[j+1];
                    agendamentos[j+1] = aux;
                }
            }
        }
    }
    
}
