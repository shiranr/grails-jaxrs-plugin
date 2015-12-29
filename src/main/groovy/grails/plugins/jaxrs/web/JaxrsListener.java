/*
 * Copyright 2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package grails.plugins.jaxrs.web;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Initializes a Jersey servlet for request processing.
 *
 * @author Martin Krasser
 */
public class JaxrsListener implements ServletContextListener {

    private static final Log LOG = LogFactory.getLog(JaxrsListener.class);

    public void contextDestroyed(ServletContextEvent event) {
        JaxrsUtils.getRequiredJaxrsContext(event.getServletContext()).destroy();
    }

    public void contextInitialized(ServletContextEvent event) {
        JaxrsContext jaxrsContext = JaxrsUtils.getRequiredJaxrsContext(event.getServletContext());
        jaxrsContext.setJaxrsServletContext(event.getServletContext());

        try {
            jaxrsContext.init();
        } catch (ServletException e) {
            LOG.error("Initialization of JAX-RS context failed", e);
        }
    }
}
