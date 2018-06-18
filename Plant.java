import java.util.Random;

public abstract class Plant extends Organism
{
    @Override
    public void action()
    {
        int chance;
        Random randomizer = new Random();
        chance = randomizer.nextInt(20);
        if(chance == 0)
        {
            int child_row_offset;
            int child_column_offset;
            int parent_row = this.coords.getRow();
            int parent_column = this.coords.getColumn();
            try
            {
                int tries = 0;
                do // randomizing position until finding a not taken spot
                {
                    child_row_offset = randomizer.nextInt(3) - 1; // randomizes row change offset from -1 or 0 or 1
                    child_column_offset = randomizer.nextInt(3) - 1; // similar
                    tries++;
                }
                while(world.board[parent_column + child_column_offset][child_row_offset] != null && tries < 7);
                if (tries == 7) throw new OverpopulationException();
                switch(this.getSpecies())
                {
                    case GRASS:
                        world.board[parent_column + child_column_offset][parent_row + child_row_offset] = new Grass();
                        break;
                    case DANDILLION:
                        world.board[parent_column + child_column_offset][parent_row + child_row_offset] = new Dandillion();
                        break;
                    case GUARANA:
                        world.board[parent_column + child_column_offset][parent_row + child_row_offset] = new Guarana();
                        break;
                    case BELLADONNA:
                        world.board[parent_column + child_column_offset][parent_row + child_row_offset] = new Belladonna();
                        break;
                }
                world.organism_container.add(world.board[parent_column + child_column_offset][parent_row + child_row_offset]);
        
            }
            catch (OverpopulationException oEx)
            {
                System.out.println("Area is overpopulated. Cannot reproduce properly.");
            }
        }
        
    }
}
