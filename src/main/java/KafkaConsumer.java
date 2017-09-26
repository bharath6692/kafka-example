import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.common.serialization.StringDeserializer;

public class KafkaConsumer {
	private static final String FILENAME = "/home/bharath/Documents/output.txt";
	public static  void main(String args[]) {
		Properties prop = new Properties();
		prop.setProperty("bootstrap.servers","127.0.0.1:9092");
		prop.setProperty("key.deserializer", StringDeserializer.class.getName());
		prop.setProperty("value.deserializer", StringDeserializer.class.getName());

		prop.setProperty("group.id","files");
		prop.setProperty("auto.offset.reset", "earliest");
		Consumer<String,String> consumer = new org.apache.kafka.clients.consumer.KafkaConsumer<String, String>(prop);
		consumer.subscribe(Arrays.asList("file_topic"));
		System.out.println("Hi In Consumer 1");
		String newline = "\n";
		try {
			while(true) {
				ConsumerRecords<String, String> consumerRecords = consumer.poll(100);
				for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
					Files.write(Paths.get(FILENAME), consumerRecord.value().getBytes(), StandardOpenOption.APPEND);
					Files.write(Paths.get(FILENAME), newline.getBytes(), StandardOpenOption.APPEND);
					System.out.println("Partition: " + consumerRecord.partition() +
							", Offset: " + consumerRecord.offset() +
							", Key: " + consumerRecord.key() +
							", Value: " + consumerRecord.value() +
							", topic :" + consumerRecord.topic());
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
