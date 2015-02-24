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
 * A RandomRobot determines which of the four directions it can legally 
 * move in, and then chooses one of them at random. Note that a 
 * RandomRobot may move back to where it came from.
 *
 * Known bugs:
 * (Seems to have the inherent ability to get stuck in a very very long loop
 *  of movements. This may just be a result of the fact that locations of dead
 *  ends are not accounted for.)
 * 
 */
public class RandomRobot extends Robot
{
    //Direction possibilites.
    private boolean north;
    private boolean south;
    private boolean west;
    private boolean east;
    
    //Reference variable.
    private Maze maze;
    
    //Default constuctor
    public RandomRobot(Maze maze)
    {
        super(maze);
        this.maze = maze;
    }

/**
 * move() calls findPossDir() to find open possitions to move. When found 
 * feeds the results of the randomDirChooser() to pick if a random open
 * position. At which point the switch method moves the bot and records
 * the movement to the movements arraylist.
 */
    @Override
    public void move() 
    {
        //Find possible directions to move.
        findPossDir();
        
        //Make move based on returned character from randomDirChooser().
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
        
        //Add move to arraylist, located in Robot Class.
        addMove(getCurRowLoc(), getCurColLoc());
        
    }

/**
 * findPossDir() searches possible directions and sets direction values
 * to true if possible, and false otherwise.
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
    
    
}
