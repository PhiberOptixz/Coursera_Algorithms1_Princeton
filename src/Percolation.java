import java.util.Random;

public class Percolation 
{
	private int[][] matArray = null;
	private WeightedQuickUnionUF qQUFind = null;
	private int numOfElements = 0;
	
	public Percolation(int N) {
		numOfElements = N;
		matArray = new int[N][N];
		for (int i = 0; i < N; ++i)
			for (int j = 0; j < N; ++j)
				matArray[i][j] = 0;
		qQUFind = new WeightedQuickUnionUF(N*N);
	}
	
	private void openSite(int i, int j) {
		if (i < 0 || j < 0 || i > (numOfElements - 1) || j > (numOfElements -1)) 
			throw new IndexOutOfBoundsException("indexes not correct");
		
		if (matArray[ i ][ j ] > 0) {
			if (isFull(i + 1, j + 1)) {
				matArray[ i ][ j ] = 2;
				
				for (int index = 0; index < numOfElements; ++index) {
					if (qQUFind.connected(i*numOfElements + j, numOfElements*(numOfElements-1) + index)) {
						matArray[ numOfElements - 1 ][ index ] = 2;
					}
				} //for(index);
			}
			return;
		}
		matArray[ i ][ j ] = 1;
		if (i > 0) {
			if (matArray[ i - 1 ][ j ] >= 1) {
				qQUFind.union( ( i - 1 ) * numOfElements + j , i * numOfElements + j );
			}
		}
		if (i < numOfElements - 1) {
			if( matArray[ i + 1 ][ j ] >= 1 ) {
				qQUFind.union( ( i + 1 ) * numOfElements + j, i * numOfElements + j );
			}
		}
		if (j > 0) {
			if( matArray[ i ][ j - 1 ] >= 1 ) {
				qQUFind.union( i * numOfElements + ( j - 1 ), i * numOfElements + j );
			}
		}
		if (j < numOfElements - 1) {
			if( matArray[ i ][ j + 1 ] >= 1 ) {
				qQUFind.union( i * numOfElements + ( j + 1 ), i * numOfElements + j );
			}
		}
		if (isFull( i + 1, j + 1)) {
			matArray[ i ][ j ] = 2;
			for( int index = 0; index < numOfElements; ++index ) {
				if( qQUFind.connected( i*numOfElements + j, numOfElements*(numOfElements-1) + index) ){
					matArray[ numOfElements - 1 ][ index ] = 2;
				}
			}//for(index);
		}
	}//openSite
	
	public void open(int i, int j) {
		//System.out.println("i: " + (i-1) + " j: " + (j-1) );
		openSite(i - 1, j - 1);
	}
	
	public boolean isOpen(int i, int j) {
		if (i - 1 < 0 || j - 1 < 0 || i > numOfElements || j > numOfElements) throw new IndexOutOfBoundsException("indexes not correct");
		return matArray[ i - 1 ][ j - 1 ] > 0;
	}
	
	public boolean isFull(int i, int j) {
		if (i - 1 < 0 || j - 1 < 0 || i > numOfElements || j > numOfElements) throw new IndexOutOfBoundsException("indexes not correct");
		//Check if any of the open sites in the top row has an union
		//with the site indexed at [i-1][j-1] !!
		for (int index = 0; index < numOfElements; ++index) {
			if (matArray[ 0 ][ index ] > 0 
					&& qQUFind.connected( index, ( i - 1 ) * numOfElements + ( j - 1 ))){
				return true;
			}
		}//for(index);
		return false;
	}//isFull
	
	public boolean percolates() {
		//check if any of the site at the bottom row has union with any of site
		//xPrintMatrix();
		for (int i = 0; i < numOfElements; ++i) {
			if (matArray[ numOfElements - 1 ][ i ] > 1) {
				return true;
			}
		}//for(i)
		return false;
	}//percolates !!
	
	/*public void xPrintMatrix() {
		for( int i = 0; i < numOfElements; ++i ) {
			for( int j = 0; j < numOfElements; ++j ) {
				System.out.print( matArray[ i ][ j ] + " ");
			}
			System.out.println();
		}
		System.out.println("---");
	}*/
	
	public static void main( String args[] ) {
		Percolation percolate = new Percolation( 5 );
		Random rn = new Random();
		//System.out.println("Hello");
		while( ! percolate.percolates() ) {
			percolate.open( rn.nextInt(5) + 1, rn.nextInt(5) + 1);
		}
		//percolate.xPrintMatrix();
	}
}
