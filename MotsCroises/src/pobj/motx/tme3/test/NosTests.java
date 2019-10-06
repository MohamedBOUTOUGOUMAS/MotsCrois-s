package pobj.motx.tme3.test;


import static org.junit.Assert.*;

import org.junit.Test;

import com.oracle.xmlns.internal.webservices.jaxws_databinding.ExistingAnnotationsType;

import pobj.motx.tme1.Grille;
import pobj.motx.tme1.GrilleLoader;
import pobj.motx.tme1.GrillePlaces;
import pobj.motx.tme2.Dictionnaire;
import pobj.motx.tme2.GrillePotentiel;
import pobj.motx.tme3.csp.MotX;
import pobj.motx.tme3.csp.CSPSolver;
import pobj.motx.tme3.csp.ICSP;

public class NosTests {
	public static String [] file={"easy","easy2","enonce","hard","large2","large4","larger","medium","propage","reverse","split"};
	@Test
	public void testEasy2() {
		for(String f : file){
			Dictionnaire gut = Dictionnaire.loadDictionnaire("data/frgut.txt");
			Grille gr = GrilleLoader.loadGrille("data/"+f+".grl");
			
			System.out.println(gr);
			
			GrillePlaces grille = new GrillePlaces(gr);
			GrillePotentiel gp = new GrillePotentiel(grille, gut);
			
			 System.out.println(gp);
			 //assertTrue(! gp.isDead());
			
			ICSP problem = new MotX(gp);
			CSPSolver solver = new CSPSolver();
			
			 //solver.setStrat(new StratFirst());
			 //solver.setStrat(new StratMin());
			
			long timestamp = System.currentTimeMillis();
			ICSP solution = solver.solve(problem);
			System.out.println("Solution \n" + solution + " \nCalculée en "+ (System.currentTimeMillis() - timestamp) +" ms " );
		}
	}

}