package com.example.advance.form;

public interface FormConvert<S, T> {
	T convert(S s);
}
