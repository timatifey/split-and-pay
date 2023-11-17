//
//  ItemView.swift
//  Split and pay
//
//  Created by Матвей Борисов on 17.11.2023.
//

import SwiftUI

struct ItemView: View {
	var item: ReceiptDTO

	var body: some View {
		HStack {
			VStack {
				HStack {
					Text(item.name)
						.foregroundStyle(Color.black)
						.font(Font.system(size: 18, weight: .regular))
					Spacer()
				}
				.padding(EdgeInsets(top: 0, leading: 0, bottom: 1, trailing: 0))
				HStack {
					Text("\(item.amount) ₽")
						.font(Font.system(size: 16, weight: .bold))
					Spacer()
				}
			}
			.padding()

			HStack {
				ForEach((0..<(item.users.count < 3 ? item.users.count: 3)), id: \.self) { iterator in
					ZStack {
						Group {
							Circle()
								.foregroundColor(Color(red: 0.27, green: 0.35, blue: 0.96))
							Text(item.users[iterator].shortName)
								.foregroundStyle(Color.white)
						}
						.frame(width: 50, height: 50, alignment: .center)
						.shadow(color: .black.opacity(0.25), radius: 2, x: 2, y: 4)
					}
					.padding(EdgeInsets(top: 0, leading: -25, bottom: 0, trailing: 0))
				}
			}
			.environment(\.layoutDirection, .rightToLeft)
			.padding(EdgeInsets(top: 0, leading: 0, bottom: 0, trailing: 32))
		}
		.frame(height: 60)
		.padding(EdgeInsets(top: 5, leading: 0, bottom: 5, trailing: 10))
		.background(.white)
		.clipShape(RoundedRectangle(cornerRadius: 14))
	}
}

struct ItemView_Previews: PreviewProvider {
	static var previews: some View {
		ItemView(item: ReceiptDTO(name: "Пирожок с картошКАМ", id: 0, amount: 500, users: [
			OwnerDTO(id: "6553ed8c248dr16a0e3gU92e", username: "Чернобыльский Ежик", shortName: "ЧЕ"),
			OwnerDTO(id: "6553ed8c248dr16a0e3gU92e", username: "Чернобыльский Ежик", shortName: "ЧЕ"),
			OwnerDTO(id: "6553ed8c248dr16a0e3gU92e", username: "Чернобыльский Ежик", shortName: "ЧЕ")
		]))
	}
}
