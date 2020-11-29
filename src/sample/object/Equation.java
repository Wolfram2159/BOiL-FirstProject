package sample.object;

public class Equation {
    static class Factor {
        private int factor;
        private boolean set;

        public Factor() {
            this.set = false;
        }

        public Factor(int factor) {
            this.factor = factor;
            this.set = true;
        }

        public int getFactor() {
            return factor;
        }

        public boolean isSet() {
            return set;
        }

        public void setFactor(int factor) {
            this.factor = factor;
            this.set = true;
        }

        @Override
        public String toString() {
            return "Factor{" +
                    "factor=" + factor +
                    ", set=" + set +
                    '}';
        }
    }

    private int z;
    private Factor alfa;
    private Factor beta;
    private int i;
    private int j;

    public Equation(int z, int i, int j) {
        this.z = z;
        this.i = i;
        this.j = j;
        this.alfa = new Factor();
        this.beta = new Factor();
        if (i == 0) {
            alfa = new Factor(0);
            solve();
        }
    }

    public void setAlfa(int alfa) {
        this.alfa = new Factor(alfa);
    }

    public void setBeta(int beta) {
        this.beta = new Factor(beta);
    }

    public Integer getResult() {
        if (isSolved()) return z - alfa.getFactor() - beta.getFactor();
        return null;
    }

    public void solve() {
        if (alfa.isSet() && !beta.isSet()) {
            int betaFactor = z - alfa.getFactor();
            beta.setFactor(betaFactor);
        }
        if (!alfa.isSet() && beta.isSet()) {
            int alfaFactor = z - beta.getFactor();
            alfa.setFactor(alfaFactor);
        }
    }

    public boolean isSolved() {
        return alfa.isSet() && beta.isSet();
    }

    public int getAlfa() {
        return alfa.getFactor();
    }

    public int getBeta() {
        return beta.getFactor();
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    @Override
    public String toString() {
        return "Equation{" +
                "z=" + z +
                ", alfa=" + alfa +
                ", beta=" + beta +
                ", i=" + i +
                ", j=" + j +
                '}';
    }
}
