import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;


public class EmployerDeSerializer implements Deserializer<Employer> {

	public void configure(Map map, boolean isKey) {
	}

	public void close() {

	}

	@Override
	public Employer deserialize(String topic, byte[] data) {
		// TODO Auto-generated method stub
		Employer e = null; 
		try {
			ByteArrayInputStream bios = new ByteArrayInputStream(data);
			ObjectInputStream ois = new ObjectInputStream(bios);
			Object o = ois.readObject();
			e = (Employer) o;
			ois.close();
			return e;
		} catch (IOException e1) {

		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return e;
	}
}
