package classes;
import java.util.Scanner;

public class CheckString {
	public static void main(String[] args) {
		Scanner input = new Scanner (System.in);
		PostFixEvaluator evaluator = new PostFixEvaluator();
		
		System.out.println("Please enter an expression and I will check if it is balanced");
		System.out.println("If it is balanced, I will convert the expression to Postfix and calculate the result");
		String arExp = input.nextLine();
		
		boolean isBalanced = InfixToPostfix.checkBalance(arExp);
		
		System.out.println (isBalanced ? "This expression is balanced" : "This expression is not balanced");
		
		if (isBalanced){
			System.out.println ("Postfix expression: " + InfixToPostfix.convertToPostfix(arExp));
			System.out.println("The result: " + evaluator.multiDigit((InfixToPostfix.convertToPostfix(arExp))));
		}
		
		// Closing the Scanner
		input.close();
	}

}
