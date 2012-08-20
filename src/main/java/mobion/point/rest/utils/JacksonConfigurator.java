package mobion.point.rest.utils;

import javax.ws.rs.ext.Provider;

import org.codehaus.jackson.JsonGenerator;
import org.jboss.resteasy.plugins.providers.jackson.ResteasyJacksonProvider;

/**
 * @author giangvo
 * Use to config jackson
 */
@Provider
public class JacksonConfigurator extends ResteasyJacksonProvider{

	public JacksonConfigurator() {
		super();
		configure(JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS, true);
	}
}
