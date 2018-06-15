package nl.hro.stkb.server;

import org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider;
import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;

import java.io.IOException;
import java.net.URI;


public class Server {
    private static Logger logger = LoggerFactory.getLogger(DatabaseImplementation.class);
    private static Database DB_INSTANCE;
    private int port = 8024;

    public Server(){
        URI uri = URI.create("http://145.24.253.34"+ port);
        ResourceConfig config = new ResourceConfig(BankEndpoint.class);
        config.register(JacksonJaxbJsonProvider.class);
        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(uri, config, true);

        try {
            server.start();
            logger.info("server is gestart op port "+port);
            while (true){
                Thread.sleep(1000);
            }
        } catch (IOException e) {
            logger.error("server is niet gestart");
        } catch (InterruptedException e) {
            logger.error("Thread is interupted");
        }
    }

    public static void main(String[] args) {
        DB_INSTANCE = new DatabaseImplementation();

       logger.info("Database instantie is "+ DB_INSTANCE.getClass().toString());

        new Server();

    }


    public static Database getDatabase() {
        return DB_INSTANCE;
    }
}
