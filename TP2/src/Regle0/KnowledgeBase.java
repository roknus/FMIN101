package Regle0;

import java.io.*;
import java.util.ArrayList;

public class KnowledgeBase {
	private FactBase fb;
	private RuleBase rb;
	
	public KnowledgeBase() {
		fb = new FactBase();
		rb= new RuleBase();
	}

	public KnowledgeBase(String s)throws IOException{
		BufferedReader lecturefichier = new BufferedReader(
											new FileReader(s));
		System.out.println("lecture du fichier "+s);
		
		String l = lecturefichier.readLine();
		fb = new FactBase(l);
		rb = new RuleBase();
		
		l = lecturefichier.readLine();
		while(l!=null){			
			rb.addRule(new Rule(l));
			l = lecturefichier.readLine();
		}
		lecturefichier.close();
		System.out.println("Fichier ferme");
		
	}

	public FactBase getFb() {
		return fb;
	}

	public void setFb(FactBase fb) {
		this.fb = fb;
	}

	public RuleBase getRb() {
		return rb;
	}

	public void setRb(RuleBase rb) {
		this.rb = rb;
	}
	
	public void addRule(Rule r){
		rb.addRule(r);
	}
	
	public void addFact(Atom a){
		fb.addAtom(a);
	}
	
	public String toString(){
		return fb.toString()+"\n"+rb.toString();
	}
	
	public void ForwardChaining(){
		ArrayList<Atom> Atraiter = new ArrayList<Atom>(fb.getAtoms());
		
		int[] compteur = new int[rb.size()];
		
		for(int i=0; i<rb.size() ; i++)
		{
			compteur[i] = rb.getRule(i).getHypothesis().size();
		}
		
		while(!Atraiter.isEmpty())
		{
			Atom t = Atraiter.get(0);
			Atraiter.remove(0);
			
			for(int i=0; i< rb.size();i++)
			{
				Rule r = rb.getRule(i);
				for(Atom a : r.getHypothesis())
				{
					if(a.equalsA(t))
					{
						compteur[i]--;					
						if(compteur[i] == 0)
						{
							if(!fb.belongsAtom(r.getConclusion()))
							{	
								Atraiter.add(r.getConclusion());
								fb.addAtom(r.getConclusion());
							}
						}
					}
				}
			}
		}
		
	}
	
	public void instanciation()
	{//Instancie tout les regles a partir de la liste de constantes
		boolean add = true;
		//Ajoute les constantes de la base de fait
		ArrayList<Term> constants = new ArrayList<Term>(fb.getTerms());
		for(Rule r : rb.getRules())
		{//Parcour les termes de la base de regles et les ajoutes si ce sont des constantes
			for(Term t : r.getTerms())
			{
				if(t.isConstant())
				{
					add = true;
					for(int i=0;i<constants.size();i++)
					{// Vérifie que la liste ne contient pas déja la constante
						if( t.equalsT(constants.get(i)) )
						{
							add = false;
						}
					}
					if(add)
					{
						constants.add(t);
					}
				}
			}
		}
		RuleBase newRb = new RuleBase();
		for(Rule r : rb.getRules())
		{
			Substitutions s = new Substitutions(constants,r.getTerms());
			s.generateAllSubstitutions();
			System.out.println(r);//Affiche la regle
			System.out.println(s);//Affiche l'ensemble des substitution
			for(Substitution sub : s.getSubstitutions())
			{//Instancie toute les regles et genere la base de regle d'ordre 0
				Rule newR = new Rule(r);
				instanciationRegle(newR,sub);
				newRb.addRule(newR);
			}
		}
		rb = newRb;
	}
	
	private void instanciationRegle(Rule r, Substitution s)
	{
		for(int i = 0; i < r.getTerms().size() ; i++)
		{// Remplace les termes par des constantes
			Term t = r.getTerms().get(i);
			if( r.getTerms().get(i).isVariable() )
			{
				r.getTerms().set(i, s.getTerm(t));				
			}
		}
		for(Atom a : r.getHypothesis())
		{// Remplace les hypothese par des constantes
			for(int i = 0; i < a.getArity() ; i++)
			{				
				if(a.getArgI(i).isVariable() )
				{
					a.setArgI(i, s.getTerm(a.getArgI(i)));
				}
			} 
		}
		for(int i = 0 ; i < r.getConclusion().getArity(); i ++)
		{// Remplace la conclusion par des constantes
			if( r.getConclusion().getArgI(i).isVariable())
			{
				r.getConclusion().setArgI(i, s.getTerm(r.getConclusion().getArgI(i)));
			}
		}
	}
	
	public void FC_Order1()
	{
		this.instanciation();
		this.ForwardChaining();
	}
}
