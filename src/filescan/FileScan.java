/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filescan;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 *
 * @author edi
 */
public class FileScan {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {

        if (args.length == 0) {
            System.out.println("Usage: \nfirst argument - folder path \nsecond argument - delete duplicates (optional)");
            System.exit(0);
        }

        String root = args[0];
        Boolean delete = false;
        if (args.length > 1) {
            delete = Boolean.parseBoolean(args[1]);
        }

        File f = new File(root);
        if (!f.exists() && !f.isDirectory()) {
            System.out.println("Path is not a directory");
            System.exit(0);
        }

        Path startingDir = f.toPath();
        PrintFiles pf = new PrintFiles(delete);
        Files.walkFileTree(startingDir, pf);

        BigDecimal oneK = new BigDecimal("1024");
        BigDecimal size = new BigDecimal(pf.getSize());

        size = size.divide(oneK);
        size = size.divide(oneK);
        size = size.divide(oneK);

        size = size.setScale(4, RoundingMode.HALF_UP);

        System.out.println("Duplicates size " + size + " Gb " + " in " + pf.getCount() + " files");
    }

}
