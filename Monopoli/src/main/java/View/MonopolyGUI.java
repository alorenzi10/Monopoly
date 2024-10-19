package View;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

public class MonopolyGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public MonopolyGUI() {
		setVisible(true);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(90, 90, 1763, 1063);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 240, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel casella0 = new JPanel();
		casella0.setBounds(30, 30, 90, 90);
		casella0.setLayout(null);
		casella0.setBorder(new MatteBorder(2, 2, 2, 2, Color.BLACK));
		contentPane.add(casella0);
		
		JLabel via_png = new JLabel("");
		via_png.setIcon(new ImageIcon("./icons/VIA!!.png"));
		via_png.setBounds(2, 2, 86, 86);
		casella0.add(via_png);
		
		JPanel casella1 = new JPanel();
		casella1.setLayout(null);
		casella1.setBounds(120, 30, 60, 90);
		casella1.setBorder(new MatteBorder(2, 0, 2, 0, Color.BLACK));
		contentPane.add(casella1);
		
		JLabel vicolo_corto = new JLabel("");
		vicolo_corto.setIcon(new ImageIcon("./icons/vicolo_corto.png"));
		vicolo_corto.setBounds(0, 2, 60, 86);
		casella1.add(vicolo_corto);
		
		JPanel casella2 = new JPanel();
		casella2.setLayout(null);
		casella2.setBounds(180, 30, 60, 90);
		casella2.setBorder(new MatteBorder(2, 2, 2, 0, Color.BLACK));
		contentPane.add(casella2);
		
		JLabel probabilita1 = new JLabel("");
		probabilita1.setIcon(new ImageIcon("./icons/Probablita1.png"));
		probabilita1.setBounds(2, 2, 58, 86);
		casella2.add(probabilita1);
		
		JPanel casella3 = new JPanel();
		casella3.setBorder(new MatteBorder(2, 2, 2, 0, Color.BLACK)); 
		casella3.setBounds(240, 30, 60, 90);
		casella3.setLayout(null);
		contentPane.add(casella3);
		
		JLabel vicolo_stretto = new JLabel("");
		vicolo_stretto.setIcon(new ImageIcon("./icons/vicolo_stretto.png"));
		vicolo_stretto.setBounds(2, 2, 58, 86);
		casella3.add(vicolo_stretto);
		
		JPanel casella4 = new JPanel();
		casella4.setBorder(new MatteBorder(2, 2, 2, 0, Color.BLACK)); 
		casella4.setBounds(300, 30, 60, 90);
		casella4.setLayout(null);
		contentPane.add(casella4);
		
		JLabel tassa_patrimoniale = new JLabel("");
		tassa_patrimoniale.setIcon(new ImageIcon("./icons/tassa_patrimoniale.png"));
		tassa_patrimoniale.setBounds(2, 2, 58, 86);
		casella4.add(tassa_patrimoniale);		
		
		JPanel casella5 = new JPanel();
		casella5.setBorder(new MatteBorder(2, 2, 2, 0, Color.BLACK)); 
		casella5.setBounds(360, 30, 60, 90);
		casella5.setLayout(null);
		contentPane.add(casella5);
		
		JLabel stazione_nord = new JLabel("");
		stazione_nord.setIcon(new ImageIcon("./icons/Stazione_nord.png"));
		stazione_nord.setBounds(2, 2, 58, 86);
		casella5.add(stazione_nord);
		
		JPanel casella6 = new JPanel();
		casella6.setBorder(new MatteBorder(2, 2, 2, 0, Color.BLACK)); 
		casella6.setBounds(420, 30, 60, 90);
		casella6.setLayout(null);
		contentPane.add(casella6);
		
		JLabel bastioni_gran_sasso = new JLabel("");
		bastioni_gran_sasso.setIcon(new ImageIcon("./icons/Bastioni_Gran_Sasso.png"));
		bastioni_gran_sasso.setBounds(2, 2, 58, 86);
		casella6.add(bastioni_gran_sasso);
		
		JPanel casella7 = new JPanel();
		casella7.setBorder(new MatteBorder(2, 2, 2, 0, Color.BLACK)); 
		casella7.setBounds(480, 30, 60, 90);
		casella7.setLayout(null);
		contentPane.add(casella7);
		
