package com.rafa.java_file_explorer;

import java.util.Map;
import java.util.Scanner;

public class OperationHandeler {
    PathHandeler pathHandeler;

    final String LS_PROMPT_MESSAGE = "Insert path. (Use . for current directory)";
    final String CD_PROMPT_MESSAGE = "Change to directory: ";
    final String PATH_IS_FILE = " is a file!";
    final String PATH_NOT_FOUND = " cannot be found";

    Scanner UserInput;

    OperationHandeler(){
        pathHandeler = new PathHandeler();
        UserInput = new Scanner(System.in);
    }

    void listDirectory(){
        System.out.println(LS_PROMPT_MESSAGE);
        String ListThisPath = UserInput.nextLine();
        Map<String, String> DirectoryMap;

        boolean PathExist = pathHandeler.checkPathExist(ListThisPath);

        if( !PathExist && !(ListThisPath.isEmpty()) ){
            System.out.println(ListThisPath + PATH_NOT_FOUND);
            return;
        }

        try{
            Boolean IsPathFile = pathHandeler.isPathFile(ListThisPath);
            if(IsPathFile){
                System.out.println("F " + ListThisPath);
                return;
            }
            DirectoryMap = pathHandeler.listDirectory(ListThisPath);
            if(DirectoryMap.isEmpty()){
                System.out.println(ListThisPath + PATH_NOT_FOUND);
            }
            DirectoryMap.forEach((DirectoryPath, PathType) -> {
                    System.out.println(PathType + " " + DirectoryPath);
                }
            );
        }catch(Exception e){
            System.out.println(e);
        }
    }

    void changeDirectory(){
        System.out.println(CD_PROMPT_MESSAGE);
        String ChangeToPath = UserInput.nextLine();
        boolean PathIsFile = pathHandeler.isPathFile(ChangeToPath);
        if(PathIsFile){
            System.out.println(ChangeToPath+PATH_IS_FILE);
            return;
        }

    }

    void printCurrentDirectory(){
        System.out.println(pathHandeler.getCurrentDirectory());
    }

    void printWorkingDirectory(){
        System.out.println(pathHandeler.printWorkingDirectory());
    }
}
