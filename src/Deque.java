import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque< Item > implements Iterable< Item >
{
	private DoubleEndedList firstNode = null;
	private DoubleEndedList lastNode = null;
	private int sizeOfList = 0;
	
	private class DoubleEndedList {
		public Item data;
		public DoubleEndedList nextNode;
		public DoubleEndedList prevNode;
	}
	
	public void addFirst( Item item ) {
		if( item == null ) throw new NullPointerException();
		DoubleEndedList newNode = new DoubleEndedList();
		newNode.data = item;
		newNode.nextNode = null;
		newNode.prevNode = null;
		if( sizeOfList == 0 ) {
			firstNode = newNode;
			lastNode = newNode;
		}else {
			newNode.nextNode = firstNode;
			firstNode.prevNode = newNode;
			firstNode = newNode;
		}
		++sizeOfList;
	}
	
	public Item removeFirst() {
		if( sizeOfList == 0 ) throw new NoSuchElementException();
		DoubleEndedList tempNode = firstNode;
		Item item = tempNode.data;
		firstNode = firstNode.nextNode;
		if( firstNode != null )
			firstNode.prevNode = null;
		else {
			lastNode = null;
		}
		tempNode = null;
		--sizeOfList;
		return item;
	}
	
	public boolean isEmpty() {
		return sizeOfList == 0;
	}
	
	public void addLast( Item item ) {
		if( item == null ) throw new NullPointerException();
		DoubleEndedList newNode = new DoubleEndedList();
		newNode.data = item;
		newNode.nextNode = null;
		newNode.prevNode = null;
		if( sizeOfList == 0 ) {
			firstNode = newNode;
			lastNode = newNode;
		}else {
			newNode.prevNode = lastNode;
			lastNode.nextNode = newNode;
			lastNode = newNode;
		}
		++sizeOfList;
	}
	
	public Item removeLast() {
		if( sizeOfList == 0 ) throw new NoSuchElementException();
		DoubleEndedList tempNode = lastNode;
		Item item = tempNode.data;
		lastNode = lastNode.prevNode;
		if( lastNode != null )
			lastNode.nextNode = null;
		else {
			firstNode = null;
		}
		tempNode = null;
		--sizeOfList;
		return item;
	}
	
	public int size() {
		return sizeOfList;
	}
	public Deque() {
		
	}
	public Iterator< Item > iterator() {
		return new listIterator();
	}
	private class listIterator implements Iterator<Item> {
		private DoubleEndedList currNode = firstNode;
		
		@Override
		public boolean hasNext() {
			return currNode != null;
		}
		@Override
		public Item next() {
			if( currNode == null ) throw new NoSuchElementException();
			Item item = currNode.data;
			currNode = currNode.nextNode;
			return item;
		}
		@Override
		public void remove() {
			throw new UnsupportedOperationException();
			
		}		
	}
	
	public static void main( String[] args ) {
		Deque< Integer > node = new Deque< Integer >();
		node.addFirst(4);
		/*node.addFirst(3);
		node.addFirst(1);
		node.addFirst(5);
		node.addFirst(6);
		node.removeFirst();
		node.removeFirst();*/
		System.out.println("Last item: " + node.removeLast());
		/*System.out.println("Last item: " + node.removeLast());
		System.out.println("Last item: " + node.removeLast());
		System.out.println("Last item: " + node.removeFirst());*/
		
		Iterator<Integer> iter = node.iterator();
		while( iter.hasNext() ) {
			System.out.print( iter.next() + "--");
		}
		System.out.println();
	}
}