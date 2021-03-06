package org.jpwh.model.associations.onetomany.bidirectional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.jpwh.model.Constants;

@Entity
public class Item {

    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    protected Long id;

    protected String name;

    @OneToMany(
        mappedBy = "item1", // Required for bidirectional association
        fetch = FetchType.LAZY
    ) // The default
    // List and Set will generate the same set of tables.
    protected Set<Bid> bids = new HashSet<>();

    // constructors

    public Item() { }

    public Item(String name) {
        this.name = name;
    }

    // getters and setters

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Set<Bid> getBids() {
        return bids;
    }
    public void setBids(Set<Bid> bids) {
        this.bids = bids;
    }
}
