package msg;

import java.util.ArrayList;
import javax.ejb.Local;


@Local
public interface JpaMasterLocal {
    
    boolean writeMessage(String message);

    boolean writeInteger(Integer number);
    
    ArrayList<String> getMessageList();
        
    ArrayList<Integer> getNumbers();
    
    int getTotal();
    
    int cleanMessages();

    int cleanNumbers();
}
