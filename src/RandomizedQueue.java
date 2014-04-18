import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

public class RandomizedQueue< Item > implements Iterable< Item >
{
	private int sizeOfElements = 0;
	private LinkedList headNode = null;
	
	private class LinkedList {
		Item data;
		LinkedList nextNode;
	}

	public void enqueue( Item item ) {
		if( item == null ) throw new NullPointerException();
		LinkedList list = new LinkedList();
		list.data  = item;
		list.nextNode = null;
		if( sizeOfElements == 0 ) {
			headNode = list;
		}else {
			list.nextNode = headNode;
			headNode = list;
		}
		++sizeOfElements;
	}
	
	public Item dequeue() {
		if( sizeOfElements == 0 ) throw new NoSuchElementException();
		Random rn = new Random();
		int getRandNum = rn.nextInt( sizeOfElements );
		LinkedList prevNode = null;
		LinkedList currNode = headNode; 
		for( int i = 0; i < getRandNum; ++i ) {
			prevNode = currNode;
			currNode = currNode.nextNode;
		}
		Item item = currNode.data;
		if( prevNode != null ) {
			prevNode.nextNode = currNode.nextNode;
		} else {
			headNode = currNode.nextNode;
			currNode = null;
		}
		--sizeOfElements;
		return item;
	}
	
	public Item sample() {
		if( sizeOfElements == 0 ) throw new NoSuchElementException();
		Random rn = new Random();
		int getRandNum = rn.nextInt( sizeOfElements );
		LinkedList currNode = headNode;
		for( int i = 0; i < getRandNum; ++i ) {
			currNode = currNode.nextNode;
		}
		return currNode.data;
	}
	
	private class RandQIterator implements Iterator< Item >{
		private Item[] itemList = null;
		private int counter = 0;
		Random rn = new Random();
		public RandQIterator() {
			itemList = (Item[])new Object[sizeOfElements];
			LinkedList tempNode = headNode;
			int tempC = 0;
			while( tempNode != null ) {
				itemList[ tempC++ ] = tempNode.data;
				tempNode = tempNode.nextNode;
			}
		}
		@Override
		public boolean hasNext() {
			return counter < sizeOfElements;
		}

		@Override
		public Item next() {
			if( counter >= sizeOfElements ) throw new NoSuchElementException();
			Item item = null;
			int index = 0;
			do {
				index = rn.nextInt( sizeOfElements );
				item = itemList[ index ];
			}while( item == null);
			itemList[ index ] = null;
			++counter;
			return item;
		}

		@Override
		public void remove(){
			throw new UnsupportedOperationException();
		}
		
	}
	public Iterator< Item > iterator() {
		return new RandQIterator();
	}
	
	public RandomizedQueue() {	
	}
	
	public boolean isEmpty() {
		return sizeOfElements == 0;
	}
	
	public int size() {
		return sizeOfElements;
	}
	
	public static void main( String[] args ) {
		RandomizedQueue< Integer > randQ = new RandomizedQueue< Integer >();
		randQ.enqueue( 4 );
		randQ.enqueue( 1 );	
		randQ.enqueue( 5 );	
		randQ.enqueue( 3 );	
		randQ.enqueue( 7 );	
		randQ.dequeue();
		randQ.dequeue();
		Iterator< Integer > iter = randQ.iterator();
		while( iter.hasNext() ) {
			System.out.println(iter.next());
		}
	}
}
