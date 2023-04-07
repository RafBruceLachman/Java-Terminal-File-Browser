package com.rafa.java_file_explorer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class PathHandeler {
    private Path currentPath;

    PathHandeler(){
        currentPath = initializeCurrentPath();
    }

    private Path getPathFromString(String path){
        Path ReturnedPath = Paths.get(path);
        return ReturnedPath;
    }

    private Path initializeCurrentPath(){
        final String InitializerString = "";
        Path initialPath = Paths.get(InitializerString).toAbsolutePath();
        return initialPath;
    }

    private Path getCurrentPath(){
        return currentPath;
    }

    String getCurrentDirectory(){
        String currentPath = getCurrentPath().toString();
        return currentPath;
    }

    void listDirectory(String DirectoryString){
        String CurrentDir = ".";
        Path ListPath = currentPath;
        if(!(DirectoryString.equals(CurrentDir))){
            // ListPath
            ListPath = currentPath.resolve(DirectoryString);
            if(ListPath.toFile().isFile()){
                System.out.println(ListPath.toAbsolutePath());
                return;
            }
        }
        printDirectoryContents(ListPath);
        // ListPath = getPathFromString(DirectoryString);
        // return "placeholder";
    }

    private void printDirectoryContents(Path Directory){
        try(Stream<Path> contentStream = Files.list(Directory)){
            contentStream.forEach(item -> System.out.println(item));
        }catch(IOException io){
            System.out.println("Given directory doesn't exist.");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    Path appendPath(Path BasePath, Path AddedPath){
        Path NewPath = BasePath.resolve(AddedPath);
        return NewPath;
    }

    Path removeLastElementPath(Path BasePath, Integer LevelsToGoUp){
        Integer StartElement = 0;
        Path NewPath = BasePath.subpath(StartElement, (BasePath.getNameCount() - LevelsToGoUp));
        return NewPath;
    }
}
