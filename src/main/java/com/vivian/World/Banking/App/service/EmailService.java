package com.vivian.World.Banking.App.service;

import com.vivian.World.Banking.App.payload.request.EmailDetails;
import org.springframework.beans.factory.annotation.Value;

public interface EmailService{

    void sendEmailAlert(EmailDetails emailDetails);

    void sendEmailWithAttachment(EmailDetails emailDetails);
}
