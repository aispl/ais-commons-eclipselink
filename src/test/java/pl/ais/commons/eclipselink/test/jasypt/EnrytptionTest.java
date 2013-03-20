package pl.ais.commons.eclipselink.test.jasypt;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import pl.ais.commons.eclipselink.test.dao.Table1Dao;
import pl.ais.commons.eclipselink.test.model.Table1;

@ContextConfiguration(locations = "classpath:pl/ais/commons/eclipselink/test/jasypt/h2.xml")
public class EnrytptionTest extends AbstractJUnit4SpringContextTests {

  @PersistenceContext
  protected EntityManager entityManager;

  @Autowired
  private Table1Dao table1Dao;

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Test
  public void testSearchByEnryptedColumn() {
    final String columnValue = "this 3";
    TypedQuery<Table1> query = entityManager.createQuery("select t from Table1 t where t.thisName = :thisName", Table1.class);
    query.setParameter("thisName", columnValue);
    List<Table1> result = query.getResultList();
    Assert.assertEquals("search using jpa query returns non-empty result", 1, result.size());
    Assert.assertEquals(columnValue, result.get(0).getThisName());

    Integer searchByEqualsCount = jdbcTemplate.queryForObject("select count(*) from table1 where this_name = ?", new SingleColumnRowMapper<Integer>(Integer.class), columnValue);
    Assert.assertEquals("search using sql query returns empty result", 0, searchByEqualsCount.intValue());

  }

  @Before
  public void createEntities() throws Exception {
    for (int i = 0; i < 10; i++) {
      Table1 t = new Table1();
      t.setThisName("this " + i);
      t.setThatName("that " + i);
      table1Dao.persist(t);
    }
  }

}
