package com.example.virtebahelper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * <title>CodeHandler</title>
 * <summary>Handles the reading of the textfile containing the code:diagnosis pairs</summary>
 * TODO: Simplify (TreeMap?)
 **/
public class CodeHandler {
    private final List<String> compCodes = new ArrayList<>(); //the entire text of pzc.txt is saved here
    private static final List<String> allDiag = new ArrayList<>(); //only the diagnosis text is saved here
    private  static final List<String> allCodes = new ArrayList<>(); //only the pzc´s are saved here
    private InputStream stream;

    /**Default Constructor*/
    public CodeHandler(){

    }

    /**
     * Constructor handling the InputStream that is needed to read the pzc file
     * @param stream an InputStream reading the pzc file
     * */
    public CodeHandler(InputStream stream){
        this.stream = stream;
    }

    /**
     * @param pos the point in the list
     * @return the diagnosis string at pos
     */
    public String getDiag(int pos){
        return allDiag.get(pos);
    }

    /**
     * @param pos the point in the list
     * @return  the integer value of the pzc
     */
    public int getCode(int pos){
        return Integer.parseInt(allCodes.get(pos));
    }

    /**
     * @return the length of the allCodes list
     */
    public int getListLen(){
        return allCodes.size();
    }

    /**
     * @return the allCodes List
     */
    public List<String> getAllCodes(){
        return allCodes;
    }

    /**
     * reads the pcz file and parses it
     */
    protected void readData(){
        //a stream reader that reads a text file with all the diagnosis inside
        //get the file
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

        final Thread readThread = new Thread(()->{
            if (stream != null) {
                try {

                    // a var to save the read data to
                    String data;
                    for (int i = 0; (data = reader.readLine()) != null; i++) //read until every line is finished
                    {
                        compCodes.add(data); //add the data to the Array
                    }

                    for (String code:compCodes) {
                        allCodes.add(code.substring(0, code.indexOf("---"))); //read the code
                        allDiag.add(code.substring(code.indexOf("---") + 3)); //read the diagnosis
                    }

                    stream.close(); //close the stream

                } catch (Exception e) {
                    e.printStackTrace(); //remove for final build
                }
            }
        });
        readThread.start();
        while (readThread.isAlive()) //wait for the thread to finish(possibly useless, IDK)
        {

        }
    }
}
