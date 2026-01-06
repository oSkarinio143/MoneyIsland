package pl.oskarinio.moneyisland.finance.infrastructure.adapter.in;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.oskarinio.moneyisland.finance.domain.dto.Asset;
import pl.oskarinio.moneyisland.finance.domain.dto.form.AssetAddForm;
import pl.oskarinio.moneyisland.finance.domain.dto.form.AssetModifyForm;
import pl.oskarinio.moneyisland.finance.application.port.balance.DeleteUserBalancePanelUseCase;
import pl.oskarinio.moneyisland.finance.application.port.balance.LoadUserBalancePanelsUseCase;
import pl.oskarinio.moneyisland.finance.application.port.balance.ModifyUserBalancePanelUseCase;
import pl.oskarinio.moneyisland.finance.application.port.balance.SaveUserBalancePanelUseCase;
import pl.oskarinio.moneyisland.shared.config.Route;

import java.util.List;

@Controller
@RequestMapping(Route.MAIN + Route.FINANCE + Route.USER + Route.BALANCE)
public class BalanceController {
    private final SaveUserBalancePanelUseCase saveUserBalancePanel;
    private final LoadUserBalancePanelsUseCase loadUserBalancePanel;
    private final ModifyUserBalancePanelUseCase modifyUserBalancePanel;
    private final DeleteUserBalancePanelUseCase deleteUserBalancePanel;
    private static final String ACTIVE_PAGE = "activePage";
    private static final String ASSETS_LIST = "assetsList";
    private static final String USERNAME = "username";
    private static final String ROUTE_BALANCE = Route.REDIRECT + Route.FINANCE + Route.USER + Route.BALANCE;

    public BalanceController(SaveUserBalancePanelUseCase saveUserBalancePanel, LoadUserBalancePanelsUseCase loadUserBalancePanel, ModifyUserBalancePanelUseCase modifyUserBalancePanel, DeleteUserBalancePanelUseCase deleteUserBalancePanel) {
        this.saveUserBalancePanel = saveUserBalancePanel;
        this.loadUserBalancePanel = loadUserBalancePanel;
        this.modifyUserBalancePanel = modifyUserBalancePanel;
        this.deleteUserBalancePanel = deleteUserBalancePanel;
    }

    @ModelAttribute()
    public void setActivePage(Model model){
        model.addAttribute(ACTIVE_PAGE, Route.VIEW_FINANCE);
    }

    @GetMapping()
    public String displayUserBalancePanel(Model model,
                                          @RequestParam String username){

        List<Asset> assetsList = loadUserBalancePanel.loadUserBalancePanels(username);
        model.addAttribute(ASSETS_LIST, assetsList);
        return Route.VIEW_BALANCE;
    }

    @PostMapping(Route.ADD)
    public String addUserBalancePanel(Model model,
                                      RedirectAttributes redirectAttributes,
                                      @ModelAttribute AssetAddForm assetAddForm){

        List<Asset> assetsList = loadUserBalancePanel.loadUserBalancePanels(assetAddForm.getUsername());
        model.addAttribute(ASSETS_LIST, assetsList);
        saveUserBalancePanel.saveUserBalancePanel(assetAddForm);
        redirectAttributes.addAttribute(USERNAME, assetAddForm.getUsername());
        return ROUTE_BALANCE;
    }

    @PostMapping(Route.MODIFY)
    public String modifyUserBalancePanel(RedirectAttributes redirectAttributes,
                                         @ModelAttribute AssetModifyForm assetModifyForm){

        modifyUserBalancePanel.modifyUserBalancePanel(assetModifyForm.getAssetId(), assetModifyForm.getAssetValue());
        redirectAttributes.addAttribute(USERNAME, assetModifyForm.getUsername());
        return ROUTE_BALANCE;
    }

    @PostMapping(Route.DELETE)
    public String deleteUserBalancePanel(RedirectAttributes redirectAttributes,
                                         @RequestParam Long assetId,
                                         @RequestParam String username){

        deleteUserBalancePanel.deleteUserBalancePanel(assetId);
        redirectAttributes.addAttribute(USERNAME, username);
        return ROUTE_BALANCE;
    }
}
