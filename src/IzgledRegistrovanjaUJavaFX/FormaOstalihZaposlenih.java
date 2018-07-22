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
import projektnizadatak.ki106.OstaliZaposleni;
import projektnizadatak.ki106.Sluzba;
import projektnizadatak.ki106.Student;
import projektnizadatak.ki106.Zvanje;
import radsafajlovima.RadSaFajlovima;

/**
 *
 * @author Marija
 */
public class FormaOstalihZaposlenih extends Application {
    VBox vBox1 = new VBox(7);
    ObservableList<OstaliZaposleni> listaOstalihZaposlenih;
    TableView<OstaliZaposleni> table;
    TextField tfIme, tfPrezime, tfJmbg, tfSluzba, tfPlata, tfStaz;
    ComboBox<Sluzba> combo;
    Button potvrdi1;
    String stariJmbg;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        Alert a1 = new Alert(Alert.AlertType.ERROR);

        Label lIme = new Label("Ime:");
        Label lPrezime = new Label("Prezime: ");
        Label lJmbg = new Label("JMBG: ");
        Label lSluzba = new Label("Služba: ");
        Label lPlata = new Label("Plata: ");
        Label lStaz = new Label("Staz: ");
        tfIme = new TextField();
        tfPrezime = new TextField();
        tfJmbg = new TextField();
        
        tfPlata = new TextField();
        tfStaz = new TextField();

