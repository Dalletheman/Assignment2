package DecisionTree;

import java.util.ArrayList;

public class new_importance {
	ArrayList<ArrayList<String>> matrix;
	int rows;
	int cols;
	
	public new_importance(ArrayList<ArrayList<String>> matrix){
		this.matrix = matrix;
		rows = matrix.get(0).size();
		cols = matrix.size();
	}
	// räknar ut gain för ett visst attribut
	public double gain(int attribute) {
		String [] alternatives = getAlternatives(attribute);
		String [] goals = getAlternatives(cols - 1);
		int[] totGoals = new int[goals.length];
		double firstEntropy = 0.0;
		double secondEntropy = 0.0;
		int totalEx = rows - 2;
		for(int i = 0 ; i < goals.length ; i++) {
			
		}
		for(int i = 0 ; i < alternatives.length ; i++) {
			int[] calcValues = calc(attribute, alternatives[i]);
			int sumValue = 0;
			for(int j = 0 ; j < calcValues.length ; j++) {
				sumValue = sumValue + calcValues[j];
				totGoals[j] = totGoals[j] + calcValues[j];
			}
			secondEntropy = secondEntropy + (sumValue*1.0 / totalEx) * entropy(calcValues);
		}
		firstEntropy = entropy(totGoals);
		return firstEntropy - secondEntropy;
	}
	//Returnerar en vektor som har samma längd som antalet värden mål-attributet har
	//Platserna i vektorn representerar antalet för varje värde
	public int[] calc(int attribute, String value) {
		String[] goals = getAlternatives(cols - 1);
		int[] count = new int[goals.length];
		
		for(int i = 2 ; i < rows ; i++ ) {
			if(value.equals(matrix.get(attribute).get(i))) {
				for(int j = 0 ; j < goals.length ; j++) {
					if(goals[j].equals(matrix.get(cols - 1).get(i))) {
						count[j]++;
					}
				}
			}
		}
		return count;
	}
	
	// Returnerar index för attributet med största gain
	public int highestGain() {
		int index = 0;
		double highestGain = 0;
		
		for(int i = 0 ; i < cols - 1 ; i++) {
			if(gain(i) > highestGain) {
				index = i;
				highestGain = gain(i);
			}
		}
		return index;
	}
	
	// Antal exempel
	private int totalEx() {
		return rows - 2;
	}
	
	// hämtar värdena från attributet
	private String[] getAlternatives(int col){ 
		String alternatives = matrix.get(col).get(1);
		String[] altSplit = alternatives.split(" ");
		return altSplit;
	}
	
	
    private double log2(double n) {
        if(n == 0) {
        	return 0.0;
        }
        else {
        	return (Math.log(n) / Math.log(2));
        }	
    }
    // Ränkar ut entropin
    public double entropy(int[] a) {
    	int tot = 0;
    	for(int i = 0 ; i < a.length ; i++) {
    		tot = tot + a[i];
    	}
    	double sum = 0;
    	for(int i = 0 ; i < a.length ; i++) {
    		sum = sum - ((a[i]*1.0)/tot)*(log2((a[i]*1.0)/tot));
    	}
    	return sum;
    }
}
