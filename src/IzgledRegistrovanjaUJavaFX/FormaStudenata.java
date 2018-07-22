/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IzgledRegistrovanjaUJavaFX;

import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import projektnizadatak.ki106.NivoStudija;
import projektnizadatak.ki106.Student;
import radsafajlovima.RadSaFajlovima;

/**
 *
 * @author Marija
 */
public class FormaStudenata extends Application {

    VBox forma = new VBox(7);
    ObservableList<Student> listaStudenata;
    TableView<Student> table;
    TextField tfIme, tfPrezime, tfJmbg, tfIndeks, tfProsek;
    ComboBox<NivoStudija> combo;
    Button btnSacuvajIzmeni;

    @Override
    public void start(Stage primaryStage) throws Exception {

        Alert a = new Alert(Alert.AlertType.INFORMATION);
        Alert a1 = new Alert(Alert.AlertType.ERROR);

        Label lIme = new Label("Ime:");
        Label lPrezime = new Label("Prezime: ");
        Label lJmbg = new Label("JMBG: ");
        Label lNivoStudija = new Label("Nivo: ");
        Label lIndeks = new Label("Indeks: ");
        Label lProsek = new Label("Prosek: ");
        tfIme = new TextField();
        tfPrezime = new TextField();
        tfJmbg = new TextField();
        tfIndeks = new TextField();
        tfIndeks.setText(RadSaFajlovima.vratiMaksimalanBrojIndeksaIzFajla()+1+"");//setuje se vrednost ovog polja na naredni slobodan indeks
        tfIndeks.setDisable(true);//zabranjuje se izmena ovog polja
        tfProsek = new TextField();
        
        ObservableList<NivoStudija> nivo = FXCollections.observableArrayList((NivoStudija.OSNOVNE_STUDIJE),(NivoStudija.MASTER_STUDIJE),(NivoStudija.DOKTORSKE_STUDIJE));
        combo = new ComboBox<>(nivo);
        combo.getSelectionModel().select(0);
        btnSacuvajIzmeni = new Button("Sačuvaj");
        btnSacuvajIzmeni.setStyle("-fx-text-fill:#464f5e");
        
        btnSacuvajIzmeni.setOnAction(e -> {

            Student student1 = new Student();
            try {
                if(tfIme.getText().isEmpty() || tfPrezime.getText().isEmpty()) {
                    a1.setContentText("Morate popuniti ime i prezime!");
                    a1.show();
                } else {
                    student1.setIme(tfIme.getText());
                    student1.setPrezime(tfPrezime.getText());
                    student1.setJmbg(tfJmbg.getText());
                    student1.setNivoStudija(combo.getValue());
                    student1.setIndeks(Integer.valueOf(tfIndeks.getText()));
                    try {
                        student1.setProsek(Double.valueOf(tfProsek.getText()));
                    } catch(Exception ex) {
                        throw new Exception("Neispravna vrednost za polje prosek!");
                    }

                    System.out.println(student1.toString());

                    a.setTitle("Success");

                    if(btnSacuvajIzmeni.getText().equals("Izmeni")) {
                        RadSaFajlovima.izmeniStudenta(student1);
                        a.setContentText("Uspesno ste izmenili podatke o studentu!");
                    } else {
                        RadSaFajlovima.upisStudentaUFajl(student1);
                        a.setContentText("Uspesno ste izvrsili registraciju!");
                    }
                    a.show();
                    osveziListu();

                    ocistiFormu();
                }
            } catch (Exception ex) {
                a1.setTitle("Error");
                a1.setContentText(ex.getMessage());
                a1.show();
            }

        });
        Button btnObrisi = new Button("Obriši");
        btnObrisi.setStyle("-fx-text-fill:#464f5e");

        btnObrisi.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Student s = table.getSelectionModel().getSelectedItem();
                if(s != null) {
                    try {
                        RadSaFajlovima.obrisiStudentaIzFajla(s);
                        osveziListu();
                        ocistiFormu();
                    } catch (Exception ex) {
                        Logger.getLogger(FormaStudenata.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    System.out.println("Obrisano je");
                } else {
                    a1.setContentText("Morate selektovati studenta iz liste kako biste izvrsili brisanje.");
                    a1.show();
                }

            }
        });
        Button nazad1 = new Button("Nazad");
        nazad1.setStyle("-fx-text-fill:#464f5e");

