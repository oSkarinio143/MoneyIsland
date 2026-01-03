package pl.oskarinio.moneyisland.finance.BalanceBlock;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.oskarinio.moneyisland.finance.BalanceBlock.Dto.AssetFormRequest;
import pl.oskarinio.moneyisland.finance.BalanceBlock.Dto.AssetOperationFormRequest;
import pl.oskarinio.moneyisland.finance.BalanceBlock.UserBalanceUseCases.DeleteUserBalancePanelUseCase;
import pl.oskarinio.moneyisland.finance.BalanceBlock.UserBalanceUseCases.LoadUserBalancePanelsUseCase;
import pl.oskarinio.moneyisland.finance.BalanceBlock.UserBalanceUseCases.ModifyUserBalancePanelUseCase;
import pl.oskarinio.moneyisland.finance.BalanceBlock.UserBalanceUseCases.SaveUserBalancePanelUseCase;
import pl.oskarinio.moneyisland.shared.config.Route;

import java.util.List;

@Controller
@RequestMapping(Route.MAIN)
public class BalanceController {
    private final SaveUserBalancePanelUseCase saveUserBalancePanel;
    private final LoadUserBalancePanelsUseCase loadUserBalancePanel;
    private final ModifyUserBalancePanelUseCase modifyUserBalancePanel;
    private final DeleteUserBalancePanelUseCase deleteUserBalancePanel;
    private static final String ACTIVE_PAGE = "activePage";
    private static final String ASSETS_LIST = "assetsList";
    private static final String USERNAME = "username";

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

    @GetMapping(Route.USER + Route.FINANCE + Route.BALANCE)
    public String displayUserBalancePanel(Model model,
                                          @RequestParam String username){

        List<Asset> assetsList = loadUserBalancePanel.loadUserBalancePanels(username);
        model.addAttribute(ASSETS_LIST, assetsList);
        return Route.VIEW_BALANCE;
    }

    @PostMapping(Route.USER + Route.FINANCE + Route.BALANCE + Route.ADD)
    public String addUserBalancePanel(Model model,
                                      RedirectAttributes redirectAttributes,
                                      @ModelAttribute AssetFormRequest assetFormRequest){

        List<Asset> assetsList = loadUserBalancePanel.loadUserBalancePanels(assetFormRequest.getUsername());
        model.addAttribute(ASSETS_LIST, assetsList);
        saveUserBalancePanel.saveUserBalancePanel(assetFormRequest);
        redirectAttributes.addAttribute(USERNAME, assetFormRequest.getUsername());
        return Route.REDIRECT + Route.USER + Route.FINANCE + Route.BALANCE;
    }

    @PostMapping(Route.USER + Route.FINANCE + Route.BALANCE + Route.MODIFY)
    public String modifyUserBalancePanel(RedirectAttributes redirectAttributes,
                                         @ModelAttribute AssetOperationFormRequest assetOperationFormRequest){

        modifyUserBalancePanel.modifyUserBalancePanel(assetOperationFormRequest.getAssetId(), assetOperationFormRequest.getAssetValue());
        redirectAttributes.addAttribute(USERNAME, assetOperationFormRequest.getUsername());
        return Route.REDIRECT + Route.USER + Route.FINANCE + Route.BALANCE;
    }

    @PostMapping(Route.USER + Route.FINANCE + Route.BALANCE + Route.DELETE)
    public String deleteUserBalancePanel(RedirectAttributes redirectAttributes,
                                         @RequestParam Long assetId,
                                         @RequestParam String username){

        deleteUserBalancePanel.deleteUserBalancePanel(assetId);
        redirectAttributes.addAttribute(USERNAME, username);
        return Route.REDIRECT + Route.USER + Route.FINANCE + Route.BALANCE;
    }
}
