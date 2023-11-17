//
//  RoomsService.swift
//  Split and pay
//
//  Created by Матвей Борисов on 16.11.2023.
//

import Foundation
import Combine

struct RoomDTO: Identifiable, Codable {
	let id: Int
	let name: String
	let owner: OwnerDTO
	let createdAt: String
}

struct RoomDetailedDTO: Identifiable, Codable {
	let id: Int
	let name, createdAt: String
	let owner: OwnerDTO
	let users: [OwnerDTO]
	let receipt: [ReceiptDTO]
	let totalSum: Double
}

struct ReceiptDTO: Identifiable, Codable {
	let name: String
	let id, amount: Int
	let users: [OwnerDTO]
}

struct OwnerDTO: Codable {
	let id, username, shortName: String
}

enum RoomEndpoint: APIEndpoint {
	case createRoom(name: String)
	case getRooms
	case getRoom(id: Int)

	var baseURL: URL {
		return URL(string: APIURL.apiURL)!
	}

	var path: String {
		switch self {
		case .createRoom:
			return "/rooms/"
		case .getRooms:
			return "/rooms/"
		case .getRoom(let id):
			return "/rooms/\(id)"
		}
	}

	var method: HTTPMethod {
		switch self {
		case .createRoom:
			return .post
		case .getRooms:
			return .get
		case .getRoom:
			return .get
		}
	}

	var headers: [String: String]? {
		switch self {
		default:
			return ["Content-Type": "application/json"]
		}
	}

	var parameters: [String: Any]? {
		switch self {
		case .createRoom(let name):
			return ["name": name]
		case .getRooms:
			return nil
		case .getRoom:
			return nil
		}
	}
}

protocol RoomServiceProtocol {
	func createRoom(name: String) -> AnyPublisher<RoomDTO, Error>
	func getRooms() -> AnyPublisher<[RoomDTO], Error>
	func loadRoom(id: Int) -> AnyPublisher<RoomDetailedDTO, Error>
}

class RoomsService: RoomServiceProtocol {
	let apiClient = URLSessionAPIClient<RoomEndpoint>()

	func createRoom(name: String) -> AnyPublisher<RoomDTO, Error> {
		return apiClient.request(.createRoom(name: name))
	}
	
	func getRooms() -> AnyPublisher<[RoomDTO], Error> {
		return apiClient.request(.getRooms)
	}
	
	func loadRoom(id: Int) -> AnyPublisher<RoomDetailedDTO, Error> {
		return apiClient.request(.getRoom(id: id))
	}
}

