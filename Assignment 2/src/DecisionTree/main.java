package DecisionTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class main {
	// DataMatrix gör om textfilen till en matris
	// Klassen DC används för att göra matrisoperationer
	// Tree besriver en nod i trädet
	// new_importance används för att avgöra vilket attribut som är bäst för tillfället
	// I main:
	// metoden print kan användas för att skriva ut hela matrisen
	// metoden DTL bygger upp trädet
	// metoden printTree skriver ut trädets struktur
	public static void main(String[] args) {
		String file = "/Users/Dalle/Dropbox/Skola/4/AI - EDAF70/Ass2/text.txt";
		DataMatrix matrix = new DataMatrix(file);
		ArrayList<ArrayList<String>> list = matrix.toArrayList();
		printTree(DTL(list,list));
		
	}
	private static void print(ArrayList<ArrayList<String>> list) {
		for(int r = 0; r < list.get(0).size(); r++){
			for(int c = 0; c < list.size(); c++){
				if(r != 1) {
				System.out.print(list.get(c).get(r)+ "    ");
				}
			}
			System.out.println();
		}
	}
	public static Tree DTL(ArrayList<ArrayList<String>> examples, ArrayList<ArrayList<String>> parentExamples) {
		 DC matrix = new DC(examples);
		 
		 if (examples.get(0).size() == 2) {
			 DC parentMatrix = new DC(parentExamples);
			 String root = parentMatrix.plurality();
			 Tree leaf = new Tree(root);
			 return leaf;
		 }
		 
		 else if (matrix.sameValue()) {
			 String root = matrix.getGoal();
			 Tree leaf = new Tree(root);
			 return leaf;
		 }
		 
		 else if (examples.size() == 1 && examples.get(0).size() > 2) {
			 String root = matrix.plurality();
			 Tree leaf = new Tree(root);
			 return leaf;
		 }
		 else {
		 
			 new_importance importance = new new_importance(examples);
			 int attributeNr = importance.highestGain();
			 String attribute = matrix.getAttribute(attributeNr);
			 Tree tree = new Tree(attribute);
		 
			 String[] values = matrix.getAlternatives(attributeNr);
			 for(String value : values) {
				 ArrayList<ArrayList<String>> nextExamples = matrix.splitMatrix(examples, value, attributeNr);
				 Tree subTree = DTL(nextExamples, examples);
				 tree.addSubtree(subTree, value);
			 }
			 return tree;
		 }
		
	}
	public static void printTree(Tree tree) {
		int level = -1;
		printTree(tree, level);
	}
	private static void printTree(Tree tree, int level) {
		level++;
		if(tree.subtreeSize() == 0) {
			System.out.print(": " + tree.getRoot());
		}
		else {
			ArrayList<Tree> subtrees = tree.getSubtrees();
			ArrayList<String> branches = tree.getBranches();
			for(int j = 0 ; j < tree.subtreeSize() ; j++) {
				System.out.println();
				for(int i = 0 ; i < level ; i++) {
					System.out.print("\t");
				}	
				System.out.print(tree.getRoot() + " = " + branches.get(j));
				
				printTree(subtrees.get(j), level);
			}
		}
		return;
	}
}
