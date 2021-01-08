import SwiftUI
import Shared

struct LoginView: View {
    @State private var login: String = ""
    @State private var senha: String = ""
    @State private var showAlert = false
    @ObservedObject var viewState: LoginViewState

    private var presenter: LoginContractPresenter
    
    init() {
        self.viewState = LoginViewState()
        self.presenter = Injector.init().providesLoginPresenter()
        self.presenter.attachView(view: self.viewState)
     }
    
    var body: some View {
        VStack(alignment: .center, spacing: 16.0, content: {
            TextField("Login", text: $login)
                .padding(.all, 10)
                .lineLimit(1)
                .overlay(
                    RoundedRectangle(cornerRadius: 8.0)
                        .stroke(Color.gray)
                )
            TextField("Senha", text: $senha)
                .padding(.all, 10)
                .lineLimit(1)
                .overlay(
                    RoundedRectangle(cornerRadius: 8.0)
                        .stroke(Color.gray)
                )
            Button(action: {
                presenter.onLogin(login: login, password: senha)
            }, label: {
                Text("Entrar")
            })
            .alert(isPresented: $viewState.isError) {
                Alert(title: Text("Erro!"), message: Text(viewState.messageError ?? "Algo inesperado aconteceu"), dismissButton: .default(Text("OK"))
                )
            }
            .padding(.all, 30)
            
            if viewState.isLoading {
                if #available(iOS 14.0, *) {
                    ProgressView()
                } else {
                    Text("Carregando...")
                }
            }
        })
        .padding(.horizontal, 16)
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        LoginView()
    }
}
