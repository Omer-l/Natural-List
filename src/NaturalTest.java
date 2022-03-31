import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.google.java.contract.PreconditionError;
import com.google.java.contract.InvariantError;
import com.google.java.contract.PostconditionError;

class NaturalTest {

	/** CONSTRUCTOR */
	
	@Test
	void falsifyConstructorPrecondition() {
		assertThrows(PreconditionError.class, () -> {
			Natural n = new Natural(-5);
		});	
	}
	
	@Test
	void satisfyConstructorPostcondition() {
		assertDoesNotThrow(() -> {
			Natural n = new Natural(5);
		});
	}
	
	/** INCREMENT */
	
	@Test
	void falsifyIncrementPrecondition() {
		assertThrows(PreconditionError.class, () -> {
			Natural n = new Natural(Integer.MAX_VALUE);
			n.increment();
		});	
	}
	
	@Test
	void satisfyIncrementPostcondition() {
		assertDoesNotThrow(() -> {
			Natural n = new Natural(5);
			n.increment();
		});
	}
	
	/** DECREMENT */
	
	@Test
	void falsifyDecrementPrecondition() {
		assertThrows(PreconditionError.class, () -> {
			Natural n = new Natural(0);
			n.decrement();
		});	
	}
	
	@Test
	void satisfyDecrementPostcondition() {
		assertDoesNotThrow(() -> {
			Natural n = new Natural(5);
			n.increment();
		});
	}
	
	/** ADD */
	
	@Test
	void falsifyAddPrecondition() {
		assertThrows(PreconditionError.class, () -> {
			Natural n1 = new Natural(1);
			Natural n2 = new Natural(Integer.MAX_VALUE);
			n1.add(n2);
		});	
	}
	
	@Test
	void satisfyAddPostcondition() {
		assertDoesNotThrow(() -> {
			Natural n1 = new Natural(Integer.MAX_VALUE / 2);
			Natural n2 = new Natural(1);
			n1.add(n2);
		});	
	}
	
	/** SUBTRACT */
	
	@Test
	void falsifySubtractPrecondition() {
		assertThrows(PreconditionError.class, () -> {
			Natural n1 = new Natural(Integer.MAX_VALUE);
			Natural n2 = new Natural(Integer.MAX_VALUE);
			n1.subtract(n2);
		});	
	}
	
	@Test
	void satisfySubtractPostcondition() {
		assertDoesNotThrow(() -> {
			Natural n1 = new Natural(Integer.MAX_VALUE);
			Natural n2 = new Natural(Integer.MAX_VALUE - 1);
			n1.subtract(n2);
		});
	}
	
	/** DIVIDE */
	
	@Test
	void falsifyDividePrecondition() {
		assertThrows(PreconditionError.class, () -> {
			Natural n1 = new Natural(Integer.MAX_VALUE);
			Natural n2 = new Natural(0);
			n1.divide(n2);
		});	
	}
	
	@Test
	void satisfyDividePostcondition() {
		assertDoesNotThrow(() -> {
			Natural n1 = new Natural(8);
			Natural n2 = new Natural(2);
			n1.divide(n2);
		});
	}
}
