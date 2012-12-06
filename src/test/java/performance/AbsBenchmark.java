package performance;

import mikera.util.mathz.FloatMaths;

import com.google.caliper.Runner;
import com.google.caliper.SimpleBenchmark;

public class AbsBenchmark extends SimpleBenchmark {
	public float r;
	
	public void timeMikeraMathsFloatAbs(int reps) {
		float c=0;
		for (int i=0; i<reps; i++) {
			c+=FloatMaths.abs(i);
		}
		r=c;
	}
	
	public void timeJavaMathsFloatAbs(int reps) {
		float c=0;
		for (int i=0; i<reps; i++) {
			c+=java.lang.Math.abs((float)i);
		}
		r=c;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new AbsBenchmark().run();
	}

	private void run() {
		new Runner().run(new String[] {this.getClass().getCanonicalName()});
	}


}
