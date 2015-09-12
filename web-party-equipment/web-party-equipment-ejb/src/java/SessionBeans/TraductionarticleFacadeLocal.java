/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBeans;

import EntityBeans.Traductionarticle;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Pierre
 */
@Local
public interface TraductionarticleFacadeLocal {

    void create(Traductionarticle traductionarticle);

    void edit(Traductionarticle traductionarticle);

    void remove(Traductionarticle traductionarticle);

    Traductionarticle find(Object id);

    List<Traductionarticle> findAll();

    List<Traductionarticle> findRange(int[] range);

    int count();
    
}
