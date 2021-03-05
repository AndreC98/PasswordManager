package Manager;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

import Object.ProgramObject;


public class Manager {
    private ArrayList<ProgramObject> allObjects = new ArrayList<>();
    private ProgramObject object;
    private FileOutputStream fileOut;
    private ObjectOutputStream out;
    private FileInputStream fileIn;
    private ObjectInputStream in;

    
    public Manager(){
        int option = 0;

        while(option != 5) {
            System.out.println("----------------");
            System.out.println("Password Manager");
            System.out.println("----------------");
            System.out.println("Select an option below");
            System.out.println("1. Add new credentials");
            System.out.println("2. View previous credentials");
            System.out.println("3. Edit previous credentials");
            System.out.println("4. Delete previous credntials");
            System.out.println("5. Exit");
            
            Scanner keyboard = new Scanner(System.in);
            option = keyboard.nextInt();

            if(option == 1) {
                keyboard.nextLine();
                add(keyboard);
            }else if(option == 2) {
                view(keyboard);
            }else if (option == 3) {
                edit();
            }else if (option == 4) {
                delete();
            }else if(option == 5){
                try{
                    out.close();
                    fileOut.close();
                    in.close();
                    fileIn.close();
                    keyboard.close();
                } catch(IOException | NullPointerException e) {}
                
            }
        }
    }
    
    private void add(Scanner keyboard){
        String application;
        String username;
        String password;

        System.out.println("Program or Website: ");
        application = keyboard.nextLine();
        System.out.println("Username for " + application);
        username = keyboard.nextLine();
        System.out.println("Password for " + application);
        password = keyboard.nextLine();
        
        if(application.length() > 0 && password.length() > 0 && username.length() > 0){
            allObjects = load();
            object = new ProgramObject(application, username, password);
            allObjects.add(object);
            try{
                fileOut = new FileOutputStream("res/saved.bin");
                out = new ObjectOutputStream(fileOut);
                for(int i = 0; i < allObjects.size(); i++){
                    this.out.writeObject(allObjects.get(i));
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Invalid credentials");
            System.out.println();
        }
        allObjects.clear();
    }

    private void view(Scanner keyboard){

        System.out.println("Select an option below");
        System.out.println("1. View all");
        System.out.println("2. Search");
        int option = keyboard.nextInt();
        keyboard.nextLine();

        if(option == 1) {
            allObjects = load();
            if(allObjects.size() > 0) {
                for(int i = 0; i < allObjects.size(); i++) {
                    System.out.println(allObjects.get(i));
                }
            }
        } else if(option == 2) {
            System.out.println("Type in the website youd like to see: ");
            String website = keyboard.nextLine();
            allObjects = load();

            if(website.length() > 0 && allObjects.size() > 0){
                for(int i = 0; i < allObjects.size(); i++) {
                    if(allObjects.get(i).getWebsite().equals(website)) {
                        System.out.println(allObjects.get(i));
                    }
                }
            }

        }
        allObjects.clear();
    }

    private void edit(){

    }

    private void delete(){

    }

    private ArrayList<ProgramObject> load(){
        try{
            boolean loop = true;
            try{
                fileIn = new FileInputStream("res/saved.bin");
                in = new ObjectInputStream(fileIn);
                while(loop) {
                    object = (ProgramObject) in.readObject();
                    if(object != null) {
                        allObjects.add(object);
                    } else {
                        loop = false;
                    }
            }
            } catch(EOFException e){}
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allObjects;
    }
}
