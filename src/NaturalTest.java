import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.google.java.contract.PreconditionError;
import com.google.java.contract.InvariantError;
import com.google.java.contract.PostconditionError;

class NaturalTest {

	/** CONSTRUCTOR */
	
	@Test
	void constructorFalsifyPrecondition() {
		assertThrows(PreconditionError.class, () -> {
			Natural n = new Natural(-5);
		});	
		int testino = Integer.MAX_VALUE + 1;
		System.out.println(testino == Integer.MIN_VALUE);
	}
	
	@Test
	void constructorSatisfyPostcondition() {
		assertDoesNotThrow(() -> {
			Natural n = new Natural(5);
		});
	}
	
	/** INCREMENT */
	
	@Test
	void incrementFalsifyPrecondition() {
		
	}
	
	@Test
	void incrementSatisfyPostcondition() {
		assertDoesNotThrow(() -> {
			Natural n = new Natural(5);
			n.increment();
		});
	}
	
}
