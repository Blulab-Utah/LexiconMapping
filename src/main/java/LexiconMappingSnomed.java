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
        String modifierFile = "C:\\Users\\Deep\\IdeaProjects\\LexiconMapping\\src\\main\\resources\\modifier.csv";

        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        List<ModifierDao> modifierDaoList = new ArrayList<>();

        br = new BufferedReader(new FileReader(modifierFile));
        br.readLine();
        while ((line = br.readLine()) != null) {

            //// use comma as separator
            String[] modifier = line.split(cvsSplitBy);
            ModifierDao modifierDao = new ModifierDao();
            modifierDao.setType(modifier[1]);
            modifierDao.setRegex(modifier[2]);
            modifierDao.setDirection(modifier[3]);
            modifierDao.setLex(modifier[4]);
            modifierDaoList.add(modifierDao);
        }

        List<FamilyMemberRoleCodesEntity> mappedSnomedModifierList=  QueryUtility.getFamilyMemberRoleCodeEntity();
        List<SnomedMappingEntity> snomedCuiList = QueryUtility.getSnomedMappedCuis();

        for(FamilyMemberRoleCodesEntity mappedModifier: mappedSnomedModifierList)
        {
            for(SnomedMappingEntity snomedEntity: snomedCuiList)
            {
                if(!mappedModifier.getSnomedCui().isEmpty())
                {
                    if(snomedEntity.getPreferredLabel().replaceAll("[^a-zA-Z]+", "")
                            .equalsIgnoreCase(mappedModifier.getSnomedPreferredLabel().replaceAll("[^a-zA-Z]+", "")))
                    {
                        QueryUtility.updateCuis(snomedEntity,mappedModifier);
                    }
                    else if(snomedEntity.getPreferredLabel().equalsIgnoreCase("ADOPTED CHILD")
                            && (mappedModifier.getSnomedPreferredLabel().equalsIgnoreCase("ADOPTIVE_CHILD")))
                    {
                        QueryUtility.updateCuis(snomedEntity,mappedModifier);

                    }
                    else if (snomedEntity.getPreferredLabel().equalsIgnoreCase("Infant of subject")
                            && (mappedModifier.getSnomedPreferredLabel().equalsIgnoreCase("INFANT_CHILD")))
                    {
                        QueryUtility.updateCuis(snomedEntity,mappedModifier);

                    }
                    else if (snomedEntity.getPreferredLabel().equalsIgnoreCase("natural grandparent")
                            && (mappedModifier.getSnomedPreferredLabel().equalsIgnoreCase("BIOLOGICAL_GRAND-PARENT")))
                    {
                        QueryUtility.updateCuis(snomedEntity,mappedModifier);

                    }
                    else if (snomedEntity.getPreferredLabel().equalsIgnoreCase("Adopted daughter")
                            && (mappedModifier.getSnomedPreferredLabel().equalsIgnoreCase("ADOPTIVE_DAUGHTER")))
                    {
                        QueryUtility.updateCuis(snomedEntity,mappedModifier);

                    } else if (snomedEntity.getPreferredLabel().equalsIgnoreCase("Maternal relative")
                            && ((mappedModifier.getSnomedPreferredLabel().equalsIgnoreCase("MATERNAL_PARENTS"))
                            || mappedModifier.getSnomedPreferredLabel().equalsIgnoreCase("MATERNAL_SIDE"))) {
                        QueryUtility.updateCuis(snomedEntity, mappedModifier);

                    } else if (snomedEntity.getPreferredLabel().equalsIgnoreCase("Adopted son")
                            && (mappedModifier.getSnomedPreferredLabel().equalsIgnoreCase("ADOPTIVE_SON"))) {
                        QueryUtility.updateCuis(snomedEntity, mappedModifier);

                    } else if (mappedModifier.getSnomedPreferredLabel().equalsIgnoreCase("MATERNAL_SIDE")
                            && snomedEntity.getPreferredLabel().equalsIgnoreCase("Maternal relative")) {
                        QueryUtility.updateCuis(snomedEntity, mappedModifier);
                    } else if (mappedModifier.getSnomedPreferredLabel().equalsIgnoreCase("MATERNAL_PARENTS")
                            && snomedEntity.getPreferredLabel().equalsIgnoreCase("Maternal relative")) {
                        QueryUtility.updateCuis(snomedEntity, mappedModifier);
                    } else if (mappedModifier.getSnomedPreferredLabel().equalsIgnoreCase("FAMILY")
                            && snomedEntity.getPreferredLabel().equalsIgnoreCase("Person in family of subject")) {
                        QueryUtility.updateCuis(snomedEntity, mappedModifier);
                    }

                }
            }
        }


        for (ModifierDao modifierDao : modifierDaoList) {
            FamilyMemberRoleCodesEntity familyMemberRoleCodesEntity = getMappedEntities(modifierDao,mappedSnomedModifierList);
            QueryUtility.insertMappedCuis(familyMemberRoleCodesEntity,modifierDao);
        }

        System.out.println("\nComplete");

    }

    private static FamilyMemberRoleCodesEntity getMappedEntities(ModifierDao modifierDao, List<FamilyMemberRoleCodesEntity> mappedSnomedModifierList) {
        for(FamilyMemberRoleCodesEntity familyMemberRoleCodesEntity : mappedSnomedModifierList)
        {
            if (modifierDao.getType().equals("MATERNAL_SIDE")
                    && familyMemberRoleCodesEntity.getSnomedPreferredLabel().equals("Maternal relative")) {
                return familyMemberRoleCodesEntity;
            } else if (modifierDao.getType().equals("MATERNAL_PARENTS")
                    && familyMemberRoleCodesEntity.getSnomedPreferredLabel().equals("Maternal relative")) {
                return familyMemberRoleCodesEntity;
            } else if (modifierDao.getType().equals("FAMILY")
                    && familyMemberRoleCodesEntity.getSnomedPreferredLabel().equals("Person in family of subject")) {
                return familyMemberRoleCodesEntity;
            }
            if (modifierDao.getType().replaceAll("[^a-zA-Z]+", "")
                    .equalsIgnoreCase(familyMemberRoleCodesEntity.getSnomedPreferredLabel().replaceAll("[^a-zA-Z]+", "")))
            {
                return familyMemberRoleCodesEntity;
            }

        }
        return new FamilyMemberRoleCodesEntity();
    }
}