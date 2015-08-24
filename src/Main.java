/**
 * Created by DotinSchool2 on 8/24/2015.
 */

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Main {
    ArrayList<Deposit> depositList = new ArrayList<Deposit>();
    public static void main(String[] args) {
        Main main = new Main();
        Deposit deposit = null;
        // Read from file
        File xmlFile = new File("src\\myfile.xml");
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = null;
        try {
            db = dbf.newDocumentBuilder();
            if (xmlFile.exists()) {
                Document doc = db.parse(xmlFile);
                Element element = doc.getDocumentElement();
                Element el;
                NodeList customerNumberList = element.getElementsByTagName("deposit");
                if (customerNumberList != null && customerNumberList.getLength() > 0) {
                    for (int i = 0; i < customerNumberList.getLength(); i++) {
                        Node node = customerNumberList.item(i);
                        el = (Element) node;
                        String  customerNumber = el.getElementsByTagName("customerNumber").item(0).getTextContent();
                        String depositType = el.getElementsByTagName("depositType").item(0).getTextContent();
                        BigDecimal depositBalance = BigDecimal.valueOf(Long.parseLong(el.getElementsByTagName("depositBalance").item(0).getTextContent()));
                        int durationDays = Integer.parseInt(el.getElementsByTagName("durationDays").item(0).getTextContent());
                        if (depositBalance.compareTo(BigDecimal.ZERO) == -1) {
                            throw new BalanceException();
                        }
                        if (durationDays <= 0) {
                            throw new DurationException();
                        }
                        try {
                            Object obj = Class.forName(depositType).newInstance();
                            deposit = new Deposit(depositBalance, durationDays, customerNumber, (DepositType) obj);

                        }catch (ClassNotFoundException e){
                            throw new DepositException();
                        }catch(InstantiationException e){
                            e.printStackTrace();
                        }
                        deposit.calculatePaiedInterest();
                        main.depositList.add(deposit);
                    }
                    // sort array
                    Collections.sort(main.depositList);
                    // print in decreasing way
                    System.out.println(main.depositList);
                    // Write to file
                    File file = new File("src\\file.txt");
                    if(!file.exists()){
                        file.createNewFile();
                    }
                    RandomAccessFile fileWrite = new RandomAccessFile(file,"rw");
                    fileWrite.writeBytes(main.depositList.toString());
                    fileWrite.writeBytes("\r\n");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
