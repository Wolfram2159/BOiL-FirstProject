package sample.object;

import java.util.ArrayList;
import java.util.List;

public class EquationList {

    private List<Equation> dualEquationList;
    private List<Equation> criterialEquationList;
    private List<Integer> criterialResults;
    private Integer[][] resultTable;
    private int solvedEquations = -1;

    public EquationList(int i, int j) {
        this.dualEquationList = new ArrayList<>();
        this.criterialEquationList = new ArrayList<>();
        this.criterialResults = new ArrayList<>();
        resultTable = new Integer[i + 1][j + 1];
    }

    public void addDualEquation(Equation equation) {
        dualEquationList.add(equation);
    }

    public void addCriterialEquation(Equation equation) {
        criterialEquationList.add(equation);
    }

    public void solveDualEquations() {
        while(!isAllSolved()) {
            if (!isLessSolutionsThanBefore()) setLastAlfaToZero();
            for (Equation equation : dualEquationList) {
                equation.solve();
                if (equation.isSolved()) {
                    int i = equation.getI();
                    int j = equation.getJ();
                    for (Equation equation1 : dualEquationList) {
                        if (equation1.getI() == i && equation1.getJ() != j) equation1.setAlfa(equation.getAlfa());
                        else if (equation1.getI() != i && equation1.getJ() == j) equation1.setBeta(equation.getBeta());
                    }
                }
            }
        }
    }

    private boolean isLessSolutionsThanBefore() {
        int sumOfSolved = 0;
        for (Equation equation : dualEquationList) {
            if (equation.isSolved()) sumOfSolved++;
        }
        boolean isLess = sumOfSolved != solvedEquations;
        solvedEquations = sumOfSolved;
        return isLess;
    }

    public void solveCriterialEquations() {
        for (Equation equation : criterialEquationList) {
            int i = equation.getI();
            int j = equation.getJ();
            equation.setAlfa(findAlfa(i));
            equation.setBeta(findBeta(j));
            criterialResults.add(equation.getResult());
        }
        createResultTable();
    }

    private Integer findAlfa(int i) {
        for (Equation equation : dualEquationList) {
            if (equation.getI() == i) return equation.getAlfa();
        }
        return null;
    }

    private Integer findBeta(int j) {
        for (Equation equation : dualEquationList) {
            if (equation.getJ() == j) return equation.getBeta();
        }
        return null;
    }

    private void createResultTable() {
        for (int i = 0; i < criterialEquationList.size(); i++) {
            resultTable[criterialEquationList.get(i).getI()][criterialEquationList.get(i).getJ()] = criterialResults.get(i);
        }

        for (int i = 0; i < resultTable.length; i++) {
            for (int j = 0; j < resultTable[i].length; j++) {
                System.out.print((resultTable[i][j] == null ? "x" : resultTable[i][j]) + "    ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public Pair[] createLoop() {
        int max = 0;
        Pair maxIndexes = new Pair();
        for (int i = 0; i < resultTable.length; i++) {
            for (int j = 0; j < resultTable[i].length; j++) {
                if (resultTable[i][j] != null && resultTable[i][j] > max) {
                    max = resultTable[i][j];
                    maxIndexes.setA(i);
                    maxIndexes.setB(j);
                }
            }
        }

        List<Pair> pairs = new ArrayList<>();
        for (int i = 0; i < resultTable.length; i++) {
            for (int j = 0; j < resultTable[i].length; j++) {
                if (i != maxIndexes.getFirst() && j != maxIndexes.getSecond() && (resultTable[i][j] == null || resultTable[i][j] > 0)) pairs.add(new Pair(i, j));
            }
        }

        for (Pair pair : pairs) {
            if (isPairMatch(pair, maxIndexes)) {
                return new Pair[] {pair, maxIndexes};
            }
        }
        return null;
    }

    private boolean isPairMatch(Pair cornerIndexes, Pair maxIndexes) {
        return (resultTable[cornerIndexes.getFirst()][maxIndexes.getSecond()] == null && resultTable[maxIndexes.getFirst()][cornerIndexes.getSecond()] == null);
    }

    public boolean isOptimalSolution() {
        for (Integer criterialResult : criterialResults) {
            if (criterialResult > 0) return false;
        }
        return true;
    }

    private boolean isAllSolved() {
        int sumOfNotSolved = 0;
        for (Equation equation : dualEquationList) {
            if (!equation.isSolved()) sumOfNotSolved++;
        }
        return sumOfNotSolved == 0;
    }

    public void clear() {
        dualEquationList.clear();
        criterialEquationList.clear();
        criterialResults.clear();
        clearResultTable();
    }

    private void clearResultTable() {
        for (int i = 0; i < resultTable.length; i++) {
            for (int j = 0; j < resultTable[i].length; j++) {
                resultTable[i][j] = null;
            }
        }
    }

    private void setLastAlfaToZero() {
        int lastAlfaIndex = 0;
        for (Equation equation : dualEquationList) {
            if (lastAlfaIndex < equation.getI()) lastAlfaIndex = equation.getI();
        }
        for (Equation equation : dualEquationList) {
            if (equation.getI() == lastAlfaIndex) equation.setAlfa(0);
        }
    }

    public void printDualEquations() {
        for (Equation equation : dualEquationList) {
            System.out.println(equation.toString());
        }
    }

    public void printCriterialEquations() {
        for (Equation equation : criterialEquationList) {
            System.out.println(equation.toString());
        }
    }
}
