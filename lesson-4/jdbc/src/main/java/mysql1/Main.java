package mysql1;

import mysql1.dao.ClientDAO2;
import mysql1.entity.Client;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/mydb";
    static final String DB_USER = "root";
    static final String DB_PASSWORD = "root";

    public static void main(String[] args) throws SQLException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        Scanner sc = new Scanner(System.in);

        ConnectionFactory factory = new ConnectionFactory(
                DB_CONNECTION, DB_USER, DB_PASSWORD
        );

        Connection conn = factory.getConnection();
        try {
            ClientDAO2 dao2 = new ClientDAO2(conn, "clients");
            dao2.init();

            while (true) {
                System.out.println("1: add client");
                System.out.println("2: view clients");
                System.out.println("3: delete clients");
                System.out.print("-> ");

                String s = sc.nextLine();
                if (s.equals("1")) {
                    System.out.print("Enter client name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter client age: ");
                    String sAge = sc.nextLine();
                    int age = Integer.parseInt(sAge);
                    Client client = new Client.Builder()
                            .setName(name)
                            .setAge(age)
                            .build();
                    System.out.println(client);
                    dao2.create(client);
                }else if (s.equals("2")) {
                    List<Client> list = dao2.getAll();
                    for (Client client1 : list) {
                        System.out.println(client1);
                    }
                }else if(s.equals("3")){
                    System.out.print("Enter client id for delete: ");
                    String id = sc.nextLine();
                    dao2.delete(Long.parseLong(id));
                } else {
                    return;
                }
            }
        } finally {
            sc.close();
            if (conn != null) conn.close();
        }
    }
}
