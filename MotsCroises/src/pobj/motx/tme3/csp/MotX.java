package pobj.motx.tme3.csp;

import java.util.ArrayList;
import java.util.List;

import pobj.motx.tme1.Emplacement;
import pobj.motx.tme2.GrillePotentiel;

public class MotX implements ICSP{
	private List<DicoVariable> dicoVariable = new ArrayList<DicoVariable>();
	GrillePotentiel gp;
	public MotX(GrillePotentiel gp){
		this.gp = gp;
		for(int i=0; i<gp.getGrilleActuelle().getPlaces().size(); i++){
			Emplacement emp = gp.getGrilleActuelle().getPlaces().get(i);
			for(int j=0; j<emp.size(); j++){
				if(emp.getList().get(j).isVide()){
					dicoVariable.add(new DicoVariable(i, gp));
					break; // **** +++++++++++++++++++++++++++++++++++++
				}
			}
		}
		
	}


	@Override
	public List<IVariable> getVars() {
		List<IVariable> l = new ArrayList<>();
		for (IVariable i : this.dicoVariable) {
			l.add(i);
		}
		return l;
	}

	@Override
	public boolean isConsistent() {
		for(int i=0; i<this.dicoVariable.size(); i++) {
			if(this.dicoVariable.get(i).getGP().isDead()) {
				return false;
			}
		}
		return true;
	}

	@Override
	public ICSP assign(IVariable vi, String val) {
		MotX m1 = null;
		//for (int i = 0; i<this.dicoVariable.size();i++){
			if(vi instanceof DicoVariable){
				m1 = new MotX(((DicoVariable) vi).getGP().fixer(((DicoVariable) vi).getIndex(), val));
			}
		//}
		return m1;
	}
	@Override
	public String toString(){
		//return (String)dicoVariable.get(0).getGP().getGrilleActuelle().getGrille().toString();
		return gp.afficheGP();
		//return 
	}
	
}
