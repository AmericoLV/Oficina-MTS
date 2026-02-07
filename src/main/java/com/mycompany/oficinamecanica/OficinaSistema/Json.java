package com.mycompany.oficinamecanica.OficinaSistema;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import com.google.gson.Gson;
import java.io.FileReader;
import java.io.Reader;
import java.lang.reflect.Type;


public class Json {
    private static final Gson gson = new Gson();
    
    public static <T> void salvarLista(List<T> lista, String caminho){
        try (Writer writer = new FileWriter(caminho)){
            gson.toJson(lista, writer);
        } catch (IOException e){
            System.out.println("Erro ao salvar: " + e.getMessage());
        }
    }   
    
    public static <T> List<T> carregarLista(String caminho, Type tipo){
        try (Reader reader = new FileReader(caminho)){
            return gson.fromJson(reader, tipo);
        } catch (IOException e) {
            System.out.println("Erro ao carregar: " + e.getMessage());
            return null;
        }
    }
}
