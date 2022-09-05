package Model;

public class OrdineProdotto {
    private int id_ordine;
    private String id_prodotto;
    private int quantita_prodotto;
    private double totale_prodotto;
    private double prezzo_prodotto;
    private String immagine_prodotto;

    public int getId_ordine() {
        return id_ordine;
    }

    public void setId_ordine(int id_ordine) {
        this.id_ordine = id_ordine;
    }

    public String getId_prodotto() {
        return id_prodotto;
    }

    public void setId_prodotto(String id_prodotto) {
        this.id_prodotto = id_prodotto;
    }

    public int getQuantita_prodotto() {
        return quantita_prodotto;
    }

    public void setQuantita_prodotto(int quantita_prodotto) {
        this.quantita_prodotto = quantita_prodotto;
    }

    public double getTotale_prodotto() {
        return totale_prodotto;
    }

    public void setTotale_prodotto(double totale_prodotto) {
        this.totale_prodotto = totale_prodotto;
    }

    public double getPrezzo_prodotto() {
        return prezzo_prodotto;
    }

    public void setPrezzo_prodotto(double prezzo_prodotto) {
        this.prezzo_prodotto = prezzo_prodotto;
    }

    public String getImmagine_prodotto() {
        return immagine_prodotto;
    }

    public void setImmagine_prodotto(String immagine_prodotto) {
        this.immagine_prodotto = immagine_prodotto;
    }
}
