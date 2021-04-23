import java.util.*;
import java.io.*;


/**
 * BinarySearchTree -- this class uses a private inner class for
 *      tree nodes. (Although in this version it is public so I can 
 *      do a prefix walk in DisplayPanel.)
 *
 * @author cs416
 */
public class BinarySearchTree 
{
    //-------------------- instance variables ---------------------
    private Node   _root;
    private int    _size;
    private Node maxNode;
    private int max;
    private String str = "";
    
    //-------------------- constructors --------------------------
    /**
     * Construct an empty tree with no nodes.
     */
    public BinarySearchTree()
    {
        _root = null;
        _size = 0;
    }
    
    /**
     * Construct a tree with a root.
     * @param rootData Data
     */ 
    public BinarySearchTree( Data rootData )
    {
        _root = new Node( rootData );
        _size = 1;
    }
    //--------------------- root() ----------------------------------
    /**
     * return the root of the tree; this is package access so that DisplayPanel
     * can do a prefix walk of the tree. Would be better to have multiple
     * iterators.
     * 
     * @return Node
     */
    public Node root()
    {
        return _root;
    }
    
    ///////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    
    
    
    
    //------------------- getSuccessor( String ) ------------------------
    /**
     *  Return the Data for the node that is the immediate successor of
     *  of the node with this key.
     *  Ordering is based on the node keys. The immediate successor
     *  is  smallest key greater than this key (if it exists). 
     *  If a node with this key is not found or it has no successor then
     *  return null.
     * 
     * @param key String
     * @return Data
     */
    public Data getSuccessor( String key )
    {
        
        
        Node n = find ( _root , key );
        
        if ( n.right != null )
        {
            Node cur = n.right;
            while ( cur.left != null )
            {
                cur = cur.left;
            }
            
            return cur.data;
        }
        else 
        {
            Node cur = n;
            while ( cur.parent.left != cur )
            {
                if ( cur.parent == _root )
                    return null;
                cur = cur.parent;
            }
            return cur.parent.data;
        
        }
            
        
        
       
        // step 1 = check and see the right of specified node
        // step 2 = if 1.n.p != null, return 1.n.p, else...
        //... check to see if 1.n.p has p, so if 1.n.p.p != null return 1.n.p.p
        
        
        
        
        
        
        
        //return null;// not implemented     
    }
    
    
    //------------------- getParentData( String ) -------------------------
    /**
     * Return the Data in the parent of the node with this key.
     * If the key is not found or the node is the root then
     * return null
     * 
     * @param key String
     * @return Data
     */
    public Data getParentData( String key )
    {
        
        
        Node n = find( _root , key );
        
        if ( n == null || n == _root )
            return null;
            
        else
        
            return n.parent.data;
        
       
      
    }
    
    
    
    
    
    //-------------------- treeStats(  ) -------------------------
    /**
     * Returns a String of tree stats:
     * Stats inclue:
     *    Number of nodes
     *    Number of leaves
     *    Tree height
     *    Minimum tree height for a tree with this many nodes and
     *    maximum imbalance.
     * 
     *   Height and number of nodes are already implemented.
     * @return String
     */
    public String treeStats( )
    {
        double oth = ( Math.ceil( Math.log( size() + 1 ) 
            / Math.log( 2 ) ) - 1 );
        String statistics = "";
        statistics = statistics + "\n" + "Number of Nodes: " + size();
        statistics = statistics + "\n" + "Number of leaves: " 
            + getLeafCount( _root );
        statistics = statistics + "\n" + "Height: " + height();
        statistics = statistics + "\n" + "Optimal tree Height: "
             + Math.round( oth );
        statistics = statistics + "\n" + "Greatest Imbalance: " 
            + getImbalance( _root.left );
        
        
            
        
        
        
        
        
        return statistics; 
    }
    
    /**
    *getImbalance .
    *@return int
    *@param t Node
    */
    public int getImbalance( Node t )
    {
       
        if( t.left != null && t.right != null )
        {
            return getImbalance( t.left ) + getImbalance( t.right );

        }
            
        else
            return -1;
    }
    
