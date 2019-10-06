package pobj.motx.tme1;

public class Case {
	private int lig;
	private int col;
	private char c;
	
	public Case(int lig, int col, char c){
		this.lig = lig;
		this.col = col;
		this.c = c;
	}
	
	public int getLig(){
		return this.lig;
	}
	
	public int getCol(){
		return this.col;
	}
	
	public char getChar(){
		return this.c;
	}
	
	public void setChar(char c){
		this.c = c;
	}
	
	public boolean isVide(){
		return (this.c == ' ');
	}
	
	public boolean isPleine(){
		return (this.c == '*');
	}
	//ajoutee
	@Override
	public boolean equals(Object other){

		if (this == other)
			return true;
		if (other == null)
			return false;
		if (!(other instanceof Case))
			return false;
		Case o = (Case)other;
		if(this.lig==o.lig && this.col==o.col && this.c==o.c)
			return true;
		else
			return false;
	}

}
