package com.example.demo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class ClientController {
    static ArrayList<Parcare> parcari = new ArrayList<>();
    static int contorRezervare = 1;

    public static void func()throws Exception {

        getConnection();

        Parcare parcare1 = new Parcare(1, "Mihai Eminescu 21");
        Parcare parcare2 = new Parcare(2, "George Cosbuc 69");
        Parcare parcare3 = new Parcare(3, "Nicolae Balcescu 43");

        ArrayList<Loc> locuri = getLocuri();
        for (int i = 0 ; i < locuri.size(); i++) {
            if(locuri.get(i).getIdParcare() == 1){
                parcare1.locuri.add(locuri.get(i));
            }
            if(locuri.get(i).getIdParcare() == 2){
                parcare2.locuri.add(locuri.get(i));
            }
            if(locuri.get(i).getIdParcare() == 3){
                parcare3.locuri.add(locuri.get(i));
            }
        }

        for(int i = 0 ; i < parcare1.locuri.size() ; i++){
            int numar = NumarLoc(i);
            parcare1.locuri.get(i).setNumar(numar);
        }
        Collections.sort(parcare1.locuri, new Comparator<Loc>(){
            public int compare(Loc loc1, Loc loc2){
                return loc1.getNumar() - loc2.getNumar();
            }});

        parcari.add(parcare1);
        parcari.add(parcare2);
        parcari.add(parcare3);
    }

    public static int NumarLoc(int contorLocuriOcupate) {
        int contor = contorLocuriOcupate / 10;
        if ((contor + 1) % 2 == 0)
            contor = (-contor - 1) / 2;
        else
            contor = (contor + 1) / 2;
        return (5 + contor) * 10 + contorLocuriOcupate % 10;
    }

    public static void updateLocuriOcupat(String valid, String id1, String id2) throws Exception{
        try{
            Connection conn = getConnection();
            PreparedStatement update = conn.prepareStatement("UPDATE locuri set ocupat = '" + valid + "' where loc_id = " + id1 + " and parcare_id = " + id2);
            update.executeUpdate();

        } catch(Exception e) {
            System.out.println(e);
        }
    }
    public static void updateRezervareTimp(String timp, String id) throws Exception{
        try{
            Connection conn = getConnection();
            PreparedStatement update = conn.prepareStatement("UPDATE rezervare set timp = '" + timp + "' where rezervare_id = " + id);
            update.executeUpdate();

        } catch(Exception e) {
            System.out.println(e);
        }
    }
    public static void updateRezervareNumarMasina(String numarMasina, String id) throws Exception{
        try{
            Connection conn = getConnection();
            PreparedStatement update = conn.prepareStatement("UPDATE rezervare set numar_masina = '" + numarMasina + "' where rezervare_id = " + id);
            update.executeUpdate();

        } catch(Exception e) {
            System.out.println(e);
        }
    }
    public static void updateRezervareTimpNumarMasina(String timp, String numarMasina, String id) throws Exception{
        try{
            Connection conn = getConnection();
            PreparedStatement update = conn.prepareStatement("UPDATE rezervare set timp = '" + timp + "' , numar_masina = '" + numarMasina + "' where rezervare_id = " + id);
            update.executeUpdate();

        } catch(Exception e) {
            System.out.println(e);
        }
    }


    public static void getParcari() throws Exception{
        try{
            Connection conn = getConnection();
            PreparedStatement posted = conn.prepareStatement("SELECT * FROM parcari");
            ResultSet result = posted.executeQuery();
            ArrayList<Parcare> parcari = new ArrayList<>();
            while(result.next()){
                System.out.print("parcare_id: " + result.getString("parcare_id") + " ");
                System.out.print("nume: " + result.getString("nume") + " ");
                System.out.print("adresa: " + result.getString("adresa") + " ");
                System.out.println("nr locuri: " + result.getString("numar_locuri"));
                //parcari.add()
            }
        } catch(Exception e) {
            System.out.println(e);
        }
    }
    public static ArrayList<Loc> getLocuri() throws Exception{
        try{
            Connection conn = getConnection();
            PreparedStatement posted = conn.prepareStatement("SELECT * FROM locuri");
            ResultSet result = posted.executeQuery();
            ArrayList<Loc> locuri = new ArrayList<>();
            while(result.next()){
                int id1 = Integer.parseInt(result.getString("loc_id"));
                int id2 = Integer.parseInt(result.getString("parcare_id"));
                boolean bool = Boolean.parseBoolean(result.getString("ocupat"));
                locuri.add(new Loc(id1, id2, bool));
                //System.out.print(result.getString("loc_id") + " ");
                //System.out.print(" din parcarea " + result.getString("parcare_id") + " ");
                //System.out.println(result.getString("ocupat"));
            }
            return locuri;
        } catch(Exception e) {
            System.out.println(e);
        }
        return null;
    }
    public static void getRezervare() throws Exception{
        try{
            Connection conn = getConnection();
            PreparedStatement posted = conn.prepareStatement("SELECT * FROM rezervare");
            ResultSet result = posted.executeQuery();
            while(result.next()){
                System.out.print("Rezervarea numarul " + result.getString("rezervare_id") + " ");
                System.out.print("pentru locul " + result.getString("loc_id") + " ");
                System.out.print("la data " + result.getString("time") + " ");
                System.out.println("pentru masina cu numarul " + result.getString("numar_masina"));
            }
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    public static void insertLocuri(String id1, String id2, String valid) throws Exception{
        final String loc_id = id1;
        final String parcare_id = id2;
        final String ocupat = valid;
        try{
            Connection conn = getConnection();
            PreparedStatement posted = conn.prepareStatement("INSERT INTO locuri (loc_id, parcare_id, ocupat) VALUES ('"+loc_id+"','"+parcare_id+"', '"+ocupat+"')");
            posted.executeUpdate();

        }catch(Exception e) {
            System.out.println(e);
        }
    }

    public static void insertRezervare(String id1, String id2, String id3, String timp, String numarMasina, String codMasina) throws Exception{
        try{
            Connection conn = getConnection();
            PreparedStatement posted = conn.prepareStatement("INSERT INTO rezervare (rezervare_id, loc_id, parcare_id, timp, numar_masina, cod_masina) VALUES ('"+id1+"','"+id2+"','"+id3+"','"+timp+"','"+numarMasina+"','"+codMasina+"')");
            posted.executeUpdate();

        }catch(Exception e) {
            System.out.println(e);
        }
    }

    public static void deleteLocuri(String id1, String id2) throws Exception{
        try{
            Connection conn = getConnection();
            PreparedStatement delete = conn.prepareStatement("DELETE from locuri where loc_id = " + id1 + " and parcare_id = " + id2);
            delete.executeUpdate();

        } catch(Exception e) {
            System.out.println(e);
        }
    }

    public static void deleteRezervare(String id1, String id2, String id3) throws Exception{
        try{
            Connection conn = getConnection();
            PreparedStatement delete = conn.prepareStatement("DELETE from rezervare where rezervare_id = " + id1 + " and loc_id = " + id2 + " and parcare_id =" + id3);
            delete.executeUpdate();

        } catch(Exception e) {
            System.out.println(e);
        }
    }





    public static Connection getConnection() throws Exception{
        try {
            String name = "com.mysql.cj.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/database_name";
            String username = "vlad";
            String password = "";
            Class.forName(name);
            Connection conn = DriverManager.getConnection(url, username, password);
            //System.out.println("connected");
            return conn;
        }catch(Exception e) {
            System.out.println(e);

        }
        return null;
    }

    @RequestMapping(value = "/home.html", method = RequestMethod.GET)
    public static ModelAndView getAdmissionForm() throws Exception{
        func();
        ModelAndView model = new ModelAndView("AdmissionForm");
        return model;
    }

    @RequestMapping(value = "/rezervare.html", method = RequestMethod.POST)
    public static ModelAndView submitAdmissionForm(@RequestParam("numar") String numar, @RequestParam(name = "msg", required = false) String msg) throws Exception {
        Masina car1 = new Masina(true, false, numar);
        String codMasina = car1.generareCodMasina(true, numar);
        if(car1.getButonRezervare()){
            boolean gasit = false;
            int i = 0;
            int loc = 0;
            while(!gasit){
                if(parcari.get(0).locuri.get(i).isOcupat()){
                    i++;
                }
                else{
                    gasit = true;
                    parcari.get(0).locuri.get(i).setOcupat(true);
                    loc = parcari.get(0).locuri.get(i).getId();
                }
            }

            SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date now = new Date();
            String date = sdfDate.format(now);
            insertRezervare("" + contorRezervare, "" + loc, "1", date, numar, codMasina);
            contorRezervare++;
        }

        ModelAndView model = new ModelAndView("AdmissionSuccess");

        model.addObject("msg",codMasina);
        return model;
    }

    @RequestMapping(value = "/locuri_libere.html", method = RequestMethod.POST)
    public ModelAndView submitAdmissionForm2(@RequestParam(name = "msg", required = false) String msg) {

        ModelAndView model = new ModelAndView("AdmissionSuccess2");
        int locuriLibere = parcari.get(0).locuri.size() - contorRezervare + 1;
        model.addObject("msg","" + locuriLibere);
        return model;
    }
}