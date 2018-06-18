import java.util.Random;

public class Antilope extends Animal
{
    public Antilope()
    {
        this.setStrength(4);
        this.setInitiative(4);
        this.setSpecies(SPECIES.ANTILOPE);
        setId();
    }
    
    @Override
    public void action()
    {
        Random randomizer = new Random();
    
        int row_offset = randomizer.nextInt(5) - 2; // randomizes row change offset from -1 or 0 or 1
        int column_offset = randomizer.nextInt(5) - 2; // similar
        if ( world.board[this.coords.getColumn() + column_offset][this.coords.getRow() + row_offset] == null )
        {
            this.world.board[this.coords.getColumn() + column_offset][this.coords.getRow() + row_offset] = this; //move Animal to new location
            this.coords.setColumn(this.coords.getColumn() + column_offset);
            this.coords.setRow(this.coords.getRow() + row_offset);
            this.world.board[this.coords.getColumn()][this.coords.getRow()] = null; //delete it from previous location
        }
        else
        {
            collision(world, this.coords.getColumn() + column_offset, this.coords.getRow() + row_offset);
        }
    }
    
    @Override
    public void collision(World world, int approached_column, int approached_row)
    {
        
        Random randomizer = new Random();
        int chance = randomizer.nextInt(2);
        if(chance == 0) // ucieczka
        {
            int row_offset = randomizer.nextInt(3) - 1; // randomizes row change offset from -1 or 0 or 1
            int column_offset = randomizer.nextInt(3) - 1; // similar
            world.board[approached_column + column_offset][approached_row + row_offset] = this;
            this.world.board[this.coords.getColumn()][this.coords.getRow()] = null;
            this.coords.setColumn(approached_column + column_offset);
            this.coords.setRow(approached_row + row_offset);
        }
        
    }
}
