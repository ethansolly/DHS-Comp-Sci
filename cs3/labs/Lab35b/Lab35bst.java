package labs.Lab35b;// Lab35bst.java
// The Binary Expression Tree Evaluation Program
// Student Version 


import java.util.*;
import java.text.DecimalFormat;

      
public class Lab35bst
{
	public static void main(String args[])
	{
		ExpressionNode root = ExpressionTree.createTree();
		
		System.out.println("Original Tree");
		System.out.println("=============");
		System.out.print("\nIn-Fix Notation:    ");
		ExpressionTree.inOrderTraverse(root);
		System.out.print("\n\nPre-Fix Notation:   ");
		ExpressionTree.preOrderTraverse(root);
		System.out.print("\n\nPost-Fix Notation:  ");		
		ExpressionTree.postOrderTraverse(root);
		System.out.println("\n");		
		
		System.out.println("This tree has "+
		    ExpressionTree.operatorCount(root)+" operators and "+
		   	ExpressionTree.numberCount(root)+" numbers, and evaluates to "+
		    ExpressionTree.evaluate(root)+"\n\n"); 
		    
		ExpressionNode mirrorRoot = ExpressionTree.mirror(root);

		System.out.println("\nMirror Tree");
		System.out.println("===========");
		System.out.print("\nIn-Fix Notation:    ");
		ExpressionTree.inOrderTraverse(mirrorRoot);
		System.out.print("\n\nPre-Fix Notation:   ");
		ExpressionTree.preOrderTraverse(mirrorRoot);
		System.out.print("\n\nPost-Fix Notation:  ");
		ExpressionTree.postOrderTraverse(mirrorRoot);
		System.out.println("\n");
		
		System.out.println("This tree has "+
		    ExpressionTree.operatorCount(mirrorRoot)+" operators and "+
		   	ExpressionTree.numberCount(mirrorRoot)+" numbers, and evaluates to "+
		    ExpressionTree.evaluate(mirrorRoot)+"\n");
	}
}


class ExpressionNode
{
	public ExpressionNode(double initNum, char initOpr, ExpressionNode initLeft, ExpressionNode initRight)
	{
		num = initNum;
		opr = initOpr;
		left = initLeft;
		right = initRight;
	}
	
	public double getNum()								{ return num; 			}
	public char getOpr()								{ return opr;			}
	public ExpressionNode getLeft()						{ return left; 			}
	public ExpressionNode getRight()					{ return right; 		}
	public void setNum(int theNewNum)					{ num = theNewNum; 		}
	public void setOpr(char theNewOpr)					{ opr = theNewOpr;		}
	public void setLeft(ExpressionNode theNewLeft)		{ left = theNewLeft; 	}
	public void setRight(ExpressionNode theNewRight)	{ right = theNewRight; 	}

	public boolean isOperator() {
		return opr != ' ';
	}

	public String getEntry() {
		if (isOperator())
			return "" + opr;
		else return "" + num;
	}

	private double num;
	private char opr;
	private ExpressionNode left;
	private ExpressionNode right;
}





class ExpressionTree
{   
	public static ExpressionNode createTree()
	{
		ExpressionNode n12  = new ExpressionNode(12,' ',null,null);
		ExpressionNode n6   = new ExpressionNode( 6,' ',null,null);
		ExpressionNode ndiv = new ExpressionNode( 0,'/',n12,n6);
		ExpressionNode n7   = new ExpressionNode( 7,' ',null,null);
		ExpressionNode nadd = new ExpressionNode( 0,'+',n7,ndiv);
		ExpressionNode n2   = new ExpressionNode( 2,' ',null,null);
		ExpressionNode n4   = new ExpressionNode( 4,' ',null,null);
		ExpressionNode ncar = new ExpressionNode( 0,'^',n2,n4);
		ExpressionNode n5   = new ExpressionNode( 5,' ',null,null);
		ExpressionNode nsub = new ExpressionNode( 0,'-',ncar,n5);
		ExpressionNode root = new ExpressionNode( 0,'*',nadd,nsub);
		return root;
	}

	
	public static void inOrderTraverse (ExpressionNode p)
	{
		if (p != null) {
			System.out.print("(");
			inOrderTraverse(p.getLeft());
			System.out.print(p.getEntry() + " ");
			inOrderTraverse(p.getRight());
			System.out.print(")");
		}
	}
	

	public static void preOrderTraverse (ExpressionNode p)
	{
		if (p != null) {
			System.out.print(p.getEntry() + " ");
			preOrderTraverse(p.getLeft());
			preOrderTraverse(p.getRight());
		}
	}
	
	public static void postOrderTraverse (ExpressionNode p)
	{
		if (p != null) {
			postOrderTraverse(p.getLeft());
			postOrderTraverse(p.getRight());
			System.out.print(p.getEntry() + " ");
		}
	}		
	
	public static int numberCount (ExpressionNode p)
	{
		int i = 0;
		if (p != null) {
			if (!p.isOperator())
				i++;
			i += numberCount(p.getLeft());
			i += numberCount(p.getRight());
		}
		return i;
	}	
			
	public static int operatorCount (ExpressionNode p)
	{
		int i = 0;
		if (p != null) {
			if (p.isOperator())
				i++;
			i += operatorCount(p.getLeft());
			i += operatorCount(p.getRight());
		}
		return i;
	}			
	
	public static double evaluate (ExpressionNode p)
	{
		double ret = 0;
		if (p != null) {
			if (p.isOperator()) {
				char o = p.getOpr();
				if (o == '^')
					ret += Math.pow(evaluate(p.getLeft()), evaluate(p.getRight()));
				if (o == '*')
					ret += evaluate(p.getLeft()) * evaluate(p.getRight());
				if (o == '/')
					ret += evaluate(p.getLeft()) / evaluate(p.getRight());
				if (o == '+')
					ret += evaluate(p.getLeft()) + evaluate(p.getRight());
				if (o == '-')
					ret += evaluate(p.getLeft()) - evaluate(p.getRight());
			}
			else
				ret += p.getNum();
		}
		return ret;
	}
	
	public static ExpressionNode mirror(ExpressionNode p)
	{
		if (p != null)
			return new ExpressionNode(p.getNum(), p.getOpr(), mirror(p.getRight()), mirror(p.getLeft()));
		else return null;
	}
}    

