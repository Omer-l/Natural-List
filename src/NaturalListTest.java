import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.google.java.contract.PreconditionError;

class NaturalListTest {
	
	/** PUSH */
	
	@Test
	void falsifyPushPreconditionNullNatural() {
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
			Natural n1 = new Natural(1234);
			Natural n2 = new Natural(12);
			Natural n3 = new Natural(12345);
			NaturalList nList = new NaturalList();
			nList.push(n1);
			nList.push(n2);
			nList.push(n3);
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
	void falsifyGetPreconditionOutOfBoundsLower() {
		assertThrows(PreconditionError.class, () -> {
			NaturalList nList = new NaturalList();
			nList.push(new Natural(12));
			nList.push(new Natural(22));
			nList.push(new Natural(32));
			nList.get(-1);
		});	
	}
	
	@Test
	void falsifyGetPreconditionOutOfBoundsUpper() {
		assertThrows(PreconditionError.class, () -> {
			NaturalList nList = new NaturalList();
			nList.push(new Natural(12));
			nList.push(new Natural(22));
			nList.push(new Natural(32));
			nList.get(3);
		});	
	}
	
	@Test
	void satisfyGetPostcondition() {
			Natural n1 = new Natural(1234);
			Natural n2 = new Natural(12);
			Natural n3 = new Natural(12345);
			NaturalList nList = new NaturalList();
			nList.push(n1);
			nList.push(n2);
			nList.push(n3);
			nList.get(2);
	}
	
	/** SET */
	
	@Test
	void falsifySetPrecondition() {
		assertThrows(PreconditionError.class, () -> {
			Natural n1 = new Natural(1234);
			Natural n2 = new Natural(12);
			Natural n3 = new Natural(12345);
			NaturalList nList = new NaturalList();
			nList.push(n1);
			nList.push(n2);
			nList.push(n3);
			nList.set(1, null);
		});	
	}
	
	@Test
	void satisfySetPostcondition() {
			Natural n1 = new Natural(1234);
			Natural n2 = new Natural(12);
			Natural n3 = new Natural(12345);
			NaturalList nList = new NaturalList();
			nList.push(n1);
			nList.push(n2);
			nList.push(n3);
			Natural n = new Natural(5000);
			nList.set(2, n);
	}

	
	@Test
	void falsifySetPreconditionOutOfBoundsLower() {
		assertThrows(PreconditionError.class, () -> {
			NaturalList nList = new NaturalList();
			nList.push(new Natural(12));
			nList.push(new Natural(22));
			nList.push(new Natural(32));
			nList.set(-1, new Natural(50));
		});	
	}
	
	@Test
	void falsifySetPreconditionOutOfBoundsUpper() {
		assertThrows(PreconditionError.class, () -> {
			NaturalList nList = new NaturalList();
			nList.push(new Natural(12));
			nList.push(new Natural(22));
			nList.push(new Natural(32));
			nList.set(3, new Natural(50));
		});	
	}
	
	/** SORT */
	
	@Test
	void falsifySortPrecondition() {
		assertThrows(PreconditionError.class, () -> {
			NaturalList nList = new NaturalList();
			nList.sort();
		});	
	}
	
	@Test
	void satisfySortPostcondition() {
			Natural n1 = new Natural(1);
			Natural n2 = new Natural(5);
			Natural n3 = new Natural(6);
			Natural n6 = new Natural(9);
			Natural n4 = new Natural(1);
			Natural n5 = new Natural(5);
			NaturalList nList = new NaturalList();
			nList.push(n1);
			nList.push(n2);
			nList.push(n3);
			nList.push(n4);
			nList.push(n5);
			nList.push(n6);
			nList.sort();
	}
	
	/** SEARCH */
	
	@Test
	void falsifySearchPrecondition() {
		assertThrows(PreconditionError.class, () -> {
			NaturalList nList = new NaturalList();
			nList.search(null);
		});	
	}
	
	@Test
	void falsifySearchPreconditionNotSortedList() {
		Natural n1 = new Natural(1);
		Natural n2 = new Natural(4);
		Natural n3 = new Natural(4);
		Natural n4 = new Natural(7);
		Natural n5 = new Natural(4);
		Natural n6 = new Natural(4);
		NaturalList nList = new NaturalList();
		nList.push(n1);
		nList.push(n2);
		nList.push(n3);
		nList.push(n4);
		nList.push(n5);
		nList.push(n6);
		assertThrows(PreconditionError.class, () -> {
			nList.search(new Natural(4));
		});	
	}
	
	@Test
	void satisfySearchPostconditionElementExists() {
			Natural n1 = new Natural(1);
			Natural n2 = new Natural(4);
			Natural n3 = new Natural(4);
			Natural n4 = new Natural(4);
			Natural n5 = new Natural(4);
			Natural n6 = new Natural(7);
			Natural n7 = new Natural(84);
			Natural n8 = new Natural(7);
			NaturalList nList = new NaturalList();
			nList.push(n1);
			nList.push(n2);
			nList.push(n3);
			nList.push(n4);
			nList.push(n5);
			nList.push(n6);
			nList.push(n7);
			nList.push(n8);
			nList.sort();
			nList.search(new Natural(4));
	}
	
	@Test
	void satisfySearchPostconditionDoesntExist() {
			Natural n1 = new Natural(1);
			Natural n2 = new Natural(4);
			Natural n3 = new Natural(4);
			Natural n4 = new Natural(4);
			Natural n5 = new Natural(4);
			Natural n6 = new Natural(7);
			Natural n7 = new Natural(84);
			Natural n8 = new Natural(7);
			NaturalList nList = new NaturalList();
			nList.push(n1);
			nList.push(n2);
			nList.push(n3);
			nList.push(n4);
			nList.push(n5);
			nList.push(n6);
			nList.push(n7);
			nList.push(n8);
			nList.sort();
			nList.search(new Natural(94));
	}
}
