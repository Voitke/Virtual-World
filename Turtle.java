import java.util.Random;

public class Turtle extends Animal
{
    public Turtle()
    {
        this.setStrength(2);
        this.setInitiative(1);
        this.setSpecies(SPECIES.TURTLE);
        setId();
    }
    
    @Override
    public void action()
    {
        Random randomizer = new Random();
        int choice = randomizer.nextInt(4);
        if(choice == 0) super.action();
    }
}
