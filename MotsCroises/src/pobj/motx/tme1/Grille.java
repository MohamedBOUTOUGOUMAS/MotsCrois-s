package pobj.motx.tme1;

public class Grille {
	private Case[][] cases;
	
	public Grille(int hauteur, int largeur){
		cases = new Case[hauteur][largeur]; // donner les dimensions da matrice des cases
		for (int i=0; i<hauteur; i++){
			for (int j=0; j<largeur; j++){
				cases[i][j] = new Case(i, j, ' '); // initialiser la matrice a ' ' vide
			}
		}
	}
	
	public Case getCase(int lig, int col){
		return cases[lig][col];
	}
	
	
	@Override
	public String toString(){
		/*String str="";
		for(int i = 0; i < this.nbLig();i++){
			for(int j = 0; j < this.nbCol();j++){
			str += this.cases[i][j].getChar();
			}
			str += "\n";
		}
		return str;
		//return "pas fini";*/
		return GrilleLoader.serialize(this, false);
	}
	
	
	public int nbLig(){
		return cases.length;
	}
	
	public int nbCol(){
		return ( (cases.length==0) ? 0 : cases[0].length );
	}
	
	public Grille copy(){
		Grille g = new Grille(this.nbLig(), this.nbCol());
		for (int i=0; i<this.nbLig(); i++){
			for (int j=0; j<this.nbCol(); j++){
				g.cases[i][j].setChar(this.getCase(i,j).getChar());
			}
		}
		return g;
	}


}