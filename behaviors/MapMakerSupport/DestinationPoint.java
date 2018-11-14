public enum Orientation{
    NORTH, SOUTH, EAST, WEST
}

public class DestinationPoint{
    Direction north;
    Direction south;
    Direction east;
    Direction west;

    DestinationPoint peviousDestination;
    boolean deadEnd;

    public DestinationPoint(){
        north = new Direction();
        south = new Direction();
        east = new Direction();
        west = new Direction();
    }
    public DestinationPoint(Direction n, Direction s, Direction e, Direction w){
        north = n;
        south = s;
        east = e;
        west = w;
        deadEnd = false;
    }

    public boolean hasUnvistedDirections(){
        return (!north.hasVisited() || !south.hasVisited() || !east.hasVisited() || !west.hasVisited());
    }

    public DestinationPoint getPreviousDestination(){
        return peviousDestination;
    }

    public int cameFrom(){
        if(previousDestination.north.hasLeftFrom())
            return NORTH;
        else if(previousDestination.south.hasLeftFrom())
            return SOUTH;
        else if(previousDestination.east.hasLeftFrom())
            return EAST;
        else if(previousDestination.west.hasLeftFrom())
            return WEST;
        else
            return -1;
    }

    public void setPreviousDestination(DestinationPoint pd){
        peviousDestination = pd;
    }

    public void goDirection(int direction){
        switch (direction) {
            case NORTH:
                north.setLeftFrom(true);
                break;
            case SOUTH:
                south.setLeftFrom(true);
                break;
            case EAST:
                east.setLeftFrom(true);
                break;
            case WEST:
                west.setLeftFrom(true);
                break;
            default:
                break;
        }
    }
}