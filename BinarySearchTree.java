
import java.util.*;

/**
 *
 * @author Bill Blunk - C202 - IUS
 * @param <E>
 */
public class BinarySearchTree<E extends Comparable<E>> extends AbstractTree<E>
{

    protected TreeNode<E> root;

    protected int size = 0;
    String inorder;

    /**
     * Create a default binary tree
     */
    public BinarySearchTree()
    {
    }

    /**
     * Create a binary tree from an array of objects
     * @param objects
     */
    public BinarySearchTree(E[] objects)
    {
        for (int i = 0; i < objects.length; i++)
        {
            insert(objects[i]);
        }
    }

    /**
     * Returns true if the element is in the tree
     * @param e
     * @return 
     */
    public boolean search(E e)
    {
        TreeNode<E> current = root; // Start from the root
        while (current != null)
        {
            if (e.compareTo(current.element) < 0)
            {
                current = current.left;
            } else if (e.compareTo(current.element) > 0)
            {
                current = current.right;
            }
            else // element matches current.element
            {
                return true; // Element is found
            }
        }
        return false;
    }
    
    public boolean search(E e, int [] count)
    {
        TreeNode<E> current = root; // Start from the root
        int i = 0;
        count[0] = 0;
        while (current != null)
        {
            if (e.compareTo(current.element) < 0)
            {
                count[0] = count[0] + 1;                
                current = current.left;
            } 
            else if (e.compareTo(current.element) > 0)
            {
                count[0] = count[0] + 1;                
                current = current.right;
            }
            else // element matches current.element
            {
                count[0] = count[0] + 1;
                return true; // Element is found
            }
        }
        return false;
    }

    /**
     * Insert element into the binary tree
     * @param e
     * @return true if element inserted, false if element not inserted
     */
    public boolean insert(E e)
    {
        if (root == null)
        {
            root = createNewNode(e); 
        }
        else
        {
            // Locate the parent node
            TreeNode<E> parent = null;
            TreeNode<E> current = root;
            while (current != null)
            {
                if (e.compareTo(current.element) < 0)
                {
                    parent = current;
                    current = current.left;
                } else if (e.compareTo(current.element) > 0)
                {
                    parent = current;
                    current = current.right;
                } 
                else
                {
                    return false; // Duplicate node not inserted
                }
            }
            // Create the new node and attach it to the parent node
            if (e.compareTo(parent.element) < 0)
            {
                parent.left = createNewNode(e);
            }
            else
            {
                parent.right = createNewNode(e);
            }
        }
        size++;
        return true; // Element inserted
    }

    /**
     *
     * @param e
     * @return new node e
     */
    protected TreeNode<E> createNewNode(E e)
    {
        return new TreeNode<E>(e);
    }

    /**
     * Inorder traversal from the root
     */
    public void inorder()
    {
        inorder(root);
    }

    /**
     * @param root
     * @ return inorder traversal of tree
     */
    protected void inorder(TreeNode<E> root)
    {
        if (root == null)
        {
            return;
        }
        inorder(root.left);
        System.out.print(root.element + " ");
        inorder(root.right);
    }

    /**
     * Postorder traversal from the root
     */
    public void postorder()
    {
        postorder(root);
    }

