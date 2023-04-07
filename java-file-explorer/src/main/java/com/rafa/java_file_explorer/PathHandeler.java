package com.rafa.java_file_explorer;

import java.nio.file.Path;

public class PathHandeler {
    String currentPathString;

    PathHandeler(){
        currentPathString = "";
    }

    private Path getPathFromString(String path){
        Path ReturnedPath = Path.of(path);
        return ReturnedPath;
    }

    String getCurrentPath(){
        Path currentPath = Path.of(currentPathString);
        return currentPath.toAbsolutePath().toString();
    }

    String listDirectory(String DirectoryString){
        Path ListPath = getPathFromString(DirectoryString);
        return "placeholder";
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
