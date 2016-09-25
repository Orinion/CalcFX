/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CalcFX;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jakob
 */
public class MainWindowController implements Initializable {

    StringProperty acteq = null;
    boolean maximized = false;
    char operation = ' ';
    StringProperty cashed = null;
    double posX, posY;
    
    // <editor-fold defaultstate="collapsed" desc="Elemente">
    @FXML
    HBox titleBox;
    @FXML
    Label label;
    // </editor-fold>

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cashe();

        titleBox.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Stage act = (Stage) titleBox.getScene().getWindow();
                posX = act.getX() - mouseEvent.getScreenX();
                posY = act.getY() - mouseEvent.getScreenY();
            }
        });

        titleBox.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Stage act = (Stage) titleBox.getScene().getWindow();
                act.setX(event.getScreenX() + posX);
                act.setY(event.getScreenY() + posY);
            }
        });
    }

    void cashe() {
        if (operation != ' ') {
            pressedopE();
        }
        label.textProperty().unbind();
        cashed = acteq;
        acteq = new SimpleStringProperty("0");
        acteq.addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                System.out.println(newValue);

                if (newValue.length() > 1 && newValue.startsWith("0") && !newValue.startsWith("0.")) {
                    acteq.set(newValue.substring(1));//remove '0' 
                }
                if (newValue.startsWith("-0") && !newValue.startsWith("-0.") && newValue.length() > 2) {
                    acteq.set(newValue.replace("-0", "-"));//remove '0'
                }
                if (newValue.startsWith(".")) {
                    acteq.set(0 + newValue);//add 0
                }

                //if(newValue.contains(".") && newValue.endsWith("0"))
                //    acteq.set(newValue.substring(0, newValue.length()-2));//remove "0"
                //if(newValue.endsWith("."))
                //    acteq.set(newValue.substring(0, newValue.length()-2));//remove "."
            }
        });
        label.textProperty().bind(acteq);
    }

    public static double round(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
    
    //<editor-fold defaultstate="collapsed" desc="WindowEvents">

    @FXML
    void close() {
        System.out.println("Test");
        Stage act = (Stage) label.getScene().getWindow();
        act.hide();
        act.close();
        System.exit(0);
    }

    @FXML
    void minimize() {
        Stage act = (Stage) label.getScene().getWindow();
        act.setIconified(true);
    }

    @FXML
    void maximize() {
        Stage act = (Stage) label.getScene().getWindow();
        act.setMaximized(!maximized);
        maximized = !maximized;
    }

//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="NrEvents">
    @FXML
    void pressednr0() {
        acteq.set(acteq.get() + "0");
    }

    @FXML
    void pressednr1() {
        acteq.set(acteq.get() + "1");
    }

    @FXML
    void pressednr2() {
        acteq.set(acteq.get() + "2");
    }

    @FXML
    void pressednr3() {
        acteq.set(acteq.get() + "3");
    }

    @FXML
    void pressednr4() {
        acteq.set(acteq.get() + "4");
    }

    @FXML
    void pressednr5() {
        acteq.set(acteq.get() + "5");
    }

    @FXML
    void pressednr6() {
        acteq.set(acteq.get() + "6");
    }

    @FXML
    void pressednr7() {
        acteq.set(acteq.get() + "7");
    }

    @FXML
    void pressednr8() {
        acteq.set(acteq.get() + "8");
    }

    @FXML
    void pressednr9() {
        acteq.set(acteq.get() + "9");
    }

    @FXML
    void pressednrK() {
        acteq.set(acteq.get() + ".");
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="OpEvents">

    @FXML
    void pressedopC() {
        acteq.set("0");
        cashed = null;
        operation = ' ';
    }

    @FXML
    void pressedopN() {
        String x = acteq.get();
        if (x.startsWith("-")) {
            acteq.set(x.substring(1));
        } else {
            acteq.set("-" + x);
        }
    }

    @FXML
    void pressedopP() {
        cashe();
        operation = 'p';
    }

    @FXML
    void pressedopD() {
        cashe();
        operation = 'd';
    }

    @FXML
    void pressedopM() {
        cashe();
        operation = 'm';
    }

    @FXML
    void pressedopS() {
        cashe();
        operation = 's';
    }

    @FXML
    void pressedopA() {
        cashe();
        operation = 'a';
    }

    @FXML
    void pressedopE() {
        double a, n;
        double e = 0;
        switch (operation) {
            case ' ':
                break;
            case 'a'://add
                a = Double.parseDouble(cashed.get());
                n = Double.parseDouble(acteq.get());
                System.out.println("A:" + a + "n:" + n);
                e = a + n;
                break;
            case 's'://substract
                a = Double.parseDouble(cashed.get());
                n = Double.parseDouble(acteq.get());
                e = a - n;
                break;
            case 'm'://multyply
                a = Double.parseDouble(cashed.get());
                n = Double.parseDouble(acteq.get());
                e = a * n;
                break;
            case 'd'://divide
                a = Double.parseDouble(cashed.get());
                n = Double.parseDouble(acteq.get());
                if (n == 0) {
                    e = 0;
                } else {
                    e = a / n;
                }
                break;
            case 'p'://Percentage
                a = Double.parseDouble(cashed.get());
                n = Double.parseDouble(acteq.get());
                e = Math.pow(a, n);
                break;
        }
        String ergebnis = round(e, 2) + "";

        while (ergebnis.contains(".") && ergebnis.endsWith("0")) {
            ergebnis = ergebnis.substring(0, ergebnis.length() - 2);//remove "0"
        }
        while (ergebnis.endsWith(".")) {
            ergebnis = ergebnis.substring(0, ergebnis.length() - 2);//remove "."
        }
        acteq.set(ergebnis);
        operation = ' ';
        cashed = null;
    }
//</editor-fold>
}
