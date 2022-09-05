package Model;

import java.util.ArrayList;
import java.util.HashMap;

public class Carrello {
    private HashMap<Prodotto, Integer> listaProdotti;

    public HashMap<Prodotto, Integer> getListaProdotti() {
        return listaProdotti;
    }

    public void setListaProdotti(HashMap<Prodotto, Integer> listaProdotti) {
        this.listaProdotti = listaProdotti;
    }

    //Dato un prodotto, restituisce il costo totale per quel prodotto.
    public double totaleProdotto(Prodotto p){
        return this.listaProdotti.get(p) * p.getPrezzo();
    }

    //Dato un prodotto, ci consente di verificare se esso è presente nel carrello.
    public boolean containsProduct(Prodotto p){
        for(Prodotto prodotto: this.listaProdotti.keySet()){
            if(prodotto.getId_prodotto().equalsIgnoreCase(p.getId_prodotto())){
                return true;
            }
        }
        return false;
    }

    //Restituisce il costo totale di tutti i prodotti inseriti nel carrello.
    public double totaleCarrello(){
        double totale = 0;
        for(Prodotto p: this.listaProdotti.keySet()){
            totale += totaleProdotto(p);
        }
        return Math.round(totale*100.0)/100.0;
    }

    //Dato un prodotto, restituisce la quantità memorizzata nel carrello.
    public int getQuantity(Prodotto prodotto){
        for(Prodotto p: this.listaProdotti.keySet()){
            if(p.equals(prodotto))
                return listaProdotti.get(p);
        }
        return 0;
    }

    //Dato un prodotto e una quantità, va a modificare la quantità memorizzata nel carrello.
    public void changeQuantityProduct(Prodotto prodotto, int quantita){
        for(Prodotto p: this.listaProdotti.keySet()){
            if(p.getId_prodotto().equalsIgnoreCase(prodotto.getId_prodotto()))
                this.listaProdotti.replace(p, this.getQuantity(p), quantita);
        }
    }

    //Restituisce tutti i prodotti memorizzati nel carrello.
    public ArrayList<Prodotto> getAllProduct(){
        ArrayList<Prodotto> elencoProdottiCart = new ArrayList<>();
        for(Prodotto p: this.listaProdotti.keySet()){
            elencoProdottiCart.add(p);
        }
        return elencoProdottiCart;
    }

    //Dato un prodotto, lo rimuove dal carrello.
    public void removeProductFromCart(Prodotto prodotto){
        for(Prodotto p: this.listaProdotti.keySet()){
            if(p.getId_prodotto().equalsIgnoreCase(prodotto.getId_prodotto())){
                this.listaProdotti.remove(p);
                break;
            }
        }
    }
}
