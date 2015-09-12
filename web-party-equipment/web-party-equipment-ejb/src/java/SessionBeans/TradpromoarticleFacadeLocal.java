/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBeans;

import EntityBeans.Tradpromoarticle;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Pierre
 */
@Local
public interface TradpromoarticleFacadeLocal {

    void create(Tradpromoarticle tradpromoarticle);

    void edit(Tradpromoarticle tradpromoarticle);

    void remove(Tradpromoarticle tradpromoarticle);

    Tradpromoarticle find(Object id);

    List<Tradpromoarticle> findAll();

    List<Tradpromoarticle> findRange(int[] range);

    int count();
    
}
