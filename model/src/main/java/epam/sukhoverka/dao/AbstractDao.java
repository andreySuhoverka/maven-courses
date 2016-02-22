package epam.sukhoverka.dao;

import epam.sukhoverka.dao.enricher.Enricher;
import epam.sukhoverka.dao.extractor.Extractor;
import epam.sukhoverka.dao.jdbc.ConnectionFactory;
import epam.sukhoverka.dao.jdbc.ConnectionFactoryFactory;
import epam.sukhoverka.exception.system.NoSuchEntityException;
import epam.sukhoverka.exception.system.dao.DBSystemException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public abstract class AbstractDao<T> {

    protected final ConnectionFactory factory = ConnectionFactoryFactory.getConnectionFactory();
    private static Logger logger = Logger.getLogger(AbstractDao.class);

    protected T getModelByLogin(String login, String SQL, Extractor<T> extractor)
            throws DBSystemException,NoSuchEntityException {

        T model = null;
        Connection dbConnection = null;
        try {
            dbConnection = factory.getConnection();
            PreparedStatement ps = null;
            try {
                ps = dbConnection.prepareStatement(SQL);
                ps.setString(1, login);
                ResultSet rs = null;
                logger.debug(ps.unwrap(PreparedStatement.class).toString());
                try {
                    rs = ps.executeQuery();

                    if (rs.next() == true) {
                        model = extractor.extractOne(rs);
                    } else {
                        //almost never can be thrown, only if you make an error creating database
                        throw new NoSuchEntityException(login + " is incorrect");
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new DBSystemException("error when reading database");
                } catch (NoSuchEntityException e) {
                    e.printStackTrace();
                    throw new DBSystemException("error in creating database", e);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new DBSystemException("prepare statement isn't created");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBSystemException("can't connect to database", e);
        } finally {
            try {
                dbConnection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new DBSystemException("error on closing connection", e);
            }
        }
        return model;

    }

    protected List<T> selectAll(String SQL , Extractor<T> extractor, Enricher<T> enricher) throws DBSystemException, NoSuchEntityException {

        List<T> result = new ArrayList<>();

        try(Connection dbConnection = factory.getConnection();
            PreparedStatement st = dbConnection.prepareStatement(SQL);
        ) {

            ResultSet rs = null;
            rs = st.executeQuery();

            while (rs.next()){
                T record = extractor.extractOne(rs);
                enricher.enrich(record);
                result.add(record);
            }
            logger.debug(st.unwrap(PreparedStatement.class).toString());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBSystemException("", e);
        }
        return result;

    }
}
