package Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdottoDAO {

    //Restituisce tutti i prodotti presenti nel database.
    public List<Prodotto> doRetrieveAll() {
        List<Prodotto> listaProdotti = new ArrayList<Prodotto>();

        try (Connection connection = Model.ConPool.getConnection()) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM prodotto;");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Prodotto prodotto = new Prodotto();
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

    //Dato un id, restituisce il corrispondente prodotto.
    public Prodotto doRetrieveById(String id_prodotto) {
        Prodotto prodotto = new Prodotto();

        try (Connection connection = Model.ConPool.getConnection()) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * " +
                            "FROM prodotto " +
                            "WHERE id_prodotto = ?");
            preparedStatement.setString(1, id_prodotto);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                prodotto.setId_prodotto(id_prodotto);
                prodotto.setNome(resultSet.getString("nome"));
                prodotto.setProvenienza(resultSet.getString("provenienza"));
                prodotto.setDescrizione(resultSet.getString("descrizione"));
                prodotto.setQuantita(resultSet.getInt("quantita"));
                prodotto.setPrezzo(resultSet.getDouble("prezzo"));
                prodotto.setCategoria(resultSet.getString("nomecategoria"));
                prodotto.setFormato(resultSet.getString("formato"));
                prodotto.setImmagine(resultSet.getString("immagine"));
            } else
                return null;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return prodotto;
    }

    //Restituisce tutte le categorie dei prodotti memorizzati nel database.
    public List<String> doRetrieveAllCategoria() {
        List<String> listaCategorie = new ArrayList<>();

        try (Connection connection = ConPool.getConnection()) {
            PreparedStatement ps =
                    connection.prepareStatement("SELECT distinct(prodotto.nomecategoria) FROM prodotto;");

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                Prodotto prodotto = new Prodotto();
                prodotto.setCategoria(resultSet.getString("nomecategoria"));
                listaCategorie.add(prodotto.getCategoria());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaCategorie;
    }

    //Data una categoria, restituisce tutti i prodotti appartenenti a questa.
    public List<Prodotto> doRetrieveByCategoria(String nomeCategoria) {
        ArrayList<Prodotto> listaProdotti = new ArrayList<>();

        try (Connection connection = ConPool.getConnection()) {
            PreparedStatement ps =
                    connection.prepareStatement("SELECT * FROM prodotto WHERE nomecategoria ='" + nomeCategoria + "';");

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                Prodotto prodotto = new Prodotto();
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

    //Data una provenienza, restituisce tutti i prodotti con quell'origine.
    public List<Prodotto> doRetrieveByOrigin(String provenienza) {
        List<Prodotto> listaProdotti = new ArrayList<>();

        try (Connection connection = ConPool.getConnection()) {
            PreparedStatement ps =
                    connection.prepareStatement("SELECT * FROM prodotto WHERE provenienza =" + provenienza + ";");

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                Prodotto prodotto = new Prodotto();
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

    //Data una quantità, restituisce tutti i prodotti che hanno una quantità disponibile maggiore.
    public List<Prodotto> doRetrieveByQuantity(int quantita) {
        List<Prodotto> listaProdotti = new ArrayList<>();

        try (Connection connection = ConPool.getConnection()) {
            PreparedStatement ps =
                    connection.prepareStatement("SELECT * FROM prodotto WHERE quantita >=" + quantita + ";");

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                Prodotto prodotto = new Prodotto();
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

    //Restitusce i prodotti in ordine crescente di prezzo.
    public List<Prodotto> doRetriveByPriceAscendingOrder() {
        List<Prodotto> listaProdotti = new ArrayList<>();

        try (Connection connection = ConPool.getConnection()) {
            PreparedStatement ps =
                    connection.prepareStatement("SELECT * FROM prodotto ORDER BY prezzo");

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                Prodotto prodotto = new Prodotto();
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

    //Restituisce i prodotti in ordine decrescente di prezzo.
    public List<Prodotto> doRetriveByPriceDescendingOrder() {
        List<Prodotto> listaProdotti = new ArrayList<>();

        try (Connection connection = ConPool.getConnection()) {
            PreparedStatement ps =
                    connection.prepareStatement("SELECT * FROM prodotto ORDER BY prezzo DESC");

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                Prodotto prodotto = new Prodotto();
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

    //Dato l'id di un prodotto, lo elimina dal database.
    public void doDeleteProduct(String id_prodotto) throws SQLException {
        try (Connection connection = ConPool.getConnection()) {
            PreparedStatement preparedStatements =
                    connection.prepareStatement("DELETE FROM prodotto WHERE id_prodotto = ?;");

            preparedStatements.setString(1, id_prodotto);
            preparedStatements.executeUpdate();
        }
    }

    //Dato un prodotto, lo memorizza nel database.
    public void doSave(Prodotto prodotto) {
        try (Connection connection = Model.ConPool.getConnection()) {
            PreparedStatement preparedStatements =
                    connection.prepareStatement("insert into Prodotto(id_prodotto, nome, provenienza, descrizione, quantita, prezzo, nomecategoria, formato) " +
                            "VALUES(?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatements.setString(1, prodotto.getId_prodotto());
            preparedStatements.setString(2, prodotto.getNome());
            preparedStatements.setString(3, prodotto.getProvenienza());
            preparedStatements.setString(4, prodotto.getDescrizione());
            preparedStatements.setInt(5, prodotto.getQuantita());
            preparedStatements.setDouble(6, prodotto.getPrezzo());
            preparedStatements.setString(7, prodotto.getCategoria());
            preparedStatements.setString(8, prodotto.getFormato());

            if (preparedStatements.executeUpdate() != 1)
                throw new RuntimeException("INSERT Error");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Aggiornamento nome prodotto.
    public void doUpdateNome(String id_prodotto, String nome) throws SQLException {
        try (Connection connection = ConPool.getConnection()) {
            PreparedStatement preparedStatements =
                    connection.prepareStatement("UPDATE prodotto SET nome = '" + nome + "' WHERE id_prodotto = ?;");

            preparedStatements.setString(1, id_prodotto);
            preparedStatements.executeUpdate();
        }
    }

    //Aggiornamento provenienza prodotto.
    public void doUpdateProvenienza(String id_prodotto, String provenienza) throws SQLException {
        try (Connection connection = ConPool.getConnection()) {
            PreparedStatement preparedStatements =
                    connection.prepareStatement("UPDATE prodotto SET provenienza = '" + provenienza + "' WHERE id_prodotto = ?;");

            preparedStatements.setString(1, id_prodotto);
            preparedStatements.executeUpdate();
        }
    }

    //Aggiornamento descrizione prodotto.
    public void doUpdateDescrizione(String id_prodotto, String descrizione) throws SQLException {
        try (Connection connection = ConPool.getConnection()) {
            PreparedStatement preparedStatements =
                    connection.prepareStatement("UPDATE prodotto SET descrizione = ? WHERE id_prodotto = ?;");

            preparedStatements.setString(1, descrizione);
            preparedStatements.setString(2, id_prodotto);
            preparedStatements.executeUpdate();
        }
    }

    //Aggiornamento quantità prodotto.
    public void doUpdateQuantita(String id_prodotto, int quantita) throws SQLException {
        try (Connection connection = ConPool.getConnection()) {
            PreparedStatement preparedStatements =
                    connection.prepareStatement("UPDATE prodotto SET quantita = ? WHERE id_prodotto = ?;");

            preparedStatements.setInt(1, quantita);
            preparedStatements.setString(2, id_prodotto);
            preparedStatements.executeUpdate();
        }
    }

    //Aggiornamento prezzo prodotto.
    public void doUpdatePrezzo(String id_prodotto, double prezzo) throws SQLException {
        try (Connection connection = ConPool.getConnection()) {
            PreparedStatement preparedStatements =
                    connection.prepareStatement("UPDATE prodotto SET prezzo = ? WHERE id_prodotto = ?;");

            preparedStatements.setDouble(1, prezzo);
            preparedStatements.setString(2, id_prodotto);
            preparedStatements.executeUpdate();
        }
    }

    //Aggiornamento formato prodotto.
    public void doUpdateFormato(String id_prodotto, String formato) throws SQLException {
        try (Connection connection = ConPool.getConnection()) {
            PreparedStatement preparedStatements =
                    connection.prepareStatement("UPDATE prodotto SET formato ='" + formato + "' WHERE id_prodotto = ?;");

            preparedStatements.setString(1, id_prodotto);
            preparedStatements.executeUpdate();
        }
    }

    //Aggiornamento categoria prodotto.
    public void doUpdateCategoria(String id_prodotto, String categoria) throws SQLException {
        try (Connection connection = ConPool.getConnection()) {
            PreparedStatement preparedStatements =
                    connection.prepareStatement("UPDATE prodotto SET nomecategoria = ? WHERE id_prodotto = ?;");

            preparedStatements.setString(1, categoria);
            preparedStatements.setString(2, id_prodotto);
            preparedStatements.executeUpdate();
        }
    }

    //Aggiornamento immagine prodotto.
    public void doUpdateImmagine(String id_prodotto, String immagine) throws SQLException {
        try (Connection connection = ConPool.getConnection()) {
            PreparedStatement preparedStatements =
                    connection.prepareStatement("UPDATE prodotto SET immagine = '" + immagine + "' WHERE id_prodotto = ?;");

            preparedStatements.setString(1, id_prodotto);
            preparedStatements.executeUpdate();
        }
    }

}