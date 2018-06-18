public class Human extends Animal
{
    public Human()
    {
        this.setStrength(5);
        this.setInitiative(4);
        this.setSpecies(SPECIES.HUMAN);
        setId();
    }
    
    @Override
    public void action()
    {
        //TODO
    }
    
    @Override
    public void collision(World world, int approached_column, int approached_row)
    {
        //TODO
    }
}
