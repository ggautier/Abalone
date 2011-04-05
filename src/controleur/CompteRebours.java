package controleur;

import java.text.DecimalFormat;

public class CompteRebours extends Thread {

	protected long tempsDepart;
	protected long tempsRestant;
	protected boolean actif;
	
	public CompteRebours(int minutes, int secondes) {
		this.tempsRestant = minutes*60*1000 + secondes*1000;
		this.actif = false;

	}
	
	public void run() {
		while (true) {
			try {
				sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (this.actif) {
				this.tempsRestant -= System.currentTimeMillis() - tempsDepart;
				this.tempsDepart = System.currentTimeMillis();

			}
		}
		
	}
	
	public void stopper() {
		this.actif = false;	
	}
	
	public void demarrer() {
		this.tempsDepart = System.currentTimeMillis();

		this.actif = true;
		
	}

	public String toString() {
		DecimalFormat df = new DecimalFormat("00");
		
		if (tempsRestant >= 0)
			return df.format((tempsRestant/1000)/60)+" : "+df.format((tempsRestant/1000)%60);
		else
			return "00 : 00";
	}


	
}
