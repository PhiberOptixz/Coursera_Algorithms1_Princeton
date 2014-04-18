import java.util.Iterator;

public class Subset 
{
	public static void main( String[] args ) {
		RandomizedQueue< String > randQ = new RandomizedQueue<String>();
		while( !StdIn.isEmpty() ) {
			randQ.enqueue( StdIn.readString());
		} //while()
		int sizeOfRandQ = Integer.parseInt(args[0]);
		int counter = 0;
		Iterator< String > iter = randQ.iterator();
		while( iter.hasNext() && ( counter++ < sizeOfRandQ ) ) {
			System.out.println( iter.next() );
		}
	} //main ! 
} //class:Subset !
