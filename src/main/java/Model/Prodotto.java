package Model;

import java.util.Objects;

public class Prodotto {

    private String id_prodotto;
    private String nome;
    private String provenienza;
    private String descrizione;
    private int quantita;
    private double prezzo;
    private String categoria;
    private String formato;

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    private String immagine;

    public String getId_prodotto() {
        return id_prodotto;
    }

    public void setId_prodotto(String id_prodotto) {
        this.id_prodotto = id_prodotto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getProvenienza() {
        return provenienza;
    }

    public void setProvenienza(String provenienza) {
        this.provenienza = provenienza;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getImmagine() {
        return immagine;
    }

    public void setImmagine(String immagine) {
        this.immagine = immagine;
    }

    @Override
    public boolean equals(Object o) {
        Prodotto prodotto = (Prodotto) o;
        if(this.id_prodotto.equalsIgnoreCase(prodotto.getId_prodotto()))
            return true;
        return false;
    }

    @Override
    public String toString() {
        return "Prodotto{" +
                "id_prodotto='" + id_prodotto + '\'' +
                ", nome='" + nome + '\'' +
                ", provenienza='" + provenienza + '\'' +
                ", descrizione='" + descrizione + '\'' +
                ", quantita=" + quantita +
                ", prezzo=" + prezzo +
                ", categoria='" + categoria + '\'' +
                ", formato='" + formato + '\'' +
                ", immagine='" + immagine + '\'' +
                '}';
    }
}
