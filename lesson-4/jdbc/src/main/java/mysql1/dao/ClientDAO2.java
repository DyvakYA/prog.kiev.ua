package mysql1.dao;

import mysql1.entity.Client;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Created by Bios on 29.11.2017.
 */
public class ClientDAO2 extends AbstractDAO<Client> implements ClientDAO{
    Connection conn;
    public ClientDAO2(Connection conn, String table) {
        super(conn, table);
        this.conn=conn;
    }

    @Override
    public void init() {
        try {
            Statement st = conn.createStatement();
            try {
                st.execute("DROP TABLE IF EXISTS Clients");
                st.execute("CREATE TABLE Clients (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, name VARCHAR(20) NOT NULL, age INT)");
            } finally {
                st.close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<Client> getAll() throws InstantiationException, IllegalAccessException {
        Class<?> cls = Client.class;
        return super.getAll((Class<Client>) cls);
    }

    @Override
    public Client findById(int id) throws InstantiationException, IllegalAccessException {
        Class<?> cls = Client.class;
        return  super.findById((Class<Client>) cls, id);
    }

    @Override
    public void create(Client client) throws IllegalAccessException, NoSuchFieldException, InstantiationException {
        Class<?> cls = Client.class;
        super.create((Class<Client>) cls, client);
    }

    @Override
    public void update(Client client) throws IllegalAccessException, NoSuchFieldException, InstantiationException {
        Class<?> cls = Client.class;
        super.update((Class<Client>) cls, client, client.getId());
    }

    @Override
    public void delete(long id) throws SQLException {
        super.delete(id);
    }
}
