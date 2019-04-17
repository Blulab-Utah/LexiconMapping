
import edu.utah.blulab.models.CuiTreeDao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SnomedCTMapping {

    public static void main(String[] args) throws IOException {
        String annotationFile = "C:\\Users\\Deep\\Box Sync\\NLP_Annotated_Classes_from_Free_Text_Comments\\Annotations_CancerTypes.txt";
        String cuiTreeFile = "C:\\Users\\Deep\\Box Sync\\NLP_Annotated_Classes_from_Free_Text_Comments\\CuiTree.csv";

        String line = "";
        String cvsSplitBy = "\t";

        List<String> annotationFileList = new ArrayList<>();

        List<CuiTreeDao> cuiTreeList = new ArrayList<>();

        BufferedReader br = new BufferedReader(new FileReader(cuiTreeFile));
        br.readLine();
        while ((line = br.readLine()) != null) {
            String cuiTreeMetadata[] = line.split(",");
            CuiTreeDao cuiTreeDao = new CuiTreeDao();
            cuiTreeDao.setCui(cuiTreeMetadata[0]);
            cuiTreeDao.setPreferredLabel(cuiTreeMetadata[1]);
            cuiTreeDao.setSynonym(cuiTreeMetadata[2]);
            cuiTreeDao.setConceptCode(cuiTreeMetadata[3]);
            cuiTreeList.add(cuiTreeDao);
        }
        String result = "FileName,FileNamewithextension,Term,Span,Class,Type,CUI,SnomedCTCode\n";
        BufferedReader bufferedReader = new BufferedReader(new FileReader(annotationFile));
        bufferedReader.readLine();
        while ((line = bufferedReader.readLine()) != null) {

            //// use comma as separator
            String[] annotation = line.split(cvsSplitBy);

            String snomedMetadata = annotation[annotation.length - 1];
            String metadata[] = snomedMetadata.split(";");
            String conceptCode = "";
            if (metadata.length > 1) {
                String cuiContent = metadata[0];
                String cui[] = cuiContent.split(":");
                String cuiValue = cui[cui.length - 1];
                cuiValue = cuiValue.replaceAll("[^A-Za-z0-9]", "");

                for (CuiTreeDao cuiTree : cuiTreeList) {
                    if (cuiTree.getCui().equalsIgnoreCase(cuiValue)) {
                        conceptCode = cuiTree.getConceptCode();
                    }
                }

            }
            result += annotation[0] + "," + annotation[1] + "," + annotation[2] + "," + annotation[3] + ","
                    + annotation[4] + "," + annotation[5] + "," + annotation[6] + conceptCode + "\n";

            System.out.println(result);


        }
    }

}
