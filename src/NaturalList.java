import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.java.contract.ContractImport;
import com.google.java.contract.Ensures;
import com.google.java.contract.Invariant;
import com.google.java.contract.Requires;


@ContractImport("java.util.ArrayList")
//Add an invariant here.
@Invariant({"numbers != null", "!anyNullElementsInList()"})
public class NaturalList {
	private ArrayList<Natural> numbers;
	
	// No contracts required for the following methods.
	public NaturalList(NaturalList o) { 
		numbers = new ArrayList<Natural>();
		for(Natural n : o.numbers) 
			numbers.add(new Natural(n));
	}
	
	public NaturalList() {
		numbers = new ArrayList<Natural>();
	}
	
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof NaturalList)) return false;
		NaturalList other = (NaturalList) o;
		return numbers.equals(other.numbers);
	}

	@Override
	public String toString() {
		return numbers.toString(); 
	}

	// Add contracts to all following methods.
	
	@Requires({"n != null", "spaceForElement()"})
	@Ensures({"bruh(old(numbers))"})
	public void push(Natural n) {
		numbers.add(n);
	}
	
	private boolean bruh(ArrayList<Natural> bruh) {
		System.out.println(bruh);
		return true;
	}
	
	public Natural get(int i) {
		return numbers.get(i);
	}
	
	public void set(int i, Natural n) {
		numbers.set(i, n);
	}
	
	public void sort() {
		Collections.sort(numbers);
	}
	
	public int search(Natural n) {
		return Collections.binarySearch(numbers, n);
	}
	
	/** MY FUNCTIONS BELOW */
	
	private boolean anyNullElementsInList() {
		for(Natural naturalNumberObject : numbers)
			if(naturalNumberObject == null)
				return true;
		
		return false;
	}
	
	private boolean spaceForElement() {
		return numbers.size() < Integer.MAX_VALUE;
	}
	
	private boolean hasOneMoreElementThan(NaturalList naturalList2) {
		System.out.println(numbers + " , == " + naturalList2.numbers);
		return this.numbers.size() == naturalList2.numbers.size() + 1;
	}
	
	private boolean containsSameElementsAs(NaturalList naturalList2) {
		ArrayList<Natural> list2 = naturalList2.numbers;
		
//		for(int naturalIndex = 0; naturalIndex < naturalList2.length; naturalIndex++) {
//			
//		}
		
		return true;
	}
}
