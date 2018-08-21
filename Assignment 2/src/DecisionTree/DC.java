package DecisionTree;

import java.util.ArrayList;

public class DC {
	ArrayList<ArrayList<String>> matrix;
	private int cols;
	private int rows;
	
	public DC(ArrayList<ArrayList<String>> matrix){
		this.matrix = matrix;
		this.cols = matrix.size();
		this.rows = matrix.get(0).size();
	}
	// Gör en mindre matris av "wholeMatrix". Den nya matrisen innehålle alla exempel med värdet "AttributeValue" hos attributet "attribute" 
	public ArrayList<ArrayList<String>> splitMatrix(ArrayList<ArrayList<String>> wholeMatrix, String AttributeValue, int attribute){
		ArrayList<ArrayList<String>> newMatrix = new ArrayList<ArrayList<String>>();
		for(int i = 0; i < wholeMatrix.size(); i++){
			newMatrix.add(new ArrayList<String>());
			newMatrix.get(i).add(wholeMatrix.get(i).get(0));
			newMatrix.get(i).add(wholeMatrix.get(i).get(1));
		}
		ArrayList<Integer> addRows = new ArrayList<Integer>();
		for(int i = 2; i < wholeMatrix.get(0).size(); i++){
			if(AttributeValue.equals(wholeMatrix.get(attribute).get(i))){
				addRows.add(i);
			}
		}
		if(! addRows.isEmpty()){
			for(int j = 0; j < addRows.size(); j++){
				for(int l = 0; l< wholeMatrix.size(); l++){
					newMatrix.get(l).add(wholeMatrix.get(l).get(addRows.get(j)));
				}
			}
		}
		newMatrix.remove(attribute);
		return newMatrix;
	}
	public ArrayList<String> getRow(int row, ArrayList<ArrayList<String>> currentMatrix){
		ArrayList<String> list = new ArrayList<String>();
		if(! (matrix.get(0).size() < 3)){
			for(int i = 0; i < currentMatrix.size(); i++){
				list.add(currentMatrix.get(i).get(row));
			}
		}
		return list;
	}
	public String getAttribute(int col) {
		return matrix.get(col).get(0);
	}
	public String[] getAlternatives(int col){
		String alternatives = matrix.get(col).get(1);
		String[] altSplit = alternatives.split(" ");
		return altSplit;
	}
	// Kollar om "Goal" är samma för alla exempel
	public boolean sameValue() {
		String ref = matrix.get(cols - 1).get(2);
		for(int i = 3 ; i < rows ; i++){
			if(!(matrix.get(cols - 1).get(i).equals(ref))) {								
				return false;
			}
		}
		return true;
	}
	public String getGoal() {
		return matrix.get(cols - 1).get(2);
	}
	// Används då attributen är slut eller exempel är slut
	public String plurality() {
		
		String[] alternatives = getAlternatives(matrix.size()-1);
		int[] score = new int[alternatives.length];

		for(int i = 0 ; i < alternatives.length ; i++) {
			score[i] = 0;
			for(int j = 2 ; j < matrix.get(0).size() ; j++) {
				if(alternatives[i].equals(matrix.get(cols - 1).get(j))) {
					score[i] ++;
				}
			}	
		}
		int largest = 0;
		for(int i = 1 ; i < score.length ; i++) {
			if(score[i] > score[largest]) {
				largest = i;
			}
			
		}
		return alternatives[largest];
	}

}
