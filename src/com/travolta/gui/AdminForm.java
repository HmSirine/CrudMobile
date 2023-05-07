/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.travolta.gui;

import static com.codename1.push.PushContent.setTitle;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;

import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author hp
 */
public class AdminForm extends Form {
    
     public AdminForm() {
        
        setTitle("Home");
        setLayout(BoxLayout.y());
        
        
        add(new Label("Choose an option"));
        Button btnUtilisateur = new Button("Utilisateur");
        Button btnReclamation = new Button("Reclamation");
        
        btnUtilisateur.addActionListener(e-> new UtilisateurForm(this).show());
        btnReclamation.addActionListener(e-> new ReclamationForm(this).show());
        addAll(btnUtilisateur,btnReclamation);
        
        
    }

    public AdminForm(Resources rs) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
