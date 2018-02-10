package edu.miracosta.cs113.finalproject;

import java.util.*;


/**
 * @author alex meza
 *
 * @param <K>
 * @param <V>
 */
public class HashtableChain<K, V> extends AbstractMap<K, V> implements Map<K,V> {

    /** The table */
    private LinkedList<Entry<K, V>>[] table;
    /** The number of keys */
    private int numKeys;
    /** The capacity */
    private static final int CAPACITY = 101;
    /** The maximum load factor */
    private static final double LOAD_THRESHOLD = 3.0;

    /** Contains key-value pairs for a hash table. */
    private static class Entry<K, V> implements Map.Entry<K, V> {

        /** The key */
        private K key;
        /** The value */
        private V value;

        /**
         * Creates a new key-value pair.
         * @param key The key
         * @param value The value
         */
        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        /**
         * Retrieves the key.
         * @return The key
         */
        @Override
        public K getKey() {
            return key;
        }

        /**
         * Retrieves the value.
         * @return The value
         */
        @Override
        public V getValue() {
            return value;
        }

        /**
         * Sets the value.
         * @param val The new value
         * @return The old value
         */
        @Override
        public V setValue(V val) {
            V oldVal = value;
            value = val;
            return oldVal;
        }
        /*
         * return entry string
         */
        public String toString(){
        	return "value: "+ value.toString();
        }
    }

    // Constructor
    public HashtableChain() {
        table = new LinkedList[CAPACITY];
    }
/*
 * returns obj if successfully removed
 */
    @Override
    public V remove(Object key){
    	int ind = key.hashCode()%table.length;
    	while(ind<0){
    		ind+= table.length;
    	}
    	if(table[ind]==null){
    		return null;
    	}
    	for (Entry<K, V> nextItem : table[ind]){
    		if(nextItem.key.equals(key)){
    			V temp = nextItem.value;
    			nextItem = null;
    			numKeys--;
    			if(table[ind].isEmpty()){
    	    		table[ind] = null;
    	    	}
    			return temp;
    			
    		}
    		
    		
    	}
    	
    	return null;
    }
    
    
  
    /**
     * Method get for class HashtableChain.
     * @param key The key being sought
     * @return The value associated with this key if found;
     *         otherwise, null
     */
    @Override
    public V get(Object key) {
        int index = key.hashCode() % table.length;
        if (index < 0) {
            index += table.length;
        }
        if (table[index] == null) {
            return null; // key is not in the table.
        }
        // Search the list at table[index] to find the key.
        for (Entry<K, V> nextItem : table[index]) {
            if (nextItem.key.equals(key)) {
                return nextItem.value;
            }
        }

        // assert: key is not in the table.
        return null;
    }

    /**
     * Method put for class HashtableChain.
     * @post This key-value pair is inserted in the
     *       table and numKeys is incremented. If the key is already
     *       in the table, its value is changed to the argument
     *       value and numKeys is not changed.
     * @param key The key of item being inserted
     * @param value The value for this key
     * @return The old value associated with this key if
     *         found; otherwise, null
     */
    @Override
    public V put(K key, V value) {
        int index = key.hashCode() % table.length;
        if (index < 0) {
            index += table.length;
        }
        if (table[index] == null) {
            // Create a new linked list at table[index].
            table[index] = new LinkedList<Entry<K, V>>();
        }

        // Search the list at table[index] to find the key.
        for (Entry<K, V> nextItem : table[index]) {
            // If the search is successful, replace the old value.
            if (nextItem.key.equals(key)) {
                // Replace value for this key.
                V oldVal = nextItem.value;
                nextItem.setValue(value);
                return oldVal;
            }
        }

        // assert: key is not in the table, add new item.
        table[index].addFirst(new Entry<K, V>(key, value));
        numKeys++;
        if (numKeys > (LOAD_THRESHOLD * table.length)) {
            rehash();
        }
        return null;
    }
    private void rehash(){
		LinkedList<Entry<K, V>>[] newTable = new LinkedList[table.length*2+1]; 
		for(int i = 0; i <table.length; i++){
    		if(table[i]!= null){
    			newTable[i] = table[i];
    		}
    	}
		table = newTable;
		
	}
    @Override
    public String toString(){
    	String  s="";
    	
    	for(int i = 0; i <table.length; i++){
    		
    		 if(table[i]!=null){
    			 for(Entry<K,V> e: table[i]){
        			 s += " Key: "+e.key+"   value: "+e.value+"\n";
        		 }
    		 }
    	
    	}
    	return s;
    	
    }

    @Override

    public int size(){
    	return numKeys;
    }

    /** Returns true if empty */
    public boolean isEmpty() {
        return numKeys == 0;
    }

	@Override
	public java.util.Set<Map.Entry<K, V>> entrySet() {

        return new EntrySet();

    }
	/** Inner class to implement the set view. */

	private class EntrySet<K,V> extends java.util.AbstractSet<Map.Entry<K, V>> { 



	    
		/** Return the size of the set. */

		@Override
		public int size() {
			int i = size();
			return i;
		}

		/** Return an iterator over the set. */

		@Override
		public Iterator<Map.Entry<K, V>> iterator() { {
			return new SetIterator();
		}

	}

private class SetIterator implements Iterator<Map.Entry<K, V>> {

		int index = 0;
		Iterator localIterator = null;

		@Override
		public boolean hasNext() {

            if (localIterator != null) {

                if (localIterator.hasNext()) {

                    return true;

                } else {

                    localIterator = null;

                    index++;

                }

            }

            while (index < table.length

                    && table[index] == null) {

                index++;

            }

            if (index == table.length) {

                return false;

            }

            localIterator = table[index].iterator();

            return localIterator.hasNext();
		}

	@Override
	public Map.Entry<K, V> next() {
		return (Map.Entry<K, V>)localIterator.next();
	}

	@Override

	public void remove() {
		localIterator.remove();
	}

}
	
	}
}
