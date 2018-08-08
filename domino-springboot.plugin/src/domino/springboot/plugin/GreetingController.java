package domino.springboot.plugin;

import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.domino.osgi.core.context.ContextInfo;

import lotus.domino.NotesException;

@RestController
public class GreetingController {

	Logger log = LoggerFactory.getLogger(GreetingController.class);
	
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    /**
     * sendet die Klasse "Greeting" als JSON zurück.
     * Pfad: "/greeting"
     * @return
     */
    @RequestMapping(value = "/greeting", produces = "application/json")
    public Greeting greeting() {
    	
    	String name = null;
		try {
			name = ContextInfo.getUserSession().getEffectiveUserName();
		} catch (NotesException e) {
			e.printStackTrace();
		}
		log.info("Username: " + name );
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }
    
    /**
     * Methode sendet generell den Forbidden-Statuscode zurück
     * Pfad: /forbidden
     */
    @RequestMapping( "/forbidden")
    public ResponseEntity<?> sendStatusCodeForbidden( ){
		return ResponseEntity.status( HttpStatus.FORBIDDEN ).build();
    }
    
   /**
    * limit the request to POST only and get
    * 
    * @param header
    * 	read a String from the HTTP header data
    * @param data
    * 	get an object from HTTP Body
    * @return
    */
    @RequestMapping( value = "/postOnly", method = RequestMethod.POST )
    public ResponseEntity<?> postOnly( @RequestHeader("header") String header, @RequestBody Greeting data ){
    	return ResponseEntity.ok( "header = " + header );
    }
    
    /**
     * access the servlet data
     * @param request
     * @return
     */
    @RequestMapping( "/servletInfo" )
    public ResponseEntity<?> getServletInfo(HttpServletRequest request ){
    	
    	System.out.println("Remote IP:" + request.getRemoteAddr() );
    	return ResponseEntity.status( HttpStatus.OK ).build();
    }
}