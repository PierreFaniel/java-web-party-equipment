/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBeans;

import EntityBeans.Client;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.Local;

/**
 *
 * @author Pierre
 */
@Local
public interface ClientFacadeLocal {

    void create(Client client);

    void edit(Client client);

    void remove(Client client);

    Client find(Object id);

    List<Client> findAll();

    List<Client> findRange(int[] range);

    int count();
    
    void create(Client client, ResourceBundle bundle) throws Exception;
    
    Client findByEmail(String email);
    
}
