package utils;

public class MinPriorityQueue<T extends Comparable<T>> {

	private final static int initialCapacity = 128;
	private int currentCapacity;
	private int size = 0;
	private Object [] elems;
    
    // Creates an empty queue constructor.
    public MinPriorityQueue() {
		elems = new Object[initialCapacity];
    	currentCapacity = initialCapacity;
    }

    //Returns the number of elements currently in the queue.
    public int size() {
        return size;
    }
    
    // Adds elem to the queue.
    public void add(T elem) {
		// if current capacity is insufficient, double it
    	if(size + 1 == currentCapacity) {
    		currentCapacity *= 2;
    		Object [] newElems = new Object[currentCapacity];
    		// copy over elements to expanded object
    		for(int i = 0; i <= size; i++) {
    			newElems[i] = elems[i];
    		}
    		elems = newElems;
    	}

    	// index of last node
    	int ind = ++size;
    	// while not the root node, and last element is greater than its parent, swap
    	while(ind/2 >= 1 && ((T)elems[ind/2]).compareTo(elem) > 0) {
    		elems[ind] = elems[ind/2];
    		ind /= 2;
    	}
    	elems[ind] = elem;
    }

    // Removes, and returns, the element at the front of the queue.
    public T remove() {

    	if(isEmpty()) { return null; }

    	T minElem = (T)elems[1];
    	T lastElem = (T)elems[size--];
    	int prevIndex = 1;

    	while(size >= prevIndex * 2) {
    		// find the minimum of the two child nodes
    		int minChildIndex = ((T)elems[prevIndex*2]).compareTo((T)elems[prevIndex*2+1])
    				> 0 ? prevIndex * 2 + 1 : prevIndex * 2;
    		// done if last element after swapping with root is smaller than its children nodes
    		if(lastElem.compareTo((T)elems[minChildIndex]) <= 0){ break; }
    		// if child is smaller than parent, swap
    		elems[prevIndex] = elems[minChildIndex];
    		// move invariant check one level lower in the tree
    		prevIndex = minChildIndex;
    	}
    	// swap occurs between the final computed index and last element
    	elems[prevIndex] = lastElem;
        return minElem;
    }

    // Returns true if the queue is empty, false otherwise.
    public boolean isEmpty() { return size==0; }
}
