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
public class OstaliZaposleni extends Zaposleni implements Comparable<OstaliZaposleni> {

    private Sluzba sluzbaOstalihZaposlenih;

    public OstaliZaposleni(Sluzba sluzbaOstalihZaposlenih, double plataZaposlenih, String ime, String prezime, String jmbg, double godineStaza) throws Exception {
        super(plataZaposlenih, ime, prezime, jmbg, godineStaza);
        this.sluzbaOstalihZaposlenih = sluzbaOstalihZaposlenih;
    }

    public OstaliZaposleni() {
        
    }

    public Sluzba getSluzbaOstalihZaposlenih() {
        return sluzbaOstalihZaposlenih;
    }

    public void setSluzbaOstalihZaposlenih(Sluzba sluzbaOstalihZaposlenih) {
        this.sluzbaOstalihZaposlenih = sluzbaOstalihZaposlenih;
    }

    @Override
    public int compareTo(OstaliZaposleni o) {
        if (this.getGodineStaza() > o.getGodineStaza()) {
            return 1;
        } else if (this.getGodineStaza() < o.getGodineStaza()) {
            return -1;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return super.toString().replaceAll("ZAPOSLENI", "OSTALI ZAPOSLENI") + " sluzba zaposlenih: " + sluzbaOstalihZaposlenih+ "\n";
    }

}
