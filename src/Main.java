import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Retriever retriever = new Retriever();
        retriever.deletePreviousHTML(new File("src/newSite/employes"));
        try {
            retriever.setMaterials();

            List<Employe> employes = retriever.tratAllEmployees();
            for(Employe employe : employes){
                new Generator().setFiche(employe, retriever.materialsMap);
                retriever.uploadIDimage(employe);
            }
            new Generator().setIndex(employes);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

