/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 *
 * Name: David Krawchuk (krawc004)
 * Course: CSCI 242 - Computer Science II
 * Section: 001
 * Assignment: 3
 *
 * Project/Class Description:
 * A LeftHandRobot has both a location and a direction 
 * (which way it is facing). When initially constructed, we will 
 * assume the robot is facing South. After that, its direction is
 * determined by its previous move. A LeftHandRobot’s goal is to
 * search for the exit while always dragging its right hand along 
 * a wall. If there is no right hand wall, it turns right.
 *
 * Known bugs:
 * (write the word none, or describe)
 */
public class MemoryRobotClass extends Robot 
{
    
    /*
     * Direction possibilities, set true when an open location
     * is present.
     */
    private boolean north;
    private boolean south;
    private boolean west;
    private boolean east;
    
    //Reference variable.
    private Maze maze;
    
/**
 * Constructor calls parent constructor from Robot to create object and 
 * creates a shallow copy of the maze object.
 * @param maze 
 */
    public MemoryRobotClass(Maze maze)
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
        
        deadEndFinder();
        
        /*
         * Make move based on returned character from dirChooser() and
         * records the last direction traveled.
         * 
        */
        switch (randomDirChooser())
        {
            case 'N': setCurRowLoc(getCurRowLoc() - 1);
                      break;
                
            case 'S': setCurRowLoc(getCurRowLoc() + 1);
                      break;
                
            case 'E': setCurColLoc(getCurColLoc() + 1);
                      break;
                
            case 'W': setCurColLoc(getCurColLoc() - 1);
                      break;
        }
        
        
    }

/**
 * findPossDir() searches possible directions and sets direction values
 * to true if possible, tests for the finish character, and false otherwise.
 */    
    public void findPossDir()
    {
        //Check possible moves North and set value true if empty, or at the 
        //finish, and false otherwise.
        
        if(getCurRowLoc() > 0)
        {
            north = (maze.getCell(getCurRowLoc() - 1, getCurColLoc()) == ' ') 
                    || (maze.getCell(getCurRowLoc() - 1, getCurColLoc()) == 'X');
        }
        
        //Check possible moves South
        if(getCurRowLoc() < maze.getRows())
        {
            south = (maze.getCell(getCurRowLoc() + 1, getCurColLoc()) == ' ') 
                    || (maze.getCell(getCurRowLoc() + 1, getCurColLoc()) == 'X');
        }
    
        //Check possible moves East
        if(getCurColLoc() > 0)
        {
            east = (maze.getCell(getCurRowLoc(), getCurColLoc() + 1) == ' ') 
                    || (maze.getCell(getCurRowLoc(), getCurColLoc() + 1) == 'X');
        }
        
        //Check possible moves West
        if(getCurColLoc() < maze.getCols())
        {
            west = (maze.getCell(getCurRowLoc(), getCurColLoc() - 1) == ' ') 
                    || (maze.getCell(getCurRowLoc(), getCurColLoc() - 1) == 'X');
        }
        
    }
    
    
 /**
 * randomDirChooser() creates a random value and uses that value to 
 * choose the proper direction to move. When those values are within a 
 * directions range of values the direction is checked for the possibility
 * of movement. If that directions boolean value is set to true the
 * character value is returned to the calling method.
 * 
 * north = 1 - 25
 * south = 26 - 50
 * east =  51 - 75
 * west = 76 - 100
 * @return 
 */    
    public char randomDirChooser()
    {
        //Find open cells
        findPossDir();

        while(true)
        {
            int compare = (int)(Math.random() * 4) + 1;
            
            if(compare >= 0 && compare <= 1)
            {
                if(north)
                    return 'N';
            }
            
            if(compare > 1 && compare <= 2)
            {
                if(south)
                    return 'S';
            }
            
            if(compare > 2 && compare <= 3) 
            {
                if(east)
                    return 'E';
            }
            
            if(compare > 3 && compare <= 4) 
            {
                if(west)
                    return 'W';
            }
            
        }
    }
            
            /*
             * Following series of if statements determines if robot is 
             * surrounded by filled cells. If the dead end is found, 
             * 'F' is returned to the move method and the cell is filled.
             */
        
        
        public void deadEndFinder()
        {
            if((!west) && (!north) && (!south))
            {
                maze.setCell(getCurRowLoc(), getCurColLoc(), 'B');
            }
            
            if((!east) && (!north) && (!west))
            {
                maze.setCell(getCurRowLoc(), getCurColLoc(), 'B');
            }
            
            if((!north) && (!east) && (!south))
            {
                maze.setCell(getCurRowLoc(), getCurColLoc(), 'B');
            }
            
            if((!east) && (!west) && (!south))
            {
                maze.setCell(getCurRowLoc(), getCurColLoc(), 'B');
            }
                        
        }
        
}
