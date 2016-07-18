/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filescan;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import static java.nio.file.FileVisitResult.CONTINUE;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author edi
 */
public class PrintFiles extends SimpleFileVisitor<Path> {

    private Map<String, MyFile> files = new HashMap<>();
    private Long size = 0L;
    private Long count = 0L;
    private Boolean delete = false;

    public PrintFiles() {
    }

    public PrintFiles(Boolean delete) {
        this.delete = delete;
    }

    // Print information about
    // each type of file.
    @Override
    public FileVisitResult visitFile(Path file,
            BasicFileAttributes attr) {

        String fileName = file.toFile().getName();

        if (files.keySet().contains(fileName)) {
            MyFile org = files.get(fileName);
            if (attr.size() == org.getAttributes().size()) {
                size += attr.size();
                count += 1;
                System.out.println("File duplicated " + (attr.size() / 1024) + " Kb \nOriginal:  [" + org.getPath() + "]"
                        + " \nDuplicate: [" + file + "]" + (delete ? " was deleted" : ""));
                if (delete) {
                    boolean deleteOk = file.toFile().delete();
                    if (!deleteOk) {
                        System.err.println("Cannot delete file: " + file);
                    }
                }
            }
        } else {
            files.put(fileName, new MyFile(file, attr));
        }

        return CONTINUE;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
//        System.out.format("Directory: %s%n", dir);
        return CONTINUE;
    }

    // If there is some error accessing
    // the file, let the user know.
    // If you don't override this method
    // and an error occurs, an IOException 
    // is thrown.
    @Override
    public FileVisitResult visitFileFailed(Path file,
            IOException exc) {
        System.err.println(exc);
        return CONTINUE;
    }

    public Long getSize() {
        return size;
    }

    public Long getCount() {
        return count;
    }

}
