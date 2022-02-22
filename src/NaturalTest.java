import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.google.java.contract.PreconditionError;
import com.google.java.contract.InvariantError;

class NaturalTest {

	@Test
	void testConstructorSatisfyPostcondition() {
		Natural n = new Natural(50);
	}
	
	@Test
	void testConstructorLessThanOrEqualTo0() {
		assertThrows(PreconditionError.class, () -> {
			Natural n = new Natural(0);
		});	
	}

}