		JLabel imprevisti1 = new JLabel("");
		imprevisti1.setIcon(new ImageIcon("./icons/imprevisti1-2.png"));
		imprevisti1.setBounds(2, 2, 58, 86);
		casella7.add(imprevisti1);
		
		JPanel casella8 = new JPanel();
		casella8.setBorder(new MatteBorder(2, 2, 2, 0, Color.BLACK)); 
		casella8.setBounds(540, 30, 60, 90);
		casella8.setLayout(null);
		contentPane.add(casella8);
		
		JLabel viale_monterosa = new JLabel("");
		viale_monterosa.setIcon(new ImageIcon("./icons/Viale_Monterosa.png"));
		viale_monterosa.setBounds(2, 2, 58, 86);
		casella8.add(viale_monterosa);
		
		JPanel casella9 = new JPanel();
		casella9.setBorder(new MatteBorder(2, 2, 2, 0, Color.BLACK)); 
		casella9.setBounds(600, 30, 60, 90);
		casella9.setLayout(null);
		contentPane.add(casella9);
		
		JLabel viale_vesuvio = new JLabel("");
		viale_vesuvio.setIcon(new ImageIcon("./icons/viale_vesuvio.png"));
		viale_vesuvio.setBounds(2, 2, 58, 86);
		casella9.add(viale_vesuvio);
		
		JPanel casella10 = new JPanel();
		casella10.setBorder(new MatteBorder(2, 2, 2, 2, Color.BLACK)); 
		casella10.setBounds(660, 30, 90, 90);
		casella10.setLayout(null);
		contentPane.add(casella10);
		
		JLabel transito = new JLabel("");
		transito.setIcon(new ImageIcon("./icons/transito.png"));
		transito.setBounds(2, 2, 86, 86);
		casella10.add(transito);

		JPanel casella11 = new JPanel();
		casella11.setBorder(new MatteBorder(0, 2, 2, 2, Color.BLACK)); 
		casella11.setBounds(660, 120, 90, 60);
		casella11.setLayout(null);
		contentPane.add(casella11);
		
		JLabel via_accademia = new JLabel("");
		via_accademia.setIcon(new ImageIcon("./icons/via_accademia.png"));
		via_accademia.setBounds(2, 0, 86, 58);
		casella11.add(via_accademia);
		
		JPanel casella12 = new JPanel();
		casella12.setBorder(new MatteBorder(0, 2, 2, 2, Color.BLACK)); 
		casella12.setBounds(660, 180, 90, 60);
		casella12.setLayout(null);
		contentPane.add(casella12);
		
		JLabel societa_elettrica = new JLabel("");
		societa_elettrica.setIcon(new ImageIcon("./icons/societa_elettrica.png"));
		societa_elettrica.setBounds(2, 0, 86, 58);
		casella12.add(societa_elettrica);
		
		JPanel casella13 = new JPanel();
		casella13.setBorder(new MatteBorder(0, 2, 2, 2, Color.BLACK)); 
		casella13.setBounds(660, 240, 90, 60);
		casella13.setLayout(null);
		contentPane.add(casella13);
		
		JLabel corso_ateneo = new JLabel("");
		corso_ateneo.setIcon(new ImageIcon("./icons/corso_ateneo.png"));
		corso_ateneo.setBounds(2, 0, 86, 58);
		casella13.add(corso_ateneo);
		
		JPanel casella14 = new JPanel();
		casella14.setBorder(new MatteBorder(0, 2, 2, 2, Color.BLACK)); 
		casella14.setBounds(660, 300, 90, 60);
		casella14.setLayout(null);
		contentPane.add(casella14);
		
		JLabel piazza_universita = new JLabel("");
		piazza_universita.setIcon(new ImageIcon("./icons/piazza_universita.png"));
		piazza_universita.setBounds(2, 0, 86, 58);
		casella14.add(piazza_universita);
		
		JPanel casella15 = new JPanel();
		casella15.setBorder(new MatteBorder(0, 2, 2, 2, Color.BLACK)); 
		casella15.setBounds(660, 360, 90, 60);
		casella15.setLayout(null);
		contentPane.add(casella15);
		
		JLabel stazione_est = new JLabel("");
		stazione_est.setIcon(new ImageIcon("./icons/stazione_est.png"));
		stazione_est.setBounds(2, 0, 86, 58);
		casella15.add(stazione_est);
		
