/**
 * TreeApp - the main class for controlling the application code. 
 * 
 * 
 * The application semantics including the graphics generated 
 * by the app are controlled from this class.
 */
import javax.swing.*;
import java.util.*;

public class TreeApp
{
    //--------------------- instance variables ----------------------------
    private DataList _list;
    private BinarySearchTree _bst = null;
    private DisplayPanel     _display;
    
    //------ "package" state variables; set by GUI ----------
    boolean printMode     = true;  // if set, print after each command
    
    //--- data generation constants with package access so GUI can change
    int     dataSize        = 15;
    int     minDataSize     = 0;
    int     maxDataSize     = 20;
    int     defaultDataSize = 8;
    int     minNodeWidth    = 1;
    int     maxNodeWidth    = 200;
    int     defaultNodeWidth = 35;
    
    int     rngSeed          = 2;   // random seed
    int     maxSeed          = 16;   // arbitrary number
    
    //--- subset constants
    private int     subsetMin       = 30;   // arbitrary; all values are 0, 100
    private int     subsetMax       = 70;
    
    //---------------------- constructor ----------------------------------
    /**
     * The app needs the display reference only so it can update the 
     * DisplayListPanel with new Lists when needed and tell it to redraw
     * when things change.
     */
    public TreeApp( DisplayPanel display )
    {
        _display = display;
        _display.setNodeWidth( defaultNodeWidth );
        makeData();
    }
    //---------------------- update() -----------------------------
    /**
     * Something in gui has change; update whatever needs to be updated
     * The GUI calls the app when a GUI update occurs; the app (in this
     * case) only needs to pass this along to the DisplayListPanel.
     */
    public void update()
    { 
        _display.setTree( _bst );     
    }
    //----------------------  setNodeWidth( int ) -------------------
    public void setNodeWidth( int newW )
    {
        if ( newW > 0 )
            _display.setNodeWidth( newW );
    }
    //+++++++++++++++++++++ methods invoked by GUI buttons +++++++++++++++++++
    //---------------------- makeData() -----------------------------
    /**
     * generate a new DataList 
     */
    public void makeData( )
    {  
        _list = generateData( this.dataSize, this.rngSeed );
        
        _bst = new BinarySearchTree();
        
        Iterator<Data> iter = _list.iterator();
        while ( iter.hasNext() )
            _bst.add( iter.next() );
        
        update();
    }
    
    //---------------------- printAllpaths -----------------------------
    /**
     *  
     */
    public void printAllPaths( )
    {  
        show(  "Print All Paths", _bst.printAllPaths() );   
        
    }
    
    
    private void show( String label, String data )
    {
        System.out.println( "\n" + label + ":\n" + data );
        JOptionPane.showMessageDialog( null,data,label, JOptionPane.PLAIN_MESSAGE);
    }
    
    //---------------------------------------------------
    /**
     * print the tree stats 
     */
    public void treeStats( )
    {  
        show( "Tree Statistics" , _bst.treeStats()) ;
    }
    //---------------------- -----------------------------
    /**
     * get the data in the parent
     */
    public void getParentData(  )
    {  
        
        String key;
        key = JOptionPane.showInputDialog( null, "Enter key to search for parent Data" );
        if ( key != null && key.length() > 0 )
        {         
            
            Data data = _bst.getParentData( key );
            
        
            if( data == null )
                show(  "Parent Data for Node: " + key, "That node is the root or dosn't exist" );
            else
                show(  "Parent Data for Node: " + key, data.toString() );
        }    
    }
    //----------------------sucessor-----------------------------
    /**
     * 
     */
    public void successor( )
    {  
        
        String key;
        key = JOptionPane.showInputDialog( null, "Enter key to search for immediate sucessor" );
        if ( key != null && key.length() > 0 )
        {         
            
            Data data = _bst.getSuccessor( key );
            
            if( data == null )
               show( "Successor of node: " + key  , "That node does not exist or has no successor" );
            else
              
                show( "Successor of node: " + key  ,   data.toString() );
            
        }
    }
    
    
    //----------------------- stringToInt( String ) -----------------------------
    /**
     * Convert string to integer
     */
    private int stringToInt( String str, int defaultVal )
    {
        try 
        {
            return Integer.parseInt( str );
        }
        catch ( NumberFormatException nfe )
        {
            String input = JOptionPane.showInputDialog( null, 
                                                       "Invalid integer input: " + str + ". No change." );
            return defaultVal;
        }
    }
    
    //---------------------------------------------------
    /**
     * print in level order
     */
    public void printLevel( )
    { 
        show( "Print Level Order" , _bst.printLevel() );
    }
    
    
       //---------------------------------------------------
    /**
     * print in level order
     */
    public void balance( )
    { 
         ArrayList<Data> list  = _bst.preOrderList();
        
         if( list != null )
             show( "Pre-order List", list.toString() );
         else
              show( "Pre-order List", "List is null" );
         
    }
    
    //------------------- printTree( _bst, String ) ---------------------------
    private void printTree( BinarySearchTree _bst, String title )
    {
        String dashes = " ------------------------ ";
        System.out.println( "\n" + dashes + title + dashes );
        if ( _bst == null || _bst.size() == 0 )
            System.out.println( "---Empty---" );
        else
        {
            System.out.println( _bst );
            System.out.println( "Tree has " + _bst.size() + " nodes." );
        }
    }
    
    //------------------- generateData -----------------------------------
    /**
     * arguments are 
     *    numItems -- number items to generate
     *    seed     -- random number seed; -1 means let system pick it.
     */
    private DataList generateData( int numItems, int seed )
    {
        String[] names = { "", "", "", "", "", "", "", 
            "", "", "", "", "" };
        DataList dl = new DataList();
        Random rng;
        if ( seed < 0 )
            rng = new Random();
        else 
            rng = new Random( seed );
        
        String        letters = "abcdefghijklmnopqrstuvwxyz";
        StringBuffer  keybuf  = new StringBuffer( "12" );
        
        while ( dl.size() < numItems )
        {
            // generate a key
            char letter1 = letters.charAt( rng.nextInt( letters.length() ));
            char letter2 = letters.charAt( rng.nextInt( letters.length() ));
            
            keybuf.setCharAt( 0, letter1 );
            keybuf.setCharAt( 1, letter2 );         
            String key = keybuf.toString();
            
            Data check = dl.find( key );
            if ( check == null )
            {            
                // generate a name field; these are allowed to duplicate
                int    nameIndex = rng.nextInt( names.length );
                String name = names[ nameIndex ];
                
                // generate a value from 0 to 99
                int    val = rng.nextInt( 100 );
                dl.add( new Data( key, name, val ));
            }
        }
        return dl;
    }
}
