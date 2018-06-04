package view;

import showboard.IPawn;
import showboard.ISquare;

/**
 * <h1>The Interface IView.</h1>
 *
 * @author Hugo
 * @version 1.0
 */
public interface IView {
    
   /**
    * remove the pawns
    */
   public void removePawns();

   /**
    * remove a pawn 
    * @param pawn
    */
   public void removePawn(IPawn pawn);
   
   /**
    * add a Pawn
    * @param pawn
    */
   public void addPawn(IPawn pawn);
   
   /**
    * add a square
    * @param square
    */
   public void addSquare(ISquare square, int x, int y);
   
   /**
    * add a square
    * @param square
    */
   public void removeSquare(int x, int y);

   /**
    * Display a message
    * @param message
    */
   public void displayMessage(String message);
   
   /**
    * Get interact
    * @return
    */
   public IInteract getInteract();
    
}
