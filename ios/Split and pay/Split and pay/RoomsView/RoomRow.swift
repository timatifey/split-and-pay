//
//  RoomRow.swift
//  Split and pay
//
//  Created by Матвей Борисов on 16.11.2023.
//

import Foundation
import SwiftUI

struct RoomRow: View {
	var room: RoomDTO

	var body: some View {
		HStack {
			VStack {
				HStack {
					Text(room.name)
						.fontWeight(.bold)
						.foregroundStyle(Color.black)
						.font(Font.system(size: 20, weight: .regular))
					Spacer()
				}
				.padding(EdgeInsets(top: 0, leading: 0, bottom: 1, trailing: 0))
				HStack {
					Text(parseDate(originDate: room.createdAt))
						.font(Font.system(size: 16, weight: .regular))
					Spacer()
				}
			}
			.padding(EdgeInsets(top: 0, leading: 20, bottom: 0, trailing: 0))

			Spacer()
			ZStack {
				Circle()
					.foregroundColor(Color(red: 0.27, green: 0.35, blue: 0.96))
				Text("F F")
					.foregroundStyle(Color.white)
			}
			.frame(height: 50)

		}
		.frame(height: 60)
		.padding(EdgeInsets(top: 5, leading: 0, bottom: 5, trailing: 10))
		.background(.white)
		.clipShape(RoundedRectangle(cornerRadius: 14))
	}

	private func parseDate(originDate: String) -> String {
		let dateFormatter = DateFormatter()
		dateFormatter.dateFormat = "yyyy-MM-dd'T'HH:mm:ss.SSS"
		if let date = dateFormatter.date(from: originDate) {
			dateFormatter.dateFormat = "dd/MM/YY в HH:mm"
			let text = dateFormatter.string(from: date)
			return text
		} else {
			return "когда-то"
		}
	}
}

#Preview {
	RoomRow(room: RoomDTO(id: 0, name: "Мясо", owner: OwnerDTO(id: "kjbhjb", username: "Олег", shortName: "О"), createdAt: "2023-11-14T15:22:33.091Z"))
}
