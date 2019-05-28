package com.company;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main
{
    //Métodos aparte
    public static String QuitaEspaciosSobrantes(String cadena)
    {
        String copia = cadena;
        int i;
        String c2 = "";

        if(copia.length() <= 1)
        {
            return copia;
        }

        for (i = 0; i < copia.length() - 1; i++)
        {
            if (!(copia.charAt(i) == ' ' && copia.charAt(i + 1) == ' '))
            {
                c2 = c2 + copia.charAt(i);
            }
        }
        c2 = c2 + copia.charAt(copia.length()-1);

        return c2;
    }

    public static String quitaCaracter(String cadena)
    {
        String copia = "";

        for(int i = 0; i < cadena.length(); i++)
        {
            if(cadena.charAt(i) != '.')
            {
                copia += cadena.charAt(i);
            }
        }

        return copia;
    }

    public static String PadLeft(String c, int longitud)
    {
        String cadena = c;
        int cantidadEspacios = longitud - c.length();
        int i;

        for (i = 0; i < cantidadEspacios; i++)
        {
            cadena = " " + cadena;
        }
        return cadena;
    }

    public static String PadRight(String c, int longitud)
    {
        String cadena = c;
        int cantidadEsapcios = longitud - c.length();
        int i;

        for (i = 0; i < cantidadEsapcios; i++)
        {
            cadena = cadena + " ";
        }
        return cadena;
    }

    public static void porcentajeUni(String fichero)
    {
        String linea;
        String textoCompleto = "";
        String[] trozos;
        int sumaTotales = 0;

        List<String> listaLineas = new ArrayList<>();
        List<String> listaSin4PrimerasLineas = new ArrayList<>();
        String[] arrayElementosCarrera = new String[3];
        String[] trozosLineasCadaCarrera;

        List<Integer> contadorHombres = new ArrayList<>();
        List<Integer> contadorMujeres = new ArrayList<>();
        List<Integer> contadorTotales = new ArrayList<>();
        List<String> contadorCarreras = new ArrayList<>();


        try
        {
            FileReader fr = new FileReader(fichero);
            BufferedReader br = new BufferedReader(fr);

            linea = br.readLine();

            while(linea != null)
            {
                if(!linea.isEmpty())
                {
                    linea = linea.replace('-', '0');
                    linea = QuitaEspaciosSobrantes(linea);
                    linea = quitaCaracter(linea);
                    textoCompleto += linea + "\n";

                }
                linea = br.readLine();

            }

            trozos = textoCompleto.split("\n");

            //Array trozos lo convertimos a una lista
            listaLineas = Arrays.asList(trozos);

            //Borramos los 4 primeros elementos de la lista
            listaSin4PrimerasLineas = listaLineas.subList(4, listaLineas.size()-1);

            System.out.println(listaSin4PrimerasLineas);

            //Recorremos listaSin4PrimerasLineas para de cada elemento convertir, obtener y guardar los enteros
            for(int i = 0; i < listaSin4PrimerasLineas.size(); i++)
            {
                String elemento = listaSin4PrimerasLineas.get(i);
                trozosLineasCadaCarrera = elemento.split(" ");

                //System.out.println(Arrays.toString(trozosLineasCadaCarrera));

                int total = Integer.parseInt(trozosLineasCadaCarrera[trozosLineasCadaCarrera.length-1]);   //Parseamos a entero dato de la última columna (Total)
                int mujeres = Integer.parseInt(trozosLineasCadaCarrera[trozosLineasCadaCarrera.length-2]); //Parseamos a entero dato de la penúltima columna (Mujeres)	
                int hombres = Integer.parseInt(trozosLineasCadaCarrera[trozosLineasCadaCarrera.length-3]); //Parseamos a entero dato de la antepenúltima columna (Hombres)
                String carrera = "";
                for(int j = 0; j < trozosLineasCadaCarrera.length-3; j++)
                {
                    carrera += trozosLineasCadaCarrera[j] + " "; //String de la primera columna (Carreras)
                }
              
              	//Metemos los datos parseados en listas aparte
                contadorHombres.add(hombres);
                contadorMujeres.add(mujeres);
                contadorTotales.add(total);
                contadorCarreras.add(carrera);  //Recordamos que carrera no hay que parsear porque siempre es un tipo String

            }

            //Suma totales (columna Total)
            for(int i = 0; i < contadorTotales.size(); i++)
            {
                sumaTotales += contadorTotales.get(i);
            }

            //Imprimimos resultados
            for(int i = 0; i < contadorCarreras.size(); i++)
            {
            	//Calculamos los porcentajes aparte antes de imprimirlos para que nos resulte más fácil leer el código
                int porHombres = (contadorHombres.get(i) * 100) / contadorTotales.get(i);
                int porMujeres = (contadorMujeres.get(i) * 100) / contadorTotales.get(i);
                int porTotales = (contadorTotales.get(i) * 100) / sumaTotales;

                System.out.println(PadRight(contadorCarreras.get(i), 50) + PadRight(String.valueOf(contadorHombres.get(i)), 8) + PadRight(String.valueOf(porHombres)+"%", 8) +
                                    PadRight(String.valueOf(contadorMujeres.get(i)), 8) + PadRight(String.valueOf(porMujeres)+"%", 8) + PadRight(String.valueOf(contadorTotales.get(i)), 8) +
                                        PadLeft(String.valueOf(porTotales), 8)+"%");
            }

            br.close();
            fr.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        porcentajeUni("universidad.prn");
    }
}
