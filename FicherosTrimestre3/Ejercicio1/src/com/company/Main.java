
package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class Main
{
    public static String padLeft(String cadena, int longitud)
    {
        while(cadena.length() < longitud)
        {
            cadena = " " + cadena;
        }
        return cadena;
    }

    public static String padRight(String cadena, int longitud)
    {
        while(cadena.length() < longitud)
        {
            cadena = cadena + " ";
        }
        return cadena;
    }

    public static int[] arrayContador(List<String> listaPartidosOriginal, List<String> listaPartidos)
    {
        int i, j;
        int[] contador = new int[listaPartidos.size()];

        for(i = 0; i < listaPartidosOriginal.size(); i++)
        {
            for(j = 0; j < listaPartidos.size(); j++)
            {
                if(listaPartidosOriginal.get(i).equals(listaPartidos.get(j)))
                {
                    contador[j]++;
                }
            }
        }
        return contador;
    }

    public static void recuentoVotos(String nombreFicheroDB)
    {
        //DB
        Connection conn;
        Statement st;
        ResultSet rs;

        int i;
        int pos = -1;
        int maxLista;
        int totalVotos = 0;
        String sql = "";
        String partido = "";
        int voto = 0;
        List<String> listaPartidosOriginal = new ArrayList<>();
        List<String> listaPartidosAuxiliar = new ArrayList<>();
        List<Integer> listaDelArray = new ArrayList<>();

        List<Integer> listaVotos = new ArrayList<>();
        List<String> listaPartidos = new ArrayList<>();

        try
        {
            //Conectamos con la base de datos
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:" + nombreFicheroDB);

            st = conn.createStatement();
            sql = "SELECT id, partido FROM votacion;";
            rs = st.executeQuery(sql);

            while(rs.next())
            {
                partido = rs.getString(2);
                listaPartidosOriginal.add(partido); //Lista completa de partidos de la BD
            }

            //Generamos una lista de partidos pero sin repetir
            for(i = 0; i < listaPartidosOriginal.size(); i++)
            {
                if(!listaPartidosAuxiliar.contains(listaPartidosOriginal.get(i)))
                {
                    listaPartidosAuxiliar.add(listaPartidosOriginal.get(i));
                }
            }

            //Array que contiene la lista de conteos de votos por partido
            int[] arrayContador = arrayContador(listaPartidosOriginal, listaPartidosAuxiliar);

            //Pasamos el array a una lista para trabajar más cómodo
            for(i = 0; i < arrayContador.length; i++)
            {
                listaDelArray.add(arrayContador[i]);
            }

            //Ordenamos de mayor a menor votación. Usaremos dos listas una para los votos y otra para los partidos
            for(i = 0; i < listaDelArray.size(); i++)
            {
                maxLista = Collections.max(listaDelArray); //Guardamos el elemento mayor ( mayor de las conteos de votos )
                pos = listaDelArray.indexOf(maxLista); //Guardamos su posición

                listaVotos.add(maxLista); //Añadimos el mayor de los conteos de votos a la lista votos
                listaPartidos.add(listaPartidosAuxiliar.get(pos)); //Añadimos el partido correspondiente a ese conteo de votos

                listaDelArray.remove(pos);
                listaPartidosAuxiliar.remove(pos);
                i--;
            }

            //Calculamos el total de los votos de todo el conjunto de votos
            for(i = 0; i < listaVotos.size(); i++)
            {
                totalVotos = totalVotos + listaVotos.get(i);
            }

            //Mostramos por consola el resultado final
            for(i = 0; i < listaVotos.size(); i++)
            {
                String part;
                int vot;
                System.out.println(padRight(listaPartidos.get(i),16) + padLeft(listaVotos.get(i).toString(),3)
                    + "   votos   " + ((listaVotos.get(i) * 100) / totalVotos) + "%");
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        recuentoVotos("votacion1.db");
    }
}

