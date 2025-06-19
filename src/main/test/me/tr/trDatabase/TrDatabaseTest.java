package me.tr.trDatabase;


import me.tr.trDatabase.query.params.functions.control.Case;
import me.tr.trDatabase.query.params.set.Set;
import me.tr.trDatabase.query.params.where.Condition;

public class TrDatabaseTest {
    public static void main(String[] args) {
        //try {
        //    Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "root", "");

        Set set = new Set()
                .variables("categoria_cliente", "sconto_applicabile")
                .values(new Case()
                                .conditions(Condition.greaterThan("fatturato_annuo", 100000),
                                        Condition.greaterThan("fatturato_annuo", 50000),
                                        Condition.greaterThan("fatturato_annuo", 10000))
                                .then("PREMIUM", "GOLD", "SILVER")
                                .otherwise("STANDARD"),
                        new Case()
                                .conditions(Condition.equals("categoria_cliente", "PREMIUM"),
                                        Condition.equals("catergoria_cliente", "GOLD"),
                                        Condition.equals("catergoria_cliente", "SILVER"))
                                .then(15.0, 10.0, 5.0)
                                .otherwise(0.0));
        System.out.println(set.toSql());
            /*
            CASE
                WHEN fatturato_annuo > 100000 THEN 'PREMIUM'
                WHEN fatturato_annuo > 50000 THEN 'GOLD'
                WHEN fatturato_annuo > 10000 THEN 'SILVER'
                ELSE 'STANDARD'
            END

            try (PreparedStatement pstmt = connection.prepareStatement("")) {
                //for (int i = 0; i < countQuestionMarks(query); i++) {
                //    pstmt.setObject(i + 1, insert.parameters().get(i));
                //}
                pstmt.executeUpdate();
            /*
            // Imposta i parametri se presenti
            for (int i = 0; i < countQuestionMarks(query); i++) {
                pstmt.setObject(i + 1, insert.parameters().get(i));
            }

            // Esegui la query e ottieni i risultati
            try (ResultSet rs = pstmt.executeQuery()) {
                // Ottieni i metadati per le informazioni sulle colonne
                ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount();

                // Calcola la larghezza ottimale per ogni colonna
                int[] columnWidths = new int[columnCount + 1]; // +1 perché iniziamo da indice 1
                String[] columnNames = new String[columnCount + 1];

                // Inizializza con la lunghezza dei nomi delle colonne
                for (int i = 1; i <= columnCount; i++) {
                    columnNames[i] = metaData.getColumnName(i);
                    columnWidths[i] = Math.max(columnNames[i].length(), 8); // minimo 8 caratteri
                }

                // Raccogli tutti i dati per calcolare le larghezze
                List<List<String>> allRows = new ArrayList<>();
                while (rs.next()) {
                    List<String> row = new ArrayList<>();
                    for (int i = 1; i <= columnCount; i++) {
                        Object value = rs.getObject(i);
                        String displayValue = (value != null) ? value.toString() : "NULL";
                        row.add(displayValue);
                        // Aggiorna la larghezza massima per questa colonna
                        columnWidths[i] = Math.max(columnWidths[i], displayValue.length());
                    }
                    allRows.add(row);
                }

                // Limita la larghezza massima delle colonne a 30 caratteri
                for (int i = 1; i <= columnCount; i++) {
                    columnWidths[i] = Math.min(columnWidths[i], 30);
                }

                // Calcola la larghezza totale per i separatori
                int totalWidth = 0;
                for (int i = 1; i <= columnCount; i++) {
                    totalWidth += columnWidths[i] + 3; // +3 per " | "
                }
                totalWidth -= 3; // rimuovi l'ultimo " | "

                // Stampa l'intestazione
                System.out.println("=".repeat(totalWidth));
                System.out.println("RISULTATI DELLA QUERY:");
                System.out.println("=".repeat(totalWidth));

                // Stampa i nomi delle colonne con allineamento dinamico
                for (int i = 1; i <= columnCount; i++) {
                    String format = "%-" + columnWidths[i] + "s";
                    System.out.printf(format, truncateString(columnNames[i], columnWidths[i]));
                    if (i < columnCount) System.out.print(" | ");
                }
                System.out.println();
                System.out.println("-".repeat(totalWidth));

                // Stampa i dati con allineamento dinamico
                int rowCount = 0;
                for (List<String> row : allRows) {
                    rowCount++;
                    for (int i = 0; i < columnCount; i++) {
                        String format = "%-" + columnWidths[i + 1] + "s";
                        System.out.printf(format, truncateString(row.get(i), columnWidths[i + 1]));
                        if (i < columnCount - 1) System.out.print(" | ");
                    }
                    System.out.println();
                }

                System.out.println("-".repeat(totalWidth));
                System.out.println("Totale righe trovate: " + rowCount);
                System.out.println("=".repeat(totalWidth));

                // Se non ci sono risultati
                if (rowCount == 0) {
                    System.out.println("Nessun risultato trovato per la query.");
                }
            }
            }

            // Chiudi la connessione
            connection.close();

        } catch (SQLException e) {
            System.err.println("Errore SQL: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Errore generale: " + e.getMessage());
            e.printStackTrace();

        }
    */
    }

    public static int countQuestionMarks(String str) {
        int amount = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '?') {
                amount++;
            }
        }
        return amount;
    }

    // Metodo helper per troncare stringhe troppo lunghe
    private static String truncateString(String str, int maxLength) {
        if (str.length() <= maxLength) {
            return str;
        }
        return str.substring(0, maxLength - 3) + "...";
    }
}