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
	private var cancellables = Set<AnyCancellable>()
	let roomService: RoomServiceProtocol
	@State var isLoading: Bool = true
	@Published var rooms: [RoomDTO] = []

	init(roomService: RoomServiceProtocol) {
		self.roomService = roomService
	}

	func createRoom(name: String) {
		roomService.createRoom(name: name)
			.receive(on: RunLoop.main)
			.sink { error in
				// Todo error handling
			} receiveValue: { [weak self] _ in
				self?.getRooms()
			}
			.store(in: &cancellables)
	}

	func getRooms() {
		isLoading = true
		roomService.getRooms()
			.receive(on: RunLoop.main)
			.sink { [weak self] _ in
				self?.isLoading = false
			} receiveValue: { [weak self] new_rooms in
				self?.isLoading = false
				self?.rooms = new_rooms
			}
			.store(in: &cancellables)
	}

	func getRoom(id: Int) {
//		roomService.getRoom(id: id)
	}
}

