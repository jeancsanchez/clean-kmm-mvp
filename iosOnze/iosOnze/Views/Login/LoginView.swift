//
//  LoginLayout.swift
//  iosOnze
//
//  Created by Jean  Carlos on 18/12/20.
//  Copyright © 2020 orgName. All rights reserved.
//

import SwiftUI
import shared

struct LoginView: View {
    @State private var login: String = ""
    @State private var senha: String = ""
    private var presenter: LoginContractPresenter?
    
    init(presenter: LoginContractPresenter?) {
        self.presenter = presenter
    }
    
    var editLogin: some View {
        TextField("Login", text: $login)
            .padding(.all, 10)
            .lineLimit(1)
            .overlay(
                RoundedRectangle(cornerRadius: 8.0)
                    .stroke(Color.gray)
            )
    }
    
    var editSenha: some View {
        TextField("Senha", text: $senha)
            .padding(.all, 10)
            .lineLimit(1)
            .overlay(
                RoundedRectangle(cornerRadius: 8.0)
                    .stroke(Color.gray)
            )
        
    }
  
    var button: some View {
        Button(action: {
            self.presenter?.onLogin(login: self.login, password: self.senha)
        }, label: { Text("Entrar") })
            .padding(.all, 30)
    }
    
    var body: some View {
        VStack(alignment: .center, spacing: 16.0, content: {
            editLogin
            editSenha
            button
        })
        .padding(.horizontal, 16)
    }
}


struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        LoginView(presenter: nil)
    }
}