		JPanel casella16 = new JPanel();
		casella16.setBorder(new MatteBorder(0, 2, 2, 2, Color.BLACK)); 
		casella16.setBounds(660, 420, 90, 60);
		casella16.setLayout(null);
		contentPane.add(casella16);
		
		JLabel via_verdi = new JLabel("");
		via_verdi.setIcon(new ImageIcon("./icons/via_verdi.png"));
		via_verdi.setBounds(2, 0, 86, 58);
		casella16.add(via_verdi);
		
		JPanel casella17= new JPanel();
		casella17.setBorder(new MatteBorder(0, 2, 2, 2, Color.BLACK)); 
		casella17.setBounds(660, 480, 90, 60);
		casella17.setLayout(null);
		contentPane.add(casella17);
		
		JLabel probabilita2 = new JLabel("");
		probabilita2.setIcon(new ImageIcon("./icons/Probabilita2.png"));
		probabilita2.setBounds(2, 0, 86, 58);
		casella17.add(probabilita2);
		
		JPanel casella18 = new JPanel();
		casella18.setBorder(new MatteBorder(0, 2, 2, 2, Color.BLACK)); 
		casella18.setBounds(660, 540, 90, 60);
		casella18.setLayout(null);
		contentPane.add(casella18);
		
		JLabel corso_raffaello = new JLabel("");
		corso_raffaello.setIcon(new ImageIcon("./icons/corso_raffaello.png"));
		corso_raffaello.setBounds(2, 0, 86, 58);
		casella18.add(corso_raffaello);
		
		JPanel casella19 = new JPanel();
		casella19.setBorder(new MatteBorder(0, 2, 0, 2, Color.BLACK)); 
		casella19.setBounds(660, 600, 90, 60);
		casella19.setLayout(null);
		contentPane.add(casella19);
		
		JLabel piazza_dante = new JLabel("");
		piazza_dante.setIcon(new ImageIcon("./icons/piazza_dante.png"));
		piazza_dante.setBounds(2, 0, 86, 60);
		casella19.add(piazza_dante);
		
		JPanel casella20 = new JPanel();
		casella20.setBorder(new MatteBorder(2, 2, 2, 2, Color.BLACK)); 
		casella20.setBounds(660, 660, 90, 90);
		casella20.setLayout(null);
		contentPane.add(casella20);
		
		JLabel parcheggio_gratuito = new JLabel("");
		parcheggio_gratuito.setIcon(new ImageIcon("./icons/parcheggio_gratuito.png"));
		parcheggio_gratuito.setBounds(2, 2, 86, 86);
		casella20.add(parcheggio_gratuito);
		
		JPanel casella21 = new JPanel();
		casella21.setBorder(new MatteBorder(2, 2, 2, 0, Color.BLACK)); 
		casella21.setBounds(600, 660, 60, 90);
		casella21.setLayout(null);
		contentPane.add(casella21);
		
		JLabel via_marco_polo = new JLabel("");
		via_marco_polo.setIcon(new ImageIcon("./icons/via_marco_polo.png"));
		via_marco_polo.setBounds(2, 2, 58, 86);
		casella21.add(via_marco_polo);
		
		JPanel casella22 = new JPanel();
		casella22.setBorder(new MatteBorder(2, 2, 2, 0, Color.BLACK)); 
		casella22.setBounds(540, 660, 60, 90);
		casella22.setLayout(null);
		contentPane.add(casella22);
		
		JLabel imprevisti2 = new JLabel("");
		imprevisti2.setIcon(new ImageIcon("./icons/imprevisti1-2.png"));
		imprevisti2.setBounds(2, 2, 58, 86);
		casella22.add(imprevisti2);
		
		JPanel casella23 = new JPanel();
		casella23.setBorder(new MatteBorder(2, 2, 2, 0, Color.BLACK)); 
		casella23.setBounds(480, 660, 60, 90);
		casella23.setLayout(null);
		contentPane.add(casella23);
		
		JLabel corso_magellano = new JLabel("");
		corso_magellano.setIcon(new ImageIcon("./icons/corso_magellano.png"));
		corso_magellano.setBounds(2, 2, 58, 86);
		casella23.add(corso_magellano);
		
