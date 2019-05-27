package com.company;

import java.security.InvalidParameterException;

public class Libro
{
    //Atributos
    private String isbn;
    private String titulo;
    private String autor;
    private int nPaginas;
    private boolean leido = true;

    //propiedades
    public String getIsbn() {
        return isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public int getnPaginas() {
        return nPaginas;
    }

    public boolean isLeido() {
        return leido;
    }

    public void setLeido(boolean leido) {
        this.leido = leido;
    }

    //Constructor
    public Libro (String isbn, String titulo, String autor, int nPaginas)
    {
        if (isbn.equals("")||titulo.equals("")||autor.equals("")||nPaginas<0)
        {
            throw new InvalidParameterException("Los parámetros nombre, titulo, autor no puede estar vacío y el número de páginas debe ser superior a 0");
        }
        else
        {
            this.isbn = isbn;
            this.titulo = titulo;
            this.autor = autor;
            this.nPaginas = nPaginas;
            this.setLeido(false);
        }
    }

    //Metodo
    public String toString ()
    {
        String datos;
        if (this.leido)
        {
            datos = this.titulo + " (" + this.autor + ") [LEÍDO]";
        }
        else
        {
            datos = this.titulo + " (" + this.autor + ")";
        }
        return datos;
    }

}
