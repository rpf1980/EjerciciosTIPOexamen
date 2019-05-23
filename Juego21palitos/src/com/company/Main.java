package com.company;

import java.util.*;

public class Main
{

    //Métodos aparte
    public static String listaPalitos(int n)
    {
        String str = "";
        for(int i = 0; i < n; i++)
        {
            str += "| ";
        }
        return str;
    }

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        boolean numeroMalo = true;
        int nPalitosQuita = 0;
        int numeroPalitos = 0;
        int turno = 1;

        Random rd = new Random();

        System.out.println("¿Cuántos palitos tendrá el juego?");

        while(numeroMalo || numeroPalitos <= 0)
        {
            try
            {
                numeroPalitos = sc.nextInt();
                if(numeroPalitos > 0)
                {
                    numeroMalo = false;
                }
                else
                {
                    System.out.println("No puede ser nº negativo");
                }
            }
            catch (Exception e)
            {
                numeroMalo = true;
                sc.next(); //Leemos lo que tengamos y vaciamos el escaner porque hay otra cosa que no es un número
                System.out.println("Error en la entrada, introduce un entero");
            }
        }

        System.out.println("Empezamos juego con "+numeroPalitos+" palitos: ");
        System.out.println(listaPalitos(numeroPalitos));
        System.out.println();

        while(numeroPalitos != 0)
        {
            if(turno == 1)
            {
                System.out.println("¿Cuántos quitas?");

                numeroMalo = true;
                while(numeroMalo || nPalitosQuita > numeroPalitos) //Se da la condición cuando el nº es malo
                {
                    try
                    {
                        nPalitosQuita = sc.nextInt();
                        if(nPalitosQuita > numeroPalitos || (nPalitosQuita > 3 || nPalitosQuita <= 0))
                        {
                            numeroMalo = true;
                            System.out.println("El número no es válido");
                        }
                        else
                        {
                            numeroMalo = false;
                        }
                    }
                    catch (Exception e)
                    {
                        numeroMalo = true;
                        System.out.println("Error en la entrada, introduce un entero");
                        sc.next();
                    }
                }

                numeroPalitos -= nPalitosQuita;

                System.out.println("Te quedan "+numeroPalitos+" palitos: ");
                System.out.println(listaPalitos(numeroPalitos));
                System.out.println();

                turno = 2;
            }
            else
            {
                //Juega la máquina

                if(numeroPalitos <= 4)
                {
                    nPalitosQuita = numeroPalitos-1;
                }
                else
                {
                    nPalitosQuita = rd.nextInt(3)+1;
                }
                System.out.println("La máquina quita "+nPalitosQuita+" palitos.");
                numeroPalitos -= nPalitosQuita;
                System.out.println("Te quedan "+numeroPalitos+" palitos: ");
                System.out.println(listaPalitos(numeroPalitos));
                System.out.println();

                turno = 1;

            }

        }

        //Anunciamos ganador
        if(turno == 1)
        {
            System.out.println("GANA JUGADOR");
        }
        else
        {
            System.out.println("GANA MÁQUINA");
        }
    }

}
