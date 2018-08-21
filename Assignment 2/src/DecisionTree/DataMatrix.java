package DecisionTree;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DataMatrix {
	String fileName;
	String line;
	ArrayList<ArrayList<String>> matrix;
	
	int count;
	int c;
	int r;
	Map<String, Integer> attribute;
	String [] attributes;
	
	public DataMatrix(String filename){
		this.fileName = filename;
		line = null;
		matrix = new ArrayList<ArrayList<String>>();
		
		count = 0;
	}
	public String[][] get(){
		try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
            	if(line.length() > 0){
            		if(( ! String.valueOf(line.charAt(0)).equals("%"))){
            		
            			if(String.valueOf(line.charAt(0)).equals("@")){
            				//String attribute = line.substring(1, 4);
            				if(String.valueOf(line.charAt(1)).equals("A")){
            				String[] attributeRow = line.split("\\s+");
            				String attributevalue  = attributeRow[2].replace("{", "");
            				attributevalue = attributevalue.replace("}", "");
            				String[] attributevalues = attributevalue.split(",");
            				
            				matrix.add(new ArrayList<String>());
            				matrix.get(count).add(attributeRow[1]);
            				String combined = "";
            				for(int i = 0; i < attributevalues.length ; i++){
            					combined = combined + attributevalues[i] +" ";
            				}
            				matrix.get(count).add(combined);
            				count++;
            				
            				}
            			}
            			else{
            				String[] dataRow = line.split(",");
            				for(int i = 0; i < dataRow.length; i++ ){
            					matrix.get(i).add(dataRow[i]);
            				}
            			}
            		}
            	}
            	
            }
            
            // Always close files.
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileName + "'");                  
            // Or we could just do this: 
            // ex.printStackTrace();
        }
        
        c = matrix.size();
        r = matrix.get(0).size();
        String[][] newMatrix = new String[r][c];
        for(int i = 0; i < matrix.size(); i ++){
        	for(int j = 0; j < matrix.get(0).size(); j++){
        		newMatrix[j][i] = matrix.get(i).get(j); 
        	}
        	
        }
        
        return newMatrix;

	}
	public int getNrCols(){
		return c;
		
	}
	public int getNrRows(){
		return r;
	}
	public String[] getColumn(int nr){
		String[] column = new String[r];
		for(int i = 0; i < r ; i++){
			column[i] = matrix.get(nr).get(i); 
		}
		return column;
	}
	public void printMatrix(){
		for(int i = 0; i < getNrRows() ; i++){
			for(int j = 0; j < getNrCols(); j++){
				System.out.print(matrix.get(j).get(i) + "     ");
			}
			System.out.println();
		}
	}
	
	public String[][] getExamples(String[][] mat){
		String[][] examples = new String[this.r-2][this.c];
		for(int i = 2; i < this.r; i++){
			for(int j = 0; j < this.c; j++){
				examples[i-2][j] = mat[i][j];
			}
		}
		return examples;
	}
	
	public String[] getAttributes(String[][] temp){
		String[] attributes = new String[this.c];
		for(int o = 0; o < this.c; o++){
			attributes[o] = temp[0][o];
			//System.out.println(temp[0][o]);
		}
		return attributes;
	}
	public ArrayList<ArrayList<String>> toArrayList(){
		get();
		return matrix;
	}
}