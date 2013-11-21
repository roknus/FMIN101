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
	public boolean isHomomorphismePartiel(ArrayList<CoupleTerms> affectations) 
	{
		boolean test;
		for(CoupleTerms ct : couples)
		{
			test = false;
			for(CoupleTerms ct2 : couples)
			{
				if(ct.equalsCT(ct2))
				{
					test = true;
				}
			}
			if(!test)
			{
				return false;
			}
		}
		return true;
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
