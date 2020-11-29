package sample;

import sample.repository.FakeRepository;
import sample.repository.Repository;
import sample.solver.Solver;

public class Controller {
    private final Repository repository = new FakeRepository();

    public void onButtonClick() {
        Solver solver = new Solver(repository.getEntryData());
        solver.solveProblem();
    }
}
