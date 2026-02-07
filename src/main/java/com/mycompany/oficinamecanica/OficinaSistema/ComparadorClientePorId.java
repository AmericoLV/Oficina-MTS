package com.mycompany.oficinamecanica.OficinaSistema;

import com.mycompany.oficinamecanica.OficinaDados.Cliente;
import java.util.Comparator;

public class ComparadorClientePorId implements Comparator<Cliente> {
   
    public static int compararClientePorId(Cliente c1, Cliente c2){
        if (c1.getId() == c2.getId()) return 0;
        else if (c1.getId() > c2.getId()) return 1;
        else return -1;
    }
    
    // esta sobrescrevendo apenas para utilizar o Collections.sort na questao extra 16
    @Override
    public int compare(Cliente c1, Cliente c2){
        return compararClientePorId(c1, c2);
    }
}
