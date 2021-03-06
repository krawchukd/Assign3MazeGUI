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
 * A RightHandRobot has both a location and a direction 
 * (which way it is facing). When initially constructed, we will 
 * assume the robot is facing South. After that, its direction is
 * determined by its previous move. A RightHandRobot’s goal is to
 * search for the exit while always dragging its right hand along 
 * a wall. If there is no right hand wall, it turns right.
 *
 * Known bugs:
 * (write the word none, or describe)
 */
public class RightHandRobot extends Robot 
{
    /*
     * Constants that hold direction values. Its sole purpose
     * if for readability only!
     */
    public final int NORTHDIR = 1;
    public final int EASTDIR = 2;
    public final int SOUTHDIR = 3;
    public final int WESTDIR = 4;
    
    /*
     * Direction possibilities, set true when an open location
     * is present.
     */
    private boolean north;
    private boolean south;
    private boolean west;
    private boolean east;
    
    /*Last direction traveled. North = 1, East = 2, South = 3, 
     * West = 4. Set South by default.
    */
    private int lastDirection = SOUTHDIR;
    
    //Reference variable.
    private Maze maze;
    
/**
 * Constructor calls parent constructor from Robot to create object and 
 * creates a shallow copy of the maze object.
 * @param maze 
 */
    public RightHandRobot(Maze maze)
    {
        super(maze);
        this.maze = maze;
    }

/**
 * move() calls findPossDir() to find open positions to move. When found 
 * feeds the results of the dirChooser method to pick open
 * positions. At which point the switch method moves the bot and records
 * the movement to the movements ArrayList.
 */
    @Override
    public void move() 
    {
        //Find possible directions to move.
        findPossDir();
        
        /*
         * Make move based on returned character from dirChooser() and
         * records the last direction traveled.
         * 
        */
        switch (dirChooser())
        {
            case 'N': setCurRowLoc(getCurRowLoc() - 1);
                      lastDirection = NORTHDIR;
                      break;
                
            case 'S': setCurRowLoc(getCurRowLoc() + 1);
                      lastDirection = SOUTHDIR;
                      break;
                
            case 'E': setCurColLoc(getCurColLoc() + 1);
                      lastDirection = EASTDIR;
                      break;
                
            case 'W': setCurColLoc(getCurColLoc() - 1);
                      lastDirection = WESTDIR;
                      break;
        }
        
        //Add move to arraylist, located in Robot Class.
        addMove(getCurRowLoc(), getCurColLoc());
        
    }

/**
 * findPossDir() searches possible directions and sets direction values
 * to true if possible, tests for the finish character, and false otherwise.
 */    
    public void findPossDir()
    {
        //Check possible moves North and set value true if empty, false otherwise.
        if(getCurRowLoc() > 0)
        {
            north = ((maze.getCell(getCurRowLoc() - 1, getCurColLoc()) == ' ') 
                    || (maze.getCell(getCurRowLoc() - 1, getCurColLoc()) == 'X'));
        }
        
        //Check possible moves South
        if(getCurRowLoc() < maze.getRows())
        {
            south = ((maze.getCell(getCurRowLoc() + 1, getCurColLoc()) == ' ') 
                    || (maze.getCell(getCurRowLoc() + 1, getCurColLoc()) == 'X'));
        }
    
        //Check possible moves East
        if(getCurColLoc() > 0)
        {
            east = (maze.getCell(getCurRowLoc(), getCurColLoc() + 1) == ' ' 
                    || (maze.getCell(getCurRowLoc(), getCurColLoc() + 1) == 'X'));
        }
        
        //Check possible moves West
        if(getCurColLoc() < maze.getCols())
        {
            west = ((maze.getCell(getCurRowLoc(), getCurColLoc() - 1) == ' ') 
                    || (maze.getCell(getCurRowLoc(), getCurColLoc() - 1) == 'X'));
        }
        
    }

/*
 * This method uses the last known direction taken and determines open
 * locations to move to. After which it returns a character value to 
 * the move method for further action.
 */
    public char dirChooser()
    {
            while(true)
            {
                if(lastDirection == NORTHDIR)
                {
                    if((!east) && (north))
                        return 'N';
                    
                    if((!east) && (!north) && (west))
                        return 'W';
                    
                    if((!north) && (!east) && (!west))
                        return 'S';
                    
                    if(east)
                        return 'E';
                }
                
                if(lastDirection == EASTDIR)
                {
                    if((!south) && (east))
                        return 'E';
                    
                    if((!east) && (!south) && (north))
                        return 'N';
                    
                    if((!east) && (!south) && (!north))
                        return 'W';
                    
                    if(south)
                        return 'S';
                }
                
                if(lastDirection == SOUTHDIR)
                {
                    if((!west) && (south))
                        return 'S';
                    
                    if((!south) && (!west) && (east))
                        return 'E';
                    
                    if((!south) && (!west) && (!east))
                        return 'N';
                    
                    if(west)
                        return 'W';
                }

                if(lastDirection == WESTDIR)
                {
                    if((!north) && (west))
                        return 'W';
                    
                    if((!north) && (!west) && (south))
                        return 'S';
                    
                    if((!west) && (!north) && (!south))
                        return 'E';
                    
                    if(north)
                        return 'N';
                }
                

            }
        
    }
    
}
