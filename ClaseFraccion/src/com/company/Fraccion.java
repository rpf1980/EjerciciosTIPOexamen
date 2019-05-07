package com.company;

public class Fraccion
{
    //Atributos
    private int numerador;
    private int denominador;

    //Constructores
    public Fraccion(int numerador, int denominador)
    {
        this.numerador = numerador;
        this.denominador = denominador;
    }

    public Fraccion(int n)
    {
        this.numerador = n;
        this.denominador = 1;
    }

    public Fraccion(double n)
    {
        this.numerador = (int)n;
        this.denominador = 100;
    }
}
