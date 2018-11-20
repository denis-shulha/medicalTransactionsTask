package itsm.liquiBaseSample.webMvc.controllers;

import itsm.liquiBaseSample.domains.Transaction;
import itsm.liquiBaseSample.webMvc.dto.TransactionDto;
import itsm.liquiBaseSample.webMvc.services.transaction.TransactionCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionsController extends CustomEntityController<Transaction, TransactionDto> {

    @Autowired
    public TransactionsController(TransactionCrudService service) {
        super(service);
    }

    @RequestMapping(value="/getListByStateId", method = RequestMethod.GET)
    public List<TransactionDto> getTransactionsListByStateId(@RequestParam("stateId") Integer stateId) {
        return ((TransactionCrudService)service).findByStateId(stateId);
    }

    @RequestMapping(value="/getListByOwnerId", method = RequestMethod.GET)
    public List<TransactionDto> getTransactionsListByOwnerId(@RequestParam("ownerId") Integer ownerId) {
        return ((TransactionCrudService)service).findByOwnerId(ownerId);
    }

    @RequestMapping(value="/getListByOwnerLogin", method = RequestMethod.GET)
    public List<TransactionDto> getTransactionsListByOwnerLogin(@RequestParam("login") String login) {
        return ((TransactionCrudService)service).findByOwnerLogin(login);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void createTransaction(@RequestBody TransactionDto transaction) {
        service.save(transaction);
    }
}
