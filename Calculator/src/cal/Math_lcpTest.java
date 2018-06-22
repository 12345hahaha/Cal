package cal;

import static org.junit.Assert.*;

import org.junit.Test;

public class Math_lcpTest {
	Math_lcp math = new Math_lcp();

	@SuppressWarnings("deprecation")
	@Test
	public void testAdd() {
		assertEquals(3.4,math.add(1.2, 2.2),0.001);
	}

	@Test
	public void testSubtract() {
		assertEquals(0,math.subtract(2.2, 2.2),0.001);
	}

	@Test
	public void testMultiply() {
		assertEquals(4.0,math.multiply(2, 2),0.001);
	}

	@Test
	public void testDivide() {
		assertEquals(2.0,math.divide(8, 4),0.001);
	}

}
