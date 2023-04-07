package com.rafa.java_file_explorer;

import java.util.Scanner;

public class FileExplorer {
    Boolean KeepProgramRunning;
    StringBuilder currentPath;
    PathHandeler pathHandeler;
    OperationHandeler operationHandeler;

    static final String OPERATION_DISPLAY_MESSAGE = """
                ls: list files in current directory or specified directory/file
                pwd: print the current directory
                cd: change directory
                mv: move file/directory
                ren: rename file/directory
                del: delete file/directory
                exit: exit program
            """;
    static final String WELCOME_MESSAGE = """
            Welcome.
            Type help for possible operations.
            """;

    FileExplorer(){
        currentPath = new StringBuilder();
        pathHandeler = new PathHandeler();
        operationHandeler = new OperationHandeler();

        KeepProgramRunning = true;
    }

    void startExplorer(){
        Scanner UserInput = new Scanner(System.in);
        StringBuilder UserChoice = new StringBuilder();

        System.out.println(WELCOME_MESSAGE);

        while(KeepProgramRunning){
            String OperationPromptMessage = "Enter an operation: ";
            System.out.println(OperationPromptMessage);
            UserChoice.replace(0, UserChoice.length(), UserInput.nextLine());
            choiceHandler(UserChoice);
        }
        UserInput.close();
    }

    void choiceHandler(StringBuilder choice){
        try{
            ExplorerOperation GetChoice = ExplorerOperation.valueOf(choice.toString().toUpperCase());
            switch(GetChoice){
                case LS -> {
                    operationHandeler.listDirectory();
                    System.out.println(GetChoice);
                }
                case PWD -> operationHandeler.printCurrentDirectory();
                case CD -> throw new UnsupportedOperationException("Unimplemented case: " + GetChoice);
                case DEL -> throw new UnsupportedOperationException("Unimplemented case: " + GetChoice);
                case MV -> throw new UnsupportedOperationException("Unimplemented case: " + GetChoice);
                case REN -> throw new UnsupportedOperationException("Unimplemented case: " + GetChoice);
                case EXIT -> {
                    exitProgram();
                }
                case HELP -> System.out.println("Possible Operations: \n"+OPERATION_DISPLAY_MESSAGE);
                default -> throw new IllegalArgumentException("Unexpected value: " + GetChoice);
            }
        }catch(IllegalArgumentException e){
            choice.insert(0, "Operation ");
            choice.append(" doesn't exist");
            System.out.println(choice);
        }catch(UnsupportedOperationException u){
            System.out.println(u);
        }
    }

    void exitProgram(){
        boolean DoExit = false;
        KeepProgramRunning = DoExit;
        String exitMessage = "Exiting Program";
        System.out.println(exitMessage);
    }
    
}