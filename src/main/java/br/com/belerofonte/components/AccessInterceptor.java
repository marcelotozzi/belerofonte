package br.com.belerofonte.components;

import java.util.Arrays;

import br.com.belerofonte.annotation.InterceptResource;
import br.com.belerofonte.annotation.NoInterceptMethod;
import br.com.belerofonte.controller.AccountController;
import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Lazy;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.resource.ResourceMethod;
import br.com.caelum.vraptor.validator.ValidationMessage;
import br.com.caelum.vraptor.view.Results;

@Intercepts
@Lazy
public class AccessInterceptor implements Interceptor {
	private Account account;
	private Result result;

	public AccessInterceptor(Account account, Result result) {
		this.account = account;
		this.result = result;
	}

	public boolean accepts(ResourceMethod method) {
		return method.getResource().getType().isAnnotationPresent(InterceptResource.class) || 
				(method.getResource().getType().isAnnotationPresent(InterceptResource.class)  && 
					!method.getMethod().isAnnotationPresent(NoInterceptMethod.class));
	}

	public void intercept(InterceptorStack stack, ResourceMethod method, Object resourceInstance) throws InterceptionException {
		if (this.account.isLogged()) {
			stack.next(method, resourceInstance);
		} else {
			result.include("errors", Arrays.asList(new ValidationMessage(null, "Faça o Login para acessar sua conta.")));
			result.use(Results.logic()).redirectTo(AccountController.class).form();
		}
	}
}