package com.company;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Main
{

    public static void haceCalor(List<String> localidades, List<Double> temperaturas)
    {
        double suma = 0;
        double media = 0;
        int i;

        for(i = 0; i < temperaturas.size(); i++)
        {
            suma += temperaturas.get(i);
        }
        media = suma / temperaturas.size();

        for(i = 0; i < temperaturas.size(); i++)
        {
            if(temperaturas.get(i) < media)
            {
                localidades.remove(i);
                temperaturas.remove(i);
                i--;
            }
        }

        System.out.println("MEDIA = " + media);
        System.out.println(localidades);
        System.out.println(temperaturas);
    }

    public static void main(String[] args)
    {
        List<String> localidades = new LinkedList<>(Arrays.asList("Jerez","Tariga","Barbate","Utrera","Conil"));
        List<Double> temperaturas = new LinkedList<>(Arrays.asList(18.9, 14.1, 18.6, 12.5,20.3));

        haceCalor(localidades,temperaturas);
    }
}

