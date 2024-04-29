package br.com.alura.screenMatch.Spring.service;

public interface IConvertData {

    <T> T convertData(String json, Class<T> classToConvertTo);

}
