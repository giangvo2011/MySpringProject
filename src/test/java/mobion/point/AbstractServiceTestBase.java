package mobion.point;

import java.util.TimeZone;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@ContextConfiguration(locations={
		"classpath*:test-context.xml"
		})

@RunWith(value=SpringJUnit4ClassRunner.class)
public class AbstractServiceTestBase extends AbstractJUnit4SpringContextTests{
	
	static{
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}
	
	@Autowired
	private ApplicationContext applicationContext;
	
	
	public ApplicationContext getContext() {
		return applicationContext;
	}
}
