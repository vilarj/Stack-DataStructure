package classes;
import java.util.Arrays;
import java.util.EmptyStackException;

public class ArrayStack<T> {
	private T[] stack;
	private boolean _init_ = false;
	private int topIndex;
	private static final int DEFAULT_CAPACITY = 10;
	private static final int MAX_CAPACITY = 10000;
	
	public ArrayStack(int capacity) {
		checkCapacity(capacity);

		@SuppressWarnings("unchecked")
		T[] tempStack = (T[]) new Object[capacity];
		stack = tempStack;
		topIndex = -1;
		_init_ = true;
	}
	
	public ArrayStack() {this(DEFAULT_CAPACITY);}
	
	private void checkCapacity(int capacity) {
		if (capacity > MAX_CAPACITY) {throw new IllegalStateException();}
	}

	private void check_Init_() {
		if (!_init_) {
			throw new SecurityException();
		}
	}

	private void ensureCapacity() {
		if (topIndex == stack.length - 1) {
			int newLength = 2 * stack.length;
			
			checkCapacity(newLength);
			stack = Arrays.copyOf(stack, newLength);
		}
	}

	public void push(T anEntry) {
		check_Init_();
		ensureCapacity();
		stack[topIndex + 1] = anEntry;
		topIndex++;
	}

	public T peek() {
		check_Init_();
		
		if (isEmpty()) {throw new EmptyStackException();}
		else {return stack[topIndex];}
	}
	
	public T pop() {
		check_Init_();
		
		if (isEmpty()) {throw new EmptyStackException();}
		else {
			T top = stack[topIndex];
			stack[topIndex] = null;
			topIndex--;
			
			return top;
		}
	}
	
	public void clear() {while(topIndex != -1) {pop();}}
	public boolean isEmpty() {return topIndex < 0;}
	
}
