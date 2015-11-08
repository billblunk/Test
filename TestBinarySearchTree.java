import java.util.*;
/**
 *
 * @author Bill Blunk - C202 - IUS - Edited to include new method calls
 */
public class TestBinarySearchTree
{
    /**
     *
     * @param args
     * @ensures updates to list of integers.
     */
    public static void main(String[] args)
  {
      Integer[] num = {67, 87, 55, 43, 48, 73, 91, 39, 59, 92, 34, 95, 81, 66, 40, 53, 84, 77};
      // Create an empty BinaryTree
      BinarySearchTree<Integer> tree = new BinarySearchTree<>(num);
     
      Scanner input = new Scanner(System.in);
      System.out.println("\nEnter an element to search");
      Integer key = input.nextInt();

      System.out.println("Search is: " + tree.search(key));
      
      System.out.println("\nEnter an element to delete");
      key = input.nextInt();
      System.out.println("Element is deleted: " + tree.delete(key));
      System.out.println();
            
      key = 52;
      System.out.println("Element is inserted: " + tree.insert(key));
      
      System.out.print("Inorder: ");
      tree.inorder();
      System.out.println();
      
      System.out.print("Preorder: ");
      tree.preorder();
      System.out.println();
      
      System.out.print("Postorder: ");
      tree.postorder();
      System.out.println();

      key = 95;
      System.out.println("Path is: " + tree.path(key));
      
      key = 43;
      System.out.println("Left Sub Tree is: " + tree.leftSubTree(key));
      
      key = 73;
      System.out.println("Right Sub Tree is: " + tree.rightSubTree(key));
      
      System.out.println("Number of leaves is: " + tree.getNumberOfLeaves());
      
      key = 87;
      System.out.println("Predecessor of " + key + " is: " + tree.inorderPredecessor(key));
      
      System.out.println("Clear tree. "); 
      tree.clear();
      System.out.print("Inorder: ");
      tree.inorder();
      System.out.println();
      
      //complete the code as suggested in the Lab document.
     
  } // end of main
} // end of class TestBianrySearch

/*
Output:
run:

Enter an element to search
53
Search is: true

Enter an element to delete
52
Element is deleted: false

Element is inserted: true
Inorder: 34 39 40 43 48 52 53 55 59 66 67 73 77 81 84 87 91 92 95 
Preorder: 67 55 43 39 34 40 48 53 52 59 66 87 73 81 77 84 91 92 95 
Postorder: 34 40 39 52 53 48 43 66 59 55 77 84 81 73 95 92 91 87 67 
Path is: [67, 87, 91, 92, 95]
Left Sub Tree is: [39, 34, 40]
Right Sub Tree is: [81, 77, 84]
Number of leaves is: 7
Predecessor of 87 is: 81
Clear tree. 
Inorder: 
BUILD SUCCESSFUL (total time: 15 seconds)
*/