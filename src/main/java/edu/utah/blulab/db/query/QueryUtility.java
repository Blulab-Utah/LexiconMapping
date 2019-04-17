package edu.utah.blulab.db.query;

import edu.utah.blulab.db.models.EpicMappedModifierEntity;
import edu.utah.blulab.db.models.FamilyMemberRoleCodesEntity;
import edu.utah.blulab.db.models.SnomedMappedModifierEntity;
import edu.utah.blulab.db.models.SnomedMappingEntity;
import edu.utah.blulab.models.CodeMapDao;
import edu.utah.blulab.models.ModifierDao;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

public class QueryUtility {


    public static boolean insertMappedContents(ModifierDao modifier, CodeMapDao codeMap) {
        Session session = null;
        Transaction transaction = null;
        try {
            SessionFactory sessionFactory = SessionHandler.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            EpicMappedModifierEntity epicMappedModifierEntity = new EpicMappedModifierEntity();
            epicMappedModifierEntity.setEpicLabel(codeMap.getEpicLabel());
            epicMappedModifierEntity.setEpicCode(codeMap.getEpicCode());
            epicMappedModifierEntity.setStandardCode(codeMap.getStandardCode());
            epicMappedModifierEntity.setStandardLabel(codeMap.getStandardLabel());
            epicMappedModifierEntity.setStandardCodeSystem(codeMap.getStandardCodeSystem());
            epicMappedModifierEntity.setEpicCodeSystem(codeMap.getEpicCodeSystem());
            epicMappedModifierEntity.setType(modifier.getType());
            epicMappedModifierEntity.setRegex(modifier.getRegex());
            epicMappedModifierEntity.setDirection(modifier.getDirection());
            epicMappedModifierEntity.setLex(modifier.getLex());
            session.persist(epicMappedModifierEntity);
            transaction.commit();
        } catch (Exception ex) {
            String s = ex.getMessage();
            return false;
        }

        session.close();

        return true;
    }

    public static List<FamilyMemberRoleCodesEntity> getFamilyMemberRoleCodeEntity() {
        SessionFactory sessionFactory = SessionHandler.getSessionFactory();
        Session session = sessionFactory.openSession();
        Criteria mappedModifierCriteria = session.createCriteria(FamilyMemberRoleCodesEntity.class);
        List<?> rawResults = mappedModifierCriteria.list();
        session.close();
        List<FamilyMemberRoleCodesEntity> result = new ArrayList<>(rawResults.size());
        for(Object object : rawResults)
        {
            result.add((FamilyMemberRoleCodesEntity) object);
        }

        return result;
    }

    public static List<SnomedMappingEntity> getSnomedMappedCuis() {
        SessionFactory sessionFactory = SessionHandler.getSessionFactory();
        Session session = sessionFactory.openSession();
        Criteria snomedMappingCriteria = session.createCriteria(SnomedMappingEntity.class);
        List<?> rawResults = snomedMappingCriteria.list();
        session.close();
        List<SnomedMappingEntity> result = new ArrayList<>(rawResults.size());
        for (Object object : rawResults) {
            result.add((SnomedMappingEntity) object);
        }

        return result;
    }

    public static void updateCuis(SnomedMappingEntity snomedMappingEntity, FamilyMemberRoleCodesEntity mappedModifier) {
        SessionFactory sessionFactory = SessionHandler.getSessionFactory();
        Session session = sessionFactory.openSession();

        if(String.valueOf(mappedModifier.getSnomedCui()).isEmpty()) {
            Query query = session.createQuery("UPDATE FamilyMemberRoleCodesEntity SET snomedCui = :cui WHERE id = :id");
            query.setParameter("cui", snomedMappingEntity.getCui());
            query.setParameter("id", mappedModifier.getId());
            int result = query.executeUpdate();

        }
        session.close();
    }

    public static void insertMappedCuis(FamilyMemberRoleCodesEntity familyMemberRoleCodesEntity, ModifierDao modifierDao) {
        Session session;
        Transaction transaction;
        SessionFactory sessionFactory = SessionHandler.getSessionFactory();
        session = sessionFactory.openSession();

//        Criteria mappedModifierCriteria = session.createCriteria(SnomedMappedModifierEntity.class);
//        mappedModifierCriteria.add(Restrictions.eq("lex",modifierDao.getLex()));
//        mappedModifierCriteria.add(Restrictions.eq("type",modifierDao.getType()));
//        mappedModifierCriteria.add(Restrictions.eq("regex",modifierDao.getRegex()));
//        mappedModifierCriteria.add(Restrictions.eq("direction",modifierDao.getDirection()));
//        List<?> rawResults = mappedModifierCriteria.list();
//        List<SnomedMappedModifierEntity> result = new ArrayList<>(rawResults.size());
//        for(Object object : rawResults)
//        {
//            result.add((SnomedMappedModifierEntity) object);
//        }
//
//        if(!result.isEmpty())
//        {
            try {
                transaction = session.beginTransaction();
                SnomedMappedModifierEntity snomedMappedModifierEntity = new SnomedMappedModifierEntity();
                snomedMappedModifierEntity.setDirection(modifierDao.getDirection());
                snomedMappedModifierEntity.setLex(modifierDao.getLex());
                snomedMappedModifierEntity.setRegex(modifierDao.getRegex());
                snomedMappedModifierEntity.setType(modifierDao.getType());
                snomedMappedModifierEntity.setTerm(familyMemberRoleCodesEntity.getTerm());
                snomedMappedModifierEntity.setSnomedCui(familyMemberRoleCodesEntity.getSnomedCui());
                snomedMappedModifierEntity.setHl7FamilyMemberRoleCode(familyMemberRoleCodesEntity.getHl7FamilyMemberRoleCode());
                snomedMappedModifierEntity.setSnomedPreferredLabel(familyMemberRoleCodesEntity.getSnomedPreferredLabel());
                snomedMappedModifierEntity.setHl7InternalId(familyMemberRoleCodesEntity.getHl7InternalId());
                session.persist(snomedMappedModifierEntity);
                transaction.commit();
            } catch (Exception e) {
                session.close();
            }
//            finally {
//                session.close();
//            }
//        }

        session.close();

    }

}
