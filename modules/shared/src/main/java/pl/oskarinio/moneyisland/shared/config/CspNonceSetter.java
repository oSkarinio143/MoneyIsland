package pl.oskarinio.moneyisland.shared.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class CspNonceSetter {
    @ModelAttribute
    public void addNonce(HttpServletRequest req, Model model) {
        String nonce = req.getHeader("X-CSP-Nonce");
        if (nonce!=null) model.addAttribute("cspNonce", nonce);
    }
}