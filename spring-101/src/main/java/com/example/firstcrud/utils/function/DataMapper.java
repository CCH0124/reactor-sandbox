package com.example.firstcrud.utils.function;

public interface DataMapper<S, D> {
  D map(final S source);
}
