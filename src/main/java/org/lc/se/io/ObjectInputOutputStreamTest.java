package org.lc.se.io;

import org.junit.jupiter.api.Test;
import org.lc.se.Car;

import java.io.*;

public class ObjectInputOutputStreamTest {

    @Test
    void read1() {
        String srcPath = "file/src/f.txt";
        try (
                InputStream is = new FileInputStream(srcPath);
                ObjectInputStream ois = new ObjectInputStream(is)
        ) {
            Object object = ois.readObject();
            if (object instanceof Car) {
                Car car = (Car) object;
                System.out.println(car.getName() + car.getAge());
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    void write1() {
        String srcPath = "file/src/f.txt";
        try (
                OutputStream os = new FileOutputStream(srcPath);
                ObjectOutputStream oos = new ObjectOutputStream(os)
        ) {
            Car car = new Car();
            car.setAge(10);
            car.setName("测试");
            oos.writeObject(car);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
