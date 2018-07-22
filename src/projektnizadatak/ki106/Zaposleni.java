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
public class Zaposleni extends Osoba {

    private double plataZaposlenih;
    private double godineStaza;

    public Zaposleni() {
    }

    public Zaposleni(double plataZaposlenih, String ime, String prezime, String jmbg, double godineStaza) throws Exception {
        super(ime, prezime, jmbg);
        if (plataZaposlenih > 0) {
            this.plataZaposlenih = plataZaposlenih;
        } else {
            throw new Exception("PLata mora biti pozitivna vrednost!");
        }
        if (godineStaza > 0) {
            this.godineStaza = godineStaza;
        } else {
            throw new Exception("Godine staza moraju biti pozitivna vrednost!");
        }
        
    }

    public double getPlataZaposlenih() {
        return plataZaposlenih;
    }

    public void setPlataZaposlenih(double plataZaposlenih) throws Exception {
        if (plataZaposlenih > 0) {
            this.plataZaposlenih = plataZaposlenih;
        } else {
            throw new Exception("PLata mora biti pozitivna vrednost!");
        }
    }

    
    public double getGodineStaza(){
        return godineStaza;
    }
    public void setGodineStaza(double godineStaza) throws Exception{
        if (godineStaza > 0) {
            this.godineStaza = godineStaza;
        } else {
            throw new Exception("Godine staza moraju biti pozitivna vrednost!");
        }
    }
    @Override
    public String toString() {
        return super.toString().replace("Osoba", "ZAPOSLENI:") + " Plata: " + plataZaposlenih + " STAZ:"+ godineStaza;
    }

}
