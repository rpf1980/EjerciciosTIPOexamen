using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace LPICTesterPro
{
    class Pregunta
    {
        public string Texto;
        public string[] Respuestas;
        public int Correcta;
    }

    class Test
    {
        List<Pregunta> lista_preguntas;

        /// <summary>
        /// Inicializa un nuevo test
        /// </summary>
        public Test()
        {
            // Aquí es donde tenéis que rellenar
        }

        /// <summary>
        /// Agrega una nueva pregunta introducida manualmente
        /// </summary>
        /// <param name="pregunta">El texto de la pregunta</param>
        /// <param name="respuesta1">El texto de la 1ª respuesta</param>
        /// <param name="respuesta2">El texto de la 2ª respuesta</param>
        /// <param name="respuesta3">El texto de la 3ª respuesta</param>
        /// <param name="respuesta4">El texto de la 4ª respuesta</param>
        /// <param name="respuesta_correcta">El número de la respuesta correcta</param>
        public void AgregaPregunta(string pregunta, string respuesta1, string respuesta2, 
                                   string respuesta3, string respuesta4, int respuesta_correcta) 
        {
            // Aquí es donde tenéis que rellenar
        }

        /// <summary>
        /// Lee un montón de preguntas desde un fichero. El fichero tendrá que tener el formato correcto
        /// </summary>
        /// <param name="nombre_fichero">El nombre del fichero de preguntas en formato Aiken</param>
        public void LeeFichero(string nombre_fichero)
        {
            // Aquí es donde tenéis que rellenar
        }

        /// <summary>
        /// Escoge una pregunta aleatoria de la lista de preguntas y la elimina para que no vuelva a salir.
        /// También le pide al usuario que la responda y tiene cuidado de no provocar excepciones si el usuario no responde adecuadamente.
        /// </summary>
        /// <returns>Devuelve true si el usuario ha acertado la pregunta y false si la ha fallado</returns>
        public bool PreguntaAleatoria()
        {
            // Aquí es donde tenéis que rellenar (podéis usar Console.WriteLine para escribir la pregunta
            // y Console.ReadLine para leer la respuesta)

            return false; // Suponemos que falláis por defecto, jeje
        }
    }
}
