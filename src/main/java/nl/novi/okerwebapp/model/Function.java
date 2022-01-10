package nl.novi.okerwebapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Functions")

public class Function {

    @Id
    @Column(nullable = false)
    private int function_id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int basicsalary;

    //constructor
    public Function() {}
        public Function(int id, String name, int basicsalary) {
            this.function_id = id;
            this.name = name;
            this.basicsalary = basicsalary;
    }

    //getters en setters

    public int getId() {
        return function_id;
    }

    public void setId(int id) {
        this.function_id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBasicsalary() {
        return basicsalary;
    }

    public void setBasicsalary(int basicsalary) {
        this.basicsalary = basicsalary;
    }
}
