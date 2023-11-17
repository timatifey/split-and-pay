//
//  RoomsListView.swift
//  Split and pay
//
//  Created by Матвей Борисов on 15.11.2023.
//

import Foundation
import SwiftUI

struct RoomsListView: View {
	@EnvironmentObject var parentViewModel: MainViewModel
	@StateObject var viewModel = RoomsViewModel(roomService: RoomsService())
	@State var isCreatingRoom: Bool = false

	var body: some View {
		ZStack {
			Color(red: 243/255, green: 245/255, blue: 246/255)
				.ignoresSafeArea()
			VStack {
				HStack {
					Button {
						parentViewModel.clearUser()
					} label: {
						Text("Clear")
					}

					Spacer()
					Button(action: {
						isCreatingRoom = true
					}, label: {
						Text("+")
							.font(Font.system(size: 26, weight: .regular))
							.frame(maxWidth: .infinity)
							.foregroundStyle(Color.white)
							.padding(EdgeInsets(top: 0, leading: 0, bottom: 5, trailing: 0))
					})
					.frame(width: 80, height: 36)
					.background(Color(red: 0.27, green: 0.35, blue: 0.96))
					.clipShape(RoundedRectangle(cornerRadius: 75/2))
					.sheet(isPresented: $isCreatingRoom, onDismiss: {
						isCreatingRoom = false
					}, content: {
						RoomCreateView()
							.environmentObject(viewModel)
							.presentationDetents([.medium])
					})
				}
				.padding(EdgeInsets(top: 0, leading: 20, bottom: 0, trailing: 20))

				ScrollView {
					switch viewModel.state {
					case .loading:
						ProgressView()
							.controlSize(.large)
							.onAppear() {
								isCreatingRoom = false
							}
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
							Text("нажмите на ")
								.font(Font.system(size: 28, weight: .bold, design: .rounded))
								.multilineTextAlignment(.center)
							Text("+")
								.font(Font.system(size: 40, weight: .bold, design: .rounded))
								.multilineTextAlignment(.center)
								.foregroundStyle(Color(red: 0.27, green: 0.35, blue: 0.96))
							Text("чтобы разделить счет")
								.font(Font.system(size: 28, weight: .bold, design: .rounded))
								.multilineTextAlignment(.center)
						}
					case .content(let rooms):
						LazyVStack(alignment: .center, spacing: nil, content: {
							ForEach(rooms) { room in
								RoomRow(room: room)
							}
						})
					}
				}
				.padding(EdgeInsets(top: 10, leading: 20, bottom: 20, trailing: 20))
				.refreshable {
					viewModel.loadRooms()
				}
			}
		}
		.onAppear() {
			viewModel.loadRooms()
		}
	}
}


struct RoomsListView_Previews: PreviewProvider {
	static var previews: some View {
		RoomsListView()
	}
}
