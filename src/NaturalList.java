import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.java.contract.ContractImport;
import com.google.java.contract.Ensures;
import com.google.java.contract.Invariant;
import com.google.java.contract.Requires;


@ContractImport({"java.util.ArrayList"})
//Add an invariant here.
@Invariant({"numbers != null && !anyNullElementsInList()"})
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
	
	@Requires({"n != null && spaceForElement()"})
	@Ensures({"naturalPushed(n) && listEqualsSkippingOne(numbers, old(new ArrayList<>(numbers)))"})
	public void push(Natural n) {
		numbers.add(n);
	}
	
	@Requires({"!empty()", "i >= 0 && i < numbers.size()"})
	@Ensures({"objectHasNotChanged(old( new NaturalList(this))) && isTheElement(i, result)"})
	public Natural get(int i) { //TODO after set come back and test this for isTheElement
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
		for(int naturalIndex = 0; naturalIndex < numbers.size(); naturalIndex++) {
			Natural naturalNumberObject = numbers.get(naturalIndex);
			if(naturalNumberObject == null)
				return true;
		}
		return false;
	}
	
	private boolean spaceForElement() {
		return numbers.size() < Integer.MAX_VALUE;
	}
	
	private static boolean hasOneMoreElement(ArrayList<Natural> list1, ArrayList<Natural> list2) {
		System.out.println(list1 + " : " + list2);
		return list1.size() == list2.size() + 1;
	}
	
    private static boolean listEqualsSkippingOne(ArrayList<Natural> longerList, ArrayList<Natural> shorterList) {
			ArrayList<Natural> listMissingLastElement = new ArrayList<Natural>(longerList);
            listMissingLastElement.remove(listMissingLastElement.size() - 1);
            return listMissingLastElement.equals(shorterList);
    }
	
	private boolean naturalPushed(Natural n) {
		return numbers.get(numbers.size() - 1).compareTo(n) == 0;
	}
	
	private boolean empty() {
		return numbers.isEmpty();
	}
	
	private boolean objectHasNotChanged(NaturalList naturalList) {
		return this.equals(naturalList);
	}
	
	private boolean isTheElement(int index, Natural natural) {
		return natural.equals(numbers.get(index));
	}
}
