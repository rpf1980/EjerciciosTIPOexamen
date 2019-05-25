package com.company;

public class Pregunta
{
    public String texto;
    public String[] respuestas = new String[4];
    private int correcta;

    public String toString(String texto, String[] respuestas)
    {
        String mostramosPregunta = getTexto() + "\n" +
                                   getRespuestas()+ '\n';


        return mostramosPregunta;
    }

    public String getTexto()
    {
        return texto;
    }

    public String[] getRespuestas()
    {
        return respuestas;
    }

    public void setCorrecta(int correcta)
    {
        this.correcta = correcta;
    }

    public int getCorrecta()
    {
        return this.correcta;
    }
}

