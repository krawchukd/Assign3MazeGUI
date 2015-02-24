/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 *
 * Name: David Krawchuk (krawc004)
 * Course: CSCI 242 - Computer Science II
 * Section: 001
 * Assignment: 3
 *
 * Project/Class Description:
 * This is a "wrapper" class around a 2D array that contains the maze
 * character data. 
 *
 * Known bugs:
 * (write the word none, or describe)
 */
public class Maze
{
    //number of rows and columns of maze.
    private int rows = 0;
    private int col = 0;
    
    //location of rows and columns of Start maze.
    private int startRows = 0;
    private int startCols = 0;
    
    //location of rows and columns of Exit maze.
    private int exitRows = 0;
    private int exitCols = 0;
    
    //Two dimention array representing the maze.
    private char[][] template;
    
/**
 * Maze() is a constructor that takes a string name of a file, and passes
 * that string to the method readData(). readData is a private method, used
 * only by the constructor to set the objects variables.
 * @param filename
 * @throws IOException 
 */    
    public Maze (String filename) 
    {
        try
        {
        //Call readData to set instance variables.
        readData(filename);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Please choose a ****.txt file with an"
                + " appropreate maze.\n"
                + "Or close the filechooser and exit the program.",
                "Error!!", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
/**
 * getRows() returns the number of rows in the maze.
 * @return 
 */    
    public int getRows()
    {
        return this.rows;
    }
    
/**
 * getCols() returns the number of columns in the maze.
 * @return 
 */    
    public int getCols()
    {
        return this.col;
    }
    
/**
 * getStartRow() returns the start cell's row.
 * @return 
 */    
    public int getStartRow()
    {
       return this.startRows; 
    }
    
/**
 * getStartCol() returns the starting column.
 * 
 * @return 
 */    
    public int getStartCol()
    {
        return this.startCols;
    }
    
/**
 * getExitRow() returns the exit cell's row.
 * @return 
 */    
    public int getExitRow()
    {
        return this.exitRows;
        
    }
    
/**
 * getExitCol() returns the exit cell's column.
 * @return 
 */    
    public int getExitCol()
    {
        return this.exitCols;
        
    }
    
/**
 * getCell() returns the character stored at this cell.
 * @param row
 * @param col
 * @return 
 */    
    public char getCell(int row, int col)
    {
        return this.template[row][col];
    }
    
/**
 * setCell() sets the value stored in this cell. This method is included
 * for use with the MemoryRobot class. Reports an error to the screen for 
 * error tracing if an out of bounds location is chosen.
 * @param row
 * @param col
 * @param newCh 
 */    
    public void setCell(int row, int col, char newCh)
    {
        if (row < 0 || row > this.rows)
        {
            System.out.println("ERROR: attempted to set cell out of"
                    + "bounds. No action performed!");
            return;
        }
        
        this.template[row][col] = newCh;
    }
    
    
/**
 * toArray() returns a copy of the maze as an array of characters.
 * note that the array makes a copy of the object array before passing.
 * 
 * @return 
 */    
    public char [][] toArray()
    {
        //Create array to return.
        char[][] retArray = new char[this.rows][this.col];
        
        //Using for loop to copy values from object to return array.
        //Outer loop controls rows. Inner loop controls column assignments.
        for(int i = 0; i < this.rows; i++)
        {
            for(int j = 0; j < this.col; j++)
            {
                retArray[i][j] = this.template[i][j];
            }
        }
        return retArray;
    }
    
    
/**
 * readData() is a private method used by the constructor to store
 * data about the maze in the object's variables.
 * 
 * @param filename 
 */
    private void readData(String filename) throws IOException
    {
        //Creat a file object to read data from.
        java.io.File file = new java.io.File(filename);
        
        //Create a Scanner for the file.
        Scanner input = new Scanner(file);
        
        //Read from the file.
        {
            //read rows and columns.
            this.rows = input.nextInt();
            this.col = input.nextInt();
            
            //Read row and column location of the Start cell.
            this.startRows = input.nextInt();
            this.startCols = input.nextInt();
            
            //Read row and column location of the Exit cell.
            this.exitRows = input.nextInt();
            this.exitCols = input.nextInt();
            
            //Read each row of characters in the maze into the array.
            this.template = new char[rows][col];    
            
            //Return to next line to begin reading maze characters.
            input.nextLine();
            
            //For each itteration of the outer loop a line is read
            //from the txt file. The inner loop parses the characters
            //into the template array.
            for (int i = 0; i < rows; i++)
            {
               
                String temp = input.nextLine();
                
                for(int j = 0; j < col; j++)
                {
                    this.template[i][j] = temp.charAt(j);
                }
            }
        }
        //close file.
        input.close();
    }
    
}
