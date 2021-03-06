package org.jpwh.model.associations.onetomany.bidirectional;

import org.jpwh.model.Constants;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
public class Bid {

    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    protected Long id;

    @ManyToOne(fetch = FetchType.LAZY) // Defaults to EAGER
    @JoinColumn(name = "ITEM_ID", nullable = false)
    protected Item item1;

    @NotNull
    protected BigDecimal amount;

    // constructors

    public Bid() { }

    public Bid(BigDecimal amount, Item item) {
        this.amount = amount;
        this.item1 = item;
    }

    // getters and setters

    public Item getItem() {
        return item1;
    }
    public void setItem(Item item) {
        this.item1 = item;
    }

    public BigDecimal getAmount() {
        return amount;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
