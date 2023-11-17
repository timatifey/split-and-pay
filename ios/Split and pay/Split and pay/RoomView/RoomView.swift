//
//  RoomView.swift
//  Split and pay
//
//  Created by Матвей Борисов on 17.11.2023.
//

import SwiftUI

struct RoomView: View {
	private let roomId: Int
	@StateObject var viewModel = RoomViewModel(roomService: RoomsService())

	init(id: Int) {
		roomId = id
	}

	var body: some View {
		ZStack {
			Color(red: 243/255, green: 245/255, blue: 246/255)
				.ignoresSafeArea()
			VStack {
				ScrollView {
					switch viewModel.state {
					case .loading:
						ProgressView()
							.controlSize(.large)
					case .empty:
						VStack {
							Image(systemName: "tray.fill")
								.foregroundStyle(Color(red: 0.27, green: 0.35, blue: 0.96))
								.font(.system(size: 72))
								.frame(height: UIScreen.main.bounds.height / 4, alignment: .bottom)
								.padding(EdgeInsets(top: 0, leading: 0, bottom: 20, trailing: 0))
							Text("Пока что тут пусто")
								.font(Font.system(size: 28, weight: .bold, design: .rounded))
								.multilineTextAlignment(.center)
						}
					case .content(let detailedInfo):
						LazyVStack(alignment: .center, spacing: nil, content: {
							ForEach(detailedInfo.receipt) { receipt in
								ItemView(item: receipt)
							}
						})
					}
				}
				.refreshable {
					viewModel.loadRoom(id: roomId)
				}

				HStack {
					Spacer()
					Text("Итого:")
						.foregroundStyle(Color.black)
						.font(Font.system(size: 22, weight: .regular))
					Text("\(viewModel.price) ₽")
						.foregroundStyle(Color.black)
						.font(Font.system(size: 22, weight: .bold))
					Spacer()
				}
				.frame(height: 40)
				.padding(EdgeInsets(top: 10, leading: 20, bottom: 10, trailing: 20))
				.background(Color.white)
				.clipShape(RoundedRectangle(cornerRadius: 14))
			}
			.padding()
		}
		.navigationTitle("Чеки")
		.onAppear() {
			viewModel.loadRoom(id: roomId)
		}
	}
}

struct RoomView_Previews: PreviewProvider {
	static var previews: some View {
		RoomView(id: 0)
	}
}
