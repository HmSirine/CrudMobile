/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.travolta.gui;

import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import java.util.ArrayList;
import com.travolta.services.ServiceUtilisateur;
import com.travolta.entites.Utilisateur;
import com.travolta.utils.Statics;
import static java.util.Collections.list;

/**
 *
 * @author hp
 */
public class UtilisateurForm extends Form {

   
       
    public ArrayList<Utilisateur> utilisateurs;
    Form current;

    public UtilisateurForm(Form previous) {
       setTitle("Liste des utilisateurs"); 
         setLayout(BoxLayout.y());
         
          Button btnUtilisateur = new Button("Ajouter ");
        
        btnUtilisateur.addActionListener(e-> new addUser(this).show());
        addAll(btnUtilisateur);
        
        ArrayList<Utilisateur> utilisateurs = ServiceUtilisateur.getInstance().getAllUtilisateurs();
        Container list = new Container(BoxLayout.y());
         list.setScrollableY(true);
        for (Utilisateur utilisateur : utilisateurs) {
            
              
             MultiButton sp = new MultiButton(utilisateur.getNom());
              sp.setTextLine1("Nom : "+utilisateur.getNom());
              sp.setTextLine2("prenom : "+utilisateur.getPrenom());
              sp.setTextLine2("adresse : "+utilisateur.getAdresse());
                     list.add(sp);
                     
                     sp.addActionListener((evt) -> {
                           if (Dialog.show("Confirmation", "Que voulez vous faire ?", "Supprimer", "Modifier")) {
                                            
                                                if(ServiceUtilisateur.getInstance().deleteUser(utilisateur.getId())){
                                                    {
                                                       Dialog.show("Success","L'utilisateur "+utilisateur.getNom()+" a été supprimé avec succées",new Command("OK"));
                                                       previous.showBack();
                                                    }
                                        }
                                    }
                                        else{ 
                                               
                                                 new modifierUtilisateur(previous,utilisateur,this).show();
                                        }
                     });
        }
        this.add(list); 
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }
    

}
