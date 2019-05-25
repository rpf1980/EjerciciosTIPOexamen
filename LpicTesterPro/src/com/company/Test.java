package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

class Test
{
    List<Pregunta> lista_preguntas;
    List<String> listaDeRespuestas = new ArrayList<>();
    public int contador = 0;

    //Constructores
    public Test() //Inicializa un nuevo test
    {
        this.lista_preguntas = new ArrayList<Pregunta>();
    }

    //Métodos

    //Agrega una nueva pregunta introducida manualmente
    //respuesta1 --> El texto de la 1ª respuesta
    //respuesta2 --> El texto de la 2ª respuesta
    //respuesta3 --> El texto de la 3ª respuesta
    //respuesta4 --> El texto de la 4ª respuesta
    public void AgregaPregunta(String pregunta, String respuesta1, String respuesta2,
            String respuesta3, String respuesta4, int respuesta_correcta
    )
    {
        // Aquí es donde tenéis que rellenar
    }

    //Lee un montón de preguntas desde un fichero. El fichero tendrá que tener el formato correcto
    public void LeeFichero(String nombre_fichero)
    {
        //El nombre del fichero de preguntas en formato Aiken
        // Aquí es donde tenéis que rellenar

        int i;
        String linea;
        int numeroDePegruntasEnFichero = cuentaNumeroPreguntasFichero(nombre_fichero);
        String pregunta, respuesta1, respuesta2, respuesta3, respuesta4, respuesta_correcta;

        try
        {
            FileReader fr = new FileReader(nombre_fichero);
            BufferedReader br = new BufferedReader(fr);

            for (i = 0; i < numeroDePegruntasEnFichero; i++)
            {
                pregunta = br.readLine();
                respuesta1 = br.readLine();
                respuesta2 = br.readLine();
                respuesta3 = br.readLine();
                respuesta4 = br.readLine();
                respuesta_correcta = br.readLine();
                int pos = respuesta_correcta.indexOf(' ');
                String res = respuesta_correcta.substring(pos + 1);
                listaDeRespuestas.add(res);

                Pregunta p = new Pregunta();
                p.texto = pregunta;
                p.getRespuestas()[0] = respuesta1;
                p.getRespuestas()[1] = respuesta2;
                p.getRespuestas()[2] = respuesta3;
                p.getRespuestas()[3] = respuesta4;

                this.lista_preguntas.add(p);
                br.readLine();
            }

            this.lista_preguntas.toString();

            br.close();
            fr.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    //Escoge una pregunta aleatoria de la lista de preguntas y la elimina para que no vuelva a salir.
    //También le pide al usuario que la responda y tiene cuidado de no provocar excepciones si el usuario no responde adecuadamente.
    public boolean PreguntaAleatoria()
    {
        // Aquí es donde tenéis que rellenar (podéis usar System.out.println para escribir la pregunta
        // y Scanner para leer la respuesta)

        int contadorParamosBucle = 0;
        Pregunta p;
        Scanner sc = new Scanner(System.in);
        String respuestaUsuario = "";
        Random rd = new Random();
        int pos = -1;
        p = new Pregunta();

        while (contadorParamosBucle < 2)
        {

            pos = rd.nextInt(this.lista_preguntas.size());
            p = this.lista_preguntas.get(pos);

            System.out.println(p.texto);
            System.out.println(p.respuestas[0]);
            System.out.println(p.respuestas[1]);
            System.out.println(p.respuestas[2]);
            System.out.println(p.respuestas[3]);
            System.out.println();

            System.out.print("Respuesta: ");
            respuestaUsuario = sc.nextLine().toUpperCase();
            this.lista_preguntas.remove(pos);

            if (respuestaUsuario.equals(listaDeRespuestas.get(pos)))
            {
                contador++;
            }
            contadorParamosBucle++;
        }

        p.setCorrecta(contador);
        System.out.println("Respuestas acertadas: " + contador);
        return false; // Suponemos que falláis por defecto, jeje
    }

    //Métodos aparte
    public int cuentaNumeroPreguntasFichero(String fichero)
    {
        String linea;
        String[] trozos;
        int nPreguntas = 0;

        try
        {
            FileReader fr = new FileReader(fichero);
            BufferedReader br = new BufferedReader(fr);

            linea = br.readLine();
            while (linea != null)
            {

                if (linea.isEmpty())
                {
                    nPreguntas++;
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

        return nPreguntas + 1;
    }

}