		JPanel casella24 = new JPanel();
		casella24.setBorder(new MatteBorder(2, 2, 2, 0, Color.BLACK)); 
		casella24.setBounds(420, 660, 60, 90);
		casella24.setLayout(null);
		contentPane.add(casella24);
		
		JLabel largo_colombo = new JLabel("");
		largo_colombo.setIcon(new ImageIcon("./icons/largo _colombo.png"));
		largo_colombo.setBounds(2, 2, 58, 86);
		casella24.add(largo_colombo);
		
		JPanel casella25 = new JPanel();
		casella25.setBorder(new MatteBorder(2, 2, 2, 0, Color.BLACK)); 
		casella25.setBounds(360, 660, 60, 90);
		casella25.setLayout(null);
		contentPane.add(casella25);
		
		JLabel stazione_sud = new JLabel("");
		stazione_sud.setIcon(new ImageIcon("./icons/stazione_sud.png"));
		stazione_sud.setBounds(2, 2, 58, 86);
		casella25.add(stazione_sud);
		
		JPanel casella26 = new JPanel();
		casella26.setBorder(new MatteBorder(2, 2, 2, 0, Color.BLACK)); 
		casella26.setBounds(300, 660, 60, 90);
		casella26.setLayout(null);
		contentPane.add(casella26);
		
		JLabel viale_costantino = new JLabel("");
		viale_costantino.setIcon(new ImageIcon("./icons/viale_costantino.png"));
		viale_costantino.setBounds(2, 2, 58, 86);
		casella26.add(viale_costantino);
		
		JPanel casella27 = new JPanel();
		casella27.setBorder(new MatteBorder(2, 2, 2, 0, Color.BLACK)); 
		casella27.setBounds(240, 660, 60, 90);
		casella27.setLayout(null);
		contentPane.add(casella27);
		
		JLabel viale_traiano = new JLabel("");
		viale_traiano.setIcon(new ImageIcon("./icons/viale_traiano.png"));
		viale_traiano.setBounds(2, 2, 58, 86);
		casella27.add(viale_traiano);
		
		JPanel casella28 = new JPanel();
		casella28.setBorder(new MatteBorder(2, 2, 2, 0, Color.BLACK)); 
		casella28.setBounds(180, 660, 60, 90);
		casella28.setLayout(null);
		contentPane.add(casella28);
		
		JLabel societa_acqua_potabile = new JLabel("");
		societa_acqua_potabile.setIcon(new ImageIcon("./icons/societa_acqua_potabile.png"));
		societa_acqua_potabile.setBounds(2, 2, 59, 86);
		casella28.add(societa_acqua_potabile);
		
		JPanel casella29 = new JPanel();
		casella29.setBorder(new MatteBorder(2, 0, 2, 0, Color.BLACK)); 
		casella29.setBounds(120, 660, 60, 90);
		casella29.setLayout(null);
		contentPane.add(casella29);
		
		JLabel piazza_giulio_cesare = new JLabel("");
		piazza_giulio_cesare.setIcon(new ImageIcon("./icons/piazza_giulio_cesare.png"));
		piazza_giulio_cesare.setBounds(0, 2, 60, 86);
		casella29.add(piazza_giulio_cesare);

		JPanel casella30 = new JPanel();
		casella30.setBorder(new MatteBorder(2, 2, 2, 2, Color.BLACK)); 
		casella30.setBounds(30, 660, 90, 90);
		casella30.setLayout(null);
		contentPane.add(casella30);
		
		JLabel vai_in_prigione = new JLabel("");
		vai_in_prigione.setIcon(new ImageIcon("./icons/vai_in_prigione.png"));
		vai_in_prigione.setBounds(2, 2, 86, 86);
		casella30.add(vai_in_prigione);
		
		JPanel casella31 = new JPanel();
		casella31.setBorder(new MatteBorder(0, 2, 0, 2, Color.BLACK)); 
		casella31.setBounds(30, 600, 90, 60);
		casella31.setLayout(null);
		contentPane.add(casella31);
		
		JLabel via_roma = new JLabel("");
		via_roma.setIcon(new ImageIcon("./icons/via_roma.png"));
		via_roma.setBounds(2, 0, 86, 60);
		casella31.add(via_roma);
		
