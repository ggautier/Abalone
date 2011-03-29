package controleur;

public class CompteRebours implements Runnable {

	protected long tempsDepart;
	protected long tempsRestant;
	protected boolean actif;
	
	public CompteRebours(int minutes, int secondes) {
		this.tempsRestant = minutes*60*1000 + secondes*1000;
		this.actif = false;
	}
	
	public void run() {
		if (this.actif)
			this.tempsRestant -= System.currentTimeMillis() - tempsDepart;
	}
	
	public void stop() {
		this.actif = false;	
	}
	
	public void start() {
		this.actif = true;
		this.tempsDepart = System.currentTimeMillis();
	}

	public String toString() {
		if (tempsRestant >= 0)
			return (tempsRestant/1000)/60+" : "+(tempsRestant/1000)%60;
		else
			return "00 : 00";
	}


	
}
