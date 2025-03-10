package ro.mpp2025;

import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;
import java.util.Properties;
import java.util.stream.StreamSupport;

public class MainBD {
    public static void main(String[] args) {

        Properties props=new Properties();
        try {
            props.load(new FileReader("bd.config"));
        } catch (IOException e) {
            System.out.println("Cannot find bd.config "+e);
        }

        CarRepository carRepo=new CarsDBRepository(props);
        carRepo.add(new Car("Tesla","Model S", 2019));
        System.out.println("Toate masinile din db");
        for(Car car:carRepo.findAll())
            System.out.println(car);


        System.out.println("Updating car with ID 2 from the database");

        Optional<Car> optionalCar = StreamSupport.stream(carRepo.findAll().spliterator(), false)
                .filter(car -> car.getId() == 2)
                .findFirst();

        if (optionalCar.isPresent()) {
            Car car = optionalCar.get();

            car.setModel("Updated Model");
            car.setYear(2023);

            carRepo.update(car.getId(), car);

            System.out.println("Car updated successfully: " + car);
        } else {
            System.out.println("Car with ID 2 not found.");
        }

        String manufacturer="Tesla";
        System.out.println("Masinile produse de "+manufacturer);
        for(Car car:carRepo.findByManufacturer(manufacturer))
            System.out.println(car);

        for(Car car:carRepo.findBetweenYears(2018, 2021))
            System.out.println(car);

    }
}
