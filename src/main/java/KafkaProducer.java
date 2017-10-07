import java.util.Properties;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

public class KafkaProducer {
	public static void main(String args[]) {
		Properties prop = new Properties();
		prop.setProperty("bootstrap.servers","127.0.0.1:9092");
		prop.setProperty("key.serializer", StringSerializer.class.getName());
		prop.setProperty("value.serializer", EmployerSerializer.class.getName());

		prop.setProperty("acks","1");
		prop.setProperty("retries","0");

		Producer<String, Employer> prod = new org.apache.kafka.clients.producer.KafkaProducer<String, Employer>(prop);
		System.out.println("Hi In Producer");
		
		Employer[] emp = new Employer[3];
		emp[0] =  new Employer(1,"Sai Bharath");
		emp[1] =  new Employer(2,"Mani Kumar");
		emp[2] =  new Employer(3,"Kishore Reddy");

		ProducerRecord<String,Employer> record ;
		for (int i = 0; i < emp.length; i++) {
			record = new ProducerRecord<String, Employer>("employer","1",emp[i]);
			prod.send(record);
		}
		prod.close();
	}
}
