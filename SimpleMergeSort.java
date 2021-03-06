import java.lang.Comparable;


public class SimpleMergeSort{
	public static Comparable[] merge(Comparable[] input){
		Comparable[] part1 = new Comparable[input.length/2];
		Comparable[] part2 = new Comparable[input.length/2];
		if(input.length > 2){
			System.arraycopy(input, 0, part1, 0, part1.length);
			System.arraycopy(input, part1.length, part2, 0, part2.length);
			part1 = merge(part1);
			part2 = merge(part2);
		}else{
			part1 = new Comparable[1];
			part2 = new Comparable[1];
			part1[0] = input[0];
			part2[0] = input[1];
		}
		int left = 0;
		int right = 0;
		int out = 0;
		Comparable[] output = new Comparable[input.length];
		if(part1.length == 1 && part2.length == 1){
			int result = part1[left].compareTo(part2[right]);
			if(result <= 0){
				output[0] = part2[0];
				output[1] = part1[0];
			} else {
				output[0] = part1[0];
				output[1] = part2[0];
			}
			return output;
		}
		while(left < part1.length || right < part2.length){
			int result = part1[left].compareTo(part2[right]);
			if( result == 0){
				if( part1[left] == null && part2[right] == null){
					break;
				}
				 output[out] = part1[left];
				 out++;
				 output[out] = part2[right];
				 out++;
				 left++;
				 right++;
			}
			else if(result < 0){
				output[out] = part2[right];
				right++;
				out++;
			}else{
				output[out] = part1[left];
				left++;
				out++;
			}
		}
		return output;
	}
}