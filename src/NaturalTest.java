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
		assertThrows(PreconditionError.class, () -> {
			Natural n = new Natural(Integer.MAX_VALUE);
			n.increment();
		});	
	}
	
	@Test
	void incrementSatisfyPostcondition() {
		assertDoesNotThrow(() -> {
			Natural n = new Natural(5);
			n.increment();
		});
	}
	
	/** DECREMENT */
	
	@Test
	void decrementFalsifyPrecondition() {
		assertThrows(PreconditionError.class, () -> {
			Natural n = new Natural(1);
			n.decrement();
		});	
	}
	
	@Test
	void decrementSatisfyPostcondition() {
		assertDoesNotThrow(() -> {
			Natural n = new Natural(5);
			n.increment();
		});
	}
}
