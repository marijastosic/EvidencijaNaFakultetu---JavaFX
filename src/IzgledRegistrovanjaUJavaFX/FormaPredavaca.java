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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import projektnizadatak.ki106.NivoStudija;
import projektnizadatak.ki106.Predavac;
import projektnizadatak.ki106.Student;
import projektnizadatak.ki106.Zvanje;
import radsafajlovima.RadSaFajlovima;

/**
 *
 * @author Marija
 */
public class FormaPredavaca extends Application {
    VBox vBox1 = new VBox(7);
    ObservableList<Predavac> listaPredavaca;
    TableView<Predavac> table;
    TextField tfIme, tfPrezime, tfJmbg, tfZvanje, tfPlata, tfStaz;
    ComboBox<Zvanje> combo;
    Button potvrdi1;
    String stariJmbg;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        

        Alert a = new Alert(Alert.AlertType.INFORMATION);
        Alert a1 = new Alert(Alert.AlertType.ERROR);

        Label lIme = new Label("Ime:");
        Label lPrezime = new Label("Prezime: ");
        Label lJmbg = new Label("JMBG: ");
        Label lZvanje = new Label("Zvanje: ");
        Label lPlata = new Label("Plata: ");
        Label lStaz = new Label("Staz: ");
        tfIme = new TextField();
        tfPrezime = new TextField();
        tfJmbg = new TextField();
        
        tfPlata = new TextField();
        tfStaz = new TextField();

        ObservableList<Zvanje> zvanje = FXCollections.observableArrayList((Zvanje.REDOVNI_PROFESOR), (Zvanje.VANREDNI_PROFESOR), (Zvanje.DOCENT),(Zvanje.ASISTENT),(Zvanje.SARADNIK));
        combo = new ComboBox<>(zvanje);
        combo.getSelectionModel().select(0);
        potvrdi1 = new Button("Sačuvaj");
        potvrdi1.setStyle("-fx-text-fill:#464f5e");
        potvrdi1.setOnAction(e -> {

            Predavac predavac1 = new Predavac();
            if(tfIme.getText().isEmpty() || tfPrezime.getText().isEmpty()) {
                a1.setHeaderText("Greska u registraciji");
                a1.setContentText("Morate popuniti ime i prezime!");
                a1.show();
            } else {
                try {
                    predavac1.setIme(tfIme.getText());
                    predavac1.setPrezime(tfPrezime.getText());
                    predavac1.setJmbg(tfJmbg.getText());
                    predavac1.setZvanjePredavaca(combo.getValue());
                    try {
                        if(Double.valueOf(tfPlata.getText()) >= 0) {
                            predavac1.setPlataZaposlenih(Double.valueOf(tfPlata.getText()));
                        } else {
                            throw new Exception("Iznos za polje plata mora biti pozitivan!");
                        }
                    } catch(Exception ex) {
                        throw new Exception("Neispravna vrednost za polje plata!");
                    }
                    try {
                        if(Double.valueOf(tfStaz.getText()) >= 0) {
                            predavac1.setGodineStaza(Double.valueOf(tfStaz.getText()));
                        } else {
                            throw new Exception("Staz mora biti pozitivan!");
                        }
                    } catch(Exception ex) {
                        throw new Exception("Neispravna vrednost za polje staz!");
                    }
                    
                    System.out.println(predavac1.toString());

                    if(potvrdi1.getText().equals("Izmeni")) {
                          RadSaFajlovima.izmeniPredavaca(predavac1, stariJmbg);
                            a.setContentText("Uspesno ste izmenili podatke o predavacu!");
                        } else {
                        RadSaFajlovima.upisPredavacaUFajl(predavac1);
                            a.setContentText("Uspesno ste izvrsili registraciju!");
                        }
                    a.setTitle("Success");
                    a.show();
                    osveziListu();

                    ocistiFormu();

                } catch (Exception ex) {
                    a1.setTitle("Error");
                    a1.setHeaderText("Greska u registraciji");
                    a1.setContentText(ex.getMessage());
                    a1.show();
                }
            }
           
        });
        Button obrisi1 = new Button("Obriši");
        obrisi1.setStyle("-fx-text-fill:#464f5e");

