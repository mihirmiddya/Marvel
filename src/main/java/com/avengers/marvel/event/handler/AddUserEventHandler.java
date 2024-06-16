package com.avengers.marvel.event.handler;

import com.avengers.marvel.event.model.Event;
import com.avengers.marvel.mail.service.MailerService;
import com.avengers.marvel.user.model.User;
import com.avengers.marvel.user.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;

import static com.avengers.marvel.event.constants.EventConstants.ADD_USER_EVENT;
import static com.avengers.marvel.mail.constants.MailConstants.DEFAULT_BODY;
import static com.avengers.marvel.mail.constants.MailConstants.DEFAULT_SUBJECT;

@Component
public class AddUserEventHandler implements EventHandler {

    private static final Logger logger = LoggerFactory.getLogger(AddUserEventHandler.class);

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MailerService mailerService;

    @Override
    public boolean isSupport(String type) {
        return ADD_USER_EVENT.equalsIgnoreCase(type);
    }

    @Override
    public void trigger(Event event) {
        try {
            Map<String, Objects> data = objectMapper.readValue(event.getData(), Map.class);
            int userid = MapUtils.getIntValue(data, "userid");
            User user = userRepository.findById(userid).orElseThrow(() -> new NoSuchElementException("User not found!"));
            mailerService.sendMail(user.getEmail(), DEFAULT_SUBJECT, String.format(DEFAULT_BODY, user.getName()));
            logger.info("Event triggered for type :{}", event.getType());
        } catch (Exception ex) {
            logger.info("Error while running event type :{}", event.getType());
        }
    }
}
