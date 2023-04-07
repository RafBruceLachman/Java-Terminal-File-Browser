package com.rafa.java_file_explorer;

public enum ExplorerOperation {
    LS("ls"), PWD("pwd"), CD("cd"), MV("mv"), REN("ren"), DEL("del"), EXIT("exit"), HELP("help");

    final String Operation;

    ExplorerOperation(String Operation){
        this.Operation = Operation;
    }
}
