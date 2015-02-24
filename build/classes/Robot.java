
import java.util.ArrayList;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 *
 * Name: David Krawchuk
 * Course: CSCI 242 - Computer Science II
 * Section: 001
 * Assignment: 3
 *
 * Project/Class Description:
 * The Robot class is the super class of several robots. It is not
 * instantiated directly, but used to make instances of its subclasses.
 * 
 * 
 *
 * Known bugs:
 * (write the word none, or describe)
 */
public abstract class Robot 
{
    //Variable holds copy of maze object.
    private Maze maze;
    //Array holds current location of robot object.
    private int[] currLoc = {0,0};
    //ArrayList holds positions visited by the robot.
    private ArrayList locations = new ArrayList();
    
/**
 * Constructs a Robot and sets a reference to the starting location
 * of the robot in the maze.
 * @param maze 
 */    
    public Robot(Maze maze)
    {
        this.maze = maze;
        
        //Set reference location.
        currLoc[0] = maze.getStartRow();
        currLoc[1] = maze.getStartCol();
        
    }
    
/**
 * Overridden method for subclasses. Called by the main method
 * to move the robot.
 */    
    public abstract void move();
    
/**
 * Tests weather the robot's current position is the same as the
 * exit cell of the maze.
 * If so, the method returns true, otherwise it returns false.
 * 
 * @return 
 */    
    public boolean done()
    {
        if(currLoc[0] == maze.getExitRow())
            if(currLoc[1] == maze.getExitCol())
                return true;
        
        return false;
    }
    
/**
 * This method obtains a copy of the maze array, to which the 
 * current cell location of the robot is filled by the 'r' character.
 * After which the copy is printed to the screen to be vied by the user.
 */    
    public void print()
    {
        //Get copy of maze.
        char[][] arrayCopy = maze.toArray();
        //Overwrite current location with character r.
        arrayCopy[currLoc[0]][currLoc[1]] = 'r'; 
        
        //Print Maze with location of Robot.
        for(int i = 0; i < maze.getRows(); i++)
        {
            //Print new line following each loop.
            System.out.println("");
            //Print each row of characters.
            for(int j = 0; j < maze.getCols(); j++)
            {
                System.out.print(arrayCopy[i][j]);
            }
        }
    }
    
/*
 * This method is called to print all the stored moves made by the robot
 * durring its travel thru the maze.
 */    
    public void printAllMoves()
    {
        System.out.println("\nMovements performed by Robot: ");
        for (int i = 0; i < locations.size() - 1; i++)
        {
            System.out.println("(" + locations.get(i) + ", " + 
                    locations.get(i + 1) + ")");
            
        }
    }
    
/**
 * This method takes two parameters: the location of the row and cell
 * that the robot has moved to. Is only called by the move method within 
 * the robot subclasses.
 * @param row
 * @param col 
 */    
    public void addMove(int row, int col)
    {
        locations.add(row);
        locations.add(col);
        
    }
    
/**
 * This method returns the current row of the location on the maze
 * that the robot currently occupies.
 * @return 
 */    
    public int getCurRowLoc()
    {
        return currLoc[0];
    }
    
/**
 * This method returns the current col of the location of the maze
 * that the robot currently occupies.
 * @return 
 */    
    public int getCurColLoc()
    {
        return currLoc[1];
    }

/**
 * This method simply sets the current row of the robot in the maze.
 * @param row 
 */    
    public void setCurRowLoc(int row)
    {
        currLoc[0] = row;
    }
    
/**
 * This method simply sets the current column of the robot in the maze.
 * @param col 
 */    
    public void setCurColLoc(int col)
    {
        currLoc[1] = col;
    }
    
    /**
     * Allows setting of the maze from outside the 
     * robot constructor.
     * 
     * @param maze
     */
    public void setMaze(Maze maze)
    {
        this.maze = maze;
        
        //Set reference location.
        currLoc[0] = maze.getStartRow();
        currLoc[1] = maze.getStartCol();
    }
}