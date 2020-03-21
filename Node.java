package beadando;

import java.util.Set;

public class Node {
    private State state;
    private Node parent;
    private Operator operator;
    private Set<Operator> usedOperators;

    public Node() {

    }

    public Node(State state, Node parent, Operator operator, Set<Operator> usedOperators) {
        this.state = state;
        this.parent = parent;
        this.operator = operator;
        this.usedOperators = usedOperators;
    }

    public Node(Node node) {
        this.state = node.state;
        this.parent = node.parent;
        this.operator = node.operator;
        this.usedOperators = node.usedOperators;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public Set<Operator> getUsedOperators() {
        return usedOperators;
    }

    public void setUsedOperators(Set<Operator> usedOperators) {
        this.usedOperators = usedOperators;
    }


    @Override
    public String toString() {
        String parentState = parent == null ? "null" : parent.getState().toString();

        return "Node [state=" + state + ", parentState=" + parentState + ", operator=" + operator + ", usedOperators="
                + usedOperators + "]";
    }
}
