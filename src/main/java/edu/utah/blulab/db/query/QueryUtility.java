package edu.utah.blulab.db.query;

import edu.utah.blulab.db.models.EpicMappedModifierEntity;
import edu.utah.blulab.db.models.FamilyMemberRoleCodesEntity;
import edu.utah.blulab.db.models.SnomedMappedModifierEntity;
import edu.utah.blulab.db.models.SnomedMappingEntity;
import edu.utah.blulab.models.CodeMapDao;
import edu.utah.blulab.models.ModifierDao;
import org.hibernate.*;

import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.criterion.Restrictions;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
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
//        CriteriaBuilder builder = session.getCriteriaBuilder();
//        CriteriaQuery<FamilyMemberRoleCodesEntity> query = builder.createQuery(FamilyMemberRoleCodesEntity.class);
//        Root<FamilyMemberRoleCodesEntity> root = query.from(FamilyMemberRoleCodesEntity.class);
//        query.select(root);
//        Query<FamilyMemberRoleCodesEntity> q = session.createQuery(query);
//        List<FamilyMemberRoleCodesEntity> list = q.getResultList();
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
        if(mappedModifier.getId().equals(null) && mappedModifier.getId().toString().isEmpty())
        {

            Transaction transaction = session.beginTransaction();
            String hql = "UPDATE FamilyMemberRoleCodes set SNOMED_CUI = :cui "  +
                "WHERE ID = :id";
            Query query = session.createQuery(hql);
        query.setParameter("cui", snomedMappingEntity.getCui());
        query.setParameter("id", 10);
        int result = query.executeUpdate();
        System.out.println("Rows affected: " + result);
        }
//        Transaction transaction = session.beginTransaction();
//        String hql = "UPDATE Employee set salary = :salary "  +
//                "WHERE id = :employee_id";
//        Query query = session.createQuery(hql);
//        query.setParameter("salary", 1000);
//        query.setParameter("employee_id", 10);
//        int result = query.executeUpdate();
//        System.out.println("Rows affected: " + result);

        Criteria mappedModifierCriteria = session.createCriteria(FamilyMemberRoleCodesEntity.class);
        mappedModifierCriteria.add(Restrictions.eq("id",mappedModifier.getId()));
        List<?> rawResults = mappedModifierCriteria.list();
        session.close();
        List<FamilyMemberRoleCodesEntity> result = new ArrayList<>(rawResults.size());
        for (Object object : rawResults) {
            result.add((FamilyMemberRoleCodesEntity) object);
        }

    }
}
