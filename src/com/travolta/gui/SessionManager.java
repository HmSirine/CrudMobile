/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.travolta.gui;

import com.codename1.io.Preferences;

/**
 *
 * @author hp
 */
public class SessionManager {
    
    public static Preferences pref ; // 3ibara memoire sghira nsajlo fiha data 
    
    
    
    // hethom données ta3 user lyt7b tsajlhom fi session  ba3d login 
    private static int id ; 
    private static String nom ; 
    private static String prenom; 
    private static String adresse ;
    private static String mdp;

    public static Preferences getPref() {
        return pref;
    }

    public static void setPref(Preferences pref) {
        SessionManager.pref = pref;
    }

    public static int getId() {
        return pref.get("id",id);// kif nheb njib id user connecté apres njibha men pref 
    }

    public static void setId(int id) {
        pref.set("id",id);//nsajl id user connecté  w na3tiha identifiant "id";
    }

    public static String getNom() {
        return pref.get("nom",nom);
    }

    public static void setNom(String nom) {
         pref.set("nom",nom);
    }

    public static String getAdresse() {
        return pref.get("adresse",adresse);
    }

    public static void setAdresse(String adresse) {
         pref.set("adresse",adresse);
    }

    public static String getMdp() {
        return pref.get("mdp",mdp);
    }

    public static void setMdp(String mdp) {
         pref.set("mdp",mdp);
    }

   
    
    
    
    
    
}
