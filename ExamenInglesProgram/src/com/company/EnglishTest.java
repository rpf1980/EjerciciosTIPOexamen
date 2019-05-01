package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class EnglishTest
{
    //Atributos
    private List<String> listaFrases = new ArrayList<>();
    private List<String> listaPalabras = new ArrayList<>();

    //Constructores
    public EnglishTest(String fichero)
    {
        int i;
        String linea;
        String palabra;
        String[] arrayPalabras;
        List<String> listaPrimeraEnMayuscula = new ArrayList<>();

        try
        {
            FileReader fr = new FileReader(fichero);
            BufferedReader br = new BufferedReader(fr);

            linea = br.readLine();
            while(linea != null)
            {
                this.getListaFrases().add(linea); //Guarda cada frase del fichero
                arrayPalabras = linea.split(" "); //Trocea las frases por palabras

                for(i = 0; i < arrayPalabras.length; i++)
                {
                    this.listaPalabras.add(arrayPalabras[i]); //Lista de palabras del fichero
                }
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

    //Constructor que nos sierve para obtener una lista con todas las frases (listaFrases)
    public EnglishTest(List<String> lista)
    {
        lista = this.getListaFrases();
    }

    //Métodos
    public void pregunta()
    {
        //Elegimos una frase al azar de la listaFrases
        Random rd = new Random();
        int i,j,k;
        int rdIndex, rdIndex2;
        String palabraOculta = "";
        String[] packPalabrasOpcion = new String[5];
        String elementoRandom = "";
        String[] arrayFrase;
        String[] array7Cadenas = new String[7];
        List<String> listaFrasesAuxiliar = getListaFrases();

        //Recorremos la lista para obtener frases aleatorias de la misma
        for(i = 0; i < listaFrasesAuxiliar.size(); i++)
        {
            rdIndex = rd.nextInt(listaFrasesAuxiliar.size()); //Guarda la posición que el random elige de la lista
            elementoRandom = listaFrasesAuxiliar.get(rdIndex); //Guarda la frase aleatoria que se ubica en el índice generado por el random
        }

        //Elegimos palabra de la frase aleatoria obtenida. Será la que ocultemos para el test-usuario
        arrayFrase = elementoRandom.split(" ");
        for(i = 0; i < arrayFrase.length; i++)
        {
            rdIndex2 = rd.nextInt(arrayFrase.length);
            palabraOculta = arrayFrase[rdIndex2]; //Esta será la PALABRA OCULTA
        }

        //Necesitamos elegir 4 palabras más al azar (sumarían 5 en total)
        packPalabrasOpcion[0] = palabraOculta; //Guardamos la palabra oculta en la primera posición del array que contiene el pack de 5 palabras para el test

        for(i = 0; i < this.listaPalabras.size(); i++)
        {
            for(j = 1; j < packPalabrasOpcion.length; j++)
            {
                rdIndex = rd.nextInt(this.listaPalabras.size());
                String stringRandom = this.listaPalabras.get(rdIndex);

                if(!stringRandom.equals(palabraOculta))
                {
                    packPalabrasOpcion[j] = stringRandom;
                }

                if(packPalabrasOpcion.length == 5)
                {
                    i = this.listaPalabras.size();
                }
            }
        }

        //Reemplazamos palabra oculta por guiones en nuestra frase
        String guionesBajos = replaceGuionesBajos(palabraOculta);
        elementoRandom = elementoRandom.replaceFirst(palabraOculta, guionesBajos);

        array7Cadenas[0] = packPalabrasOpcion[0]; //Aquí tenemos la palabra oculta
        array7Cadenas[1] = elementoRandom; //Aquí tenemos la frase con la palabraOculta sustituida por guiones bajos
        //Recorremos el pack de palabras para el test
        j = 2;
        for(i = 0; i < packPalabrasOpcion.length; i++) //Empezamos en la pos 1 porque la 0 contiene la palabra oculta y no quiero machacarla
        {
            array7Cadenas[j] = packPalabrasOpcion[i];
            j++;
        }

        //Mostramos impresión final a partir de la posición 1 del array7Cadenas
        System.out.print(array7Cadenas[1]);
        formatoBonito5palabras(packPalabrasOpcion);

    }

    public List<String> primerasEnMayuscula(List<String> lista) //Aquí tenemos que pasar this.listaFrases en el main
    {
        List<String> lista2 = new ArrayList<>();
        String frase = "";
        String[] array;
        int i;

        for(i = 0; i < lista.size(); i++)
        {
            frase = getListaFrases().get(i);
            array = frase.split(" "); //Aquí ya tengo un array con las palabras de las frases

            lista2.add(array[0]); //Guarda cada primera palabra de cada frase (palabras que empiezan en mayúsculas)
        }
        return lista2; //Recuerda: el tamaño de esta lista que genera es igual al tamaño de la listaFrases (para luego poder iterar sobre las mismas)
    }

    public String replaceGuionesBajos(String cadena)
    {
        String guiones = "";
        int tamaCadena = cadena.length();

        for(int i = 0; i < tamaCadena; i++)
        {
            guiones += "_";
        }
        return guiones;
    }

    public String[] BorraDeArray(String[] a, int posicion)
    {
        String[] b = new String[a.length - 1];
        int i;
        for (i = 0; i < posicion; i++)  //[3,4,6,7,8,9,7]
        {
            b[i] = a[i];
        }
        for (i = posicion + 1; i < a.length; i++)
        {
            b[i - 1] = a[i];
        }
        return b;
    }

    public String[] Elimina1ElementoArray(String[] a, int valor)
    {
        int i;
        for (i = 0; i < a.length; i++)
        {
            if (a[i].equals(valor))
            {
                a = BorraDeArray(a, i);
                i = a.length;
            }
        }
        return a;
    }

    public static void formatoBonito5palabras(String[] arrayString)
    {
        String resultado = "";

        int i;
        System.out.print("(");
        for(i = 0; i < arrayString.length-1; i++)
        {
            System.out.print(resultado = arrayString[i] + " /");
        }
        System.out.println(arrayString[arrayString.length-1] + ")");

    }

    public List<String> getListaFrases()
    {
        return listaFrases;
    }
}
