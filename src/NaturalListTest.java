import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.google.java.contract.PreconditionError;

class NaturalListTest {
	
	/** PUSH */
	
	@Test
	void falsifyPushPrecondition() {
		assertThrows(PreconditionError.class, () -> {
			Natural n = new Natural(12);
			Natural nullNObject = null;
			NaturalList nList = new NaturalList();
			nList.push(n);
			nList.push(nullNObject);
		});	
	}
	
	@Test
	void satisfyPushPostcondition() {
		assertDoesNotThrow(() -> {
			Natural n1 = new Natural(1234);
			Natural n2 = new Natural(12);
			Natural n3 = new Natural(12345);
			NaturalList nList = new NaturalList();
			nList.push(n1);
			nList.push(n2);
			nList.push(n3);
		});
	}
	
	/** GET */
	
	@Test
	void falsifyGetPrecondition() {
		assertThrows(PreconditionError.class, () -> {
			NaturalList nList = new NaturalList();
			nList.get(1);
		});	
	}
	
	@Test
	void satisfyGetPostcondition() {
		assertDoesNotThrow(() -> {
			Natural n1 = new Natural(1234);
			Natural n2 = new Natural(12);
			Natural n3 = new Natural(12345);
			NaturalList nList = new NaturalList();
			nList.push(n1);
			nList.push(n2);
			nList.push(n3);
			nList.get(2);
		});
	}

}
