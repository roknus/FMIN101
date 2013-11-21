package Regle0;

import java.util.ArrayList;

public class Homomorphisme 
{
	private  ArrayList<CoupleTerms> couples;
	
	public Homomorphisme()
	{
		couples = new ArrayList<CoupleTerms>();	
	}
	public Homomorphisme(Homomorphisme h)
	{
		this.couples = new ArrayList<CoupleTerms>();
		for(CoupleTerms ct : h.getCouples())
		{
			this.couples.add(ct);
		}
	}

	public boolean isHomomorphismePartiel(ArrayList<Atom> atoms, ArrayList<Atom> atoms2, int order) 
	{
		boolean Atomfound;
		for(Atom a : atoms)
		{
			if(a.getOrder() >= order)
			{
				Atomfound = false;
				Atom a2 = new Atom(a);
				instanciation(a2);
				for(Atom aa : atoms2)
				{
					if(a2.equalsAConstant(aa))
					{
						Atomfound = true;
						break;
					}
				}
				if(!Atomfound)
				{
					return false;
				}
			}
		}
		return true;
	}
	
	public void instanciation(Atom a)
	{
		for(Term t : a.getArgs())
		{
			for(CoupleTerms cp : couples)
			{
				if(t.getLabel().equals(cp.getVariable().getLabel()))
				{
					t.setConstant();
					t.setLabel(cp.getConstant().getLabel());
				}			
			}
		}
	}
	
	public ArrayList<CoupleTerms> getCouples() {
		return couples;
	}
	public void setCouples(ArrayList<CoupleTerms> couples) {
		this.couples = couples;
	}
	public void addCouples(CoupleTerms cp)
	{
		this.couples.add(cp);
	}
	public Term getTerm(Term variable)
	{
		for(CoupleTerms cp : couples)
		{
			if( cp.getVariable().equalsT(variable) )
			{
				return cp.getConstant();
			}
		}
		return null;
	}
	
	public String toString()
	{
		String str = "{";
		for(CoupleTerms c : couples)
		{
			str += c + ",";
		}
		return str+"}";
	}
}
