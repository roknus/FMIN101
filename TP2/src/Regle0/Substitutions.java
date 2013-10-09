package Regle0;

import java.util.*;

public class Substitutions 
{
	private ArrayList<Term> terms;
	private ArrayList<Term> variables;
	private ArrayList<Substitution> substitutions;
	
	public Substitutions()
	{
		this.substitutions = new ArrayList<Substitution>();
		this.terms = new ArrayList<Term>();
		this.variables = new ArrayList<Term>();
	}

	public ArrayList<Substitution> getSubstitutions() {
		return substitutions;
	}

	public void setSubstitutions(ArrayList<Substitution> substitutions) {
		this.substitutions = substitutions;
	}

	public ArrayList<Term> getTerms() {
		return terms;
	}

	public void setTerms(ArrayList<Term> terms) {
		this.terms = terms;
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
		generateRec(s);
	}
	
	private void generateRec(Substitution s)
	{
		
	}

}