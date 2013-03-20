package pl.ais.commons.eclipselink.test.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.eclipse.persistence.annotations.Convert;
import org.eclipse.persistence.annotations.Converter;

@Entity
@Table(name = "table1")
public class Table1 {

  private Long id;
  private String thisName;
  private String thatName;

  @Id
  @GeneratedValue
  @Column(name = "id")
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Column(name = "this_name")
  @Convert("Encrypting")
  public String getThisName() {
    return thisName;
  }

  public void setThisName(String thisName) {
    this.thisName = thisName;
  }

  @Column(name = "that_name")
  public String getThatName() {
    return thatName;
  }

  public void setThatName(String thatName) {
    this.thatName = thatName;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    Table1 other = (Table1) obj;
    if (id == null) {
      if (other.id != null) {
        return false;
      }
    } else if (!id.equals(other.id)) {
      return false;
    }
    return true;
  }

}
