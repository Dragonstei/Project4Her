package nl.hro.stkb.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class DatabaseImplementation implements Database {
    private static final Logger logger = LoggerFactory.getLogger(DatabaseImplementation.class);
    private Connection connection ;
    private String host;
    private String userName;
    private String userPassword;
    private ResultSet rs;

    public DatabaseImplementation(){
        connection = null;
        host = "jdbc:mysql://localhost:3306/STK?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        userName = "root";
        userPassword = "24cPP4";
        this.rs =  null;

        this.connect();
    }

    private void connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(host, userName, userPassword);
            logger.info("Connection to the database established");
        } catch (Exception e) {
            logger.error("connection to the database failed,", e);

        }
    }

    //@Override
    public long getBalance(String rekeningNr) {
        long saldo = 0;

        try {
            PreparedStatement ps = connection.prepareStatement("SELECT saldo FROM rekening WHERE rekening.iban = ? ");

            ps.setString(1, rekeningNr);
            rs = ps.executeQuery();

            rs.next();
            saldo = rs.getInt("saldo");
            logger.debug("Saldo van "+rekeningNr+" = "+saldo);

        } catch (SQLException e) {
            logger.error("execution of query select saldo failed", e);
        }
        return saldo;
    }

   // @Override
    public boolean withdraw(String rekeningNr, long amount) {
        try {
            long saldo = getBalance(rekeningNr);
            if (saldo >= amount) {
                logger.debug("IBAN: {}\t Saldo: {} ", rekeningNr, saldo);

                PreparedStatement ps = connection.prepareStatement("UPDATE rekening SET saldo = ? WHERE iban = ?");
                ps.setLong(1, (saldo - amount));
                ps.setString(2, rekeningNr);

                boolean rs = ps.execute();

                if (logger.isDebugEnabled()) {
                    logger.debug("Mieuw saldo: {}", getBalance(rekeningNr));
                }
                return true;
            }
            logger.debug("Saldo is ontoreikend");
            return false;
        }
        catch (SQLException e){
            logger.error("Execution of query withdraw failed", e);
        }
        return false;
    }

   // @Override
    public boolean verifyCard(String uid, String iban ){
        try{
            PreparedStatement ps = connection.prepareStatement("SELECT pasnr FROM pinpas WHERE pasnr = ? AND rekening_iban = ?");
            ps.setString(1, uid);
            ps.setString(2, iban);

            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                logger.info("pas "+uid+" met IBAN "+iban+" bestaat");
                return true;
            }
            else {

                return false;
            }
        }
        catch (SQLException e){
            logger.error("Query to verify pin is mislukt");
        }

        return false;
    }

   // @Override
    public boolean verifyPin(String uid, String pin){
        try{
            PreparedStatement ps = connection.prepareStatement("SELECT blokkade FROM pinpas WHERE pasnr = ?");
            ps.setString(1, uid);
            ResultSet rs = ps.executeQuery();
            rs.next();
            int blok = rs.getInt("blokkade");

            if(blok == 1){
                return false;
            }
            else {
                PreparedStatement ps2 = connection.prepareStatement("SELECT pincode FROM pinpas WHERE pasnr = ? AND pincode = ?");
                ps2.setString(1, uid);
                ps2.setString(2, pin);

                ResultSet rs2 = ps2.executeQuery();

                if (rs2.next()){

                    PreparedStatement ps3 = connection.prepareCall("UPDATE pinpas SET attempts = 0 WHERE pasnr = ?");
                    ps3.setString(1,uid);
                    ps3.execute();

                    return true;
                }
                else {
                    int plus = getAttemps(uid);
                    plus+=1;

                    PreparedStatement ps4 = connection.prepareCall("UPDATE pinpas SET attempts = ? WHERE pasnr = ?");
                    ps4.setInt(1, plus);
                    ps4.setString(2,uid);
                    ps4.execute();




                    if (getAttemps(uid) >= 3){
                        PreparedStatement ps5 = connection.prepareCall("UPDATE pinpas SET blokkade = 1 WHERE pasnr = ?");
                        ps5.setString(1,uid);
                        ps5.execute();
                        return false;
                    }
                    return false;
                }
            }


        }
        catch (SQLException e){
            logger.error("Query to update blokkade kon niet worden uitgevoerd - Error: 1");
        }

        return false;
    }


    //@Override
    public int getAttemps(String uid){
        int pogingen = 0;
        try{
            PreparedStatement ps = connection.prepareStatement("SELECT attempts FROM pinpas WHERE pasnr = ?");
            ps.setString(1, uid);
            ResultSet rs = ps.executeQuery();
            rs.next();
            pogingen = rs.getInt("attempts");
            return pogingen;
        }
        catch (SQLException e){
            logger.error("Query to get attempts kon niet worden uitgevoerd - Error: 2");
        }

        return pogingen;
    }

   // @Override
    public int getblokkade(String uid){
        int blokkade = 0;
        try{
            PreparedStatement ps = connection.prepareStatement("SELECT blokkade FROM pinpas WHERE pasnr = ?");
            ps.setString(1, uid);
            ResultSet rs = ps.executeQuery();
            rs.next();
            blokkade = rs.getInt("blokkade");
            return blokkade;
        }
        catch (SQLException e){
            logger.error("Query to get blokkade kon niet worden uitgevoerd - Error: 3");
        }

        return blokkade;
    }

}
