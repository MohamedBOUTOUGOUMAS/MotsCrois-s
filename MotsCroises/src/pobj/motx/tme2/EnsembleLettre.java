package pobj.motx.tme2;

import java.util.ArrayList;
import java.util.List;

public class EnsembleLettre {
	private List<Character> ensembleL = new ArrayList<Character>();
	
	public boolean add(Character c){
		if (this.ensembleL.contains(c)){
			return false;
		}
		else{
			this.ensembleL.add(c);
			return true;
		}
	}
	
	public EnsembleLettre intersection(EnsembleLettre e){
		if ((this.size() == 0) || (e.size() ==0))
			return new EnsembleLettre();
		else{
			EnsembleLettre cop = this.copy();
			cop.ensembleL.retainAll(e.ensembleL);
			return cop;
		}
	}
	public EnsembleLettre aSansB(EnsembleLettre e){
		if ((this.size() == 0))
			return new EnsembleLettre();
		else{
			EnsembleLettre cop = this.copy();
			cop.ensembleL.removeAll(e.ensembleL);
			return cop;
		}
	}
	public boolean contains(Character c){
		return this.ensembleL.contains(c);
	}
	
	public int size(){
		return this.ensembleL.size();
	}
	public EnsembleLettre copy(){
		EnsembleLettre cop = new EnsembleLettre();
		for(int i=0; i<this.size(); i++){
			cop.add(this.ensembleL.get(i));
		}
		return cop;
	}
	
	public List<Character> getEnsembleL(){
		return this.ensembleL;
	}
}