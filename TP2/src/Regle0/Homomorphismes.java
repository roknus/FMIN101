package Regle0;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Homomorphismes 
{
	protected ArrayList<Term> constants;
	protected ArrayList<Term> variables;
	private FactBase A1;
	private FactBase A2;
	private ArrayList<ArrayList<CoupleTerms>> affectations;
	
	public Homomorphismes(String s) throws IOException
	{
		BufferedReader lecturefichier = new BufferedReader(new FileReader(s));
		System.out.println("lecture du fichier "+s);

		A1 = new FactBase(lecturefichier.readLine());
		A2 = new FactBase(lecturefichier.readLine());
		
		lecturefichier.close();
		System.out.println("Fichier ferme");
		
		affectations = new ArrayList<ArrayList<CoupleTerms>>();
		
		this.constants = A2.getTerms();
		this.variables = new ArrayList<Term>();
		
		for(Term v : A1.getTerms())
		{
			if(v.isVariable())
			{
				variables.add(new Term(v.getLabel(),v.isConstant()));
			}
		}
		
	}

//	private void affectations() 
//	{
//		for(Atom a : A1.getAtoms())
//		{
//			for(Atom aa : A2.getAtoms())
//			{
//				if(a.getPredicate().equals(aa.getPredicate()))
//				{
//					ArrayList<CoupleTerms> aff = new ArrayList<CoupleTerms>();
//					for(int i=0; i< a.getArgs().size(); i++)
//					{
//						Term t = a.getArgI(i);
//						if(t.isVariable())
//						{
//							CoupleTerms ct = new CoupleTerms(t,aa.getArgI(i));
//							aff.add(ct);// TODO faire en sorte de pas rajouter de doublons
//						}
//					}
//					affectations.add(aff);
//				}				
//			}
//		}
//	}

	public boolean backtrack()
	{
//		affectations();
		Homomorphisme h = new Homomorphisme();
		return backtrackRec(h,0,0);	
	}
	
	public boolean backtrackRec(Homomorphisme h, int var, int constant)
	{
		if (var >= variables.size())
		{
			return true;
		}
		else
		{
			if(constant < constants.size())
			{
				Homomorphisme h2 = new Homomorphisme(h);
				Homomorphisme h3 = new Homomorphisme(h);
				CoupleTerms couple = new CoupleTerms(variables.get(var),constants.get(constant));
				h3.addCouples(couple);
				System.out.println(h3);
				if(h3.isHomomorphismePartiel(A1.getAtoms(), A2.getAtoms()))
				{
					if(backtrackRec(h3,var+1,0) == true) return true;
				}
				System.out.println(" ---- ");
				return backtrackRec(h2,var,constant+1);
			}
			return false;
		}
	}
	
	public FactBase getA2() {
		return A2;
	}
	public void setA2(FactBase a2) {
		A2 = a2;
	}
	public FactBase getA1() {
		return A1;
	}
	public void setA1(FactBase a1) {
		A1 = a1;
	}
	public String toString(){
		return A1.toString()+"\n"+A2.toString();
	}

	public ArrayList<Term> getConstants() {
		return constants;
	}

	public void setConstants(ArrayList<Term> constants) {
		this.constants = constants;
	}

	public ArrayList<Term> getVariables() {
		return variables;
	}

	public void setVariables(ArrayList<Term> variables) {
		this.variables = variables;
	}

	public ArrayList<ArrayList<CoupleTerms>> getAffectations() {
		return affectations;
	}

	public void setAffectations(ArrayList<ArrayList<CoupleTerms>> affectations) {
		this.affectations = affectations;
	}
	
}
