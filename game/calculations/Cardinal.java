package game.calculations;

public enum Cardinal {
	WEST,
	NORTH,
	EAST,
	SOUTH;

	public Cardinal nextClockwise(){
		switch(this){
		case WEST:
			return NORTH;
		case NORTH:
			return EAST;
		case EAST:
			return SOUTH;
		case SOUTH:
			return WEST;
		default:
			return null;
		}
	}
	
	public Cardinal nextCounterClockwise(){
		switch(this){
		case WEST:
			return SOUTH;
		case SOUTH:
			return EAST;
		case EAST:
			return NORTH;
		case NORTH:
			return WEST;
		default:
			return null;
		}
	}
}
