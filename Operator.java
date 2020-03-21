package beadando;


import java.util.Arrays;
import java.util.Objects;

public class Operator {
    private String direction;

    private String[][] world = {
            {String.valueOf(1), String.valueOf(5), String.valueOf(3), String.valueOf(4), String.valueOf(3), String.valueOf(6), String.valueOf(7), String.valueOf(1), String.valueOf(1), String.valueOf(6)},
            {String.valueOf(4), String.valueOf(4), String.valueOf(3), String.valueOf(4), String.valueOf(2), String.valueOf(6), String.valueOf(2), String.valueOf(6), String.valueOf(2), String.valueOf(6)},
            {String.valueOf(1), String.valueOf(3), String.valueOf(9), String.valueOf(4), String.valueOf(5), String.valueOf(2), String.valueOf(4), String.valueOf(2), String.valueOf(9), String.valueOf(5)},
            {String.valueOf(5), String.valueOf(2), String.valueOf(3), String.valueOf(5), String.valueOf(5), String.valueOf(6), String.valueOf(4), String.valueOf(6), String.valueOf(2), String.valueOf(4)},
            {String.valueOf(1), String.valueOf(3), String.valueOf(3), String.valueOf(2), String.valueOf(5), String.valueOf(6), String.valueOf(5), String.valueOf(2), String.valueOf(3), String.valueOf(2)},
            {String.valueOf(2), String.valueOf(5), String.valueOf(2), String.valueOf(5), String.valueOf(5), String.valueOf(6), String.valueOf(4), String.valueOf(8), String.valueOf(6), String.valueOf(1)},
            {String.valueOf(9), String.valueOf(2), String.valueOf(3), String.valueOf(6), String.valueOf(5), String.valueOf(6), String.valueOf(2), String.valueOf(2), String.valueOf(2), "*"}
    };

    public Operator(String direction) {
        this.direction = direction;
    }

    public boolean preconditionForUse(State state) {
        switch (direction){
            case "U": //Up
                if(state.getRow() > Integer.parseInt(state.getValue()))
                return ((state.getRow() - Integer.parseInt(state.getValue())) >= 0);
                else return false;
            case "D": //Down
                return ((state.getRow() + Integer.parseInt(state.getValue())) <= 6);
            case "L": //Left
                if(state.getColumn() > Integer.parseInt(state.getValue()))
                return ((state.getColumn() - Integer.parseInt(state.getValue())) >= 0);
                else return false;
            case "R": //Right
                return ((state.getColumn() + Integer.parseInt(state.getValue())) <= 9);
            case "UR": //Up-Right
                if(state.getRow() > Integer.parseInt(state.getValue()))
                return ((state.getRow()- Integer.parseInt(state.getValue())) >= 0 && state.getColumn() + Integer.parseInt(state.getValue()) <=9);
                else return false;
            case "UL": //Up-Left
                if(state.getRow() > Integer.parseInt(state.getValue()) && state.getColumn() > Integer.parseInt(state.getValue()))
                return ((state.getRow() - Integer.parseInt(state.getValue())) >= 0 && state.getColumn() - Integer.parseInt(state.getValue()) >=0);
                else return false;
            case "DR": //Down-Right
                return ((state.getRow() + Integer.parseInt(state.getValue())) <= 6 && state.getColumn() + Integer.parseInt(state.getValue()) <=9);
            case "DL": //Down-Left
                if(state.getRow() > Integer.parseInt(state.getValue()) && state.getColumn() > Integer.parseInt(state.getValue()))
                return ((state.getRow() + Integer.parseInt(state.getValue())) <= 6 && state.getColumn() - Integer.parseInt(state.getValue()) >=0);
                else return false;
            default : return false;
        }
    }

    public State use(State state) {
        State newState = new State();

        switch (direction) {
            case "U": //Up
                newState.setRow(state.getRow() - Integer.parseInt(state.getValue()));
                newState.setColumn(state.getColumn());
                newState.setValue(world[newState.getRow()][newState.getColumn()]);
                break;
            case "D": //Down
                newState.setRow(state.getRow() + Integer.parseInt(state.getValue()));
                newState.setColumn(state.getColumn());
                newState.setValue(world[newState.getRow()][newState.getColumn()]);
                break;
            case "L": //Left
                newState.setRow(state.getRow());
                newState.setColumn(state.getColumn() - Integer.parseInt(state.getValue()));
                newState.setValue(world[newState.getRow()][newState.getColumn()]);
                break;
            case "R": //Right
                newState.setRow(state.getRow());
                newState.setColumn(state.getColumn() + Integer.parseInt(state.getValue()));
                newState.setValue(world[newState.getRow()][newState.getColumn()]);
                break;
            case "UR": //Up-Right
                newState.setRow(state.getRow() - Integer.parseInt(state.getValue()));
                newState.setColumn(state.getColumn() + Integer.parseInt(state.getValue()));
                newState.setValue(world[newState.getRow()][newState.getColumn()]);
                break;
            case "UL": //Up-Left
                newState.setRow(state.getRow() - Integer.parseInt(state.getValue()));
                newState.setColumn(state.getColumn() - Integer.parseInt(state.getValue()));
                newState.setValue(world[newState.getRow()][newState.getColumn()]);
                break;
            case "DR": //Down-Right
                newState.setRow(state.getRow() + Integer.parseInt(state.getValue()));
                newState.setColumn(state.getColumn() + Integer.parseInt(state.getValue()));
                newState.setValue(world[newState.getRow()][newState.getColumn()]);
                break;
            case "DL": //Down-Left
                newState.setRow(state.getRow() + Integer.parseInt(state.getValue()));
                newState.setColumn(state.getColumn() - Integer.parseInt(state.getValue()));
                newState.setValue(world[newState.getRow()][newState.getColumn()]);
                break;
            default:
                System.out.println("No match");
        }
        return newState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Operator operator = (Operator) o;
        return direction == operator.direction &&
                Arrays.equals(world, operator.world);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(direction);
        result = 31 * result + Arrays.hashCode(world);
        return result;
    }

    @Override
    public String toString() {
        return "Operator{" +
                "direction='" + direction + '\'' +
                '}';
    }
}
