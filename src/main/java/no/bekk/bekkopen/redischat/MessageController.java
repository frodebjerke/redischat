package no.bekk.bekkopen.redischat;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/message")
public class MessageController {

	@Autowired
	MessageService messageService;
	
	private static Logger log = Logger.getLogger(MessageController.class.getName());
		
	@RequestMapping(method=RequestMethod.GET)
	public @ResponseBody Message[] getMessages() {
		Message[] messages = messageService.getMessages();
		log.info("Messages read: " + messages.length);
		return messages;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<String> post(@RequestBody Message m) {
		log.info("Posting message from user: " + m.username);

		// by default firefox parses all ajax POST responses as XML, so
		// an empty response will lead to a parsing error ("element not found")
		// unless we set the response type explicitly.
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setContentType(MediaType.TEXT_PLAIN);
		
		if (messageService.postMessage(m)) {
			return new ResponseEntity<String>(responseHeaders, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
