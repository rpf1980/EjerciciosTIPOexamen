package com.company;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main
{

    //Métodos aparte

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


    public static void palabraMasRepite(String fichero)
    {
        int i,j;
        int contador = 0;
        String palabra;
        String linea;
        String[] trozos;
        List<String> listaPalabrasUnicas = new ArrayList<>();
        List<String> listaConTodoElTexto = new ArrayList<>();
        int[] arrayContador;

        try
        {
            FileReader fr = new FileReader(fichero);
            BufferedReader br = new BufferedReader(fr);

            linea = br.readLine();
            while(linea != null)
            {
                linea = limpiaCadena(linea); //Aquí va limpiando las lineas del fichero
                linea = linea.toLowerCase(); //Las pasamos todas a minúsculas
                trozos = linea.split(" ");

                //Recorremos los arrays generados (array por linea)
                for(i = 0; i < trozos.length; i++)
                {
                    palabra = trozos[i]; //Tomamos elemento por elemento de cada array que recorremos (elemento == palabra)
                    listaConTodoElTexto.add(palabra); //Lista con todas las palabras del texto

                    //Si esta palabra no está en nuestra lista de palabras sin repetir... la añadimos
                    if(!listaPalabrasUnicas.contains(palabra))
                    {
                        listaPalabrasUnicas.add(palabra);
                    }
                }
                linea = br.readLine();
            }

            //Comparamos todo el contenido del texto con nuestra lista de palabras únicas y cada vez que coincidan se suma el contador en dicha posición
            arrayContador = new int[listaPalabrasUnicas.size()]; //Al array contador le damos el tamaño de la lista de palabras únicas
            for(i = 0; i < listaConTodoElTexto.size(); i++)
            {
                for(j = 0; j < listaPalabrasUnicas.size(); j++)
                {
                    if(listaConTodoElTexto.get(i).equals(listaPalabrasUnicas.get(i)))
                    {
                        arrayContador[j]++;
                    }
                }
            }

            //Ahora tenemos que comparar los tamaños de los elementos del contador... y con ello tendremos números de veces que se repiten las palabras de nuestra lista de palabras únicas

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
        palabraMasRepite("parrafoPruebas.txt");
    }
}

