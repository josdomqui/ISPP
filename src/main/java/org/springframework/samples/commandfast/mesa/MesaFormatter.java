package org.springframework.samples.commandfast.mesa;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

@Component
public class MesaFormatter implements Formatter<Mesa>{

	@Autowired
	private MesaService ms;
	
	@Override
	public String print(Mesa object, Locale locale) {
		Integer number = object.getNumber();
		return number.toString();
	}

	@Override
	public Mesa parse(String text, Locale locale) throws ParseException {
		Mesa m = this.ms.findMesaByNumber(Integer.parseInt(text));
		if(m==null) {
        	
        	throw new ParseException(text,0);
        	
        } else {
        	return m;
        }
	}
	

}
