//
//  MainViewModel.swift
//  Split and pay
//
//  Created by Матвей Борисов on 15.11.2023.
//

import Foundation
import Combine

class MainViewModel: ObservableObject {
	private var cancellables = Set<AnyCancellable>()
	let userService: UserServiceProtocol
	@Published var user: UserDTO?

	init(userService: UserServiceProtocol) {
		self.userService = userService
		self.user = userService.loadUser()
	}

	func createUser(name: String) {
		userService.createUser(name: name)
			.receive(on: RunLoop.main)
			.sink { error in
				// Todo error handling
			} receiveValue: { [weak self] user in
				self?.userService.saveUser(id: user.id)
				self?.user = user
			}
			.store(in: &cancellables)

	}

	func checkUserLogin() {
		user = userService.loadUser()
	}

	func clearUser() {
		userService.clearUser()
	}
}

