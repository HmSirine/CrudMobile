/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.travolta.entites;

/**
 *
 * @author hp
 */
public class Reclamation {
    
    private int id;
    private int ref;
    private String objet;
    private String MSG;

    public Reclamation(int id, int ref, String objet, String MSG) {
        this.id = id;
        this.ref = ref;
        this.objet = objet;
        this.MSG = MSG;
    }

    public Reclamation(int id, String objet, String MSG) {
        this.id = id;
        this.objet = objet;
        this.MSG = MSG;
    }

    public Reclamation() {
    }

   

    public Reclamation(String objet, String MSG) {
        this.objet = objet;
        this.MSG = MSG;
    }

    public int getId() {
        return id;
    }

    public int getRef() {
        return ref;
    }

    public String getObjet() {
        return objet;
    }

    public String getMSG() {
        return MSG;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRef(int ref) {
        this.ref = ref;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public void setMSG(String MSG) {
        this.MSG = MSG;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", ref=" + ref + ", objet=" + objet + ", MSG=" + MSG + '}';
    }
    
    
    
    
    
}
