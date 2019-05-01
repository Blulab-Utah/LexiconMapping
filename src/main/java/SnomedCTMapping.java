
import edu.utah.blulab.models.SnomedCoreSubsetDao;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SnomedCTMapping {

    public static void main(String[] args) throws IOException, ParseException {
        String annotationFile = "/home/deep/IdeaProjects/LexiconMapping/src/main/resources/NLP_Annotated_Classes_from_Free_Text_Comments/Annotations_CancerTypes.txt";
        String snomedCoreSubsetFile = "/home/deep/IdeaProjects/LexiconMapping/src/main/resources/NLP_Annotated_Classes_from_Free_Text_Comments/SNOMEDCT_CORE_SUBSET_201901.json";

        String line = "";
        String cvsSplitBy = "\t";

        List<String> annotationFileList = new ArrayList<>();

        List<SnomedCoreSubsetDao> snomedSubsetList = new ArrayList<>();

        JSONParser parser = new JSONParser();
        JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(snomedCoreSubsetFile));
        for(Object o : jsonArray)
        {
            JSONObject cuiTreeMetadata = (JSONObject) o;
            String id = (String) cuiTreeMetadata.get("ID)");
            String snomedCid = (String) cuiTreeMetadata.get("SNOMED_CID");
            String snomedFsn = (String) cuiTreeMetadata.get("SNOMED_FSN");
            String snomedConceptStatus = (String) cuiTreeMetadata.get("SNOMED_CONCEPT_STATUS");
            String umlsCui = (String) cuiTreeMetadata.get("UMLS_CUI");
            String occurence = (String) cuiTreeMetadata.get("OCCURRENCE");
            String usage = (String) cuiTreeMetadata.get("USAGE");
            String firstInSubset = (String) cuiTreeMetadata.get("FIRST_IN_SUBSET");
            String isRetiredFromSubset = (String) cuiTreeMetadata.get("IS_RETIRED_FROM_SUBSET");

            SnomedCoreSubsetDao snomedCoreSubsetDao = new SnomedCoreSubsetDao();
            snomedCoreSubsetDao.setId(id);
            snomedCoreSubsetDao.setSnomedFsn(snomedFsn);
            snomedCoreSubsetDao.setSnomedCid(snomedCid);
            snomedCoreSubsetDao.setSnomedConceptStatus(snomedConceptStatus);
            snomedCoreSubsetDao.setUmlsCui(umlsCui);
            snomedCoreSubsetDao.setOccurence(occurence);
            snomedCoreSubsetDao.setUsage(usage);
            snomedCoreSubsetDao.setFirstInSubset(firstInSubset);
            snomedCoreSubsetDao.setIsRetiredFromSubset(isRetiredFromSubset);
            snomedSubsetList.add(snomedCoreSubsetDao);

        }

        BufferedReader bufferedReader = new BufferedReader(new FileReader(annotationFile));
        bufferedReader.readLine();
        String result = "FileName,FileNamewithextension,Term,Span,Class,Type,CUI,SnomedCTCode,Snomed_FSN,Snomed_Concept_Status";
        System.out.println(result);

        while ((line = bufferedReader.readLine()) != null) {

            //// use comma as separator
            String[] annotation = line.split(cvsSplitBy);

            String snomedMetadata = annotation[annotation.length - 1];
            String metadata[] = snomedMetadata.split(";");
            String conceptCode = "", fsn = "", status = "";
            if (metadata.length > 1) {
                String cuiContent = metadata[0];
                String cui[] = cuiContent.split(":");
                String cuiValue = cui[cui.length - 1];
                cuiValue = cuiValue.replaceAll("[^A-Za-z0-9]", "");

//                String apiKey = "ca614266-9e8e-4d8c-ab7f-b8529468a607";
//                RestTicketClient ticketClient = new RestTicketClient(apiKey);
//                //get a ticket granting ticket for this session.
//                String tgt = ticketClient.getTgt();

                for (SnomedCoreSubsetDao cuiMetadata : snomedSubsetList) {

                    if (cuiMetadata.getUmlsCui().equalsIgnoreCase(cuiValue)) {
                         conceptCode = cuiMetadata.getSnomedCid();
                         fsn = cuiMetadata.getSnomedFsn();
                         status = cuiMetadata.getSnomedConceptStatus();
                    }
//                    break;
                }
                result = annotation[0] + "," + annotation[1] + "," + annotation[2] + "," + annotation[3] + ","
                        + annotation[4] + "," + annotation[5] + "," + annotation[6] +","+ conceptCode + "," +
                        fsn + "," + status;
                System.out.println(result);

            }





        }
    }

}
