package com.guezey.gms.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.XsdSchemaCollection;
import org.springframework.xml.xsd.commons.CommonsXsdSchemaCollection;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {
    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean<>(servlet, "/ws/*");
    }

    @Bean(name = "schema")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchemaCollection schemaCollection) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("GaragePort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://guezey.com/gms/xml/request");
        wsdl11Definition.setSchemaCollection(schemaCollection);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchemaCollection schemaCollection() {
        CommonsXsdSchemaCollection commonsXsdSchemaCollection = new CommonsXsdSchemaCollection(
                new ClassPathResource("garage.xsd"),
                new ClassPathResource("garage-request.xsd"));
        commonsXsdSchemaCollection.setInline(true);
        return commonsXsdSchemaCollection;
    }
}