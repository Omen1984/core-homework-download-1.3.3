import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Main {

    public static void main(String[] args) {
        openZip("E:/JavaProject/Games/savegames/zip.zip", "E:/JavaProject/Games/savegames/");
        openProgress("E:/JavaProject/Games/savegames/save2.dat");
    }

    private static void openProgress(String thePathOfTheFile) {
        GameProgress gp = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(thePathOfTheFile))) {
            gp = (GameProgress) ois.readObject();
        } catch (IOException ex) {
            ex.getMessage();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        System.out.println(gp);
    }

    private static void openZip(String thePathOfTheZip, String unpackingPath) {
        try (ZipInputStream zin = new ZipInputStream(new FileInputStream(thePathOfTheZip))) {
            ZipEntry entry;
            String name;
            while ((entry = zin.getNextEntry()) != null) {
                name = entry.getName();
                FileOutputStream fout = new FileOutputStream(unpackingPath + name);
                for (int c = zin.read(); c != -1; c = zin.read()) {
                    fout.write(c);
                }
                fout.flush();
                zin.closeEntry();
                fout.close();
            }
        } catch (IOException ex) {
            ex.getMessage();
        }
    }
}
