package com.mycompany.oficinamecanica.OficinaSistema;

import com.mycompany.oficinamecanica.OficinaDados.Agendamento;

public class ComparadorAgendamentoPorId {
    
    public static int compararAgendamentoPorId(Agendamento a1, Agendamento a2){
        if (a1.getId() == a2.getId()) return 0;
        else if (a1.getId() > a2.getId()) return 1;
        else return -1;
    }
}