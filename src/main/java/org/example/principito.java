package org.example;

import java.text.Normalizer;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class principito {


    private static final String ROJO = "\u001B[31m";
    private static final String VERDE = "\u001B[32m";
    private static final String AZUL = "\u001B[34m";
    private static final String AMARILLO = "\u001B[33m";
    private static final String RESET = "\u001B[0m";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {

            String texto = "Cuando yo tenía seis años vi en un libro una magnífica lámina.\n" +
                    "Representaba una serpiente boa que se tragaba a una fiera.\n" +
                    "En el libro se decía: \"Las boas tragan a sus presas enteras, sin masticarlas.\n" +
                    "Después ya no pueden moverse y duermen durante los seis meses de su digestión\".\n" +
                    "Reflexioné mucho entonces sobre las aventuras de la selva y, a mi vez, logré\n" +
                    "trazar con un lápiz de colores mi primer dibujo.\n" +
                    "Era una obra maestra que representaba una serpiente boa digiriendo un elefante.\n" +
                    "Mostré mi obra a las personas mayores y les pregunté si mi dibujo les asustaba.\n" +
                    "Me respondieron: \"¿Por qué habría de asustar un sombrero?\".\n" +
                    "Mi dibujo no representaba un sombrero.\n" +
                    "Representaba una serpiente boa que digiere un elefante.\n" +
                    "Es necesario explicar a los adultos muchas cosas, porque nunca comprenden nada por sí\n" +
                    "mismos.";

            if (texto == null || texto.trim().isEmpty()) {
                throw new IllegalArgumentException("El texto del cuento no puede estar vacío.");
            }


            String normalizado = Normalizer.normalize(texto, Normalizer.Form.NFD)
                    .replaceAll("\\p{M}", ""); // elimina tildes

            Pattern patron = Pattern.compile("[a-zA-Z\\s]+");
            Matcher matcher = patron.matcher(normalizado);

            StringBuilder sb = new StringBuilder();
            while (matcher.find()) {
                sb.append(matcher.group()).append(" ");
            }
            String cuento = sb.toString().trim();


            String[] palabras = cuento.split("\\s+");
            ArrayList<String> listaPalabras = new ArrayList<>();
            for (String palabra : palabras) {
                if (!palabra.isEmpty()) {
                    listaPalabras.add(palabra.toLowerCase());
                }
            }

            HashMap<String, Integer> mapaPalabras = new HashMap<>();
            for (String p : listaPalabras) {
                mapaPalabras.put(p, mapaPalabras.getOrDefault(p, 0) + 1);
            }


            ArrayList<Character> listaChars = new ArrayList<>();
            for (char ch : cuento.toCharArray()) {
                if (Character.isLetter(ch)) {
                    listaChars.add(Character.toLowerCase(ch));
                }
            }

            HashMap<Character, Integer> mapaChars = new HashMap<>();
            for (Character c : listaChars) {
                mapaChars.put(c, mapaChars.getOrDefault(c, 0) + 1);
            }

            int maxFrecuencia = 0;
            String palabraMasFrecuente = "";
            int sumaFrecuencias = 0;

            for (Map.Entry<String, Integer> entry : mapaPalabras.entrySet()) {
                int freq = entry.getValue();
                sumaFrecuencias += freq;
                if (freq > maxFrecuencia) {
                    maxFrecuencia = freq;
                    palabraMasFrecuente = entry.getKey();
                }
            }

            double promedioFrecuencias = (mapaPalabras.size() > 0) ?
                    (double) sumaFrecuencias / mapaPalabras.size() : 0;

            System.out.println(AZUL + "\n📊 ESTADÍSTICAS DEL CUENTO:" + RESET);
            System.out.println("Palabras totales: " + listaPalabras.size());
            System.out.println("Palabras únicas: " + mapaPalabras.size());
            System.out.println("Letras distintas: " + mapaChars.size());

            System.out.println(AZUL + "\n🔥 TOP 10 palabras más frecuentes:" + RESET);
            mapaPalabras.entrySet().stream()
                    .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
                    .limit(10)
                    .forEach(e ->
                            System.out.printf("%-12s → %s%d%s veces%n",
                                    e.getKey(),
                                    VERDE, e.getValue(), RESET));

            while (true) {
                try {
                    System.out.print(AMARILLO + "\nIngrese una letra, palabra o frase (o 'salir' para terminar): " + RESET);
                    String busqueda = sc.nextLine();

                    if (busqueda.equalsIgnoreCase("salir")) {
                        break;
                    }

                    if (busqueda == null || busqueda.trim().isEmpty()) {
                        throw new IllegalArgumentException("La búsqueda no puede estar vacía.");
                    }

                    String busquedaNorm = Normalizer.normalize(busqueda, Normalizer.Form.NFD)
                            .replaceAll("\\p{M}", "")
                            .trim();

                    if (!busquedaNorm.matches("[a-zA-Z\\s]+")) {
                        throw new InputMismatchException("Entrada inválida. Escriba solo letras y espacios.");
                    }


                    if (busquedaNorm.length() == 1) {
                        char c = Character.toLowerCase(busquedaNorm.charAt(0));
                        int frecuencia = mapaChars.getOrDefault(c, 0);
                        System.out.printf(VERDE + "La letra '%c' aparece %d veces en el cuento.%n" + RESET, c, frecuencia);
                    }

                    else if (busquedaNorm.contains(" ")) {
                        String frase = busquedaNorm.toLowerCase();
                        int count = 0;
                        for (int i = 0; i <= listaPalabras.size() - frase.split("\\s+").length; i++) {
                            String posible = String.join(" ",
                                    listaPalabras.subList(i, i + frase.split("\\s+").length));
                            if (posible.equals(frase)) {
                                count++;
                            }
                        }
                        System.out.printf(VERDE + "La frase \"%s\" aparece %d veces en el cuento.%n" + RESET,
                                frase, count);
                    }
                    else {
                        String palabraBuscada = busquedaNorm.toLowerCase();
                        int frecuencia = mapaPalabras.getOrDefault(palabraBuscada, 0);
                        System.out.printf(VERDE + "La palabra \"%s\" aparece %d veces en el cuento.%n" + RESET,
                                palabraBuscada, frecuencia);

                        double porcentaje = (maxFrecuencia > 0) ?
                                (frecuencia * 100.0 / maxFrecuencia) : 0;

                        System.out.printf("La palabra más frecuente es \"%s\" (%d apariciones).%n",
                                palabraMasFrecuente, maxFrecuencia);
                        System.out.printf("Tu palabra representa el %.2f%% de la frecuencia de la más usada.%n",
                                porcentaje);

                        System.out.printf("El promedio de frecuencia de todas las palabras es: %.2f apariciones.%n",
                                promedioFrecuencias);
                    }

                } catch (IllegalArgumentException | InputMismatchException e) {
                    System.err.println(ROJO + "Error: " + e.getMessage() + RESET);
                    System.out.println("Inténtelo nuevamente.\n");
                }
            }

        } catch (Exception e) {
            System.err.println(ROJO + "Ha ocurrido un error inesperado: " + e.getMessage() + RESET);
        } finally {
            System.out.println(AZUL + "\nPrograma finalizado." + RESET);
            sc.close();
        }
    }
}
