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
		KnowledgeBase kb = new KnowledgeBase("animal.txt");
		System.out.println(kb);
		kb.FC_Order1();
		System.out.println(kb);
	}
}
