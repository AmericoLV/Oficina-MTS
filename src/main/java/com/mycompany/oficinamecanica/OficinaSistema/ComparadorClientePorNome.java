package com.mycompany.oficinamecanica.OficinaSistema;

import com.mycompany.oficinamecanica.OficinaDados.Cliente;
import java.util.Comparator;

public class ComparadorClientePorNome implements Comparator<Cliente>{
  
   public static int compararClientePorNome(Cliente c1, Cliente c2){
       String nome1 = c1.getNome();
       String nome2 = c2.getNome();
       int minLength = Math.min(nome1.length(), nome2.length());
       
       for (int i = 0; i < minLength; i++){
           char ch1 = nome1.charAt(i);
           char ch2 = nome2.charAt(i);
           
           if (ch1 < ch2) return -1;
           if (ch1 > ch2) return 1;
       }
       
       // Se os primeiros caracteres são iguais, quem tiver mais letras é maior
       if (nome1.length() < nome2.length()) return -1;
       if (nome1.length() > nome2.length()) return 1;
       
       return 0; //sao iguais
   }
   
   @Override
   public int compare(Cliente c1, Cliente c2){
       return compararClientePorNome(c1, c2);
   }
           
}
