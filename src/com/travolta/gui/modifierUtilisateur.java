/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.travolta.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.travolta.entites.Utilisateur;
import com.travolta.services.ServiceUtilisateur;

/**
 *
 * @author hp
 */
public class modifierUtilisateur extends Form{

    public modifierUtilisateur(Form previous, Utilisateur utilisateur,Form UtilisateurForm) {
        setTitle("modifier utilisateur");
        setLayout(BoxLayout.y());
        TextField tfNom = new TextField(utilisateur.getNom(), "Nom");            
        TextField tfPrenom = new TextField(utilisateur.getPrenom(), "prenom");
        TextField tfAdresse = new TextField(utilisateur.getAdresse(), "adresse");
        Button btnValider = new Button("modifier utilisateur");
       btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfNom.getText().length()==0) ||(tfPrenom.getText().length()==0)||(tfAdresse.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try{
                        utilisateur.setId(utilisateur.getId());
                        utilisateur.setNom(tfNom.getText());
                        utilisateur.setPrenom(tfPrenom.getText());
                        utilisateur.setAdresse(tfAdresse.getText());
                        if ( new ServiceUtilisateur().getInstance().modifierUser(utilisateur))
                            Dialog.show("Success", "Connection accepted", new Command("Ok"));
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    }catch(Exception e){
                        Dialog.show("ERROR", "name and type must be string", new Command("Ok"));
                    }
                    
                }
                
                
            }
        });
        
        addAll(tfNom,tfPrenom,tfAdresse,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
            
    
}

