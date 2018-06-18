import java.io.IOException;

public class Main
{
    public static void main(String args[])
    {
        World world = new World();
        char choice = ' ';
        while(choice != 'q')
        {
            world.executeTurn();
            world.drawWorld();
            try
            {
                choice = (char)System.in.read();
    
            }catch (IOException ex) { }
        }
        System.out.println("Heelo");
    }
}
