package com.company;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class LigaBaloncesto
{
    //Atributos
    List<EquipoBaloncesto> listaEquipos;

    //Constructor
    public LigaBaloncesto()
    {
        this.listaEquipos = new ArrayList<EquipoBaloncesto>();
    }

    //Métodos
    public void cargarLiga(String fichero)
    {
        this.listaEquipos.clear();

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

                listaEquipos.add(eb);

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
        int puntosRecib;

        try
        {
            FileWriter fw = new FileWriter(fichero);
            BufferedWriter bw = new BufferedWriter(fw);

            for(i = 0; i < this.listaEquipos.size(); i++)
            {
                EquipoBaloncesto equipo = this.listaEquipos.get(i);

                bw.write(equipo.getNombre() + ";");
                bw.write(equipo.getVictorias() + ";");
                bw.write(equipo.getDerrotas() + ";");
                bw.write(equipo.getPuntosAnotados() + ";");
                puntosRecib = equipo.getPuntosRecibidos();
                bw.write(puntosRecib);

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
}
