package com.rafa.java_file_explorer;

import java.nio.file.Path;

public class PathHandeler {
    Path getPath(String path){
        Path ReturnedPath = Path.of(path);
        return ReturnedPath;
    }

    Path appendPath(Path BasePath, Path AddedPath){
        Path NewPath = BasePath.resolve(AddedPath);
        return NewPath;
    }
}
