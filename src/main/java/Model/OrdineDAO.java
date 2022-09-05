package Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrdineDAO {

    //Restituisce tutti gli ordini memorizzati nel database.
    public List<Ordine> doRetrieveAll(){
        List<Ordine> listaOrdini = new ArrayList<Ordine>();
        Ordine ordine = new Ordine();

        try(Connection connection = Model.ConPool.getConnection()){
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM ordine;");

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                ordine.setId_ordine(resultSet.getInt("id_ordine"));
                ordine.setData_ordine(resultSet.getDate("data_ordine"));
                ordine.setVia(resultSet.getString("via"));
                ordine.setNumero_civico(resultSet.getInt("num_civico"));
                ordine.setCap(resultSet.getString("cap"));
                ordine.setImporto(resultSet.getDouble("importo"));
                ordine.setId_utente(resultSet.getInt("id_utente"));
                listaOrdini.add(ordine);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaOrdini;
    }

    //Dato un id, restituisce il corrispondente ordine memorizzato nel database.
    public Ordine doRetrieveOrderById(int idOrdine){
        Ordine ordine = new Ordine();

        try(Connection connection = Model.ConPool.getConnection()){
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * " +
                            "FROM ordine " +
                            "WHERE id_ordine = ?");
            preparedStatement.setInt(1, idOrdine);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                ordine.setId_ordine(idOrdine);
                ordine.setData_ordine(resultSet.getDate("data_ordine"));
                ordine.setVia(resultSet.getString("via"));
                ordine.setNumero_civico(resultSet.getInt("num_civico"));
                ordine.setCap(resultSet.getString("cap"));
                ordine.setImporto(resultSet.getDouble("importo"));
                ordine.setId_utente(resultSet.getInt("id_utente"));
            }
            else
                return null;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ordine;
    }

    //Data una data di inizio e di fine, restituisce tutti gli ordini effettuati in quel periodo.
    public List<Ordine> doRetrieveByDate(Date inizioFiltro, Date fineFiltro){
        List<Ordine> listaOrdini = new ArrayList<Ordine>();

        try(Connection connection = ConPool.getConnection()) {
            PreparedStatement ps =
                    connection.prepareStatement("SELECT * FROM ordine WHERE data_ordine >" + inizioFiltro + "and data_ordine <" + fineFiltro + ";");

            ResultSet resultSet = ps.executeQuery();

            while(resultSet.next()) {
                Ordine ordine = new Ordine();
                ordine.setId_ordine(resultSet.getInt("id_ordine"));
                ordine.setData_ordine(resultSet.getDate("data_ordine"));
                ordine.setVia(resultSet.getString("via"));
                ordine.setNumero_civico(resultSet.getInt("num_civico"));
                ordine.setCap(resultSet.getString("cap"));
                ordine.setImporto(resultSet.getDouble("importo"));
                ordine.setId_utente(resultSet.getInt("id_utente"));
                listaOrdini.add(ordine);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaOrdini;
    }

    //Dato un importo, restituisce tutti gli ordini il cui importo è inferiore a quello passato come parametro.
    public List<Ordine> doRetrieveByAmount(Double importo){
        List<Ordine> listaOrdini = new ArrayList<Ordine>();

        try(Connection connection = ConPool.getConnection()) {
            PreparedStatement ps =
                    connection.prepareStatement("SELECT * FROM ordine WHERE importo <" + importo + ";");

            ResultSet resultSet = ps.executeQuery();

            while(resultSet.next()) {
                Ordine ordine = new Ordine();
                ordine.setId_ordine(resultSet.getInt("id_ordine"));
                ordine.setData_ordine(resultSet.getDate("data_ordine"));
                ordine.setVia(resultSet.getString("via"));
                ordine.setNumero_civico(resultSet.getInt("num_civico"));
                ordine.setCap(resultSet.getString("cap"));
                ordine.setImporto(resultSet.getDouble("importo"));
                ordine.setId_utente(resultSet.getInt("id_utente"));
                listaOrdini.add(ordine);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaOrdini;
    }

    //Dato l'id di un utente, restituisce la lista di tutti gli ordini effettuati da egli.
    public List<Ordine> doRetrieveByUser(int id_utente){
        List<Ordine> listaOrdini = new ArrayList<Ordine>();

        try(Connection connection = ConPool.getConnection()) {
            PreparedStatement ps =
                    connection.prepareStatement("SELECT * FROM ordine WHERE id_utente = ?;");
            ps.setInt(1, id_utente);

            ResultSet resultSet = ps.executeQuery();

            while(resultSet.next()) {
                Ordine ordine = new Ordine();
                ordine.setId_ordine(resultSet.getInt("id_ordine"));
                ordine.setData_ordine(resultSet.getDate("data_ordine"));
                ordine.setVia(resultSet.getString("via"));
                ordine.setNumero_civico(resultSet.getInt("num_civico"));
                ordine.setCap(resultSet.getString("cap"));
                ordine.setImporto(resultSet.getDouble("importo"));
                ordine.setId_utente(resultSet.getInt("id_utente"));
                listaOrdini.add(ordine);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaOrdini;
    }

    //Dato l'id di un utente, restituisce la somma totale degli ordini effettuati da egli.
    public double doRetrieveOrderAmountByUser(int id_utente){
        double orderAmountUser = 0.0;

        try(Connection connection = ConPool.getConnection()) {
            PreparedStatement ps =
                    connection.prepareStatement("SELECT sum(importo) FROM ordine WHERE id_utente = ? GROUP BY id_utente;");
            ps.setInt(1, id_utente);

            ResultSet resultSet = ps.executeQuery();

            while(resultSet.next()) {
                orderAmountUser = resultSet.getDouble("sum(importo)");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderAmountUser;
    }

    //Restituisce il ricavo totale di tutti gli ordini effettuati.
    public double doRetrieveAllOrderAmount(){
        double orderAmountUser = 0.0;

        try(Connection connection = ConPool.getConnection()) {
            PreparedStatement ps =
                    connection.prepareStatement("SELECT sum(importo) FROM ordine;");

            ResultSet resultSet = ps.executeQuery();

            while(resultSet.next()) {
                orderAmountUser = resultSet.getDouble("sum(importo)");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderAmountUser;
    }

    //Restituisce il numero di ordini effettuati.
    public int doRetrieveAllOrderNumber(){
        int orderNumber = 0;

        try(Connection connection = ConPool.getConnection()) {
            PreparedStatement ps =
                    connection.prepareStatement("SELECT count(id_ordine) FROM ordine;");

            ResultSet resultSet = ps.executeQuery();

            while(resultSet.next()) {
                orderNumber = resultSet.getInt("count(id_ordine)");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderNumber;
    }

    //Dato un ordine, lo memorizza nel database.
    public void doSave(Ordine ordine){
        try (Connection connection = Model.ConPool.getConnection()){
            PreparedStatement ps =
                    connection.prepareStatement("insert into Ordine(data_ordine, via, num_civico, cap, importo, id_utente) " +
                            "VALUES(?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setDate(1, ordine.getData_ordine());
            ps.setString(2, ordine.getVia());
            ps.setInt(3, ordine.getNumero_civico());
            ps.setString(4, ordine.getCap());
            ps.setDouble(5, ordine.getImporto());
            ps.setInt(6, ordine.getId_utente());

            if(ps.executeUpdate() != 1)
                throw new RuntimeException("INSERT Error");

            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()) {
                int id = rs.getInt(1);
                ordine.setId_ordine(id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
