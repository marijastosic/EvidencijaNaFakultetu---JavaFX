/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projektnizadatak.ki106;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marija
 */
public class Student extends Osoba implements Comparable<Student>{

    private NivoStudija nivoStudija;
    private int indeks;
    private double prosek;

    public Student() {
    }

    public Student(NivoStudija nivoStudija, int indeks, double prosek, String ime, String prezime, String jmbg) throws Exception {
        super(ime, prezime, jmbg);
        this.nivoStudija = nivoStudija;
        if (indeks > 1000) {
            this.indeks = indeks;
        } else {
            throw new Exception("Broj indeksa mora biti veci od 1000!");
        }

        this.prosek = prosek;
    }

    public NivoStudija getNivoStudija() {
        return nivoStudija;
    }

    public void setNivoStudija(NivoStudija nivoStudija) {
        this.nivoStudija = nivoStudija;
    }

    public int getIndeks() {
        return indeks;
    }

    public void setIndeks(int indeks) throws Exception {
        if (indeks > 1000) {
            this.indeks = indeks;
        } else {
            throw new Exception("Broj indeksa mora biti veci od 1000!");
        }

    }

    public double getProsek() {
        return prosek;
    }

    public void setProsek(double prosek) {
        this.prosek = prosek;
    }

//    public double compareTo(Student s) {
//
//        if ((this.nivoStudija.equals(s.getNivoStudija()) && (this.getProsek()) > s.getProsek())) {
//            return 1;
//        } else if ((this.nivoStudija.equals(s.getNivoStudija()) && this.getProsek() < s.getProsek())) {
//            return -1;
//        } else {
//            return 0;
//        }
//    }
    @Override
    public int compareTo(Student s) {

        if (this.getProsek() > s.getProsek()) {
            return 1;
        } else if (this.getProsek() < s.getProsek()) {
            return -1;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return super.toString().replaceAll("Osoba", "STUDENT: ") + " Nivo studija: " + nivoStudija + " Indeks: " + indeks + " Prosek: " + prosek + "\n";
    }

}
