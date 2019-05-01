package com.company;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.security.InvalidParameterException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;

public class Main
{

    public static void transfiereFicheroBD(String fichero, String basedatos)
    {
        //BD
        Connection conn;
        Statement st;
        ResultSet rs;

        String linea;
        String nombre, fecha, curso;
        String[] trozos;
        String[] trozosFecha;
        String fechaBien;
        int dia, mes, anio;
        String sql1, sql2;
        int idCurso = -1;

        try
        {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:" + basedatos);
            st = conn.createStatement();

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

                    //Hacemos la consulta para los id de los cursos
                    sql1 = "SELECT id FROM cursos WHERE nombre = '" + curso + "';";
                    rs = st.executeQuery(sql1);

                    if(rs.next())
                    {
                        idCurso = rs.getInt(1);
                    }
                    else
                    {
                        System.out.println("No se puede insertar el alumno '" + nombre + "' porque su curso '" + curso + "' no se encuentra en la base de datos" );
                    }

                    rs.close();

                    if(idCurso != -1)
                    {
                        trozosFecha = fecha.split("/");
                        dia = Integer.valueOf(trozosFecha[0]);
                        mes = Integer.valueOf(trozosFecha[1]);
                        anio = Integer.valueOf(trozosFecha[2]);
                        LocalDate fecha2 = null;
                        boolean bien = true;

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

                            //Ahora que ya está comprobado todo, insertamos datos

                            sql2 = "INSERT INTO alumnos(nombre, fechaNacimiento, idCurso) VALUES ('" + nombre + "', '" + fechaBien + "', " + idCurso + ");";
                            st.executeUpdate(sql2);
                        }
                        else
                        {
                            System.out.println("El alumno '" + nombre + "' no se puede insertar en la base de datos porque la fecha '" + fecha + "' no es correcta");
                        }

                    }

                    linea = br.readLine();
                    rs.close();
                }
            }
            else
            {
                throw new InvalidParameterException("El fichero no es válido");
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
        transfiereFicheroBD("alumnos.txt", "alumnos.db");
        //transfiereFicheroBD("alumnos_cabeceramal.txt", "alumnos.db");
        //transfiereFicheroBD("alumnos_cursomal.txt", "alumnos.db");
        //transfiereFicheroBD("alumnos_fechamal.txt", "alumnos.db");

    }
}
