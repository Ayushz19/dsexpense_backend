package com.debsoc.expense.services.expense;

import com.debsoc.expense.dto.ExpenseDTO;
import com.debsoc.expense.entity.Expense;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


public interface ExpenseService {


    Expense postExpense(ExpenseDTO expenseDTO);

    List<Expense> getAllExpenses();

    Expense getExpenseById(Long id);


    Expense updateExpense(Long id, ExpenseDTO expenseDTO);

    void deleteExpense(Long id);

    Expense updateExpenseStatus(Long id , String status);

//    Expense postExpense(ExpenseDTO expenseDTO, MultipartFile file) throws IOException;


}
