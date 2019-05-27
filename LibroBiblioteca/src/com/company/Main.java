package com.company;

import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Biblioteca bib = new Biblioteca();
        bib.leerCSV("biblio.csv");

        Scanner sc = new Scanner(System.in);

        System.out.print("Elige una opción: ");
        int opcion = -1;

        while (opcion != 0)
        {
            System.out.println("GESTIÓN DE BIBLIOTECA PERSONAL");
            System.out.println("------------------------------");
            System.out.println();
            System.out.println("1- Listado de libros");
            System.out.println("2- Añadir un libro");
            System.out.println("3- Marcar un libro como leído");
            System.out.println();
            System.out.println("0- Salir (se grabarán las modificaciones)");
            System.out.println();
            System.out.print("Opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion)
            {
                case 1:
                    System.out.println(bib);
                    System.out.println();
                    System.out.println("Pulse una tecla para volver al menú...");
                    break;
                case 2:
                {
                    String isbn;
                    String titulo;
                    String autor;
                    int npaginas;

                    System.out.println("NUEVO LIBRO");
                    System.out.println("-----------");
                    System.out.println();

                    System.out.println("Introduzca el ISBN: ");
                    isbn = sc.nextLine();
                    while ((isbn.isEmpty()))
                    {
                        System.out.println("El ISBN no puede estar en blanco, escríbalo de nuevo: ");
                    }

                    System.out.println("Introduzca el título: ");
                    titulo = sc.nextLine();
                    while (titulo.isEmpty())
                    {
                        System.out.println("El título no puede estar en blanco, escríbalo de nuevo: ");
                    }

                    System.out.println("Introduzca el autor: ");
                    autor = sc.nextLine();
                    while (autor.isEmpty())
                    {
                        System.out.println("El autor no puede estar en blanco, escríbalo de nuevo: ");
                    }

                    System.out.println("Introduzca el número de páginas: ");
                    npaginas = sc.nextInt();
                    while (npaginas <= 0)
                    {
                        System.out.println("El número de páginas debe ser un entero mayor que cero, escríbalo de nuevo: ");
                    }

                    Libro l = new Libro(isbn, titulo, autor, npaginas);
                    if (bib.insertaLibro(l))
                    {
                        System.out.println("Libro insertado con éxito");
                    }
                    else
                    {
                        System.out.println("El libro no se ha podido insertar: ISBN duplicado");
                    }
                    System.out.println();
                    System.out.println("Pulse una tecla para volver al menú...");
                }
                break;
                case 3:
                {
                    String cadena = "";

                    System.out.println("Introduzca el título o parte del título a marcar como leído");
                    cadena = sc.nextLine();

                    while (cadena.length() == 0)
                    {
                        System.out.println("No puede usar una cadena en blanco. Escriba el título:");
                    }

                    int marcados = bib.marcarLeidoTitulo(cadena);

                    if (marcados > 1)
                    {
                        System.out.println("Marcados " + marcados + " libros como leídos");
                    }
                    else
                    {
                        if (marcados == 1)
                        {
                            System.out.println("El libro se ha marcado como leído");
                        }
                        else
                        {
                            System.out.println("No se ha encontrado ningún libro con ese título");
                        }
                    }

                }
                break;
            }

        }

        bib.guardarCSV("biblio.csv");
        System.out.println("¡Hasta pronto!");
    }
}
