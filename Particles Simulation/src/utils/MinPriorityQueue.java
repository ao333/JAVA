package utils;

public class MinPriorityQueue<T extends Comparable<T>> {
	final static int initialCapacity = 100;
	int currentCapacity; // if current capacity is not enough, let's double it
	int size = 0;
	Object [] elements;
    
    /**
     * Creates an empty queue.
     */
    public MinPriorityQueue() {
        // TODO implement the constructor
    	elements = new Object[initialCapacity];
    	currentCapacity = initialCapacity;
    }

    /**
     * Returns the number of elements currently in the queue.
     */
    public int size() {
        // TODO implement this method
        return size;
    }
    
    /**
     * Adds elem to the queue.
     */
    public void add(T elem) {
        // TODO implement this method
    	if(size + 1 == currentCapacity) {
    		currentCapacity *= 2;
    		Object [] newElements = new Object[currentCapacity];
    		for(int i = 0; i <= size; i++) {
    			newElements[i] = elements[i];
    		}
    		elements = newElements;
    	}
    	int correctPosition = ++size;
    	while(correctPosition/2 >= 1 && ((T)elements[correctPosition/2]).compareTo(elem) > 0) {
    		elements[correctPosition] = elements[correctPosition/2];
    		correctPosition /= 2;
    	}
    	elements[correctPosition] = elem;
    }

    /**
     * Removes, and returns, the element at the front of the queue.
     */
    public T remove() {
        // TODO implement this method
    	if(isEmpty()) {
    		return null;
    	}
    	T minimal_element = (T)elements[1];
    	T last_element = (T)elements[size--];
    	int indexForLastElement = 1;
    	while(size >= indexForLastElement * 2) {
    		int indexForSmallChild = 
    				((T)elements[indexForLastElement*2]).compareTo((T)elements[indexForLastElement*2+1]) 
    				== 1 ? indexForLastElement * 2 + 1 : indexForLastElement * 2;
    		if(last_element.compareTo((T)elements[indexForSmallChild]) != 1){
    			break;
    		}
    		elements[indexForLastElement] = elements[indexForSmallChild];
    		indexForLastElement = indexForSmallChild;
    	}
    	elements[indexForLastElement] = last_element;
    	
        return minimal_element;
    }

    /**
     * Returns true if the queue is empty, false otherwise.
     */
    public boolean isEmpty() {
        // TODO implement this method
    	if(size == 0)
    		return true;
    	return false;
    }
    
}