    /**
    *gets the count of all leaves in tree .
    *@return int
    *@param n Node
    */
    
    
    public int getLeafCount( Node n )
    {
        if ( n == null )
            return 0;
        if ( n.left == null && n.right == null )
            return 1;
        else
            return getLeafCount( n.left ) + getLeafCount ( n.right );
        
    
    
    }
    
     //-------------------- preOrderList(  ) -------------------------
    /**
     * Returns an ArrayList of Data in pre-order order.
     * 
     * @return ArrayList<Data>
     */
    public ArrayList<Data>  preOrderList( )
    {
        ArrayList<Data> list = new ArrayList<Data>();
        preOrderTraverse( list , _root );
        return list;
    }
    
    /**
    *traverses in preorder order .
    *@param list ArrayList<Data>
    *@param n Node
    */
    
    public void preOrderTraverse( ArrayList<Data> list , Node n )
    {
        if ( n != null )
        {
            list.add ( n.data );
            preOrderTraverse ( list, n.left );
            preOrderTraverse ( list , n.right );
        }
        
        else
        {
            return;
        
        }
        
    }
    /**
    *find method, finds a node .
    *@return Node
    *@param n Node
    *@param key String
    */

        
    private Node find( Node n, String key )
    {
        
        if ( n == null )
        {
            return null;
        }
            
        
        if ( key.equals( n.data.key ) )
        {
            return n;
        }
        
        else if ( key.compareTo( n.data.key ) < 0 )
        {
            return find( n.left , key );
        } 
        
        else 
        {
            return find ( n.right , key );
        }
        
          
    }

        
            

    
    
    
    //-------------------- printLevel( ) ------------------------------------
    /**
     * 
     * Returns a String with keys  in level order.
     * 
     * @return String
     */
    public String printLevel( )
    {
        Queue<Node> queue = new LinkedList<>();
        ArrayList<String> stringList = new ArrayList<String>();
        String s = "";
        queue.add( _root );
        
        
        while( queue.peek() != null )
        {
            Node curNode = queue.remove();
            
            if ( curNode.left != null )
            {
                queue.add( curNode.left );
               
                
            }
            
            if ( curNode.right != null )
            {
                queue.add( curNode.right );
               
            }
            
            stringList.add( curNode.data.key );
            
            
            queue.remove( curNode );
        }
        
        for( String x : stringList )
        {
            s += x + ", ";
           
        }
        
        return s;
        
       
    }
    
    
    
    
    
    
    //------------------ printAllPaths(  ) ---------------
    /**
     * 
     * Returns a String with the path to each leaf.
     * a path starts with a "/" and is followed by a "/" separated 
     * list of all keys on the path from the root to the leaf.
     * 
     * @return String
     */
    public String  printAllPaths( )
    {
        ArrayList<String> list2 = new ArrayList<String>();
        printRootToLeaf( _root , list2 );
        
        return str;
    }
    
    /**
    * finds all paths from root to leaf and prints.
    * @return String
    * @param root Node
    * @param list ArrayList<String> 
    */
    
  
    public String printRootToLeaf( Node root , ArrayList<String> list )
    {
        
        
        if( root == null )
        {
            return null;
        }

        list.add( root.data.key );

        if( root.left == null && root.right == null )
        {
            str = str + "\n" + list;
            return str;
                
        }
        else
        {

            printRootToLeaf( root.left , new ArrayList<String> ( list ) );
            printRootToLeaf( root.right , new ArrayList<String> ( list ) );
        }
    
        return null;
        
    }         
    
    
    
    ///////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    
    
