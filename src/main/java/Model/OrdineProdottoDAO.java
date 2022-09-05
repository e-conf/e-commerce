package Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrdineProdottoDAO {

    //Dato l'id di un ordine, restituisce tutti i prodotti compresi in esso.
    public List<Prodotto> doRetrieveProductByOrder(int idOrdine){
        List<Prodotto> listaProdotti = new ArrayList<>();
        Prodotto prodotto = new Prodotto();

        try(Connection connection = Model.ConPool.getConnection()){
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT id_prodotto FROM ordine_prodotto WHERE id_ordine = ?;");
            preparedStatement.setInt(1, idOrdine);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                prodotto.setId_prodotto(resultSet.getString("id_prodotto"));
                prodotto.setNome(resultSet.getString("nome"));
                prodotto.setProvenienza(resultSet.getString("provenienza"));
                prodotto.setDescrizione(resultSet.getString("descrizione"));
                prodotto.setQuantita(resultSet.getInt("quantita"));
                prodotto.setPrezzo(resultSet.getDouble("prezzo"));
                prodotto.setCategoria(resultSet.getString("nomecategoria"));
                prodotto.setFormato(resultSet.getString("formato"));
                prodotto.setImmagine(resultSet.getString("immagine"));
                listaProdotti.add(prodotto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaProdotti;
    }

    //Dato l'id di un ordine, restituisce tutti gli ordini effettuati da egli con tutti i prodotti acquistati.
    public ArrayList<OrdineProdotto> doRetrieveOrdineProdottoByIdOrder(int idOrdine){
        ArrayList<OrdineProdotto> listaOrdiniProdotto = new ArrayList<>();

        try(Connection connection = Model.ConPool.getConnection()){
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM ordine_prodotto WHERE id_ordine = ?;");
            preparedStatement.setInt(1, idOrdine);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                OrdineProdotto ordineProdotto = new OrdineProdotto();
                ordineProdotto.setId_ordine(resultSet.getInt("id_ordine"));
                ordineProdotto.setId_prodotto(resultSet.getString("id_prodotto"));
                ordineProdotto.setQuantita_prodotto(resultSet.getInt("quantita_prodotto"));
                ordineProdotto.setTotale_prodotto(resultSet.getDouble("totale_prodotto"));
                ordineProdotto.setPrezzo_prodotto(resultSet.getDouble("prezzo_prodotto"));
                ordineProdotto.setImmagine_prodotto(resultSet.getString("immagine_prodotto"));
                listaOrdiniProdotto.add(ordineProdotto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaOrdiniProdotto;
    }

    //Restituisce il prodotto più venduto.
    public HashMap<String, Integer> doRetrieveBestSellingProduct(){
        HashMap<String, Integer> prodottoVendite = new HashMap<>();

        try(Connection connection = Model.ConPool.getConnection()){
            PreparedStatement preparedStatement =
                    connection.prepareStatement("select sum(quantita_prodotto) as quantitaVenduta, id_prodotto\n" +
                            "from ordine_prodotto\n" +
                            "group by id_prodotto\n" +
                            "having quantitaVenduta >= ALL( select sum(quantita_prodotto)\n" +
                            "from ordine_prodotto\n" +
                            "group by id_prodotto);");

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                prodottoVendite.put(resultSet.getString("id_prodotto"), resultSet.getInt("quantitaVenduta"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return prodottoVendite;
    }

    //Restituisce il prodotto da cui si è tratto maggior guadagno.
    public HashMap<String, Double> doRetrieveMostRemunerativeProduct(){
        HashMap<String, Double> prodottoVendite = new HashMap<>();

        try(Connection connection = Model.ConPool.getConnection()){
            PreparedStatement preparedStatement =
                    connection.prepareStatement("select sum(totale_prodotto) as totaleProdotto, id_prodotto\n" +
                            "from ordine_prodotto\n" +
                            "group by id_prodotto\n" +
                            "having totaleProdotto >= ALL( select sum(totale_prodotto)\n" +
                            "from ordine_prodotto\n" +
                            "group by id_prodotto);");

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                prodottoVendite.put(resultSet.getString("id_prodotto"), resultSet.getDouble("totaleProdotto"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return prodottoVendite;
    }

    //Dato l'id dell'ordine e le informazioni sul prodotto, li memorizza nel database.
    public void doSave(int idOrdine, String idProdotto, int quantita, double totale, double prezzo, String immagine){
        try (Connection connection = Model.ConPool.getConnection()) {
            PreparedStatement preparedStatements =
                    connection.prepareStatement("insert into ordine_prodotto(id_ordine, id_prodotto, quantita_prodotto, totale_prodotto, prezzo_prodotto, immagine_prodotto) " +
                            "VALUES(?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatements.setInt(1, idOrdine);
            preparedStatements.setString(2, idProdotto);
            preparedStatements.setInt(3, quantita);
            preparedStatements.setDouble(4, totale);
            preparedStatements.setDouble(5, prezzo);
            preparedStatements.setString(6, immagine);

            if (preparedStatements.executeUpdate() != 1)
                throw new RuntimeException("INSERT Error");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
