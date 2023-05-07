/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.travolta.entites;

/**
 *
 * @author hp
 */
public class Utilisateur {
    
     private int id;
    private String nom;
    private String prenom;
    private String adresse;
    private String mdp;
    private String role;
    private String resetToken;

    public Utilisateur() {
    }

    public Utilisateur(int id) {
        this.id = id;
    }
    
    

    public Utilisateur(String nom, String prenom, String adresse, String mdp, String role) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.mdp = mdp;
        this.role = role;
    }
    
    

    public Utilisateur(int id, String nom, String prenom, String adresse, String mdp, String role, String resetToken) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.mdp = mdp;
        this.role = role;
        this.resetToken = resetToken;
    }

    public Utilisateur(String nom, String prenom, String adresse, String mdp, String role, String resetToken) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.mdp = mdp;
        this.role = role;
        this.resetToken = resetToken;
    }

    public Utilisateur(String adresse, String mdp) {
        this.adresse = adresse;
        this.mdp = mdp;
    }

    public Utilisateur(String nom, String prenom, String adresse, String mdp) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.mdp = mdp;
    }

    public Utilisateur(String nom, String prenom, String adresse) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
    }
    
    

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getMdp() {
        return mdp;
    }

    public String getRole() {
        return role;
    }

    public String getResetToken() {
        return resetToken;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setResetToken(String resetToken) {
        this.resetToken = resetToken;
    }

    @Override
    public String toString() {
        return "Utilisateur{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", adresse=" + adresse + ", mdp=" + mdp + ", role=" + role + ", resetToken=" + resetToken + '}';
    }
    
    
    
}
