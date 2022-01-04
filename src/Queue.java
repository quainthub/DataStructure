
public class Queue <T> implements Iterable<T>{

	private java.util.LinkedList<T> list = new java.util.LinkedList<T>();
	
	//Create a queue
	public Queue() {}
	
	//Create a queue with an initial value
	public Queue(T element) {
		offer(element);
	}
	
	//Find queue's size
	public int size() {
		return list.size();
	}
	
	//Test if queue is empty
	public boolean isEmpty() {
		return size() == 0;
	}
	
	//View the first element
	public T peek() {
		if(isEmpty()) throw new RuntimeException("Queue is empty");	
		return list.peekFirst();
	}
	
	//Offer new element to the queue
	public void offer(T element) {
		list.addLast(element);
	}
	
	//Poll the first element from queue
	public T poll() {
		if(isEmpty()) throw new RuntimeException("Queue is empty");
		return list.removeFirst();
	}
	
	@Override
	public java.util.Iterator<T> iterator(){
		return list.iterator();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Queue<String> myQueue = new Queue<String>();
		myQueue.offer("Element 1");
		myQueue.poll();
		myQueue.offer("Element 2");
		myQueue.offer("Element 3");
		myQueue.offer("Element 4");
		
		System.out.println(myQueue.poll());
	}

}
