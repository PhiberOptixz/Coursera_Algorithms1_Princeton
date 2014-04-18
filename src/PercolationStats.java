import java.util.Random;

public class PercolationStats 
{
	private double mean = 0.0;
	private double standardDev = 0.0;
	private double confLow = 0.0;
	private double confHigh = 0.0;
	
	public PercolationStats(int N, int T) {
		if (N <= 0 || T <= 0) throw new IllegalArgumentException();
		
		double[] arrNumOfOpenSites = new double[T];
		int expCounter = 0;
		for (; expCounter < T; ++expCounter) {
			Percolation percolate = new Percolation(N);
			Random rn = new Random();
			int numOfOpenSites = 0;
			while(! percolate.percolates()) {
				int i = rn.nextInt(N) + 1;
				int j = rn.nextInt(N) + 1;
				if (! percolate.isOpen( i, j )) {
					++numOfOpenSites;
				}
				percolate.open( i, j);
			}//while(!percolation)
			arrNumOfOpenSites[expCounter] = (double)numOfOpenSites/(N*N);
			//System.out.println("numOfOpenSites: " + arrNumOfOpenSites[expCounter]);
		}//for(expCouner)
		double tempOpenSites = 0.0;
		for (int i = 0; i < T; ++i) {
			tempOpenSites += arrNumOfOpenSites[i];
		}
		//System.out.println("tempOpenSites: " + tempOpenSites);
		mean = (double)tempOpenSites/T;
		double tempDev = 0.0;
		for (int i = 0; i < T; ++i) {
			tempDev += ((arrNumOfOpenSites[i]-mean)*(arrNumOfOpenSites[i]-mean));
		}
		standardDev = Math.sqrt(tempDev/(T-1) );
		confLow = mean - ((1.96 * standardDev)/Math.sqrt(T) );
		confHigh = mean + ((1.96 * standardDev) / Math.sqrt(T) );
	}
	
	public double mean() {
		return mean;
	}
	
	public double stddev() {
		return standardDev;
	}
	
	public double confidenceLo() {
		return confLow;
	}
	
	public double confidenceHi() {
		return confHigh;
	}
	
	public static void main( String args[] ) {
		//System.out.println("Hello world");
		PercolationStats perStats = new PercolationStats(100, 50 );
		System.out.println( "mean                    = " +perStats.mean() );
		System.out.println( "stddev                  = " +perStats.stddev() );
		System.out.println( "95% confidence interval = " +perStats.confidenceHi()+","+perStats.confidenceLo());
	}
}

