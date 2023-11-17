//
//  RoomViewModel.swift
//  Split and pay
//
//  Created by Матвей Борисов on 17.11.2023.
//

import Foundation
import Combine

class RoomViewModel: ObservableObject {
	enum screenStates {
		case loading
		case empty
		case content(detailedInfo: RoomDetailedDTO)
	}

	private var cancellables = Set<AnyCancellable>()
	let roomService: RoomServiceProtocol
	@Published var state: screenStates = .empty
	@Published var price = ""

	init(roomService: RoomServiceProtocol) {
		self.roomService = roomService
	}

	func loadRoom(id: Int) {
		state = .loading
		price = "••••"
		roomService.loadRoom(id: id)
			.receive(on: RunLoop.main)
			.sink { error in
				//
			} receiveValue: { [weak self] detailedInfo in
				if detailedInfo.receipt.isEmpty {
					self?.state = .empty
					self?.price = "0"
				} else {
					self?.state = .content(detailedInfo: detailedInfo)
					self?.price = "\(detailedInfo.totalSum)"
				}
			}
			.store(in: &cancellables)
	}
}
