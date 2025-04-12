// name : Alireza Nejati
// Student ID : 4011262156

package ac.um.ds;

public class UnboundedInteger{
	private String val;

	public UnboundedInteger(int value) {
		this.val = Integer.toString(value);
	}

	public UnboundedInteger(String value) {
		this.val = value;
	}

	public int getDigit(int r, int j){
		int result = 0 ;
		int first_char_index = val.length() - (r * j) ;
		int last_char_index = first_char_index + r ;
		int digit_index_in_result = 1 ;
		for (int i=0 ; i<r-1 ; i++) {
			digit_index_in_result *=10 ;
		}
		for (int i=first_char_index ; i<last_char_index ; i++) {
			if (i >= 0) {
				result += digit_index_in_result * (val.charAt(i) - '0');
			}
			digit_index_in_result /= 10;
		}
		return result ;
	}

	public String getVal() {
		return this.val;
	}

}
