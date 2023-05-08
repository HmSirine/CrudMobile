/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.travolta.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.travolta.entites.Utilisateur;
import com.travolta.services.ServiceUtilisateur;
import java.security.MessageDigest;

/**
 *
 * @author hp
 */

       
     public class SignUpForm extends Form {
   

    public SignUpForm( ) {
        setTitle("Sign Up");
        setLayout(BoxLayout.y());

       TextField tfNom = new TextField("","Nom");
        TextField tfPrenom = new TextField("","Prenom");
         TextField tfAdresse = new TextField("","Adresse");
          TextField tfMdp = new TextField("","mot de passe");
            tfMdp.setConstraint(TextField.PASSWORD);
       
        Button btnValider = new Button("S'inscrire");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfNom.getText().length()==0) ||(tfPrenom.getText().length()==0) ||(tfAdresse.getText().length()==0) ||(tfMdp.getText().length()==0) )
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try{
                        
                         // Hashing the password
                        String password = tfMdp.getText();
                        MessageDigest md = MessageDigest.getInstance("SHA-256");
                        byte[] hashedPassword = md.digest(password.getBytes("UTF-8"));
                        String hashedPasswordString = bytesToHex(hashedPassword);
                        
                        Utilisateur u = new Utilisateur(tfNom.getText(),tfPrenom.getText(),tfAdresse.getText(),hashedPasswordString);
                        u.setRole("ROLE_USER");
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
        
        addAll(tfNom,tfPrenom,tfAdresse,tfMdp,btnValider);
       // getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
       
                
    }
    
     private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();

    /**
     * Helper method to convert bytes to hex string
     */
    private static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }
}
     

    

   
