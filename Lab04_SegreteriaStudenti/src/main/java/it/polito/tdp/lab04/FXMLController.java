package it.polito.tdp.lab04;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class FXMLController {
	
	Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<Corso> DDCorsi;

    @FXML
    private Button btnCercaIscritti;

    @FXML
    private TextField txtMatricola;

    @FXML
    private Button btnCercaMatricola;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCognome;

    @FXML
    private Button btnCercaCorsi;

    @FXML
    private Button btnIscrivi;

    @FXML
    private TextArea areaTxt;

    @FXML
    private Button btnReset;

    @FXML
    void doCercaCorsi(ActionEvent event) {
    	int matricola = Integer.parseInt(txtMatricola.getText());
    	Corso corsoSelezionato = DDCorsi.getValue();
    	if ( corsoSelezionato == null) {
    		String res = "";
        	Studente s = model.getStudente(matricola);
        	if (s == null) {
        		areaTxt.setText("Studente non trovato");
        		return;
        	}
        	for (Corso c: model.getCorsiStudente(s)) {
        		res += c.getCodiceInsegnamento() + "\t" + c.getNome() + "\n";
        	}
        	
        	areaTxt.setText(res.substring(0, res.length() - 1));
    	} else {
    		if(model.studenteIscrittoAlCorso(matricola, corsoSelezionato)) {
    			areaTxt.setText("Lo studente " + matricola + " è iscritto al corso " + corsoSelezionato);
    		} else {
    			areaTxt.setText("Lo studente " + matricola + " NON è iscritto al corso " + corsoSelezionato);

    		}
    		
    	}
    	
    }

    @FXML
    void doCercaIscritti(ActionEvent event) {
    	String res = "";
    	if (DDCorsi.getValue() != null) {
	    	for (Studente s: model.getStudentiIscritti(DDCorsi.getValue())) {
	    		res += s.getMatricola() + "\t" + s.getNome() + " " + s.getCognome() + "\n";
	    	}
    	} else {
    		res = "Corso non selezionato!\n";
    	}
    	
    	areaTxt.setText(res.substring(0, res.length() - 1));

    }

    @FXML
    void doCercaMatricola(ActionEvent event) {
    	int matricola = Integer.parseInt(txtMatricola.getText());
    	Studente s = model.getStudente(matricola);
    
    	if (s != null) {
    		txtNome.setText(s.getNome());
    		txtCognome.setText(s.getCognome());
    	} else {
    		txtNome.setText("MATRICOLA NON TROVATA");
    		txtCognome.clear();
    	}
    }

    @FXML
    void doIscrivi(ActionEvent event) {
    	int matricola = Integer.parseInt(txtMatricola.getText());
    	Studente s = model.getStudente(matricola);
    	
    	if (s!=null) {
    		if (model.iscriviStudente(matricola, DDCorsi.getValue())) {
    			areaTxt.setText("Iscrizione avvenuta con successo");
    		} else {
    			areaTxt.setText("L'iscrizione NON è andata a buon fine");

    		}
    	} else {
    		txtNome.setText("MATRICOLA NON TROVATA");
    		txtCognome.clear();
    	}
    }

    @FXML
    void doReset(ActionEvent event) {
    	areaTxt.clear();
    	txtNome.clear();
    	txtCognome.clear();
    	txtMatricola.clear();
    	DDCorsi.setValue(null);
    }

    @FXML
    void initialize() {    	
        assert DDCorsi != null : "fx:id=\"DDCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCercaIscritti != null : "fx:id=\"btnCercaIscritti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCercaMatricola != null : "fx:id=\"btnCercaMatricola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert areaTxt != null : "fx:id=\"areaTxt\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Scene.fxml'.";
        
        btnCercaMatricola.setStyle("-fx-background-color: #2B580C;");
        
    }
    
    public void setModel(Model model) {
    	this.model = model;
    	
    	DDCorsi.getItems().add(null);
    	ArrayList<Corso> corsi = (ArrayList<Corso>) model.getCorsi();
        for (Corso c: corsi) {
        	DDCorsi.getItems().add(c);
        }
        
    }
}
