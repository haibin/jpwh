package org.jpwh.test.collections;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import org.jpwh.env.JPATest;
import org.jpwh.model.collections.setofstrings.Item;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.transaction.UserTransaction;

import static org.testng.Assert.assertEquals;

public class SetOfStrings extends JPATest {

    @Override
    public void configurePersistenceUnit() throws Exception {
        configurePersistenceUnit("SetOfStringsPU");
    }

    private Long persist(UserTransaction tx)
        throws SystemException, NotSupportedException, HeuristicRollbackException, HeuristicMixedException, RollbackException {
        tx.begin();
        EntityManager em = JPA.createEntityManager();
        Item someItem = new Item();

        someItem.getImages().add("foo.jpg");
        someItem.getImages().add("bar.jpg");
        someItem.getImages().add("baz.jpg");
        someItem.getImages().add("baz.jpg"); // Duplicate, filtered at Java level by HashSet!

        em.persist(someItem);
        tx.commit();
        em.close();

        return someItem.getId();
    }

    @Test
    public void storeLoadCollection() throws Exception {
        UserTransaction tx = TM.getUserTransaction();
        try {
            Long ITEM_ID = persist(tx);

            tx.begin();
            EntityManager em = JPA.createEntityManager();

            Item item = em.find(Item.class, ITEM_ID);
            assertEquals(item.getImages().size(), 3);

            tx.commit();
            em.close();
        } finally {
            TM.rollback();
        }
    }

}
