package com.rafa.java_file_explorer;

import java.util.Scanner;

public class OperationHandeler {
    PathHandeler pathHandeler;
    final String LsPromptMessage = """
        Insert name of directory to get content from.
        Insert . for current directory.""";

    OperationHandeler(){
        pathHandeler = new PathHandeler();
    }

    void listDirectory(){
        Scanner ListInput = new Scanner(System.in);
        System.out.println(LsPromptMessage);
        String ListThisDirectory = ListInput.nextLine();
        try{
            pathHandeler.listDirectory(ListThisDirectory);
        }catch(Exception e){
            // System.out.println(e);
        }
    }

    void printCurrentDirectory(){
        System.out.println(pathHandeler.getCurrentDirectory());
    }
}
