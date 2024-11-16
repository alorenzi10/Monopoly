package View;

import javax.swing.JPanel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

public class GestioneProprietaView extends JPanel {
	
	private JPanel panel_sfondo;
	private JPanel panel_gestione_proprieta;
	
	private JButton btnCostruisci, btnFine, btnDemolisci, btnDisipoteca, btnIpoteca, btnIndietro; //proprietà
	private JButton btnBlu, btnVerde, btnGiallo, btnRosso, btnArancio, btnViola, btnAzzurro, btnMarrone;
	private JButton btn1, btn2, btn3, btnIndietro1;
	int contatoreBottone;
	
	SchermataDiGioco frame;

	public GestioneProprietaView(SchermataDiGioco frame) {
		
		this.frame=frame;
		btnBlu=  new JButton("Blu");
		btnVerde=  new JButton("Verde");
		btnGiallo=   new JButton("Giallo");
		btnRosso=   new JButton("Rosso");
		btnArancio=   new JButton("Arancio");
		btnViola=   new JButton("Viola");
		btnAzzurro=   new JButton("Azzurro");
		btnMarrone= new JButton("Marrone");
		btnIndietro1= new JButton("Indietro1");
		btnIndietro= new JButton("Indietro");
		btn1=new JButton();
		btn2=new JButton();
		btn3=new JButton();
		btnFine = new JButton("Fine");
	
		btnCostruisci = new JButton("Costruisci");

		btnIpoteca = new JButton("Ipoteca");
		
		btnDemolisci = new JButton("Demolisci");
		
		btnDisipoteca = new JButton("Disipoteca");
		

		panel_sfondo = new JPanel();
		panel_sfondo.setBounds(0, 0, 1540, 845);
		panel_sfondo.setOpaque(false);
		panel_sfondo.setLayout(null);
		frame.getContentPane().add(panel_sfondo, 1);
		
	
	}
	
	public void Scelte() {
		

		panel_gestione_proprieta = new JPanel() {
		private static final long serialVersionUID = 1L;
		protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImageIcon icon = new ImageIcon("./icons/sfondo2.png"); //carica l'immagine di sfondo
				Image image = icon.getImage();
				int panelWidth = getWidth();
				int imageWidth = image.getWidth(this);
				int imageHeight = image.getHeight(this);
				int newHeight = (imageHeight * panelWidth) / imageWidth;
				g.drawImage(image, 0, 0, panelWidth, newHeight, this); 
			}
		};
		panel_gestione_proprieta.setLayout(null);
		panel_gestione_proprieta.setBounds(130, 130, 520, 520);
		panel_gestione_proprieta.setBorder(new MatteBorder(5, 5, 5, 5, Color.BLACK)); 
		panel_sfondo.add(panel_gestione_proprieta);
		
		btnFine.setFont(new Font("Monopoly Inline", Font.PLAIN, 20));
		btnFine.setBounds(10, 467, 140, 43);
		panel_gestione_proprieta.add(btnFine);
		
		JLabel lblGestisciLeProprieta = new JLabel("Gestisci le tue proprietà");
		lblGestisciLeProprieta.setHorizontalAlignment(SwingConstants.CENTER);
		lblGestisciLeProprieta.setFont(new Font("Monopoly Inline", Font.PLAIN, 25));
		lblGestisciLeProprieta.setBounds(129, 27, 266, 31);
		panel_gestione_proprieta.add(lblGestisciLeProprieta);
		
		btnCostruisci.setBounds(39, 120, 203, 82);
		btnCostruisci.setFont(new Font("Monopoly Inline", Font.PLAIN, 30));
		panel_gestione_proprieta.add(btnCostruisci);
		
		btnIpoteca.setBounds(281, 120, 203, 82);
		btnIpoteca.setFont(new Font("Monopoly Inline", Font.PLAIN, 30));
		panel_gestione_proprieta.add(btnIpoteca);

		
		btnDemolisci.setBounds(39, 322, 203, 82);
		btnDemolisci.setFont(new Font("Monopoly Inline", Font.PLAIN, 30));
		panel_gestione_proprieta.add(btnDemolisci);