		JPanel casella32 = new JPanel();
		casella32.setBorder(new MatteBorder(0, 2, 2, 2, Color.BLACK)); 
		casella32.setBounds(30, 540, 90, 60);
		casella32.setLayout(null);
		contentPane.add(casella32);
		
		JLabel corso_impero = new JLabel("");
		corso_impero.setIcon(new ImageIcon("./icons/corso_impero.png"));
		corso_impero.setBounds(2, 0, 86, 58);
		casella32.add(corso_impero);
		
		JPanel casella33 = new JPanel();
		casella33.setBorder(new MatteBorder(0, 2, 2, 2, Color.BLACK)); 
		casella33.setBounds(30, 480, 90, 60);
		casella33.setLayout(null);
		contentPane.add(casella33);
		
		JLabel probabilita3 = new JLabel("");
		probabilita3.setIcon(new ImageIcon("./icons/probabilita3.png"));
		probabilita3.setBounds(2, 0, 86, 58);
		casella33.add(probabilita3);

		JPanel casella34 = new JPanel();
		casella34.setBorder(new MatteBorder(0, 2, 2, 2, Color.BLACK)); 
		casella34.setBounds(30, 420, 90, 60);
		casella34.setLayout(null);
		contentPane.add(casella34);
		
		JLabel largo_augusto = new JLabel("");
		largo_augusto.setIcon(new ImageIcon("./icons/largo_augusto.png"));
		largo_augusto.setBounds(2, 0, 86, 58);
		casella34.add(largo_augusto);
		
		JPanel casella35 = new JPanel();
		casella35.setBorder(new MatteBorder(0, 2, 2, 2, Color.BLACK)); 
		casella35.setBounds(30, 360, 90, 60);
		casella35.setLayout(null);
		contentPane.add(casella35);
		
		JLabel stazione_ovest = new JLabel("");
		stazione_ovest.setIcon(new ImageIcon("./icons/stazione_ovest.png"));
		stazione_ovest.setBounds(2, 0, 86, 58);
		casella35.add(stazione_ovest);
		
		JPanel casella36 = new JPanel();
		casella36.setBorder(new MatteBorder(0, 2, 2, 2, Color.BLACK)); 
		casella36.setBounds(30, 300, 90, 60);
		casella36.setLayout(null);
		contentPane.add(casella36);
		
		JLabel imprevisti3 = new JLabel("");
		imprevisti3.setIcon(new ImageIcon("./icons/imprevisti3.png"));
		imprevisti3.setBounds(2, 0, 86, 58);
		casella36.add(imprevisti3);

		JPanel casella37 = new JPanel();
		casella37.setBorder(new MatteBorder(0, 2, 2, 2, Color.BLACK)); 
		casella37.setBounds(30, 240, 90, 60);
		casella37.setLayout(null);
		contentPane.add(casella37);
		
		JLabel viale_dei_giardini = new JLabel("");
		viale_dei_giardini.setIcon(new ImageIcon("./icons/viale_dei_giardini.png"));
		viale_dei_giardini.setBounds(2, 0, 86, 58);
		casella37.add(viale_dei_giardini);
		
		JPanel casella38 = new JPanel();
		casella38.setBorder(new MatteBorder(0, 2, 2, 2, Color.BLACK)); 
		casella38.setBounds(30, 180, 90, 60);
		casella38.setLayout(null);
		contentPane.add(casella38);
		
		JLabel tassa_di_lusso = new JLabel("");
		tassa_di_lusso.setIcon(new ImageIcon("./icons/tassa_di_lusso.png"));
		tassa_di_lusso.setBounds(2, 0, 86, 58);
		casella38.add(tassa_di_lusso);
		
		JPanel casella39 = new JPanel();
		casella39.setBorder(new MatteBorder(0, 2, 2, 2, Color.BLACK)); 
		casella39.setBounds(30, 120, 90, 60);
		casella39.setLayout(null);
		contentPane.add(casella39);
		
		JLabel parco_della_vittoria = new JLabel("");
		parco_della_vittoria.setIcon(new ImageIcon("./icons/parco_della_vittoria.png"));
		parco_della_vittoria.setBounds(2, 0, 86, 58);
		casella39.add(parco_della_vittoria);
		
		
	}

}
