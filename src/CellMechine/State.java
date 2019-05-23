package CellMechine;

public class State {
    private int resource;
    private int ability;

    public State(int resource, int ability) {
        this.resource = resource;
        this.ability = ability;
    }

    public int getResource() {
        return resource;
    }

    public void setResource(int resource) {
        this.resource = resource;
    }

    public int getAbility() {
        return ability;
    }

    public void setAbility(int ability) {
        this.ability = ability;
    }
}
