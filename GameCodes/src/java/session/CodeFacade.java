
package session;

import entity.Code;
import entity.Product;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Stateless
public class CodeFacade extends AbstractFacade<Code> {

    @PersistenceContext(unitName = "GameCodePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CodeFacade() {
        super(Code.class);
    }

    public Code findFreeCodeByProduct(Product product) {
    try {
        return em.createQuery("SELECT c FROM Code c WHERE c.game = :product AND c.purchase = 'no'", Code.class)
                 .setParameter("product", product)
                 .getSingleResult();
    } catch (Exception e) {
        return null;
    }
}
    

}



