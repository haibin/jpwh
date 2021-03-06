package org.jpwh.model.associations.manytoone.uni;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.jpwh.model.Constants;

@Entity
public class Item {
  @Id
  @GeneratedValue(generator = Constants.ID_GENERATOR)
  protected Long id;

  protected String name;

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

}
