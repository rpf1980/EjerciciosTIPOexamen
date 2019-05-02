package com.company;

public class Main
{

    public static void main(String[] args)
    {
        LigaBaloncesto lb = new LigaBaloncesto();

        lb.cargarLiga("liga.csv");

        lb.guardarLiga("LIGAguardada.csv");

    }
}

