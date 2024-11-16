package View;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class CaseAlberghiView extends JPanel {
	
	public JLabel[] case1;
	public JLabel[] alberghi;

	
	public CaseAlberghiView() {
		
		case1=new JLabel[88];
		alberghi=new JLabel[22];
		
		for(int i=0; i<88; i++) {
			JLabel casa = new JLabel("");
			casa.setIcon(new ImageIcon("./icons/Case.png"));
			case1[i]=casa;
			
		}
		
		for(int i=0; i<22; i++) {
			JLabel albergo = new JLabel("");
			albergo.setIcon(new ImageIcon("./icons/Alberghi.png"));
			alberghi[i]=albergo;
		}

		int posCasa=0;
		int posAlbergo=0;
		int contatoreCase=0;
		int contatoreAlberghi=0;
		
		for(int z=0; z<5; z++) {
			
			if(z==0) {posCasa=122; posAlbergo=posCasa+20;}
			if(z==1) {posCasa=242; posAlbergo=posCasa+20;}
			if(z==2) {posCasa=422; posAlbergo=posCasa+20;}
			if(z==3) {posCasa=542; posAlbergo=posCasa+20;}
			if(z==4) {posCasa=602; posAlbergo=posCasa+20;}
			
			alberghi[contatoreAlberghi].setBounds(posAlbergo, 102, 20, 13);
			alberghi[contatoreAlberghi].setVisible(false);
			contatoreAlberghi++;
			for(int i=0; i<4; i++) {
				case1[contatoreCase].setBounds(posCasa+14*i, 102, 12, 12);
				case1[contatoreCase].setVisible(false);
				contatoreCase++;
			}
		}
		
		for(int z=0; z<6; z++) {
			
			if(z==0) {posCasa=120; posAlbergo=posCasa+20;}
			if(z==1) {posCasa=240; posAlbergo=posCasa+20;}
			if(z==2) {posCasa=300; posAlbergo=posCasa+20;}
			if(z==3) {posCasa=420; posAlbergo=posCasa+20;}
			if(z==4) {posCasa=540; posAlbergo=posCasa+20;}
			if(z==5) {posCasa=600; posAlbergo=posCasa+20;}
			
			alberghi[contatoreAlberghi].setBounds(665, posAlbergo, 13, 20);
			alberghi[contatoreAlberghi].setVisible(false);
			contatoreAlberghi++;
			for(int i=0; i<4; i++) {
				case1[contatoreCase].setBounds(665, posCasa+14*i, 12, 12);
				case1[contatoreCase].setVisible(false);
				contatoreCase++;
			}
		}
		
		for(int z=0; z<6; z++) {
			
			if(z==0) {posCasa=648; posAlbergo=posCasa-20;}
			if(z==1) {posCasa=528; posAlbergo=posCasa-20;}
			if(z==2) {posCasa=468; posAlbergo=posCasa-20;}
			if(z==3) {posCasa=348; posAlbergo=posCasa-20;}
			if(z==4) {posCasa=288; posAlbergo=posCasa-20;}
			if(z==5) {posCasa=168; posAlbergo=posCasa-20;}
			
			alberghi[contatoreAlberghi].setBounds(posAlbergo, 665, 20, 13);
			alberghi[contatoreAlberghi].setVisible(false);
			contatoreAlberghi++;
			for(int i=0; i<4; i++) {
				case1[contatoreCase].setBounds(posCasa-i*14, 665, 12, 12);
				case1[contatoreCase].setVisible(false);
				contatoreCase++;
			}
		}
		
		for(int z=0; z<5; z++) {
			
			if(z==0) {posCasa=647; posAlbergo=posCasa-20;}
			if(z==1) {posCasa=585; posAlbergo=posCasa-20;}
			if(z==2) {posCasa=465; posAlbergo=posCasa-20;}
			if(z==3) {posCasa=284; posAlbergo=posCasa-20;}
			if(z==4) {posCasa=166; posAlbergo=posCasa-20;}

			alberghi[contatoreAlberghi].setBounds(104, posAlbergo, 13, 20);
			alberghi[contatoreAlberghi].setVisible(false);
			contatoreAlberghi++;
			for(int i=0; i<4; i++) {
				case1[contatoreCase].setBounds(104, posCasa-i*14, 12, 12);
				case1[contatoreCase].setVisible(false);
				contatoreCase++;
			}
		}

	}
	
	public void mostraCasa(int i) {
		case1[i].setVisible(true);
		}
	
	public void mostraAlbergho(int i) {
		alberghi[i].setVisible(true);
		}
	
	public void rimuoviCasa(int i) {
		case1[i].setVisible(false);
		}
	
	public void rimuoviAlbergho(int i) {
		alberghi[i].setVisible(false);
		}

	public JLabel getCasa(int i) {
		JLabel prova=case1[i];
		return prova;
		
	}
	
	public JLabel getAlbergo(int i) {
		JLabel prova=alberghi[i];
		return prova;
	}
	
	
}
