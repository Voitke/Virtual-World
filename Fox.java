import java.util.Random;

public class Fox extends Animal
{
    public Fox()
    {
        this.setStrength(3);
        this.setInitiative(7);
        this.setSpecies(SPECIES.FOX);
        setId();
    }
    
    @Override
    public void action()
    {
        Random randomizer = new Random();
    
        int row_offset = randomizer.nextInt(3) - 1; // randomizes row change offset from -1 or 0 or 1
        int column_offset = randomizer.nextInt(3) - 1; // similar
        if ( world.board[this.coords.getColumn() + column_offset][this.coords.getRow() + row_offset] == null )
        {
            this.world.board[this.coords.getColumn() + column_offset][this.coords.getRow() + row_offset] = this; //move Animal to new location
            this.coords.setColumn(this.coords.getColumn() + column_offset);
            this.coords.setRow(this.coords.getRow() + row_offset);
            this.world.board[this.coords.getColumn()][this.coords.getRow()] = null; //delete it from previous location
        }
        else
        {
            if(world.board[this.coords.getColumn() + column_offset][this.coords.getRow() + row_offset].getStrength() <= this.getStrength())
            {
                collision(world, this.coords.getColumn() + column_offset, this.coords.getRow() + row_offset);
            }
        }
    }
}
