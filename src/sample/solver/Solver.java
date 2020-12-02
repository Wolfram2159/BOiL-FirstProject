package sample.solver;

import java.util.Arrays;

import sample.object.Equation;
import sample.object.EquationList;
import sample.object.Pair;
import sample.object.TableCell;
import sample.repository.EntryData;

public class Solver {
    private final EntryData data;
    private int[][] zArray;
    private TableCell[][] table;
    private EquationList equationList;
    private int profit = 0;

    public TableCell[][] getTable() {
        return table;
    }

    public int getProfit() {
        return profit;
    }

    public Solver(EntryData data) {
        this.data = data;
        this.equationList = new EquationList(data.getSuppliers().length, data.getRecipients().length);
    }

    public void solveProblem() {
        zArray = calculateIndividualProfit(data);
        createTable();
        printTable();
        createEquations();
        solveEquations();
        while(!equationList.isOptimalSolution()) {
            balanceTable(equationList.createLoop());
            createEquations();
            solveEquations();
        }
        showSolution();
    }

    private void printTable() {
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                System.out.print(table[i][j].getZ() + "/" + table[i][j].getVal() + "    ");
            }
            System.out.println();
        }
    }

    private int[][] calculateIndividualProfit(EntryData entryData) {
        int[][] individualProfits = new int[2][];

        for (int i = 0; i < entryData.getTransportCosts().length; i++) {
            individualProfits[i] = new int[4];
            for (int j = 0; j < entryData.getTransportCosts()[i].length; j++) {
                individualProfits[i][j] = entryData.getSellPrices()[j] - entryData.getTransportCosts()[i][j] - entryData.getBuyPrices()[i];
                System.out.println(i + "/" + j + " = " + individualProfits[i][j]);
            }
        }

        return individualProfits;
    }

    private void createTable() {
        fillTable();
        completeTable(getIndexesSortedByValue());
    }

    private void fillTable() {
        table = new TableCell[zArray.length + 1][];
        int secondDimension = zArray[0].length + 1;
        for (int i = 0; i < table.length; i++) {
            table[i] = new TableCell[secondDimension];
            for (int j = 0; j < table[i].length; j++) {
                table[i][j] = (i == zArray.length || j == zArray[0].length) ? new TableCell() : new TableCell(zArray[i][j]);
            }
        }
    }

    private Pair[] getIndexesSortedByValue() {
        Pair[] indexes = new Pair[zArray.length * zArray[0].length];
        int index = 0;
        for (int i = 0; i < zArray.length; i++) {
            for (int j = 0; j < zArray[i].length; j++) {
                indexes[index] = new Pair(i, j);
                index++;
            }
        }
        int n = indexes.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (zArray[indexes[j].getFirst()][indexes[j].getSecond()] < zArray[indexes[j + 1].getFirst()][indexes[j + 1].getSecond()]) {
                    Pair temp = indexes[j];
                    indexes[j] = indexes[j + 1];
                    indexes[j + 1] = temp;
                }
            }
        }
        return indexes;
    }

    private void completeTable(Pair[] indexesSortedByValue) {
        int[] supplier = Arrays.copyOf(data.getSuppliers(), data.getSuppliers().length + 1);
        int supplierSum = 0;
        for (int i : supplier) {
            supplierSum += i;
        }
        int[] recipient = Arrays.copyOf(data.getRecipients(), data.getRecipients().length + 1);
        int recipientSum = 0;
        for (int i : recipient) {
            recipientSum += i;
        }
        recipient[recipient.length - 1] = supplierSum;
        supplier[supplier.length - 1] = recipientSum;
        for (Pair pair : indexesSortedByValue) {
            if (supplier[pair.getFirst()] > 0) {
                int availableSpace = getAvailableSpace(pair.getSecond());
                if (availableSpace <= supplier[pair.getFirst()]) {
                   table[pair.getFirst()][pair.getSecond()].setVal(availableSpace);
                   supplier[pair.getFirst()] -= availableSpace;
                } else {
                    table[pair.getFirst()][pair.getSecond()].setVal(supplier[pair.getFirst()]);
                    supplier[pair.getFirst()] = 0;
                }
            }
        }
        fillFictional(supplier);
    }

    private int getAvailableSpace(int odbiorca) {
        int sum = 0;
        for (int i = 0; i < table.length; i++) {
            sum += table[i][odbiorca].getVal();
        }
        return Math.max(data.getRecipients()[odbiorca] - sum, 0);
    }

    private void fillFictional(int[] dostawcy) {
        for (int i = 0; i < table.length; i++) {
            if (dostawcy[i] > 0 && i != table.length - 1) {
                table[i][table[i].length - 1].setVal(dostawcy[i]);
                dostawcy[i] = 0;
            }
            if (i == table.length - 1) {
                for (int j = 0; j < table[0].length - 1; j++) {
                    int availableSpace = getAvailableSpace(j);
                    table[i][j].setVal(availableSpace);
                    dostawcy[i] -= availableSpace;
                }
            }
            if (i == table.length - 1) {
                table[i][table[0].length - 1].setVal(dostawcy[dostawcy.length - 1]);
            }
        }
    }

    private void createEquations() {
        equationList.clear();
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                if (table[i][j].getVal() != 0) {
                    equationList.addDualEquation(new Equation(table[i][j].getZ(), i, j));
                } else {
                    equationList.addCriterialEquation(new Equation(table[i][j].getZ(), i, j));
                }
            }
        }
    }

    private void solveEquations() {
        equationList.solveDualEquations();
        equationList.solveCriterialEquations();
    }

    private void balanceTable(Pair[] pairCoords) {
        if (pairCoords == null) {
            System.out.println("Something wrong :/");
        } else {
            Pair firstCoords = pairCoords[0];
            Pair secondCoords = pairCoords[1];
            int min = Math.min(table[firstCoords.getFirst()][secondCoords.getSecond()].getVal(), table[secondCoords.getFirst()][firstCoords.getSecond()].getVal());
            table[firstCoords.getFirst()][firstCoords.getSecond()].addVal(min);
            table[secondCoords.getFirst()][secondCoords.getSecond()].addVal(min);
            table[firstCoords.getFirst()][secondCoords.getSecond()].substractVal(min);
            table[secondCoords.getFirst()][firstCoords.getSecond()].substractVal(min);
            printTable();
        }
    }

    private void showSolution() {
        for (int i = 0; i < table.length - 1; i++) {
            for (int j = 0; j < table[i].length - 1; j++) {
                System.out.print(table[i][j].getZ() + " / " + table[i][j].getVal() + "      ");
                profit += table[i][j].getZ() * table[i][j].getVal();
            }
            System.out.println();
        }
        System.out.println("Profit = " + profit);
    }
}
