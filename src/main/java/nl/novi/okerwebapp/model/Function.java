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
    private int basic_salary;

    public Function() {}
        public Function(int function_id, String name, int basic_salary) {
            this.function_id = function_id;
            this.name = name;
            this.basic_salary = basic_salary;
    }

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

    public int getBasic_salary() {
        return basic_salary;
    }

    public void setBasic_salary(int basic_salary) {
        this.basic_salary = basic_salary;
    }
}
