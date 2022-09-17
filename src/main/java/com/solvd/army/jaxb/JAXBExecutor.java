package com.solvd.army.jaxb;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

import java.util.ArrayList;
import java.util.Arrays;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class JAXBExecutor {
    private static final Logger logger = LogManager.getLogger(JAXBExecutor.class);

    public static void main(String[] args) {

        Car car1 = new Car("Mercedes", 1999, "Blue");
        Car car2 = new Car("BMW", 2000, "Yellow");
        Car car3 = new Car("Audi", 2007, "White");

        ArrayList<Car> cars = new ArrayList<Car>(Arrays.asList(car1, car2, car3));
        // toXML
        try {
            JAXBContext context = JAXBContext.newInstance(Cars.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            // File file = new File("src/main/java/com/solvd/army/jaxb/jaxbExmp.xml");
            marshaller.marshal(new Cars(cars), new File("src/main/java/com/solvd/army/jaxb/jaxbExmp.xml"));

        } catch (JAXBException e) {
            e.printStackTrace();
        }

        // fromXML
        try {
            Cars cars1 = new Cars();
            JAXBContext context = JAXBContext.newInstance(Cars.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            cars1 = (Cars) unmarshaller.unmarshal(new File("src/main/java/com/solvd/army/jaxb/jaxbExmp.xml"));
            logger.info(cars1);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }

}
