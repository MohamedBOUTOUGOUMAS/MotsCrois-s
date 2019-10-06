package pobj.motx.tme3.csp;

import java.util.List;

import pobj.motx.tme2.GrillePotentiel;

public class DicoVariable implements IVariable {
	private int index;
	private GrillePotentiel gp;

	public DicoVariable(int index, GrillePotentiel gp) {
		this.index = index;
		this.gp = gp;
	}

	@Override
	public String toString() {
		return gp.getMotsPot().get(this.index).toString();
	}

	
	public List<String> getDomain() {
		return this.getGP().getMotsPot().get(this.index).getMots();
	}

	public GrillePotentiel getGP() {
		return this.gp;
	}
	public int getIndex(){
		return this.index;
	}
	public String afficheDic(){
		return gp.afficheGP();
	}
}
