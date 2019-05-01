package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.security.InvalidParameterException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Arrays;

public class Main
{
    public static void transfiereFicheroDB(String fichero, String nombreDB)
    {
        //DB
        Connection conn;
        Statement st;
        ResultSet rs;

        //Variables
        int idCurso;
        String sql1, sql2;
        String nombre, fecha, curso;
        String linea = "";
        String[] trozos = new String[3];

        String[] trozosFecha = new String[3];
        LocalDate fecha2 = null;
        String fechaBien = "";
        boolean bien = true;
        int dia = 0;
        int mes = 0;
        int anio = 0;

        try
        {
            //Conectamos con la BD
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:" + nombreDB);
            st = conn.createStatement();

            //Preparamos para la lectura del fichero de csv
            FileReader fr = new FileReader(fichero);
            BufferedReader br = new BufferedReader(fr);

            linea = br.readLine();

            if(linea.equals("NOMBRE,FECHA DE NACIMIENTO,CURSO"))
            {
                linea = br.readLine();

                while(linea != null)
                {
                    trozos = linea.split(",");

                    nombre = trozos[0];
                    fecha = trozos[1];
                    curso = trozos[2];

                    sql1 = "SELECT id FROM cursos WHERE nombre = '" +curso+ "';";
                    rs = st.executeQuery(sql1);

                    if(rs.next())
                    {
                        idCurso = rs.getInt(1);
                    }
                    else
                    {
                        System.out.println("El alumno " + nombre + " no se puede insertar el la bd porque el curso " + curso + " no existe");
                        idCurso = -1;
                    }

                    rs.close(); //Cerramos el rs

                    //Si el id no nos da un error entramos en el siguiente if
                    if(idCurso != -1)
                    {
                        trozosFecha = fecha.split("/");
                        dia = Integer.valueOf(trozosFecha[0]);
                        mes = Integer.valueOf(trozosFecha[1]);
                        anio = Integer.valueOf(trozosFecha[2]);

                        try
                        {
                            fecha2 = LocalDate.of(anio,mes,dia);
                        }
                        catch (Exception e)
                        {
                            bien = false;
                        }

                        if(bien)
                        {
                            fechaBien = fecha2.toString() + " 00:00:00.000";

                            sql2 = "INSERT INTO alumnos(nombre, fechaNacimiento, idCurso) VALUES ('" + nombre + "', '" + fechaBien + "', " + idCurso + ");";
                            st.executeUpdate(sql2);

                        }
                        else
                        {
                            System.out.println("El alumno '" + nombre + "' no se puede insertar el base de datos porque la fecha '" + fecha + "' no es correcta");
                        }

                        rs.close();
                    }
                    linea = br.readLine();
                }
            }
            else
            {
                throw new InvalidParameterException("Formato del fichero no válido");
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
        transfiereFicheroDB("alumnos.txt", "alumnos.db");
        //transfiereFicheroDB("alumnos_cabeceramal.txt", "alumnos.db");
        //transfiereFicheroDB("alumnos_cursomal.txt", "alumnos.db");
        //transfiereFicheroDB("alumnos_fechamal.txt", "alumnos.db");
        //transfiereFicheroBD("alumnos_fechamal.txt", "alumnos.db");
    }
}

