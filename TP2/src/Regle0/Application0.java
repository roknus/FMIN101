package Regle0;

import java.io.IOException;


public class Application0 {
	public static void main(String[] args) throws IOException
	{
		/*
		// creation de la base de faits de 2 facons differentes
		FactBase bf = new FactBase("A;B;C");
		System.out.println("Etat initial de la base de faits :\n"+bf);
	
		// creation de la base de regles
		RuleBase br = new RuleBase();
		br.addRule(new Rule("A;B;C"));
		System.out.println("Base de regles :\n"+br);
		*/
		// creation de la base de connaissance
//		KnowledgeBase kb = new KnowledgeBase("test.txt");
//		System.out.println(kb);
//		kb.FC_Order1();
//		System.out.println(kb);
//		KnowledgeBase kb = new KnowledgeBase("unification.txt");
//		System.out.println(kb);
//		Atom a1 = new Atom(kb.getRb().getRule(0).getAtomHyp(0));
//		Atom a2 = new Atom(kb.getRb().getRule(1).getAtomHyp(0));
//		if(a1.isUnifiable(a2))
//		{
//			System.out.println("Unifiable");
//		}
//		else
//		{
//			System.out.println("Pas unifiable");
//		}
		
		Homomorphismes h = new Homomorphismes("homomorphisme.txt");
		System.out.println(h);
		System.out.println(h.backtrack());
		System.out.println(h.getAffectations());
		
		
		
	}
}
