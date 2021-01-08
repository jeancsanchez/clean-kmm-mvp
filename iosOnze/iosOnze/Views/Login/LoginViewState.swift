import Shared

class LoginViewState: BaseViewState {
    
}

extension LoginViewState: LoginContractView {
    
    func showConflictAccount() {
        errorState(message: "Ja existe essa conta!")
    }
    
    func showEmailIsRequired() {
        errorState(message: "Preencha o campo de Email")
    }
    
    func showInvalidCredentials() {
        errorState(message: "Credenciais inválidas")
    }
    
    func showInvalidEmail() {
        errorState(message: "Email inválido")
    }
    
    func showInvalidPassword() {
        errorState(message: "Senha incorreta")
    }
    
    func showLoginNameInvalid() {
        errorState(message: "Nome de usuário incorreto")
    }
    
    func showLoginNameValid(email: String) {
        
    }
    
    func showLoginSuccess(user: Player) {
        successState()
    }
    
    func showNeedLoginWithEmail() {
        errorState(message: "Entre com seu Email")
    }
    
    
    func showError(exception: KotlinThrowable) {
        errorState(message: exception.message ?? "Algo errado aconteceu :s")
    }
    
    func showLoading() {
        self.resetStates()
        self.isLoading = true
    }
    
    func hideLoading() {
        self.isLoading = false
    }
}

