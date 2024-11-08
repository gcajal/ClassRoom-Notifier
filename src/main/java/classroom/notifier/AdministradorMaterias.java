package classroom.notifier;

import classroom.notifier.entity.Comparador;
import classroom.notifier.entity.Observable;
import classroom.notifier.implement.Filter;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.Map;

public class AdministradorMaterias extends Observable<Map<String,String>> implements Filter {

    private Comparador Comparador;
    Map<String,String> Materias;

    AdministradorMaterias(Comparador Comparador,Map<String,String> Materias){
        this.Comparador = Comparador;
        this.Materias = Materias;
    }
    @Override
    public void execute(Object obj) {
        if(obj instanceof Map<?,?>) {
            Map<String, String> Novedad = (Map<String, String>)obj;
            Map<String, String> diferencia = this.Comparador.comparar(Novedad,this.Materias);
            if (!diferencia.isEmpty()) {
                Materias = Novedad;
                setChanged();
                notifyObservers(diferencia);
                sendMessageToBroker(diferencia);
            }
        }
    }

    public void sendMessageToBroker(Map<String, String> diferenciaMap) {
        String differences = "Results of comparison";

        try {
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
            Connection connection = connectionFactory.createConnection();
            connection.start();

            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createTopic("courseComparisonTopic");
            MessageProducer producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.PERSISTENT);

            diferenciaMap.forEach((materia, aula) -> {
                String cambioText = String.format("La materia %s cambio al aula %s", materia, aula);

                TextMessage message = null;
                try {
                    message = session.createTextMessage(cambioText);
                    producer.send(message);
                } catch (JMSException e) {
                    throw new RuntimeException(e);
                }

            });

            session.close();
            connection.close();
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    static class Builder {
        private Comparador comparador;
        private Map<String,String> dataActual;

        public Builder agregarComparador(){
            this.comparador = new Comparador();
            return this;
        }
        public Builder agregarDatosActuales(Map<String,String> data){
            this.dataActual = data;
            return this;
        }

        public AdministradorMaterias build(){
            return new AdministradorMaterias(this.comparador,dataActual);
        }
    }
}
