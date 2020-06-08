package org.jpwh.test.associations;

import static org.testng.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.transaction.UserTransaction;
import org.jpwh.env.JPATest;
import org.jpwh.model.associations.manytoone.uni.Bid;
import org.jpwh.model.associations.manytoone.uni.Item;
import org.testng.annotations.Test;

public class ManyToOneUnidirectional extends JPATest {

    @Override
    public void configurePersistenceUnit() throws Exception {
        configurePersistenceUnit("ManyToOneUnidirectionalPU");
    }

    @Test
    public void storeAndLoadItemBids() throws Exception {
        UserTransaction tx = TM.getUserTransaction();
        try {
            tx.begin();
            EntityManager em = JPA.createEntityManager();

            Item someItem = new Item("Some Item");
            Bid someBid = new Bid(new BigDecimal("123.00"), someItem);
            Bid secondBid = new Bid(new BigDecimal("456.00"), someItem);

            // When should we persis them?
            // The only requirement is that item should be persisted before bid.
            // Of course we can persist item much earlier.

            // Before persisting, id is null.
            // After persisting, id is 1000 (no need to retrieve it again)

            em.persist(someItem);
            em.persist(someBid);
            em.persist(secondBid);

            // No need to persist. Dirty checking does it.
            someItem.setName("hello world");

            tx.commit(); // Dirty checking, SQL execution
            em.close();



//            Long ITEM_ID = someItem.getId();
//
//            tx.begin();
//            em = JPA.createEntityManager();
//
//            Item item = em.find(Item.class, ITEM_ID); // First SELECT to load ITEM row
//            assertEquals(item.getName(), "Some Item");
//
//            tx.commit();
//            em.close();
//
//
//
//
//            // Repeat the same with an explicit query, no collection mapping needed!
//            tx.begin();
//            em = JPA.createEntityManager();
//
//            Collection<Bid> bids =
//                    em.createQuery("select b from Bid b where b.item.id = :itemId")
//                    .setParameter("itemId", ITEM_ID)
//                    .getResultList();
//            assertEquals(bids.size(), 2);
//
//            tx.commit();
//            em.close();

        } finally {
            TM.rollback();
        }
    }
}