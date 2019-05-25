package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        Test test = new Test();


        test.LeeFichero("lpic.txt");
        System.out.println(test.PreguntaAleatoria());
    }
}
