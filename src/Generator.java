import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;

import static java.lang.System.out;

public class Generator {
    public void setIndex(List<Employe> employes) throws IOException {
        System.setOut(new PrintStream("src/newSite/index.html"));

        StringBuilder content = new StringBuilder(Files.readString(Path.of("src/template/index/1.txt")));
        for (Employe employe : employes) {
            content.append("<li><a href=\"employes/").append(employe.unique).append("/index.html\">").append(employe.prenom).append(" ").append(employe.nom).append("</a></li>");
        }
        content.append(Files.readString(Path.of("src/template/index/3.txt")));

        out.println(content);
    }

    public void setFiche(Employe employe, HashMap<String, String> materialsMap) throws IOException {
        String path = "src/newSite/employes/" + employe.unique ;

        File D = new File(path);
        D.mkdir();

        System.setOut(new PrintStream(path + "/index.html"));

        StringBuilder content = new StringBuilder(Files.readString(Path.of("src/template/employe/1.txt")));
        content.append(employe.prenom).append(" ").append(employe.nom);
        content.append(Files.readString(Path.of("src/template/employe/3.txt")));

        for (String key: materialsMap.keySet()){
            String checked = "";
            if(employe.materiaux.contains(key)){
                checked = "checked";
            }
            content.append("<div class=\"form-check\">\n"
                    + "<input class=\"form-check-input\" type=\"checkbox\" value=\"\" id=\"" + key + "\" "
                    + checked + ">\n"
                    + "<label class=\"form-check-label\" for=\"" + key + "\">\n"
                    + "" + materialsMap.get(key) + "\n"
                    + "</label>\n"
                    + "</div>");
        }

        content.append(Files.readString(Path.of("src/template/employe/4.txt")));
        content.append(employe.IDimage);
        content.append(Files.readString(Path.of("src/template/employe/6.txt")));


        out.println(content);
    }
}
