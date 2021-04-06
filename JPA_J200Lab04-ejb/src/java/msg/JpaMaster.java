package msg;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;


@Stateless
public class JpaMaster implements JpaMasterLocal {

    @PersistenceContext
    EntityManager em;
    
    @Override
    public ArrayList<String> getMessageList(){
        //StringBuilder sb = new StringBuilder();
        TypedQuery<Messages> typedQuery = em.createNamedQuery("Messages.findAll", Messages.class);
        List<Messages> list = typedQuery.getResultList();
        ArrayList<String> l = new ArrayList<>(list.size());
        //sb.append("<ul>\n");
        for(Messages m : list){
            l.add(m.getMessage());
        }
        //sb.append("</ul>\n");
        //return sb.toString();
        return l;
    }
    
    @Override
    public ArrayList<Integer> getNumbers(){
        TypedQuery<Numbers> typedQueryNumbers = em.createNamedQuery("Numbers.findAll", Numbers.class);
        List<Numbers> list = typedQueryNumbers.getResultList();
        ArrayList<Integer> l = new ArrayList<>(list.size());
        for(Numbers n : list){
            l.add(n.getNumber());
        }
        return l;
    }
    
    @Override
    public int getTotal(){
        ArrayList<Integer> list = getNumbers();
        return list.stream().mapToInt(x -> x).sum();
    }
    
    @Override
    public boolean writeMessage(String message) {
        if(message == null)
            return false;
        Messages m = new Messages(message);
        if(em.contains(m)){
            return false;
        }
        Messages tmp = em.find(Messages.class, m.getMessage());
        if(tmp == null){
            em.persist(m);
            em.merge(m);
            return true;
        }
        return false;
    }
    
    @Override
    public boolean writeInteger(Integer number) {
        if(number == null)
            return false;
        Numbers n = new Numbers(number);
        if(em.contains(n)){
            return false;
        }
        Numbers tmp = em.find(Numbers.class, n.getNumber());
        if(tmp == null){
            em.persist(n);
            em.merge(n);
            return true;
        }
        return false;
    }
    
    @Override
    public int cleanMessages(){
        Query q = em.createNamedQuery("Messages.cleanMessages");
        return q.executeUpdate();                       //--------------------> Может быть тут ошибка?
    }

    @Override
    public int cleanNumbers() {
        Query q = em.createNamedQuery("Messages.cleanNumbers");
        return q.executeUpdate();                        //--------------------> Может быть тут ошибка?
    }


}
