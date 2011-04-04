package modele;

import java.awt.Color;

public class Options {

	public String	nomJ1, nomJ2, IP, proxy;
	public int	    portEcoute, portProxy;
	public boolean	IA;
	public boolean 	uProxy;
	public Color	couleurJ1, couleurJ2;
	
	public Options() {
		this.nomJ1 = "Joueur1";
		this.nomJ2 = "Joueur2";
		this.IA = false;
		this.IP = "localhost";
		this.portEcoute = 300;
		this.uProxy = false;
		this.proxy = "cache.cites-u.univ-nantes.fr";
		this.portProxy = 3128;
	}

	public String getNomJ1() {
		return nomJ1;
	}

	public void setNomJ1(String nomJ1) {
		this.nomJ1 = nomJ1;
	}

	public String getNomJ2() {
		return nomJ2;
	}

	public void setNomJ2(String nomJ2) {
		this.nomJ2 = nomJ2;
	}

	public String getIP() {
		return IP;
	}

	public void setIP(String iP) {
		IP = iP;
	}

	public int getPortEcoute() {
		return portEcoute;
	}

	public void setPortEcoute(int portEcoute) {
		this.portEcoute = portEcoute;
	}

	public boolean isIA() {
		return IA;
	}

	public void setIA(boolean iA) {
		IA = iA;
	}

	public Color getCouleurJ1() {
		return couleurJ1;
	}

	public void setCouleurJ1(Color couleurJ1) {
		this.couleurJ1 = couleurJ1;
	}

	public Color getCouleurJ2() {
		return couleurJ2;
	}

	public void setCouleurJ2(Color couleurJ2) {
		this.couleurJ2 = couleurJ2;
	}

	public String getProxy() {
		return proxy;
	}

	public void setProxy(String proxy) {
		this.proxy = proxy;
	}

	public int getPortProxy() {
		return portProxy;
	}

	public void setPortProxy(int portProxy) {
		this.portProxy = portProxy;
	}

	public boolean isuProxy() {
		return uProxy;
	}

	public void setuProxy(boolean uProxy) {
		this.uProxy = uProxy;
	}
	
	
	
}
