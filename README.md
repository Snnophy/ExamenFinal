📖 Análisis de Frecuencia de Palabras y Letras en un Cuento

Este programa en Java permite analizar un texto (un cuento de ejemplo) para:

Contar palabras totales, palabras únicas y letras distintas.

Construir un HashMap con la frecuencia de cada palabra y letra.

Mostrar las 10 palabras más frecuentes.

Permitir al usuario buscar:

✅ Una letra → muestra cuántas veces aparece.

✅ Una palabra → muestra frecuencia, porcentaje respecto a la más usada y promedio.

✅ Una frase completa → muestra cuántas veces aparece de forma contigua.

Validar entradas del usuario con excepciones personalizadas.

Soporte de colores en consola (ANSI).

🛡️ Manejo de Excepciones

El programa implementa un manejo robusto de errores:

IllegalArgumentException → si el texto base está vacío.

InputMismatchException → si el usuario ingresa datos inválidos (números, símbolos, etc.).

NullPointerException → si se intenta procesar una lista nula.

Exception (global) → captura cualquier error inesperado mostrando un mensaje amigable.

📂 Estructura del Código

Normalización: se eliminan acentos y signos (Normalizer + Regex).

Listas: se almacenan palabras (ArrayList<String>) y letras (ArrayList<Character>).

Mapas:

HashMap<String, Integer> → frecuencia de palabras.

HashMap<Character, Integer> → frecuencia de letras.

Streams: se utilizan para ordenar y mostrar el Top 10 de palabras.

Bucle interactivo: permite al usuario realizar múltiples consultas hasta que decida salir.


📌Resumen: Implemente conocimientos adquiridos en las clases como el tema de los ArrayList, el uso de programación funcional para hacer más eficaz el codigo a la hora de trabajar y entregar
  Otra cosa seria el tema de las palabras reservadas en java, ya que todas nos las conocia y muchas me toca investigar para poder implementar las en el proyecto, pero en general algo muy practico para mejorar más la logica de programación
  
