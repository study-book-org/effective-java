package chapter03.ndy.person;

public class CloneablePerson  {
    String name;

    public CloneablePerson(String name) {
        this.name = name;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return new CloneablePerson(this.name);
    }

    @Override
    public String toString() {
        return "CloneablePerson{" +
                "name='" + name + '\'' +
                '}';
    }
}