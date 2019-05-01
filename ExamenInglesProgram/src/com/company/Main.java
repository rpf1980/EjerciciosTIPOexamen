package com.company;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Main
{
    public static void main(String[] args)
    {
        EnglishTest test = new EnglishTest("frases_ingles.txt");
        List<String> listaFrases = test.getListaFrases();
        List<String> listaMayusculas = test.primerasEnMayuscula(listaFrases);


        for(int i = 0; i < 5; i++)
        {
            test.pregunta();
        }

    }
}

