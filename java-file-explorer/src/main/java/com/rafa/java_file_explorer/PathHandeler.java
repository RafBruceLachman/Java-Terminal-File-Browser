package com.rafa.java_file_explorer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Collectors;
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
            ListPath = currentPath.resolve(DirectoryString);
        }
        try{
            Stream<Path> DirectoryContentStream = getDirectoryContentsStream(ListPath);
            Map<Path, String> DirectoryContentMap = DirectoryContentStream
            .collect(
                Collectors.toMap(
                    ItemPath -> ItemPath, 
                    ItemType -> getPathItemSymbol(ItemType)
                    
                )
            );
            
            DirectoryContentMap.forEach( (MapItem, ItemType) -> {
                System.out.println(ItemType + " " + MapItem);
            });
        }catch(IOException io){
            System.out.println(DirectoryString + PathCannotBeFound);
        }
    }

    private Stream<Path> getDirectoryContentsStream(Path Directory) throws IOException{
        Stream<Path> DirectoryContentStream = Files.list(Directory);
        return DirectoryContentStream;
    }

    public boolean isPathFile(String pathString){
        Path path = Paths.get(pathString);
        boolean IsFile = isPathFile(path);
        return IsFile;
    }

    private boolean isPathFile(Path path){
        boolean IsFile = false;
        if(path.toFile().isFile()){
            IsFile = true;
        }
        return IsFile;
    }

    private String getPathItemSymbol(Path path){
        String File = "F";
        String Directory = "D";
        if(isPathFile(path)){
            return File;
        }else{
            return Directory;
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

    private void updateAbsoluteCurrentDirectory(Path toDirectory) throws FileNotFoundException{
        if(!(toDirectory.toFile().exists())){
            throw new FileNotFoundException(toDirectory + PathCannotBeFound);
        }
        absoluteCurrentPath = appendPath(absoluteCurrentPath, toDirectory);
    }

    public String printWorkingDirectory() {
        return absoluteCurrentPath.toString();
    }
}