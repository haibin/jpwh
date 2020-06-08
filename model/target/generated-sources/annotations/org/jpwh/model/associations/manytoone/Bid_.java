package org.jpwh.model.associations.manytoone;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.jpwh.model.associations.manytoone.uni.Bid;
import org.jpwh.model.associations.manytoone.uni.Item;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Bid.class)
public abstract class Bid_ {

	public static volatile SingularAttribute<Bid, Item> item;
	public static volatile SingularAttribute<Bid, BigDecimal> amount;
	public static volatile SingularAttribute<Bid, Long> id;

}

