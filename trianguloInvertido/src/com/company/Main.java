package com.company;

import java.security.InvalidParameterException;

public class Main
{
    public static String repiteCaracter(char c, int veces)
    {
        String cadena = "";

        for(int i = 0; i < veces; i++)
        {
            cadena = cadena + c;
        }

        return cadena;
    }
    public static void trianguloInvertido (int pisos)
    {
        int i, j;
        int asteriscos = (pisos*2)-1;
        int espaciosDelante;
        int espaciosMedio;

        if (pisos>=3&&pisos<=20)
        {
            System.out.print(repiteCaracter('*', asteriscos));
            System.out.println();

            espaciosDelante = 1;
            espaciosMedio = asteriscos-4;

            for (i=1;i<pisos-1;i++)
            {
                System.out.print(repiteCaracter(' ', espaciosDelante));
                System.out.print('*');
                System.out.print(repiteCaracter(' ', espaciosMedio));
                System.out.print('*');
                System.out.println();

                espaciosDelante++;
                espaciosMedio = espaciosMedio-2;
            }
            System.out.print(repiteCaracter(' ', espaciosDelante));
            System.out.print('*');
        }
        else
        {
            throw new InvalidParameterException("El número de pisos debe estar entre 3 y 20");
        }
    }
    public static void main(String[] args)
    {
        trianguloInvertido(17);
    }
}
