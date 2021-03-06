package com.javed.spring.soap.web.service.config;

import com.mongodb.MongoClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;



@Configuration
@EnableWs
public class WSConfig extends WsConfigurerAdapter {

    private Logger logger = LoggerFactory.getLogger(WSConfig.class);

    @Bean
    public ServletRegistrationBean messageDispatcherServlet (ApplicationContext applicationContext) {
        MessageDispatcherServlet messageDispatcherServlet = new MessageDispatcherServlet();

        messageDispatcherServlet.setApplicationContext(applicationContext);
        messageDispatcherServlet.setTransformWsdlLocations(true);

        logger.debug("successfully setup application message dispatcher servlet");
        return new ServletRegistrationBean(messageDispatcherServlet, "/soapws/*");
    }

    @Bean(name = "articles")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema xsdSchema) {
        DefaultWsdl11Definition defaultWsdl11Definition = new DefaultWsdl11Definition();

        defaultWsdl11Definition.setPortTypeName("ArticlesPort");
        defaultWsdl11Definition.setLocationUri("/soapws");
        defaultWsdl11Definition.setTargetNamespace("http://www.concretepage.com/article-ws");
        defaultWsdl11Definition.setSchema(xsdSchema);

        logger.debug("configured default wsdl11 definition");
        return defaultWsdl11Definition;
    }

    @Bean
    public XsdSchema xsdSchema() {
        logger.debug("configured xsd schema for application");
        return new SimpleXsdSchema(new ClassPathResource("xsds/articles.xsd"));
    }

    @Bean
    MongoTemplate mongoTemplate() throws Exception {
        MongoTemplate mongoTemplate = new MongoTemplate(new MongoClient("127.0.0.1"),
                "articledb");
        return mongoTemplate;

    }
}
