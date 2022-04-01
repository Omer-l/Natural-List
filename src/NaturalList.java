import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.google.java.contract.ContractImport;
import com.google.java.contract.Ensures;
import com.google.java.contract.Invariant;
import com.google.java.contract.Requires;


@ContractImport({"java.util.ArrayList"})
//Add an invariant here.
@Invariant({"numbers != null && !nullElementsExist()"})
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
	@Ensures({"naturalAtEndOfList(n) && listEqualsSkippingOne(numbers, old(new ArrayList<>(numbers)))"})
	public void push(Natural n) {
		numbers.add(n);
	}
	
	@Requires({"!empty() && withinBounds(i)"})
	@Ensures({"objectHasNotChanged(old( new NaturalList(this))) && elementExistsAtIndex(i, result)"})
	public Natural get(int i) {
		return numbers.get(i);
	}
	
	@Requires({"n != null && withinBounds(i)"})
	@Ensures({"elementExistsAtIndex(i, n) && allOtherElementsAreTheSame(i, old( new NaturalList(this))) && lengthIsTheSameAs(old( new NaturalList(this)))"})
	public void set(int i, Natural n) {
		numbers.set(i, n);
	}
	
	@Requires("!empty()")
	@Ensures("isSorted() && containsSameElements(old( new NaturalList(this))) && lengthIsTheSameAs(old( new NaturalList(this)))")
	public void sort() {
		Collections.sort(numbers);
	}
	
	@Requires("n != null && isSorted()")
	@Ensures("objectHasNotChanged(old( new NaturalList(this))) && correctlyBinarySearched(n, result)")
	public int search(Natural n) {
		return Collections.binarySearch(numbers, n);
	}
	
	/** MY FUNCTIONS BELOW */
	
	private boolean nullElementsExist() {
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
	
	private boolean naturalAtEndOfList(Natural n) {
		return numbers.get(numbers.size() - 1).compareTo(n) == 0;
	}
	
	private boolean empty() {
		return numbers.isEmpty();
	}
	
	private boolean objectHasNotChanged(NaturalList naturalList) {
		return this.equals(naturalList);
	}
	
	private boolean elementExistsAtIndex(int index, Natural natural) {
		return natural.equals(numbers.get(index));
	}
	
	private boolean lengthIsTheSameAs(NaturalList naturalList) {
		return numbers.size() == naturalList.numbers.size();
	}
	
	private boolean allOtherElementsAreTheSame(int index, NaturalList listWithDifferentElementAtGivenIndex) {
		ArrayList<Natural> listMissingElementAtGivenIndex = new ArrayList<Natural>(numbers);
        listMissingElementAtGivenIndex.remove(index);
        listWithDifferentElementAtGivenIndex.numbers.remove(index);
        return listMissingElementAtGivenIndex.equals(listWithDifferentElementAtGivenIndex.numbers);
	}
	
	private boolean isSorted() {
		for(int naturalIndex = 1; naturalIndex < numbers.size(); naturalIndex++) {
			Natural nextNumber = numbers.get(naturalIndex);
			Natural previousNumber = numbers.get(naturalIndex - 1);
			
			if(nextNumber.compareTo(previousNumber) == -1) //NOT greater than or equal to the previous number
				return false;
		}
		return true;
	}
	
	private boolean containsSameElements(NaturalList list) {
		return list.numbers.containsAll(this.numbers);
	}
	
	private boolean correctlyBinarySearched(Natural n, int resultIndex) {
		return numbers.get(resultIndex).compareTo(n) == 0;
	}
	
	private boolean withinBounds(int index) {
		return 0 <= index && index < numbers.size();
	}
}
