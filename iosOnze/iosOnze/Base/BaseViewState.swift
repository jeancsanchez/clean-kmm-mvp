//
//  ResultState.swift
//  iosOnze
//
//  Created by Jean  Carlos on 31/12/20.
//  Copyright © 2020 orgName. All rights reserved.
//
import SwiftUI

class BaseViewState: ObservableObject {
    @Published var isLoading: Bool = false
    @Published private (set) var isSuccess: Bool = false
    @Published private (set) var messageError: String? = ""
    @Published var isError: Bool = false
    
    func resetStates() {
        isLoading = false
        isSuccess = false
        isError = false
        messageError = ""
    }
    
    func successState() {
        self.messageError = ""
        self.isError = false
        self.isSuccess = true
    }
    
    func errorState(message: String) {
        isError = true
        messageError = message
        isSuccess = false
    }
}
