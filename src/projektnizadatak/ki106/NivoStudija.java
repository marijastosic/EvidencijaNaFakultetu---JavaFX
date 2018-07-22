/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projektnizadatak.ki106;

/**
 *
 * @author Marija
 */
public enum NivoStudija {
    
    OSNOVNE_STUDIJE("Osnovne studije"),MASTER_STUDIJE("Master studije"), DOKTORSKE_STUDIJE("Doktorske studije");
    private final String nazivStudija;

    private NivoStudija(String nazivStudija) {
        this.nazivStudija = nazivStudija;
    }    

    @Override
    public String toString() {
        return nazivStudija;
    }
    
}
