package Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ScontoUtenteDAO {

    //Dato l'id di un utente, restituisce tutti gli sconti utilizzati da egli.
    public List<String> doRetrieveCodiceScontoByUser(int id_utente){
        List<String> listaSconti = new ArrayList<>();

        try(Connection connection = Model.ConPool.getConnection()){
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT codice_sconto FROM sconto_utente WHERE id_utente = ?;");
            preparedStatement.setInt(1, id_utente);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                listaSconti.add(resultSet.getString("codice_sconto"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaSconti;
    }

    //Dato un codice sconto, restuisce il numero di utenti che hanno usufruito di quello sconto.
    public List<Integer> doRetrieveUserByCodiceSconto(String codice_sconto){
        List<Integer> listaSconti = new ArrayList<>();

        try(Connection connection = Model.ConPool.getConnection()){
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT id_utente FROM sconto_utente WHERE codice_sconto = ?;");
            preparedStatement.setString(1, codice_sconto);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                listaSconti.add(resultSet.getInt("id_utente"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaSconti;
    }

    //Dato l'id di un utente e un codice sconto, li memorizza nel database.
    public void doSave(int id_utente, String codice_sconto){
        try (Connection connection = Model.ConPool.getConnection()) {
            PreparedStatement preparedStatements =
                    connection.prepareStatement("insert into sconto_utente(codice_sconto, id_utente) " +
                            "VALUES(?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatements.setString(1, codice_sconto);
            preparedStatements.setInt(2, id_utente);

            if (preparedStatements.executeUpdate() != 1)
                throw new RuntimeException("INSERT Error");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
