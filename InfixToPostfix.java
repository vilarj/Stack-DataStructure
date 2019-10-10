package classes;

public class InfixToPostfix {
	public static boolean checkBalance (String expression) {
		ArrayStack <Character> openDelimiterStack = new ArrayStack<>();
		boolean isBalanced = true;
		int i = 0;
		int characterCount = expression.length();
		char nextCharacter;

		while (isBalanced && i < characterCount){
			nextCharacter = expression.charAt(i);
			
			switch (nextCharacter){
			case '(': case '[': case '{':
				openDelimiterStack.push(nextCharacter);
				break;
				
			case ')': case ']': case '}':
				if (openDelimiterStack.isEmpty())
					isBalanced = false;
				else {
					char openDelimiter = openDelimiterStack.pop();
					isBalanced = isPaired (openDelimiter, nextCharacter );
				}
				
				break;
			default:
				break;
			}
			
			i++;
		}
		
		if(!openDelimiterStack.isEmpty())
			isBalanced = false;

		return isBalanced;
	}
	
	private static boolean isPaired (char first, char second) {
		return (first == '(' && second == ')' ||
				first == '[' && second == ']' ||
				first == '{' && second == '}');
	}

	public static String convertToPostfix (String infix) {
		ArrayStack <Character> operatorStack = new ArrayStack <>();
		String postfix = new String();
		Character nextCharacter;
		Character topOperator;
		for (int idx = 0; idx < infix.length(); idx ++) 
		{
			nextCharacter = infix.charAt(idx);
			if (Character.isLetterOrDigit(nextCharacter)) {
				postfix += (nextCharacter);
				
			}
			else if (nextCharacter == '^') {
				postfix += " ";//add space delimiter
				operatorStack.push(nextCharacter);
			}
			else if (nextCharacter == '*' || nextCharacter == '/' || nextCharacter == '+' || nextCharacter == '-') {
				postfix += " ";//add space delimiter
				
				while (!operatorStack.isEmpty() && precedence (nextCharacter) <= precedence (operatorStack.peek()) ){
					postfix += operatorStack.pop();  
				}
				operatorStack.push(nextCharacter);
			}

			else if (isOpenDelimiter (nextCharacter) )
				operatorStack.push(nextCharacter);
			else if (isCloseDelimiter (nextCharacter) ) {
				topOperator = operatorStack.pop();
				while (!isOpenDelimiter(topOperator)) {
					postfix += topOperator;
					topOperator = operatorStack.pop();
				}	 
			}
			
			else 
				continue;
		}

		while ( !operatorStack.isEmpty()) {
			topOperator = operatorStack.pop();
			postfix += topOperator;
		}
		return postfix;
	}
	private static int precedence (Character operator ) {
		if (operator == '+' || operator == '-' )
			return 2;
		else if (operator == '*' || operator == '/')
			return 3;
		else if (operator == '^')
			return 4;
		else if (operator == '(' ||
				operator == '[' ||
				operator == '{') 
			return 1;

		throw new IllegalArgumentException ();
	}

	private static boolean isOpenDelimiter (char ch) {
		return (ch == '(') || (ch == '[') || (ch == '{');
	}

	private static boolean isCloseDelimiter (char ch) {
		return (ch == ')') || (ch == ']') || (ch == '}');
	}
}