package edu.gmu.swe681.checkers.config;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/applicationContext.xml", "file:src/main/webapp/WEB-INF/spring/spring-security.xml", "file:src/main/webapp/WEB-INF/spring/webmvc-config.xml"})
@TransactionConfiguration(defaultRollback=true, transactionManager="transactionManager")
public abstract class BaseIntegrationTest {

}
