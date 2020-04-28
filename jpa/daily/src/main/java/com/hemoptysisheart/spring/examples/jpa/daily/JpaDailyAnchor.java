package com.hemoptysisheart.spring.examples.jpa.daily;

/**
 * @since 2020/04/28
 */
public abstract class JpaDailyAnchor {
  public static final Package PACKAGE = JpaDailyAnchor.class.getPackage();
  public static final String PACKAGE_NAME = PACKAGE.getName();

  protected JpaDailyAnchor() {
    throw new UnsupportedOperationException();
  }
}
