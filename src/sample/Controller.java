package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.object.TableCell;
import sample.repository.EntryData;
import sample.repository.FakeRepository;
import sample.repository.Repository;
import sample.solver.Solver;

public class Controller {
    private final Repository repository = new FakeRepository();

    @FXML
    public TextField popyt1, popyt2, popyt3, popyt4;
    public TextField podaz1, podaz2;
    public TextField c_zakupu1, c_zakupu2;
    public TextField c_sprzedazy1, c_sprzedazy2, c_sprzedazy3, c_sprzedazy4;
    public TextField k_transp00, k_transp01, k_transp02, k_transp03, k_transp10, k_transp11, k_transp12, k_transp13;
    public Label zysk, label00, label01, label02, label03, label10, label11, label12, label13;

    public void onButtonClick() {
        //Solver solver = new Solver(repository.getEntryData());
        Solver solver = new Solver(collectData());
        solver.solveProblem();
        showTable(solver.getTable());
        showProfit(solver.getProfit());
    }

    private EntryData collectData() {
        int[] dostawcy = {Integer.parseInt(podaz1.getText()), Integer.parseInt(podaz2.getText())};
        int[] odbiorcy = {Integer.parseInt(popyt1.getText()), Integer.parseInt(popyt2.getText()), Integer.parseInt(popyt3.getText()), Integer.parseInt(popyt4.getText())};
        int[][] kosztyTransportu = new int[][]{
                new int[]{Integer.parseInt(k_transp00.getText()), Integer.parseInt(k_transp01.getText()), Integer.parseInt(k_transp02.getText()), Integer.parseInt(k_transp03.getText())},
                new int[]{Integer.parseInt(k_transp10.getText()), Integer.parseInt(k_transp11.getText()), Integer.parseInt(k_transp12.getText()), Integer.parseInt(k_transp13.getText())}
        };
        int[] cenySprzedazy = {Integer.parseInt(c_sprzedazy1.getText()), Integer.parseInt(c_sprzedazy2.getText()), Integer.parseInt(c_sprzedazy3.getText()), Integer.parseInt(c_sprzedazy4.getText())};
        int[] cenyZakupu = {Integer.parseInt(c_zakupu1.getText()), Integer.parseInt(c_zakupu2.getText())};
        return new EntryData(dostawcy, odbiorcy, kosztyTransportu, cenySprzedazy, cenyZakupu);
    }

    private void showProfit(int profit) {
        zysk.setText(String.valueOf(profit));
    }

    private void showTable(TableCell[][] table) {
        label00.setText(Integer.toString(table[0][0].getVal()));
        label01.setText(Integer.toString(table[0][1].getVal()));
        label02.setText(Integer.toString(table[0][2].getVal()));
        label03.setText(Integer.toString(table[0][3].getVal()));
        label10.setText(Integer.toString(table[1][0].getVal()));
        label11.setText(Integer.toString(table[1][1].getVal()));
        label12.setText(Integer.toString(table[1][2].getVal()));
        label13.setText(Integer.toString(table[1][3].getVal()));
    }

}
