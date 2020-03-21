package beadando;

import java.util.Objects;

public class State {
    private int row;
    private int column;
    private String value;


    public State() {

    }

    public State(int row, int column, String value) {
        this.row = row;
        this.column = column;
        this.value = value;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return row == state.row &&
                column == state.column &&
                Objects.equals(value, state.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column, value);
    }

    @Override
    public String toString() {
        return "State{" +
                "row=" + row +
                ", column=" + column +
                ", value='" + value + '\'' +
                '}';
    }
}