    /**
     * @param root
     * @return postorder traversal of tree
     */
    protected void postorder(TreeNode<E> root)
    {
        if (root == null)
        {
            return;
        }
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.element + " ");
    }

    /**
     * Preorder traversal from the root
     */
    public void preorder()
    {
        preorder(root);
    }

    /**
     * Preorder traversal from a subtree
     * @param root
     */
    protected void preorder(TreeNode<E> root)
    {
        if (root == null)
        {
            return;
        }
        System.out.print(root.element + " ");
        preorder(root.left);
        preorder(root.right);
    }

    /**
     * Inner class tree node
     * @param <E>
     */
    public static class TreeNode<E extends Comparable<E>>
    {

        E element;
        TreeNode<E> left;
        TreeNode<E> right;

        /**
         *
         * @param e
         */
        public TreeNode(E e)
        {
            element = e;
        }
    }

    /**
     * Get the number of nodes in the tree
     * @return integer of number of nodes
     */
    public int getSize()
    {
        return size;
    }

    /**
     * @returns the root of the tree
     */
    public TreeNode getRoot()
    {
        return root;
    }

    /**
     * 
     * @param e
     * @return an ArrayList containing elements in the path from the root
     * to the specified element, returns an empty ArrayList if no such
     * element exists.
     */
    public ArrayList<E> path(E e)
    {
        java.util.ArrayList<E> list = new java.util.ArrayList<>();
        TreeNode<E> current = root; // Start from the root
        if (search(e)) {
            while (current != null)
            {
                if (e.compareTo(current.element) < 0)
                {
                    list.add(current.element);
                    current = current.left;
                }
                else if (e.compareTo(current.element) > 0)
                {
                    list.add(current.element);
                    current = current.right;
                }
                else // element matches current.element
                {
                    list.add(current.element);
                    break;// Element is found
                }
            }
            return list;
        }
        return list; // Return an array of elements
    }

   

    /*
     * @return the number of leaf nodes in this tree,
     * 0 if tree is empty
     */   
    public int getNumberOfLeaves()
    {
        int numberOfLeaves = 0;
        numberOfLeaves = getNumberOfLeaves(root);
        return numberOfLeaves;
    }

    /**
     * get
     *
     * @param root
     * @return Number Of Leaves travered from a subtree
     */
    protected int getNumberOfLeaves(TreeNode<E> root)
    {
        int numberOfLeaves = 0;
        if (root == null)
        {
            return numberOfLeaves;
        }
        if (root.left == null && root.right == null)
        {
            numberOfLeaves++;
        }
        else
        {
            numberOfLeaves += getNumberOfLeaves(root.left);
            numberOfLeaves += getNumberOfLeaves(root.right);
        }
        return numberOfLeaves;
    }


    /**
     *
     * @param e
     * @return an ArrayList containing all elements in preorder
     *  of the specified element’s left sub-tree,
     *  returns an empty ArrayList if no such element exists.
     */
    
    public ArrayList<E> leftSubTree(E e)
    {
        java.util.ArrayList<E> list = new java.util.ArrayList<>();
        TreeNode<E> current = root;
        while (current != null) 
        {
            if (e.compareTo(current.element) < 0)
            {
                current = current.left;
            } else if (e.compareTo(current.element) > 0)
            {
                current = current.right;
            }
            else 
            {
                break;
            }
        }
        if (current != null)
        {
            leftSubTree(current.left, 0, list);
        }
        return list; // ASK HIM IF IT PASSES BY REFERENCE AND CHANGES WITHOUT PASSING IT THROUGH
    }

    /**
     *
     * @param current
     * @param i
     * @param list
     * @return left subtree elements
     */
    
    protected int leftSubTree(TreeNode<E> current, int i, java.util.ArrayList<E> list)
    {
        if (current == null)
        {
            return i;
        }
        list.add(current.element);
        i++;
        i = leftSubTree(current.left, i, list);
        i = leftSubTree(current.right, i, list);
        return i;
    }

    /* 

    /**
     *
     * @param e
     * @return an ArrayList containing all elements in preorde
     *  of the specified element’s right sub-tree, returns an empty ArrayList
     *  if no  such element exists.
     */
    
    public ArrayList<E> rightSubTree(E e)
    {
        java.util.ArrayList<E> list = new java.util.ArrayList<>();
        TreeNode<E> current = root;
        while (current != null)
        {
            if (e.compareTo(current.element) < 0)
            {
                current = current.left;
            }
            else if (e.compareTo(current.element) > 0)
            {
                current = current.right;
            }
            else
            {
                break;
            }
        }
        if (current != null)
        {
            rightSubTree(current.right, 0, list);
        }
        return list;
    }

    /* recusive step to add nodes to the array List*/

    /**
     *
     * @param current
     * @param i
     * @param list
     * @return none
     */
    
    protected int rightSubTree(TreeNode<E> current, int i, java.util.ArrayList<E> list)
    {
        if (current == null)
        {
            return i;
        }
        list.add(current.element);
        i++;
        i = rightSubTree(current.left, i, list);
        i = rightSubTree(current.right, i, list);
        return i;
    }

    /* Returns inorder predecessor for a specified element, returns null if tree is empty or element 'e' is not in the tree. */

    /**
     *
     * @param e
     * @return inorder predecessor
     */
    
    public E inorderPredecessor(E e)
    {
        TreeNode<E> current = root;
        while (current != null) {
            if (e.compareTo(current.element) < 0)
            {
                current = current.left;
            } else if (e.compareTo(current.element) > 0)
            {
                current = current.right;
            } else {
                break;//
            }
        }
        TreeNode<E> parentOfRightMost = current;
        TreeNode<E> rightMost = current.left;

        while (rightMost.right != null)
        {
            parentOfRightMost = rightMost;
            rightMost = rightMost.right; // Keep going to the right
        }
        return parentOfRightMost.element;
    }

    /**
     * Delete an element from the binary tree. Return true if the element is
     * deleted successfully Return false if the element is not in the tree
     * @param e
     * @return 
     */
    public boolean delete(E e)
    {
        // Locate the node to be deleted and also locate its parent node
        TreeNode<E> parent = null;
        TreeNode<E> current = root;
        while (current != null)
        {
            if (e.compareTo(current.element) < 0)
            {
                parent = current;
                current = current.left;
            }
            else if (e.compareTo(current.element) > 0)
            {
                parent = current;
                current = current.right;
            } else
            {
                break; // Element is in the tree pointed by current
            }
        }
        if (current == null)
        {
            return false; // Element is not in the tree
        }    // Case 1: current has no left children
        if (current.left == null)
        {
            // Connect the parent with the right child of the current node
            if (parent == null)
            {
                root = current.right;
            }
            else
            {
                if (e.compareTo(parent.element) < 0)
                {
                    parent.left = current.right;
                } 
                else
                {
                    parent.right = current.right;
                }
            }
        }
        else
        {
            // Case 2 & 3: The current node has a left child
            // Locate the rightmost node in the left subtree of
            // the current node and also its parent
            TreeNode<E> parentOfRightMost = current;
            TreeNode<E> rightMost = current.left;

            while (rightMost.right != null)
            {
                parentOfRightMost = rightMost;
                rightMost = rightMost.right; // Keep going to the right
            }
            // Replace the element in current by the element in rightMost
            current.element = rightMost.element;

            // Eliminate rightmost node
            if (parentOfRightMost.right == rightMost)
            {
                parentOfRightMost.right = rightMost.left;
            }
            else // Special case: parentOfRightMost == current
            {
                parentOfRightMost.left = rightMost.left;
            }
        }
        size--;
        return true; // Element inserted
    }

    /**
     * Obtain an iterator. Use inorder.
     * @return in order iterator
     */
    public java.util.Iterator iterator()
    {
        return inorderIterator();
    }

    /**
     * Obtain an inorder iterator
     * @return 
     */
    public java.util.Iterator inorderIterator()
    {
        return new InorderIterator();
    }

    // Inner class InorderIterator
    class InorderIterator implements java.util.Iterator 
    {
        // Store the elements in a list
        private java.util.ArrayList<E> list = new java.util.ArrayList<E>();
        private int current = 0; // Point to the current element in list

        public InorderIterator()
        {
            inorder(); // Traverse binary tree and store elements in list
        }

        /**
         * Inorder traversal from the root
         */
        private void inorder()
        {
            inorder(root);
        }

        /**
         * Inorder traversal from a subtree
         */
        private void inorder(TreeNode<E> root)
        {
            if (root == null)
            {
                return;
            }
            inorder(root.left);
            list.add(root.element);
            inorder(root.right);
        }

        /**
         * Next element for traversing?
         */
        public boolean hasNext()
        {
            if (current < list.size())
            {
                return true;
            }
            return false;
        }

        /**
         * Get the current element and move cursor to the next
         */
        public Object next()
        {
            return list.get(current++);
        }

        /**
         * Remove the current element and refresh the list
         */
        public void remove()
        {
            delete(list.get(current)); // Delete the current element
            list.clear(); // Clear the list
            inorder(); // Rebuild the list
        }
    }

    /**
     * Remove all elements from the tree
     */
    public void clear()
    {
        root = null;
        size = 0;
    }
}// end of class binarysearchtree
