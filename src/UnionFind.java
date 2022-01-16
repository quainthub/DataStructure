
public class UnionFind {
	/*
	 * Union Find is a way to union nodes and find nodes/roots along the way.
	 * The number of components is equal to the number of roots remaining. 
	 * The number of root nodes never increase.
	 * Attribution: William Fiset
	 */
	//Size of the entire union find
	private int size;
	//Track the size of each component
	private int[] sz;
	//id[i] points to the parent of i, if id[i] = i then i is a root node
	private int[] id;
	//Track the number of components in the union find
	private int numComponents;
	
	
	public UnionFind(int size) {
		if (size <=0) throw new IllegalArgumentException("Size <=0 is not allowed");
		
		this.size = size;
		this.numComponents = size;
		sz = new int[size];
		id = new int[size];
		
		for (int i=0; i<size; i++) {
			id[i] = i;
			sz[i] = 1;
		}
	}
	
	//Find which component/set 'p' belongs to, takes amortized constant time
	public int find(int p) {
		
		//Find root
		int root = p;
		while (root != id[root]) {
			root = id[root];
		}
		
		//Compress the path leading back the root
		//This is called path compression and gives us amortized time complexity
		while(p != root) {
			int next = id[p];
			id[p] = root;
			p = next;
		}
		
		return root;
	}
	
	
	//This is an alternative recursive formulation for the find method
	//	public int find(int p) {
	//		if (p == id[p]) {
	//			return p;
	//		}
	//		return id[p] = find(id[p]);
	//	}
	
	
	//Test if element 'p' and 'q' are in the same component
	public boolean conencted(int p, int q) {
		return find(p) == find(q);
	}
	
	//Return the size of of the component hosting 'p'
	public int componentSize(int p) {
		return sz[find(p)];
	}
	
	public int size() {
		return size;
	}
	
	
	//Return number of remaining components
	public int components() {
		return numComponents;
	}
	
	//Unify the components containing element 'p' and 'q'
	public void unify(int p, int q) {
		int root1 = id[p];
		int root2 = id[q];
		
		if (root1 == root2) return;
		
		if (sz[root1] < sz[root2]){
			id[root1] = root2;
			sz[root2] += sz[root1];
		}else{
			id[root2] = root1;
			sz[root1] += sz[root2];
		}
		numComponents--;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UnionFind uf = new UnionFind(10);
		System.out.println(uf.find(2));
		uf.unify(2, 4);
		uf.unify(2, 6);
		uf.unify(2, 8);
		System.out.println(uf.find(8));
		uf.unify(3, 9);
		uf.unify(4, 1);
		System.out.println(uf.find(1));
		uf.unify(3, 8);
		System.out.println(uf.find(9));
		System.out.println(uf.components());
	}

}