        nazad1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                PocetnaStrana r = new PocetnaStrana();
                r.start(primaryStage);
            }
        });
        
        listaStudenata = FXCollections.observableArrayList(RadSaFajlovima.ucitajSveStudenteIzFajla());

        table = new TableView<>(listaStudenata);
        
        //kolone za tabelu
        //u konstruktoru se navode nazivi kolona
        TableColumn imeColumn = new TableColumn("Ime");
        TableColumn prezimeColumn = new TableColumn("Prezime");
        TableColumn jmbgColumn = new TableColumn("JMBG");
        TableColumn nivoStudijaColumn = new TableColumn("Nivo studija");
        TableColumn indeksColumn = new TableColumn("Broj indeksa");
        TableColumn prosekColumn = new TableColumn("Prosek");
        
        //popunjavanje tabele vrednostima
        imeColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("ime"));
        prezimeColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("prezime"));
        jmbgColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("jmbg"));
        nivoStudijaColumn.setCellValueFactory(new PropertyValueFactory<Student, NivoStudija>("nivoStudija"));
        indeksColumn.setCellValueFactory(new PropertyValueFactory<Student, Integer>("indeks"));
        prosekColumn.setCellValueFactory(new PropertyValueFactory<Student, Double>("prosek"));
        
        table.getColumns().addAll(imeColumn, prezimeColumn, jmbgColumn, nivoStudijaColumn, indeksColumn, prosekColumn);
        
        //kada se klikne dva puta na neki red, forma se popunjava podacima 
        //iz tog objekta i tekst dugmeta postaje "Izmeni"
        table.setRowFactory(t -> {
            TableRow<Student> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Student s = row.getItem();
                    tfIme.setText(s.getIme());
                    tfPrezime.setText(s.getPrezime());
                    tfJmbg.setText(s.getJmbg());
                    combo.setValue(s.getNivoStudija());
                    tfIndeks.setText(s.getIndeks()+"");
                    tfProsek.setText(s.getProsek()+"");
                    btnSacuvajIzmeni.setText("Izmeni");
                }
            });
            return row;
        });
        
        BorderPane pane = new BorderPane();
        forma.getChildren().addAll(lIme, tfIme, lPrezime, tfPrezime, lJmbg, tfJmbg, lNivoStudija, combo,lIndeks,tfIndeks,lProsek,tfProsek, btnSacuvajIzmeni, btnObrisi, nazad1);
        pane.setLeft(forma);
        pane.setRight(table);
        
        table.setPrefWidth(550);
        //table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        pane.setPadding(new Insets(15));
        pane.setStyle("-fx-base: #ccd9ff;");

        Scene scene = new Scene(pane, 850, 700);

        primaryStage.setTitle("FORMA STUDENATA");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void ocistiFormu() throws FileNotFoundException {
        tfIme.clear();
        tfPrezime.clear();
        tfJmbg.clear();
        combo.setValue(NivoStudija.OSNOVNE_STUDIJE);
        tfIndeks.setText(RadSaFajlovima.vratiMaksimalanBrojIndeksaIzFajla()+1+"");
        tfProsek.clear();
        btnSacuvajIzmeni.setText("Sačuvaj");
    }

    /**
     * @param args the command line arguments
     */

    private void osveziListu() throws Exception {
        listaStudenata = FXCollections.observableArrayList(RadSaFajlovima.ucitajSveStudenteIzFajla());
        table.setItems(listaStudenata);
    }

}
