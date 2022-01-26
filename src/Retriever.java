import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

public class Retriever {
    final String PATH = "src/exemple/";
    HashMap<String, String> materialsMap;

    public Retriever(){
        materialsMap = new HashMap<String, String>();
    }

    public void setMaterials() throws IOException {
        String content = Files.readString(Path.of(PATH + "material.txt"));
        String[] materials = content.split("\n");

        for (String material: materials) {
            int blankIndex = material.indexOf(' ');
            materialsMap.put(material.substring(0,blankIndex), material.substring(blankIndex));
        }
    }

    public List<Employe> tratAllEmployees() throws IOException {
        String content = Files.readString(Path.of(PATH + "staff.txt"));
        String[] names = content.split("\r\n");

        List<Employe> employes = new ArrayList<Employe>();
        for (String name : names){
            employes.add(createEmploye(name));
        }
        return employes;
    }

    public Employe createEmploye(String login) throws IOException {
        String content = Files.readString(Path.of(PATH + login + ".txt"));
        String[] names = content.split("\r\n");

        List<String> materiaux = new ArrayList<>();
        for (String material : materialsMap.keySet()){
            if(content.contains(material)){
                materiaux.add(material);
            }
        }

        return new Employe(
                names[0],
                names[1],
                names[2],
                names[3],
                materiaux
                );
    }

    public void uploadIDimage(Employe employe) throws IOException {
        Path originalPath = Path.of(PATH + employe.IDimage);
        Path copied = Paths.get("src/newSite/employes/" + employe.unique + "/" + employe.IDimage);
        Files.copy(originalPath, copied, StandardCopyOption.REPLACE_EXISTING);
    }

    public void deletePreviousHTML(File file){
        File[] files = file.listFiles();
        if(files!=null) {
            for(File f: files) {
                if(f.isDirectory()) {
                    File[] underFiles = f.listFiles();
                    for (File underFile: underFiles){
                        underFile.delete();
                    }
                }
                f.delete();
            }
        }
    }
}
