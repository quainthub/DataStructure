import java.util.Iterator;

public class SinglyLinkedList<T> implements Iterable<T>{
	/*
	 * This class helps to use doubly linked lists. Attribution: William Fiset
	 */
	
	private int size=0;
	private Node<T> head = null;
	private Node<T> tail = null;
	
	private static class Node<T>{
		private T data;
		private Node <T> next;
		
		public Node (T data, Node<T> next) {
			this.data = data;
			this.next = next;
		}
		@Override public String toString() {
			return data.toString();
		}
	}
	
	//Clear this linked list starting from the head. Time O(n) 
	public void clear() {
		Node<T> trav = head;
		while(trav.next != null) {
			Node<T> next = trav.next;
			trav.data = null;
			trav.next = null;
			trav = next;			
		}
		head = null;
		trav = null;
		size = 0;
	}
	
	//Return size of the linked list
	public int size() {
		return size;
	}
	
	//Test if the linked list is empty
	public boolean isEmpty() {
		return size == 0;
	}
	
	//Add element to the tail
	public void add(T element) {
		addLast(element);
	}
	
	//Add element to the tail
	public void addLast(T element) {
		Node<T> newNode = new Node<T>(element, null);
		if (isEmpty()) {
			head = newNode;
			tail = newNode;
		}else {
			tail.next = newNode;
			tail = newNode;
		}
		size++;
	}
	
	//Add element to the head
	public void addFirst(T element) {
		Node<T> newNode = new Node<T>(element, null);
		if (isEmpty()) {
			head = newNode;
			tail = newNode;
		}else {
			newNode.next = head;
			head = newNode;
		}
		size++;
	}
	
	//Check the value of the head
	public T peekHead() {
		if(isEmpty()) throw new RuntimeException("Linked list is empty");
		return head.data;
	}
	
	//Check the value of the tail
	public T peekTail() {
		if (isEmpty()) throw new RuntimeException("Linked list is empty");
		return tail.data;
	}
	
	//Remove the last element
	public T removeLast() {
		if(isEmpty()) throw new RuntimeException("Linked list is empty");
		T data = tail.data;
		Node<T> trav = head;
		
		while(trav.next != tail) {
			trav = trav.next;
		}
		
		tail = trav;
		trav.next = null;
		size--;
		
		if(isEmpty()) head = null;
		return data;
	}
	
	//Remove the first element
	public T removeFirst() {
		if(isEmpty()) throw new RuntimeException("Linked list is empty");
		
		T data = head.data;
		
		head = head.next;
		size--;
		
		if(isEmpty()) tail = null;
		return data;
		
	}
	
	//Remove any node from the linked list
	private T remove(Node<T> node) {
		T data;
		if (head == node)return removeFirst(); 
		if (node.next == null) return removeLast();
		else {
			data = node.data;
			Node<T> trav = head;
			while(trav.next != node) {
				trav = trav.next;
			}
			
			data = remove(node, trav);			
		}
		return data;
	}
	
	private T remove(Node<T> node, Node<T> preNode) {
		T data = node.data;
		preNode.next = node.next;
		node = null;
		size--;
		return data;
	}
	
	//Remove node at a specified index
	public T removeAt(int index) {
		if(index>=size || index<0) throw new IllegalArgumentException();
		T data;
		if (index==0)return removeFirst(); 
		if (index==size-1) return removeLast();
		else {
			int i=0;
			Node<T> trav = head;
			while(i<index-1) {
				i++;
				trav = trav.next;
			}
			data = remove(trav.next, trav);
		}
		return data;
	}

	// Remove a particular value in the linked list, O(n)
	public boolean remove(Object obj) {
		Node<T> trav = head;
		
		if (obj == null) {
			for (trav = head; trav.next != null; trav = trav.next) {
				if (trav.data == null) {
					remove(trav);
					return true; 
				}
			}
		}else {
			for(trav = head; trav.next != null; trav = trav.next) {
				if(obj.equals(trav.data)){
					remove(trav);
					return true;
				}
			}
		}
		return false;
	}
	
	//Get index of an object, If it doesn't exist, return -1
	public int indexOf(Object obj) {
		int i = 0;
		Node<T> trav = head;
		if (obj == null) {
			for (trav=head; trav.next != null; trav = trav.next, i++) {
				if (trav.data == null) {
					return i;
				}
			}
		}else {
			for (trav=head; trav.next != null; trav = trav.next, i++) {
				if (obj.equals(trav.data)) {
					return i;
				}
			}
		}
		return -1;
	}
	
	//Test if linked list contains an object
	public boolean contains(Object obj) {
		return indexOf(obj) != -1;
	}
	

	@Override
	public java.util.Iterator<T> iterator() {
		return new java.util.Iterator<T>() {
		    private Node<T> trav = head;
	
		    @Override
		    public boolean hasNext() {
		    	return trav != null;
		    }
	
		    @Override
		    public T next() {
		    	T data = trav.data;
		        trav = trav.next;
		        return data;
		    }
	
		    @Override
		    public void remove() {
		    	throw new UnsupportedOperationException();
		    }
	    };
	}
	
	@Override
	public String toString() {
		StringBuilder newString = new StringBuilder();
		newString.append("[");
		Node<T> trav = head;
		for(; trav.next != null; trav = trav.next) {
			newString.append(trav.data.toString()+", ");
		}
		newString.append(trav.data.toString());
		newString.append("]");
		return newString.toString();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SinglyLinkedList<Integer> myLinkedList = new SinglyLinkedList<>();
		
		myLinkedList.add(3);
		myLinkedList.add(7);
		myLinkedList.removeFirst();

		System.out.println(myLinkedList.toString());
		System.out.println(myLinkedList.head);

	}

}
