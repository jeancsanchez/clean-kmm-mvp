//
//  SplashPage.swift
//  iosOnze
//
//  Created by Jean  Carlos on 22/11/20.
//  Copyright © 2020 orgName. All rights reserved.
//

import SwiftUI

struct SplashView: View {
    @State var isActive:Bool = false
    
    var body: some View {
        VStack {
            if self.isActive {
                LoginView()
            } else {
                Text("Awesome Splash Screen!")
                    .font(Font.largeTitle)
            }
        }
        .onAppear {
            DispatchQueue.main.asyncAfter(deadline: .now() + 2.5) {
                withAnimation {
                    self.isActive = true
                }
            }
        }
    }
}

struct SplashPage_Previews: PreviewProvider {
    static var previews: some View {
        SplashView()
    }
}
