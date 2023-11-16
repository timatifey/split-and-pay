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


	var body: some View {
		ZStack {
			Color(red: 243/255, green: 245/255, blue: 246/255)
				.ignoresSafeArea()
			VStack {
				HStack {
					Spacer()
					Button(action: {
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
				}
				.padding(EdgeInsets(top: 0, leading: 20, bottom: 0, trailing: 20))

				ScrollView {
					LazyVStack(alignment: /*@START_MENU_TOKEN@*/.center/*@END_MENU_TOKEN@*/, spacing: /*@START_MENU_TOKEN@*/nil/*@END_MENU_TOKEN@*/, content: {
						ForEach(viewModel.rooms) { room in
							RoomRow(room: room)
						}
					})
				}
				.padding(EdgeInsets(top: 10, leading: 20, bottom: 20, trailing: 20))
				.refreshable {

				}
			}
		}
	}
}


struct RoomsListView_Previews: PreviewProvider {
	static var previews: some View {
		RoomsListView()
	}
}
