package br.com.alura.screenMatch.Spring.service.processdata_related;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvertSeriesData implements IConvertData {

    private ObjectMapper mapper = new ObjectMapper();


    @Override
    public <T> T convertData(String json, Class<T> classToConvertTo) {
        try {
            return mapper.readValue(json, classToConvertTo);
        }catch(ExceptionInInitializerError | JsonProcessingException e){
            throw new RuntimeException(e);
        }
    }
}
