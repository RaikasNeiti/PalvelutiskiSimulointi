package simu.model;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;


public class DataAccessObject {

    SessionFactory istuntotehdas = null;

    public DataAccessObject() {

        try {
            istuntotehdas = new Configuration().configure().buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Istuntotehtaan luominen ei 	onnistunut.");
            System.exit(-1);
        }
    }


    public boolean tallennaAsiakas(Asiakas asiakas) {
        Transaction transaktio = null;
        try (Session istunto = istuntotehdas.openSession()){
            transaktio = istunto.beginTransaction();

            istunto.saveOrUpdate(asiakas);
            transaktio.commit();
            return true;
        }catch(Exception e) {

        }
        return false;
    }
}
