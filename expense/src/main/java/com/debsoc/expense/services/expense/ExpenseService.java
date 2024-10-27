package com.debsoc.expense.services.expense;

import com.debsoc.expense.dto.ExpenseDTO;
import com.debsoc.expense.entity.Expense;

import java.util.List;


public interface ExpenseService {


    Expense postExpense(ExpenseDTO expenseDTO);

    List<Expense> getAllExpenses();

    Expense getExpenseById(Long id);


    Expense updateExpense(Long id, ExpenseDTO expenseDTO);

    void deleteExpense(Long id);

    Expense updateExpenseStatus(Long id , String status);
}
