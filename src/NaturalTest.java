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
	
	/** ADD */
	
	@Test
	void addFalsifyPrecondition() {
		assertThrows(PreconditionError.class, () -> {
			Natural n1 = new Natural(1);
			Natural n2 = new Natural(Integer.MAX_VALUE);
			n1.add(n2);
		});	
	}
	
	@Test
	void addSatisfyPostcondition() {
		assertDoesNotThrow(() -> {
			Natural n1 = new Natural(Integer.MAX_VALUE / 2);
			Natural n2 = new Natural(1);
			n1.add(n2);
		});
		
//		int t1 = Integer.MAX_VALUE / 2;
//		int t2 = Integer.MAX_VALUE / 2 + 1;
//		System.out.println( (t1 + t2));
//		Natural n1 = new Natural(Integer.MAX_VALUE / 2);
//		Natural n2 = new Natural(Integer.MAX_VALUE / 2 + 1);
//		n1.add(n2);
	}
}
