package pobj.motx.tme1;
import java.util.ArrayList;
import java.util.List;

public class GrillePlaces {
	
	private List<Emplacement> places= new ArrayList<>(); 
	private int listHorizontaux = 0; // nombre de mots horizontaux
	private int listVerticaux = 0;   // nombre de mots verticaux
	
	private Grille g;
	
	
	public GrillePlaces(Grille gr) {
		 this.g = gr.copy(); // faire une copie de la grille donnée en paramètre
		List<Case> l=new ArrayList<>(); // liste qui contiendra les caractères de la grille
		/* lancer une recherche horizontale */
		for(int i=0; i < this.g.nbLig();i++ ) { 
			for(int j=0; j < this.g.nbCol();j++ ) {
				l.add(this.g.getCase(i,j));
			}
			cherchePlaces(l); // recherer les mots qui existe dans la liste
			l.clear(); // vider la liste des mots pour pouvoir ajouter des autres de la ligne suivante
		}
		this.listHorizontaux = this.places.size(); // actualiser le nombre de mots horizontaux
		/* lancer une recherche verticale */
		for(int j=0; j < this.g.nbCol();j++ ) {
			for(int i=0; i < this.g.nbLig();i++ ) {
				l.add(this.g.getCase(i,j));
			}
			cherchePlaces(l);// recherer les mots qui existe dans la liste
			l.clear();// vider la liste des mots pour pouvoir ajouter des autres de la colonne suivante
		}
		this.listVerticaux = this.places.size()-this.listHorizontaux; // actualiser le nombre des mots verticaux
	}
	
	public List<Emplacement> getPlaces(){
		return places;
	}
	
	public int getNbHorizontal(){
		return this.listHorizontaux;
	}
	public int getNbVerticaux(){
		return this.listVerticaux;
	}

	// ------------------ ajoutee ----------
	public Grille getGrille(){
		return this.g;
	}
	//--------------------------
	
	public String toString(){
		String str="";
		for(int i = 0; i < getPlaces().size();i++){
			str += getPlaces().get(i).toString()+" ";
		}
		return str;
	}
	
	private void cherchePlaces(List<Case> cases) {
		Emplacement mot = new Emplacement(new ArrayList<>());
		for(int i=0; i<cases.size();i++) {
			if(cases.get(i).getChar()!='*') {
				mot.getList().add(cases.get(i));
			}else if(mot.size()>1) {
				/* faire un copie de l'emplacement pour éviter d'écraser sa valeur*/
				Emplacement v = new Emplacement(new ArrayList<>());
				for(int j=0; j<mot.getList().size();j++) {
					v.getList().add(mot.getList().get(j));
				}
				places.add(v); // ajouter la copie a l'ensemble des emplacements
				mot.getList().clear(); // vider la liste de caractères de notre mot pour pouvoir enregistrer d'autres
			}else{
				mot.getList().clear(); // vider la liste de caractères de notre mot pour pouvoir enregistrer d'autres
			}
		}
		/* pour pouvoir ajouter le dernier mot de la ligne de la grille a l'ensemble des emplacement*/
		if(mot.size()>=2) {
			Emplacement v = new Emplacement(new ArrayList<>()); // faire une copie avant d'ajouter
			for(int j=0; j<mot.getList().size();j++) {
				v.getList().add(mot.getList().get(j));
			}
			places.add(v);
			mot.getList().clear();
		}
	}
	
	public GrillePlaces fixer(int m, String soluce) {
		Grille g1 = this.g.copy();
		GrillePlaces gp = new GrillePlaces(g1);
		Emplacement mot = gp.getPlaces().get(m);
		for (int j=0; j < mot.size() ; j++) {
			mot.getList().get(j).setChar(soluce.charAt(j));
		}
		return gp;
	}
	public String afficheGPlace(){
		return g.toString();
	}
}