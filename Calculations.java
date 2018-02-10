package edu.miracosta.cs113.finalproject;
/*
 * Class Calculations gui actionlistener = uses  string basicPattern() to display answer
 */
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Stack;

public class Calculations {
	
/**
 * @param:String recieves string to be calculated
 * returns string answer
 * uses hashtable chain to store history of operations of each equation
 * converts string to reverse polish notation
 * takes reverse polish notation and uses order of it to create answer
 **/
private String calculate(String s)
	{
		Scanner scanner = new Scanner(s);
		String answer="";
		String reversePolishNotation="";
		String operator = "";
		Stack<String> stack = new Stack<String>();
		ArrayList<String> operators  =new ArrayList<String>();
		//stores history of operations done
		HashtableChain<Integer,String> history = new HashtableChain<Integer,String>();
		int ind = 0;
		try
		{
			//scan string
			while(scanner.hasNext())
			{
				String temp = scanner.next();
				//go through string
				/*adds all numbers to reverse polishNotationSTring
				 * adds opertators to a list until a * or / is reached then adds all them to the reverse polish notation screen
				 * 
				 */
				switch(temp)
				{
				case "=": break;
				case "+": operators.add(" + ");break;
				case"-": operators.add(" - ");break;
				case "*": {
					operators.add("* ");
					reversePolishNotation+=scanner.next();
					String string = "";
					for(String t: operators)
					{
						string += t;
					}
					//should have used Stack so i could add all the components within the operators list
					String reverse = new StringBuffer(string).reverse().toString();
					reversePolishNotation+= reverse;
					operators.clear();			
				}
							break;
				case "/":
				{
					operators.add(" / ");
					reversePolishNotation+=scanner.next();
					String string = "";
					//put operators all into one string but operators are in reversed order 
					//then reverse string and add all to the reverse polish notation string
					for(String t: operators)
					{
						string += t;
					}
					String reverse = new StringBuffer(string).reverse().toString();
					
					reversePolishNotation+= reverse;
					operators.clear();				
				}
							break;
				default: reversePolishNotation+= temp+" ";
				}
				
			}
			//at very end of there are operators left over add them to the end of the notation string
			for(int i = 0;i<operators.size();i++)
			{
				reversePolishNotation+= operators.get(i)+" ";
			}
			operators.clear();	
			System.out.println("Reverse Polish Notation: "+reversePolishNotation);
			//begin 
			Scanner scanner2 = new Scanner(reversePolishNotation);
			String number1;
			double t1;
			String number2;
			double t2;
			double t3;
			double total = 0;
			
			while(scanner2.hasNext())
			{
				String x = scanner2.next();
				
				t3 = 0;
				switch (x) {
                case "+":
                    number1 = stack.pop();
                    
                    t1 = Double.parseDouble(number1);
                    number2 = stack.pop();
                    t2 = Double.parseDouble(number2);
                    
                    t3= t1+t2;
                    stack.add(Double.toString(t3));
                    history.put(ind++, t1+"+"+t2+"="+t3);
                    
                    total+=t3;
                    break;
                case "-":
                    number1 = stack.pop();
                    number2 = stack.pop();
                    t1 = Double.parseDouble(number1);
                    t2 = Double.parseDouble(number2);
                     t3= t2-t1;
                     history.put(ind++, t2+"-"+t1+"="+t3);
                     
                     stack.add(Double.toString(t3));
                     total+=t3;
                    break;
                case "/":
                    number1 = stack.pop();
                    number2 = stack.pop();
                    t1 = Double.parseDouble(number1);
                    t2 = Double.parseDouble(number2);
                     t3= t2/t1;
                     history.put(ind++, t2+"/"+t1+"="+t3);
                     if(t1==0)
                     {
                    	 throw new DivideByZeroException("divide by zero error");
                    	 
                     }
                     stack.add(Double.toString(t3));
                     total+=t3;
                    break;
                case "*":
                    number1 = stack.pop();
                    number2 = stack.pop();
                    t1 = Double.parseDouble(number1);
                    t2 = Double.parseDouble(number2);
                    t3= t1*t2;
                    history.put(ind++, t1+"*"+t2+"="+t3);
                    
                    stack.add(Double.toString(t3));
                     total+=t3;
                    break;
                case "": break;
                default: stack.push(x);break;
                }
				
				
			}
			answer = stack.pop();
			stack.clear();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		//display order of operations
		Iterator it = history.entrySet().iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
		System.out.println();
		return answer;
	}
/**
 * Recursive method
 * @param s (comes from jlabel: screen
 * goes  through equation string and replaces all cos sin tan e operations  and replaces with rounded decimal
 * @return calculate(s)
 */
public String basicPattern(String s)
{
	
	String original = s;
	String temp="";

	
	if(s.contains("cos"))
	{
		
		int begin =s.indexOf("cos");//inded of beginning of cos
		int end=begin+3;//index of end of cos number
		Scanner scan = new Scanner(s.substring(begin+3));
		scan.useDelimiter("");
		
		while(scan.hasNext()){
			temp = scan.next();
			end++;
			if(temp.equals("+")||temp.equals("-")||temp.equals("/")||temp.equals("*")||temp.equals(" ")){
				break;
			}
				
			
			
		}
		System.out.println("test");
		String cx = s.substring(begin+3,end);
		
		
		double x = Double.parseDouble(cx);
		
		double answer = cos(x);
		System.out.println(cx);
		DecimalFormat formatter = new DecimalFormat("###0.####");

		String ans = formatter.format(answer);
		String sub1 =s.substring(0,begin);
		String sub2 = s.substring(end);
		
		s = sub1+ans+" "+sub2;
		
		
		return basicPattern(s);
	}
	if(s.contains("sin"))
	{
		int begin =s.indexOf("sin");//inded of beginning of cos
		int end=begin+3;//index of end of cos number
		Scanner scan = new Scanner(s.substring(begin+3));
		scan.useDelimiter("");
		while(scan.hasNext()){
			temp = scan.next();
			end++;
			if(temp.equals("+")||temp.equals("-")||temp.equals("/")||temp.equals("*")||temp.equals(" ")){
				break;
			}
			
			
		}
		
		String cx = s.substring(begin+3,end);
		
	
		double x = Double.parseDouble(cx);
		
		double answer = sin(x);
		
		
		DecimalFormat formatter = new DecimalFormat("###0.####");

		String ans = formatter.format(answer);
		String sub1 =s.substring(0,begin);
		String sub2 = s.substring(end);
		
		s = sub1+ans+" "+sub2;
		
		return basicPattern(s);
	}
	if(s.contains("tan"))
	{
		int begin =s.indexOf("tan");//inded of beginning of cos
		int end=begin+3;//index of end of cos number
		Scanner scan = new Scanner(s.substring(begin+3));
		scan.useDelimiter("");
		while(scan.hasNext()){
			
			temp = scan.next();
			end++;
			if(temp.equals("+")||temp.equals("-")||temp.equals("/")||temp.equals("*")||temp.equals(" ")){
				break;
			}
		}
		
		String cx = s.substring(begin+3,end);
		
	
		double x = Double.parseDouble(cx);
		
		double answer = tan(x);
		
		DecimalFormat formatter = new DecimalFormat("###0.####");

		String ans = formatter.format(answer);
		String sub1 =s.substring(0,begin);
		String sub2 = s.substring(end);
		
		s = sub1+ans+" "+sub2;
		
		
		return basicPattern(s);
	}
	if(s.contains("e^"))
	{
		int begin =s.indexOf("e^");//inded of beginning of cos
		int end=begin+2;//index of end of cos number
		Scanner scan = new Scanner(s.substring(begin+2));
		scan.useDelimiter("");
		while(scan.hasNext()){
			temp = scan.next();
			end++;
			if(temp.equals("+")||temp.equals("-")||temp.equals("/")||temp.equals("*")||temp.equals(" ")){
				break;
			}
			
			
		}
		
		String cx = s.substring(begin+2,end);
		
	
		double x = Double.parseDouble(cx);
		
		double answer = e(x);
		
		
		DecimalFormat formatter = new DecimalFormat("###0.####");

		String ans = formatter.format(answer);
		String sub1 =s.substring(0,begin);
		String sub2 = s.substring(end);
		
		s = sub1+ans+" "+sub2;
		
		
		return basicPattern(s);
	}
	else
	{
		return calculate(original);
	}	
}
	/**
 * @param n
	 * @return angle between 0 and 360
	 */
private double terminalAngle(double n)
	{
		while(n<0||n>360){
		
			if(n<0){
				n+=360;
			}
			else{
				n-= 360;
			}
		}
	
		return n;
	}
	//maclarin series cosx = 1-x^2/2!+x^4/4!+...
private double cos(double n)
	{
		double temp = 1-Math.pow(sin(n), 2);
		double cosOut;
		if(temp<0){
			cosOut =Math.sqrt(-temp);
		}
		else{
			cosOut =Math.sqrt(temp);
		}
		if(terminalAngle(n)<360&&terminalAngle(n)>270){
			return cosOut;
		}if(terminalAngle(n)<90){
			return cosOut;
		}
		return -cosOut;
		
	}
//cos x =(1-sin^2(x))^(1/2)
//checks sign by seeing what quadrant angle is in
private double sin(double n) {
		
	    // angle to radians
	    double rad = terminalAngle(n)*1./180.*Math.PI;
	    int PRECISION = 10;
	    // the first element of the taylor series
	    double sinOut = rad;
	    // add them up until a certain precision (eg. 10)
	    for (int i = 1; i <= PRECISION; i++) {
	        if (i % 2 == 0) 
	            sinOut += Math.pow(rad, 2*i+1) / factorial(2 * i + 1);
	        else 
	            sinOut -= Math.pow(rad, 2*i+1) / factorial(2 * i + 1);
	    }
	    //round
	    return sinOut;
	}
//tanx = sinx/cosx
private double tan(double x)

{
	//tanx = sinx/cosx
	return sin(x)/cos(x);
}
//recursive factorial method
private long factorial(int n)


	{
		if(n== 0)
		{
			return 1;
		}
		else
		{
			//keeps multiplying its self by one less value until n = 0
			return(n*factorial(n-1));
		}
	}

/**
 * @param x
 * @return e^x
 * uses maclarin series e^x =1 +x +x^2/2! +x^3/3!+... 
 */
private double e(double x)
	{
		//first  2terms
		double out = 1+x;
		int n =2;
		double power =1;
		while(n<20)
		{
			out+= Math.pow(x, n)/factorial(n);
			n++;
		}
		return out;
	}
}
