import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

public class NewTask {

    private static final String TASK_QUEUE_NAME = "task_queue";

    public static void main (String [] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory ();
        factory.setHost ("localhost");
        factory.setUsername("mqadmin");
        factory.setPassword("Admin123XX_");

        try (Connection connection = factory.newConnection ();
             Channel channel = connection.createChannel ()) {
            channel.queueDeclare (TASK_QUEUE_NAME, true, false, false, null);

            String mensagem = String.join("", "Wellington da Silva");

            channel.basicPublish ("", TASK_QUEUE_NAME,
                    MessageProperties.PERSISTENT_TEXT_PLAIN, mensagem.getBytes("UTF-8"));
            System.out.println ("[x] Enviado '" + mensagem + "'");
        }
    }

}
