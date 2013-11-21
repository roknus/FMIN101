package Regle0;

import java.util.*;

public class Substitution
{
	protected ArrayList<CoupleTerms> couples;
	
	public Substitution()
	{
		couples = new ArrayList<CoupleTerms>();
	}

	public Substitution(Substitution s) {
		this.couples = new ArrayList<CoupleTerms>();
		for(CoupleTerms ct : s.getCouples())
		{
			this.couples.add(ct);
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
