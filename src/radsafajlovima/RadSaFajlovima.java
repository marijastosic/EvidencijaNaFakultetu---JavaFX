/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package radsafajlovima;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import projektnizadatak.ki106.NivoStudija;
import projektnizadatak.ki106.OstaliZaposleni;
import projektnizadatak.ki106.Predavac;
import projektnizadatak.ki106.Sluzba;
import projektnizadatak.ki106.Student;
import projektnizadatak.ki106.Zaposleni;
import projektnizadatak.ki106.Zvanje;

/**
 *
 * @author Marija
 */
public class RadSaFajlovima {

    /**
     * Metoda koja sluzi za upisivanje novog predavaca u fajl.
     * Metoda prima objekat klase Predavac i upisuje njegove atribute u fajl (svaki u novi red)
     * @param p
     * @throws IOException 
     */
    public static void upisPredavacaUFajl(Predavac p) throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter("predavaci.txt", true));
        pw.write(p.getIme() + " " + p.getPrezime());
        pw.write("\r\n");
        pw.write(p.getJmbg());
        pw.write("\r\n");
        pw.write(p.getZvanjePredavaca() + "");
        pw.write("\r\n");
        pw.write(p.getPlataZaposlenih() + "");
        pw.write("\r\n");
        pw.write(p.getGodineStaza() + "");
        pw.write("\r\n");
        pw.close();
    }

    public static void upisOstalihZaposlenihUFajl(OstaliZaposleni o) throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter("ostaliZaposleni.txt", true));
        pw.write(o.getIme() + " " + o.getPrezime());
        pw.write("\r\n");
        pw.write(o.getJmbg());
        pw.write("\r\n");
        pw.write(o.getSluzbaOstalihZaposlenih() + "");
        pw.write("\r\n");
        pw.write(o.getPlataZaposlenih() + "");
        pw.write("\r\n");
        pw.write(o.getGodineStaza() + "");
        pw.write("\r\n");
        pw.close();
    }

    public static void upisStudentaUFajl(Student s) throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter("studenti.txt", true));
        pw.write(s.getIme() + " " + s.getPrezime());
        pw.write("\r\n");
        pw.write(s.getJmbg());
        pw.write("\r\n");
        pw.write(s.getIndeks() + "");
        pw.write("\r\n");
        pw.write(s.getNivoStudija().toString());
        pw.write("\r\n");
        pw.write(s.getProsek() + "");
        pw.write("\r\n");
        pw.close();
    }

    public static ArrayList<Student> ucitajSveStudenteIzFajla() throws Exception {
        ArrayList<Student> lista = new ArrayList<>();

        Scanner scanner;
        try {
            scanner = new Scanner(new File("studenti.txt"));
            while (scanner.hasNext()) {
                Student student = new Student();
                String[] imeIPrezime = scanner.nextLine().split(" "); //{"Marija", "Stosic"}
                student.setIme(imeIPrezime[0]);
                student.setPrezime(imeIPrezime[1]);
                student.setJmbg(scanner.nextLine());
                student.setIndeks(scanner.nextInt());
                scanner.nextLine();
                String nivoStudija = scanner.nextLine();
                switch (nivoStudija) {
                    case "Osnovne studije":
                        student.setNivoStudija(NivoStudija.OSNOVNE_STUDIJE);
                        break;
                    case "Master studije":
                        student.setNivoStudija(NivoStudija.MASTER_STUDIJE);
                        break;
                    case "Doktorske studije":
                        student.setNivoStudija(NivoStudija.DOKTORSKE_STUDIJE);
                        break;
                    default:
                        student.setNivoStudija(null);
                        break;
                }

                student.setProsek(scanner.nextDouble());
                scanner.nextLine();

                lista.add(student);
            }

            scanner.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RadSaFajlovima.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

    public static ArrayList<Predavac> ucitajSvePredavaceIzFajla() throws Exception {
        ArrayList<Predavac> lista1 = new ArrayList<>();

        Scanner scanner;
        try {
            scanner = new Scanner(new File("predavaci.txt"));
            while (scanner.hasNext()) {
                Predavac predavac = new Predavac();
                String[] imeIPrezime = scanner.nextLine().split(" "); //{"Marija", "Stosic"}
                predavac.setIme(imeIPrezime[0]);
                predavac.setPrezime(imeIPrezime[1]);
                predavac.setJmbg(scanner.nextLine());
                String zvanjePredavaca = scanner.nextLine();
                switch (zvanjePredavaca) {
                    case "Redovni profesor":
                        predavac.setZvanjePredavaca(Zvanje.REDOVNI_PROFESOR);
                        break;
                    case "Vanredni profesor":
                        predavac.setZvanjePredavaca(Zvanje.VANREDNI_PROFESOR);
                        break;
                    case "Docent":
                        predavac.setZvanjePredavaca(Zvanje.DOCENT);
                        break;
                    case "Asistent":
                        predavac.setZvanjePredavaca(Zvanje.ASISTENT);
                        break;
                    case "Saradnik":
                        predavac.setZvanjePredavaca(Zvanje.SARADNIK);
                        break;
                    default:
                        predavac.setZvanjePredavaca(null);
                        break;
                }
                
                predavac.setPlataZaposlenih(scanner.nextDouble());
                scanner.nextLine();
                predavac.setGodineStaza(scanner.nextDouble());
                scanner.nextLine();

                lista1.add(predavac);
                
            }
            scanner.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RadSaFajlovima.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista1;
    }

    public static ArrayList<OstaliZaposleni> ucitajSveOstaleZaposleneIzFajla() throws Exception {
        ArrayList<OstaliZaposleni> lista2 = new ArrayList<>();

        Scanner scanner;
        try {
            scanner = new Scanner(new File("ostaliZaposleni.txt"));
            while (scanner.hasNext()) {
                OstaliZaposleni ostaliZaposleni = new OstaliZaposleni();
                String[] imeIPrezime = scanner.nextLine().split(" "); //{"Marija", "Stosic"}
                ostaliZaposleni.setIme(imeIPrezime[0]);
                ostaliZaposleni.setPrezime(imeIPrezime[1]);
                ostaliZaposleni.setJmbg(scanner.nextLine());
                String zvanjePredavaca = scanner.nextLine();
                switch (zvanjePredavaca) {
                    case "Studentska sluzba":
                        ostaliZaposleni.setSluzbaOstalihZaposlenih(Sluzba.STUDENTSKA_SLUZBA);
                        break;
                    case "Pravna sluzba":
                        ostaliZaposleni.setSluzbaOstalihZaposlenih(Sluzba.PRAVNA_SLUZBA);
                        break;
                    case "IT sluzba":
                        ostaliZaposleni.setSluzbaOstalihZaposlenih(Sluzba.IT_SLUZBA);
                        break;
                    default:
                        ostaliZaposleni.setSluzbaOstalihZaposlenih(null);
                        break;
                }
                
                ostaliZaposleni.setPlataZaposlenih(scanner.nextDouble());
                scanner.nextLine();
                ostaliZaposleni.setGodineStaza(scanner.nextDouble());
                scanner.nextLine();

                
                lista2.add(ostaliZaposleni);
                
            }
            scanner.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RadSaFajlovima.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista2;
    }
    
    public static int vratiMaksimalanBrojIndeksaIzFajla() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("studenti.txt"));
        
        int max = 1001;
        
        while(scanner.hasNext()) {
            scanner.nextLine();
            scanner.nextLine();
            int indeks = scanner.nextInt();
            if(indeks > max) {
                max = indeks;
            }
            scanner.nextLine();
            scanner.nextLine();
            scanner.nextLine();
        }
        scanner.close();
        return max;
    }
    
    public static void obrisiStudentaIzFajla(Student s) throws FileNotFoundException, Exception {
        
        File studenti = new File("studenti.txt");
        File studentiTemp = new File("studentiTemp.txt");
        
        Scanner scanner = new Scanner(studenti);

        PrintWriter pw = new PrintWriter(new FileWriter(studentiTemp, true));
        
        while (scanner.hasNext()) {
            Student student = new Student();
            String[] imeIPrezime = scanner.nextLine().split(" "); //{"Marija", "Stosic"}
            student.setIme(imeIPrezime[0]);
            student.setPrezime(imeIPrezime[1]);
            student.setJmbg(scanner.nextLine());
            student.setIndeks(scanner.nextInt());
            scanner.nextLine();
            String nivoStudija = scanner.nextLine();
            switch (nivoStudija) {
                case "Osnovne studije":
                    student.setNivoStudija(NivoStudija.OSNOVNE_STUDIJE);
                    break;
                case "Master studije":
                    student.setNivoStudija(NivoStudija.MASTER_STUDIJE);
                    break;
                case "Doktorske studije":
                    student.setNivoStudija(NivoStudija.DOKTORSKE_STUDIJE);
                    break;
                default:
                    student.setNivoStudija(null);
                    break;
            }

            student.setProsek(scanner.nextDouble());
            scanner.nextLine();
                        
            //provera da li je trenutni objekat onaj koji hocemo da obrisemo
            //ako jeste, ne prepisujemo ga
            //ako nije, prepisujemo ga u studentiTemp
            if (student.getIndeks() != s.getIndeks()) {
                pw.write(student.getIme() + " " + student.getPrezime());
                pw.write("\r\n");
                pw.write(student.getJmbg());
                pw.write("\r\n");
                pw.write(student.getIndeks() + "");
                pw.write("\r\n");
                pw.write(student.getNivoStudija().toString());
                pw.write("\r\n");
                pw.write(student.getProsek() + "");
                pw.write("\r\n");
            }

        }
        scanner.close();
        pw.close();
        
        //ovde imamo prepisan fajl studenti.txt u fajl studentiTemp.txt, bez onog koji treba da se brise
        //praznjenje fajla studenti
        pw = new PrintWriter(studenti);
        pw.print("");
        pw.close();
        
        scanner = new Scanner(studentiTemp);
        pw = new PrintWriter(new FileWriter(studenti, true));
        
        //kopiranje iz studentiTemp u studenti
        while(scanner.hasNext()) {
            pw.write(scanner.nextLine());
            pw.write("\r\n");
            pw.write(scanner.nextLine());
            pw.write("\r\n");
            pw.write(scanner.nextLine());
            pw.write("\r\n");
            pw.write(scanner.nextLine());
            pw.write("\r\n");
            pw.write(scanner.nextLine());
            pw.write("\r\n");
        }
        scanner.close();
        pw.close();
        
        //praznjenje fajla studentiTemp
        pw = new PrintWriter(studentiTemp);
        pw.print("");
        pw.close();
        
    }
    
    public static void izmeniStudenta(Student s) throws FileNotFoundException, Exception {
        
        File studenti = new File("studenti.txt");
        File studentiTemp = new File("studentiTemp.txt");
        
        Scanner scanner = new Scanner(studenti);

        PrintWriter pw = new PrintWriter(new FileWriter(studentiTemp, true));
        
        while (scanner.hasNext()) {
            Student student = new Student();
            String[] imeIPrezime = scanner.nextLine().split(" "); //{"Marija", "Stosic"}
            student.setIme(imeIPrezime[0]);
            student.setPrezime(imeIPrezime[1]);
            student.setJmbg(scanner.nextLine());
            student.setIndeks(scanner.nextInt());
            scanner.nextLine();
            String nivoStudija = scanner.nextLine();
            switch (nivoStudija) {
                case "Osnovne studije":
                    student.setNivoStudija(NivoStudija.OSNOVNE_STUDIJE);
                    break;
                case "Master studije":
                    student.setNivoStudija(NivoStudija.MASTER_STUDIJE);
                    break;
                case "Doktorske studije":
                    student.setNivoStudija(NivoStudija.DOKTORSKE_STUDIJE);
                    break;
                default:
                    student.setNivoStudija(null);
                    break;
            }

            student.setProsek(scanner.nextDouble());
            scanner.nextLine();
                        
            if (student.getIndeks() != s.getIndeks()) {
                pw.write(student.getIme() + " " + student.getPrezime());
                pw.write("\r\n");
                pw.write(student.getJmbg());
                pw.write("\r\n");
                pw.write(student.getIndeks() + "");
                pw.write("\r\n");
                pw.write(student.getNivoStudija().toString());
                pw.write("\r\n");
                pw.write(student.getProsek() + "");
                pw.write("\r\n");
            } else {
                //u fajl studentiTemp se upisuje izmenjeni objekat
                //koji je prosledjen metodi iz forme
                pw.write(s.getIme() + " " + s.getPrezime());
                pw.write("\r\n");
                pw.write(s.getJmbg());
                pw.write("\r\n");
                pw.write(s.getIndeks() + "");
                pw.write("\r\n");
                pw.write(s.getNivoStudija().toString());
                pw.write("\r\n");
                pw.write(s.getProsek() + "");
                pw.write("\r\n");
            }

        }
        scanner.close();
        pw.close();
        
        //ovde imamo prepisan fajl studenti.txt u fajl studentiTemp.txt, bez onog koji treba da se brise
        pw = new PrintWriter(studenti);
        pw.print("");
        pw.close();
        
        scanner = new Scanner(studentiTemp);
        pw = new PrintWriter(new FileWriter(studenti, true));
        
        while(scanner.hasNext()) {
            pw.write(scanner.nextLine());
            pw.write("\r\n");
            pw.write(scanner.nextLine());
            pw.write("\r\n");
            pw.write(scanner.nextLine());
            pw.write("\r\n");
            pw.write(scanner.nextLine());
            pw.write("\r\n");
            pw.write(scanner.nextLine());
            pw.write("\r\n");
        }
        scanner.close();
        pw.close();
        
        pw = new PrintWriter(studentiTemp);
        pw.print("");
        pw.close();
        
    }
    
    public static void obrisiPredavacaIzFajla(Predavac p) throws FileNotFoundException, Exception {
        
        File predavaci = new File("predavaci.txt");
        File predavaciTemp = new File("predavaciTemp.txt");
        
        Scanner scanner = new Scanner(predavaci);

        PrintWriter pw = new PrintWriter(new FileWriter(predavaciTemp, true));
        
        while (scanner.hasNext()) {
            Predavac predavac = new Predavac();
            String[] imeIPrezime = scanner.nextLine().split(" "); //{"Marija", "Stosic"}
            predavac.setIme(imeIPrezime[0]);
            predavac.setPrezime(imeIPrezime[1]);
            predavac.setJmbg(scanner.nextLine());
            String zvanje = scanner.nextLine();
            switch (zvanje) {
                case "Redovni profesor":
                    predavac.setZvanjePredavaca(Zvanje.REDOVNI_PROFESOR);
                    break;
                case "Vanredni profesor":
                    predavac.setZvanjePredavaca(Zvanje.VANREDNI_PROFESOR);
                    break;
                case "Docent":
                    predavac.setZvanjePredavaca(Zvanje.DOCENT);
                    break;
                case "Asistent":
                    predavac.setZvanjePredavaca(Zvanje.ASISTENT);
                    break;
                case "Saradnik":
                    predavac.setZvanjePredavaca(Zvanje.SARADNIK);
                    break;
                default:
                    predavac.setZvanjePredavaca(null);
                    break;
            }
            
            predavac.setPlataZaposlenih(scanner.nextDouble());
            scanner.nextLine();
            predavac.setGodineStaza(scanner.nextDouble());
            scanner.nextLine();
            
            if (!predavac.getJmbg().equals(p.getJmbg())) {
                pw.write(predavac.getIme() + " " + predavac.getPrezime());
                pw.write("\r\n");
                pw.write(predavac.getJmbg());
                pw.write("\r\n");
                pw.write(predavac.getZvanjePredavaca() + "");
                pw.write("\r\n");
                pw.write(predavac.getPlataZaposlenih() + "");
                pw.write("\r\n");
                pw.write(predavac.getGodineStaza() + "");
                pw.write("\r\n");
            }
        }
        scanner.close();
        pw.close();
        
        pw = new PrintWriter(predavaci);
        pw.print("");
        pw.close();
        
        scanner = new Scanner(predavaciTemp);
        pw = new PrintWriter(new FileWriter(predavaci, true));
        
        while(scanner.hasNext()) {
            pw.write(scanner.nextLine());
            pw.write("\r\n");
            pw.write(scanner.nextLine());
            pw.write("\r\n");
            pw.write(scanner.nextLine());
            pw.write("\r\n");
            pw.write(scanner.nextLine());
            pw.write("\r\n");
            pw.write(scanner.nextLine());
            pw.write("\r\n");
        }
        scanner.close();
        pw.close();
        
        pw = new PrintWriter(predavaciTemp);
        pw.print("");
        pw.close();
        
    }
    
    public static void obrisiOstZapIzFajla(OstaliZaposleni oz) throws FileNotFoundException, Exception {
        
        File ostaliZaposleni = new File("ostaliZaposleni.txt");
        File ostaliZaposleniTemp = new File("ostaliZaposleniTemp.txt");
        
        Scanner scanner = new Scanner(ostaliZaposleni);

        PrintWriter pw = new PrintWriter(new FileWriter(ostaliZaposleniTemp, true));
        
        while (scanner.hasNext()) {
            OstaliZaposleni ostaliZap = new OstaliZaposleni();
            String[] imeIPrezime = scanner.nextLine().split(" "); //{"Marija", "Stosic"}
            ostaliZap.setIme(imeIPrezime[0]);
            ostaliZap.setPrezime(imeIPrezime[1]);
            ostaliZap.setJmbg(scanner.nextLine());
            String sluzba = scanner.nextLine();
            switch (sluzba) {
                case "Studentska sluzba":
                    ostaliZap.setSluzbaOstalihZaposlenih(Sluzba.STUDENTSKA_SLUZBA);
                    break;
                case "Pravna sluzba":
                    ostaliZap.setSluzbaOstalihZaposlenih(Sluzba.PRAVNA_SLUZBA);
                    break;
                case "IT sluzba":
                    ostaliZap.setSluzbaOstalihZaposlenih(Sluzba.IT_SLUZBA);
                    break;
                default:
                    ostaliZap.setSluzbaOstalihZaposlenih(null);
                    break;
            }
            
            ostaliZap.setPlataZaposlenih(scanner.nextDouble());
            scanner.nextLine();
            ostaliZap.setGodineStaza(scanner.nextDouble());
            scanner.nextLine();
            
            System.out.println("Ostali zaposleni: " + ostaliZap);
            
            if (!ostaliZap.getJmbg().equals(oz.getJmbg())) {
                pw.write(ostaliZap.getIme() + " " + ostaliZap.getPrezime());
                pw.write("\r\n");
                pw.write(ostaliZap.getJmbg());
                pw.write("\r\n");
                pw.write(ostaliZap.getSluzbaOstalihZaposlenih()+ "");
                pw.write("\r\n");
                pw.write(ostaliZap.getPlataZaposlenih() + "");
                pw.write("\r\n");
                pw.write(ostaliZap.getGodineStaza() + "");
                pw.write("\r\n");
            }
        }
        scanner.close();
        pw.close();
        
        pw = new PrintWriter(ostaliZaposleni);
        pw.print("");
        pw.close();
        
        scanner = new Scanner(ostaliZaposleniTemp);
        pw = new PrintWriter(new FileWriter(ostaliZaposleni, true));
        
        while(scanner.hasNext()) {
            pw.write(scanner.nextLine());
            pw.write("\r\n");
            pw.write(scanner.nextLine());
            pw.write("\r\n");
            pw.write(scanner.nextLine());
            pw.write("\r\n");
            pw.write(scanner.nextLine());
            pw.write("\r\n");
            pw.write(scanner.nextLine());
            pw.write("\r\n");
        }
        scanner.close();
        pw.close();
        
        pw = new PrintWriter(ostaliZaposleniTemp);
        pw.print("");
        pw.close();
        
    }
    
    public static void izmeniPredavaca(Predavac p, String stariJmbg) throws FileNotFoundException, Exception {
        
        File predavaci = new File("predavaci.txt");
        File predavaciTemp = new File("predavaciTemp.txt");
        
        Scanner scanner = new Scanner(predavaci);

        PrintWriter pw = new PrintWriter(new FileWriter(predavaciTemp, true));
        
        while (scanner.hasNext()) {
            Predavac predavac = new Predavac();
            String[] imeIPrezime = scanner.nextLine().split(" "); //{"Marija", "Stosic"}
            predavac.setIme(imeIPrezime[0]);
            predavac.setPrezime(imeIPrezime[1]);
            predavac.setJmbg(scanner.nextLine());
            String sluzba = scanner.nextLine();
            switch (sluzba) {
                case "Redovni profesor":
                    predavac.setZvanjePredavaca(Zvanje.REDOVNI_PROFESOR);
                    break;
                case "Vanredni profesor":
                    predavac.setZvanjePredavaca(Zvanje.VANREDNI_PROFESOR);
                    break;
                case "Docent":
                    predavac.setZvanjePredavaca(Zvanje.DOCENT);
                    break;
                case "Asistent":
                    predavac.setZvanjePredavaca(Zvanje.ASISTENT);
                    break;
                case "Saradnik":
                    predavac.setZvanjePredavaca(Zvanje.SARADNIK);
                    break;
                default:
                    predavac.setZvanjePredavaca(null);
                    break;
            }
            
            predavac.setPlataZaposlenih(scanner.nextDouble());
            scanner.nextLine();
            predavac.setGodineStaza(scanner.nextDouble());
            scanner.nextLine();
            
            //promenljiva stariJmbg sluzi kako bismo mogli da uporedimo jmbg objekata iz fajla
            //sa jmbg-om objekta koji je bio pre izmene, jer moze doci do situacije
            //da je u izmenjenom objektu izmenjen bas jmbg i onda ne mozemo da pronadjemo
            //koji je to bio objekat kako bismo ga zamenili u fajlu
            if (!predavac.getJmbg().equals(stariJmbg)) {
                pw.write(predavac.getIme() + " " + predavac.getPrezime());
                pw.write("\r\n");
                pw.write(predavac.getJmbg());
                pw.write("\r\n");
                pw.write(predavac.getZvanjePredavaca() + "");
                pw.write("\r\n");
                pw.write(predavac.getPlataZaposlenih() + "");
                pw.write("\r\n");
                pw.write(predavac.getGodineStaza() + "");
                pw.write("\r\n");
            } else {
                pw.write(p.getIme() + " " + p.getPrezime());
                pw.write("\r\n");
                pw.write(p.getJmbg());
                pw.write("\r\n");
                pw.write(p.getZvanjePredavaca() + "");
                pw.write("\r\n");
                pw.write(p.getPlataZaposlenih() + "");
                pw.write("\r\n");
                pw.write(p.getGodineStaza() + "");
                pw.write("\r\n");
            }
        }
        scanner.close();
        pw.close();
        
        pw = new PrintWriter(predavaci);
        pw.print("");
        pw.close();
        
        scanner = new Scanner(predavaciTemp);
        pw = new PrintWriter(new FileWriter(predavaci, true));
        
        while(scanner.hasNext()) {
            pw.write(scanner.nextLine());
            pw.write("\r\n");
            pw.write(scanner.nextLine());
            pw.write("\r\n");
            pw.write(scanner.nextLine());
            pw.write("\r\n");
            pw.write(scanner.nextLine());
            pw.write("\r\n");
            pw.write(scanner.nextLine());
            pw.write("\r\n");
        }
        scanner.close();
        pw.close();
        
        pw = new PrintWriter(predavaciTemp);
        pw.print("");
        pw.close();
        
    }
    
    public static void izmeniOstalogZaposlenog(OstaliZaposleni oz, String stariJmbg) throws FileNotFoundException, Exception {
        
        System.out.println("Ostali zaposleni: " + oz);
        System.out.println("Stari jmbg: " + stariJmbg);
        
        File ostaliZaposleni = new File("ostaliZaposleni.txt");
        File ostaliZaposleniTemp = new File("ostaliZaposleniTemp.txt");
        
        Scanner scanner = new Scanner(ostaliZaposleni);

        PrintWriter pw = new PrintWriter(new FileWriter(ostaliZaposleniTemp, true));
        
        while (scanner.hasNext()) {
            OstaliZaposleni ostZap = new OstaliZaposleni();
            String[] imeIPrezime = scanner.nextLine().split(" "); //{"Marija", "Stosic"}
            ostZap.setIme(imeIPrezime[0]);
            ostZap.setPrezime(imeIPrezime[1]);
            ostZap.setJmbg(scanner.nextLine());
            String sluzba = scanner.nextLine();
            switch (sluzba) {
                case "Studentska sluzba":
                    ostZap.setSluzbaOstalihZaposlenih(Sluzba.STUDENTSKA_SLUZBA);
                    break;
                case "Pravna sluzba":
                    ostZap.setSluzbaOstalihZaposlenih(Sluzba.PRAVNA_SLUZBA);
                    break;
                case "IT sluzba":
                    ostZap.setSluzbaOstalihZaposlenih(Sluzba.IT_SLUZBA);
                    break;
                default:
                    ostZap.setSluzbaOstalihZaposlenih(null);
                    break;
            }
            
            ostZap.setPlataZaposlenih(scanner.nextDouble());
            scanner.nextLine();
            ostZap.setGodineStaza(scanner.nextDouble());
            scanner.nextLine();
            
            
            if (!ostZap.getJmbg().equals(stariJmbg)) {
                pw.write(ostZap.getIme() + " " + ostZap.getPrezime());
                pw.write("\r\n");
                pw.write(ostZap.getJmbg());
                pw.write("\r\n");
                pw.write(ostZap.getSluzbaOstalihZaposlenih()+ "");
                pw.write("\r\n");
                pw.write(ostZap.getPlataZaposlenih() + "");
                pw.write("\r\n");
                pw.write(ostZap.getGodineStaza() + "");
                pw.write("\r\n");
            } else {
                pw.write(oz.getIme() + " " + oz.getPrezime());
                pw.write("\r\n");
                pw.write(oz.getJmbg());
                pw.write("\r\n");
                pw.write(oz.getSluzbaOstalihZaposlenih() + "");
                pw.write("\r\n");
                pw.write(oz.getPlataZaposlenih() + "");
                pw.write("\r\n");
                pw.write(oz.getGodineStaza() + "");
                pw.write("\r\n");
            }
        }
        scanner.close();
        pw.close();
        
        pw = new PrintWriter(ostaliZaposleni);
        pw.print("");
        pw.close();
        
        scanner = new Scanner(ostaliZaposleniTemp);
        pw = new PrintWriter(new FileWriter(ostaliZaposleni, true));
        
        while(scanner.hasNext()) {
            pw.write(scanner.nextLine());
            pw.write("\r\n");
            pw.write(scanner.nextLine());
            pw.write("\r\n");
            pw.write(scanner.nextLine());
            pw.write("\r\n");
            pw.write(scanner.nextLine());
            pw.write("\r\n");
            pw.write(scanner.nextLine());
            pw.write("\r\n");
        }
        scanner.close();
        pw.close();
        
        pw = new PrintWriter(ostaliZaposleniTemp);
        pw.print("");
        pw.close();
        
    }
}
