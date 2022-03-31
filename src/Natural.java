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
	
	@Requires("n.data < Integer.MAX_VALUE") // less than because max value + 1 will overflow
	@Ensures("!overflowsAddition(old(data), n.data) && correctlyAdded(old(data), n.data)")
	public void add(Natural n) {
		this.data += n.data;
	}

	@Requires("n.data < Integer.MAX_VALUE") // less than because max value - max value will be 0
	@Ensures("!minusNumber(old(data), n.data) && correctlySubtracted(old(data), n.data)")
	public void subtract(Natural n) {
		data -= n.data;
	}
	
	@Requires("n.data != 0 && n.data <= Integer.MAX_VALUE") //1 * max value is max value worst case.
	@Ensures("!overflowsMultiply(old(data), n.data) && correctlyMultiplied(old(data), n.data)")
	public void multiply(Natural n) {
		data *= n.data;
		System.out.println(data);
	}
	
//	@Requires("n.data != 0 && n.data <= Integer.MAX_VALUE") //max value divided by max value is worst case.
//	@Ensures("!overflowsMultiply(old(this), n) && correctlyMultiplied(old(data), n.data)")
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

	private boolean minusNumber(int n1, int n2) {
		int result = n1 - n2;
	
	    if (result < 0)
	        return true;
	    else
	    	return false;
	}

	private boolean correctlySubtracted(int n1, int n2) {
		int result = n1 - n2;
		return result == this.data;
	}
	
	private boolean overflowsMultiply(int n1, int n2) {
		int result = n1 * n2; 

	    if (n1 != result / n2)
	        return true;
	    else
	    	return false;
	}

	private boolean correctlyMultiplied(int n1, int n2) {
		int result = n1 * n2;
		return result == this.data;
	}
}
