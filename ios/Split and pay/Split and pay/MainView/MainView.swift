//
//  MainView.swift
//  Split and pay
//
//  Created by Матвей Борисов on 15.11.2023.
//

import Foundation
import SwiftUI

struct MainView: View {
	@StateObject var viewModel = MainViewModel(userService: UserService())

	var body: some View {
		if let user = viewModel.user {
			RoomsListView()
				.environmentObject(viewModel)
				.transition(.opacity)
		} else {
			IntroduceView()
				.environmentObject(viewModel)
				.transition(.opacity)
		}
	}
}

struct MainView_Previews: PreviewProvider {
	static var previews: some View {
		MainView()
	}
}
