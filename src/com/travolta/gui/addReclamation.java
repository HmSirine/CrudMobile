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
import com.travolta.entites.Reclamation;
import com.travolta.services.ServiceReclamation;

/**
 *
 * @author hp
 */
public class addReclamation extends Form {

   public addReclamation(Form previous) {
       
     setTitle("Add a new reclamation");
        setLayout(BoxLayout.y());
        
        TextField tfObjet = new TextField("","Objet");
        TextField tfMessage = new TextField("","Message");
         
        Button btnValider = new Button("Ajouter reclamation");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfObjet.getText().length()==0) ||(tfMessage.getText().length()==0) )
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try{
                        Reclamation r = new Reclamation(tfObjet.getText(),tfMessage.getText());
                        if ( new ServiceReclamation().addReclamation(r))
                            Dialog.show("Success", "Connection accepted", new Command("Ok"));
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    }catch(Exception e){
                        Dialog.show("ERROR", "name and type must be string", new Command("Ok"));
                    }
                    
                }
                
                
            }
        });
        
        addAll(tfObjet,tfMessage,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
}
