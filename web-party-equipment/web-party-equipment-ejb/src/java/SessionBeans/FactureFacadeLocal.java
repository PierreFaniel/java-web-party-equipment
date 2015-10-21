/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBeans;

import EntityBeans.Facture;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.Local;

/**
 *
 * @author Pierre
 */
@Local
public interface FactureFacadeLocal {

    void create(Facture facture);

    void edit(Facture facture);

    void remove(Facture facture);

    Facture find(Object id);

    List<Facture> findAll();

    List<Facture> findRange(int[] range);

    int count();
    
    public ArrayList<Facture> findParClient(Integer idClient);
    
    void create(ResourceBundle bundle, Facture facture);
    
    public Integer getNewId();
    
    public Facture getFactureById(Integer id);
    
}
