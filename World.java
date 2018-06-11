import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;

import java.io.IOException;
import java.util.Random;

public class World
{
    private static int rows = 20;
    
    private static int columns = 20;
    
    Organism[][] board;
    
    OrganismContainer organism_container;
    
    public World()
    {
        
        try
        {
            System.out.println("Input number of rows: ");
            this.rows = System.in.read();
            
            System.out.println("Input number of columns: ");
            this.columns = System.in.read();
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }
        
        this.board = new Organism[columns][rows];
        
        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < columns; j++)
            {
                board[j][i] = null;
            }
        }
        
        organism_container = new OrganismContainer();
    
        Random randomizer = new Random();
        for(int i = 0; i < 20; i++)
        {
            int row = randomizer.nextInt(this.rows);
            int column = randomizer.nextInt(this.columns);
            int species = randomizer.nextInt(9) + 1;
            switch (species)
            {
                case 1:
                    board[column][row] = new Wolf();
                    break;
                case 2:
                    board[column][row] = new Sheep();
                    break;
                case 3:
                    board[column][row] = new Fox();
                    break;
                case 4:
                    board[column][row] = new Turtle();
                    break;
                case 5:
                    board[column][row] = new Antilope();
                    break;
                case 6:
                    board[column][row] = new Grass();
                    break;
                case 7:
                    board[column][row] = new Dandillion();
                    break;
                case 8:
                    board[column][row] = new Guarana();
                    break;
                case 9:
                    board[column][row] = new Belladonna();
                    break;
                
            }
            organism_container.add(board[column][row]);
        }
    }
    
    public void executeTurn()
    {
        for(Organism org : organism_container.existing_organisms )
        {
            org.action();
            org.incrementAge();
        }

        
    }
    
    public void drawWorld()
    {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < columns; j++)
            {
                board[j][i].draw();
            }
        }
    }
}
