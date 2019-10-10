package classes;

public class PostFixEvaluator<T> {
	public float multiDigit(String postFix) {
		LinkedStack<Float> valueStack = new LinkedStack<Float>();
		int [] size = new int[10];
		float[] array = new float[2];
		boolean end = false;
		char operator = 0;
		int operand = 0;
		float result = 0;
		
		while (!end) {
			char nextChar = postFix.charAt(0);
				
			if (Character.isDigit(nextChar)) {
				int pos = postFix.indexOf(' ');
				
				if (pos == -1) {
					pos = postFix.length();
					end = true;
				}
				
				for (int j = 0; j < pos; j++) { 
					char myChar = postFix.charAt(j);
					
					if (Character.isDigit(myChar)) {valueStack.push((float) postFix.charAt(j));} 
					else {
						operator = myChar;
						operand = 1;
						
						if (result > 0) {array[0] = result;
						}
					}
				}
				
				if (!end)
					postFix = postFix.substring(pos + 1);
				
				if (operator != 0)
					pos--;
				
				float tmp = 0;
				
				for (int j = 0; j < pos; j++) {
					tmp += (valueStack.pop() - 48) * Math.pow(10, j);
				}
				array[operand] = tmp;

				if (operand == 2) { 
					operand = 0;
				}
			}
			
			switch (operator) {
			case '+':
				result = array[0] + array[1];
				break;
			case '-':
				result = array[0] - array[1];
				break;
			case '*':
				result = array[0] * array[1];
				break;
			case '/':
				result = array[0] / array[1];
				break;
			case '^':
				result = (float) Math.pow((double) array[0], (double) array[1]);
				break;
			default:
				break;
			}
			operator = 0;
			
			if (end) {break;}
		}
		return result;
	}

	public float evaluator(String postfix) {
		LinkedStack<Float> valueStack = new LinkedStack<Float>();
		float result;
		float firstOperator;
		float secondOperator;

		for (int i = 0; i < postfix.length(); i++) {
			char nextChar = postfix.charAt(i);
			
			while (nextChar == ' ') {
				i++;
				nextChar = postfix.charAt(i);
			}

			switch (nextChar) {
			case '0':
			case '1':
			case '2':
			case '3':
			case '4':
			case '5':
			case '6':
			case '7':
			case '8':
			case '9':
				valueStack.push((float) nextChar - 48);
				break;
				
			case '+':
				secondOperator = valueStack.pop();
				firstOperator = valueStack.pop();
				
				result = firstOperator + secondOperator;
				valueStack.push(result);
				break;
				
			case '-':
				secondOperator = valueStack.pop();
				firstOperator = valueStack.pop();
				
				result = firstOperator - secondOperator;
				valueStack.push(result);
				break;
				
			case '*':
				secondOperator = valueStack.pop();
				firstOperator = valueStack.pop();
				
				result = firstOperator * secondOperator;
				valueStack.push(result);
				break;
				
			case '/':
				secondOperator = valueStack.pop();
				firstOperator = valueStack.pop();
				
				result = firstOperator / secondOperator;
				valueStack.push(result);
				break;
				
			case '^':
				secondOperator = valueStack.pop();
				firstOperator = valueStack.pop();
				
				result = (float) Math.pow(firstOperator, secondOperator);
				valueStack.push(result);
				break;
			default:
				break;
			}
		}
		return (float) valueStack.pop();
	}
}
