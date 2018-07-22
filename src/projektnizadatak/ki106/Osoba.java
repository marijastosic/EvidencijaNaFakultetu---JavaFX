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
public class Osoba {

    private String ime, prezime, jmbg;

    public Osoba() {
    }

    public Osoba(String ime, String prezime, String jmbg) throws Exception {
        this.ime = ime;
        this.prezime = prezime;

        String brojKojiJeSaNulama = "0000000000000";
        if (jmbg.length() == 13 && !jmbg.contains(brojKojiJeSaNulama)) {
            this.jmbg = jmbg;
//        } else if (jmbg.contains(brojKojiJeSaNulama)) {
//            System.out.println("JMBG ne sme sadrzati sve nule!");
//        } else if (!(jmbg.length() == 13) && jmbg.contains(brojKojiJeSaNulama)) {
//            System.out.println("JMBG mora imati 13 cifara i ne sadrzati sve nule");
        } else {
            throw new Exception("JMBG mora imati 13 cifara i ceo jmbg ne sme sadrzati nule!");
        }
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) throws Exception {
        String brojKojiJeSaNulama = "0000000000000";
        if (jmbg.length() == 13 && !jmbg.contains(brojKojiJeSaNulama)) {
            this.jmbg = jmbg;
        } else {
            throw new Exception("JMBG mora imati 13 cifara i ceo jmbg ne sme sadrzati nule!");
        }
    }

    @Override
    public String toString() {
        return "Osoba \n" + "IME: " + ime + " PREZIME: " + prezime + " JMBG: " + jmbg ;
    }

}
