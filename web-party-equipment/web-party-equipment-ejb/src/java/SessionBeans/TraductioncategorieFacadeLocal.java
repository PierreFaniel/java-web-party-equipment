/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBeans;

import EntityBeans.Traductioncategorie;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Pierre
 */
@Local
public interface TraductioncategorieFacadeLocal {

    void create(Traductioncategorie traductioncategorie);

    void edit(Traductioncategorie traductioncategorie);

    void remove(Traductioncategorie traductioncategorie);

    Traductioncategorie find(Object id);

    List<Traductioncategorie> findAll();

    List<Traductioncategorie> findRange(int[] range);

    int count();
    
}
