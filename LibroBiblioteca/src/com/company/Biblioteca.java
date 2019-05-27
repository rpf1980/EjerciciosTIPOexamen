package com.company;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class Biblioteca
{
    //Atributos
    private List <Libro> listaLibros;

    //Constructor
    public Biblioteca ()
    {
        listaLibros = new ArrayList<>();
    }

    //Metodo
    public String toString()
    {
        return listaLibros.toString();
    }

    public boolean insertaLibro (Libro l)
    {
        boolean respuesta, encontrado;
        encontrado=false;
        int i;

        for (i=0;i<listaLibros.size();i++)
        {
            if (listaLibros.get(i).getIsbn().equals(l.getIsbn()))
            {
                encontrado=true;
            }
        }
        if (encontrado)
        {
            respuesta=true;
        }
        else
        {
            listaLibros.add(l);
            respuesta=false;
        }

        return respuesta;
    }

    public int marcarLeidoTitulo (String cadena)
    {
        int i, ntitulos=0;
        String minuscula;
        minuscula=cadena.toLowerCase();
        String [] trozos= minuscula.split(" ");

        for (i=0;i<trozos.length;i++)
        {
            if (trozos[i].contains(cadena))
            {
                ntitulos++;
                listaLibros.get(i).isLeido();
            }
        }
        return ntitulos;
    }

    public void guardarCSV (String fichero)
    {
        int i;
        try
        {
            FileWriter fw = new FileWriter(fichero);
            BufferedWriter bw = new BufferedWriter(fw);

            for (i=0;i<listaLibros.size();i++)
            {
                bw.write(listaLibros.get(i).getIsbn());
                bw.write(";");
                bw.write(listaLibros.get(i).getTitulo());
                bw.write(";");
                bw.write(listaLibros.get(i).getnPaginas());
                bw.write(";");
                bw.write("leído(");
                bw.write(listaLibros.get(i).getIsbn());
                bw.write(")");
                bw.newLine();

            }

            bw.close();
            fw.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void leerCSV (String fichero)
    {
        String [] trozos;
        String linea, isbn, titulo, autor;
        int npaginas;
        boolean leido;

        listaLibros.clear();
        try
        {
            FileReader fr = new FileReader(fichero);
            BufferedReader br = new BufferedReader(fr);

            linea = br.readLine();
            while(linea != null)
            {
                trozos=linea.split(";");
                isbn=trozos[0];
                titulo=trozos[1];
                autor=trozos[2];
                npaginas=Integer.parseInt(trozos[3]);
                leido = Boolean.parseBoolean(trozos[4]);
                linea = br.readLine();

                Libro l = new Libro(isbn, titulo, autor, npaginas);
                l.setLeido(leido);

                listaLibros.add(l);
            }

            br.close();
            fr.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
