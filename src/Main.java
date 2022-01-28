import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Retriever retriever = new Retriever();
        retriever.deletePreviousHTML(new File("src/newSite/agents"));
        try {
            retriever.setMaterials();

            List<Agent> agents = retriever.tratAllagentes();
            for(Agent agent : agents){
                Generator.setFiche(agent, retriever.materialsMap);
                retriever.uploadIDimage(agent);
            }
            Generator.setIndex(agents);
            Generator.setHtaccess(agents);
            Generator.setHtpassword(agents);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

