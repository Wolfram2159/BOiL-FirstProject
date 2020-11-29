package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
    public Label zysk;

    public void onButtonClick() {
        //Solver solver = new Solver(repository.getEntryData());
        Solver solver = new Solver(collectData());
        solver.solveProblem();
    }

    private EntryData collectData() {
        int[] dostawcy = {Integer.parseInt(podaz1.getText()), Integer.parseInt(podaz2.getText())};
        int[] odbiorcy = {Integer.parseInt(popyt1.getText()), Integer.parseInt(popyt2.getText()), Integer.parseInt(popyt3.getText()), Integer.parseInt(popyt4.getText())};
        int[][] kosztyTransportu = new int[][]{
                new int[]{Integer.parseInt(k_transp00.getText()),Integer.parseInt(k_transp01.getText()),Integer.parseInt(k_transp02.getText()),Integer.parseInt(k_transp03.getText())},
                new int[]{Integer.parseInt(k_transp10.getText()),Integer.parseInt(k_transp11.getText()),Integer.parseInt(k_transp12.getText()),Integer.parseInt(k_transp13.getText())}
        };
        int [] cenySprzedazy = {Integer.parseInt(c_sprzedazy1.getText()), Integer.parseInt(c_sprzedazy2.getText()),Integer.parseInt(c_sprzedazy3.getText()),Integer.parseInt(c_sprzedazy4.getText())};
        int [] cenyZakupu = {Integer.parseInt(c_zakupu1.getText()),Integer.parseInt(c_zakupu2.getText())};
        return  new EntryData(dostawcy,odbiorcy,kosztyTransportu,cenySprzedazy,cenyZakupu);
    }

}