        ObservableList<Sluzba> sluzbe = FXCollections.observableArrayList((Sluzba.STUDENTSKA_SLUZBA), (Sluzba.PRAVNA_SLUZBA), (Sluzba.IT_SLUZBA));
        combo = new ComboBox<>(sluzbe);
        combo.getSelectionModel().select(0);
        potvrdi1 = new Button("Sačuvaj");
        potvrdi1.setStyle("-fx-text-fill:#464f5e");
        potvrdi1.setOnAction(e -> {

            OstaliZaposleni ostaliZaposleni = new OstaliZaposleni();
            if(tfIme.getText().isEmpty() || tfPrezime.getText().isEmpty()) {
                a1.setContentText("Morate popuniti ime i prezime!");
                a1.show();
            } else {
                try {
                    ostaliZaposleni.setIme(tfIme.getText());
                    ostaliZaposleni.setPrezime(tfPrezime.getText());
                    ostaliZaposleni.setJmbg(tfJmbg.getText());
                    ostaliZaposleni.setSluzbaOstalihZaposlenih(combo.getValue());
                    try {
                        if(Double.valueOf(tfPlata.getText()) >= 0) {
                            ostaliZaposleni.setPlataZaposlenih(Double.valueOf(tfPlata.getText()));
                        } else {
                            throw new Exception("Iznos za polje plata mora biti pozitivan!");
                        }
                    } catch(Exception ex) {
                        throw new Exception("Neispravna vrednost za polje plata!");
                    }
                    try {
                        if(Double.valueOf(tfStaz.getText()) >= 0) {
                            ostaliZaposleni.setGodineStaza(Double.valueOf(tfStaz.getText()));
                        } else {
                            throw new Exception("Staz mora biti pozitivan!");
                        }
                    } catch(Exception ex) {
                        throw new Exception("Neispravna vrednost za polje staz!");
                    }
                    System.out.println(ostaliZaposleni.toString());

                    if (potvrdi1.getText().equals("Izmeni")) {
                        RadSaFajlovima.izmeniOstalogZaposlenog(ostaliZaposleni, stariJmbg);
                        a.setContentText("Uspesno ste izmenili podatke o predavacu!");
                    } else {
                        RadSaFajlovima.upisOstalihZaposlenihUFajl(ostaliZaposleni);
                        a.setContentText("Uspesno ste izvrsili registraciju!");
                    }
                    a.setTitle("Success");
                    a.show();
                    osveziListu();

                    ocistiFormu();

                } catch (Exception ex) {
                    a1.setTitle("Error");
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
                OstaliZaposleni oz = table.getSelectionModel().getSelectedItem();
                if (oz != null) {
                    try {
                    RadSaFajlovima.obrisiOstZapIzFajla(oz);
                    osveziListu();
                    ocistiFormu();
                } catch (Exception ex) {
                    Logger.getLogger(FormaOstalihZaposlenih.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("Obrisano je");
                } else {
                    a1.setContentText("Morate selektovati zaposlenog iz liste kako biste izvrsili brisanje.");
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

        listaOstalihZaposlenih = FXCollections.observableArrayList(RadSaFajlovima.ucitajSveOstaleZaposleneIzFajla());

        table = new TableView<>(listaOstalihZaposlenih);

        TableColumn imeColumn = new TableColumn("Ime");
        TableColumn prezimeColumn = new TableColumn("Prezime");
        TableColumn jmbgColumn = new TableColumn("JMBG");
        TableColumn sluzbaColumn = new TableColumn("Zvanje");
        TableColumn plataColumn = new TableColumn("Plata");
        TableColumn stazColumn = new TableColumn("Staž");

        imeColumn.setCellValueFactory(new PropertyValueFactory<OstaliZaposleni, String>("ime"));
        prezimeColumn.setCellValueFactory(new PropertyValueFactory<OstaliZaposleni, String>("prezime"));
        jmbgColumn.setCellValueFactory(new PropertyValueFactory<OstaliZaposleni, String>("jmbg"));
        sluzbaColumn.setCellValueFactory(new PropertyValueFactory<OstaliZaposleni, Sluzba>("sluzbaOstalihZaposlenih"));
        plataColumn.setCellValueFactory(new PropertyValueFactory<OstaliZaposleni, Double>("plataZaposlenih"));
        stazColumn.setCellValueFactory(new PropertyValueFactory<OstaliZaposleni, Double>("godineStaza"));

        table.getColumns().addAll(imeColumn, prezimeColumn, jmbgColumn, sluzbaColumn, plataColumn, stazColumn);

        //edit
        table.setRowFactory(t -> {
            TableRow<OstaliZaposleni> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    OstaliZaposleni oz = row.getItem();
                    tfIme.setText(oz.getIme());
                    tfPrezime.setText(oz.getPrezime());
                    stariJmbg = oz.getJmbg();
                    tfJmbg.setText(oz.getJmbg());
                    combo.setValue(oz.getSluzbaOstalihZaposlenih());
                    tfPlata.setText(oz.getPlataZaposlenih() + "");
                    tfStaz.setText(oz.getGodineStaza() + "");
                    potvrdi1.setText("Izmeni");
                }
            });
            return row;
        });

        BorderPane pane = new BorderPane();
        vBox1.getChildren().addAll(lIme, tfIme, lPrezime, tfPrezime, lJmbg, tfJmbg, lSluzba, combo, lPlata, tfPlata, lStaz, tfStaz, potvrdi1, obrisi1, nazad1);
        pane.setLeft(vBox1);
        pane.setRight(table);

        table.setPrefWidth(550);
        //table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        pane.setPadding(new Insets(15));
        pane.setStyle("-fx-base: #ccd9ff;");

        Scene scene = new Scene(pane, 850, 700);

        primaryStage.setTitle("FORMA OSTALIH ZAPOSLENIH");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void ocistiFormu() throws FileNotFoundException {
        tfIme.clear();
        tfPrezime.clear();
        tfJmbg.clear();
        combo.setValue(Sluzba.STUDENTSKA_SLUZBA);
        tfPlata.clear();
        tfStaz.clear();
        potvrdi1.setText("Sačuvaj");
    }

    /**
     * @param args the command line arguments
     */
    private void osveziListu() throws Exception {
        listaOstalihZaposlenih = FXCollections.observableArrayList(RadSaFajlovima.ucitajSveOstaleZaposleneIzFajla());
        table.setItems(listaOstalihZaposlenih);
    }

}
