package Model;

import java.sql.*;

public class UtenteDAO {

    //Date email e password, ne restituisce l'utente corrispondente.
    public Utente doRetrieveByEmailPassword(Utente utente) {
        Utente doRetrieve = new Utente();

        try(Connection connection = Model.ConPool.getConnection()){
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * " +
                            "From utente " +
                            "where email= ? AND passwordhash=SHA1(?);");

            preparedStatement.setString(1, utente.getEmail());
            preparedStatement.setString(2, utente.getPassword());

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                doRetrieve.setId(resultSet.getInt("id_utente"));
                doRetrieve.setNome(resultSet.getString("nome"));
                doRetrieve.setCognome(resultSet.getString("cognome"));
                doRetrieve.setEmail(resultSet.getString("email"));
                doRetrieve.setPassword(resultSet.getString("passwordhash"));
                doRetrieve.setAdmin(resultSet.getBoolean("admin"));
            }
            else
                return null;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return doRetrieve;
    }

    //Dato un id, restituisce l'utente corrispondente.
    public Utente doRetrieveById(int id) {
        Utente doRetrieve = new Utente();

        try(Connection connection = Model.ConPool.getConnection()){
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * " +
                            "From utente " +
                            "where id_utente = ?;");

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                doRetrieve.setId(resultSet.getInt("id_utente"));
                doRetrieve.setNome(resultSet.getString("nome"));
                doRetrieve.setCognome(resultSet.getString("cognome"));
                doRetrieve.setEmail(resultSet.getString("email"));
                doRetrieve.setPassword(resultSet.getString("passwordhash"));
                doRetrieve.setAdmin(resultSet.getBoolean("admin"));
            }
            else
                return null;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return doRetrieve;
    }

    //Data un email, restituisce l'utente corrispondente.
    public Utente doRetrieveByEmail(String email) {
        Utente doRetrieve = new Utente();

        try(Connection connection = Model.ConPool.getConnection()){
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * " +
                            "From utente " +
                            "where email = ?;");

            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                doRetrieve.setId(resultSet.getInt("id_utente"));
                doRetrieve.setNome(resultSet.getString("nome"));
                doRetrieve.setCognome(resultSet.getString("cognome"));
                doRetrieve.setEmail(resultSet.getString("email"));
                doRetrieve.setPassword(resultSet.getString("passwordhash"));
                doRetrieve.setAdmin(resultSet.getBoolean("admin"));
            }
            else {
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return doRetrieve;
    }

    //Restituisce il numero di utenti registrati.
    public int doRetrieveUserRegisteredNumber() {
        int userNumber = 0;

        try(Connection connection = Model.ConPool.getConnection()){
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT count(id_utente) " +
                            "FROM utente;");

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                userNumber = resultSet.getInt("count(id_utente)");
            } else {
                return 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userNumber;
    }

    //Dato un utente, lo memorizza all'interno del database.
    public void doSave(Utente utente){
        try (Connection connection = Model.ConPool.getConnection()){
            PreparedStatement ps =
                    connection.prepareStatement("insert into Utente (nome, cognome, email, passwordhash, admin) " +
                            "VALUES(?, ?, ?,SHA1(?),false)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, utente.getNome());
            ps.setString(2, utente.getCognome());
            ps.setString(3, utente.getEmail());
            ps.setString(4, utente.getPassword());

            if(ps.executeUpdate() != 1)
                throw new RuntimeException("INSERT Error");

            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                int id = rs.getInt(1);
                utente.setId(id);
                utente.setAdmin(false);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Dato un utente già presente nel database, gli consente di modificare la password.
    public void doSaveChangePassword(Utente utente){
        try (Connection connection = Model.ConPool.getConnection()){
            PreparedStatement ps =
                    connection.prepareStatement("insert into Utente (id_utente, nome, cognome, email, passwordhash, admin) " +
                            "VALUES(?, ?, ?, ?,SHA1(?),false)", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, utente.getId());
            ps.setString(2, utente.getNome());
            ps.setString(3, utente.getCognome());
            ps.setString(4, utente.getEmail());
            ps.setString(5, utente.getPassword());

            if(ps.executeUpdate() != 1)
                throw new RuntimeException("INSERT Error");

            utente.setAdmin(false);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Dato l'id di un utente, lo elimina dal database.
    public void doDeleteUser(int id_utente) throws SQLException {
        try (Connection connection = ConPool.getConnection()) {
            PreparedStatement preparedStatements =
                    connection.prepareStatement("DELETE FROM utente WHERE id_utente = ?;");

            preparedStatements.setInt(1, id_utente);
            preparedStatements.executeUpdate();
        }
    }
}
