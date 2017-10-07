import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.common.serialization.StringDeserializer;

public class KafkaConsumer {
	public static  void main(String args[]) {
		Properties prop = new Properties();
		prop.setProperty("bootstrap.servers","127.0.0.1:9092");
		prop.setProperty("key.deserializer", StringDeserializer.class.getName());
		prop.setProperty("value.deserializer", EmployerDeSerializer.class.getName());

		prop.setProperty("group.id","files");
		prop.setProperty("auto.offset.reset", "earliest");
		Consumer<String,Employer> consumer = new org.apache.kafka.clients.consumer.KafkaConsumer<String, Employer>(prop);
		consumer.subscribe(Arrays.asList("employer"));
		System.out.println("Hi In Consumer");
		while(true) {
			ConsumerRecords<String, Employer> consumerRecords = consumer.poll(100);
			Employer e ;
			for (ConsumerRecord<String, Employer> consumerRecord : consumerRecords) {
				e = (Employer)consumerRecord.value();
				System.out.println("Partition: " + consumerRecord.partition() +
						", Offset: " + consumerRecord.offset() +
						", Key: " + consumerRecord.key() +
						", Value: " + e.getRollNo() + "-" + e.getName() +
						", topic :" + consumerRecord.topic());
			}
		}
	}
}
