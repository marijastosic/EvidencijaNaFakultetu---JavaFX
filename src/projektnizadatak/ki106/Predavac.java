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
public class Predavac extends Zaposleni implements Comparable<Predavac> {
    private Zvanje zvanjePredavaca;

    public Predavac(Zvanje zvanjePredavaca, double plataZaposlenih, String ime, String prezime, String jmbg, double godineStaza) throws Exception {
        super(plataZaposlenih, ime, prezime, jmbg, godineStaza);
        this.zvanjePredavaca = zvanjePredavaca;
    }

    public Predavac() {
        
    }

    public Zvanje getZvanjePredavaca() {
        return zvanjePredavaca;
    }

    public void setZvanjePredavaca(Zvanje zvanjePredavaca) {
        this.zvanjePredavaca = zvanjePredavaca;
    }
    
    @Override
    public int compareTo(Predavac p) {
        if(this.zvanjePredavaca.getRang() > p.zvanjePredavaca.getRang()) {
            return 1;
        } else if(this.zvanjePredavaca.getRang() == p.zvanjePredavaca.getRang()) {
            return 0;
        } else {
            return -1;
        }
    }

    
    @Override
    public String toString() {
        return super.toString().replace("ZAPOSLENI", "PREDAVAC") + " zvanje predavaca: " + zvanjePredavaca+ "\n";
    }
    
}
