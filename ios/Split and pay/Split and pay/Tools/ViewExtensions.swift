//
//  ViewExtensions.swift
//  Split and pay
//
//  Created by Матвей Борисов on 15.11.2023.
//

import Foundation
import SwiftUI

extension View {
	func hideKeyboard() {
		let resign = #selector(UIResponder.resignFirstResponder)
		UIApplication.shared.sendAction(resign, to: nil, from: nil, for: nil)
	}
}
