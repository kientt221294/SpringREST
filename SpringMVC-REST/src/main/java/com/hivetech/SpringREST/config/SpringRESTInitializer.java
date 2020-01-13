package com.hivetech.SpringREST.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class SpringRESTInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{
        @Override
        protected Class<?>[] getRootConfigClasses() {
            return null;
        }

        @Override
        protected Class<?>[] getServletConfigClasses() {
            return new Class[] { SpringRESTConfig.class };
        }

        @Override
        protected String[] getServletMappings() {
            return new String[] { "/" };
        }
}
