import java.util.LinkedList;

public class OrganismContainer
{
    protected LinkedList<Organism> existing_organisms = new LinkedList<Organism>();
    
    public void add(Organism organism)
    {
        existing_organisms.add(organism);
    }
    
    public void remove(int id)
    {
        existing_organisms.remove(id);
    }
}
