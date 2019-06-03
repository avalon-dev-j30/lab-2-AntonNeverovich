package ru.avalon.java.j30.labs;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.*;


/**
 * Лабораторная работа №3
 * <p>
 * Курс: "DEV-OCPJP. Подготовка к сдаче сертификационных экзаменов серии Oracle Certified Professional Java Programmer"
 * <p>
 * Тема: "JDBC - Java Database Connectivity" 
 *
 * @author Daniel Alpatov <danial.alpatov@gmail.com>
 */
public class Main {

    /**
     * Точка входа в приложение
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, IOException {
        /*
         * TODO #01 Подключите к проекту все библиотеки, необходимые для соединения с СУБД.
         */
        try (Connection connection = getConnection()) {
            /*ProductCode code = new ProductCode("MO", 'N', "Movies");
            code.save(connection);
            printAllCodes(connection);

            code.setCode("MV");
            code.save(connection);
            printAllCodes(connection);*/
            ArrayList<ProductCode> a = (ArrayList<ProductCode>) ProductCode.all(connection);
            a.get(0).setDescription("uiyfhc");
            a.get(0).save(connection);
            printAllCodes(connection);
        }
        /*
         * TODO #14 Средствами отладчика проверьте корректность работы программы
         */
    }
    /**
     * Выводит в кодсоль все коды товаров
     * 
     * @param connection действительное соединение с базой данных
     * @throws SQLException 
     */    
    private static void printAllCodes(Connection connection) throws SQLException {
        Collection<ProductCode> codes = ProductCode.all(connection);
        for (ProductCode code : codes) {
            System.out.println(code);
        }
    }
    /**
     * Возвращает URL, описывающий месторасположение базы данных
     * 
     * @return URL в виде объекта класса {@link String}
     */
    private static String getUrl() {
        /*
         * TODO #02 Реализуйте метод getUrl
         */
        // Подключаемся к моему удаленному серверу. Сделал на нем отдельную БД
        // На сервере неправильно настроена Timezone. Сейчас проще подключиться так
        final String URL = "jdbc:mysql://185.11.246.52:3306/j30_lab_2_antonneverovich";
        final String TIMEZONE_VALUE = "?verifyServerCertificate=false"+
                                      "&useSSL=false"+
                                      "&requireSSL=false"+
                                      "&useLegacyDatetimeCode=false"+
                                      "&amp"+
                                      "&serverTimezone=UTC";
        return URL + TIMEZONE_VALUE;
    }
    /**
     * Возвращает параметры соединения
     * 
     * @return Объект класса {@link Properties}, содержащий параметры user и 
     * password
     */
    private static Properties getProperties() {
        /*
         * TODO #03 Реализуйте метод getProperties
         */
        final String PATH_TO_PROPERTIES = "src/ru/avalon/java/j30/labs/resources/config.properties";
        Properties properties = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream(PATH_TO_PROPERTIES);
            properties.load(fileInputStream);
            fileInputStream.close();
        } catch (IOException e) {
            System.out.println("Property not found!..");
            e.printStackTrace();
        }
        return properties;
    }
    /**
     * Возвращает соединение с базой данных Sample
     * 
     * @return объект типа {@link Connection}
     */
    private static Connection getConnection() throws SQLException {
        /*
         * TODO #04 Реализуйте метод getConnection
         */
        Connection connection = DriverManager.getConnection(getUrl(), getProperties());
        System.out.println("Connection successful!...");

        return connection;
    }
    
}
