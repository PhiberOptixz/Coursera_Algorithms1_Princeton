
import java.util.ArrayList;

public class QuickUnion
{
	private ArrayList< Integer > arrayList = null;
	public QuickUnion( int N ) {
		arrayList = new ArrayList< Integer >();
		for( int index = 0; index < N; ++index ) {
			arrayList.add( index );
		}
	}
	
	private int findRoot( int n ) {
		if( arrayList.get( n ) == n ) return n;
		return findRoot( arrayList.get( n ) );
	}
	
	public boolean connected( int p, int q ) {
		//System.out.println( "size: " + arrayList.size());
		return arrayList.get( p ) == arrayList.get( q );
	}
	
	public void union( int p, int q ) {
		if( ! connected( p, q  ) ) {
			arrayList.set( findRoot( p ), findRoot( q ) );
		}
	}
	
	public void printElements() {
		for( int index = 0; index < arrayList.size(); ++index ) {
			System.out.print( arrayList.get( index ) + " " );
		}
		System.out.println();
	}
	
	public static void main( String args[] ) {
		QuickUnion qu = new QuickUnion( 10 );
		qu.union( 4 , 3 );
		qu.union( 3 , 8 );
		qu.union( 6 , 5 );
		qu.union( 9 , 4 );
		qu.union( 2 , 1 );
		System.out.println("Connected: " + qu.connected( 8 , 9 ) );
		qu.printElements();
		System.out.println("Connected: " + qu.connected( 5 , 4 ) );
		qu.union(5, 0);
		qu.union(7, 2);
		qu.union(6, 1);
		qu.union(7, 3);
		qu.printElements();
	}
}
