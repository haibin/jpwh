package org.jpwh.model.associations.manytoone.bi;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
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
      mappedBy = "item",
      fetch = FetchType.LAZY,
      cascade = CascadeType.PERSIST
  )
  private Set<Bid> bids = new HashSet<>();

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
