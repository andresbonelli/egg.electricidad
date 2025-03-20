package com.egg.electricidad.api.controller.advice;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public RedirectView handleFrontendException(Exception ex, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        String referer = request.getHeader("Referer"); // Get previous page
        if (referer == null || referer.isBlank()) {
            referer = "/"; // Default redirect if no referer is found
        }

        redirectAttributes.addFlashAttribute("error", ex.getMessage());
        return new RedirectView(referer);
    }

}
