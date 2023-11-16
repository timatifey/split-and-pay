//
//  UserService.swift
//  Split and pay
//
//  Created by Матвей Борисов on 15.11.2023.
//

import Foundation
import Combine

struct UserDTO: Codable {
	let id: String
}

enum UserEndpoint: APIEndpoint {
	case createUser(name: String)

	var baseURL: URL {
		return URL(string: APIURL.apiURL)!
	}

	var path: String {
		switch self {
		case .createUser:
			return "/user/"
		}
	}

	var method: HTTPMethod {
		switch self {
		case .createUser:
			return .post
		}
	}

	var headers: [String: String]? {
		switch self {
		case .createUser:
			return ["Content-Type": "application/json"]
		}
	}

	var parameters: [String: Any]? {
		switch self {
		case .createUser(let name):
			return ["username": name]
		}
	}
}

protocol UserServiceProtocol {
	func loadUser() -> UserDTO?

	func createUser(name: String) -> AnyPublisher<UserDTO, Error>

	func clearUser()
}

class UserService: UserServiceProtocol {
	private let keychainProvider = KeychainProvider()

	func loadUser() -> UserDTO? {
		guard let id = keychainProvider.obtainAuthToken() else { return nil }
		return UserDTO(id: id)
	}
	
	let apiClient = URLSessionAPIClient<UserEndpoint>()

	func createUser(name: String) -> AnyPublisher<UserDTO, Error> {
		return apiClient.request(.createUser(name: name))
	}

	func clearUser() {
		keychainProvider.deleteAuthToken()
	}
}
