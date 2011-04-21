package br.com.belerofonte.components;

import br.com.belerofonte.controller.ContaController;
import br.com.belerofonte.controller.HomeController;
import br.com.belerofonte.controller.UsuarioController;
import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.resource.ResourceClass;
import br.com.caelum.vraptor.resource.ResourceMethod;

@Intercepts
public class ContaInterceptor implements Interceptor {
	private Conta conta;
	private Result result;

	public ContaInterceptor(Conta conta, Result result) {
		this.conta = conta;
		this.result = result;
	}

	public boolean accepts(ResourceMethod method) {
		ResourceClass resource = method.getResource();
		//return true;
		return !(
					(
							resource.getType().isAssignableFrom(ContaController.class) || 
							resource.getType().isAssignableFrom(HomeController.class) ||
							(
									resource.getType().isAssignableFrom(UsuarioController.class) &&
									(
											method.getMethod().getName().equals("form") ||
											method.getMethod().getName().equals("criar")
									)
							)
					) 
					&& !this.conta.isLogado()
				);
	}

	public void intercept(InterceptorStack stack, ResourceMethod method, Object instance) throws InterceptionException {
		if (this.conta.isLogado()) {
			if (method.getResource().getType().isAssignableFrom(ContaController.class) && method.getMethod().getName().equals("form")) {
				this.result.redirectTo(ContaController.class).conta();
			} else {
				stack.next(method, instance);
			}
		} else {
			this.result.redirectTo(ContaController.class).form();
		}
	}
}