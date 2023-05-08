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
import com.codename1.ui.events.ActionListener;
import com.travolta.entites.Reclamation;
import com.travolta.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author hp
 */
public class ServiceReclamation {

    public ArrayList<Reclamation> reclamations;

    public static ServiceReclamation instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceReclamation() {
        req = new ConnectionRequest();
    }

    public static ServiceReclamation getInstance() {
        if (instance == null) {
            instance = new ServiceReclamation();
        }
        return instance;
    }

    public boolean addReclamation(Reclamation r) {

        int ref = r.getRef();
        String objet = r.getObjet();
        String MSG = r.getMSG();

        //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
        String url = Statics.BASE_URL + "/reclamationJson/AddReclamationJson/new?objet=" + r.getObjet() + "&MSG=" + r.getMSG();

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

    public ArrayList<Reclamation> parseReclamations(String jsonText) {
        try {
            reclamations = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> reclamationsListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) reclamationsListJson.get("root");
            for (Map<String, Object> obj : list) {
                Reclamation r = new Reclamation();
                float id = Float.parseFloat(obj.get("id").toString());
                r.setId((int) id);

                if (obj.get("objet") == null) {
                    r.setObjet("null");
                } else {
                    r.setObjet(obj.get("objet").toString());
                }
                if (obj.get("msg") == null) {
                    r.setMSG("null");
                } else {
                    r.setMSG(obj.get("msg").toString());
                }

                reclamations.add(r);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return reclamations;
    }

    public ArrayList<Reclamation> getAllReclamation() {
        String url = Statics.BASE_URL + "/reclamationJson";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                reclamations = parseReclamations(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return reclamations;
    }

    //Delete 
    public boolean deleteReclamation(int id) {

        String url = Statics.BASE_URL + "/reclamationJson/deleteReclamationJson/" + id;
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

//     //Update 
//    public boolean modifierReclamation(Reclamation reclamation) {
//        String url = Statics.BASE_URL +"/reclamationJson/updateUtilisateurJson/"+reclamation.getId()+"?objet="+reclamation.getObjet()+"&msg="+reclamation.getMSG();
//        req.setUrl(url);
//        
//        req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                resultOK = req.getResponseCode() == 200 ;  // Code response Http 200 ok
//                req.removeResponseListener(this);
//            }
//        });
//        
//    NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
//    return resultOK;
//        
//    }
}
