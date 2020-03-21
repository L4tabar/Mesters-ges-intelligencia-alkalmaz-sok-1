package beadando;

import java.util.*;

public class Main {

    private static boolean isGoalState(String value) {
        return value.equals("*");
    }

    public static void main(String[] args) {
        String[][] world = {
                {String.valueOf(1), String.valueOf(5), String.valueOf(3), String.valueOf(4), String.valueOf(3), String.valueOf(6), String.valueOf(7), String.valueOf(1), String.valueOf(1), String.valueOf(6)},
                {String.valueOf(4), String.valueOf(4), String.valueOf(3), String.valueOf(4), String.valueOf(2), String.valueOf(6), String.valueOf(2), String.valueOf(6), String.valueOf(2), String.valueOf(6)},
                {String.valueOf(1), String.valueOf(3), String.valueOf(9), String.valueOf(4), String.valueOf(5), String.valueOf(2), String.valueOf(4), String.valueOf(2), String.valueOf(9), String.valueOf(5)},
                {String.valueOf(5), String.valueOf(2), String.valueOf(3), String.valueOf(5), String.valueOf(5), String.valueOf(6), String.valueOf(4), String.valueOf(6), String.valueOf(2), String.valueOf(4)},
                {String.valueOf(1), String.valueOf(3), String.valueOf(3), String.valueOf(2), String.valueOf(5), String.valueOf(6), String.valueOf(5), String.valueOf(2), String.valueOf(3), String.valueOf(2)},
                {String.valueOf(2), String.valueOf(5), String.valueOf(2), String.valueOf(5), String.valueOf(5), String.valueOf(6), String.valueOf(4), String.valueOf(8), String.valueOf(6), String.valueOf(1)},
                {String.valueOf(9), String.valueOf(2), String.valueOf(3), String.valueOf(6), String.valueOf(5), String.valueOf(6), String.valueOf(2), String.valueOf(2), String.valueOf(2), "*"}
        };
        State startState = new State(0,0,world[0][0]);

        Node actualNode = new Node(startState, null, null, new HashSet<Operator>());

        List<Operator> operators = Arrays.asList(
                new Operator("U"),
                new Operator("D"),
                new Operator("L"),
                new Operator("R"),
                new Operator("UR"),
                new Operator("UL"),
                new Operator("DR"),
                new Operator("DL")
        );

        List<State> states = new ArrayList<>();

        String v = "";

        while(true){
            if(actualNode == null) {
                break;
            }
            if(actualNode.getState() != null)
            if(isGoalState(String.valueOf(actualNode.getState().getValue()))) {
                break;
            }

            if (states.contains(actualNode.getState())) {
                actualNode = actualNode.getParent();
            }

            states.add(actualNode.getState());
            List<Operator> availableOperators = new LinkedList<>();

            for(int i = 0; i < operators.size(); i++) {
                if(operators.get(i).preconditionForUse(actualNode.getState())
                        &&  ! actualNode.getUsedOperators().contains(operators.get(i))){
                    availableOperators.add(operators.get(i));}
            }

            if(availableOperators.size() > 0) {
                Operator operator = availableOperators.get(0);

                Node newNode = new Node();
                newNode.setState(operator.use(actualNode.getState()));
                newNode.setParent(new Node(actualNode));
                Set<Operator> set = actualNode.getUsedOperators();
                if(set == null) {
                    set = new HashSet<>();
                }
                set.add(operator);
                actualNode.setUsedOperators(set);
                newNode.setUsedOperators(set = new HashSet<>());
                newNode.setOperator(operator);
                actualNode = newNode;
                v = String.valueOf(actualNode.getState().getValue());
            }
            else if (actualNode.getParent() != null){
                v = String.valueOf(actualNode.getState().getValue());
            }
            else {
                actualNode = null;
            }

        }
        if(isGoalState(v)) {
            List<Node> solution = new ArrayList<>();
            System.out.println("Találtunk megoldást");
            while(actualNode.getParent() != null) {
                solution.add(actualNode);
                actualNode = actualNode.getParent();
            }
            for (int i = solution.size() - 1; i >= 0; i--) System.out.println(solution.get(i).getState());
        }
        else {
            System.out.println("Nincs megoldás");
        }

    }

}

