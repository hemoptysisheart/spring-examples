package com.hemoptysisheart.spring.examples.jpa.daily;

/**
 * @since 2020/04/28
 */
public abstract class DailyAnchor {
  public static final Package PACKAGE = DailyAnchor.class.getPackage();
  public static final String PACKAGE_NAME = PACKAGE.getName();

  protected DailyAnchor() {
    throw new UnsupportedOperationException();
  }
}
