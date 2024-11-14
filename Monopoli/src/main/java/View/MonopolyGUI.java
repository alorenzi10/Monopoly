package View;

import java.awt.Color;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

import Controller.NomiGiocatoriController;
import Controller.SceltaPedineController;

import javax.swing.JButton;
import java.io.File;
import java.io.IOException;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;

public class MonopolyGUI extends JLayeredPane {

	private static final long serialVersionUID = 1L;
	private SchermataDiGioco frame;
	private JTextArea consoleTextArea;
	private JPanel[] caselle;
	private JLabel[] pedine;

	private JPanel panel_sfondo;
	public JPanel panel_scelte_turno;
	private JButton btnTiraDadi, btnScambi, btnProprieta, btnDichiaraBancarotta, btnFineTurno, btnAcquista, btnAsta, btnMostraProprieta;
	private JButton btnConfermaBancarotta, btnNoBancarotta ;
	private JButton btnUsaCartaEsciDiPrigione, btnPagaCauzione;
	private JButton btn1, btn5, btn10, btnConfermaOfferta;
	public JButton btnRitirati; //asta
	private JLabel lblTurno, lblOfferta;

	private JPanel panel_gestione_proprieta;
	private JButton btnCostruisci; //proprietà
	private JButton btnBlu, btnVerde, btnGiallo, btnRosso, btnArancio, btnViola, btnAzzurro, btnMarrone;

	private boolean decisioneBancarotta;

