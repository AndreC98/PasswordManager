package Manager;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

import Object.ProgramObject;


public class Manager {
    private ArrayList<ProgramObject> allObjects = new ArrayList();
    private ProgramObject object;

    
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
                keyboard.close();
            }
        }
    }
    
    private void add(Scanner keyboard){
        String application;
        String password;

        System.out.println("Program or Website: ");
        application = keyboard.nextLine();
        System.out.println("Password: ");
        password = keyboard.nextLine();
        
        if(application.length() > 0 && password.length() > 0){
            object = new ProgramObject(application, password);
            try{
            FileOutputStream fileOut = new FileOutputStream("res/saved.bin");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(object);
            out.close();
            fileOut.close();
            } catch(Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Invalid credentials");
            System.out.println();
        }
    }

    private void view(Scanner keyboard){

        System.out.println("Select an option below");
        System.out.println("1. View all");
        System.out.println("2. Search");
        int option = keyboard.nextInt();

        if(option == 1) {
            allObjects = load();
            if(allObjects.size() > 0) {
                for(int i = 0; i < allObjects.size(); i++) {
                    System.out.println(allObjects.get(i));
                }
            }
        }
    }

    private void edit(){

    }

    private void delete(){

    }

    private ArrayList<ProgramObject> load(){
        try{
            FileInputStream fileIn = new FileInputStream("res/saved.bin");
            ObjectInputStream in = new ObjectInputStream(fileIn);

            boolean loop = true;
            try{
                while(loop) {
                    object = (ProgramObject) in.readObject();
                    if(object != null) {
                        allObjects.add(object);
                    } else {
                        loop = false;
                    }
            }
                fileIn.close();
                in.close();
            } catch(EOFException e){}
        } catch (Exception e) {
            System.out.println("Error");
        }
        return allObjects;
    }
}
