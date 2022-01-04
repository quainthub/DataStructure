
public class Stack<T> implements Iterable<T>{
	private java.util.LinkedList<T> list = new java.util.LinkedList<T>();
	
	//Create a empty stack
	public Stack() {}
	
	//Create a stack with an initial element
	public Stack(T element) {
		push(element);
	}
	
	//Find stack's size
	public int size() {
		return list.size();
	}
	
	//Check if stack is empty
	public boolean isEmpty() {
		return size() == 0;
	}
	
	//Add a new element to the top
	public void push(T element) {
		list.add(element);
	}

	//Pop element from the top
	public T pop() {
		if(isEmpty()) throw new RuntimeException("Stack is empty");
		return list.removeLast();
	}
	
	//View the top element in the stack
	public T peek() {
		return list.peekLast();
	}
	  
	@Override
	public java.util.Iterator<T> iterator() {
		return list.iterator();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Stack<String> myStack = new Stack<String>();
		myStack.push("Element 1");
		myStack.push("Element 2");
		myStack.pop();
		myStack.push("Element 3");
		System.out.println(myStack.peek());
	}

}
