package br.com.tcc.user.microservice.configuration;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

import br.com.tcc.user.microservice.configuration.to.AuthorizerResponseTO;

@Configuration
public class SecurityFilter implements Filter {
	
	private EurekaClient eurekaClient;
	private RestTemplate restTemplate;
	
	@Value("${application.acccess.authorizer}")
	private String authorizer;
	
	@Value("${spring.application.name}")
	private String applicationName;
	
	@Autowired
	public SecurityFilter(EurekaClient eurekaClient, RestTemplate restTemplate) {
		this.eurekaClient = eurekaClient;
		this.restTemplate = restTemplate;
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		
		try {
			InstanceInfo instanceInfo = this.eurekaClient.getNextServerFromEureka(authorizer, Boolean.FALSE);
			
			HttpServletRequest request = (HttpServletRequest) req;
			HttpHeaders httpHeaders = getHeaders(request);
			HttpEntity<String> entity = new HttpEntity<>("heades", httpHeaders);		
			
			ResponseEntity<AuthorizerResponseTO> responseEntity = this.restTemplate.exchange(instanceInfo.getHomePageUrl(), HttpMethod.GET, entity, AuthorizerResponseTO.class);
			AuthorizerResponseTO authorizerResponseTO = responseEntity.getBody();
			res.setContentType(MediaType.APPLICATION_JSON_VALUE);
			
			if(HttpStatus.OK.value() == authorizerResponseTO.getRequestStatus()) {
				chain.doFilter(req, res);
			
			} else {
				res.getWriter().append("{\"message\": \"" + authorizerResponseTO.getMessage() + "\"}");
			}
			
		} catch (RestClientException e) {
			res.getWriter().append("{\"message\": \"not authorized\", \"error\":" + e.getMessage() + "\"}");
			e.printStackTrace();
		
		} catch (Exception e) {
			res.getWriter().append("{\"message\": \"not authorized\", \"error\":" + e.getMessage() + "\"}");
			e.printStackTrace();
		}
		
		
	}

	private HttpHeaders getHeaders(HttpServletRequest request) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		httpHeaders.add("Authentication", request.getHeader("Authentication"));
		httpHeaders.add("appname", applicationName);
		httpHeaders.add("http_method", request.getMethod());
		httpHeaders.add("route", request.getRequestURI());

		return httpHeaders;
	}
}
