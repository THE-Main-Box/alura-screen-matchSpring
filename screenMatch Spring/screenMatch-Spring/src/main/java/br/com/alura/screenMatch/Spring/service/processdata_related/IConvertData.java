package br.com.alura.screenMatch.Spring.service.processdata_related;

public interface IConvertData {

    <T> T convertData(String json, Class<T> classToConvertTo);

}
