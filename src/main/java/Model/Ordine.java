package Model;

import java.sql.Date;

public class Ordine {

    private int id_ordine;
    private Date data_ordine;
    private String via;
    private int numero_civico;
    private String cap;
    private double importo;
    private int id_utente;

    public int getId_ordine() {
        return id_ordine;
    }

    public void setId_ordine(int id_ordine) {
        this.id_ordine = id_ordine;
    }

    public Date getData_ordine() {
        return data_ordine;
    }

    public void setData_ordine(Date data_ordine) {
        this.data_ordine = data_ordine;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public int getNumero_civico() {
        return numero_civico;
    }

    public void setNumero_civico(int numero_civico) {
        this.numero_civico = numero_civico;
    }

    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    public double getImporto() {
        return importo;
    }

    public void setImporto(double importo) {
        this.importo = importo;
    }

    public int getId_utente() {
        return id_utente;
    }

    public void setId_utente(int id_utente) {
        this.id_utente = id_utente;
    }

}
