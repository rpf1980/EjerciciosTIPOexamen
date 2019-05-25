package com.company;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        int i, pos, suma, cont, n, media;
        String nombreCompleto, nombreFichero, extension;
        File ficheros = new File(".");
        File [] listado = ficheros.listFiles();
        List <String> anotadores= new ArrayList<>();
        List <Integer> listaMedias = new ArrayList<>();
        extension=".stat";

        int maxList;
        List<String> listaAnotadoresFinal = new ArrayList<>();
        List<Integer> listaMediaFinal = new ArrayList<>();

        for (i=0;i<listado.length;i++)
        {
            nombreCompleto = listado[i].getName();
            if (nombreCompleto.endsWith(extension))
            {
                pos = nombreCompleto.lastIndexOf(".");
                nombreFichero = nombreCompleto.substring(0, pos);
                anotadores.add(nombreFichero);
            }
        }
        System.out.println(anotadores);
        try
        {
            for (i=0; i<listado.length;i++)
            {
                nombreCompleto = listado[i].getName();
                if (nombreCompleto.endsWith(extension)) //Nuevamente hay que especificar la extensión del fichero
                {
                    suma = 0;
                    cont = 0;

                    FileInputStream fis = new FileInputStream(listado[i]);
                    DataInputStream dis = new DataInputStream(fis);

                    while (dis.available() > 0)
                    {
                        n = dis.readInt();
                        suma = suma + n;
                        cont++;
                    }
                    dis.close();
                    fis.close();
                    media = suma / cont;
                    listaMedias.add(media);
                }
            }
            System.out.println(listaMedias);

            //Aquí ordenamos las listas de mayor a menor dato-media
            maxList = listaMedias.get(0);
            for(i = 0; i < listaMedias.size(); i++)
            {
                if(listaMedias.get(i) > maxList)
                {
                    maxList = listaMedias.get(i);
                    pos = listaMedias.indexOf(maxList);

                    listaMediaFinal.add(maxList);
                    listaAnotadoresFinal.add(anotadores.get(pos));

                    listaMedias.remove(pos);
                    anotadores.remove(pos);
                    i--;
                }
            }

            //Mostramos los resultados por consola
            for(i = 0; i < listaMediaFinal.size(); i++)
            {
                System.out.println(padRight(listaAnotadoresFinal.get(i), 16) + padLeft(String.valueOf(listaAnotadoresFinal.get(i)), 9));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    //Métodos aparte
    public static String padLeft(String c, int longitud)
    {
        String cadena = c;
        int cantidadEspacios = longitud - c.length();
        int i;

        for (i = 0; i < cantidadEspacios; i++)
        {
            cadena = " " + cadena;
        }
        return cadena;
    }

    public static String padRight(String c, int longitud)
    {
        String cadena = c;
        int cantidadEsapcios = longitud - c.length();
        int i;

        for (i = 0; i < cantidadEsapcios; i++)
        {
            cadena = cadena + " ";
        }
        return cadena;
    }
}
