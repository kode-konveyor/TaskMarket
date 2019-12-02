package com.kodekonveyor.market;

import java.util.Iterator;

import org.slf4j.Marker;

import com.kodekonveyor.annotations.ExcludeFromCodeCoverage;
import com.kodekonveyor.annotations.InterfaceClass;

import lombok.Getter;

@Getter
@InterfaceClass
@ExcludeFromCodeCoverage("interface")
class LogMarker implements Marker {

  private static final long serialVersionUID = 1L;
  private final String name;

  LogMarker(final String name) {
    this.name = name;
  }

  @Override
  public void add(final Marker reference) {
  }

  @Override
  public boolean contains(final Marker other) {
    return false;
  }

  @Override
  public boolean contains(final String name) {
    return false;
  }

  @Override
  @SuppressWarnings("deprecation")
  public boolean hasChildren() {
    return false;
  }

  @Override
  public boolean hasReferences() {
    return false;
  }

  @Override
  public Iterator<Marker> iterator() {
    return null;
  }

  @Override
  public boolean remove(final Marker reference) {
    return false;
  }

  @Override
  public String toString() {
    return name;
  }

}