        obrisi1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Predavac p = table.getSelectionModel().getSelectedItem();
                if (p != null) {
                    try {
                  RadSaFajlovima.obrisiPredavacaIzFajla(p);
                        osveziListu();
                        ocistiFormu();
                    } catch (Exception ex) {
                        Logger.getLogger(FormaPredavaca.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    System.out.println("Obrisano je");
                } else {
                    a1.setContentText("Morate selektovati predavaca iz liste kako biste izvrsili brisanje.");
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

        listaPredavaca = FXCollections.observableArrayList(RadSaFajlovima.ucitajSvePredavaceIzFajla());

        table = new TableView<>(listaPredavaca);

        TableColumn imeColumn = new TableColumn("Ime");
        TableColumn prezimeColumn = new TableColumn("Prezime");
        TableColumn jmbgColumn = new TableColumn("JMBG");
        TableColumn zvanjeColumn = new TableColumn("Zvanje");
        TableColumn plataColumn = new TableColumn("Plata");
        TableColumn stazColumn = new TableColumn("Staž");

        imeColumn.setCellValueFactory(new PropertyValueFactory<Predavac, String>("ime"));
        prezimeColumn.setCellValueFactory(new PropertyValueFactory<Predavac, String>("prezime"));
        jmbgColumn.setCellValueFactory(new PropertyValueFactory<Predavac, String>("jmbg"));
        zvanjeColumn.setCellValueFactory(new PropertyValueFactory<Predavac, Zvanje>("zvanjePredavaca"));
        plataColumn.setCellValueFactory(new PropertyValueFactory<Predavac, Double>("plataZaposlenih"));
        stazColumn.setCellValueFactory(new PropertyValueFactory<Predavac, Double>("godineStaza"));

        table.getColumns().addAll(imeColumn, prezimeColumn, jmbgColumn, zvanjeColumn, plataColumn, stazColumn);

        table.setRowFactory(t -> {
            TableRow<Predavac> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Predavac p = row.getItem();
                    tfIme.setText(p.getIme());
                    tfPrezime.setText(p.getPrezime());
                    stariJmbg = p.getJmbg();
                    tfJmbg.setText(p.getJmbg());
                    combo.setValue(p.getZvanjePredavaca());
                    tfPlata.setText(p.getPlataZaposlenih() + "");
                    tfStaz.setText(p.getGodineStaza() + "");
                    potvrdi1.setText("Izmeni");
                }
            });
            return row;
        });

        BorderPane pane = new BorderPane();
        vBox1.getChildren().addAll(lIme, tfIme, lPrezime, tfPrezime, lJmbg, tfJmbg, lZvanje, combo, lPlata, tfPlata, lStaz, tfStaz, potvrdi1, obrisi1, nazad1);
        pane.setLeft(vBox1);
        pane.setRight(table);

        table.setPrefWidth(550);
        //table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        pane.setPadding(new Insets(15));
        pane.setStyle("-fx-base: #ccd9ff;");

        Scene scene = new Scene(pane, 850, 700);

        primaryStage.setTitle("FORMA PREDAVAČA");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void ocistiFormu() throws FileNotFoundException {
        tfIme.clear();
        tfPrezime.clear();
        tfJmbg.clear();
        combo.setValue(Zvanje.REDOVNI_PROFESOR);
        tfPlata.clear();
        tfStaz.clear();
        potvrdi1.setText("Sačuvaj");
    }

    /**
     * @param args the command line arguments
     */
    private void osveziListu() throws Exception {
        listaPredavaca = FXCollections.observableArrayList(RadSaFajlovima.ucitajSvePredavaceIzFajla());
        table.setItems(listaPredavaca);
    }

}
