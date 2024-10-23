package Model;

public class Pedina {

	String nome;
	Casella posizione;
	boolean isTaken;
	Player proprietario;
	
	public Pedina(String nome, Casella posizione, Player proprietario) {
		this.nome = nome;
		this.posizione = posizione;
		this.isTaken = false;
		this.proprietario = proprietario;
	}
	
	
}
