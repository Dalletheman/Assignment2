package DecisionTree;

import java.util.ArrayList;

public class Tree {
	private String root;
	private ArrayList<Tree> subtrees;
	private ArrayList<String> branches;
	
	public Tree(String root){
		this.root = root;
		this.branches = new ArrayList<String>();	
		this.subtrees = new ArrayList<Tree>();
		
	}
	
	public void addSubtree(Tree subtree, String attribute){
		branches.add(attribute);
		subtrees.add(subtree);
	}
	public String getRoot() {
		return root;
	}
	public int subtreeSize() {
		return subtrees.size();
	}
	public ArrayList<String> getBranches(){
		return branches;
	}
	public ArrayList<Tree> getSubtrees(){
		return subtrees;
	}
	
	
}
