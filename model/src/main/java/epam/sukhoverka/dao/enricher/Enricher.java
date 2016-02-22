package epam.sukhoverka.dao.enricher;


import epam.sukhoverka.exception.system.NoSuchEntityException;
import epam.sukhoverka.exception.system.dao.DBSystemException;

public interface Enricher<T> {

    public static final Enricher NULL = (Object object) ->{};       

    public void enrich(T record) throws  DBSystemException, NoSuchEntityException;
}
