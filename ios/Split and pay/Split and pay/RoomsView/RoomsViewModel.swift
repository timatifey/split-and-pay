//
//  RoomsViewModel.swift
//  Split and pay
//
//  Created by Матвей Борисов on 16.11.2023.
//

import Foundation
import Combine
import SwiftUI

class RoomsViewModel: ObservableObject {
	enum screenStates {
		case loading
		case empty
		case content(rooms: [RoomDTO])
	}

	private var cancellables = Set<AnyCancellable>()
	let roomService: RoomServiceProtocol
	@Published var state: screenStates = .loading

	init(roomService: RoomServiceProtocol) {
		self.roomService = roomService
	}

	func createRoom(name: String) {
		roomService.createRoom(name: name)
			.receive(on: RunLoop.main)
			.sink { error in
				// Todo error handling
			} receiveValue: { [weak self] _ in
				self?.loadRooms()
			}
			.store(in: &cancellables)
	}

	func loadRooms() {
		state = .loading
		roomService.getRooms()
			.receive(on: RunLoop.main)
			.sink { _ in
			} receiveValue: { [weak self] new_rooms in
				self?.state = .content(rooms: new_rooms)
			}
			.store(in: &cancellables)
	}

	func getRoom(id: Int) {
//		roomService.getRoom(id: id)
	}
}

