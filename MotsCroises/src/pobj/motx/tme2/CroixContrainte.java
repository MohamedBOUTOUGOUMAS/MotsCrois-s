package pobj.motx.tme2;

public class CroixContrainte implements IContrainte{
	private int m1;
	private int c1;
	private int m2;
	private int c2;
	
	public CroixContrainte(int m1, int c1, int m2, int c2){
		this.m1 = m1;
		this.c1 = c1;
		this.m2 = m2;
		this.c2 = c2;
	}
	
	public int reduce( GrillePotentiel grille){
		int cpt = 0;
		Dictionnaire mots1 = grille.getMotsPot().get(this.m1);
		Dictionnaire mots2 = grille.getMotsPot().get(this.m2);
		EnsembleLettre l1 = mots1.charAt(this.c1);
		EnsembleLettre l2 = mots2.charAt(this.c2);
		/*
		for (int i=0; i< mots1.size();i++){
			if (mots1.get(i).length() >= this.c1)
				//l1.add(mots1.get(i).charAt(c1));
				l1 = mots1.copy().charAt(this.c1);
		}
		for (int i=0; i< mots2.size();i++){
			if (mots2.get(i).length() >= this.c2)
				//l2.add(mots2.get(i).charAt(c2));
				l2 = mots2.copy().charAt(this.c2);
		}*/
		EnsembleLettre s = new EnsembleLettre();
		s = l1.intersection(l2);
		if (l1.size() > s.size()){
			//for (int i=0; i<s.size(); i++){
				cpt += mots1.filtreParLettre(s, this.c1);
			//}
		}
		if (l2.size() > s.size()){
			//for (int i=0; i<s.size(); i++){
				cpt += mots2.filtreParLettre(s, this.c2);
			//}
		}
		return cpt;
	}
	
	@Override
	public boolean equals(Object other){
		if (this == other)
			return true;
		if (other == null)
			return false;
		if (!(other instanceof CroixContrainte))
			return false;
		CroixContrainte o = (CroixContrainte)other;
		if(this.m1==o.m1 && this.c1==o.c1 && this.m2==o.m2 && this.c2==o.c2)
			return true;
		else
			return false;
	}
	

}