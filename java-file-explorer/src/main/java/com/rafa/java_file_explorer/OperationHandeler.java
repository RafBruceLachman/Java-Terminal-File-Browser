package com.rafa.java_file_explorer;

import java.util.Scanner;

public class OperationHandeler {
    PathHandeler pathHandeler;

    final String LS_PROMPT_MESSAGE = "Insert path. (Use . for current directory)";
    final String CD_PROMPT_MESSAGE = "Change to directory: ";

    Scanner UserInput;

    OperationHandeler(){
        pathHandeler = new PathHandeler();
        UserInput = new Scanner(System.in);
    }

    void listDirectory(){
        System.out.println(LS_PROMPT_MESSAGE);
        String ListThisPath = UserInput.nextLine();
        
        try{
            Boolean IsPathFile = pathHandeler.isPathFile(ListThisPath);
            if(IsPathFile){
                System.out.println("F " + ListThisPath);
                return;
            }
            pathHandeler.listDirectory(ListThisPath);
        }catch(Exception e){
            System.out.println(e);
        }
    }

    void changeDirectory(){
        System.out.println(CD_PROMPT_MESSAGE);
    }

    void printCurrentDirectory(){
        System.out.println(pathHandeler.getCurrentDirectory());
    }

    void printWorkingDirectory(){
        System.out.println(pathHandeler.printWorkingDirectory());
    }
}
