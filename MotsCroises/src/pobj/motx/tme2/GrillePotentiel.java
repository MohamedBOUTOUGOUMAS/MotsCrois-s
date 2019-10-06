package pobj.motx.tme2;

import java.util.ArrayList;
import java.util.List;

import pobj.motx.tme1.Case;
import pobj.motx.tme1.Emplacement;
import pobj.motx.tme1.GrillePlaces;

public class GrillePotentiel {

	private GrillePlaces grilleActuelle;
	private Dictionnaire dictionnaireFrancais;
	private List<Dictionnaire> motsPot = new ArrayList<>();
	private List<IContrainte> contraintes = new ArrayList<>();;
	//private List<CroixContrainte> contraintes = new ArrayList<>();
	
	 public GrillePotentiel(GrillePlaces grille, Dictionnaire dicoComplet) {
		 this.grilleActuelle = grille;
		 this.dictionnaireFrancais = dicoComplet;
		 for(int i = 0; i < this.grilleActuelle.getPlaces().size(); i++) {
			 Dictionnaire d = new Dictionnaire();
			 d = this.dictionnaireFrancais.copy();
			 int len = this.grilleActuelle.getPlaces().get(i).getList().size();
			 d.filtreLongueur(len);
			 for (int j=0 ; j<this.grilleActuelle.getPlaces().get(i).getList().size();j++) {
				 Case case_expect = this.grilleActuelle.getPlaces().get(i).getList().get(j);
				 if(!case_expect.isVide()) {
					 d.filtreParLettre(case_expect.getChar(), j);
				 }
			 }
			 this.motsPot.add(d);
		 }
		 for(int m1=0; m1<this.grilleActuelle.getNbHorizontal(); m1++){
			 Emplacement motH = this.grilleActuelle.getPlaces().get(m1);
			 for(int m2=this.grilleActuelle.getNbHorizontal(); m2 < this.grilleActuelle.getPlaces().size(); m2++){
				 Emplacement motV = this.grilleActuelle.getPlaces().get(m2);
				 for(int c1=0; c1<motH.size(); c1++){
					 for(int c2=0; c2<motV.size(); c2++){
						 if (motH.getList().get(c1) == (motV.getList().get(c2))){//un croisement
							 int ligCase = motH.getList().get(c1).getLig();
							 int colCase = motH.getList().get(c1).getCol();
							 if(this.grilleActuelle.getGrille().getCase(ligCase, colCase).isVide()){
								 CroixContrainte c = new CroixContrainte(m1, c1, m2, c2);
								 contraintes.add(c);
							 }
						 }
					 }
				 }
			 }
		 }
		 this.propage();
	 }
	 //------------- ajoutee
	 public List<IContrainte> getContraintes(){
		 return this.contraintes;
	 }
	 //-----------------------------------------
	 
	 public List<Dictionnaire> getMotsPot(){
		 return this.motsPot;
	 }
	 public boolean isDead() {
		 for(int i=0; i<this.motsPot.size(); i++){
			 if (this.motsPot.get(i).size() == 0){
				 return true;
			 }
		 }
		 return false;
	 }
	
	 public GrillePotentiel fixer(int m, String soluce) {
		 	GrillePlaces gp = grilleActuelle.fixer(m, soluce);
		 	GrillePotentiel gpt = new GrillePotentiel(gp, this.dictionnaireFrancais.copy());
		 	return gpt;
	 }
	 
	 private boolean propage(){
		 int delete = 0;
		 while(true){
			 for(int j = 0; j< this.getContraintes().size();j++){
				  CroixContrainte c = (CroixContrainte) this.getContraintes().get(j);
				  delete += c.reduce(this);
			 }
			 
			 if(this.isDead()){
				 return false;
			 }
			 if(delete == 0){
				 return true;
			 }
			 delete=0;
		}
		
	 }
	 
	 public GrillePlaces getGrilleActuelle(){
		 return this.grilleActuelle;
	 }
	 
	 public String afficheGP(){
		 return grilleActuelle.afficheGPlace();
	 }
	 
	 
}
