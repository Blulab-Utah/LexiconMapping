import edu.utah.blulab.db.query.QueryUtility;
import edu.utah.blulab.models.CodeMapDao;
import edu.utah.blulab.models.ModifierDao;
import org.hibernate.cfg.Configuration;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LexiconMappingEpic {

    public static void main(String[] args) throws IOException {
        String codeMapFile = "/home/deep/IdeaProjects/LexiconMapping/src/main/resources/code-map.csv";
        String modifierFile = "/home/deep/IdeaProjects/LexiconMapping/src/main/resources/modifier.csv";

        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        List<CodeMapDao> codeMapDaoList = new ArrayList<>();
        List<ModifierDao> modifierDaoList = new ArrayList<>();


        br = new BufferedReader(new FileReader(codeMapFile));
        while ((line = br.readLine()) != null) {

            // use comma as separator
            String[] codeMap = line.split(cvsSplitBy);
            CodeMapDao codeMapDao = new CodeMapDao();
            codeMapDao.setEpicLabel(codeMap[1]);
            codeMapDao.setEpicCode(codeMap[2]);
            codeMapDao.setStandardCode(codeMap[3]);
            codeMapDao.setStandardLabel(codeMap[4]);
            codeMapDao.setStandardCodeSystem(codeMap[5]);
            codeMapDao.setEpicCodeSystem(codeMap[6]);
            codeMapDaoList.add(codeMapDao);
        }

        br = new BufferedReader(new FileReader(modifierFile));
        while ((line = br.readLine()) != null) {

            // use comma as separator
            String[] modifier = line.split(cvsSplitBy);
            ModifierDao modifierDao = new ModifierDao();
            modifierDao.setType(modifier[1]);
            modifierDao.setRegex(modifier[2]);
            modifierDao.setDirection(modifier[3]);
            modifierDao.setLex(modifier[4]);
            modifierDaoList.add(modifierDao);
        }

        for (ModifierDao modifierDao : modifierDaoList) {
            CodeMapDao codeMap = getMappedCode(modifierDao, codeMapDaoList);
            boolean status = QueryUtility.insertMappedContents(modifierDao,codeMap);
        }

        System.out.println("\nComplete");
    }

    private static CodeMapDao getMappedCode(ModifierDao modifierDao, List<CodeMapDao> codeMapDaoList) {

        for (CodeMapDao codeMapDao : codeMapDaoList) {
            if (codeMapDao.getEpicLabel().equalsIgnoreCase(modifierDao.getType())) {
                return codeMapDao;
            }
            else if(codeMapDao.getEpicLabel().replace("_","")
                    .equalsIgnoreCase(modifierDao.getType().replace("_","")))
            {
                return codeMapDao;
            }
        }
        CodeMapDao blank = new CodeMapDao();
//        blank.setEpicLabel("");
//        blank.setEpicCode("");
//        blank.setEpicCodeSystem("");
//        blank.setStandardCode("");
//        blank.setStandardLabel("");
//        blank.setStandardCodeSystem("");
        return blank;
    }
}
