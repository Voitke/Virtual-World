public abstract class Organism //what is this shit, i didnt plant it !!!
{
    public static int nextId = 0;
    
	public static int bullshit = 4;
	
    private int age = 0;
    
    private int id;
    
    private int strength;
    
    enum SPECIES
    {
        HUMAN,
        WOLF,
        SHEEP,
        FOX,
        TURTLE,
        ANTILOPE,
        
        GRASS,
        DANDILLION,
        GUARANA,
        BELLADONNA
    }
    
    private SPECIES species;
    
    protected class Coords
    {
        private int row;
        private int column;
    
        public int getColumn() {
            return column;
        }
    
        public int getRow() {
            return row;
        }
    
        public void setColumn(int column) {
            this.column = column;
        }
    
        public void setRow(int row) {
            this.row = row;
        }
    }
    
    Coords coords;
    
    protected World world;
    
    public abstract void action();
    
    public void draw()
    {
        switch (this.getSpecies())
        {
            case WOLF:
                System.out.println("W");
                break;
            case SHEEP:
                System.out.println("S");
                break;
            case FOX:
                System.out.println("F");
                break;
            case TURTLE:
                System.out.println("T");
                break;
            case ANTILOPE:
                System.out.println("A");
                break;
            case GRASS:
                System.out.println("6");
                break;
            case DANDILLION:
                System.out.println("D");
                break;
            case GUARANA:
                System.out.println("W");
                break;
            case BELLADONNA:
                System.out.println("B");
                break;
            case HUMAN:
                System.out.println("H");
                break;
            default:
                System.out.println(" ");
                break;
        }
    }
    
    public int getStrength() {
        return strength;
    }
    
    public void setStrength(int strength) {
        this.strength = strength;
    }
    
    public SPECIES getSpecies() {
        return species;
    }
    
    public void setSpecies(SPECIES species) {
        this.species = species;
    }
    
    public int getAge() {
        return age;
    }
    
    public void incrementAge()
    {
        this.age++;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId() {
        this.id = nextId;
        nextId++;
    }
}