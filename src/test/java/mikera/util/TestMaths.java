package mikera.util;

import static org.junit.Assert.assertEquals;
import mikera.util.Maths;
import mikera.util.Rand;
import mikera.util.mathz.AlternativeMaths;
import mikera.util.mathz.FloatMaths;

import org.junit.Test;

public class TestMaths {
	@Test public void testClamp() {
		assertEquals(1,Maths.bound(0,1,2));
		assertEquals(10,Maths.bound(10,0,20));
		assertEquals(20,Maths.bound(100,0,20));
	}
	
	@Test public void testModPower32Bit() {
		assertEquals(13,Maths.modPower32Bit(13,1));
		assertEquals(169,Maths.modPower32Bit(13,2));
		assertEquals(100000,Maths.modPower32Bit(10,5));
		assertEquals(-1,Maths.modPower32Bit(-1,105));
		assertEquals(0,Maths.modPower32Bit(0,435435));
		assertEquals(1,Maths.modPower32Bit(435435,0));
		assertEquals(0,Maths.modPower32Bit(2,32));
		assertEquals(0,Maths.modPower32Bit(2,320));
	}
	
	@Test public void testSigmoid() {
		assertEquals(0.0f,Maths.logistic(-1000000),0.001f);
		assertEquals(0.0f,Maths.logistic(-800),0.001f);
		assertEquals(0.0f,Maths.logistic(-100),0.001f);
		assertEquals(0.0f,Maths.logistic(-30),0.001f);
		
		assertEquals(1.0f,Maths.logistic(1000000),0.001f);
		assertEquals(1.0f,Maths.logistic(800),0.001f);
		assertEquals(1.0f,Maths.logistic(100),0.001f);
		assertEquals(1.0f,Maths.logistic(30),0.001f);
		
		assertEquals(0.5f,Maths.logistic(0),0.001f);

		assertEquals(0.0f,Maths.logistic(Maths.inverseLogistic(0.0f)),0.001f);
		assertEquals(1.0f,Maths.logistic(Maths.inverseLogistic(1.0f)),0.001f);
		assertEquals(0.5f,Maths.logistic(Maths.inverseLogistic(0.5f)),0.001f);

		assertEquals(0.0f,Maths.logisticDerivative(100),0.001f);
		assertEquals(0.25f,Maths.logisticDerivative(0),0.001f);
		assertEquals(0.0f,Maths.logisticDerivative(-100),0.001f);

		
		assertEquals(0.0f,Maths.inverseLogistic(Maths.logistic(0.0f)),0.001f);
		assertEquals(2.0f,Maths.inverseLogistic(Maths.logistic(2.0f)),0.001f);
		assertEquals(-2.0f,Maths.inverseLogistic(Maths.logistic(-2.0f)),0.001f);
	}
	
	@Test public void testIntMod() {
		assertEquals(2,Maths.mod(2, 5));
		assertEquals(2,Maths.mod(7, 5));
		assertEquals(1,Maths.mod(-7, 8));
		assertEquals(0,Maths.mod(10, 10));
	}
	
	@Test public void testDoubleMod() {
		assertEquals(2.0,Maths.mod(2.0, 5.0),0.00001);
		assertEquals(2.0,Maths.mod(-3.0, 5.0),0.00001);
		assertEquals(0.0,Maths.mod(10.0, 5.0),0.00001);
	}
	
	@Test public void testSign() {
		for (int i=-100; i<100; i++) {
			assertEquals((int)Math.signum(i),Maths.sign(i));
			assertEquals((int)Math.signum(i),AlternativeMaths.sign2(i));
		}
		assertEquals((int)Math.signum(Integer.MIN_VALUE),Maths.sign(Integer.MIN_VALUE));	
		assertEquals((int)Math.signum(Integer.MIN_VALUE),AlternativeMaths.sign2(Integer.MIN_VALUE));	
	}
	
	@Test public void testMinMax() {
		for (int i=-100; i<100; i++) {
			int a=Rand.xorShift32(i);
			int b=Rand.xorShift32(a);
			
			assertEquals(Math.min(a,b),Maths.min(a,b));
			assertEquals(Math.max(a,b),Maths.max(a,b));
			assertEquals(Math.min(a,b),AlternativeMaths.min2(a,b));
			assertEquals(Math.max(a,b),AlternativeMaths.max2(a,b));
		}
	}
	

	
	@Test public void testFloor() {
		assertEquals(0,Maths.roundDown(0));
		assertEquals(1,Maths.roundDown(1));
		assertEquals(-1,Maths.roundDown(-1));
		assertEquals(1,Maths.roundDown(1.2));
		assertEquals(-1,Maths.roundDown(-0.0001));
		assertEquals(-1,Maths.roundDown(-0.9001));
		
		assertEquals(0,Maths.roundDown(0.1f));
		assertEquals(1,Maths.roundDown(1.3f));
		assertEquals(-1,Maths.roundDown(-0.1f));

	}
	
	@Test public void testSpeed1() {
		for (int i=0; i<100000; i++) {
			float f=Maths.sqrt(i);
			assertEquals(f,f,0.01f);
		}
	}
	
	@Test public void testSpeed2() {
		for (int i=0; i<100000; i++) {
			float f=FloatMaths.alternateSqrt(i);
			assertEquals(f,f,0.01f);
		}
	}
}
