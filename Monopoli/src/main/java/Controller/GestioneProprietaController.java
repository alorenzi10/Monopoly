package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import Model.Cantiere;
import Model.GruppoColore;
import Model.Monopoly;
import Model.Tabellone;
import View.GestioneProprietaView;
import View.MonopolyGUI;
import View.SchermataDiGioco;

public class GestioneProprietaController {
	
	GestioneProprietaView gestioneProprieta;
	Monopoly monopoly;
	MonopolyGUI monopolyGUI;
	Tabellone tabellone;
	SchermataDiGioco frame;
	GruppoColore colore;
	ArrayList<Cantiere> prova;
	boolean demolisci=false;
	
	public GestioneProprietaController(SchermataDiGioco frame, MonopolyGUI monopolyGUI, Monopoly monopoly) {
		this.monopolyGUI=monopolyGUI;
		this.monopoly=monopoly;
		this.tabellone=monopoly.getTabellone();
		this.frame=frame;
		gestioneProprieta=new GestioneProprietaView(frame);
		gestioneProprieta.Scelte();
		
		gestioneProprieta.addBtnCostruisci(new BtnCostruisci());
		gestioneProprieta.addBtnDemolisci(new BtnDemolisci());
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
		gestioneProprieta.addBtn1(new Btn1());
		gestioneProprieta.addBtn2(new Btn2());
		gestioneProprieta.addBtn3(new Btn3());
		
		
	}	
	
	private class BtnCostruisci implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			gestioneProprieta.Costruisci();
		}
	}
	
	private class BtnDemolisci implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			gestioneProprieta.Demolisci();
			demolisci=true;
		}
	}

	private class BtnMarrone implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			gestioneProprieta.Colore("marrone", demolisci);
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
			gestioneProprieta.Colore("azzurro", demolisci);
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
			gestioneProprieta.Colore("viola", demolisci);
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
			gestioneProprieta.Colore("arancione", demolisci);
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
			gestioneProprieta.Colore("rosso", demolisci);
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
			gestioneProprieta.Colore("giallo", demolisci);
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
			gestioneProprieta.Colore("verde", demolisci);
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
			gestioneProprieta.Colore("blu", demolisci);
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
			demolisci=false;
		
		}
	}
	
	private class BtnIndietro1 implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(demolisci) {
				gestioneProprieta.Demolisci();
			}
			else {
				gestioneProprieta.Costruisci();
			}
		}
	}
	private class BtnFine implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			gestioneProprieta.Fine();
			monopolyGUI.buttonsState(true);
		}
	}
	
	private class Btn1 implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			Cantiere p=prova.get(0);
			if(demolisci) {
				monopoly.demolisci(p);
			}else {
			monopoly.costruisci(p);
			}
		}
	}
	
	
	private class Btn2 implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			Cantiere p=prova.get(1);
			if(demolisci) {
				monopoly.demolisci(p);
			}else {
			monopoly.costruisci(p);
			}
		}
	}
	
	
	private class Btn3 implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			Cantiere p=prova.get(2);
			if(demolisci) {
				monopoly.demolisci(p);
			}else {
			monopoly.costruisci(p);
			}
		}
	}
	
	
	
}
