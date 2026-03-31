public class Consult {

    private final String consult;


    public Consult(String consult) {
        this.consult = consult;
    }

    public String getConsult() {
        return consult;
    }

    @Override
    public String toString() {
        return consult;
    }
}
