package epam.sukhoverka.dao.jdbc;


public class ConnectionFactoryFactory {

    public enum FactoryType {RAW, C3P0}
    
    static {
        try {
            setType(FactoryType.C3P0);
        } catch (Exception e) {
            throw new IllegalArgumentException("Exception while initializing Connection Factory");
        }
    }

    private static ConnectionFactory connectionFactory =  ConnectionFactoryJDBC.getInstance();

    public synchronized static ConnectionFactory getConnectionFactory() {
        return connectionFactory;
    }

    public static synchronized void setType(FactoryType type) throws Exception {

        switch (type){
            case RAW:
                connectionFactory = ConnectionFactoryJDBC.getInstance();
                break;
            case C3P0:
                connectionFactory = ConnectionFactoryC3P0.getInstance();
                break;
            default:
                throw new IllegalArgumentException("Unknown type of Connection Factory: " + type);
        }

    }
}
