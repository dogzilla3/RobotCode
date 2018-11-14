class Direction {
    
    
    boolean wall;
    boolean visited;
    boolean leftFrom;
    
    public Direction(boolean walledOff){
        wall = walledOff;
        visited = false;
        leftFrom = false;
    }

    public Direction(boolean walledOff, boolean leftFrom){
        wall = walledOff;
        visited = false;
        this.leftFrom = leftFrom;
    }
    
    public Direction(Direction d){
        wall = d.isWall()
        visited = d.hasVisted();
        leftFrom = d.hasLeftFrom();
    }

    Direction() : this(false, false){}
    
    public boolean hasVisted(){return visited;}
    public boolean isWall(){return wall;}
    public boolean hasLeftFrom(){return false;}

    public void setVisted(boolean visit){
        visited = visit;
    }
    public void setLeftFrom(boolean departed){
        leftFrom = departed;
    }


}