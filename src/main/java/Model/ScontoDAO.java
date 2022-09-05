package Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ScontoDAO {

    //Restituisce tutti gli sconti presenti nel database.
    public List<Sconto> doRetrieveAll(){
        List<Sconto> listaScontiDisponibili = new ArrayList<Sconto>();

        try(Connection connection = Model.ConPool.getConnection()){
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM sconto;");

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                Sconto sconto = new Sconto();
                sconto.setCodiceSconto(resultSet.getString("codice_sconto"));
                sconto.setPercentualeSconto(resultSet.getInt("percentuale_sconto"));
                sconto.setDataFineSconto(resultSet.getDate("data_fine_sconto"));
                listaScontiDisponibili.add(sconto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaScontiDisponibili;
    }

    //Restituisce tutti gli sconti non ancora scaduti.
    public List<Sconto> doRetrieveScontiValidi(){
        List<Sconto> listaScontiValidi = new ArrayList<Sconto>();

        try(Connection connection = Model.ConPool.getConnection()){
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM sconto WHERE curdate() > data_fine_sconto;");

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                Sconto sconto = new Sconto();
                sconto.setCodiceSconto(resultSet.getString("codice_sconto"));
                sconto.setPercentualeSconto(resultSet.getInt("percentuale_sconto"));
                sconto.setDataFineSconto(resultSet.getDate("data_fine_sconto"));
                listaScontiValidi.add(sconto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaScontiValidi;
    }

    //Dato l'id, restituisce il corrispondente sconto.
    public Sconto doRetrieveScontoById(String idSconto){
        Sconto sconto = new Sconto();

        try(Connection connection = Model.ConPool.getConnection()){
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * " +
                            "FROM sconto " +
                            "WHERE codice_sconto = ?");
            preparedStatement.setString(1, idSconto);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                sconto.setCodiceSconto(idSconto);
                sconto.setPercentualeSconto(resultSet.getInt("percentuale_sconto"));
                sconto.setDataFineSconto(resultSet.getDate("data_fine_sconto"));
            }

            else
                return null;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sconto;
    }

    //Dato uno sconto, lo memorizza nel database.
    public void doSave(Sconto sconto){
        try (Connection connection = Model.ConPool.getConnection()){
            PreparedStatement ps =
                    connection.prepareStatement("insert into Sconto(codice_sconto, percentuale_sconto, data_fine_sconto) " +
                            "VALUES(?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, sconto.getCodiceSconto());
            ps.setInt(2, sconto.getPercentualeSconto());
            java.sql.Date dataSql = new java.sql.Date(sconto.getDataFineSconto().getTime());
            ps.setDate(3, dataSql);

            if(ps.executeUpdate() != 1)
                throw new RuntimeException("INSERT Error");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Dato l'id, cancella lo sconto corrispondente dal database.
    public void doDeleteSconto(String codice_sconto) throws SQLException {
        try (Connection connection = ConPool.getConnection()) {
            PreparedStatement preparedStatements =
                    connection.prepareStatement("DELETE FROM sconto WHERE codice_sconto = ?;");

            preparedStatements.setString(1, codice_sconto);
            preparedStatements.executeUpdate();
        }
    }
}
