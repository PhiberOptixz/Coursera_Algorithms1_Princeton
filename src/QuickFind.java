import java.util.ArrayList;

public class QuickFind
{
	private ArrayList< Integer > arrayList = null;
	public QuickFind( int N ) {
		arrayList = new ArrayList< Integer >();
		for( int index = 0; index < N; ++index ) {
			arrayList.add( index );
		}
	}
	
	//All the values stored with the value stored in the index[p] 
	//will be converted to the value stored in the index[q] !!
	public void union( int p, int q ) {
		int valueAtIndex = arrayList.get( p );
		for( int index = 0; index < arrayList.size(); ++index ) {
			if( arrayList.get( index ) == valueAtIndex ) {
				arrayList.set( index , arrayList.get( q ) );
			}//if
		}//for(index);
	}//union;
	
	public boolean connected( int p, int q ) {
		return arrayList.get( p ) == arrayList.get( q );
	}
	
	public void printElements() {
		for( int index = 0; index < arrayList.size(); ++index ) {
			System.out.print( arrayList.get( index ) + " " );
		}
		System.out.println();
	}
	
	public static void main( String args[] ) {
		QuickFind qf = new QuickFind( 10 );
		/*qf.union( 4 , 3 );
		qf.union( 3 , 8 );
		qf.union( 6 , 5 );
		qf.union( 9 , 4 );
		qf.union( 2 , 1 );
		System.out.println("Connected: " + qf.connected( 8 , 9 ) );
		qf.printElements();
		System.out.println("Connected: " + qf.connected( 5 , 0 ) );
		qf.union(5, 0);*/
		qf.union(  1, 4 );
		qf.union( 9 , 4 );
		qf.union( 6 , 8 );
		qf.union( 2 , 1 );
		qf.union( 0 , 4 );
		qf.union( 3, 7 );
		qf.printElements();
		
	}
}//QuickFind
