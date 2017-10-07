import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Map;

import org.apache.kafka.common.serialization.Serializer;


public class EmployerSerializer implements Serializer<Employer>{
	public void configure(Map map, boolean isKey) {
	}

	public void close() {

	}

	@Override
	public byte[] serialize(String topic, Employer data) {
		// TODO Auto-generated method stub
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(data);
			oos.flush();
			oos.close();
			byte[] b = baos.toByteArray();
			return b;
		} catch (IOException e) {
			return new byte[0];
		}
	}
}
