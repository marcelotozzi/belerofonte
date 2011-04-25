package br.com.belerofonte.components;

import java.util.Arrays;

import br.com.belerofonte.controller.AccountController;
import br.com.belerofonte.controller.AdminController;
import br.com.belerofonte.controller.UserController;
import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.resource.ResourceMethod;
import br.com.caelum.vraptor.validator.ValidationMessage;
import br.com.caelum.vraptor.view.Results;

@Intercepts
public class AccountInterceptor implements Interceptor {
	private Account account;
	private Result result;
	
	public AccountInterceptor(Account account, Result result) {
		this.account = account;
		this.result = result;
	}

	public boolean accepts(ResourceMethod method) {
		//Intercepta se for admin
		return method.getResource().getType().isAssignableFrom(AdminController.class) || 
		// ou for diferente de User.form = register
			(	
					method.getResource().getType().isAssignableFrom(UserController.class) &&
					method.getMethod().getName().equals("form")
			) ||
		// ou seja diferente de Account.form = login
			(
					method.getResource().getType().isAssignableFrom(AccountController.class) && 
					method.getMethod().getName().equals("form")
			);
	}

	public void intercept(InterceptorStack stack, ResourceMethod method, Object resourceInstance) throws InterceptionException {
		if (!this.account.isAdmin()) {
			result.include("errors", Arrays.asList(new ValidationMessage(null,"Faça o Login para acessar sua conta.")));

			result.use(Results.logic()).redirectTo(AccountController.class).form();
		} else {
			stack.next(method, resourceInstance);
		}
	}
}