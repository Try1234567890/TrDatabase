package me.tr.trDatabase;

import me.tr.trDatabase.database.query.additions.*;
import me.tr.trDatabase.database.query.list.SchemaBuilder;
import me.tr.trDatabase.database.query.list.select.SelectBuilder;
import me.tr.trDatabase.database.types.MariaDB;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class TrDatabaseTest {


    public static void main(String[] args) {
        new TrDatabase();
        MariaDB db = new MariaDB("localhost", 3306, "database", "root", "");
        try {
            db.connect();
            //String json = "{\n" +
            //        "  \"Pacco\": {\n" +
            //        "    \"Dim\": \"Medio\",\n" +
            //        "    \"Type\": \"Plastica\",\n" +
            //        "    \"Tracciamento\": {\n" +
            //        "      \"Corriere\": \"ExpressBox\",\n" +
            //        "      \"Numero\": \"XB123456789IT\"\n" +
            //        "    }\n" +
            //        "  },\n" +
            //        "  \"Contenuto\": {\n" +
            //        "    \"Sapone\": {\n" +
            //        "      \"Marca\": \"Hairsss\",\n" +
            //        "      \"Quantita\": 1,\n" +
            //        "      \"Profumazione\": \"Lavanda\"\n" +
            //        "    },\n" +
            //        "    \"Shampoo\": {\n" +
            //        "      \"Marca\": \"ClearMax\",\n" +
            //        "      \"Quantita\": 2,\n" +
            //        "      \"Litri\": 0.5\n" +
            //        "    },\n" +
            //        "    \"Dentifricio\": {\n" +
            //        "      \"Marca\": \"SmilePlus\",\n" +
            //        "      \"Quantita\": 3,\n" +
            //        "      \"Fluoro\": true\n" +
            //        "    },\n" +
            //        "    \"Campioni\": [\n" +
            //        "      {\n" +
            //        "        \"Tipo\": \"Crema mani\",\n" +
            //        "        \"Marca\": \"SoftTouch\"\n" +
            //        "      },\n" +
            //        "      {\n" +
            //        "        \"Tipo\": \"Balsamo\",\n" +
            //        "        \"Marca\": \"Hairsss\"\n" +
            //        "      }\n" +
            //        "    ]\n" +
            //        "  },\n" +
            //        "  \"Destinatario\": {\n" +
            //        "    \"Nome\": \"Mario Rossi\",\n" +
            //        "    \"Indirizzo\": {\n" +
            //        "      \"Via\": \"Via dei Fiori, 12\",\n" +
            //        "      \"Città\": \"Roma\",\n" +
            //        "      \"CAP\": \"00100\"\n" +
            //        "    },\n" +
            //        "    \"Telefono\": \"+39 345 6789012\"\n" +
            //        "  }\n" +
            //        "}";
//
            //for (int i = 0; i < 10; i++) {
            //    String id = UUID.randomUUID().toString();
            //    final String[] NOMI = {
            //            "Mario", "Luigi", "Peach", "Bowser", "Toad", "Yoshi", "Daisy", "Rosalina",
            //            "Ar", "El", "Gal", "Thar", "Lor", "Zan", "Mir", "Val",
            //            "ion", "eth", "dor", "iel", "as", "mir", "an", "or"
            //    };
            //    new InsertBuilder(db.getConnection())
            //            .table("users")
            //            .columns("ID", "Name", "Surname", "Age")
            //            .values(id, NOMI[new Random().nextInt(NOMI.length)], NOMI[new Random().nextInt(NOMI.length)], new Random().nextInt(NOMI.length))
            //            .execute();
            //    new InsertBuilder(db.getConnection())
            //            .table("ordini")
            //            .columns("ID", "UserID", "Content")
            //            .values(UUID.randomUUID().toString(), id, json)
            //            .execute();
            //}


            List<Map<String, Object>> result = new SelectBuilder(db.getConnection())
                    .columns("users.ID", "users.Name", "users.Surname", "users.Age", "COUNT(ordini.ID) as NumeroOrdini")
                    .table("users")
                    .distinct(true)
                    .join(new Join("ordini", "users.ID = ordini.UserID", Join.JoinType.INNER))
                    .where("users.Age >= ?", 18)
                    .groupBy(new GroupBy("users.ID"))
                    .having(new Having("COUNT(ordini.ID) >= 1"))
                    .orderBy(new OrderBy("users.name", OrderBy.OrderByType.DESC), new OrderBy("users.age", OrderBy.OrderByType.ASC))
                    .limit(new Limit(10))
                    .execute()
                    .select();
            new SchemaBuilder(db.getConnection(),
                    """
                            create table if not exists contacts(
                                id int auto_increment,
                                first_name varchar(50) not null,
                                last_name varchar(50) not null,
                                full_name varchar(101) 
                                    as (concat(first_name, ' ', last_name)) virtual,
                                phone varchar(100),
                                contact_group varchar(50) default 'General',
                                primary key(id)
                            );
                            """).execute();
            for (Map<String, Object> row : result) {
                System.out.println("------------------------------------");
                for (Map.Entry<String, Object> entry : row.entrySet()) {
                    System.out.println(entry.getKey() + ": " + entry.getValue());
                }
            }
            System.out.println("------------------------------------");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
