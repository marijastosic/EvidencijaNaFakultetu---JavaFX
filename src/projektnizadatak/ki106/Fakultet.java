/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projektnizadatak.ki106;

import java.util.ArrayList;

/**
 *
 * @author Marija
 */
public class Fakultet {

    private String imeFakulteta;
    private String pib;
    private ArrayList<Predavac> listaPredavaca = new ArrayList<>();
    private ArrayList<Student> listaStudenata = new ArrayList<>();
    private ArrayList<OstaliZaposleni> listaOstalihZaposlenih = new ArrayList<>();

    public Fakultet() {
    }

    public Fakultet(String imeFakulteta, String pib) {
        this.imeFakulteta = imeFakulteta;
        this.pib = pib;
    }
    
    public String getImeFakulteta() {
        return imeFakulteta;
    }

    public void setImeFakulteta(String imeFakulteta) {
        this.imeFakulteta = imeFakulteta;
    }

    public String getPib() {
        return pib;
    }

    public void setPib(String pib) {
        this.pib = pib;
    }

    public ArrayList<Predavac> getListaPredavaca() {
        return listaPredavaca;
    }

    public void setListaPredavaca(ArrayList<Predavac> listaPredavaca) {
        this.listaPredavaca = listaPredavaca;
    }

    public ArrayList<Student> getListaStudenata() {
        return listaStudenata;
    }

    public void setListaStudenata(ArrayList<Student> listaStudenata) {
        this.listaStudenata = listaStudenata;
    }
    
    public ArrayList<OstaliZaposleni> getListaOstalihZaposlenih() {
        return listaOstalihZaposlenih;
    }
    
    public void setListaOstalihZaposlenih(ArrayList<OstaliZaposleni> listaOstalihZaposlenih) {    
        this.listaOstalihZaposlenih = listaOstalihZaposlenih;
    }

    public ArrayList<Student> getListaStudenataZaOdredjeniNivoStudija(NivoStudija nivoStudija) {
        ArrayList<Student> listaZaVracanje = new ArrayList<>();
        for (Student s : listaStudenata) {
            if (s.getNivoStudija().equals(nivoStudija)) {
                listaZaVracanje.add(s);
            }
        }
        return listaZaVracanje;
    }
    
    public ArrayList<Zaposleni> getListaSvihZaposlenih() {
        ArrayList<Zaposleni> sviZaposleni = new ArrayList<>();
        sviZaposleni.addAll(listaPredavaca);
        sviZaposleni.addAll(listaOstalihZaposlenih);
        
        return sviZaposleni;
    }
    public void dodajPredavacaUListu(Predavac p) {
        listaPredavaca.add(p);
    }

    public void dodajOstaleZaposleneUListu(OstaliZaposleni o) {
        listaOstalihZaposlenih.add(o);
    }

    public void dodajStudentaUListu(Student s) {
        listaStudenata.add(s);
    }

    @Override
    public String toString() {
        return "Fakultet- " + imeFakulteta + ", PIB: " + pib + " LISTA PREDAVACA: \n" + listaPredavaca + " LISTA STUDENATA: \n" + listaStudenata + "LISTA OSTALIH ZAPOSLENIH: \n" + listaOstalihZaposlenih + "\n";
    }

}
