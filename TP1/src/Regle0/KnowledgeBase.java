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
		ArrayList<Atom> Atraiter = new ArrayList(fb.getAtoms());
		
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
}
