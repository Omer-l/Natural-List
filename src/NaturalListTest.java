import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.google.java.contract.PreconditionError;

class NaturalListTest {
	
	/** PUSH */
	
	@Test
	void falsifyConstructorPrecondition() {
		assertThrows(PreconditionError.class, () -> {
			Natural n = new Natural(12);
			Natural nullNObject = null;
			NaturalList nList = new NaturalList();
			nList.push(n);
			nList.push(nullNObject);
		});	
	}
	
	@Test
	void satisfyConstructorPostcondition() {
		assertDoesNotThrow(() -> {
			Natural n = new Natural(1234);
		});
	}

}
