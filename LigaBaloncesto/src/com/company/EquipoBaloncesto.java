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

    //Método para sacar mayor - menor - igual de las victorias
    public int compareTo(Object obj)
    {
        EquipoBaloncesto equipo = (EquipoBaloncesto)obj; //Ojeto genérico (se adapta a la clase que lo asignemos)

        if(this.victorias > equipo.victorias)
        {
            return 1;
        }
        else
        {
            if(this.victorias < equipo.victorias)
            {
                return -1;
            }
            else
            {
                //En esta parte entramos si hay empate... y lo que hacemos es volver a sacar las cifras mayor-menor-mepate...de los cálculos del empate
                int dif1 = this.puntosAnotados - this.puntosRecibidos;
                int dif2 = equipo.puntosAnotados - equipo.puntosRecibidos;

                if(dif1 > dif2)
                {
                    return 1;
                }
                else
                {
                    if(dif1 < dif2)
                    {
                        return -1;
                    }
                    else
                    {
                        return 0;
                    }
                }
            }
        }

    }

}
