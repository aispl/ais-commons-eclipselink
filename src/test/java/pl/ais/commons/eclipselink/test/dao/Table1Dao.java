package pl.ais.commons.eclipselink.test.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pl.ais.commons.eclipselink.test.model.Table1;

@Repository(Table1Dao.NAME)
public class Table1Dao {

  public static final String NAME = "Table1Dao";

  @PersistenceContext
  protected EntityManager entityManager;

  @Transactional
  public void persist(Table1 t) {
    entityManager.persist(t);
  }

}
