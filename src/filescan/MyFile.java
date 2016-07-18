/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filescan;

import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

/**
 *
 * @author edi
 */
public class MyFile {

    private Path path;
    private BasicFileAttributes attributes;

    public MyFile(Path path, BasicFileAttributes attributes) {
        this.path = path;
        this.attributes = attributes;
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public BasicFileAttributes getAttributes() {
        return attributes;
    }

    public void setAttributes(BasicFileAttributes attributes) {
        this.attributes = attributes;
    }

}
