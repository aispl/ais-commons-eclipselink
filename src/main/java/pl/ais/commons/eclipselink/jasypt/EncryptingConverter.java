package pl.ais.commons.eclipselink.jasypt;

import org.eclipse.persistence.mappings.DatabaseMapping;
import org.eclipse.persistence.mappings.converters.Converter;
import org.eclipse.persistence.sessions.Session;
import org.jasypt.encryption.StringEncryptor;

public class EncryptingConverter implements Converter {

  private StringEncryptor encryptor;

  @Override
  public Object convertObjectValueToDataValue(Object objectValue, Session session) {
    if (objectValue == null) {
      return null;
    }
    return encryptor.encrypt(String.valueOf(objectValue));
  }

  @Override
  public Object convertDataValueToObjectValue(Object dataValue, Session session) {
    if (dataValue == null) {
      return null;
    }
    return encryptor.decrypt(String.valueOf(String.valueOf(dataValue)));
  }

  @Override
  public boolean isMutable() {
    return false;
  }

  @Override
  public void initialize(DatabaseMapping mapping, Session session) {
    encryptor = (StringEncryptor) session.getProperty(EncryptingEclipseLinkJpaVendorAdapter.PROPERTY_NAME);
  }
}
