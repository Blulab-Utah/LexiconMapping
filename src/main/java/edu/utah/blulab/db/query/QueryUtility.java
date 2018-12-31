package edu.utah.blulab.db.query;

import edu.utah.blulab.db.models.EpicMappedModifierDao;
import edu.utah.blulab.models.CodeMapDao;
import edu.utah.blulab.models.ModifierDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class QueryUtility {


    public static boolean insertMappedContents(ModifierDao modifier, CodeMapDao codeMap) {
        Session session = null;
        Transaction transaction = null;
        try
        {
            SessionFactory sessionFactory = SessionHandler.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            EpicMappedModifierDao epicMappedModifierDao = new EpicMappedModifierDao();
            epicMappedModifierDao.setEpicLabel(codeMap.getEpicLabel());
            epicMappedModifierDao.setEpicCode(codeMap.getEpicCode());
            epicMappedModifierDao.setStandardCode(codeMap.getStandardCode());
            epicMappedModifierDao.setStandardLabel(codeMap.getStandardLabel());
            epicMappedModifierDao.setStandardCodeSystem(codeMap.getStandardCodeSystem());
            epicMappedModifierDao.setEpicCodeSystem(codeMap.getEpicCodeSystem());
            epicMappedModifierDao.setType(modifier.getType());
            epicMappedModifierDao.setRegex(modifier.getRegex());
            epicMappedModifierDao.setDirection(modifier.getDirection());
            epicMappedModifierDao.setLex(modifier.getLex());
            session.persist(epicMappedModifierDao);
            transaction.commit();
        }
        catch (Exception ex)
        {
            String s = ex.getMessage();
            return false;
        }

        session.close();

        return true;
    }
}
