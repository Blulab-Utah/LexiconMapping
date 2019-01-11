import edu.utah.blulab.db.models.FamilyMemberRoleCodesEntity;
import edu.utah.blulab.db.models.SnomedMappingEntity;
import edu.utah.blulab.db.query.QueryUtility;
import edu.utah.blulab.models.ModifierDao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LexiconMappingSnomed {

    public static void main(String[] args) throws IOException {
        String modifierFile = "/home/deep/IdeaProjects/LexiconMapping/src/main/resources/modifier.csv";

        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        List<ModifierDao> modifierDaoList = new ArrayList<>();

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

        List<FamilyMemberRoleCodesEntity> mappedSnomedModifierList=  QueryUtility.getFamilyMemberRoleCodeEntity();
        List<SnomedMappingEntity> snomedMappedCuiList = QueryUtility.getSnomedMappedCuis();

        for(FamilyMemberRoleCodesEntity mappedModifier: mappedSnomedModifierList)
        {
            for(SnomedMappingEntity snomedMappingEntity: snomedMappedCuiList)
            {
                if(snomedMappingEntity.getPreferredLabel().replaceAll("[^a-zA-Z]+", "")
                .equalsIgnoreCase(mappedModifier.getSnomedPreferredLabel().replaceAll("[^a-zA-Z]+", "")))
                {
                    QueryUtility.updateCuis(snomedMappingEntity,mappedModifier);
                }
            }
        }



    }
}
