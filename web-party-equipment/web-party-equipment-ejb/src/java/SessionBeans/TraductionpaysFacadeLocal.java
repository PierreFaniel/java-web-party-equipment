/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBeans;

import EntityBeans.Traductionpays;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Pierre
 */
@Local
public interface TraductionpaysFacadeLocal {

    void create(Traductionpays traductionpays);

    void edit(Traductionpays traductionpays);

    void remove(Traductionpays traductionpays);

    Traductionpays find(Object id);

    List<Traductionpays> findAll();

    List<Traductionpays> findRange(int[] range);

    int count();
    
}
