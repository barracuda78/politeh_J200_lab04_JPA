package msg;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;


@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/StringIntegerQ")
    ,
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class IntegerConsumer implements MessageListener {
    
    //@EJB
    //private DbMasterLocal dbMaster;
    
    @EJB
    private JpaMasterLocal jpaMaster;
    
    public IntegerConsumer() {
    }
    
    @Override
    public void onMessage(Message message) {
        if(message instanceof ObjectMessage){
            ObjectMessage om = (ObjectMessage)message;
            try {
                Integer num = (Integer)om.getObject();
                jpaMaster.writeInteger(num);
            } catch (JMSException ex) {
                System.out.println("Ошибка извлечения числа из сообщения");
            }
        }
    }
}
