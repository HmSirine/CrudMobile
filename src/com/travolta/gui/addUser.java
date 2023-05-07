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
import com.travolta.services.ServiceUtilisateur;
import com.travolta.entites.Utilisateur;
import com.travolta.utils.Statics;

/**
 *
 * @author hp
 */
public class addUser extends Form {

   public addUser(Form previous) {
       
     setTitle("Add a new utilisateur");
        setLayout(BoxLayout.y());
        
        TextField tfNom = new TextField("","Nom");
        TextField tfPrenom = new TextField("","Prenom");
         TextField tfAdresse = new TextField("","Adresse");
          TextField tfMdp = new TextField("","mot de passe");
          TextField tfRole = new TextField("","Role");
        Button btnValider = new Button("Ajouter Utilisateur");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfNom.getText().length()==0) ||(tfPrenom.getText().length()==0) ||(tfAdresse.getText().length()==0) ||(tfMdp.getText().length()==0) ||(tfRole.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try{
                        Utilisateur u = new Utilisateur(tfNom.getText(),tfPrenom.getText(),tfAdresse.getText(),tfMdp.getText(), tfRole.getText());
                        if ( new ServiceUtilisateur().addUtilisateur(u))
                            Dialog.show("Success", "Connection accepted", new Command("Ok"));
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    }catch(Exception e){
                        Dialog.show("ERROR", "name and type must be string", new Command("Ok"));
                    }
                    
                }
                
                
            }
        });
        
        addAll(tfNom,tfPrenom,tfAdresse,tfMdp,tfRole,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
}