	public MonopolyGUI(SchermataDiGioco frame) {

		this.frame=frame;
		caselle = new JPanel[40];
		setBounds(0, 0, 1540, 845);
		setLayout(null);

		JPanel casella0 = new JPanel();
		casella0.setLayout(null);
		casella0.setBounds(30, 30, 90, 90);
		casella0.setBorder(new MatteBorder(2, 2, 2, 2, Color.BLACK));
		add(casella0);

		JLabel via_png = new JLabel();
		via_png.setIcon(new ImageIcon("./icons/VIA!!.png"));
		via_png.setBounds(2, 2, 86, 86);
		casella0.add(via_png);
		caselle[0] = casella0;

		JPanel casella1 = new JPanel();
		casella1.setLayout(null);
		casella1.setBounds(120, 30, 60, 90);
		casella1.setBorder(new MatteBorder(2, 0, 2, 0, Color.BLACK));
		add(casella1);

		JLabel vicolo_corto = new JLabel();
		vicolo_corto.setIcon(new ImageIcon("./icons/vicolo_corto.png"));
		vicolo_corto.setBounds(0, 2, 60, 86);
		casella1.add(vicolo_corto);
		caselle[1]=casella1;


		JPanel casella2 = new JPanel();
		casella2.setLayout(null);
		casella2.setBounds(180, 30, 60, 90);
		casella2.setBorder(new MatteBorder(2, 2, 2, 0, Color.BLACK));
		add(casella2);


		JLabel probabilita1 = new JLabel("");
		probabilita1.setIcon(new ImageIcon("./icons/Probablita1.png"));
		probabilita1.setBounds(2, 2, 58, 86);
		casella2.add(probabilita1);
		caselle[2]=casella2;

		JPanel casella3 = new JPanel();
		casella3.setBorder(new MatteBorder(2, 2, 2, 0, Color.BLACK)); 
		casella3.setBounds(240, 30, 60, 90);
		casella3.setLayout(null);
		add(casella3);

		JLabel vicolo_stretto = new JLabel("");
		vicolo_stretto.setIcon(new ImageIcon("./icons/vicolo_stretto.png"));
		vicolo_stretto.setBounds(2, 2, 58, 86);
		casella3.add(vicolo_stretto);
		caselle[3]=casella3;

		JPanel casella4 = new JPanel();
		casella4.setBorder(new MatteBorder(2, 2, 2, 0, Color.BLACK)); 
		casella4.setBounds(300, 30, 60, 90);
		casella4.setLayout(null);
		add(casella4);

		JLabel tassa_patrimoniale = new JLabel("");
		tassa_patrimoniale.setIcon(new ImageIcon("./icons/tassa_patrimoniale.png"));
		tassa_patrimoniale.setBounds(2, 2, 58, 86);
		casella4.add(tassa_patrimoniale);		
		caselle[4]=casella4;

		JPanel casella5 = new JPanel();
		casella5.setBorder(new MatteBorder(2, 2, 2, 0, Color.BLACK)); 
		casella5.setBounds(360, 30, 60, 90);
		casella5.setLayout(null);
		add(casella5);

		JLabel stazione_nord = new JLabel("");
		stazione_nord.setIcon(new ImageIcon("./icons/Stazione_nord.png"));
		stazione_nord.setBounds(2, 2, 58, 86);
		casella5.add(stazione_nord);
		caselle[5]=casella5;

		JPanel casella6 = new JPanel();
		casella6.setBorder(new MatteBorder(2, 2, 2, 0, Color.BLACK)); 
		casella6.setBounds(420, 30, 60, 90);
		casella6.setLayout(null);
		add(casella6);

		JLabel bastioni_gran_sasso = new JLabel("");
		bastioni_gran_sasso.setIcon(new ImageIcon("./icons/Bastioni_Gran_Sasso.png"));
		bastioni_gran_sasso.setBounds(2, 2, 58, 86);
		casella6.add(bastioni_gran_sasso);
		caselle[6]=casella6;

		JPanel casella7 = new JPanel();
		casella7.setBorder(new MatteBorder(2, 2, 2, 0, Color.BLACK)); 
		casella7.setBounds(480, 30, 60, 90);
		casella7.setLayout(null);
		add(casella7);

		JLabel imprevisti1 = new JLabel("");
		imprevisti1.setIcon(new ImageIcon("./icons/imprevisti1-2.png"));
		imprevisti1.setBounds(2, 2, 58, 86);
		casella7.add(imprevisti1);
		caselle[7]=casella7;

		JPanel casella8 = new JPanel();
		casella8.setBorder(new MatteBorder(2, 2, 2, 0, Color.BLACK)); 
		casella8.setBounds(540, 30, 60, 90);
		casella8.setLayout(null);
		add(casella8);

		JLabel viale_monterosa = new JLabel("");
		viale_monterosa.setIcon(new ImageIcon("./icons/Viale_Monterosa.png"));
		viale_monterosa.setBounds(2, 2, 58, 86);
		casella8.add(viale_monterosa);
		caselle[8]=casella8;

		JPanel casella9 = new JPanel();
		casella9.setBorder(new MatteBorder(2, 2, 2, 0, Color.BLACK)); 
		casella9.setBounds(600, 30, 60, 90);
		casella9.setLayout(null);
		add(casella9);

		JLabel viale_vesuvio = new JLabel("");
		viale_vesuvio.setIcon(new ImageIcon("./icons/viale_vesuvio.png"));
		viale_vesuvio.setBounds(2, 2, 58, 86);
		casella9.add(viale_vesuvio);
		caselle[9]=casella9;

		JPanel casella10 = new JPanel();
		casella10.setBorder(new MatteBorder(2, 2, 2, 2, Color.BLACK)); 
		casella10.setBounds(660, 30, 90, 90);
		casella10.setLayout(null);
		add(casella10);

		JLabel transito = new JLabel("");
		transito.setIcon(new ImageIcon("./icons/transito.png"));
		transito.setBounds(2, 2, 86, 86);
		casella10.add(transito);
		caselle[10]=casella10;

		JPanel casella11 = new JPanel();
		casella11.setBorder(new MatteBorder(0, 2, 2, 2, Color.BLACK)); 
		casella11.setBounds(660, 120, 90, 60);
		casella11.setLayout(null);
		add(casella11);

		JLabel via_accademia = new JLabel("");
		via_accademia.setIcon(new ImageIcon("./icons/via_accademia.png"));
		via_accademia.setBounds(2, 0, 86, 58);
		casella11.add(via_accademia);
		caselle[11]=casella11;

		JPanel casella12 = new JPanel();
		casella12.setBorder(new MatteBorder(0, 2, 2, 2, Color.BLACK)); 
		casella12.setBounds(660, 180, 90, 60);
		casella12.setLayout(null);
		add(casella12);

		JLabel societa_elettrica = new JLabel("");
		societa_elettrica.setIcon(new ImageIcon("./icons/societa_elettrica.png"));
		societa_elettrica.setBounds(2, 0, 86, 58);
		casella12.add(societa_elettrica);
		caselle[12]=casella12;

		JPanel casella13 = new JPanel();
		casella13.setBorder(new MatteBorder(0, 2, 2, 2, Color.BLACK)); 
		casella13.setBounds(660, 240, 90, 60);
		casella13.setLayout(null);
		add(casella13);

		JLabel corso_ateneo = new JLabel("");
		corso_ateneo.setIcon(new ImageIcon("./icons/corso_ateneo.png"));
		corso_ateneo.setBounds(2, 0, 86, 58);
		casella13.add(corso_ateneo);
		caselle[13]=casella13;

		JPanel casella14 = new JPanel();
		casella14.setBorder(new MatteBorder(0, 2, 2, 2, Color.BLACK)); 
		casella14.setBounds(660, 300, 90, 60);
		casella14.setLayout(null);
		add(casella14);

		JLabel piazza_universita = new JLabel("");
		piazza_universita.setIcon(new ImageIcon("./icons/piazza_universita.png"));
		piazza_universita.setBounds(2, 0, 86, 58);
		casella14.add(piazza_universita);
		caselle[14]=casella14;

		JPanel casella15 = new JPanel();
		casella15.setBorder(new MatteBorder(0, 2, 2, 2, Color.BLACK)); 
		casella15.setBounds(660, 360, 90, 60);
		casella15.setLayout(null);
		add(casella15);

		JLabel stazione_est = new JLabel("");
		stazione_est.setIcon(new ImageIcon("./icons/stazione_est.png"));
		stazione_est.setBounds(2, 0, 86, 58);
		casella15.add(stazione_est);
		caselle[15]=casella15;

		JPanel casella16 = new JPanel();
		casella16.setBorder(new MatteBorder(0, 2, 2, 2, Color.BLACK)); 
		casella16.setBounds(660, 420, 90, 60);
		casella16.setLayout(null);
		add(casella16);

		JLabel via_verdi = new JLabel("");
		via_verdi.setIcon(new ImageIcon("./icons/via_verdi.png"));
		via_verdi.setBounds(2, 0, 86, 58);
		casella16.add(via_verdi);
		caselle[16]=casella16;

		JPanel casella17= new JPanel();
		casella17.setBorder(new MatteBorder(0, 2, 2, 2, Color.BLACK)); 
		casella17.setBounds(660, 480, 90, 60);
		casella17.setLayout(null);
		add(casella17);

		JLabel probabilita2 = new JLabel("");
		probabilita2.setIcon(new ImageIcon("./icons/Probabilita2.png"));
		probabilita2.setBounds(2, 0, 86, 58);
		casella17.add(probabilita2);
		caselle[17]=casella17;

		JPanel casella18 = new JPanel();
		casella18.setBorder(new MatteBorder(0, 2, 2, 2, Color.BLACK)); 
		casella18.setBounds(660, 540, 90, 60);
		casella18.setLayout(null);
		add(casella18);

		JLabel corso_raffaello = new JLabel("");
		corso_raffaello.setIcon(new ImageIcon("./icons/corso_raffaello.png"));
		corso_raffaello.setBounds(2, 0, 86, 58);
		casella18.add(corso_raffaello);
		caselle[18]=casella18;

		JPanel casella19 = new JPanel();
		casella19.setBorder(new MatteBorder(0, 2, 0, 2, Color.BLACK)); 
		casella19.setBounds(660, 600, 90, 60);
		casella19.setLayout(null);
		add(casella19);

		JLabel piazza_dante = new JLabel("");
		piazza_dante.setIcon(new ImageIcon("./icons/piazza_dante.png"));
		piazza_dante.setBounds(2, 0, 86, 60);
		casella19.add(piazza_dante);
		caselle[19]=casella19;

		JPanel casella20 = new JPanel();
		casella20.setBorder(new MatteBorder(2, 2, 2, 2, Color.BLACK)); 
		casella20.setBounds(660, 660, 90, 90);
		casella20.setLayout(null);
		add(casella20);

		JLabel parcheggio_gratuito = new JLabel("");
		parcheggio_gratuito.setIcon(new ImageIcon("./icons/parcheggio_gratuito.png"));
		parcheggio_gratuito.setBounds(2, 2, 86, 86);
		casella20.add(parcheggio_gratuito);
		caselle[20]=casella20;

		JPanel casella21 = new JPanel();
		casella21.setBorder(new MatteBorder(2, 2, 2, 0, Color.BLACK)); 
		casella21.setBounds(600, 660, 60, 90);
		casella21.setLayout(null);
		add(casella21);

		JLabel via_marco_polo = new JLabel("");
		via_marco_polo.setIcon(new ImageIcon("./icons/via_marco_polo.png"));
		via_marco_polo.setBounds(2, 2, 58, 86);
		casella21.add(via_marco_polo);
		caselle[21]=casella21;

		JPanel casella22 = new JPanel();
		casella22.setBorder(new MatteBorder(2, 2, 2, 0, Color.BLACK)); 
		casella22.setBounds(540, 660, 60, 90);
		casella22.setLayout(null);
		add(casella22);

		JLabel imprevisti2 = new JLabel("");
		imprevisti2.setIcon(new ImageIcon("./icons/imprevisti1-2.png"));
		imprevisti2.setBounds(2, 2, 58, 86);
		casella22.add(imprevisti2);
		caselle[22]=casella22;

		JPanel casella23 = new JPanel();
		casella23.setBorder(new MatteBorder(2, 2, 2, 0, Color.BLACK)); 
		casella23.setBounds(480, 660, 60, 90);
		casella23.setLayout(null);
		add(casella23);

		JLabel corso_magellano = new JLabel("");
		corso_magellano.setIcon(new ImageIcon("./icons/corso_magellano.png"));
		corso_magellano.setBounds(2, 2, 58, 86);
		casella23.add(corso_magellano);
		caselle[23]=casella23;

		JPanel casella24 = new JPanel();
		casella24.setBorder(new MatteBorder(2, 2, 2, 0, Color.BLACK)); 
		casella24.setBounds(420, 660, 60, 90);
		casella24.setLayout(null);
		add(casella24);

		JLabel largo_colombo = new JLabel("");
		largo_colombo.setIcon(new ImageIcon("./icons/largo _colombo.png"));
		largo_colombo.setBounds(2, 2, 58, 86);
		casella24.add(largo_colombo);
		caselle[24]=casella24;

		JPanel casella25 = new JPanel();
		casella25.setBorder(new MatteBorder(2, 2, 2, 0, Color.BLACK)); 
		casella25.setBounds(360, 660, 60, 90);
		casella25.setLayout(null);
		add(casella25);

		JLabel stazione_sud = new JLabel("");
		stazione_sud.setIcon(new ImageIcon("./icons/stazione_sud.png"));
		stazione_sud.setBounds(2, 2, 58, 86);
		casella25.add(stazione_sud);
		caselle[25]=casella25;

		JPanel casella26 = new JPanel();
		casella26.setBorder(new MatteBorder(2, 2, 2, 0, Color.BLACK)); 
		casella26.setBounds(300, 660, 60, 90);
		casella26.setLayout(null);
		add(casella26);

		JLabel viale_costantino = new JLabel("");
		viale_costantino.setIcon(new ImageIcon("./icons/viale_costantino.png"));
		viale_costantino.setBounds(2, 2, 58, 86);
		casella26.add(viale_costantino);
		caselle[26]=casella26;

		JPanel casella27 = new JPanel();
		casella27.setBorder(new MatteBorder(2, 2, 2, 0, Color.BLACK)); 
		casella27.setBounds(240, 660, 60, 90);
		casella27.setLayout(null);
		add(casella27);

		JLabel viale_traiano = new JLabel("");
		viale_traiano.setIcon(new ImageIcon("./icons/viale_traiano.png"));
		viale_traiano.setBounds(2, 2, 58, 86);
		casella27.add(viale_traiano);
		caselle[27]=casella27;

		JPanel casella28 = new JPanel();
		casella28.setBorder(new MatteBorder(2, 2, 2, 0, Color.BLACK)); 
		casella28.setBounds(180, 660, 60, 90);
		casella28.setLayout(null);
		add(casella28);

		JLabel societa_acqua_potabile = new JLabel("");
		societa_acqua_potabile.setIcon(new ImageIcon("./icons/societa_acqua_potabile.png"));
		societa_acqua_potabile.setBounds(2, 2, 59, 86);
		casella28.add(societa_acqua_potabile);
		caselle[28]=casella28;

		JPanel casella29 = new JPanel();
		casella29.setBorder(new MatteBorder(2, 0, 2, 0, Color.BLACK)); 
		casella29.setBounds(120, 660, 60, 90);
		casella29.setLayout(null);
		add(casella29);

		JLabel piazza_giulio_cesare = new JLabel("");
		piazza_giulio_cesare.setIcon(new ImageIcon("./icons/piazza_giulio_cesare.png"));
		piazza_giulio_cesare.setBounds(0, 2, 60, 86);
		casella29.add(piazza_giulio_cesare);
		caselle[29]=casella29;

		JPanel casella30 = new JPanel();
		casella30.setBorder(new MatteBorder(2, 2, 2, 2, Color.BLACK)); 
		casella30.setBounds(30, 660, 90, 90);
		casella30.setLayout(null);
		add(casella30);

		JLabel vai_in_prigione = new JLabel("");
		vai_in_prigione.setIcon(new ImageIcon("./icons/vai_in_prigione.png"));
		vai_in_prigione.setBounds(2, 2, 86, 86);
		casella30.add(vai_in_prigione);
		caselle[30]=casella30;

		JPanel casella31 = new JPanel();
		casella31.setBorder(new MatteBorder(0, 2, 0, 2, Color.BLACK)); 
		casella31.setBounds(30, 600, 90, 60);
		casella31.setLayout(null);
		add(casella31);

		JLabel via_roma = new JLabel("");
		via_roma.setIcon(new ImageIcon("./icons/via_roma.png"));
		via_roma.setBounds(2, 0, 86, 60);
		casella31.add(via_roma);
		caselle[31]=casella31;

		JPanel casella32 = new JPanel();
		casella32.setBorder(new MatteBorder(0, 2, 2, 2, Color.BLACK)); 
		casella32.setBounds(30, 540, 90, 60);
		casella32.setLayout(null);
		add(casella32);

		JLabel corso_impero = new JLabel("");
		corso_impero.setIcon(new ImageIcon("./icons/corso_impero.png"));
		corso_impero.setBounds(2, 0, 86, 58);
		casella32.add(corso_impero);
		caselle[32]=casella32;

		JPanel casella33 = new JPanel();
		casella33.setBorder(new MatteBorder(0, 2, 2, 2, Color.BLACK)); 
		casella33.setBounds(30, 480, 90, 60);
		casella33.setLayout(null);
		add(casella33);

		JLabel probabilita3 = new JLabel("");
		probabilita3.setIcon(new ImageIcon("./icons/probabilita3.png"));
		probabilita3.setBounds(2, 0, 86, 58);
		casella33.add(probabilita3);
		caselle[33]=casella33;

		JPanel casella34 = new JPanel();
		casella34.setBorder(new MatteBorder(0, 2, 2, 2, Color.BLACK)); 
		casella34.setBounds(30, 420, 90, 60);
		casella34.setLayout(null);
		add(casella34);

		JLabel largo_augusto = new JLabel("");
		largo_augusto.setIcon(new ImageIcon("./icons/largo_augusto.png"));
		largo_augusto.setBounds(2, 0, 86, 58);
		casella34.add(largo_augusto);
		caselle[34]=casella34;

		JPanel casella35 = new JPanel();
		casella35.setBorder(new MatteBorder(0, 2, 2, 2, Color.BLACK)); 
		casella35.setBounds(30, 360, 90, 60);
		casella35.setLayout(null);
		add(casella35);

		JLabel stazione_ovest = new JLabel("");
		stazione_ovest.setIcon(new ImageIcon("./icons/stazione_ovest.png"));
		stazione_ovest.setBounds(2, 0, 86, 58);
		casella35.add(stazione_ovest);
		caselle[35]=casella35;

		JPanel casella36 = new JPanel();
		casella36.setBorder(new MatteBorder(0, 2, 2, 2, Color.BLACK)); 
		casella36.setBounds(30, 300, 90, 60);
		casella36.setLayout(null);
		add(casella36);

		JLabel imprevisti3 = new JLabel("");
		imprevisti3.setIcon(new ImageIcon("./icons/imprevisti3.png"));
		imprevisti3.setBounds(2, 0, 86, 58);
		casella36.add(imprevisti3);
		caselle[36]=casella36;

		JPanel casella37 = new JPanel();
		casella37.setBorder(new MatteBorder(0, 2, 2, 2, Color.BLACK)); 
		casella37.setBounds(30, 240, 90, 60);
		casella37.setLayout(null);
		add(casella37);

		JLabel viale_dei_giardini = new JLabel("");
		viale_dei_giardini.setIcon(new ImageIcon("./icons/viale_dei_giardini.png"));
		viale_dei_giardini.setBounds(2, 0, 86, 58);
		casella37.add(viale_dei_giardini);
		caselle[37]=casella37;

		JPanel casella38 = new JPanel();
		casella38.setBorder(new MatteBorder(0, 2, 2, 2, Color.BLACK)); 
		casella38.setBounds(30, 180, 90, 60);
		casella38.setLayout(null);
		add(casella38);

		JLabel tassa_di_lusso = new JLabel("");
		tassa_di_lusso.setIcon(new ImageIcon("./icons/tassa_di_lusso.png"));
		tassa_di_lusso.setBounds(2, 0, 86, 58);
		casella38.add(tassa_di_lusso);
		caselle[38]=casella38;

		JPanel casella39 = new JPanel();
		casella39.setBorder(new MatteBorder(0, 2, 2, 2, Color.BLACK)); 
		casella39.setBounds(30, 120, 90, 60);
		casella39.setLayout(null);
		add(casella39);

		JLabel parco_della_vittoria = new JLabel("");
		parco_della_vittoria.setIcon(new ImageIcon("./icons/parco_della_vittoria.png"));
		parco_della_vittoria.setBounds(2, 0, 86, 58);
		casella39.add(parco_della_vittoria);
		caselle[39]=casella39;

		consoleTextArea = new JTextArea();
		consoleTextArea.setEditable(false);
		consoleTextArea.setFont(new Font("Monopoly Inline", consoleTextArea.getFont().getStyle(), 25));

		JScrollPane scrollPane = new JScrollPane(consoleTextArea);
		scrollPane.setBounds(760, 490, 770, 260);
		add(scrollPane);

		panel_scelte_turno = new JPanel();
		panel_scelte_turno.setBounds(130, 130, 520, 520);
		panel_scelte_turno.setBorder(new MatteBorder(5, 5, 5, 5, Color.BLACK)); 
		frame.getContentPane().add(panel_scelte_turno);
		panel_scelte_turno.setLayout(null);

		// Bottone tiro dadi
		btnTiraDadi = new JButton("Tira i dadi");
		btnTiraDadi.setBounds(40, 36, 200, 60);
		btnTiraDadi.setFont(new Font("Monopoly Inline", Font.PLAIN, 18));
		try {
			btnTiraDadi.setIcon(new ImageIcon(ImageIO.read(new File("./icons/dice.png")).getScaledInstance(32, 32, Image.SCALE_DEFAULT)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		panel_scelte_turno.add(btnTiraDadi);

		// Bottone scambi
		btnScambi = new JButton("Scambi");
		btnScambi.setBounds(280, 36, 200, 60);
		btnScambi.setFont(new Font("Monopoly Inline", Font.PLAIN, 18));
		try {
			btnScambi.setIcon(new ImageIcon(ImageIO.read(new File("./icons/exchange.png")).getScaledInstance(32, 32, Image.SCALE_DEFAULT)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		panel_scelte_turno.add(btnScambi);

		// Bottone proprietà
		btnProprieta = new JButton("Proprietà");
		btnProprieta.setBounds(40, 162, 200, 60);
		btnProprieta.setFont(new Font("Monopoly Inline", Font.PLAIN, 18));
		try {
			btnProprieta.setIcon(new ImageIcon(ImageIO.read(new File("./icons/properties.png")).getScaledInstance(32, 32, Image.SCALE_DEFAULT)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		panel_scelte_turno.add(btnProprieta);

		// Bottone dichiara bancarotta
		btnDichiaraBancarotta = new JButton("Bancarotta");
		btnDichiaraBancarotta.setFont(new Font("Monopoly Inline", Font.PLAIN, 18));
		btnDichiaraBancarotta.setBounds(280, 162, 200, 60);
		try {
			btnDichiaraBancarotta.setIcon(new ImageIcon(ImageIO.read(new File("./icons/bankrupt.png")).getScaledInstance(32, 32, Image.SCALE_DEFAULT)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		panel_scelte_turno.add(btnDichiaraBancarotta);

		// Bottone fine turno
		btnFineTurno = new JButton("Fine del turno");
		btnFineTurno.setFont(new Font("Monopoly Inline", Font.PLAIN, 18));
		btnFineTurno.setBounds(160, 437, 200, 60);
		try {
			btnFineTurno.setIcon(new ImageIcon(ImageIO.read(new File("./icons/stop.png")).getScaledInstance(32, 32, Image.SCALE_DEFAULT)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		panel_scelte_turno.add(btnFineTurno);

		JPanel panel_azioni_prigione = new JPanel();
		panel_azioni_prigione.setBounds(10, 232, 500, 184);
		panel_scelte_turno.add(panel_azioni_prigione);
		panel_azioni_prigione.setLayout(null);

		JLabel lblAzioniPrigione = new JLabel("Azioni prigione");
		lblAzioniPrigione.setFont(new Font("Monopoly Inline", Font.PLAIN, 20));
		lblAzioniPrigione.setBounds(184, 34, 131, 25);
		panel_azioni_prigione.add(lblAzioniPrigione);

		btnUsaCartaEsciDiPrigione = new JButton("Esci gratis di prigione");
		btnUsaCartaEsciDiPrigione.setFont(new Font("Monopoly Inline", Font.PLAIN, 18));
		btnUsaCartaEsciDiPrigione.setBounds(18, 85, 223, 60);
		btnUsaCartaEsciDiPrigione.setEnabled(false);
		panel_azioni_prigione.add(btnUsaCartaEsciDiPrigione);

		btnPagaCauzione = new JButton("Paga cauzione (50€)");
		btnPagaCauzione.setFont(new Font("Monopoly Inline", Font.PLAIN, 18));
		btnPagaCauzione.setBounds(259, 85, 223, 60);
		btnPagaCauzione.setEnabled(false);
		panel_azioni_prigione.add(btnPagaCauzione);

		btnAcquista = new JButton("Acquisto");
		btnAsta = new JButton("Asta");
		btnConfermaBancarotta = new JButton("Confermo");
		btnNoBancarotta = new JButton("No, torna al gioco");

		// Per asta
		btn1=new JButton("+1");
		btn5=new JButton("+5");
		btn10=new JButton("+10");
		btnConfermaOfferta=new JButton("Conferma Offerta");
		btnRitirati=new JButton("Ritirati");

		btnCostruisci = new JButton("Costruisci");
		btnBlu=  new JButton("Blu");
		btnVerde=   new JButton("Verde");
		btnGiallo=   new JButton("Giallo");
		btnRosso=   new JButton("Rosso");
		btnArancio=   new JButton("Arancio");
		btnViola=   new JButton("Viola");
		btnAzzurro=   new JButton("Azzurro");
		btnMarrone= new JButton("Marrone");

		mostraInfoGiocatori();
		creaPedine();

	}

	public void addBtnTiraDadi(ActionListener listener) {
		btnTiraDadi.addActionListener(listener);
	}
	public void addBtnDichiaraBancarotta(ActionListener listener) {
		btnDichiaraBancarotta.addActionListener(listener);
	}
	public void addBtnScambi(ActionListener listener) {
		btnScambi.addActionListener(listener);
	}
	public void addBtnProprieta(ActionListener listener) {
		btnProprieta.addActionListener(listener);
	}
	public void addBtnFineTurno(ActionListener listener) {
		btnFineTurno.addActionListener(listener);
	}

	public void addBtnAcquista(ActionListener listener) {
		btnAcquista.addActionListener(listener);
	}
	public void addBtnAsta(ActionListener listener) {
		btnAsta.addActionListener(listener);
	}
	public void addBtnConfermaBancarotta(ActionListener listener) {
		btnConfermaBancarotta.addActionListener(listener);
	}
	public void addBtnNoBancarotta(ActionListener listener) {
		btnNoBancarotta.addActionListener(listener);
	}
	public void addBtnUsaCartaEsciDiPrigione(ActionListener listener){
		btnUsaCartaEsciDiPrigione.addActionListener(listener);
	}
	public void addBtnPagaCauzione(ActionListener listener){
		btnPagaCauzione.addActionListener(listener);
	}
	public void addBtnMostraProprietaGiocatori(ActionListener listener) {
		btnMostraProprieta.addActionListener(listener);
	}
	public void addBtn1(ActionListener listener) {
		btn1.addActionListener(listener);
	}
	public void addBtn5(ActionListener listener) {
		btn5.addActionListener(listener);
	}
	public void addBtn10(ActionListener listener) {
		btn10.addActionListener(listener);
	}
	public void addBtnConfermaOfferta(ActionListener listener) {
		btnConfermaOfferta.addActionListener(listener);
	}
	public void addBtnRitirati(ActionListener listener) {
		btnRitirati.addActionListener(listener);
	}
	public void addBtnCostruisci(ActionListener listener) {
		btnCostruisci.addActionListener(listener);
	}
	public void addBtnMarrone(ActionListener listener) {
		btnMarrone.addActionListener(listener);
	}


	public void mostraInfoGiocatori() {

		JPanel panel_info_giocatori = new JPanel();
		panel_info_giocatori.setBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(0, 0, 0)));
		panel_info_giocatori.setBounds(760, 82, 770, 398);
		add(panel_info_giocatori);

		int numGiocatori = SceltaPedineController.getNumGiocatori();
		String [] nomiGiocatori = NomiGiocatoriController.getNomiGiocatori();

		panel_info_giocatori.setLayout(new GridLayout(2, 3, 10, 10));

		btnMostraProprieta = new JButton("Mostra le proprietà dei giocatori");
		btnMostraProprieta.setFont(new Font("Monopoly Inline", Font.PLAIN, 25));
		btnMostraProprieta.setBounds(974, 30, 342, 42);
		add(btnMostraProprieta);

		for (int i = 0; i < numGiocatori; i++) {

			JPanel panelGiocatore = new JPanel();
			panelGiocatore.setLayout(new BoxLayout(panelGiocatore, BoxLayout.Y_AXIS));

			// Nome del giocatore
			JLabel lblNomeGiocatore = new JLabel(nomiGiocatori[i]);
			lblNomeGiocatore.setFont(new Font("Monopoly Inline", Font.PLAIN, 30));
			lblNomeGiocatore.setAlignmentX(JLabel.CENTER_ALIGNMENT);
			panelGiocatore.add(lblNomeGiocatore);

			// Saldo del giocatore
			JLabel lblSaldoGiocatore = new JLabel("Saldo: ");
			lblSaldoGiocatore.setFont(new Font("Arial", Font.PLAIN, 16));
			lblSaldoGiocatore.setAlignmentX(JLabel.CENTER_ALIGNMENT);
			panelGiocatore.add(lblSaldoGiocatore);

			// Immagine della pedina
			JLabel lblPedinaGiocatore = new JLabel(new ImageIcon("path/to/pedina" + i + ".png"));
			lblPedinaGiocatore.setAlignmentX(JLabel.CENTER_ALIGNMENT);
			panelGiocatore.add(lblPedinaGiocatore);

			panel_info_giocatori.add(panelGiocatore);
		}
	}

	public void mostraProprietaGiocatori() {

		int numGiocatori = SceltaPedineController.getNumGiocatori();
		String [] nomiGiocatori = NomiGiocatoriController.getNomiGiocatori();


		JPanel panel_proprieta_giocatori = new JPanel();
		panel_proprieta_giocatori.setBounds(160, 212, 1600, 656);
		add(panel_proprieta_giocatori);
		panel_proprieta_giocatori.setLayout(null);

		JButton btnChiudi = new JButton("X");
		btnChiudi.setFont(new Font("Monopoly Inline", Font.PLAIN, 50));
		btnChiudi.setBounds(1525, 10, 65, 65);
		panel_proprieta_giocatori.add(btnChiudi);

		JPanel panel_proprieta = new JPanel();
		panel_proprieta.setBounds(10, 10, 1505, 636);
		panel_proprieta_giocatori.add(panel_proprieta);
		panel_proprieta.setLayout(new GridLayout(2, 3, 0, 0));

	}

	public void attivaUscitaConCarta(boolean decisione){
		btnUsaCartaEsciDiPrigione.setEnabled(decisione);
	}
	public void attivaUscitaConCauzione(boolean decisione){
		btnPagaCauzione.setEnabled(decisione);
	}

	// In base alla condition i bottoni saranno attivi o meno
	public void buttonsState(boolean condition) {

		btnTiraDadi.setEnabled(condition);
		btnScambi.setEnabled(condition);
		btnProprieta.setEnabled(condition);
		btnDichiaraBancarotta.setEnabled(condition);
		btnFineTurno.setEnabled(condition);

		panel_scelte_turno.setVisible(condition);
	}

	// Chiedo conferma al giocatore se vuole davvero andare in bancarotta
	public boolean getDecisioneBancarotta() {
		return decisioneBancarotta;
	}

	public void confermaBancarotta() {

		buttonsState(false);

		panel_sfondo = new JPanel();
		panel_sfondo.setBounds(0, 0, 1540, 845);
		panel_sfondo.setOpaque(false);
		panel_sfondo.setLayout(null);
		add(panel_sfondo, 1);
		//frame.setComponentZOrder(panel_sfondo, 0);	

		JPanel panel_conferma_bancarotta = new JPanel();
		panel_conferma_bancarotta.setBounds(130, 130, 520, 520);
		panel_conferma_bancarotta.setBorder(new MatteBorder(5, 5, 5, 5, Color.BLACK));
		panel_sfondo.add(panel_conferma_bancarotta);
		panel_conferma_bancarotta.setLayout(null);

		JLabel lblConfermaBancarotta = new JLabel("Confermi di voler dichiarare bancarotta?");
		lblConfermaBancarotta.setFont(new Font("Monopoly Inline", Font.PLAIN, 25));
		lblConfermaBancarotta.setHorizontalAlignment(SwingConstants.CENTER);
		lblConfermaBancarotta.setBounds(27, 22, 471, 53);
		panel_conferma_bancarotta.add(lblConfermaBancarotta);


		btnConfermaBancarotta.setFont(new Font("Monopoly Inline", Font.PLAIN, 30));
		btnConfermaBancarotta.setBounds(21, 232, 230, 82);
		panel_conferma_bancarotta.add(btnConfermaBancarotta);


		btnNoBancarotta.setFont(new Font("Monopoly Inline", Font.PLAIN, 25));
		btnNoBancarotta.setBounds(272, 232, 230, 82);
		panel_conferma_bancarotta.add(btnNoBancarotta);

		JLabel lblIconaBancarotta = new JLabel("");
		lblIconaBancarotta.setBounds(212, 102, 100, 100);
		try {
			lblIconaBancarotta.setIcon(new ImageIcon(ImageIO.read(new File("./icons/bankrupt.png")).getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		panel_conferma_bancarotta.add(lblIconaBancarotta);
	}

	public void mostraGestioneProprieta() {

		buttonsState(false);

		panel_sfondo = new JPanel();
		panel_sfondo.setBounds(0, 0, 1540, 845);
		panel_sfondo.setOpaque(false);
		panel_sfondo.setLayout(null);
		frame.getContentPane().add(panel_sfondo, 1);

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

		JButton btnFine = new JButton("Fine");
		btnFine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonsState(true);
				frame.remove(panel_sfondo);
				frame.revalidate();
				frame.repaint();
			}
		});
		btnFine.setFont(new Font("Monopoly Inline", Font.PLAIN, 20));
		btnFine.setBounds(10, 467, 140, 43);
		panel_gestione_proprieta.add(btnFine);

		JLabel lblGestisciLeProprieta = new JLabel("Gestisci le tue proprietà");
		lblGestisciLeProprieta.setHorizontalAlignment(SwingConstants.CENTER);
		lblGestisciLeProprieta.setFont(new Font("Monopoly Inline", Font.PLAIN, 25));
		lblGestisciLeProprieta.setBounds(129, 27, 266, 31);
		panel_gestione_proprieta.add(lblGestisciLeProprieta);

		btnCostruisci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnCostruisci.setBounds(39, 120, 203, 82);
		btnCostruisci.setFont(new Font("Monopoly Inline", Font.PLAIN, 30));
		panel_gestione_proprieta.add(btnCostruisci);

		JButton btnIpoteca = new JButton("Ipoteca");
		btnIpoteca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnIpoteca.setBounds(281, 120, 203, 82);
		btnIpoteca.setFont(new Font("Monopoly Inline", Font.PLAIN, 30));
		panel_gestione_proprieta.add(btnIpoteca);

		JButton btnDemolisci = new JButton("Demolisci");
		btnDemolisci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnDemolisci.setBounds(39, 322, 203, 82);
		btnDemolisci.setFont(new Font("Monopoly Inline", Font.PLAIN, 30));
		panel_gestione_proprieta.add(btnDemolisci);

		JButton btnDisipoteca = new JButton("Disipoteca");
		btnDisipoteca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnDisipoteca.setBounds(281, 322, 203, 82);
		btnDisipoteca.setFont(new Font("Monopoly Inline", Font.PLAIN, 30));
		panel_gestione_proprieta.add(btnDisipoteca);

	}

	public void scelteCostruzione() {

		panel_sfondo = new JPanel();
		panel_sfondo.setBounds(0, 0, 1540, 845);
		panel_sfondo.setOpaque(false);
		panel_sfondo.setLayout(null);
		panel_gestione_proprieta.setVisible(false);
		frame.getContentPane().add(panel_sfondo, 1);

		JPanel panel_gestione_proprieta = new JPanel() {
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

		JButton btnFine = new JButton("Fine");
		btnFine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonsState(true);
				frame.remove(panel_sfondo);
				frame.revalidate();
				frame.repaint();
			}
		});
		btnFine.setFont(new Font("Monopoly Inline", Font.PLAIN, 20));
		btnFine.setBounds(10, 467, 140, 43);
		panel_gestione_proprieta.add(btnFine);

		JLabel lblGestisciLeProprieta = new JLabel("Gestisci le tue proprietà");
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

	}

	public void mostraScambi(int giCorrente) {

		buttonsState(false);

		panel_sfondo = new JPanel();
		panel_sfondo.setBounds(0, 0, 1540, 845);
		panel_sfondo.setOpaque(false);
		panel_sfondo.setLayout(null);
		frame.getContentPane().add(panel_sfondo, 1);

		JPanel panel_gestione_scambi = new JPanel() {
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
		panel_gestione_scambi.setLayout(null);
		panel_gestione_scambi.setBounds(130, 130, 520, 520);
		panel_gestione_scambi.setBorder(new MatteBorder(5, 5, 5, 5, Color.BLACK)); 
		panel_sfondo.add(panel_gestione_scambi);
		int numGiocatori = SceltaPedineController.getNumGiocatori();
		String[] nomiGiocatori = NomiGiocatoriController.getNomiGiocatori();
		for (int i = 0; i < numGiocatori; i++) {// listener
			if(nomiGiocatori[i] == NomiGiocatoriController.getNomiGiocatori(giCorrente))
				i++;
			JButton btnNomeGiocatore = new JButton(nomiGiocatori[i]);
			btnNomeGiocatore.setFont(new Font("Monopoly Inline", Font.PLAIN, 20));
			btnNomeGiocatore.setBounds(170, 152 + (i * 48), 180, 40);
			btnNomeGiocatore.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					contrattazioneScambi(panel_gestione_scambi);
				}
			});
			panel_gestione_scambi.add(btnNomeGiocatore);
		}

		JLabel lblScambiGiocatori = new JLabel("Scambi tra giocatori");
		lblScambiGiocatori.setHorizontalAlignment(SwingConstants.CENTER);
		lblScambiGiocatori.setFont(new Font("Monopoly Inline", Font.PLAIN, 25));
		lblScambiGiocatori.setBounds(132, 32, 256, 43);
		panel_gestione_scambi.add(lblScambiGiocatori);

		JLabel lblScegliGiocatore = new JLabel("Scegli un giocatore con cui contrattare:");
		lblScegliGiocatore.setHorizontalAlignment(SwingConstants.CENTER);
		lblScegliGiocatore.setFont(new Font("Monopoly Inline", Font.PLAIN, 25));
		lblScegliGiocatore.setBounds(25, 111, 470, 31);
		panel_gestione_scambi.add(lblScegliGiocatore);

		JButton btnFine = new JButton("Fine");
		btnFine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonsState(true);
				frame.remove(panel_sfondo);
				frame.revalidate();
				frame.repaint();
			}
		});
		btnFine.setFont(new Font("Monopoly Inline", Font.PLAIN, 20));
		btnFine.setBounds(10, 467, 140, 43);
		panel_gestione_scambi.add(btnFine);	
	}

	public void contrattazioneScambi(JPanel panel_gestione_scambi) {

		panel_gestione_scambi.removeAll();

		JLabel lblContrattazione = new JLabel("Contrattazione");
		lblContrattazione.setHorizontalAlignment(SwingConstants.CENTER);
		lblContrattazione.setFont(new Font("Monopoly Inline", Font.PLAIN, 25));
		lblContrattazione.setBounds(132, 32, 256, 43);
		panel_gestione_scambi.add(lblContrattazione);

		JLabel lblOfferta = new JLabel("Offro:");
		lblOfferta.setHorizontalAlignment(SwingConstants.CENTER);
		lblOfferta.setFont(new Font("Monopoly Inline", Font.PLAIN, 25));
		lblOfferta.setBounds(25, 111, 470, 31);
		panel_gestione_scambi.add(lblOfferta);

		JButton btnAnnulla = new JButton("Annulla");
		btnAnnulla.setFont(new Font("Monopoly Inline", Font.PLAIN, 20));
		btnAnnulla.setBounds(10, 467, 140, 43);
		btnAnnulla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.remove(panel_sfondo);
				frame.revalidate();
				frame.repaint();
				//mostraScambi();
			}
		});
		panel_gestione_scambi.add(btnAnnulla);

		JPanel panel_proprieta_offerta = new JPanel();
		panel_proprieta_offerta.setBounds(10, 150, 500, 106);
		panel_gestione_scambi.add(panel_proprieta_offerta);

		JPanel panel_denaro_offerta = new JPanel();
		panel_denaro_offerta.setBounds(10, 266, 500, 191);
		panel_gestione_scambi.add(panel_denaro_offerta);
		panel_denaro_offerta.setLayout(null);

		JLabel lblPerDenaro = new JLabel("In cambio di:");
		lblPerDenaro.setHorizontalAlignment(SwingConstants.CENTER);
		lblPerDenaro.setFont(new Font("Monopoly Inline", Font.PLAIN, 25));
		lblPerDenaro.setBounds(61, 10, 377, 50);
		panel_denaro_offerta.add(lblPerDenaro);

		JTextField txtDigitareLaQuantit = new JTextField();
		txtDigitareLaQuantit.setBounds(144, 64, 212, 36);
		panel_denaro_offerta.add(txtDigitareLaQuantit);
		txtDigitareLaQuantit.setColumns(10);

		JLabel lbl€ = new JLabel("€");
		lbl€.setFont(new Font("Monopoly Inline", Font.PLAIN, 25));
		lbl€.setBounds(366, 70, 21, 25);
		panel_denaro_offerta.add(lbl€);

		JButton btnAccettaOfferta = new JButton("Accetta");
		btnAccettaOfferta.setFont(new Font("Monopoly Inline", Font.PLAIN, 18));
		btnAccettaOfferta.setBounds(73, 124, 106, 36);
		btnAccettaOfferta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//accettaOfferta();
			}
		});
		panel_denaro_offerta.add(btnAccettaOfferta);

		JButton btnRifiutaOfferta = new JButton("Rifiuta");
		btnRifiutaOfferta.setFont(new Font("Monopoly Inline", Font.PLAIN, 18));
		btnRifiutaOfferta.setBounds(304, 124, 106, 36);
		btnRifiutaOfferta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contrattazioneScambi(panel_gestione_scambi);
			}
		});
		panel_denaro_offerta.add(btnRifiutaOfferta);

		panel_gestione_scambi.repaint();
		panel_gestione_scambi.revalidate();
	}

	public void creaPedine() {

		String [] pedineSelezionate = SceltaPedineController.getPedineScelte();
		int num = SceltaPedineController.getNumGiocatori();
		pedine = new JLabel[num];

		for(int i=0; i<num; i++) {
			pedine[i] = new JLabel();

			switch (pedineSelezionate[i]) {
			case "Cane":
				pedine[i].setIcon(new ImageIcon("./icons/cane.png"));		break;
			case "Cappello":	
				pedine[i].setIcon(new ImageIcon("./icons/cappello.png")); 	break;
			case "Cariola":	
				pedine[i].setIcon(new ImageIcon("./icons/cariola.png"));	break;
			case "Nave":	
				pedine[i].setIcon(new ImageIcon("./icons/nave.png"));		break;
			case "Ditale":
				pedine[i].setIcon(new ImageIcon("./icons/ditale.png"));		break;
			case "Ferro":	
				pedine[i].setIcon(new ImageIcon("./icons/ferro_da_stiro.png"));	break;
			case "Macchina":	
				pedine[i].setIcon(new ImageIcon("./icons/macchina.png"));	break;
			case "Stivale":	
				pedine[i].setIcon(new ImageIcon("./icons/stivale.png"));	break;
			}
		}
		for(int i=0; i<num; i++) {
			pedine[i].setBounds(7, 20, 45, 50);
			caselle[0].add(pedine[i]);
			caselle[0].setComponentZOrder(pedine[i], 0);
		}
	}	

	public void muoviPedina(int partenza, int arrivo, int pedina) {

		//controlla che il vettore non sia nullo per evitare l'eccezione
		if(pedine[pedina]!=null) {
			caselle[partenza].remove(pedine[pedina]); //rimuove il componente
			caselle[arrivo].add(pedine[pedina]); //aggiunge il componente al pannello di arrivo
			caselle[arrivo].setComponentZOrder(pedine[pedina], 0); 
		}

		frame.repaint();
	}

	public void atterraggioSuProprietaVuota() {

		buttonsState(false);

		panel_sfondo = new JPanel();
		panel_sfondo.setBounds(0, 0, 1540, 845);
		panel_sfondo.setOpaque(false);
		panel_sfondo.setLayout(null);
		add(panel_sfondo, 1);
		setComponentZOrder(panel_sfondo, 1);	

		JPanel panel_decisione_proprieta = new JPanel();
		panel_decisione_proprieta.setBounds(130, 130, 520, 520);
		panel_decisione_proprieta.setBorder(new MatteBorder(5, 5, 5, 5, Color.BLACK));
		panel_sfondo.add(panel_decisione_proprieta);
		panel_decisione_proprieta.setLayout(null);

		JLabel lblDecisioneProprieta = new JLabel("Cosa vuoi fare con la proprietà?");
		lblDecisioneProprieta.setFont(new Font("Monopoly Inline", Font.PLAIN, 25));
		lblDecisioneProprieta.setHorizontalAlignment(SwingConstants.CENTER);
		lblDecisioneProprieta.setBounds(25, 22, 471, 53);
		panel_decisione_proprieta.add(lblDecisioneProprieta);


		btnAcquista.setFont(new Font("Acquista", Font.PLAIN, 30));
		btnAcquista.setBounds(21, 232, 230, 82);
		panel_decisione_proprieta.add(btnAcquista);


		btnAsta.setFont(new Font("Metti all'asta", Font.PLAIN, 30));
		btnAsta.setBounds(272, 232, 230, 82);
		panel_decisione_proprieta.add(btnAsta);


	}
	//////INIZIO METODI ASTA
	public void asta(int giCorrente) { //forse da mettere da un altra parte

		buttonsState(false);
		panel_sfondo = new JPanel();
		panel_sfondo.setBounds(0, 0, 1540, 845);
		panel_sfondo.setOpaque(false);
		panel_sfondo.setLayout(null);
		add(panel_sfondo, 1);
		setComponentZOrder(panel_sfondo, 1);	

		JPanel panel_asta = new JPanel();
		panel_asta.setBounds(130, 130, 520, 520);
		panel_asta.setBorder(new MatteBorder(5, 5, 5, 5, Color.BLACK));
		panel_sfondo.add(panel_asta);
		panel_asta.setLayout(null);

		JLabel lblAsta = new JLabel("Asta");
		lblAsta.setFont(new Font("Monopoly Inline", Font.PLAIN, 30));
		lblAsta.setHorizontalAlignment(SwingConstants.CENTER);
		lblAsta.setBounds(25, 22, 471, 53);
		panel_asta.add(lblAsta);

		lblOfferta = new JLabel("La base d'asta è: 10€");
		lblOfferta.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblOfferta.setHorizontalAlignment(SwingConstants.CENTER);
		lblOfferta.setBounds(25, 160 , 232, 53);
		panel_asta.add(lblOfferta);

		lblTurno = new JLabel(NomiGiocatoriController.getNomiGiocatori(giCorrente) + " fai la prima offerta");
		lblTurno.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTurno.setHorizontalAlignment(SwingConstants.CENTER);
		lblTurno.setBounds(25, 100 , 232, 53);
		panel_asta.add(lblTurno);

		btn1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btn1.setBounds(21, 232, 100, 100);
		panel_asta.add(btn1);

		btn5.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btn5.setBounds(130, 232, 100, 100);
		panel_asta.add(btn5);

		btn10.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btn10.setBounds(240, 232, 100, 100);
		panel_asta.add(btn10);

		btnConfermaOfferta.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnConfermaOfferta.setBounds(21, 400, 230, 82);
		panel_asta.add(btnConfermaOfferta);

		btnRitirati.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnRitirati.setBounds(272, 400, 230, 82);
		panel_asta.add(btnRitirati);

	}	

	public void aggiornaOfferta(int offertaAggiornata) {
		lblOfferta.setText("Offerta attuale è: " + offertaAggiornata + "€");
	}

	public void aggiornaTurno(String nome) {
		lblTurno.setText(nome + " tocca a te partecipare all'asta");
	}
	/////FINE METODI ASTA

	//rimuove anche bancarotta
	public void rimuoviAcquistoAsta() {
		buttonsState(true);
		remove(panel_sfondo);
		revalidate();
		repaint();
	}

	public void stampa(String text) {
		consoleTextArea.append(">> " + text + "\n");
		consoleTextArea.setCaretPosition(consoleTextArea.getDocument().getLength()); //scorre alla fine sempre
	}

}
