using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Correccion_Mayo_Ejercicio_4
{
    class Program
    {
        static int[,] TablaMultiplicar()
        {
            int i, j;
            int[,] array = new int[11, 11];

            for (i = 1; i <= 10; i++)
            {
                array[i, 0] = i;
                array[0, i] = i;
            }

            for (i = 1; i <= 10; i++)
            {
                for (j = 1; j <= 10; j++)
                {
                    array[i, j] = i * j;
                }
            }

            return array;
        }

        static void EscribeBonito(int[,] array)
        {
            // Primero: buscar lo que mas ocupa del array
            int i, j;
            int max = 0;
            int longitud;

            for (i = 0; i < array.GetLength(0); i++)
            {
                for (j = 0; j < array.GetLength(1); j++)
                {
                    longitud = array[i, j].ToString().Length;

                    if (longitud > max)
                    {
                        max = longitud;
                    }                    
                }
            }

            max = max + 1;

            // Segundo: imprimimos

            for (i = 0; i < array.GetLength(0); i++)
            {
                for (j = 0; j < array.GetLength(1); j++)
                {
                    Console.Write(array[i, j].ToString().PadLeft(max));
                }
                Console.WriteLine();
            }
        }
        
        static void Main(string[] args)
        {
            EscribeBonito(TablaMultiplicar());

            Console.ReadKey();
        }
    }
}
