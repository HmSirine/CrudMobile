/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.travolta.gui;

import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.travolta.entites.Utilisateur;
import com.travolta.services.ServiceReclamation;
import java.util.ArrayList;
import com.travolta.entites.Reclamation;

/**
 *
 * @author hp
 */
public class ReclamationForm extends Form {

    public ArrayList<Reclamation> reclamations;
    Form current;

    public ReclamationForm(Form previous) {
        setTitle("Liste des reclamations");
        setLayout(BoxLayout.y());

//          Button btnUtilisateur = new Button("Ajouter ");
//        
//        btnUtilisateur.addActionListener(e-> new addUser(this).show());
//        addAll(btnUtilisateur);
//        
        ArrayList<Reclamation> reclamations = ServiceReclamation.getInstance().getAllReclamation();
        Container list = new Container(BoxLayout.y());
        list.setScrollableY(true);
        for (Reclamation reclamation : reclamations) {

           MultiButton sp = new MultiButton();
            sp.setTextLine1("Objet : " + reclamation.getObjet());
            sp.setTextLine2("Message : " + reclamation.getMSG());

            list.add(sp);

            sp.addActionListener((evt) -> {
                if (Dialog.show("Confirmation", "Voulez-vous vraiment supprimer cette réclamation ?", "Oui", "Non")) {
                    if (ServiceReclamation.getInstance().deleteReclamation(reclamation.getId())) {
                        Dialog.show("Success", "La réclamation a été supprimée avec succès", new Command("OK"));
                        previous.showBack();
                    } else {
                        Dialog.show("Error", "Une erreur est survenue lors de la suppression de la réclamation", new Command("OK"));
                    }
                }
            });

        }
        this.add(list);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }

}


