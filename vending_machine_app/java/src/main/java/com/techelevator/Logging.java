package com.techelevator;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class Logging {
    private File logFile;
    private PrintWriter writer;


    public Logging(String path){
        this.logFile = new File(path);
        if(logFile.exists()){
            try{
                this.writer = new PrintWriter(new FileWriter(this.logFile, true));
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
        else{
            try{
                writer = new PrintWriter(this.logFile);
            }
            catch(FileNotFoundException e){
                e.printStackTrace();
            }
        }
    }

    public void write(String logMessage){
        writer.println(logMessage);

    }
    public void close() throws IOException{
        writer.close();
    }

    public  static File createLogFile(String path) throws IOException {

        File logFile = new File(path);
        FileWriter writer = new FileWriter(logFile);
        PrintWriter write = new PrintWriter(writer, false);
        if (logFile.exists() && !logFile.toString().equals("src/main/resources/Hidden.txt")){
            write.flush();
            writer.close();
            write.close();
        }
        try{
            logFile.createNewFile();
        }catch (IOException e){
            System.out.println("Could not create log file");
        }
        return  logFile;
    }

    public static File initializeHidden(String path, List<Snack> snackList) throws IOException{
        File hiddenFile = new File(path);
        Scanner inputReader = new Scanner(hiddenFile);
        FileWriter fileWriter = new FileWriter(hiddenFile,false);
        PrintWriter writer = new PrintWriter(fileWriter, true);
        if (hiddenFile.length() == 0) {
            for (int i = 0; i < snackList.size(); i++) {
                writer.println(snackList.get(i).getNameOfItem() + "|0");
//                Purchase.snackMap.put(snackList.get(i).getNameOfItem(),0);
            }
        }
        else {
            for (int i = 0; i< snackList.size();i++){
                while(inputReader.hasNextLine()){
                    String currentLine = inputReader.nextLine();
                    String[] lineArray = currentLine.split("\\|");
//                    Purchase.snackMap.put(lineArray[0],Integer.parseInt(lineArray[1]));
                }

            }

        }
        writer.close();
        return hiddenFile;
    }
}