		btnDisipoteca.setBounds(281, 322, 203, 82);
		btnDisipoteca.setFont(new Font("Monopoly Inline", Font.PLAIN, 30));
		panel_gestione_proprieta.add(btnDisipoteca);
		
		panel_gestione_proprieta.repaint();
		panel_gestione_proprieta.revalidate();
	}
	public void Fine() {
		panel_sfondo.remove(panel_gestione_proprieta);
	}
		
	public void Costruisci() {
		
		panel_sfondo.remove(panel_gestione_proprieta);
		
		panel_gestione_proprieta = new JPanel() {
			private static final long serialVersionUID = 1L;
			protected void paintComponent(Graphics g) {
					super.paintComponent(g);
					ImageIcon icon = new ImageIcon("./icons/sfondo2.png"); //carica l'immagine di sfondo
					Image image = icon.getImage();
					int panelWidth = getWidth();
					int imageWidth = image.getWidth(this);
					int imageHeight = image.getHeight(this);
					int newHeight = (imageHeight * panelWidth) / imageWidth;
					g.drawImage(image, 0, 0, panelWidth, newHeight, this); 
				}
			};
			panel_gestione_proprieta.setLayout(null);
			panel_gestione_proprieta.setBounds(130, 130, 520, 520);
			panel_gestione_proprieta.setBorder(new MatteBorder(5, 5, 5, 5, Color.BLACK)); 
			panel_sfondo.add(panel_gestione_proprieta);
		
		JLabel lblGestisciLeProprieta = new JLabel("Costruisci sulle tue proprietà");
		lblGestisciLeProprieta.setHorizontalAlignment(SwingConstants.CENTER);
		lblGestisciLeProprieta.setFont(new Font("Monopoly Inline", Font.PLAIN, 25));
		lblGestisciLeProprieta.setBounds(129, 27, 266, 31);
		panel_gestione_proprieta.add(lblGestisciLeProprieta);
		btnBlu.setBounds(39, 120, 100, 100);
		btnBlu.setFont(new Font("Monopoly Inline", Font.PLAIN, 20));
		panel_gestione_proprieta.add(btnBlu);
		btnVerde.setBounds(140, 120, 100, 100);
		btnVerde.setFont(new Font("Monopoly Inline", Font.PLAIN, 20));
		panel_gestione_proprieta.add(btnVerde);
		btnGiallo.setBounds(240, 120, 100, 100);
		btnGiallo.setFont(new Font("Monopoly Inline", Font.PLAIN, 20));
		panel_gestione_proprieta.add(btnGiallo);
		btnRosso.setBounds(340, 120, 100, 100);
		btnRosso.setFont(new Font("Monopoly Inline", Font.PLAIN, 20));
		panel_gestione_proprieta.add(btnRosso);
		
		btnArancio.setBounds(39, 322, 100, 100);
		btnArancio.setFont(new Font("Monopoly Inline", Font.PLAIN, 20));
		panel_gestione_proprieta.add(btnArancio);
		btnViola.setBounds(140, 322,  100, 100);
		btnViola.setFont(new Font("Monopoly Inline", Font.PLAIN, 20));
		panel_gestione_proprieta.add(btnViola);
		btnAzzurro.setBounds(240, 322, 100, 100);
		btnAzzurro.setFont(new Font("Monopoly Inline", Font.PLAIN, 20));
		panel_gestione_proprieta.add(btnAzzurro);
		btnMarrone.setBounds(340, 322,  100, 100);
		btnMarrone.setFont(new Font("Monopoly Inline", Font.PLAIN, 20));
		panel_gestione_proprieta.add(btnMarrone);
		
		
		btnIndietro.setFont(new Font("Monopoly Inline", Font.PLAIN, 20));
		btnIndietro.setBounds(10, 467, 140, 43);
		panel_gestione_proprieta.add(btnIndietro);
		
		panel_gestione_proprieta.repaint();
		panel_gestione_proprieta.revalidate();
	}
	
	public void Demolisci() {
		panel_sfondo.remove(panel_gestione_proprieta);
		
		panel_gestione_proprieta = new JPanel() {
			private static final long serialVersionUID = 1L;
			protected void paintComponent(Graphics g) {
					super.paintComponent(g);
					ImageIcon icon = new ImageIcon("./icons/sfondo2.png"); //carica l'immagine di sfondo
					Image image = icon.getImage();
					int panelWidth = getWidth();
					int imageWidth = image.getWidth(this);
					int imageHeight = image.getHeight(this);
					int newHeight = (imageHeight * panelWidth) / imageWidth;
					g.drawImage(image, 0, 0, panelWidth, newHeight, this); 
				}
			};
			panel_gestione_proprieta.setLayout(null);
			panel_gestione_proprieta.setBounds(130, 130, 520, 520);
			panel_gestione_proprieta.setBorder(new MatteBorder(5, 5, 5, 5, Color.BLACK)); 
			panel_sfondo.add(panel_gestione_proprieta);
		
		JLabel lblGestisciLeProprieta = new JLabel("Demolisci dalle tue proprietà");
		lblGestisciLeProprieta.setHorizontalAlignment(SwingConstants.CENTER);
		lblGestisciLeProprieta.setFont(new Font("Monopoly Inline", Font.PLAIN, 25));
		lblGestisciLeProprieta.setBounds(129, 27, 266, 31);
		panel_gestione_proprieta.add(lblGestisciLeProprieta);
		btnBlu.setBounds(39, 120, 100, 100);
		btnBlu.setFont(new Font("Monopoly Inline", Font.PLAIN, 20));
		panel_gestione_proprieta.add(btnBlu);
		btnVerde.setBounds(140, 120, 100, 100);
		btnVerde.setFont(new Font("Monopoly Inline", Font.PLAIN, 20));
		panel_gestione_proprieta.add(btnVerde);
		btnGiallo.setBounds(240, 120, 100, 100);
		btnGiallo.setFont(new Font("Monopoly Inline", Font.PLAIN, 20));
		panel_gestione_proprieta.add(btnGiallo);
		btnRosso.setBounds(340, 120, 100, 100);
		btnRosso.setFont(new Font("Monopoly Inline", Font.PLAIN, 20));
		panel_gestione_proprieta.add(btnRosso);
		
		btnArancio.setBounds(39, 322, 100, 100);
		btnArancio.setFont(new Font("Monopoly Inline", Font.PLAIN, 20));
		panel_gestione_proprieta.add(btnArancio);
		btnViola.setBounds(140, 322,  100, 100);
		btnViola.setFont(new Font("Monopoly Inline", Font.PLAIN, 20));
		panel_gestione_proprieta.add(btnViola);
		btnAzzurro.setBounds(240, 322, 100, 100);
		btnAzzurro.setFont(new Font("Monopoly Inline", Font.PLAIN, 20));
		panel_gestione_proprieta.add(btnAzzurro);
		btnMarrone.setBounds(340, 322,  100, 100);
		btnMarrone.setFont(new Font("Monopoly Inline", Font.PLAIN, 20));
		panel_gestione_proprieta.add(btnMarrone);
		
		
		btnIndietro.setFont(new Font("Monopoly Inline", Font.PLAIN, 20));
		btnIndietro.setBounds(10, 467, 140, 43);
		panel_gestione_proprieta.add(btnIndietro);
		
		panel_gestione_proprieta.repaint();
		panel_gestione_proprieta.revalidate();

	}
	
	public void Colore(String colore, boolean demolisci) {
		
		contatoreBottone=0;
		panel_sfondo.remove(panel_gestione_proprieta);
		
		panel_gestione_proprieta = new JPanel();
		panel_gestione_proprieta.setLayout(null);
		panel_gestione_proprieta.setBounds(130, 130, 520, 520);
		panel_gestione_proprieta.setBorder(new MatteBorder(5, 5, 5, 5, Color.BLACK)); 
		panel_sfondo.add(panel_gestione_proprieta);
		
		JLabel lblGestisciLeProprieta;
		if(demolisci) {
			lblGestisciLeProprieta = new JLabel("Demolisci sul "+colore);
		}else {
			lblGestisciLeProprieta = new JLabel("Costruisci sul "+colore);
		}
		lblGestisciLeProprieta.setHorizontalAlignment(SwingConstants.CENTER);
		lblGestisciLeProprieta.setFont(new Font("Monopoly Inline", Font.PLAIN, 25));
		lblGestisciLeProprieta.setBounds(129, 27, 266, 31);
		panel_gestione_proprieta.add(lblGestisciLeProprieta);
		int costo=0;
		if(colore=="marrone" || colore=="azzurro") {
			costo=50;
		}
		if(colore=="viola" || colore=="arancione") {
			costo=100;
		}
		if(colore=="rosso" || colore=="giallo") {
			costo=150;
		}
		if(colore=="verde" || colore=="blu") {
			costo=200;
		}
		
		JLabel lblCosto = new JLabel("Il costo di una casa è di: "+costo);
		lblCosto.setHorizontalAlignment(SwingConstants.CENTER);
		lblCosto.setFont(new Font("Monopoly Inline", Font.PLAIN, 18));
		lblCosto.setBounds(129, 50, 266, 31);
		panel_gestione_proprieta.add(lblCosto);
		
		
		btn1.setBounds(120, 100 , 200, 100);
		btn1.setFont(new Font("Monopoly Inline", Font.PLAIN, 20));
		btn1.setVisible(false);
		panel_gestione_proprieta.add(btn1);
		
		btn2.setBounds(120, 220, 200, 100);
		btn2.setFont(new Font("Monopoly Inline", Font.PLAIN, 20));
		btn2.setVisible(false);
		panel_gestione_proprieta.add(btn2);
		
		btn3.setBounds(120, 340, 200, 100);
		btn3.setFont(new Font("Monopoly Inline", Font.PLAIN, 20));
		btn3.setVisible(false);
		panel_gestione_proprieta.add(btn3);
		
		btnIndietro1.setFont(new Font("Monopoly Inline", Font.PLAIN, 20));
		btnIndietro1.setBounds(10, 467, 140, 43);
		panel_gestione_proprieta.add(btnIndietro1);
		
		panel_gestione_proprieta.repaint();
		panel_gestione_proprieta.revalidate();
	}
	
	public void aggiungiBottone(String proprietà) {
		
		contatoreBottone++;
		if(contatoreBottone==1) {
			btn1.setText(proprietà);
			btn1.setVisible(true);
		}
		if(contatoreBottone==2) {
			btn2.setText(proprietà);
			btn2.setVisible(true);
		}
		if(contatoreBottone==3) {
			btn3.setText(proprietà);
			btn3.setVisible(true);
		}
	}
	
	
	
	public void addBtnCostruisci(ActionListener listener) {
		btnCostruisci.addActionListener(listener);
	}
	public void addBtnDemolisci(ActionListener listener) {
		btnDemolisci.addActionListener(listener);
	}
	public void addBtnMarrone(ActionListener listener) {
		btnMarrone.addActionListener(listener);
	}
	public void addBtnAzzurro(ActionListener listener) {
		btnAzzurro.addActionListener(listener);
	}
	public void addBtnViola(ActionListener listener) {
		btnViola.addActionListener(listener);
	}
	public void addBtnArancio(ActionListener listener) {
		btnArancio.addActionListener(listener);
	}
	public void addBtnRosso(ActionListener listener) {
		btnRosso.addActionListener(listener);
	}
	public void addBtnGiallo(ActionListener listener) {
		btnGiallo.addActionListener(listener);
	}
	public void addBtnVerde(ActionListener listener) {
		btnVerde.addActionListener(listener);
	}
	public void addBtnBlu(ActionListener listener) {
		btnBlu.addActionListener(listener);
	}
	
	public void addBtnIndietro(ActionListener listener) {
		btnIndietro.addActionListener(listener);
	}
	
	public void addBtnIndietro1(ActionListener listener) {
		btnIndietro1.addActionListener(listener);
	}
	
	public void addBtnFine(ActionListener listener) {
		btnFine.addActionListener(listener);
	}
	
	public void addBtn1(ActionListener listener) {
		btn1.addActionListener(listener);
	}
	
	public void addBtn2(ActionListener listener) {
		btn2.addActionListener(listener);
	}
	
	public void addBtn3(ActionListener listener) {
		btn3.addActionListener(listener);
	}
	
	

}
