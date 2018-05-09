package com.appointment.system;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.stereotype.Component;

@Component
public class JsonDateSerializer  extends JsonDeserializer<LocalDateTime>
{
	@Override
	public LocalDateTime deserialize(JsonParser jsonParser,
			DeserializationContext deserializationContext) throws IOException, JsonProcessingException {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		String date = jsonParser.getText();
		try {
			return LocalDateTime.parse(date);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

}
