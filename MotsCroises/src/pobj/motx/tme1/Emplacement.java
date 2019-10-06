package pobj.motx.tme1;

import java.util.List;

public class Emplacement {
	
	private List<Case> lettres;
	
	public Emplacement( List<Case> l) {
		this.lettres=l;
	}
	
	
	
	public List<Case> getList(){
		return lettres;
	}
	
	@Override
	public String toString() {
		String str=""; // la chaine a renvoyer 
		for(int i=0; i < this.getList().size();i++ ) {
			str += String.valueOf(this.getList().get(i).getChar())+" "; // ajouter chaque caractère a notre chaine
		}
		return str;
	}
	
	public int size() {
		return this.getList().size();
	}
}