package com.company;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Calendar;

public class Main
{
    public static boolean tiene4vocales(String cadena)
    {
        boolean ok;
        String vocales = "aeiouAEIOUáéíóúÁÉÍÓÚüÜ";
        int contador = 0;
        int i;

        for(i = 0; i < cadena.length(); i++)
        {
            if(vocales.indexOf(cadena.charAt(i)) != -1)
            {
                contador++;
            }
        }

        if(contador >= 4)
        {
            ok = true;
        }
        else
        {
            ok = false;
        }

        return ok;
    }

    public static String limpiaCadena(String cadena)
    {
        String copia = "";
        int i;

        for(i = 0; i < cadena.length(); i++)
        {
            if(cadena.charAt(i) == ' ' || Character.isLetter(cadena.charAt(i)))
            {
                copia = copia + cadena.charAt(i);
            }
        }
        return copia;
    }

    public static void cuatroVocales(String fichero)
    {
        int i;
        String linea;
        String palabra;
        String[] arrayPalabras;

        try
        {
            FileReader fr = new FileReader(fichero);
            BufferedReader br = new BufferedReader(fr);

            FileWriter fw = new FileWriter("4vocales.txt");
            BufferedWriter bw = new BufferedWriter(fw);

            linea = br.readLine();

            while(linea != null)
            {
                linea = limpiaCadena(linea);
                arrayPalabras = linea.split(" ");

                for(i = 0; i < arrayPalabras.length; i++)
                {
                    palabra = arrayPalabras[i];

                    if(tiene4vocales(palabra))
                    {
                        bw.write(palabra);
                        bw.newLine();
                    }
                }

                linea = br.readLine();
            }

            bw.close();
            fw.close();

            br.close();
            fr.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        cuatroVocales("lazarilloBien.txt");
    }
}

