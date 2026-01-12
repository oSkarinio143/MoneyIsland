package pl.oskarinio.moneyisland.gateway.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ServerWebExchange;

@Slf4j
@Controller
class CustomErrorController implements ErrorController {

    @RequestMapping(Route.ERROR)
    public String handleError(ServerWebExchange exchange) {
        Object status = exchange.getResponse().getStatusCode();
        log.error("Error - {}", status);
        return Route.REDIRECT;
    }
}
