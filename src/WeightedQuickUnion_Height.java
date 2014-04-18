
public class WeightedQuickUnion_Height
{
	private int[] array = null;
	private int[] height = null;
	public WeightedQuickUnion_Height( int N ) {
		array = new int[N];
		height = new int[N];
		for( int index = 0; index < array.length; ++index ) {
			array[ index ] = index;
			height[ index ] = 1;
		}
	}
	
	private int findRoot( int n ) {
		if( array[ n ] == n ) return n;
		return findRoot( array[ n ] );
	}
	
	public boolean connected( int p, int q ) {
		return findRoot( p ) == findRoot( q );
	}
	
	public void union( int p, int q ) {
		if( ! connected( p, q ) ) {
			int rootP = findRoot( p );
			int rootQ = findRoot( q );
			
			if( height[ rootP ] == height[ rootQ ] ) {
				++height[ rootP ];
				array[ rootQ ] = array[ rootP ];
			} else if( height[ rootP ] > height[ rootQ ] ) {
				array[ rootQ ] = array[ rootP ];
			} else {
				array[ rootP ] = array[ rootQ ];
			}
		}
	}//union
	
	private void printElements() {
		for( int index = 0; index < array.length; ++index ) {
			System.out.print( array[ index ] + " " );
		}
		System.out.println();
	}
	
	public static void main( String args[] ) {
		WeightedQuickUnion_Height wh = new WeightedQuickUnion_Height( 10 );
		wh.union( 3, 1 );
		wh.union( 4, 6 );
		wh.union( 7, 9 );
		wh.union( 3, 8 );
		wh.union( 5, 8 );
		wh.union( 4, 7 );
		wh.union(2, 3 );
		wh.union( 7, 2 );
		wh.union( 0 , 6 );
		wh.printElements();
	}
}
