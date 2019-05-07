package com.company;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;

public class Main
{
    public static int[][]tablaMultiplicar()
    {
        int i, j;
        int[][]array = new int[11][11];

        for (i = 1; i <= 10; i++)
        {
            array[i][0] =i;
            array[0][i] =i;
        }

        for (i = 1; i <= 10; i++)
        {
            for (j = 1; j <= 10; j++)
            {
                array[i][j] =i * j;
            }
        }
        return array;
    }

    public static void escribeBonito(int[][] array)
    {
        // Primero: buscar lo que mas ocupa del array
        int i, j;
        int max = 0;
        int longAuxiliar;
        int longitud;

        for (i = 0; i < array.length; i++)
        {
            for (j = 0; j < array[i].length; j++)
            {
                longitud = String.valueOf(array[i][j]).length();

                if (longitud > max)
                {
                    max = longitud;
                }
            }
        }

        max = max + 1;

        // Segundo: imprimimos

        for (i = 0; i < array.length; i++)
        {
            for (j = 0; j < array[i].length; j++)
            {
                System.out.print(padLeft(String.valueOf(array[i][j]), max));
            }
            System.out.println();
        }
    }

    public static String padLeft(String cadena, int longitud)
    {
        while(cadena.length() < longitud)
        {
            cadena = " " + cadena;
        }
        return cadena;
    }

    public static void main(String[] args)
    {
        escribeBonito(tablaMultiplicar());
    }
}



