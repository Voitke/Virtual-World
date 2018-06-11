import java.util.Random;

public abstract class Animal extends Organism {
    
    private int initiative;
    
    public int getInitiative() {
        return initiative;
    }
    
    public void setInitiative(int initiative) {
        this.initiative = initiative;
    }
    
    @Override
    public void action() { // TODO: solve borderline problem - Animal checks all 8 surroundings even if he has only 3 available
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
            collision(world, this.coords.getColumn() + column_offset, this.coords.getRow() + row_offset);
        }
        
    }
    
    public void collision(World world, int approached_column, int approached_row) {
        
        Organism approached = world.board[approached_column][approached_row];
        
        if(this.getSpecies() == approached.getSpecies()) // reproducing
        {
            Random randomizer = new Random();
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
                    case WOLF:
                        world.board[parent_column + child_column_offset][parent_row + child_row_offset] = new Wolf();
                        break;
                    case SHEEP:
                        world.board[parent_column + child_column_offset][parent_row + child_row_offset] = new Sheep();
                        break;
                    case FOX:
                        world.board[parent_column + child_column_offset][parent_row + child_row_offset] = new Fox();
                        break;
                    case TURTLE:
                        world.board[parent_column + child_column_offset][parent_row + child_row_offset] = new Turtle();
                        break;
                    case ANTILOPE:
                        world.board[parent_column + child_column_offset][parent_row + child_row_offset] = new Antilope();
                        break;
                }
                
                world.organism_container.add(world.board[parent_column + child_column_offset][parent_row + child_row_offset]);
                
            }
            catch (OverpopulationException oEx)
            {
                oEx.displayMessage();
            }
        }
        else // fighting
        {
            if( !(approached.getSpecies() == SPECIES.TURTLE && this.getStrength() < 5) ) // attack doesn't take place when attacker isn't powerful enough to defeat his turtle opponent
            {
                if (approached.getStrength() > this.getStrength()) // attacker loses
                {
                    if (approached.getSpecies() == SPECIES.BELLADONNA)
                    {
                        world.organism_container.remove(this.world.board[approached_column][approached_row].getId());
                        this.world.board[approached_column][approached_row] = null;
                        world.organism_container.remove(this.world.board[this.coords.getColumn()][this.coords.getRow()].getId());
                        this.world.board[this.coords.getColumn()][this.coords.getRow()] = null;
                    }
                    else
                    {
                        world.organism_container.remove(this.world.board[this.coords.getColumn()][this.coords.getRow()].getId());
                        this.world.board[this.coords.getColumn()][this.coords.getRow()] = null;
                    }

                    
                } else //attacker wins
                {
                    if (approached.getSpecies() == SPECIES.GUARANA) this.setStrength(getStrength() + 3);
                    world.organism_container.remove(this.world.board[approached_column][approached_row].getId());
                    this.world.board[approached_column][approached_row] = null;
                    
                    this.world.board[approached_column][approached_row] = this;  //move Animal to new location
                    this.world.board[this.coords.getColumn()][this.coords.getRow()] = null; //delete it from previous location
                    this.coords.setColumn(approached_column);
                    this.coords.setRow(approached_row);
                }
            }
        }
    }
}