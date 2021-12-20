1. Create a web project
2. Add spring web dependency (5.3.6)
a) spring-core
b) spring-context
c) spring-bean
d) spring-web
e) spring-webmvc

show the jsp page add two dependency
jstl- 1.2
javax.jsp-api -2.5

4. configure web xml file

<listener>
        <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
    </listener>    
    <context-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>/WEB-INF/app-servlet.xml</param-value>
    </context-param>
    
    <servlet>
        <servlet-name>app</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <load-on-startup>1</load-on-startup>
    </servlet>
    
    <servlet-mapping>
        <servlet-name>app</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>


5. added servlet
-------------------app-servlet---------------------
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:c="http://www.springframework.org/schema/c"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:p="http://www.springframework.org/schema/p"
       xmlns:tx="http://www.springframework.org/schema/tx"

       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
          http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd
          http://www.springframework.org/schema/mvc 
        http://www.springframework.org/schema/mvc/spring-mvc.xsd
          http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx.xsd
">
    <mvc:annotation-driven />
    <context:component-scan base-package="com.example"/>
    <mvc:default-servlet-handler />
    
    <bean id="viewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
		<property name="prefix" value="/WEB-INF/view/" />
		<property name="suffix" value=".jsp" />
	</bean>

    
    <mvc:resources mapping="/assets/**" location="/assets/" cache-period="31556926" />

</beans>

--------------------------tutorial----about servlet bean-------------------

6. <mvc:annotation-driven /> = Using to configure annotation base 
<context:component-scan base-package="com.example"/>
(here give your project base path)


<bean id="viewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
		<property name="prefix" value="/WEB-INF/view/" />
		<property name="suffix" value=".jsp" />
	</bean>
---It is jsp page resolver when we are mapping jsp page just give page name. It's show autoometically.

    <mvc:resources mapping="/assets/**" location="/assets/" cache-period="31556926" />

--- to point assets and give associate permission also give cache period.


-------------------------controller--------------------------

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Instructor
 */

@Controller
public class HomeController {
    
    @GetMapping("/")
    public String helloWorld(){
        return "index";
    }
    
}
