package Regle0;

import java.util.*;

public class Substitution
{
	private ArrayList<CoupleTerms> couples;
	
	public Substitution()
	{
		couples = new ArrayList<CoupleTerms>();
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
}
