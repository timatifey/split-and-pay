//
//  RoomCreateView.swift
//  Split and pay
//
//  Created by Матвей Борисов on 17.11.2023.
//

import SwiftUI

struct RoomCreateView: View {
	enum Field: Hashable {
		case name
	}

	@FocusState private var focusedField: Field?
	@EnvironmentObject var parentViewModel: RoomsViewModel
	@State private var isLoading: Bool = false
	@State var roomName: String = ""

	var body: some View {
		ZStack {
			Color(red: 243/255, green: 245/255, blue: 246/255)
				.ignoresSafeArea()
			VStack {
				Spacer()
				Text("Придумайте название")
					.font(Font.system(size: 32, weight: .bold, design: .default))
					.foregroundStyle(Color(red: 60/255, green: 60/255, blue: 60/255))

				TextField("Его сюда писать", text: $roomName)
					.focused($focusedField, equals: .name)
					.frame(height: 50)
					.multilineTextAlignment(.center)
					.background(.white)
					.clipShape(RoundedRectangle(cornerRadius: 14))
					.onTapGesture {
						focusedField = .name
					}
					.padding(EdgeInsets(top: 20, leading: 20, bottom: 10, trailing: 20))
				Spacer()

				Button(action: {
					parentViewModel.createRoom(name: roomName)
					isLoading = true
					focusedField = nil
				}, label: {
					if isLoading {
						ProgressView()
							.frame(height: 50)
							.frame(maxWidth: .infinity)
							.foregroundStyle(Color.white)
							.background(Color(red: 0.27, green: 0.35, blue: 0.96))
							.clipShape(RoundedRectangle(cornerRadius: 14))
							.tint(.white)
					} else {
						Text($roomName.wrappedValue.isEmpty ? "Не путю" : "Создать, \(roomName)")
							.font(Font.system(size: 20, weight: .bold))
							.frame(height: 50)
							.frame(maxWidth: .infinity)
							.foregroundStyle(Color.white)
							.background( $roomName.wrappedValue.isEmpty ? .gray : Color(red: 0.27, green: 0.35, blue: 0.96))
							.clipShape(RoundedRectangle(cornerRadius: 14))
					}
				})
				.buttonStyle(PressEffectButtonStyle())
				.disabled($roomName.wrappedValue.isEmpty)
				.padding(.all, 20)

			}
			.contentShape(Rectangle())
			.onTapGesture {
				hideKeyboard()
			}
			.padding()
		}
	}
}

struct RoomCreateView_Previews: PreviewProvider {
	static var previews: some View {
		RoomCreateView()
	}
}
