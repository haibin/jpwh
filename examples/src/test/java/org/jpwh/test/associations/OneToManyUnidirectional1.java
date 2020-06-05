package org.jpwh.test.associations;

import static org.testng.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import org.jpwh.env.JPATest;
import org.jpwh.model.associations.onetomany.unidirectional1.Bid;
import org.jpwh.model.associations.onetomany.unidirectional1.Item;
import org.testng.annotations.Test;

public class OneToManyUnidirectional1 extends JPATest {

    @Override
    public void configurePersistenceUnit() throws Exception {
        configurePersistenceUnit("OneToManyUnidirectional1PU");
    }

    private Long persist(UserTransaction tx)
        throws SystemException, NotSupportedException, HeuristicRollbackException, HeuristicMixedException, RollbackException {
        tx.begin();
        EntityManager em = JPA.createEntityManager();

        Bid someBid = new Bid(new BigDecimal("123.00"));
        em.persist(someBid);

        Bid secondBid = new Bid(new BigDecimal("456.00"));
        em.persist(secondBid);

        Item someItem = new Item("Some Item");
        someItem.getBids().add(someBid);
        someItem.getBids().add(secondBid);
        em.persist(someItem);

        tx.commit(); // Dirty checking, SQL execution
        em.close();

        return someItem.getId();
    }

    private void find(UserTransaction tx, Long ITEM_ID)
        throws SystemException, NotSupportedException, HeuristicRollbackException, HeuristicMixedException, RollbackException {
        tx.begin();
        EntityManager em = JPA.createEntityManager();

        Item item = em.find(Item.class, ITEM_ID); // First SELECT to load ITEM row

        tx.commit();
        em.close();
    }

    private void query(UserTransaction tx, Long ITEM_ID)
        throws SystemException, NotSupportedException, HeuristicRollbackException, HeuristicMixedException, RollbackException {
        // Repeat the same with an explicit query, no collection mapping needed!
        tx.begin();
        EntityManager em = JPA.createEntityManager();

        Collection<Bid> bids =
            em.createQuery("select b from Bid b where b.item.id = :itemId")
                .setParameter("itemId", ITEM_ID)
                .getResultList();
        assertEquals(bids.size(), 2);

        tx.commit();
        em.close();
    }

    @Test
    public void storeAndLoadItemBids() throws Exception {
        UserTransaction tx = TM.getUserTransaction();
        try {
            Long ITEM_ID = persist(tx);

            find(tx, ITEM_ID);

//            query(tx, ITEM_ID);
        } finally {
            TM.rollback();
        }
    }
}