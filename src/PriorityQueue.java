import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class PriorityQueue<T extends Comparable<T>>{
	/*
	 * This is a Min Binary Heap implementation.
	 */
	private int heapSize = 0;
	//Internal capacity of the heap. Since the heap is implemented using List
	//We will just nullify the removed item until something else replaces it
	private int heapCapacity = 0;
	private List<T> heap = null;
	
	public PriorityQueue() {
		this(1);
	}
	
	public PriorityQueue(int sz) {
		heap = new ArrayList<>(sz);
	}
	
	public PriorityQueue(T[] elements) {
		heapSize = heapCapacity = elements.length;
		heap = new ArrayList<>(heapSize);
		
		// Place all element in heap
		for (int i=0; i<heapSize; i++) {
			heap.add(elements[i]);
		}
		
		//Heapify O(n). 
		//sink compares two node at the same time so the entire heap is run
		for (int i = Math.max(0, (heapSize/2)-1); i>=0; i--) {
			sink(i);
		}
	}
	
	//Priority queue construction, O(nlogn)
	public PriorityQueue(Collection<T> elements) {
		this(elements.size());
		for (T ele : elements) add(ele);
	}
	
	//Test if heap is empty
	public boolean isEmpty() {
		return heapSize == 0;
	}
	
	//Clear the heap
	public void clear() {
		for (int i=0; i<heapCapacity; i++) {
			heap.set(i, null);
		}
		heapSize = 0;
	}
	

	//Return the value of the element with the lowest priority in this priority
	//queue. Return null is the heap is empty
	public T peek() {
		if (isEmpty()) return null;
		return heap.get(0);
	}
	
	//Remove the root of the heap O(logn)
	public T poll() {
		return removeAt(0);
	}
	
	//Test if heap contains a given element, O(n)
	public boolean contains(T ele) {
		//Linear scan to check containment
		for (int i = 0; i<heapSize; i++)
		{
			if (heap.get(i) == ele) return true;
		}
		return false;
	}
	
	
	//Adds an element to the heap, the element must not be null
	public void add(T ele) {
		if (ele == null) throw new IllegalArgumentException();
		
		if (heapSize <heapCapacity) {
			heap.set(heapSize, ele);
		}else {
			heap.add(ele);
			heapCapacity++;
		}
		
		swim(heapSize);
		heapSize++;
	}
	
	//Test if node i is less than node j
	//This assumes i and j are valid indices, )(1)
	private boolean less (int i, int j) {
		T node1 = heap.get(i);
		T node2 = heap.get(j);
		
		return node1.compareTo(node2) <0;
	}
	
	
	//Perform bottom up node swim O(logn)
	private void swim(int k) {
		// Get the index of the next parent node WRT to k
		int parent = (k-1)/2;
		
		// Keep swimming up while we have not reached the root and less than
		// its parents
		while (k>0 && less(k, parent)) {
			swap (parent, k);
			k = parent;
			
			parent = (k-1)/2;
		}
	}
	
	// Top down node sink O(logn)
	private void sink(int k) {
		while (true) {
			int left = k*2+1;
			int right = k*2+2;
			
			int smallest = left; // Assume left node is the samllest first
			
			if (right<heapSize && less(right, left)) {
				smallest = right;
			}
			
			if (left>=heapSize || less(k, smallest)) {
				break;
			}
			
			swap(smallest, k);
			k = smallest;
		}
	}
	
	//Swap two nodes, assume i and j are valid O(n)
	private void swap(int i, int j) {
		T node1 = heap.get(i);
		T node2 = heap.get(j);
		
		heap.set(i, node2);
		heap.set(j, node1);
	}
	
	//Remove a particular element from the heap O(n)
	public boolean remove(T ele) {
		if (ele == null) return false;
		
		for (int i=0; i<heapSize; i++) {
			if (heap.get(i).equals(ele)) {
				removeAt(i);
				return true;
			}
		}
		return false;
	}
	
	//Remove an element at a given index
	private T removeAt(int k) {
		if (isEmpty()) return null;
		
		heapSize--;
		T removed_data = heap.get(k);
		swap(heapSize, k);
		
		//nullify the value
		heap.set(heapSize, null);
		
		//if the last element is removed nothing to sink
		if (k == heapSize) return removed_data;
		//otherwise get the 
		T ele = heap.get(k);
		
		sink(k);
		
		//if sink didn't work, we try to swim it up
		if (heap.get(k).equals(ele)) swim(k);
		return removed_data;
	}
	
	//Recursively cheks if this heap is a min heap. Call with 0 to start from
	//the root
	public boolean isMinHeap(int k){
		//return true if we are outisde of the bound
		if (k>=heapSize) return true;
		
		int left = k*2+1;
		int right = k*2+2;
		
		//Make sure the current node is less than left and right node
		if (left<heapSize && !less(k, left)) return false;
		if (right<heapSize && !less(k, right)) return false;
		
		return isMinHeap(left) && isMinHeap(right);
	}
	
	@Override
	public String toString() {
		return heap.toString();
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PriorityQueue<String> pq = new PriorityQueue<>();
		pq.add("Z");
		pq.add("B");
		pq.remove("B");
		pq.add("A");
		
		System.out.println(pq.toString());
		System.out.println("The root is "+ pq.peek());
		System.out.println("This is a min heap? " + pq.isMinHeap(0));

	}

}
