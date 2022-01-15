package nl.novi.okerwebapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Transports")
public class Transport {
    @Id
    @Column(nullable = false)
    private int transport_id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int costs;

    public int getId() {
        return transport_id;
    }

    public void setId(int id) {
        this.transport_id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCosts() {
        return costs;
    }

    public void setCosts(int costs) {
        this.costs = costs;
    }
}
