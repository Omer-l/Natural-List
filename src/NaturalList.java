import com.google.java.contract.Ensures;
import com.google.java.contract.Invariant;
import com.google.java.contract.Requires;

//Add an invariant here.
@Invariant("data >= 0 && (data % 1) == 0 && data <= Integer.MAX_VALUE")
public class Natural implements Comparable<Natural> {
	private int data;

	// No contracts required for the following methods.

	@Override
	public boolean equals(Object o) {
		if(!(o instanceof Natural)) return false;
		Natural n = (Natural) o;
		return data==n.data;
	}
	
	public int compareTo(Natural n) { 
		return Integer.compare(data, n.data); 
	}
	
	public Natural(Natural n) {
		this(n.data);
	}

	@Override
	public String toString() {
		return Integer.toString(data); 
	}

	// Add contracts to all following methods.
	@Requires("d >= 0")
	@Ensures("data == d")
	public Natural(int d) {
		data = d;
	}
	
	@Requires("data < Integer.MAX_VALUE")
	@Ensures({"incrementedByOne(old(data), data)"})
	public void increment() {
		data++; 
	}
	
	@Requires("data > 0")
	@Ensures({"decrementedByOne(old(data), data)"})
	public void decrement() {
		data--;
	}
	
	@Requires("!overflowsAddition(data, n.data)") 
	@Ensures("correctlyAdded(old(data), n.data)")
	public void add(Natural n) {
		this.data += n.data;
	}

	@Requires("n.data <= data")
	@Ensures("correctlySubtracted(old(data), n.data)")
	public void subtract(Natural n) {
		data -= n.data;
	}
	
	@Requires("!overflowsMultiply(data, n.data)")
	@Ensures("correctlyMultiplied(old(data), n.data)")
	public void multiply(Natural n) {
		data *= n.data;
		System.out.println(data);
	}
	
	@Requires("n.data != 0") 
	@Ensures("correctlyDivided(old(data), n.data) && resultOfDivideNatural(old(data), n.data)")
	public void divide(Natural n) {
		data /= n.data;
	}
	
	/** MY FUNCTIONS */
	
	private boolean incrementedByOne(int oldData, int newData) {
		return oldData + 1 == newData;
	}
	
	private boolean decrementedByOne(int oldData, int newData) {
		return oldData - 1 == newData;
	}
	
	private boolean overflowsAddition(int n1, int n2) {
		int result = n1 + n2;

	    if (result < 0)
	        return true;
	    else
	    	return false;
	}

	private boolean correctlyAdded(int n1, int n2) {
		int result = n1 + n2;
		return result == this.data;
	}

	private boolean correctlySubtracted(int n1, int n2) {
		int result = n1 - n2;
		return result == this.data;
	}
	
	private boolean overflowsMultiply(int n1, int n2) {
		int result = n1 * n2; 
		boolean n1n2NotZero = n1 != 0 && n2 != 0;
		
	    if (n1n2NotZero && n1 != result / n2)
	        return true;
	    else
	    	return false;
	}

	private boolean correctlyMultiplied(int n1, int n2) {
		int result = n1 * n2;
		return result == this.data;
	}
	
	private boolean correctlyDivided(int n1, int n2) {
		int result = n1 / n2;
		return result == this.data;
	}
	
	private boolean resultOfDivideNatural(int n1, int n2) {
		return n1 % n2 == 0;
	}
}
