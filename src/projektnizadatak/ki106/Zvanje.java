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
public enum Zvanje {

    REDOVNI_PROFESOR("Redovni profesor", 5), VANREDNI_PROFESOR("Vanredni profesor", 4), DOCENT("Docent", 3), ASISTENT("Asistent", 2), SARADNIK("Saradnik", 1);

    private final String vrednost;
    private final int rang;

    private Zvanje(String vrednost, int rang) {
        this.vrednost = vrednost;
        this.rang = rang;
    }

    public int getRang() {
        return rang;
    }
    

    @Override
    public String toString() {
        return vrednost;
    }
}
