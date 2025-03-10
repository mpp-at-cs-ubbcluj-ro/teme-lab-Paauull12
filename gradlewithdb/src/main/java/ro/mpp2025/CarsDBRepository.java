package ro.mpp2025;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class CarsDBRepository implements CarRepository{

    private JdbcUtils dbUtils;

    private static final Logger logger= LogManager.getLogger();

    public CarsDBRepository(Properties props) {
        logger.info("Initializing CarsDBRepository with properties: {} ",props);
        dbUtils=new JdbcUtils(props);
    }

    @Override
    public List<Car> findByManufacturer(String manufacturerN) {
        logger.traceEntry("getting cars by manufacturer");
        Connection con = dbUtils.getConnection();

        List<Car> cars = new ArrayList<>();

        try(PreparedStatement preparedStatement = con.prepareStatement("select * from Masini where manufacturer=?")) {

            preparedStatement.setString(1, manufacturerN);

            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while(resultSet.next()){
                    Car car = new Car(resultSet.getString("manufacturer"),resultSet.getString("model"),resultSet.getInt("year"));

                    int id = resultSet.getInt("id");
                    car.setId(id);

                    cars.add(car);
                }
            }
            logger.trace("Successfully got cars by manufacturer");
        }
        catch (SQLException e){
            logger.error(e);
            System.err.println("Error getting cars");
        }

        logger.traceExit();
        return cars;
    }

    @Override
    public List<Car> findBetweenYears(int min, int max) {
        logger.traceEntry("getting cars between years");
        Connection con = dbUtils.getConnection();

        List<Car> cars = new ArrayList<>();

        try(PreparedStatement preparedStatement = con.prepareStatement("select * from Masini where year between ? and ?")) {

            preparedStatement.setInt(1, min);
            preparedStatement.setInt(2, max);

            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while(resultSet.next()){
                    Car car = new Car(resultSet.getString("manufacturer"),resultSet.getString("model"),resultSet.getInt("year"));

                    int id = resultSet.getInt("id");
                    car.setId(id);

                    cars.add(car);
                }
            }
            logger.trace("Successfully got cars between years");
        }
        catch (SQLException e){
            logger.error(e);
            System.err.println("Error getting cars");
        }

        logger.traceExit();
        return cars;
    }

    @Override
    public void add(Car elem) {
        logger.traceEntry("saving task {}", elem);

        Connection con = dbUtils.getConnection();

        try(PreparedStatement preparedStatement = con.prepareStatement("insert into Masini (manufacturer, model, year) values (?, ?, ?)")){

            preparedStatement.setString(1, elem.getManufacturer());
            preparedStatement.setString(2, elem.getModel());
            preparedStatement.setInt(3, elem.getYear());
            int result = preparedStatement.executeUpdate();
            logger.trace("Successfully inserted {} instances", result);
        }catch (SQLException e){
            logger.error(e);
            System.err.println("Error inserting car");
        }

        logger.traceExit();
    }

    @Override
    public void update(Integer integer, Car elem) {
        logger.traceEntry("saving cars {}", elem);

        Connection con = dbUtils.getConnection();

        try(PreparedStatement preparedStatement = con.prepareStatement("update Masini set manufacturer=?, model=?, year=? where id=?")){

            preparedStatement.setString(1, elem.getManufacturer());
            preparedStatement.setString(2, elem.getModel());
            preparedStatement.setInt(3, elem.getYear());
            preparedStatement.setInt(4, integer);

            int result = preparedStatement.executeUpdate();
            logger.trace("Successfully updated {} instances", result);
        }catch (SQLException e){
            logger.error(e);
            System.err.println("Error inserting car");
        }

        logger.traceExit();
    }

    @Override
    public Iterable<Car> findAll() {
        logger.traceEntry("saving cars");
        Connection con = dbUtils.getConnection();

        List<Car> cars = new ArrayList<>();

        try(PreparedStatement preparedStatement = con.prepareStatement("select * from Masini")){

            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while(resultSet.next()){
                    Car car = new Car(resultSet.getString("manufacturer"),resultSet.getString("model"),resultSet.getInt("year"));

                    int id = resultSet.getInt("id");
                    car.setId(id);

                    cars.add(car);
                }
            }
            logger.trace("Successfully loaded cars");
        }
        catch (SQLException e){
            logger.error(e);
            System.err.println("Error getting cars");
        }

        logger.traceExit();
        return cars;
    }
}
