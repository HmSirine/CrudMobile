/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.travolta.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.travolta.utils.Statics;
import com.travolta.entites.Utilisateur;
import com.travolta.gui.AdminForm;
import com.travolta.gui.SessionManager;




/**
 *
 * @author hp
 */
public class ServiceUtilisateur {
    
     public ArrayList<Utilisateur> utilisateurs;

    public static ServiceUtilisateur instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceUtilisateur() {
        req = new ConnectionRequest();
    }

    public static ServiceUtilisateur getInstance() {
        if (instance == null) {
            instance = new ServiceUtilisateur();
        }
        return instance;
    }

    public boolean addUtilisateur(Utilisateur u) {

        String nom = u.getNom();
        String prenom =  u.getPrenom();
        String adresse = u.getAdresse();
        String mdp = u.getMdp();
        String role = u.getRole();
        

        //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
        String url = Statics.BASE_URL + "/UtilisateurJSON/AddUtilisateurJson/new?nom=" +u.getNom() + "&prenom=" +u.getPrenom() + "&adresse=" +u.getAdresse() + "&mdp=" + u.getMdp() + "&role=" + u.getRole();

        req.setUrl(url);
        req.setPost(false);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public ArrayList<Utilisateur> parseUtilisateurs(String jsonText) {
        try {
            utilisateurs = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> utilisateursListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) utilisateursListJson.get("root");
            for (Map<String, Object> obj : list) {
                Utilisateur u = new Utilisateur();
                float id = Float.parseFloat(obj.get("id").toString());
                u.setId((int) id);
               
                if (obj.get("nom") == null) {
                    u.setNom("null");
                } else {
                    u.setNom(obj.get("nom").toString());
                }
                if (obj.get("prenom") == null) {
                    u.setPrenom("null");
                } else {
                    u.setPrenom(obj.get("prenom").toString());
                }
                if (obj.get("adresse") == null) {
                    u.setAdresse("null");
                } else {
                    u.setAdresse(obj.get("adresse").toString());
                }
                utilisateurs.add(u);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return utilisateurs;
    }

    public ArrayList<Utilisateur> getAllUtilisateurs() {
        String url = Statics.BASE_URL + "/UtilisateurJSON";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                utilisateurs = parseUtilisateurs(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return utilisateurs;
    }
    
    
     //Delete 
    public boolean deleteUser(int id ) {
        
        
       String url = Statics.BASE_URL +"/UtilisateurJSON/deleteUtilisateurJson/"+ id ;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    
    }
    
    
    
     //Update 
    public boolean modifierUser(Utilisateur utilisateur) {
        String url = Statics.BASE_URL +"/UtilisateurJSON/updateUtilisateurJson/"+utilisateur.getId()+"?nom="+utilisateur.getNom()+"&Prenom="+utilisateur.getPrenom()+"&Adresse="+utilisateur.getAdresse();
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200 ;  // Code response Http 200 ok
                req.removeResponseListener(this);
            }
        });
        
    NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
    return resultOK;
        
    }
    
    
       
        
    
    
//    //Signup
//    public void signup(TextField nom,TextField prenom,TextField adresse,TextField mdp, ComboBox<String> role  ) {
//        
//     
//        
//        String url = Statics.BASE_URL+"/utilisateur/signup?username="+nom.getText().toString()+"&prenom="+prenom.getText().toString()+"&adresse="+adresse.getText().toString()+
//                "&mdp="+mdp.getText().toString()+"&roles="+role.getSelectedItem().toString();
//        
//        req.setUrl(url);
//       
//        //Control saisi
//        if(nom.getText().equals(" ") && prenom.getText().equals(" ") && adresse.getText().equals(" ") && mdp.getText().equals(" ")) {
//            
//            Dialog.show("Erreur","Veuillez remplir les champs","OK",null);
//            
//        }
//        
//        //hethi wa9t tsir execution ta3 url 
//        req.addResponseListener((e)-> {
//         
//            //njib data ly7atithom fi form 
//            byte[]data = (byte[]) e.getMetaData();//lazm awl 7aja n7athrhom ke meta data ya3ni na5o id ta3 kol textField 
//            String responseData = new String(data);//ba3dika na5o content 
//            
//            System.out.println("data ===>"+responseData);
//        }
//        );
//        
//        
//        //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
//        NetworkManager.getInstance().addToQueueAndWait(req);
//        
//            
//        
//    }
//    
//    
    //SignIn
    
    public void signin(TextField adresse,TextField mdp, Resources rs ) {
        
        
        String url = Statics.BASE_URL+"/utilisateur/signin?adresse="+adresse.getText().toString()+"&mdp="+mdp.getText().toString();
        req = new ConnectionRequest(url, false); //false ya3ni url mazlt matba3thtich lel server
        req.setUrl(url);
        
        req.addResponseListener((e) ->{
            
            JSONParser j = new JSONParser();
            
            String json = new String(req.getResponseData()) + "";
            
            
            try {
            
            if(json.equals("failed")) {
                Dialog.show("Echec d'authentification","Username ou mot de passe éronné","OK",null);
            }
            else {
                System.out.println("data =="+json);
                
                Map<String,Object> user = j.parseJSON(new CharArrayReader(json.toCharArray()));
                
                
             
                //Session 
                float id = Float.parseFloat(user.get("id").toString());
                SessionManager.setId((int)id);//jibt id ta3 user ly3ml login w sajltha fi session ta3i
                
                SessionManager.setMdp(user.get("mdp").toString());
                SessionManager.setAdresse(user.get("adresse").toString());
                
                
                
               
                
                
                if(user.size() >0 ) // l9a user
                   // new ListReclamationForm(rs).show();//yemchi lel list reclamation
                    new AdminForm(rs).show();
                    
                    }
            
            }catch(Exception ex) {
                ex.printStackTrace();
            }
            
            
            
        });
    
         //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    
    
}
