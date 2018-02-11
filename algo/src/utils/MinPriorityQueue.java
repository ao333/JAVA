package utils;

import java.util.ArrayList;  //lisa: need arraylist package for this class
/*we can manipulate the size of array and let it grow - i think thats what we need 
 to use in order to make the Pqueue work, could probably also use Priority queue package
 but not sure, sorting might not work since pQ would just spit out smallest element but
 iterating through rest which is unsorted will take longer than logN, also pQueue structure
 doesnt allow adding the null*/


public class MinPriorityQueue<T extends Comparable<T>> {
    
    private ArrayList<T> priorityQueue;   //lisa this creates an empty queue/heap whatever
    private int qSize;
    	
    	
    public MinPriorityQueue() {                 //lisa - constructor
        priorityQueue = new ArrayList<T>();
        priorityQueue.add(null);                //one of the advantages of using ArrayList
        /*https://docs.oracle.com/javase/7/docs/api/java/util/ArrayList.html*/
        qSize = 0;                              //sets size to 0
    }

 
    public int size() {
        return qSize;                          //has to return the qSize no?
    }
    

    public void add(T elem) {                  //lisa
       priorityQueue.add(elem);                //adding an element to the queue
       qSize++;                                //increasing qSize   
       int index = qSize;                           //last position in queue/index
      
      /*insert the new element efficiently into the heap
       guys please read this its SUPER helpful. :)
       http://pages.cs.wisc.edu/~vernon/cs367/notes/11.PRIORITY-Q.html
       */

	while(index > 1 && elem.compareTo(priorityQueue.get(index/2))<0) {    //comparing with parent node
		priorityQueue.set(index, priorityQueue.get(index/2));           
		index = index/2;                                 //smallest element slowly moves up the heap - beginning of array
		}
	priorityQueue.set(index, elem);     //inserted            
    
    }

    
    public T remove() {
    	if (!isEmpty())
	{
    	    T removeElement = priorityQueue.get(1);        //lisa: well I assume if I did everything correctly it should just be get(1)
            T lastElement = priorityQueue.get(qSize);      // now I proceed like outlined in the website that ive mentioned above - should make sense
            priorityQueue.set(1, lastElement);
            priorityQueue.remove(qSize);
            qSize--;
            
            /*now comes the tricky bit - please look over the moveNode function and correct if you find mistakes*/
            /*make sure you really understand the algorithm and how the array is sorted, to understand the code*/
            if(qSize > 1)
            {
            	this.moveNodes();
            }
            
            return removeElement;
            
    	}
    		return null;         //if the queue is empty it returns null
    }


    public boolean isEmpty() {
        return  qSize == 0;
    }
    
    public void moveNodes() {
    	T rootValue = priorityQueue.get(1);       //the root of the tree - first element in array is the former last leaf
    	int child;
    	int parent = 1;
    	
    	while (2*parent <= qSize) 
	{
    		child = 2*parent;
    		                                           //find smallest childnode of parent
    		if (child < qSize && priorityQueue.get(child+1).compareTo(priorityQueue.get(child)) < 0) 
    		{  //if child+1 smaller than child, get that one
			child++;
    		}
    		                                          //if parent is larger than child
    		if (rootValue.compareTo(priorityQueue.get(child)) > 0) 
		{                                    
    			priorityQueue.set(parent, priorityQueue.get(child));  //move child to parentnode
    			
    			parent = child;                      //update parentIndex for its subtree
    			
    		}
		
		else 
		{
    			break;
    		}
    	}
    	
    	priorityQueue.set(parent, rootValue);       //move original root to correct location - not 100% sure whether thats correct since last row of tree might get messed up , but actually shouldnt impact on running time of code
    }
    
}
