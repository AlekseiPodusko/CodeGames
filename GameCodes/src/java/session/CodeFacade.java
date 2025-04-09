
package session;

import entity.Code;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
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

    public Code findByProductId(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
