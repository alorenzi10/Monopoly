package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import Model.Cantiere;
import Model.GruppoColore;
import Model.Tabellone;
import View.GestioneProprietaView;
import View.MonopolyGUI;
import View.SchermataDiGioco;

public class GestioneProprietaController {
	
	GestioneProprietaView gestioneProprieta;
	MonopolyGUI monopolyGUI;
	Tabellone tabellone;
	SchermataDiGioco frame;
	GruppoColore colore;
	ArrayList<Cantiere> prova;
	public GestioneProprietaController(SchermataDiGioco frame, MonopolyGUI monopolyGUI, Tabellone tabellone) {
		this.monopolyGUI=monopolyGUI;
		this.tabellone=tabellone;
		this.frame=frame;
		gestioneProprieta=new GestioneProprietaView(frame);
		gestioneProprieta.Scelte();
		
		gestioneProprieta.addBtnCostruisci(new BtnCostruisci());
		gestioneProprieta.addBtnFine(new BtnFine());
		gestioneProprieta.addBtnMarrone(new BtnMarrone());
		gestioneProprieta.addBtnAzzurro(new BtnAzzurro());
		gestioneProprieta.addBtnViola(new BtnViola());
		gestioneProprieta.addBtnArancio(new BtnArancione());
		gestioneProprieta.addBtnRosso(new BtnRosso());
		gestioneProprieta.addBtnGiallo(new BtnGiallo());
		gestioneProprieta.addBtnVerde(new BtnVerde());
		gestioneProprieta.addBtnBlu(new BtnBlu());
		
		gestioneProprieta.addBtnIndietro(new BtnIndietro());
		gestioneProprieta.addBtnIndietro1(new BtnIndietro1());
		
	}	
	
	private class BtnCostruisci implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			gestioneProprieta.Costruisci();
		}
	}
	private class BtnMarrone implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			gestioneProprieta.Colore("marrone");
			colore=tabellone.getMarrone();
			prova=colore.getMembri();
			for(Cantiere p: prova) {
				gestioneProprieta.aggiungiBottone(p.getNome());
				
			}
		}
	}
	private class BtnAzzurro implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			gestioneProprieta.Colore("azzurro");
			colore=tabellone.getAzzurro();
			prova=colore.getMembri();
			for(Cantiere p: prova) {
				gestioneProprieta.aggiungiBottone(p.getNome());
				
			}
		}
	}
	private class BtnViola implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			gestioneProprieta.Colore("viola");
			colore=tabellone.getViola();
			prova=colore.getMembri();
			for(Cantiere p: prova) {
				gestioneProprieta.aggiungiBottone(p.getNome());
				
			}
		}
	}

	private class BtnArancione implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			gestioneProprieta.Colore("arancione");
			colore=tabellone.getArancione();
			prova=colore.getMembri();
			for(Cantiere p: prova) {
				gestioneProprieta.aggiungiBottone(p.getNome());
				
			}
		}
	}
	private class BtnRosso implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			gestioneProprieta.Colore("rosso");
			colore=tabellone.getRosso();
			prova=colore.getMembri();
			for(Cantiere p: prova) {
				gestioneProprieta.aggiungiBottone(p.getNome());
				
			}
		}
	}
	private class BtnGiallo implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			gestioneProprieta.Colore("Giallo");
			colore=tabellone.getGiallo();
			prova=colore.getMembri();
			for(Cantiere p: prova) {
				gestioneProprieta.aggiungiBottone(p.getNome());
				
			}
		}
	}
	private class BtnVerde implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			gestioneProprieta.Colore("verde");
			colore=tabellone.getVerde();
			prova=colore.getMembri();
			for(Cantiere p: prova) {
				gestioneProprieta.aggiungiBottone(p.getNome());
				
			}
		}
	}
	private class BtnBlu implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			gestioneProprieta.Colore("blu");
			colore=tabellone.getBlu();
			prova=colore.getMembri();
			for(Cantiere p: prova) {
				gestioneProprieta.aggiungiBottone(p.getNome());
				
			}
		}
	}
	
	private class BtnIndietro implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			gestioneProprieta.Fine();
			gestioneProprieta.Scelte();
		
		}
	}
	
	private class BtnIndietro1 implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			
			gestioneProprieta.Costruisci();
		}
	}
	private class BtnFine implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			gestioneProprieta.Fine();
			monopolyGUI.buttonsState(true);
		}
	}
	
	
	
}
