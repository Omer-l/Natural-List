import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.google.java.contract.PreconditionError;
import com.google.java.contract.InvariantError;
import com.google.java.contract.PostconditionError;

class NaturalTest {

	@Test
	void testConstructorSatisfyPostcondition() {
		assertDoesNotThrow(() -> {
			Natural n = new Natural(5);
		});
	}
	
	@Test
	void testConstructorFalsifyPrecondition() {
		assertThrows(PreconditionError.class, () -> {
			Natural n = new Natural(-5);
		});	
	}
}
