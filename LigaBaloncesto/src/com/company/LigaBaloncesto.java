package com.company;

import com.sun.nio.sctp.SctpStandardSocketOptions;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

public class LigaBaloncesto
{
    //Atributos
    private List<EquipoBaloncesto> listaEquipos;

    //Constructor
    public LigaBaloncesto()
    {
        this.listaEquipos = new ArrayList<EquipoBaloncesto>();
    }

    //Métodos
    public void cargarLiga(String fichero)
    {
        this.getListaEquipos().clear();

        String linea;
        String[] trozos;
        String nombre;
        int victorias, derrotas, puntosAnotados, puntosRecibidos;

        try
        {
            FileReader fr = new FileReader(fichero);
            BufferedReader br = new BufferedReader(fr);

            linea = br.readLine();
            while(linea != null)
            {
                trozos = linea.split(";");

                nombre = trozos[0];
                victorias = Integer.parseInt(trozos[1]);
                derrotas = Integer.parseInt(trozos[2]);
                puntosAnotados = Integer.parseInt(trozos[3]);
                puntosRecibidos = Integer.parseInt(trozos[4]);

                EquipoBaloncesto eb = new EquipoBaloncesto(nombre, victorias, derrotas,
                        puntosAnotados, puntosRecibidos);

                getListaEquipos().add(eb);

                linea = br.readLine();

            }
            br.close();
            fr.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void guardarLiga(String fichero)
    {
        int i;
<<<<<<< HEAD
        int puntosRecibidos;
=======
        int puntosRecib = 0;
>>>>>>> ed9eb90480ebe361dc558a90f2c16717bbe781c5

        try
        {
            FileWriter fw = new FileWriter(fichero);
            BufferedWriter bw = new BufferedWriter(fw);

            for(i = 0; i < this.getListaEquipos().size(); i++)
            {
<<<<<<< HEAD
                EquipoBaloncesto equipo = this.getListaEquipos().get(i);
=======
                EquipoBaloncesto equipo = this.listaEquipos.get(i);

                bw.write(equipo.getNombre() + ";" + equipo.getVictorias() + ";" + equipo.getDerrotas() +
                            equipo.getPuntosAnotados() + ";" + equipo.getPuntosRecibidos());
                
>>>>>>> ed9eb90480ebe361dc558a90f2c16717bbe781c5

                bw.write(equipo.getNombre() + ";" + equipo.getVictorias() + ";" + equipo.getDerrotas() + ";"
                            + equipo.getPuntosAnotados() + ";" + equipo.getPuntosRecibidos());
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

    //Método clasificacion
    public String clasificacion()
    {
        String cadena = "";

        while(listaEquipos.size() > 0) //Mientras tengamos contenido en la lista
        {
            //Buscamos el máximo de la lista
            int maxVictorias;
            int maxPuntos;
            int pos;

            pos = 0;
            maxVictorias = listaEquipos.get(0).getVictorias();
            maxPuntos = listaEquipos.get(0).getPuntosAnotados() - listaEquipos.get(0).getPuntosRecibidos();
            for(int i = 1; i < listaEquipos.size(); i++) //Posición 1 porque es donde tenemos las victorias en la lista
            {
                if ((listaEquipos.get(i).getVictorias() > maxVictorias) ||
                        (listaEquipos.get(i).getVictorias() == maxVictorias && (listaEquipos.get(i).getPuntosAnotados()-listaEquipos.get(i).getPuntosRecibidos()) > maxPuntos))
                {
                    pos = i;
                    maxVictorias = listaEquipos.get(i).getVictorias();
                    maxPuntos = listaEquipos.get(i).getPuntosAnotados() - listaEquipos.get(i).getPuntosRecibidos();
                }
            }

            EquipoBaloncesto equipo = listaEquipos.get(pos);

            cadena = cadena + padRight(equipo.getNombre(),30);
            cadena = cadena + padRight(equipo.getVictorias() + "-" + equipo.getDerrotas(), 8);

            int diferencia = equipo.getPuntosAnotados() - equipo.getPuntosRecibidos();
            if(diferencia > 0)
            {
                cadena = cadena + padLeft("+" + diferencia, 5);
            }
            else
            {
                cadena = cadena + padLeft(String.valueOf(diferencia), 5);
            }
            cadena = cadena + "\n";

            listaEquipos.remove(pos);
        }
        return cadena;
    }

    public String padRight(String cadena, int longitud)
    {
        while(cadena.length() < longitud)
        {
            cadena = cadena + " ";
        }
        return cadena;
    }

    public String padLeft(String cadena, int longitud)
    {
        while(cadena.length() < longitud)
        {
            cadena = " " + cadena;
        }
        return cadena;
    }

    //Propiedad del atributo de la clase
    public List<EquipoBaloncesto> getListaEquipos()
    {
        return listaEquipos;
    }


}
