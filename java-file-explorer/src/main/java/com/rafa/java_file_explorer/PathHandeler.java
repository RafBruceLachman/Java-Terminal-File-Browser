package com.rafa.java_file_explorer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class PathHandeler {
    private final String InitializerString = "";
    private final String PathCannotBeFound = " cannot be found!";

    private Path currentPath;
    private Path absoluteCurrentPath;


    PathHandeler(){
        currentPath = Paths.get(InitializerString);
        absoluteCurrentPath = initializeCurrentPath();
    }

    private Path getPathFromString(String path){
        Path ReturnedPath = Paths.get(path);
        return ReturnedPath;
    }

    private Path initializeCurrentPath(){
        Path initialPath = Paths.get(InitializerString).toAbsolutePath();
        return initialPath;
    }

    private Path getCurrentPath(){
        return currentPath;
    }

    public String getCurrentDirectory(){
        String currentPath = getCurrentPath().toString();
        return currentPath;
    }

    public void listDirectory(String DirectoryString){
        String CurrentDir = ".";
        Path ListPath = currentPath;
        if(!(DirectoryString.equals(CurrentDir))){
            // ListPath
            ListPath = currentPath.resolve(DirectoryString);
            if(ListPath.toFile().isFile()){
                System.out.println(ListPath);
                return;
            }
        }
        printDirectoryContents(ListPath);
    }

    private void printDirectoryContents(Path Directory){
        try(Stream<Path> contentStream = Files.list(Directory)){
            contentStream.forEach(item -> System.out.println(item));
        }catch(IOException io){
            System.out.println(Directory+PathCannotBeFound);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private Path appendPath(Path BasePath, Path AddedPath){
        Path NewPath = BasePath.resolve(AddedPath);
        return NewPath;
    }

    private Path removeLastElementPath(Path BasePath, Integer LevelsToGoUp){
        Integer StartElement = 0;
        Path NewPath = BasePath.subpath(StartElement, (BasePath.getNameCount() - LevelsToGoUp));
        return NewPath;
    }

    private void updateAbsoluteCurrentDirectory(Path toDirectory){
        if(!(toDirectory.toFile().exists())){
            System.out.println(toDirectory  + PathCannotBeFound);
            return;
        }
        absoluteCurrentPath = appendPath(absoluteCurrentPath, toDirectory);
    }

    public String printWorkingDirectory() {
        return absoluteCurrentPath.toString();
    }
}
