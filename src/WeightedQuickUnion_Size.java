
public class WeightedQuickUnion_Size 
{
	private int[] array = null;
	private int[] size = null;
	public WeightedQuickUnion_Size( int N ) {
		array = new int[ N ];
		size  = new int[ N ];
		for( int index = 0; index < N; ++index ){
			array[ index ] = index;
			size[ index ] = 1;
		}
	}
	
	int findRoot( int p ) {
		if( array[ p ] == p ) return p;
		return findRoot( array[ p ] );
	}
	
	boolean connected( int p, int q ) {
		return findRoot( p ) == findRoot( q );
	}
	
	void union( int p, int q ) {
		if( ! connected( p , q ) ) {
			int rootP = findRoot( p );
			int rootQ = findRoot( q );
			
			if( size[ rootP ] >= size[ rootQ ] ) {
				size[ rootP ] += size[ rootQ ];
				array[ rootQ ] = array[ rootP ];
			} else {
				size[ rootQ ] += size[ rootP ];
				array[ rootP ] = array[ rootQ ];
			}
		}
	}//union
	
	void printElements() {
		for( int index = 0; index < array.length; ++index ) {
			System.out.print( array[ index ] + " ");
		}
		System.out.println();
	}
	
	public static void main( String args[] ) {
		WeightedQuickUnion_Size ws = new WeightedQuickUnion_Size( 10 );
		ws.union( 4, 0 );
		ws.union( 3, 7 );
		ws.union( 6, 8 );
		ws.union( 0, 7 );
		ws.union( 9, 8 );
		ws.union( 8, 5 );
		ws.union(2, 1 );
		ws.union( 5, 7 );
		ws.union( 8 , 1 );
		ws.printElements();
	}
}
