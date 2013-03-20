package pl.ais.commons.eclipselink.jasypt;

import java.util.Map;

import org.jasypt.encryption.StringEncryptor;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.springframework.util.Assert;

public class EncryptingEclipseLinkJpaVendorAdapter extends EclipseLinkJpaVendorAdapter {

  public static final String PROPERTY_NAME = "pl.ais.commons.eclipselink.jasypt.encryptor";

  private StringEncryptor encryptor;

  @Override
  public Map<String, Object> getJpaPropertyMap() {
    Assert.notNull(encryptor, "Encryptor cannot be null");
    Map<String, Object> result = super.getJpaPropertyMap();
    result.put(PROPERTY_NAME, encryptor);
    return result;
  }

  public void setEncryptor(StringEncryptor encryptor) {
    this.encryptor = encryptor;
  }

}
