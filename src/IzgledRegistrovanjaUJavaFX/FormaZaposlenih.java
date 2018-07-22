/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IzgledRegistrovanjaUJavaFX;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.stage.Stage;

/**
 *
 * @author Marija
 */
public class FormaZaposlenih extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Button btn1 = new Button();
        btn1.setText("Predavaci");
        btn1.setStyle("-fx-text-fill: BLANCHEDALMOND;");
        btn1.setFont(Font.font("Georgia", FontPosture.ITALIC, 20));
        btn1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FormaPredavaca f = new FormaPredavaca();
                try {
                    f.start(primaryStage);
                } catch (Exception ex) {
                    Logger.getLogger(PocetnaStrana.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        Button btn2 = new Button();
        btn2.setText("Ostali zaposleni");
        btn2.setStyle("-fx-text-fill: BLANCHEDALMOND;");
        btn2.setFont(Font.font("Georgia", FontPosture.ITALIC, 20));
        btn2.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                FormaOstalihZaposlenih f = new FormaOstalihZaposlenih();
                try {
                    f.start(primaryStage);
                } catch (Exception ex) {
                    Logger.getLogger(FormaZaposlenih.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });
        HBox h1 = new HBox();
        h1.setSpacing(30);
        h1.setPadding(new Insets(10, 10, 10, 10));
        h1.getChildren().addAll(btn1, btn2);
        h1.setAlignment(Pos.CENTER);
        StackPane stack = new StackPane();
        stack.getChildren().addAll(h1);
        stack.setStyle("-fx-base: #395cc6;");

        Scene scene = new Scene(stack, 720, 450);

        primaryStage.setTitle("REGISTRACIJA!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
}
