package org.jpwh.model.associations.onetoone.jointable;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Shipment.class)
public abstract class Shipment_ {

	public static volatile SingularAttribute<Shipment, ShipmentState> shipmentState;
	public static volatile SingularAttribute<Shipment, Long> id;
	public static volatile SingularAttribute<Shipment, Date> createdOn;
	public static volatile SingularAttribute<Shipment, Item> auction;

}

