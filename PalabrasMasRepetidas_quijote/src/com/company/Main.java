package com.company;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
        int max = 0;
        int pos = -1;
        int contador = 0;
        String palabra;
        String linea;
        String[] trozos;
        List<String> listaPalabrasUnicas = new ArrayList<>();
        List<String> listaConTodoElTexto = new ArrayList<>();
        List<Integer> listaContador = new ArrayList<>();
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
            br.close();
            fr.close();

            /*Comparamos todo el contenido del texto con nuestra lista de palabras únicas y cada vez que coincidan se suma el contador en dicha posición*/
            arrayContador = new int[listaPalabrasUnicas.size()]; //Al array contador le damos el tamaño de la lista de palabras únicas
            for(i = 0; i < listaPalabrasUnicas.size(); i++)
            {
                for(j = 0; j < listaConTodoElTexto.size(); j++)
                {
                    if(listaPalabrasUnicas.get(i).equals(listaConTodoElTexto.get(j)))
                    {
                        arrayContador[i]++;
                    }
                }
            }

            //Ahora tenemos que buscar los 10 valores mayores de la lista de únicos ... Recuerda que nuestro contador tiene las mismas posiciones que dicha lista
            //Voy a pasar el contador a una lista
            for(i = 0; i < arrayContador.length; i++)
            {
                listaContador.add(arrayContador[i]);
            }

            //Ahora es cuando buscamos los máximos de la lista y los vamos borrando tanto en una como en otra
            listaConTodoElTexto.clear();
            for(i = 0; i < listaContador.size(); i++)
            {
                max = Collections.max(listaContador);
                pos = listaContador.indexOf(max);

                listaConTodoElTexto.add(listaPalabrasUnicas.get(pos));

                listaContador.remove(pos);
                listaPalabrasUnicas.remove(pos);
                i--;

                if(listaConTodoElTexto.size() == 3)
                {
                    i = listaContador.size();
                }
            }

            System.out.println(listaConTodoElTexto);
            System.out.println(listaConTodoElTexto.size());

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        //palabraMasRepite("donquijote.txt");

        //Hacemos la prueba con un fragmento del libro.. ya que el programa se cuelga debido al contenido masivo del mismo
        palabraMasRepite("parrafoPruebas.txt");
    }
}