    //------------------ preOrderPrint( PrintStream ) ---------------
    /**
     * Traverse the tree in in-order fashion to print the nodes of the tree
     * to the PrintStream parameter.
     * This method uses the private utility method to print a subtree rooted
     * at a particular node.
     */
    public void preOrderPrint(  )
    {
        preOrderPrint( System.out, _root );
        System.out.println();
    }
    //------------------ preOrderPrint( PrintStream, node ) ---------------
    /**
     * Print the subtree rooted at "node" in in-order fashion.
     * 
     * @param out PrintStream
     * @param n Node
     */
    private void preOrderPrint( PrintStream out, Node n )
    {
        if ( n != null )
        {
            out.print( " " + n.data + " " );
            preOrderPrint( out, n.left );
            preOrderPrint( out, n.right );
        }
    }
    //--------------------- add -----------------------------------
    /**
     * add a node to the tree in its proper position determined by the
     * "key" field of the Data object. This method uses the addNode 
     * recursive utility method.
     * 
     * @param data Data
     */
    public void add( Data data )
    {
        Node newNode = new Node( data );
        if ( _root == null )
            _root = newNode;
        else
            addNode( _root, newNode );
        _size++;
    }
    
    //------------------ addNode( Node, Node ) -----------------------
    /**
     * a recursive method to add a new Node (2nd argument) to the subtree
     * rooted at the first argument.
     * 
     * @param parent Node
     * @param newOne Node
     */
    private void addNode( Node parent, Node newOne )
    {
        if ( newOne.data.compareTo( parent.data ) < 0 )
        {
            if ( parent.left != null )
                addNode( parent.left, newOne );
            else 
            {
                parent.left = newOne;
                newOne.parent = parent;
            }
        }
        else
        {
            if ( parent.right != null )
                addNode( parent.right, newOne );
            else 
            {
                parent.right = newOne;
                newOne.parent = parent;
            }
        }
    }
    
    
    //-------------------- height() ------------------------------------
    /**
     * return the height of the tree.
     * 
     * @return int
     */
    public int height()
    {
        return height( _root ) - 1;
    }
    
    //-------------------- height(Node) --------------------------------
    /**
     * return the height of the subtree rooted at node.
     * @return int
     * @param node Node
     */
    private int height( Node node )
    {
        if ( node == null ) 
            return 0;
        else 
        {
            int leftHeight = height( node.left );
            int rightHeight = height( node.right );
            if ( leftHeight > rightHeight )
                return leftHeight + 1;
            else
                return rightHeight + 1;
        }         
    }
    
    
    //-------------------------- size() -------------------------
    /**
     * return tree size.
     * @return int
     */
    public int size()
    {
        return _size;
    }
    //-------------------------- toString() -------------------------
    /**
     * Generate a string representation of the tree.
     * @return String
     */
    public String toString()
    {
        return toString( _root, "  ", "  " ) ;        
    }
    
    /**
     * recursively generate a string representation for a Node of a tree.
     * indent is increased for increasing depth.
     * branch is a short character string that prefixes each node indicating
     *        whether this node is a left (L) or right (R) child of its parent.
     * 
     * @return String
     * @param n Node
     * @param indent String
     * @param branch String
     */
    private String toString( Node n, String indent, String branch )
    {
        String s = "";
        if ( n != null )
        {
            String prefix = indent.substring( 0, indent.length() - 2 ) + branch;
            s += prefix + n.data.toString() + "\n";
            if ( n.left != null )
                s += toString( n.left, indent + "  ", "L " );
            if ( n.right != null )
                s += toString( n.right, indent + "  ", "R " );
        }
        return s;
    }
    //+++++++++++++++++++++++ inner class Node ++++++++++++++++++++++
    /**
     * The Node class does not have to be seen outside this class, so
     * it is private.
     */
    public class Node
    {
        //-------------- instance variables ---------------------------
        protected Data data;
        protected Node left;
        protected Node right;
        protected Node parent;
        
        //--------------- constructor --------------------------------
        /**
         * Constructor.
         *@param d Data
         */ 
        public Node( Data d )
        {
            data = d;
            left = null;
            right = null;
            parent = null;
        }
    }
    //--------------------- main -----------------------------------------
    /**
     * Start the app.
     * 
     * @param args String[]
     * 
     */ 
    public static void main( String[] args )
    {
        ProgramBST app = new ProgramBST( "ProgramBST", args );
    }
}


