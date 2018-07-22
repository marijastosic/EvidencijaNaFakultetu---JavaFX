/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projektnizadatak.ki106;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import radsafajlovima.RadSaFajlovima;

/**
 *
 * @author Marija
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, Exception {
        // Osoba o = new Osoba();
//       o.setJmbg("1209");
//        System.out.println(o);
//        o.setJmbg("120");
//        
        //Osoba o1 = new Osoba("Makica", "Stosiv", "012345");
        //System.out.println(o1);
        Osoba o3 = new Zaposleni(150000, "Milica", "Meric", "1209994735012", 4);

        System.out.println(o3);
        // poredjenje predavaca po zvanju
        Predavac p1 = new Predavac(Zvanje.SARADNIK, 10, "Pera", "Zikic", "1234567891011", 2);
        Predavac p2 = new Predavac(Zvanje.ASISTENT, 10, "Mika", "Bikic", "1234567891011", 2);

        if (p1.compareTo(p2) > 0) {
            System.out.println(p1.getIme() + " ima vece zvanje od " + p2.getIme());
        } else if (p1.compareTo(p2) == 0) {
            System.out.println(p1.getIme() + " i " + p2.getIme() + " imaju isto zvanje");
        } else {
            System.out.println(p2.getIme() + " ima vece zvanje od " + p1.getIme());
        }
        //poredjenje ostalih zaposlenih po stazu ako su u istoj sluzbi
        OstaliZaposleni o5 = new OstaliZaposleni(Sluzba.PRAVNA_SLUZBA, 30000, "Ana", "Stosic", "1209994735012", 4);
        OstaliZaposleni o6 = new OstaliZaposleni(Sluzba.PRAVNA_SLUZBA, 25000, "Miki", "Peric", "1111111111111", 5);
        
        if (o5.getSluzbaOstalihZaposlenih().equals(o6.getSluzbaOstalihZaposlenih())) {

            if (o5.compareTo(o6) > 0) {
                System.out.println(o5.getIme() + " " + o5.getPrezime() + " ima veci broj godina staza od " + o6.getIme() + " " + o6.getPrezime());
            } else if (o5.compareTo(o6) < 0) {
                System.out.println(o5.getIme() + " " + o5.getPrezime() + " ima manji broj godina staza od " + o6.getIme() + " " + o6.getPrezime());
            } else {
                System.out.println("Imaju jednak broj godina staza: " + o5.getIme() + " " + o5.getPrezime() + " i " + o6.getIme() + " " + o6.getPrezime());
            } 
        } else {
            System.out.println("Nisu iz iste sluzbe!");
        }

        //poredjenje studenata po proseku ako su na istom nivou studija
        Student s1 = new Student(NivoStudija.OSNOVNE_STUDIJE, 1001, 8.15, "Iko", "Sevic", "1234567892145");
        Student s2 = new Student(NivoStudija.MASTER_STUDIJE, 1002, 8.15, "Pera", "Peric", "1212121212121");
        
        
        if(s1.getNivoStudija().equals(s2.getNivoStudija())) {
            if(s1.compareTo(s2)> 0){
                System.out.println(s1.getIme() + " " + s1.getPrezime() + " ima veci prosek od " + s2.getIme() + " " + s2.getPrezime());
            } else if(s1.compareTo(s2)< 0){
                System.out.println(s1.getIme() + " " +s1.getPrezime() + " ima manji prosek od " + s2.getIme() + " " + s2.getPrezime());
            } else {
                System.out.println("Imaju jednak prosek: " + s1.getIme() + " " + s1.getPrezime() + " i " + s2.getIme() + " " + s2.getPrezime());
            } 
        } else {
            System.out.println("Nisu na istom nivou studija, ne mogu se porediti!");
        }

        //metoda koja dodaje studenta,predavaca i ostale zaposlene u liste
        Fakultet f1 = new Fakultet("Metropolitan", "5555");
        f1.dodajStudentaUListu(s1);
        f1.dodajStudentaUListu(s2);
        
        f1.dodajOstaleZaposleneUListu(o5);
        f1.dodajOstaleZaposleneUListu(o6);
        f1.dodajPredavacaUListu(p1);
        f1.dodajPredavacaUListu(p2);
        //prikaz lista
        System.out.println("Lista predavaca: ");
        for(Predavac p : f1.getListaPredavaca()) {
            System.out.println(p);
        }
        
        System.out.println("Lista ostalih zaposlenih: ");
        for(OstaliZaposleni o : f1.getListaOstalihZaposlenih()) {
            System.out.println(o);
        }
        
        System.out.println("Lista svih zaposlenih: ");
        for(Zaposleni z : f1.getListaSvihZaposlenih()) {
            System.out.println(z);
        }        
        //pravljenje liste za odredjeni nivo studija i pozivanje metode i prosledjujemo nivo studija kroz konstruktor
        ArrayList<Student> listaOsnovne = f1.getListaStudenataZaOdredjeniNivoStudija(NivoStudija.OSNOVNE_STUDIJE);
        ArrayList<Student> listaMaster = f1.getListaStudenataZaOdredjeniNivoStudija(NivoStudija.MASTER_STUDIJE);
        ArrayList<Student> listaDoktorske = f1.getListaStudenataZaOdredjeniNivoStudija(NivoStudija.DOKTORSKE_STUDIJE);
        ArrayList<Student> listaSvi = f1.getListaStudenata();
        
        //prikaz liste svih studenata i zasebne liste sa studentima na tim nivoima studija
        System.out.println("Svi studenti: ");
        for(Student s : listaSvi) {
            System.out.println(s);
        }
        
        System.out.println("OAS studenti: ");
        for(Student s : listaOsnovne) {
            System.out.println(s);
        }
        
        System.out.println("MAS studenti: ");
        for(Student s : listaMaster) {
            System.out.println(s);
        }
        
        System.out.println("DS studenti: ");
        for(Student s : listaDoktorske) {
            System.out.println(s);
        }
        
        Zaposleni z = new Zaposleni(25000, "Maja", "Pric","1231231231231", 2);
                
        System.out.println(NivoStudija.MASTER_STUDIJE);
        
        System.out.println("Indeks: " + s1.getIndeks());
        
        //UpisUFajlove.upisStudentaUFajl(s1);
        //UpisUFajlove.upisStudentaUFajl(s2);
        //UpisUFajlove.upisPredavacaUFajl(p2);
        //UpisUFajlove.upisOstalihZaposlenihUFajl(o6);
        
        ArrayList<Student> listaStudenata = RadSaFajlovima.ucitajSveStudenteIzFajla();
        
        
        System.out.println("Studenti iz fajla: ");
        for(Student s : listaStudenata) {
            System.out.println(s);
        }
        
        System.out.println("Predavaci iz fajla: ");
        ArrayList<Predavac> listaPredavaca = RadSaFajlovima.ucitajSvePredavaceIzFajla();
        for(Predavac p : listaPredavaca){
            System.out.println(p);
        }
        
        System.out.println("Ostali zaposleni iz fajla: ");
        ArrayList<OstaliZaposleni> listaOstalihZaposenih = RadSaFajlovima.ucitajSveOstaleZaposleneIzFajla();
        for(OstaliZaposleni o : listaOstalihZaposenih){
            System.out.println(o);
        }
        
        System.out.println(RadSaFajlovima.vratiMaksimalanBrojIndeksaIzFajla());
    }
}
