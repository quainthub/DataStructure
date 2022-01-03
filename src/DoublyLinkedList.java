import java.util.Arrays;

import javax.management.RuntimeErrorException;

public class DoublyLinkedList <T> implements Iterable<T>{
	/*
	 * This class helps to use doubly linked lists. Attribution: William Fiset
	 */
	
	private int size=0;
	private Node<T> head = null;
	private Node<T> tail = null;
	
	private static class Node<T> {
		T data;
		Node <T> pre, next;
		
		public Node (T data, Node<T> pre, Node<T> next) {
			this.data = data;
			this.pre = pre;
			this.next = next;
		}
		@Override public String toString() {
			return data.toString();
		}
	}
	
	//Clear this linked list starting from the head Time: O(n) 
	public void clear() {
		Node<T> trav = head;
		while (trav != null) {
			Node<T> next = trav.next;
			trav.pre = null;
			trav.next = null;
			trav.data= null;
			trav = next;
		}
		head = null;
		tail = null;
		trav = null;
		size = 0;
	}
	
	//Return size of the linked list
	public int size() {
		return size;
	}
	
	//Test if linked list is empty
	public boolean isEmpty() {
		return size == 0;
	}
	
	//Add element to the tail
	public void add(T element) {
		addLast(element);
	}
	
	//Add element to the tail
	public void addLast(T element) {
		if (isEmpty()) {
			Node<T> newNode = new Node<T>(element, null, null);
			head = newNode;
			tail = newNode;
		}else {
			Node<T> newNode = new Node<T>(element, tail, null);
			tail.next = newNode;
			tail = newNode;
		}
		size++;
	}
	
	//Add element to the head
	public void addFirst(T element) {
		if (isEmpty()) {
			Node<T> newNode = new Node<T>(element, null, null);
			head = newNode;
			tail = newNode;
		}else{
			Node<T> newNode = new Node<T>(element, null, head);
			head.pre = newNode;
			head = newNode;
		}
		size++;
	}
	
	//Check the value of the head
	public T peekHead() {
		if (isEmpty()) throw new RuntimeException("Linked list is empty");
		return head.data;
	}
	
	//Check the value of the tail
	public T peekTail() {
		if (isEmpty()) throw new RuntimeException("Linked list is empty");
		return tail.data;
	}
	
	//Remove the last element
	public T removeLast() {
		if (isEmpty()) throw new RuntimeException("Linked list is empty");
		
		// Copy the removing data
		T data = tail.data;
		tail = tail.pre;
		tail.next = null;
		
		//Adjust the head if the linked list is now empty
		if(isEmpty()) head = null;
		size--;
		return data;
	}
	
	//Remove the first element
	public T removeFirst() {
		if (isEmpty()) throw new RuntimeException("Linked list is empty");
		
		// Copy the removing data
		T data = head.data;
		head = head.next;
		head.pre = null;
		
		//Adjust the head if the linked list is now empty
		if(isEmpty()) tail = null;
		size--;
		return data;
	}
	
	
	//Remove any node from the linked list
	private T remove(Node<T> node) {
		//Remove from the head if node is the head
		if(node.pre == null) return removeFirst();
		//Remove from the tail if node if the tail
		if (node.next == null) return removeLast();
		
		//Copy the removing data
		T data = node.data;
		
		node.pre.next = node.next;
		node.next.pre = node.pre;
		
		node.pre = null;
		node.next = null;
		node.data = null;
		node = null;

		size--;
		return data;
	}
	
	//Remove node at a specified index
	public T removeAt(int index) {
		if(index>=size || index <0) throw new IllegalArgumentException();
		
		int i;
		Node<T> trav;
		if (index < size/2) {
			i = 0;
			trav = head;
			while (i<index) {
				trav = trav.next;
				i++;
			}
		}else{
			i = size-1;
			trav = tail;
			while (i>index) {
				trav = trav.pre;
				i--;
			}
		}
		return remove(trav);
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
	
	public int indexOf(Object obj) {
		int i = 0;
		Node<T> trav = head;
		
		if (obj == null) {
			for (trav = head; trav.next != null; trav = trav.next, i++) {
				if (trav.data == null) {
					return i;
				}
			}
		}else {
			for(trav = head; trav.next != null; trav = trav.next, i++) {
				if(obj.equals(trav.data)){
					return i;
				}
			}
		}
		return -1;
	}
	
	public boolean contians(Object obj) {
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
		
		newString.append("]");
		return newString.toString();
	}
	
	public static void main(String[] args) {
		
	}
}
