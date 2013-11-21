package Regle0;

public class CoupleTerms {
	private Term variable;
	private Term constant;
	
	public CoupleTerms(Term variable, Term constant)
	{
		this.variable = variable;
		this.constant = constant;
	}
	
	public boolean equalsCT(CoupleTerms ct)
	{
		return (ct.getConstant().equalsT(constant) && ct.getVariable().equalsT(variable));
	}

	public Term getVariable() {
		return variable;
	}

	public void setVariable(Term variable) {
		this.variable = variable;
	}

	public Term getConstant() {
		return constant;
	}

	public void setConstant(Term constant) {
		this.constant = constant;
	}	
	
	public String toString()
	{
		return "("+variable+","+constant+")";
	}
}
