import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;

import static java.lang.System.out;

public class Generator {
    public static void setIndex(List<Agent> agents) throws IOException {
        System.setOut(new PrintStream("src/newSite/index.html"));

        StringBuilder content = new StringBuilder(Files.readString(Path.of("src/template/index/1.txt")));
        for (Agent agent : agents) {
            content.append("<li><a href=\"agents/").append(agent.unique).append("/index.html\">").append(agent.prenom).append(" ").append(agent.nom).append("</a></li>");
        }
        content.append(Files.readString(Path.of("src/template/index/3.txt")));

        out.println(content);
    }

    public static void setFiche(Agent agent, HashMap<String, String> materialsMap) throws IOException {
        String path = "src/newSite/agents/" + agent.unique ;

        File D = new File(path);
        D.mkdir();

        System.setOut(new PrintStream(path + "/index.html"));

        StringBuilder content = new StringBuilder(Files.readString(Path.of("src/template/agent/1.txt")));
        content.append(agent.prenom).append(" ").append(agent.nom);
        content.append(Files.readString(Path.of("src/template/agent/3.txt")));

        for (String key: materialsMap.keySet()){
            String checked = "";
            if(agent.materiaux.contains(key)){
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

        content.append(Files.readString(Path.of("src/template/agent/4.txt")));
        content.append(agent.IDimage);
        content.append(Files.readString(Path.of("src/template/agent/6.txt")));

        out.println(content);
    }

    public static void setHtaccess(List<Agent> agents) throws IOException {
        System.setOut(new PrintStream( "src/newSite/.htaccess"));
        out.println(Files.readString(Path.of("src/template/htaccess/.htaccess.txt")));
    }

    public static void setHtpassword(List<Agent> agents) throws IOException {
        System.setOut(new PrintStream( "src/newSite/.htpasswd"));
        StringBuilder content = new StringBuilder();
        for(Agent agent: agents){
            content.append(agent.unique).append(":").append(agent.mdp).append('\n');
        }
        out.println(content);
    }
}
