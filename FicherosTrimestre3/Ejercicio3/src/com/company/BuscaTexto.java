package com.company;

import java.io.BufferedReader;
import java.io.FileReader;

public class BuscaTexto
{
    //Atributos
    private String texto;
    private int puntero;

    //Constructores
    public BuscaTexto()
    {
        this.texto = "";
        this.setPuntero(0);
    }

    public BuscaTexto(String cadena)
    {
        this.texto = cadena;
        this.puntero = 0;
    }

    //Métodos
    public void cargarFichero(String fichero)
    {
        String linea;
        this.texto = "";

        try
        {
            FileReader fr = new FileReader(fichero);
            BufferedReader br = new BufferedReader(fr);

            linea = br.readLine();
            while(linea != null)
            {
                this.texto = this.texto + linea + "\n";

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

    public void busca(String cadena)
    {
        setPuntero(this.texto.indexOf(cadena));
    }

    public void buscaSiguiente(String cadena)
    {
        setPuntero(this.texto.indexOf(cadena, puntero+1));
    }

    public String extraeCadena(String del1, String del2)
    {
        String cadena = "";

        int pos1 = this.texto.indexOf(del1, puntero);
        int pos2 = this.texto.indexOf(del2, pos1+1);

        cadena = texto.substring(pos1 + del1.length(), pos2);
        return cadena;
    }

    //Propiedades de los atributos
    public int getPuntero()
    {
        return puntero;
    }

    public void setPuntero(int puntero)
    {
        if(puntero < 0)
        {
            this.puntero = 0;
        }
        else
        {
            if(puntero > this.texto.length())
            {
                this.puntero = this.texto.length();
            }
        }
        this.puntero = puntero;
    }

    public String getTexto()
    {
        return texto;
    }
}
