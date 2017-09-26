import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

public class KafkaProducer {
	public static void main(String args[]) {
		Properties prop = new Properties();
		prop.setProperty("bootstrap.servers","127.0.0.1:9092");
		prop.setProperty("key.serializer", StringSerializer.class.getName());
		prop.setProperty("value.serializer", StringSerializer.class.getName());

		prop.setProperty("acks","1");
		prop.setProperty("retries","0");

		Producer<String, String> prod = new org.apache.kafka.clients.producer.KafkaProducer<String, String>(prop);
		System.out.println("Hi In Producer");
		String fileName = "/home/bharath/Documents/input.txt";

		ProducerRecord<String,String> record ;
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			String line;
			while ((line = br.readLine()) != null) {
				record = new ProducerRecord<String, String>("file_topic","1",line);
				prod.send(record);
			}
			prod.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
