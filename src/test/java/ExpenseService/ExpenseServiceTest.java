package ExpenseService;

import ExpenseService.Exception.UnexpectedProjectTypeException;
import ExpenseService.Expense.ExpenseType;
import ExpenseService.Project.Project;
import ExpenseService.Project.ProjectType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;

class ExpenseServiceTest {
    @Test
    void should_return_internal_expense_type_if_project_is_internal() throws UnexpectedProjectTypeException {
        //given
        ProjectType projectType = ProjectType.INTERNAL;
        Project project = new Project(projectType, "myProject");
        //when
        ProjectType newProjectType = project.getProjectType();
        //then
        Assertions.assertEquals(newProjectType, projectType);
    }

    @Test
    void should_return_expense_type_A_if_project_is_external_and_name_is_project_A() throws UnexpectedProjectTypeException {
        //given
        ExpenseService service = new ExpenseService();
        ExpenseType eType = ExpenseType.EXPENSE_TYPE_A;
        ProjectType pType = ProjectType.EXTERNAL;
        //when
        Project project = new Project(pType, "Project A");
        ExpenseType projectTypeandName = service.getExpenseCodeByProjectTypeAndName(project);
        //then
        Assertions.assertEquals(project.getProjectType(), pType);
        Assertions.assertEquals(projectTypeandName, eType);


    }

    @Test
    void should_return_expense_type_B_if_project_is_external_and_name_is_project_B() throws UnexpectedProjectTypeException {
        //given
        ExpenseService service = new ExpenseService();
        ExpenseType eType = ExpenseType.EXPENSE_TYPE_B;
        ProjectType pType = ProjectType.EXTERNAL;
        //when
        Project project = new Project(pType, "Project B");
        ExpenseType projectTypeandName = service.getExpenseCodeByProjectTypeAndName(project);
        //then
        Assertions.assertEquals(project.getProjectType(), pType);
        Assertions.assertEquals(projectTypeandName, eType);
    }

    @Test
    void should_return_other_expense_type_if_project_is_external_and_has_other_name() throws UnexpectedProjectTypeException {
        //given
        ExpenseService service = new ExpenseService();
        ExpenseType eType = ExpenseType.OTHER_EXPENSE;
        ProjectType pType = ProjectType.EXTERNAL;
        //when
        Project project = new Project(pType, "Project C");
        ExpenseType projectTypeandName = service.getExpenseCodeByProjectTypeAndName(project);
        //then
        Assertions.assertEquals(project.getProjectType(), pType);
        Assertions.assertEquals(projectTypeandName, eType);
    }

    @Test
    void should_throw_unexpected_project_exception_if_project_is_invalid() throws UnexpectedProjectTypeException {
        //given
        ExpenseService expenseService = new ExpenseService();
        Project project=new Project(ProjectType.UNEXPECTED_PROJECT_TYPE,"project c");
        //when
        Assertions.assertThrows(UnexpectedProjectTypeException.class,()->expenseService.getExpenseCodeByProjectTypeAndName(project));
        
    }
}