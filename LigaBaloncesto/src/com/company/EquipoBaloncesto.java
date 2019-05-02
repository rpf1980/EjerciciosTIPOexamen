package com.company;

public class EquipoBaloncesto
{
    //Atributos
    private String nombre;
    private int victorias;
    private int derrotas;
    private int puntosAnotados;
    private int puntosRecibidos;

    //Propiedades de los atributos (solo lectura)
    public String getNombre()
    {
        return nombre;
    }

    public int getVictorias()
    {
        return victorias;
    }

    public int getDerrotas()
    {
        return derrotas;
    }

    public int getPuntosAnotados()
    {
        return puntosAnotados;
    }

    public int getPuntosRecibidos()
    {
        return puntosRecibidos;
    }

    //Constructores
    public EquipoBaloncesto(String nombre)
    {
        this.nombre = nombre;
        this.victorias = 0;
        this.derrotas = 0;
        this.puntosAnotados = 0;
        this.puntosRecibidos = 0;
    }

    public EquipoBaloncesto(String nombre, int victorias, int derrotas,
                                int puntosAnotados, int puntosRecibidos)
    {
        this.nombre = nombre;
        this.victorias = victorias;
        this.derrotas = derrotas;
        this.puntosAnotados = puntosAnotados;
        this.puntosRecibidos = puntosRecibidos;
    }

    //Método toString
    public String toString()
    {
       return getNombre();
    }

}
