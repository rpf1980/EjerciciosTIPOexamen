package com.company;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class Main
{
    public static void main(String[] args)
    {
        String textoCargado = "";
        BuscaTexto b = new BuscaTexto(textoCargado);

        b.cargarFichero("cambio.html");
        b.busca("cc-ratebox");
        b.buscaSiguiente("tabindex");

        String cadena = b.extraeCadena(">", "<");
        System.out.println(cadena);
    }
}