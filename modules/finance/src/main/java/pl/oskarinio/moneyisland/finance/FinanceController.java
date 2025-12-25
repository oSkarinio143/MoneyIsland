package pl.oskarinio.moneyisland.finance;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.oskarinio.moneyisland.finance.BalanceBlock.Asset;
import pl.oskarinio.moneyisland.finance.BalanceBlock.AssetEntity;
import pl.oskarinio.moneyisland.finance.BalanceBlock.Dto.AssetFormRequest;
import pl.oskarinio.moneyisland.finance.BalanceBlock.Dto.AssetOperationFormRequest;
import pl.oskarinio.moneyisland.finance.BalanceBlock.MapStruct;
import pl.oskarinio.moneyisland.finance.BalanceBlock.UserBalanceUseCases.DeleteUserBalancePanelUseCase;
import pl.oskarinio.moneyisland.finance.BalanceBlock.UserBalanceUseCases.LoadUserBalancePanelsUseCase;
import pl.oskarinio.moneyisland.finance.BalanceBlock.UserBalanceUseCases.ModifyUserBalancePanelUseCase;
import pl.oskarinio.moneyisland.finance.BalanceBlock.UserBalanceUseCases.SaveUserBalancePanelUseCase;
import pl.oskarinio.moneyisland.shared.config.Route;

import java.util.List;

@Controller
@RequestMapping(Route.MAIN)
public class FinanceController {
    private final SaveUserBalancePanelUseCase saveUserBalancePanel;
    private final LoadUserBalancePanelsUseCase loadUserBalancePanel;
    private final ModifyUserBalancePanelUseCase modifyUserBalancePanel;
    private final DeleteUserBalancePanelUseCase deleteUserBalancePanel;
    private final MapStruct mapper;
    private final static String ACTIVE_PAGE = "activePage";

    public FinanceController(SaveUserBalancePanelUseCase saveUserBalancePanel, LoadUserBalancePanelsUseCase loadUserBalancePanel, ModifyUserBalancePanelUseCase modifyUserBalancePanel, DeleteUserBalancePanelUseCase deleteUserBalancePanel, MapStruct mapper) {
        this.saveUserBalancePanel = saveUserBalancePanel;
        this.loadUserBalancePanel = loadUserBalancePanel;
        this.modifyUserBalancePanel = modifyUserBalancePanel;
        this.deleteUserBalancePanel = deleteUserBalancePanel;
        this.mapper = mapper;
    }

    @GetMapping()
    public String displayMainPanel(Model model){
        model.addAttribute(ACTIVE_PAGE, Route.VIEW_MAIN_PANEL);
        return Route.VIEW_MAIN_PANEL;
    }

    @GetMapping(Route.USER + Route.FINANCE)
    public String displayFinancePanel(Model model){
        model.addAttribute(ACTIVE_PAGE, Route.VIEW_FINANCE);
        return Route.VIEW_FINANCE;
    }

    @GetMapping(Route.USER + Route.FINANCE + Route.BALANCE)
    public String displayUserBalancePanel(Model model,
                                          @RequestParam String username){
        System.out.println("username - " + username);

        List<Asset> assetsList = loadUserBalancePanel.loadUserBalancePanels(username);
        List<AssetEntity> assetEntitiesList = assetsList.stream()
                .map(mapper::toAssetEntity)
                .toList();

        model.addAttribute(ACTIVE_PAGE, Route.VIEW_FINANCE);
        model.addAttribute("assetsList", assetEntitiesList);

        return Route.VIEW_BALANCE;
    }

    @PostMapping(Route.USER + Route.FINANCE + Route.BALANCE + Route.ADD)
    public String addUserBalancePanel(Model model,
                                      RedirectAttributes redirectAttributes,
                                      @ModelAttribute AssetFormRequest assetFormRequest){
        System.out.println("user assets - " + loadUserBalancePanel.loadUserBalancePanels(assetFormRequest.getUsername()));
        List<Asset> assetsList = loadUserBalancePanel.loadUserBalancePanels(assetFormRequest.getUsername());
        System.out.println("dlugosc listy - " + assetsList);
        List<AssetEntity> assetEntitiesList = assetsList.stream()
                        .map(mapper::toAssetEntity)
                        .toList();

        System.out.println("dlugosc listy - " + assetEntitiesList);
        model.addAttribute(ACTIVE_PAGE, Route.VIEW_FINANCE);
        model.addAttribute("assetsList", assetEntitiesList);

        saveUserBalancePanel.saveUserBalancePanel(assetFormRequest);
        redirectAttributes.addAttribute("username", assetFormRequest.getUsername());
        return Route.REDIRECT + Route.USER + Route.FINANCE + Route.BALANCE;
    }

    @PostMapping(Route.USER + Route.FINANCE + Route.BALANCE + "/modify")
    public String modifyUserBalancePanel(Model model,
                                         RedirectAttributes redirectAttributes,
                                         @ModelAttribute AssetOperationFormRequest assetOperationFormRequest){

        System.out.println("id do edytowania - " + assetOperationFormRequest.getAssetId());

        modifyUserBalancePanel.modifyUserBalancePanel(assetOperationFormRequest.getAssetId(), assetOperationFormRequest.getAssetValue());

        model.addAttribute(ACTIVE_PAGE, Route.VIEW_FINANCE);
        redirectAttributes.addAttribute("username", assetOperationFormRequest.getUsername());

        return Route.REDIRECT + Route.USER + Route.FINANCE + Route.BALANCE;
    }

    @PostMapping(Route.USER + Route.FINANCE + Route.BALANCE + "/delete")
    public String deleteUserBalancePanel(Model model,
                                         RedirectAttributes redirectAttributes,
                                         @RequestParam Long assetId,
                                         @RequestParam String username){

        System.out.println("id do usunięcia - " + assetId);

        deleteUserBalancePanel.deleteUserBalancePanel(assetId);

        model.addAttribute(ACTIVE_PAGE, Route.VIEW_FINANCE);
        redirectAttributes.addAttribute("username", username);

        return Route.REDIRECT + Route.USER + Route.FINANCE + Route.BALANCE;
    }
}
