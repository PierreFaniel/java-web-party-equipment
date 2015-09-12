/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

/**
 *
 * @author Pierre
 */
public class TexteInfo {
    private String libelle;
    private String descriptif;

    public TexteInfo() {
    }

    public TexteInfo(String libelle, String descriptif) {
        this.libelle = libelle;
        this.descriptif = descriptif;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescriptif() {
        return descriptif;
    }

    public void setDescriptif(String Descriptif) {
        this.descriptif = Descriptif;
    }
}
