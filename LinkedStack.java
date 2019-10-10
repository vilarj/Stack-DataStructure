package classes;
import java.util.EmptyStackException;

public final class LinkedStack <T> implements StackInterface <T> {
	private Node topNode;
	
	public LinkedStack() {topNode = null;}

	@Override
	public void push(T anEntry) {
		Node newNode = new Node(anEntry, topNode);
		topNode = newNode;
	}

	@Override
	public T pop() {
		T top = peek();
		assert topNode != null;
		topNode = topNode.getNext();
		return top;
	}

	@Override
	public T peek() {
		if (isEmpty()) {throw new EmptyStackException();} 
		
		return topNode.getData();
	}

	@Override
	public boolean isEmpty() {return topNode == null;}

	@Override
	public void clear() {topNode = null;}
	
	private class Node {
		private T data;
		private Node next;
		
		public Node(T data, Node next) {this.data = data;this.next = next;}
		public T getData() {return data;}
		public void setData(T data) {this.data = data;}
		public Node getNext() {return next;}
		public void setNext(Node next) {this.next = next;}
	}

}
