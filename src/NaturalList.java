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
	
	@Requires({"!empty()"})
	@Ensures({"objectHasNotChanged(old( new NaturalList(this))) && elementExistsAtIndex(i, result)"})
	public Natural get(int i) {
		return numbers.get(i);
	}
	
	@Requires({"n != null"})
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
	@Ensures("containsSameElements(old( new NaturalList(this)))") //&& (-old( new NaturalList(this)).size() < result && result < old( new NaturalList(this)).size())
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
	
	private int getMaximumNatural(ArrayList<Natural> list) {
		Natural max = list.get(0);
		for(int listIndex = 0; listIndex < list.size(); listIndex++) {
			if(max.compareTo(list.get(listIndex)) < 0)
				max = list.get(listIndex);
		}

		return Integer.parseInt(max.toString());
	}
	
	private int[] countNumberOfTimesNaturalsAppeared(NaturalList list, int maximumNatural) {
		
		int numberOfNaturals = list.numbers.size();
		int[] counterListObject = new int[maximumNatural];
		ArrayList<Natural> naturals = list.numbers;
		for(int naturalIndex = 0; naturalIndex < numberOfNaturals; naturalIndex++) {
			int data = Integer.parseInt(naturals.get(naturalIndex).toString());
			counterListObject[data - 1]++;
		}
		
		return counterListObject;
	}
	
	private boolean containsSameElements(NaturalList list) {
		int maxNaturalInList = getMaximumNatural(list.numbers);
		int maxNaturalInThisList = getMaximumNatural(this.numbers);
		if(maxNaturalInList != maxNaturalInThisList) //they have a different maximum natural number
			return false;
		//count up given list's element occurences
		int[] countListNumberOfTimesNaturalsAppeared = countNumberOfTimesNaturalsAppeared(list, maxNaturalInList);
		int[] countThisNumberOfTimesNaturalsAppeared = countNumberOfTimesNaturalsAppeared(this, maxNaturalInList);
		//compare counter list
		for(int counterIndex = 0; counterIndex < maxNaturalInList; counterIndex++) {
			int countInList = countListNumberOfTimesNaturalsAppeared[counterIndex];
			int countInThisList = countThisNumberOfTimesNaturalsAppeared[counterIndex];
			if(countInList != countInThisList)
				return false; //number occured more times compared to the other list
		}
		return true;
	}
}
