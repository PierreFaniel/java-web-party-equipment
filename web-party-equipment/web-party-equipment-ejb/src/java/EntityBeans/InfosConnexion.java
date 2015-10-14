package EntityBeans;

import java.io.Serializable;

public class InfosConnexion implements Serializable {
    
    private boolean connecte;
    private String email;
    private String motDePasse;

    public InfosConnexion() {
        setConnecte(false);
    }

    public boolean getConnecte() {
        return connecte;
    }

    public void setConnecte(boolean estConnecte) {
        this.connecte = estConnecte;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
}
