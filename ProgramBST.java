/**
 * ProgramBST -- Trees
 * 
 */
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;


public class ProgramBST extends JFrame
{
   //---------------------- instance variables ----------------------   
   //--------------------------- constructor -----------------------
   public ProgramBST( String title, String[] args ) 
   {      
      super( title );
      GUI          gui     = new GUI( this );
      
      add( gui );
      this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
      
      this.pack();
      this.setSize(  new Dimension( gui.getWidth() + 300, gui.getHeight() + 100 ));
      this.setVisible( true );
   }
   //--------------------- main -----------------------------------------
   public static void main( String[] args )
   {
      ProgramBST app = new ProgramBST( "ProgramBst", args );
   }
}