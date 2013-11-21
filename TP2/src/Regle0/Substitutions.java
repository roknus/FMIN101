package Regle0;

import java.util.*;

public class Substitutions 
{
	protected ArrayList<Term> constants;
	protected ArrayList<Term> variables;
	protected ArrayList<Substitution> substitutions;
	
	public Substitutions()
	{
		this.substitutions = new ArrayList<Substitution>();
		this.constants = new ArrayList<Term>();
		this.variables = new ArrayList<Term>();
	}
	
	public Substitutions(ArrayList<Term> constants, ArrayList<Term> var)
	{
		this.substitutions = new ArrayList<Substitution>();
		this.constants = constants;
		this.variables = new ArrayList<Term>();
		
		for(Term v : var)
		{
			if(v.isVariable())
			{
				variables.add(v);
			}
		}
	}

	public ArrayList<Substitution> getSubstitutions() {
		return substitutions;
	}

	public void setSubstitutions(ArrayList<Substitution> substitutions) {
		this.substitutions = substitutions;
	}

	public ArrayList<Term> getTerms() {
		return constants;
	}

	public void setTerms(ArrayList<Term> terms) {
		this.constants = terms;
	}

	public ArrayList<Term> getVariables() {
		return variables;
	}

	public void setVariables(ArrayList<Term> variables) {
		this.variables = variables;
	}
	
	public void addSubstitution(Substitution s)
	{
		this.substitutions.add(s);
	}
	
	public void generateAllSubstitutions()
	{
		Substitution s = new Substitution();
		generateRec(s,0,0);
	}
	
	private void generateRec(Substitution s, int var, int constant)
	{
		
		if (var >= variables.size())
		{
			substitutions.add(s);
		}
		else
		{
			if(constant < constants.size())
			{
				Substitution s2 = new Substitution(s);
				Substitution s3 = new Substitution(s);
				CoupleTerms couple = new CoupleTerms(variables.get(var),constants.get(constant));
				s3.addCouples(couple);
				generateRec(s2,var,constant+1);	
				generateRec(s3,var+1,0);	
			}
		}
	}
	
	public String toString()
	{
		String str = "";
		for(Substitution s : substitutions)
		{
			str += s+"\n";
		}
		return str;
	}

}
